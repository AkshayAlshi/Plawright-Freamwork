package com.qa.aims.projectUtality;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.TimeoutError;
import com.qa.aims.Factory.PlaywrightFactory;

public class ProjectUtils extends PlaywrightFactory {
 public static Page page;



public static void aimsLogin2(){
    Locator USERNAME=page.locator("//div[@id='gmd_LoginId-width-selector']//input");
    Locator PASSWORD=page.locator("//div[@id='gmd_Password-width-selector']//input");
    String ACAYERDROPINPUT="//igx-simple-combo[@id='cclf_aca_year_mst']//input";
    String SESSIONDROPINPUT="//igx-simple-combo[@id='cclf_aca_session_mst']//input";
    Locator LOGINBUTTON =page.locator("//button[@id='gmd_Login-width-selector']");
    USERNAME.waitFor();
    USERNAME.type("SYSTEM");
    PASSWORD.type("pass1234");

    enterAndSelectDrop(page,ACAYERDROPINPUT,"2025-26");
    enterAndSelectDrop(page, SESSIONDROPINPUT,"RS24");
    clickOnAnyButton(LOGINBUTTON);
}


    public static void aimsLogin(Page page) {
        try {
//            page.navigate("http://mproaimslt.microproindia.com/mproaims/#/");

            Locator USERNAME = page.locator("//div[@id='gmd_LoginId-width-selector']//input");
            Locator PASSWORD = page.locator("//div[@id='gmd_Password-width-selector']//input");
            Locator LOGINBUTTON = page.locator("//button[@id='gmd_Login-width-selector']");

            String ACAYERDROPINPUT = "//igx-simple-combo[@id='cclf_aca_year_mst']//input";
            String SESSIONDROPINPUT = "//igx-simple-combo[@id='cclf_aca_session_mst']//input";

            USERNAME.type("SYSTEM");
            PASSWORD.type("pass1234");

//            page.locator(ACAYERDROPINPUT).type("2025-26");
            enterAndSelectDrop(page,ACAYERDROPINPUT,"2025-26");
//            page.keyboard().press("Enter");


            page.locator(SESSIONDROPINPUT).click();
            page.locator(SESSIONDROPINPUT).type("S26");
//            enterAndSelectDrop(page,SESSIONDROPINPUT,"SS25");
            page.keyboard().press("Enter");
            LOGINBUTTON.click();

            System.out.println(">>>> Login successful");

        } catch (Exception e) {
            System.err.println(">>>> Login failed: " + e.getMessage());
        }
    }


    public static void enterAndSelectDrop(Page page, String inputPath, String inputText){

        Locator input= ProjectUtils.page.locator(inputPath);
        Locator option = ProjectUtils.page.locator("//igx-combo-item[@role='option']//parent::igx-display-container");

        if (input.isVisible() || input.isEnabled() == true){
            input.click();
            input.type(inputText);

            if (option.isVisible() || option.isEnabled() == true){
                System.out.println(">>>>  You select this dropdown option  -: "+option.innerText());
                option.waitFor();
                option.click();
            }else {
                System.out.println(">>>> option is not visible or may be not enabled");
            }

        }else {
            System.out.println(" >>>>  There is probleam in  'enterAndSelectDrop' method ");
        }


    }


    public static  void SetDate(String dateInputPath,String Date){

        Locator dateinput= page.locator(dateInputPath);


        if (dateinput.isVisible() || dateinput.isEnabled() == true){
            String dateFieldText=dateinput.innerText();
            dateinput.click();
            dateinput.type(Date);
            System.out.println("You entered date   "+dateFieldText +" -  "+ Date);


        }else {
            System.out.println(" >>>>  There is probleam in  'SetDate' method ");
        }

   }



    public static void clickOnAnyButton(Locator button) {
        try {
            if (button.isVisible() || button.isEnabled()) {
                button.click();
            } else {
                System.out.println("Button is not visible or not enabled.");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking button: " + e.getMessage());
        }
    }


    public static  String seeListingPageData(Locator GRIDLIST, String textcode) {
        String getresult = null;
        try {
            getresult = GRIDLIST.textContent();

            if (getresult != null && getresult.contentEquals(textcode)) {
                System.out.println(">>>> Your code is already present in the grid list, please change the data.");
            } else {
                System.out.println(">>>> Your code is not present in the grid list, please proceed further.");
            }

        } catch (PlaywrightException e) {
            System.err.println(">>>> Playwright-related issue occurred while accessing the grid list: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println(">>>> NullPointerException: Possibly gridList is not located or returned null text.");
        } catch (Exception e) {
            System.err.println(">>>> Unexpected error while verifying listing page data: " + e.getMessage());
            e.printStackTrace();
        }
        return getresult;
    }



    public static void selectMSF(Page page, String module, String submodule, String form) {

        try {
            Locator MODULE = ProjectUtils.page.locator(module);
            MODULE.waitFor(); // optional, Playwright auto-waits on click
            if (MODULE.isVisible() && MODULE.isEnabled()) {
                MODULE.scrollIntoViewIfNeeded();
                MODULE.click();
            } else {
                System.out.println("Module element is not interactable: " + module);
            }

            Locator SUBMODULE = ProjectUtils.page.locator(submodule);
            SUBMODULE.waitFor();
            if (SUBMODULE.isVisible() && SUBMODULE.isEnabled()) {
                SUBMODULE.click();
            } else {
                System.out.println("Submodule element is not interactable: " + submodule);
            }

            Locator FORM = ProjectUtils.page.locator(form);
            FORM.waitFor();
            if (FORM.isVisible() && FORM.isEnabled()) {
                FORM.click();
            } else {
                System.out.println("Form element is not interactable: " + form);
            }

        } catch (TimeoutError te) {
            System.out.println("Timeout while waiting for one of the elements: " + te.getMessage());
        } catch (PlaywrightException pe) {
            System.out.println("Playwright exception occurred: " + pe.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception occurred in selectMSF: " + e.getMessage());
        }
    }












    }

























