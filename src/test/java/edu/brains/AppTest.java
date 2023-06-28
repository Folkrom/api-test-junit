package edu.brains;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Unit test for simple App.
 */
@DisplayName("Testing a remote REST API endpoint")
public class AppTest 
{
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }
    
    @Test
    void shouldGetHttp200Response() {
        RestAssured
                .when()
                .get("/api/show")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldGet3Results() {
        String requestedBody = "{ \"limite\": 3, \"desde\": 0 }";

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(requestedBody)
                .when()
                    .get("/api/show")
                .then()
                    .assertThat()
                    .body("comics.size()", Is.is(3));
    }
    
}
