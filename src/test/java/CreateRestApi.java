import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import payloadFiles.JavaPayload;
import tau.ReuseableMethods;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(DataProviderRunner.class)
public class CreateRestApi {

    @Test
    @UseDataProvider("booksData")
    public void addBook(String language, String name){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Create
        String response = given()
                .queryParam("key", "qaclick")
                .header("Content-Type", "application/json")
                .body(JavaPayload.AddPlace(language, name))
                .when()
                .post("maps/api/place/add/json").
                then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = ReuseableMethods.rawToJson(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);
        System.out.println();


        //Update Place
        String newAddress = "Armenia";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\"place_id\":\"" + placeId + "\",\n" +
                        " \"address\": \"" + newAddress + "\",\n" +
                        "\"key\": \"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).log().all().extract().response().asString();
        JsonPath js2 = ReuseableMethods.rawToJson(getPlaceResponse);
        String currentAddress = js2.getString("address");
        System.out.println(currentAddress);

        Assert.assertEquals(currentAddress, "Armenia");
    }

    @DataProvider
    public static Object[][] booksData() {
        return new Object[][] {
                {"Chinese", "Sia"},
                {"japanese", "Gig"}
        };
    }
}