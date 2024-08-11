
import AllureSteps.CourierAllureSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import static Constants.ConstantAPI.SCOOTER_URL;

    public class СourierCreatingTest {
        CourierAllureSteps courierAllureSteps;

        @Before
        public void setUp() {
            RestAssured.baseURI = SCOOTER_URL;
            courierAllureSteps = new CourierAllureSteps();
        }

        @Test
        @DisplayName("Creating a new courier")
        @Description("Positive test of new courier create and delete")
        public void createNewCourier() {
            Response responseCreateCourier = courierAllureSteps.createCourier("GoslingTEST20","1337", "Райан");
            courierAllureSteps.checkCreatingCourierBody(responseCreateCourier);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST20","1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test
        @DisplayName("Creating a same courier")
        @Description("Negative test of same courier creating")
        public void createSameCourier () {
            courierAllureSteps.createCourier("GoslingTEST20","1337", "Райан");
            Response responseSameCourier = courierAllureSteps.createCourier("GoslingTEST20","1337", "Райан");
            courierAllureSteps.checkReCreatingSameCourierBody(responseSameCourier);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST20","1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test
        @DisplayName("Creating a courier with an existing login")
        @Description("Negative test of creating a existing courier")
        public void createExistingCourier () {
            courierAllureSteps.createCourier("GoslingTEST20","1337", "Райан");
            Response responseExistingCourier = courierAllureSteps.createCourier("GoslingTEST20","1337", "Райан");
            courierAllureSteps.checkReCreatingSameCourierBody(responseExistingCourier);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST20","1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty login field")
        @Description("Creating a courier with empty login field")
        public void creatingCourierWithoutLogin () {
            Response responseCourierWithoutLogin = courierAllureSteps.createCourier("","1337", "Райан");
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithoutLogin);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("","1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty login field and value null")
        @Description("Creating a courier with empty login field and value null")
        public void creatingCourierWithNullLogin () {
            Response responseCourierWithNullLogin = courierAllureSteps.createCourier(null,"1337", "Райан");
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithNullLogin);
            Response responseDeleteCourier = courierAllureSteps.courierDelete(null,"1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty password field")
        @Description("Creating a courier with empty password field")
        public void creatingCourierWithoutPassword () {
            Response responseCourierWithoutPassword = courierAllureSteps.createCourier("GoslingTEST","", "Райан");
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithoutPassword);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty password field and value null")
        @Description("Creating a courier with empty password field and value null")
        public void creatingCourierWithNullPassword () {
            Response responseCourierWithNullPassword = courierAllureSteps.createCourier("GoslingTEST",null, "Райан");
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithNullPassword);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST",null);
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty firstName field")
        @Description("Creating a courier with empty firstName field")
        public void creatingCourierWithoutFirstName () {
            Response responseCourierWithoutFirstName = courierAllureSteps.createCourier("GoslingTEST","1337", "");
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithoutFirstName);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }

        @Test // Тест падает
        @DisplayName("Creating a courier with empty firstName field and value null")
        @Description("Creating a courier with empty firstName field and value null")
        public void creatingCourierWithNullFirstName () {
            Response responseCourierWithNullFirstName = courierAllureSteps.createCourier("GoslingTEST","1337", null);
            courierAllureSteps.checkCreatingCourierWithoutDataBody(responseCourierWithNullFirstName);
            Response responseDeleteCourier = courierAllureSteps.courierDelete("GoslingTEST","1337");
            courierAllureSteps.checkDeleteCourierBody(responseDeleteCourier);
        }
    }
