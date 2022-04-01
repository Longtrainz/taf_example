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

import static framework.constants.Path.POST_BOOKING_RESPONSE_SCHEMA;
import static io.qameta.allure.Allure.step;

@Feature("Booking")
@Story("Booking API")
@Tag("json")
@ExtendWith({AllureJunit5.class})
public class ValidatePostBookingSchemaTest {
    private final Utils utils = new Utils();
    private final BookingService bookingService = new BookingService();

    @BeforeEach()
    void before() {
        bookingService.healthCheckRequest();
    }

    @Test
    @DisplayName("Create booking via API test")
    void postBookingTest() {
        // Get random Booking data object
        Booking expectedBookingData = utils.getRandomBookingData();

        // Create new Booking using POST API method and extract response
        Response response = bookingService.post(expectedBookingData);

        // Validate json schema
        utils.validateJsonSchema(response, POST_BOOKING_RESPONSE_SCHEMA);
    }
}
