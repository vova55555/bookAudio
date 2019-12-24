package libs;

import com.oracle.javafx.jmx.json.JSONException;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiManager {
    private String base_url;

    public ApiManager(ConfigProperties configProperties) {
        this.base_url = configProperties.base_url();
    }

    public ResponseBody createNewAccount(String login, String password) throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("login", login)
                .put("pass", password);
        return given()
                    .contentType(ContentType.URLENC)
                    .accept("*/*")
                    .header(new Header("Origin", base_url))
                    .header(new Header("X-Requested-With", "XMLHttpRequest"))
                    .formParams(requestParams.toMap())
                .when()
                    .post(base_url + "notSocialRegistrationConfirm")
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .response().getBody();
    }
}
