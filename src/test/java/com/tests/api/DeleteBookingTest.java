package com.tests.api;

import framework.api.services.BookingService;
import framework.helpers.Utils;
import framework.models.booking.payload.Booking;
import framework.models.booking.response.BookingResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.qameta.allure.Allure.step;

@Feature("Booking")
@Story("Booking API")
@Tag("api")
@ExtendWith({AllureJunit5.class})
public class DeleteBookingTest {
    private int createdBookingId;
    private final Utils utils = new Utils();
    private final BookingService bookingService = new BookingService();

    @BeforeEach()
    void createBookingForDeletion() {
        bookingService.healthCheckRequest();

        step("Create booking and extract it's booking id for further deletion", () -> {
            Booking booking = utils.getRandomBookingData();
            createdBookingId = bookingService.post(booking).as(BookingResponse.class).bookingid();
        });
    }

    @Test
    @DisplayName("Delete booking via API test")
    void deleteBookingTest() {
        bookingService.delete(createdBookingId);
        step(String.format(
                "Verify booking id #%s doesn't exist by making GET request", createdBookingId), () -> {
            bookingService.get(createdBookingId, 404);
        });
    }
}
