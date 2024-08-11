import AllureSteps.CourierAllureSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static Constants.ConstantAPI.SCOOTER_URL;

public class CourierLoginTest {
    CourierAllureSteps courierAllureSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
        courierAllureSteps = new CourierAllureSteps();
    }

    @Test
    @DisplayName("Positive courier login")
    @Description("Successful courier login")
    public void courierLogin () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response loginResponse = courierAllureSteps.courierLoginProfile("GoslingTEST","1337");
        courierAllureSteps.checkCourierIdIsNotNull(loginResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login with wrong login field")
    @Description("Courier login with wrong login field - 404")
    public void courierWrongLogin () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response wrongLoginResponse = courierAllureSteps.courierLoginProfile("GoslingTET","1337");
        courierAllureSteps.checkUserInputMissDataBody(wrongLoginResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login with wrong login field")
    @Description("Courier login with wrong login field - 404")
    public void courierWrongPassword () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response wrongPasswordResponse = courierAllureSteps.courierLoginProfile("GoslingTEST","1336");
        courierAllureSteps.checkUserInputMissDataBody(wrongPasswordResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login without login field")
    @Description("Courier login without login field - 400")
    public void courierWithoutLogin () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response withoutLoginResponse = courierAllureSteps.courierLoginProfile("","1337");
        courierAllureSteps.checkNoneInputUserDataBody(withoutLoginResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login without password field")
    @Description("Courier login without password field - 400")
    public void courierWithoutPass () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response withoutPasswordResponse = courierAllureSteps.courierLoginProfile("GoslingTEST","");
        courierAllureSteps.checkNoneInputUserDataBody(withoutPasswordResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login without login field and value null")
    @Description("Courier login without login field and value null - 400")
    public void courierNullLogin () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response withoutLoginResponse = courierAllureSteps.courierLoginProfile(null,"1337");
        courierAllureSteps.checkNoneInputUserDataBody(withoutLoginResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }

    @Test
    @DisplayName("Courier login without password field and value null")
    @Description("Courier login without password field and value null - 400")
    public void courierNullPassword () {
        courierAllureSteps.createCourier("GoslingTEST","1337", "Райан");
        Response withoutPasswordResponse = courierAllureSteps.courierLoginProfile("GoslingTEST",null);
        courierAllureSteps.checkNoneInputUserDataBody(withoutPasswordResponse);
        Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
        courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
    }
}
