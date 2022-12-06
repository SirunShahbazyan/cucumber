//package runners;
//
//import org.hamcrest.Matchers.*;
//import org.junit.jupiter.api.Test;
//
//import static com.google.common.base.Predicates.equalTo;
//import static io.restassured.RestAssured.given;
//import io.restassured.response.Response;
////import static org.apache.commons.codec.digest.UnixCrypt.body;
//
////import static org.apache.commons.codec.digest.UnixCrypt.*;
//public class RequestZipCode {
//
//
//    @Test
//    public void requestUsZipCode(){
//        given().
//                when().
//                get("http://zippopotam.us/us/90210").
//                then().
//                assertThat().
//             body("places[0].'place name'","New York");



//        RestAssured.baseURI = "http://api.zippopotam.us/us/90210";
//        RequestSpecification httpRequest = given();
//        Response response = httpRequest.get("http://api.zippopotam.us/us/90210");
//        ResponseBody body = response.getBody();
//        System.out.println(body.asString());
//        System.out.println(body.asString().contains("Beverly Hills"));
//        body("places[0].'place name'",equalTo("Beverly Hills"));
//
//    }
//
//}

package runners;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter1Test {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
