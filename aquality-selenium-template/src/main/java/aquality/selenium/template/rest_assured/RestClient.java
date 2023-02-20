package aquality.selenium.template.rest_assured;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import aquality.selenium.template.configuration.Configuration ;
import static io.restassured.RestAssured.given;

public class RestClient {
    private static final String BASE_URL = Configuration.getApiUrl();

    public static RequestSpecification getBaseSpec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.ANY);
    }
}
