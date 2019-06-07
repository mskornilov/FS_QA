package testpreps;

import utils.TestUtils;

import static constants.Endpoints.*;
import static constants.Files.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RepoPrep {

    public static void createRepo(){
        given()
                .body(TestUtils.resourceToJSONObject(NEW_REPO_JSON)).
        when()
                .post(CREATE_REPO);
    }

    public static void deleteRepo(){
        given().
        when()
                .delete(DELETE_REPO);
    }

    public static int createMilestone(){
        return
            given()
                    .body(TestUtils.resourceToJSONObject(NEW_MILESTONE_JSON)).
            when()
                    .post(CREATE_MILESTONE).
            then()
                    .contentType(JSON)
                    .extract()
                    .response()
                    .jsonPath()
                    .getInt("number");
    }
}
