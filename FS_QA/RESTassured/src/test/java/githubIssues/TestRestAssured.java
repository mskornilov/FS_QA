package githubIssues;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;

import static constants.Endpoints.*;
import static constants.Files.*;
import static constants.Users.USER_PASSWORD;
import static constants.Users.USER_USERNAME;
import static org.assertj.core.api.Assertions.*;

import models.Issue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import testpreps.RepoPrep;
import testpreps.RequestPrep;
import utils.TestUtils;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


@TestInstance(Lifecycle.PER_CLASS)
public class TestRestAssured {
    private Issue issue;

    @BeforeAll
    void prepareRepoAndReqSpec(){
        RestAssured.requestSpecification = RequestPrep.prapareRequestSpec();
        RepoPrep.createRepo();
        RepoPrep.createMilestone();
    }


    @BeforeEach
    void createIssue(){
        issue =
                given()
                        .body(TestUtils.resourceToJSONObject(NEW_ISSUE_JSON)).
                when()
                        .post(CREATE_ISSUE).
                then()
                        .contentType(JSON)
                        .extract()
                        .response()
                        .as(Issue.class);
    }

    @Test
    void testNewIssue(){
        List<Integer> ids =
                given().
                when()
                        .get(LIST_OF_ISSUES).
                then()
                        .contentType(JSON)
                        .extract()
                        .response()
                        .jsonPath()
                        .getList("id", Integer.class);
        assertThat(ids).contains(issue.getId());
    }

    @Test
    void testBodyEdit(){
        JsonObject newBodyJSON = TestUtils.resourceToJSONObject(NEW_ISSUE_BODY);
        String newBodyOfIssue = newBodyJSON.get("body").getAsString();

        given().body(newBodyJSON).
        when().patch(editIssue(issue.getNumber()));

        Issue editedIssue =
                given().
                when()
                        .get(getSingleIssue(issue.getNumber())).
                then()
                        .contentType(JSON)
                        .extract()
                        .response()
                        .as(Issue.class);
        assertThat(newBodyOfIssue).isEqualTo(editedIssue.getBody());
    }

    @Test
    void testLockingIssue(){
        given().param("lock_reason", "too_heated").
        when().put(lockIssue(issue.getNumber()));

        given()
                .auth()
                .preemptive()
                .basic(USER_USERNAME, USER_PASSWORD)
                .baseUri(BASE_URI)
                .body(TestUtils.resourceToJSONObject(NEW_COMMENT_JSON)).
        when().post(postComment(issue.getNumber())).
        then().statusCode(403);
    }

    @AfterAll
    void deleteRepo(){
        RepoPrep.deleteRepo();
    }

}
