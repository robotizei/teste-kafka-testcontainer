package br.com.kafka;

import br.com.kafka.model.Connectors;
import br.com.kafka.predicates.StatusRequestPredicate;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import io.restassured.http.ContentType;
import lombok.Getter;
import lombok.extern.java.Log;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import static br.com.kafka.model.TFAConnectors.*;
import static io.restassured.RestAssured.given;

@Log
public class KafkaComposeUtils {

    @Getter
    static DockerClient client = DockerClientFactory
            .instance()
            .client();
    
    @Getter
    private static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/docker-compose/docker-compose-all.yml"))
                    .withExposedService("connect", 8083,
                            Wait.forHttp("/connectors/")
                                    .forStatusCodeMatching(new StatusRequestPredicate())
                                    .withStartupTimeout(Duration.ofSeconds(150))
                    )
                    .withPull(true)
                    .withLocalCompose(true);

    public static void startContainers() {
        environment.start();
    }

    public static void stopContainers() {
        environment.stop();
        environment.close();
    }

    public static void addConnector(File connector) {
        given()
                .body(connector)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .post("http://localhost:8083/connectors")
                .then()
                .log().all()
                .statusCode(new StatusRequestPredicate())
                .extract()
                .response();

    }

    public static void removeConnector(String connectorName) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .delete("http://localhost:8083/connectors/" + connectorName + "/")
                .then()
                .log().all()
                .statusCode(new StatusRequestPredicate())
                .extract()
                .response();

    }

    public static Optional<ContainerState> getContainerInfo(String containerServiceName) {
        return environment.getContainerByServiceName(containerServiceName);
    }

    public static void main(String[] args) {
        startContainers();
        addConnector(getFileJson(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_INCREMENTING_1));
        addConnector(getFileJson(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_TIMESTAMP_1));
        addConnector(getFileJson(AWS_RUMO_INTEG_SINK_TFA_PRISMA_0));
        getConnectors();
        removeConnector(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_INCREMENTING_1);
        removeConnector(AWS_RUMO_INTEG_SOURCE_TRANSLOGIC_TFA_TIMESTAMP_1);
        removeConnector(AWS_RUMO_INTEG_SINK_TFA_PRISMA_0);
        stopContainers();
    }

    private static Connectors[] getConnectors() {
        return
                given()
                        .when()
                        .get("http://localhost:8083/connectors")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(Connectors[].class);
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
