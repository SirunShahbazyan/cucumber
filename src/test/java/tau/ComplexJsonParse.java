package tau;

import io.restassured.path.json.JsonPath;
import payloadFiles.JavaPayload;

public class ComplexJsonParse {
    public static void main(String[] args) {


        JsonPath js = new JsonPath(JavaPayload.CoursePrice());

        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        //Print title of the first course
        String firstTitle = js.get("courses[0].title");
        System.out.println(firstTitle);
        System.out.println();

        //Print all courses titles and their prices
        for (int i = 0; i < count; i++) {
            String titles = js.get("courses[" + i + "].title");
            int prices = js.getInt("courses[" + i + "].price");

            System.out.println(titles);
            System.out.println(prices);
            System.out.println();
            System.out.println("Print number of courses sold by RPA title");

            if (titles.equalsIgnoreCase("RPA")) {
                int copiesTitle = js.get("courses[" + i + "].copies");
                System.out.println(copiesTitle);
                break;
            }
        }
    }
}