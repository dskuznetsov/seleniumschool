package dskuznetsov.Utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PublicApiAdaptor {
    public static Response helloRequest(String xauthorizationHeader) {
        return given()
                .headers("X-authorization", xauthorizationHeader, "Accept", "application/json")
                .contentType("application/json")
                .when()
                .body("{}")
                .post("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/device/hello");
    }

    public static Response addFavoriteRequest(String xauthorizationHeader, String deviceUID, String category, String offer_id) {
        return given()
                .headers("X-authorization", xauthorizationHeader, "Accept", "application/json", "X-Device-UID", deviceUID)
                .contentType("application/json")
                .pathParams("category", category, "offer_id", offer_id)
                .when()
                .post("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/user/favorites/{category}/{offer_id}");
    }
}
