import AllureSteps.OrderAllureSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static Constants.ConstantAPI.SCOOTER_URL;

public class OrderGetListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Get list of orders")
    @Description("Get list of orders and chet that list is not null")
    public void getOrderList() {
        OrderAllureSteps orderAllureSteps = new OrderAllureSteps();
        Response response = orderAllureSteps.getOrderList();
        orderAllureSteps.checkOrderListNotNull(response);
    }
}
