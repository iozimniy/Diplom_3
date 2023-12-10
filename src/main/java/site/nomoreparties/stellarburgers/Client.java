package site.nomoreparties.stellarburgers;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    public static RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    public static RequestSpecification specAuth(String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization", accessToken)
                .baseUri(BASE_URI);
    }
}
