package aquality.selenium.template.rest_assured;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import aquality.selenium.template.configuration.Configuration ;
import static io.restassured.RestAssured.given;

public class RestClient {
    private static final String BASE_URL = Configuration.getApiUrl();
    private static final String NAME_PARAM_ACCESS_TOKEN = "access_token";
    private static final String NAME_PARAM_VERSION_API = "v";
    private static String versionApi;
    private static String accessToken;

    public static RequestSpecification getBaseSpec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.ANY)
                .queryParam(NAME_PARAM_ACCESS_TOKEN, accessToken)
                .queryParam(NAME_PARAM_VERSION_API, versionApi);
    }

    public static void setToken(String token){
        accessToken = token;
    }

    public static void setVersionApi(String version){
        versionApi = version;
    }
}
