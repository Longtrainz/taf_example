package com.tests.api;

import framework.api.services.BookingService;
import framework.helpers.Utils;
import framework.models.booking.payload.Booking;
import framework.models.booking.response.BookingResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static framework.constants.Path.GET_BOOKING_RESPONSE_SCHEMA;
import static io.qameta.allure.Allure.step;

@Feature("Booking")
@Story("Booking API")
@Tag("api")
@ExtendWith({AllureJunit5.class})
public class GetBookingTest {
    private int createdBookingId;
    private final Utils utils = new Utils();
    private final BookingService bookingService = new BookingService();

    @BeforeEach()
    void createBooking() {
        bookingService.healthCheckRequest();

        step("Create booking and extract it's booking id for GET request", () -> {
            Booking booking = utils.getRandomBookingData();
            createdBookingId = bookingService.post(booking).as(BookingResponse.class).bookingid();
        });
    }

    @Test
    @DisplayName("Get booking via API test")
    void getBookingTest() {
        Response response = bookingService.get(createdBookingId, 200);
        utils.validateJsonSchema(response, GET_BOOKING_RESPONSE_SCHEMA);
    }
}
