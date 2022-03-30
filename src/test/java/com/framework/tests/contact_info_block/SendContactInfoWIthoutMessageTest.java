package com.framework.tests.contact_info_block;

import com.framework.models.ContactInfo;
import com.framework.pages.HomePage;
import com.framework.tests.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.framework.constants.ErrorMessage.CONTACT_INCORRECT_SIZE_MESSAGE;
import static com.framework.constants.ErrorMessage.CONTACT_INFO_MUST_NOT_BE_BLANK_MESSAGE;


@Feature("Send Contact Info")
@Story("Contact Info")
@Tag("web")
@ExtendWith({AllureJunit5.class})
class SendContactInfoWIthoutMessageTest extends TestBase {
    // @formatter:off
    ContactInfo contactInfo = utils.getRandomContactInfo();
    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Send contact info without message")
    void sendContactInfoWithoutMessageTest() {
        // set empty message body
        contactInfo.message("");

        homePage.open()
                .sendContactInfo(contactInfo)
                .verifyErrorMessageDisplayed(CONTACT_INCORRECT_SIZE_MESSAGE)
                .verifyErrorMessageDisplayed(CONTACT_INFO_MUST_NOT_BE_BLANK_MESSAGE);
    }

}

