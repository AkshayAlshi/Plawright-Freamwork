package com.qa.aims.tests.com.qa;

import com.microsoft.playwright.Page;
import com.qa.aims.Factory.PlaywrightFactory;
import com.qa.aims.base.BaseTest;
import com.qa.aims.constants.AppConstants;
import com.qa.aims.pages.HostelSessionMstPom;
import com.qa.aims.projectUtality.ProjectUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HostelSessionMstTest  extends BaseTest {


    @Test (priority = 1)
    public void verifyHeader() {
        String actualHeaderText = HSP.getFormHeader();
        Assert.assertEquals(actualHeaderText, AppConstants.PAGE_HEADER);
    }

   @Test(priority = 2)
   public void VerifyenterSessionCode(){
        HSP.enterSessionCode("SCOO1");
   }

   @Test(priority = 3)
   public void verifySessionName(){
        HSP.enterSessionName("SUMMER");
   }


   @Test(priority = 4)
   public void selectsessionTypeDrop(){
        HSP.selectsessionTypeDrop("MAIN SUMMER");
   }

    @Test(priority = 5)
    public void selectacdamcYearDropM(){
        HSP.selectacdamcYearDrop("2024-25");
    }

    @Test(priority = 6)
    public void enterFormDate(){
        HSP.enterformDate("07/09/2024");
    }


    @Test(priority = 6)
    public void enterToDate(){
        HSP.entertoDate("06/03/2025");
    }


    @Test(priority = 7)
    public void verifyIsActiveCheck(){
        boolean result =HSP.veifyisActiveCheckbox();
        Assert.assertEquals(result,true);

    }

    @Test(priority = 8)
    public void verifyResetButton(){
        String text=HSP.verifyResetButton();
        Assert.assertEquals(text,"  Reset  ");
    }






}


