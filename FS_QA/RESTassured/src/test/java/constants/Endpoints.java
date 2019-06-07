package constants;

import static constants.Repo.REPO_NAME;
import static constants.Users.OWNER_USERNAME;

public class Endpoints {
    public static final String BASE_URI = "https://api.github.com";

    // Milestones
    public static final String CREATE_MILESTONE = "/repos/" + OWNER_USERNAME + "/" + REPO_NAME + "/milestones";

    // Repos
    public static final String CREATE_REPO = "/user/repos";
    public static final String DELETE_REPO = "/repos/" + OWNER_USERNAME + "/" + REPO_NAME;

    // Issues
    private static final String ISSUES_BASE_URI = "/repos/" + OWNER_USERNAME + "/" + REPO_NAME + "/issues";
    public static final String CREATE_ISSUE = ISSUES_BASE_URI;
    public static final String LIST_OF_ISSUES = ISSUES_BASE_URI;

    public static String getSingleIssue(int numberOfIssue){
        return ISSUES_BASE_URI + "/" + numberOfIssue;
    }

    public static String editIssue(int numberOfIssue){
        return ISSUES_BASE_URI + "/" + numberOfIssue;
    }

    public static String lockIssue(int numberOfIssue){
        return ISSUES_BASE_URI + "/" + numberOfIssue + "/lock";
    }

    public static String postComment(int numberOfIssue){
        return ISSUES_BASE_URI + "/" + numberOfIssue + "/comments";
    }
}
