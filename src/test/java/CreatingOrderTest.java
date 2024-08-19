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
public class CreatingOrderTest {
    private final List<String> colour;

    public CreatingOrderTest (List<String> colour) {
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

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Positive creating order")
    @Description("Creating order with all types of colour")
    public void creatingOrder() {
        OrderAllureSteps orderAllureSteps = new OrderAllureSteps();
        OrderCreating orderCreating = new OrderCreating("Райан", "Госуслуги", "Магадан","5","+75880353535",7, "2024-12-08", "Я поведу", colour);
        Response orderCreatingResponse = orderAllureSteps.createOrder(orderCreating);
        orderAllureSteps.checkOrderTrackIsNotNull(orderCreatingResponse);
    }

}
