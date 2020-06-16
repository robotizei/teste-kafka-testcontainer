package br.com.kafka;

import br.com.kafka.predicates.StatusRequestPredicate;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import io.restassured.http.ContentType;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.http.NoHttpResponseException;
import org.awaitility.Awaitility;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kafka.model.TFAConnectors.*;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;

@Log
public class KafkaComposeUtils {
    public static final int POLL_INTERVAL = 5;
    public static final int TIMEOUT = 150;
    public static final String DOCKER_COMPOSE_ALL_YML = "src/test/resources/docker-compose/docker-compose-all.yml";
    public static final String URL_CONNECTORS = "http://localhost:8083/connectors";
    public static final int INITIAL_DELAY = 60;
    @Getter
    static DockerClient client = DockerClientFactory
            .instance()
            .client();

    private static DockerComposeContainer environment =
            new DockerComposeContainer(
                    new File(
                            DOCKER_COMPOSE_ALL_YML
                    ))
                    .withPull(true)
                    .withLocalCompose(true);

    public static void startContainers() {
        environment.start();
        awaitUrlResponse(URL_CONNECTORS);
    }

    private static void awaitUrlResponse(String url) {
        log.info("Await initial");
        Awaitility.with()
                .pollDelay(INITIAL_DELAY, SECONDS)
                .pollInterval(POLL_INTERVAL, SECONDS)
                .atMost(TIMEOUT, SECONDS)
                .await()
                .ignoreException(NoHttpResponseException.class)
                .until(() -> {
                    log.info("Await interation");
                    int status =
                            given()
                                    .when()
                                    .get(url)
                                    .then()
                                    .extract()
                                    .response()
                                    .statusCode();

                    return new StatusRequestPredicate().test(status);
                });
        log.info("Await finish");
    }

    public static void stopContainers() {
        environment.stop();
    }

    public static void addConnector(File connector) {
        given()
                .body(connector)
                .contentType(ContentType.JSON)
                .when()
                .post(URL_CONNECTORS.concat("/"))
                .then()
                .statusCode(new StatusRequestPredicate());
    }

    public static void removeConnector(String connectorName) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL_CONNECTORS + "/" + connectorName + "/")
                .then()
                .statusCode(new StatusRequestPredicate());
    }

    public static Optional<ContainerState> getContainerInfo(String containerServiceName) {
        return environment.getContainerByServiceName(containerServiceName);
    }

    public static void main(String[] args) {
        startContainers();
        addConnector(getFileJson(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_INCREMENTING_1));
        addConnector(getFileJson(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_TIMESTAMP_1));
        addConnector(getFileJson(AWS_RUMO_INTEG_SINK_TFA_PRISMA_0));
        log.info(getAllConnectors().stream().collect(Collectors.joining(",")));
        removeConnector(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_INCREMENTING_1);
        removeConnector(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_TIMESTAMP_1);
        removeConnector(AWS_RUMO_INTEG_SINK_TFA_PRISMA_0);
        stopContainers();
    }

    private static List<String> getAllConnectors() {
        return
                given()
                        .when()
                        .get(URL_CONNECTORS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as((Type) String[].class);
    }

    /**
     * @param containerId
     * @param awaitCompletion
     * @param command
     * @return output
     * @Example execCommand(result.get ().getContainerId(), true, "df", "-P")
     */
    public static String execCommand(String containerId, boolean awaitCompletion, String... command) {
        ExecCreateCmdResponse exec = client.execCreateCmd(containerId).withCmd(command).withTty(false)
                .withAttachStdin(true).withAttachStdout(true).withAttachStderr(true).exec();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String output = null;
        try {
            ExecStartResultCallback resultCallback = client.execStartCmd(exec.getId()).withDetach(false)
                    .withTty(true).exec(new ExecStartResultCallback(outputStream, System.err));
            if (awaitCompletion) {
                resultCallback.awaitCompletion();
            }
            output = new String(outputStream.toByteArray());
        } catch (InterruptedException e) {
            log.warning("Exception executing command {} on container {}" + Arrays.toString(command) +
                    containerId + e);
        }
        return output;
    }

    public static File getFileJson(String jsonFileName) {
        try {
            return new File(KafkaComposeUtils.class.getClassLoader().getResource("connectors/" + jsonFileName + ".json").toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
