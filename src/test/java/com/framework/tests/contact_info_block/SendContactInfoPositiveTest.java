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


@Feature("Send Contact Info")
@Story("Contact Info")
@Tag("web")
@ExtendWith({AllureJunit5.class})
class SendContactInfoPositiveTest extends TestBase {
    // @formatter:off
    ContactInfo contactInfo = utils.getRandomContactInfo();
    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Send contact info")
    void sendContactInfoTest() {
        homePage.open()
                .sendContactInfo(contactInfo)
                .verifyConfirmationMessageTitleDisplayed(contactInfo)
                .verifyConfirmationMessageSubjectDisplayed(contactInfo);
    }

}

