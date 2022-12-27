package tau;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RestApi {

    @Test
    public void Testing(){

        //Login scenario
        SessionFilter session = new SessionFilter();
        RestAssured.baseURI = "http://localhost:8082";
        String response = given().header("Content-Type", "application/json").body("{\n" +
                "    \"username\": \"Sirun\",\n" +
                "    \"password\": \"Flower111\"\n" +
                "}").log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
        String expectedMessage = "Hi, how are you?";

        //Add comment
        String addCommentResponse = given().pathParams("key","10110").log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \""+expectedMessage+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session)
                .when().post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201)
                .extract().response().asString();
        JsonPath js = new JsonPath(addCommentResponse);
        String commentId = js.getString("id");

        //Get issue
       String issueDetails = given().filter(session).pathParams("key","10110")
               .queryParam("fields", "comment")
                .when().get("/rest/api/2/issue/{key}").then().log().all()
                .extract().response().asString();

        System.out.println(issueDetails);

        JsonPath js1 = new JsonPath(issueDetails);
        int comments = js1.get("fields.comment.comments.size()");
        for (int i = 0; i < comments; i++) {
            String commentIdIssue = js1.get("fields.comment.comments["+i+"].id").toString();
            if (commentIdIssue.equalsIgnoreCase(commentId)) {
                String message = js1.get("fields.comment.comments["+i+"].body").toString();
                System.out.println(message);
                Assert.assertEquals(expectedMessage, message);
            };
        }
    }
}
