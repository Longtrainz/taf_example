package framework.api.services;

import framework.api.ApiBase;
import framework.models.booking.payload.Booking;
import framework.api.Route;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BookingService extends ApiBase {
    // @formatter:off

    @Step("Create new booking")
    public Response post(Booking booking) {
        return requestSpec()
                .body(booking)
            .when()
                .post(Route.BOOKING)
            .then()
                .spec(responseSpec(200))
                .extract()
                .response();
    }

    @Step("Update booking")
    public Response update(Booking booking, int bookingId) {
        return requestSpec()
                .pathParam("bookingId", bookingId)
                .cookie("token", getAuthToken())
                .body(booking)
            .when()
                .put(Route.BOOKING + "/{bookingId}")
            .then()
                .spec(responseSpec(200))
                .extract()
                .response();
    }

    @Step("Delete booking")
    public Response delete(int bookingId) {
        return requestSpec()
                .pathParam("bookingId", bookingId)
                .cookie("token", getAuthToken())
            .when()
                .delete(Route.BOOKING + "/{bookingId}")
            .then()
                .spec(responseSpec(201))
                .extract()
                .response();
    }

    @Step("Get booking")
    public Response get(int bookingId, int expectedStatusCode) {
        return requestSpec()
                .pathParam("bookingId", bookingId)
            .when()
                .get(Route.BOOKING + "/{bookingId}")
            .then()
                .spec(responseSpec(expectedStatusCode))
                .extract()
                .response();
    }

    @Step("Ping - health check request")
    public void healthCheckRequest() {
         requestSpec()
            .when()
                .get(Route.PING)
            .then()
                .spec(responseSpec(201));
    }

}
