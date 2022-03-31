package framework.models.booking.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates {

	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;
}