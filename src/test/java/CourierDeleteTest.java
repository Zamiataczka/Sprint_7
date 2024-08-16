import AllureSteps.CourierAllureSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static Constants.ConstantAPI.SCOOTER_URL;

public class CourierDeleteTest {
    CourierAllureSteps courierAllureSteps;

    @Before
    public void setUp () {
        RestAssured.baseURI = SCOOTER_URL;
        courierAllureSteps = new CourierAllureSteps();
    }

    @Test
    @DisplayName("Delete a courier")
    @Description("Positive test of new courier create and then delete")
    public void deleteCreatedCourier() {
        Response responseCreateCourier = courierAllureSteps.createCourier("GoslingTEST15","1337", "Райан");
        courierAllureSteps.checkCreatingCourierBody(responseCreateCourier);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST15","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test // Тест падает - неверная ошибка в response - 404 вместо - 400
    @DisplayName("Delete a courier")
    @Description("Delete a courier without ID")
    public void deleteMissCourier() {
        Response responseDeleteCourier = courierAllureSteps.courierWithoutIdDelete();
        courierAllureSteps.checkDeleteCourierWithoutIdResponse(responseDeleteCourier);
    }

    @Test // Тест падает - неверная ошибка в response - 404 вместо - 400
    @DisplayName("Delete a courier")
    @Description("Delete a courier with wrong ID")
    public void deleteWrongCourier() {
        Response responseDeleteCourier = courierAllureSteps.courierWrongDelete();
        courierAllureSteps.checkDeleteCourierWithWrongData(responseDeleteCourier);
    }
}
