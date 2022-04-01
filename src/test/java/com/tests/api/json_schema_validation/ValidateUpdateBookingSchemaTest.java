package com.tests.api.json_schema_validation;

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

import static framework.constants.Path.UPDATE_BOOKING_RESPONSE_SCHEMA;
import static io.qameta.allure.Allure.step;

@Feature("Booking")
@Story("Booking API")
@Tag("json")
@ExtendWith({AllureJunit5.class})
public class ValidateUpdateBookingSchemaTest {
    private int createdBookingId;
    private final Utils utils = new Utils();
    private final BookingService bookingService = new BookingService();

    @BeforeEach()
    void createBookingForUpdate() {
       bookingService.healthCheckRequest();

        step("Create booking and extract it's booking id for further update", () -> {
            Booking booking = utils.getRandomBookingData();
            createdBookingId = bookingService.post(booking).as(BookingResponse.class).bookingid();
        });
    }

    @Test
    @DisplayName("Validate json schema for PUT booking request")
    void updateBookingTest() {
        // Get random Booking data object
        Booking expectedBookingData = utils.getRandomBookingData();

        // Update new Booking using PUT API method and extract response
        Response response = bookingService.update(expectedBookingData, createdBookingId);

        // Validate json schema
        utils.validateJsonSchema(response, UPDATE_BOOKING_RESPONSE_SCHEMA);
    }
}
