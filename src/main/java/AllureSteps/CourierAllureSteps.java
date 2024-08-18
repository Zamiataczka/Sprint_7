package AllureSteps;

import Serialization.CourierCreating;
import Serialization.CourierLoginProfile;
import io.restassured.response.Response;


import io.qameta.allure.Step;

import static Constants.ConstantAPI.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CourierAllureSteps {
    @Step ("Create courier")
    public Response createCourier(String login, String password, String firstName) {
        CourierCreating courier = new CourierCreating(login, password, firstName);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_CREATING);
    }

    @Step("Status test creating courier - 201")
    public void checkCreatingCourierBody(Response response) {
        response
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Step("Status test double creating same courier - 409")
    public void checkReCreatingSameCourierBody (Response response) {
        response
                .then()
                .statusCode(409)
                .and()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Status test double creating courier without enough data - 400")
    public void checkCreatingCourierWithoutDataBody (Response response) {
        response
                .then()
                .statusCode(400)
                .and()
                .assertThat()
                .body("massage", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step ("Login courier in profile")
    public Response courierLoginProfile(String login, String password) {
        CourierLoginProfile courierLoginProfile = new CourierLoginProfile(login, password);
        return given()
                .header("Content-type", "application/json")
                .body(courierLoginProfile)
                .when()
                .post(COURIER_LOGIN);
    }

    @Step("Get courier ID")
    public Integer getCourierID(String login, String password) {
        return courierLoginProfile(login, password)
                .body()
                .as(CourierCreating.class).getId();
    }

    @Step("Check availability of the courier ID")
    public void checkCourierIdIsNotNull (Response response) {
        response
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("id", notNullValue());
    }

    @Step("Status test not found profile with missing data - 404")
    public void checkUserInputMissDataBody (Response response) {
        response
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Status test not found profile with none input user data - 400")
    public void checkNoneInputUserDataBody (Response response) {
        response
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Delete request")
    public String courierDeletePreparingToString(Integer courierID) {
        return COURIER_DELETE + courierID;
    }

    @Step("Delete courier from system")
    public Response courierDelete(String login, String password) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(courierDeletePreparingToString(getCourierID(login, password)));
    }

    @Step("Status test delete courier - 200")
    public void checkDeleteCourierBody(Response response) {
        response
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Step("Delete courier without ID")
    public String courierWithoutIdDeletePreparingToString() {
        String courierID = "";
        return COURIER_DELETE + courierID;
    }

    @Step("Status test delete courier - 400")
    public void checkDeleteCourierWithoutIdResponse(Response response) {
        response
                .then()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для удаления курьера"));
    }

    @Step("Delete courier without ID from system")
    public Response courierWithoutIdDelete() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(courierWithoutIdDeletePreparingToString());
    }

    @Step("Delete courier with non-existent ID")
    public String courierWrongDeletePreparingToString() {
        String courierID = "13125";
        return COURIER_DELETE + courierID;
    }

    @Step("Status test delete courier - 400")
    public void checkDeleteCourierWithWrongData(Response response) {
        response
                .then()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Курьера с таким id нет"));
    }

    @Step("Delete courier from system")
    public Response courierWrongDelete() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(courierWrongDeletePreparingToString());
    }
}
