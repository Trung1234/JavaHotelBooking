package com.example.hotel;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControlerTest {

    @LocalServerPort
    private int port;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("server.port", () -> "8080");
    }



    @Test
    public void testHelloEndpoint() {
        String jsonBody = "{\n" +
                "  \"username\": \"hai1@gmail.com\",\n" +
                "  \"password\": \"AB12345568\"\n" +
                "}";
        RequestSpecification request = given();
        request.baseUri("http://localhost:8080")
                .accept("application/json")
                .contentType("application/json")
                .body(jsonBody);

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");

//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = port;s


        // Extract token from the response
        response.prettyPrint();
        System.out.println("Token: " + response.jsonPath());
//        String token = response.jsonPath().getString("response");
//        System.out.println("Token: " + token);
//        // Test another API with the token
//        testAPIWithToken(token);

    }

    public static void testAPIWithToken(String token) {
        // Use the token to access a protected endpoint
        Response response = given()
                .baseUri("http://localhost")
                .port(8080)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get("/protected-endpoint");
        // Print the response
        System.out.println("Response: " + response.getBody().asString());
    }
}