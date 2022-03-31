package com.tests.contact_info_block;

import framework.models.contact_info.ContactInfo;
import framework.pages.HomePage;
import com.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@Feature("Send Contact Info")
@Story("Contact Info")
@Tag("web")
@ExtendWith({AllureJunit5.class})
class SendContactInfoFailingTest extends TestBase {
    // @formatter:off
    ContactInfo contactInfo = utils.getRandomContactInfo();
    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Send contact info always failing test")
    void sendContactInfoFailingTest() {
        // set invalid phone number
        contactInfo.phone("123");

        homePage.open()
                .sendContactInfo(contactInfo)
                .verifyConfirmationMessageTitleDisplayed(contactInfo)
                .verifyConfirmationMessageSubjectDisplayed(contactInfo);
    }
}

