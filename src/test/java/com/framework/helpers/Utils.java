package com.framework.helpers;

import com.framework.models.ContactInfo;
import com.github.javafaker.Faker;

import java.time.YearMonth;

public class Utils {
    private final Faker faker = new Faker();

    /**
     * Generate random contact info for sending message form
     *
     * @return contact info
     */
    public ContactInfo getRandomContactInfo() {
        return new ContactInfo()
                .name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .subject(faker.name().fullName())
                .message(faker.lorem().fixedString(20));
    }

    public static int getDaysNumberInMonth(int yearNumber, int monthNumber) {
        YearMonth yearMonthObject = YearMonth.of(yearNumber, monthNumber);
        return yearMonthObject.lengthOfMonth();
    }
}
