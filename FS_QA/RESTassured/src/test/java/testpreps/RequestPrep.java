package testpreps;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static constants.Endpoints.BASE_URI;
import static constants.Users.OWNER_PASSWORD;
import static constants.Users.OWNER_USERNAME;

public class RequestPrep {
    public static RequestSpecification prapareRequestSpec() {

        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(OWNER_USERNAME);
        preemptiveBasicAuthScheme.setPassword(OWNER_PASSWORD);

       return new RequestSpecBuilder()
               .setAuth(preemptiveBasicAuthScheme)
               .setBaseUri(BASE_URI)
               .build();
    }

}
