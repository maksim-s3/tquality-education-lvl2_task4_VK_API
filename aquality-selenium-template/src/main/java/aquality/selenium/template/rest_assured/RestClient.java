package aquality.selenium.template.rest_assured;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import aquality.selenium.template.configuration.Configuration ;
import static io.restassured.RestAssured.given;

public class RestClient {
    private static final String BASE_URL = Configuration.getApiUrl();
    private static String versionApi;
    private static String accessToken;

    public static RequestSpecification getBaseSpec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.ANY)
                .queryParam(RequestParams.ACCESS_TOKEN.toString(), accessToken)
                .queryParam(RequestParams.VERSION_API.toString(), versionApi);
    }

    public static void setToken(String token){
        accessToken = token;
    }

    public static void setVersionApi(String version){
        versionApi = version;
    }
}
