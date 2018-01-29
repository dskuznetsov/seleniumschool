package dskuznetsov;

import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import static dskuznetsov.Utils.PublicApiAdaptor.addFavoriteRequest;
import static dskuznetsov.Utils.PublicApiAdaptor.helloRequest;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetFavoritesTest {
    private PublicApi cfg = ConfigFactory.create(PublicApi.class);

    @Test
    public void shouldSeeCorrectCategory() {

        Response helloResponse = helloRequest(cfg.xauthorization());
        String deviceUID = helloResponse.header("X-Device-UID");

        addFavoriteRequest(cfg.xauthorization(), deviceUID, "cars", "1069102724-91169");

        Response getFavorites = given()
                .headers("X-authorization", cfg.xauthorization(), "Accept", "application/json", "X-Device-UID", deviceUID)
                .contentType("application/json")
                .when()
                .get("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/user/favorites/all");

        String getFavoriteCategory = getFavorites.jsonPath().get("offers[0].category");
        assertThat(getFavoriteCategory).isEqualTo("CARS");
    }

    @Test
    public void shouldSeeCorrectOfferId() {
        Response helloResponse = helloRequest(cfg.xauthorization());
        String deviceUID = helloResponse.header("X-Device-UID");

        addFavoriteRequest(cfg.xauthorization(), deviceUID, "cars", "1069102724-91169");

        Response getFavorites = given()
                .headers("X-authorization", cfg.xauthorization(), "Accept", "application/json", "X-Device-UID", deviceUID)
                .contentType("application/json")
                .when()
                .get("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/user/favorites/all");

        String getFavoriteId = getFavorites.jsonPath().get("offers[0].id").toString();
        assertThat(getFavoriteId).isEqualTo("1069102724-91169");
    }
}
