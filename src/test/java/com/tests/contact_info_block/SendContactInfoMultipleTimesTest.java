package com.tests.contact_info_block;

import framework.helpers.Utils;
import framework.models.contact_info.ContactInfo;
import framework.pages.HomePage;
import com.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.ParameterizedTest.INDEX_PLACEHOLDER;


@Feature("Send Contact Info")
@Story("Contact Info")
@Tag("web")
@ExtendWith({AllureJunit5.class})
class SendContactInfoMultipleTimesTest extends TestBase {
    // @formatter:off
    HomePage homePage = new HomePage();

    @MethodSource("getContactInfo")
    @ParameterizedTest(name = "Send contact info multiple times-" + INDEX_PLACEHOLDER)
    void sendMultipleContactInfoTest(ContactInfo contactInfo) {
        homePage.open()
                .sendContactInfo(contactInfo)
                .verifyConfirmationMessageTitleDisplayed(contactInfo)
                .verifyConfirmationMessageSubjectDisplayed(contactInfo);
    }

    static Stream<Arguments> getContactInfo() {
        return Stream.of(
                Arguments.of(new Utils().getRandomContactInfo()),
                Arguments.of(new Utils().getRandomContactInfo()),
                Arguments.of(new Utils().getRandomContactInfo()));
    }
}

