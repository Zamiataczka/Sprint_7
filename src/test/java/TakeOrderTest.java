import AllureSteps.CourierAllureSteps;
import AllureSteps.OrderAllureSteps;
import Serialization.OrderCreating;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static Constants.ConstantAPI.SCOOTER_URL;

@RunWith(Parameterized.class)
public class TakeOrderTest {

    private final List<String> colour;

    public TakeOrderTest (List<String> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters()
    public static Object[][] getTestData() {
        return new Object[][] {
                {List.of("")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
        };
    }

    OrderAllureSteps orderAllureSteps;
    OrderCreating order;

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
        orderAllureSteps = new OrderAllureSteps();
        order = new OrderCreating("Райан", "Госуслуги", "Магадан","5","+75880353535",7, "2024-12-08", "Я поведу", colour);
    }

    @Test // Тест падает
    @DisplayName("Positive taking order test")
    @Description("Taking a order")
    public void createOrder() {
        orderAllureSteps.createOrder(order);
        Response takeOrder = orderAllureSteps.acceptOrder(order);
        orderAllureSteps.checkOrderHasTaken(takeOrder);
    }
}
