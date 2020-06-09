package br.com.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static br.com.utils.PropertiesHelper.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.fail;

public class UtilsAPI {
    private static String typeUser = "TLG.CCO";

    public static void setTypeUser(String typeUser) {
        UtilsAPI.typeUser = typeUser;
    }

    public static RequestSpecification getRestAssured() {

        if (generateToken.equalsIgnoreCase("true")) {
            return given()
                    .header("perfilNome", typeUser)
                    .header("usuarioLogin", userSSO)
                    .header("usuarioNome", "Usuário DEV")
                    .header("Authorization", "bearer " + getTokenUser());
        } else {
            return given()
                    .header("perfilNome", typeUser)
                    .header("usuarioLogin", userSSO)
                    .header("usuarioNome", "Usuário DEV");
        }

    }

    private static String getTokenUser() {
        try {
            Response client = given()
                    .contentType(ContentType.URLENC.withCharset("UTF-8"))
                    .formParam("grant_type", "password")
                    .formParam("client_id", "frontend-developers")
                    .formParam("username", userSSO)
                    .formParam("password", passSSO)
                    .post(urlTokenAuthorization.concat("token"))
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            return new JsonPath(client.body().asString()).get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro ao requisitar token authorization.");
            return "";
        }
    }

    public static Response uploadFilePDF(String urlServer, File file) {
        // Specify your own location and file
        return getRestAssured()
                .multiPart("file", file, "application/pdf")
                .when()
                .post(urlServer)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response uploadFilePDF(String name, File file, String comentario, String urlServer) {
        // Specify your own location and file
        return getRestAssured()
                .multiPart(file)
                .when()
                .post(urlServer)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
