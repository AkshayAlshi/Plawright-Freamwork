package com.qa.aims.base;

import com.microsoft.playwright.Page;
import com.qa.aims.Factory.PlaywrightFactory;
import com.qa.aims.pages.HostelSessionMstPom;
import com.qa.aims.projectUtality.ProjectUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    protected Page page;
    protected Properties prop;
    protected PlaywrightFactory pf;
    protected HostelSessionMstPom HSP;

    @BeforeTest
    public void setup() {
        pf = new PlaywrightFactory();

        // Load properties from config file
        prop = pf.init_prop();
        if (prop == null || prop.isEmpty()) {
            throw new RuntimeException("Failed to load configuration properties.");
        }

        // Launch browser with properties
        page = pf.initBrowser(prop);
        if (page == null) {
            throw new RuntimeException("Browser initialization failed. Test aborted.");
        }

        // Set Playwright Page in utility class for static access
        ProjectUtils.page = page;

        // Perform login
        ProjectUtils.aimsLogin(page);

        // Initialize Page Object Model (POM) and perform module selection
        HSP = new HostelSessionMstPom(page);
        HSP.clickOnMSF();
    }

    @AfterTest
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
            System.out.println("Browser closed successfully.");
        } else {
            System.out.println("Page is null, browser may not have launched.");
        }
    }
}
