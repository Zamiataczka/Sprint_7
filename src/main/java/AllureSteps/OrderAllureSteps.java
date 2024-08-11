package AllureSteps;

import Serialization.CourierCreating;
import Serialization.OrderCreating;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Constants.ConstantAPI.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAllureSteps {
    @Step ("Create a new order")
    public Response createOrder(OrderCreating order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDERS);
    }

    @Step("Get track")
    public Integer getTrack(OrderCreating order) {
        return createOrder(order)
                .body()
                .as(OrderCreating.class).getTrack();
    }

    @Step ("Check that track of the order is not a null")
    public void checkOrderTrackIsNotNull (Response response) {
        response
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }


    @Step ("Request a list of orders")
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS);
    }

    @Step("Check that a list of orders is not null")
    public void checkOrderListNotNull (Response response) {
        response
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("orders", notNullValue());
    }

    @Step("Accept request")
    public String courierAcceptPreparingToString(Integer track) {
        return ACCEPT_ORDER + track;
    }

    @Step ("Accept a order")
    public Response acceptOrder(OrderCreating order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .put(courierAcceptPreparingToString(getTrack(order)));
    }

    @Step ("Checking that the order has been taken")
    public void checkOrderHasTaken (Response response) {
        response
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("ok", equalTo(true));
    }
}
