package tau;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import payloadFiles.JavaPayload;

public class SumValidation {

    @Test
    public void someOfCourses(){
        int sum = 0;
        JsonPath js = new JsonPath(JavaPayload.CoursePrice());
        int count = js.get("courses.size()");
        for (int i = 0; i < count; i++) {
            int prices = js.getInt("courses[" + i + "].price");
            int copies = js.get("courses[" + i + "].copies");
            int amount = prices*copies;
            System.out.println(amount);
            sum = sum + amount;
        }

        System.out.println(sum);
        int purchaseamount = js.getInt("dashboard.purchaseAmount");
        System.out.println();
        System.out.println(purchaseamount);
        Assert.assertEquals(sum, purchaseamount);
    }
}
