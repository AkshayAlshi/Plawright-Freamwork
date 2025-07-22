package com.qa.aims.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.qa.aims.projectUtality.ProjectUtils;

public class HostelSessionMstPom {
    private Page page;

    private  String module = "//div[@class='module-card']//h3[contains (text(),' Hostel Management ')]";private String subModule = "//igx-nav-drawer//span[contains(text(),' Master ')]";
private String formName="//igx-nav-drawer[contains(@id,'project-menu')]//child::div[contains(text(),'Hostel Session Master ')]";
private String formHeader = "(//span[contains(text(),'Hostel Session Master ')]//parent::div)[1]";
private String sessionCode = "//*[@id=\"l_hsm_ses_cd-width-selector\"]//input";
private String sessionName = "//*[@id=\"l_hsm_sessname-width-selector\"]//input";
private String sessionTypeDrop ="//igx-simple-combo[contains(@id,'l_hsm_ayd_type')]//input";
private String acdamcYearDrop = "//igx-simple-combo[contains(@id,'l_hsm_ayd_yr_id')]//input";
private String formDateInput = "//igx-date-picker[contains(@id,'l_hsm_fr_dt')]//input";
private String toDateInput = "//igx-date-picker[contains(@id,'l_hsm_to_dt')]//input";
private String isActiveCheckbox = "//igx-checkbox[@id='l_hsm_isactive']";
private String gridList = "(//igx-grid[@id='l_grid_cclf_aca_session_mst_igx_grid']//igx-grid-cell[@role='gridcell'])[1]//div";
private String resetButton = "//button[@id='l_action_reset_btn-width-selector']";
private String submitButton = "//button[@id='l_action_save_btn-width-selector']";



public HostelSessionMstPom(Page page){
    this.page=page;
}


public void clickOnMSF(){
    ProjectUtils.selectMSF(page,module,subModule,formName);
}



public String getFormHeader(){
    Locator FORMHEADER=page.locator(formHeader);
    String text=FORMHEADER.textContent();
    System.out.println(text);
    return text;

}


public void enterSessionCode(String session_code){
    Locator SESSIONCODE=page.locator(sessionCode);
    if (SESSIONCODE.isVisible() || SESSIONCODE.isEnabled()==true){
        SESSIONCODE.fill(session_code);
    }else {
        System.out.println("SESSIONCODE field is maybe not enable or visible");
    }
}


    public void enterSessionName(String session_Name){
        Locator SESSIONNAME=page.locator(sessionName);
        if (SESSIONNAME.isVisible() || SESSIONNAME.isEnabled()==true){
            SESSIONNAME.fill(session_Name);
        }else {
            System.out.println("SESSIONNAME field is maybe not enable or visible");
        }
    }


    public void selectsessionTypeDrop(String dropdoenvalue){

    Locator SESSIONTYPE_DROP =page.locator(sessionTypeDrop);
    if (SESSIONTYPE_DROP.isEnabled()){
        ProjectUtils.enterAndSelectDrop(page, sessionTypeDrop,dropdoenvalue);
    }

}

    public void selectacdamcYearDrop(String dropdoenvalue) {

        Locator ACADAMICYEAR_DROP = page.locator(acdamcYearDrop);
        if (ACADAMICYEAR_DROP.isEnabled()) {
            ProjectUtils.enterAndSelectDrop(page, acdamcYearDrop, dropdoenvalue);
        }
   }

   public void enterformDate(String formDate){
    Locator FROMDATE=page.locator(formDateInput);

    if (FROMDATE.isEnabled() || FROMDATE.isVisible()==true){
        FROMDATE.click();
        FROMDATE.fill(formDate);
        System.out.println(">>> You enetred date is --"+ FROMDATE.innerText());

    }else {
        System.out.println(">>>>  There is issue in  'enterformDate' method ");
    }

   }


    public void entertoDate(String todate) {
        Locator TODATE = page.locator(toDateInput);

        if (TODATE.isEnabled() || TODATE.isVisible() == true) {
            TODATE.click();
            TODATE.fill(todate);
            System.out.println(">>> You enetred date is --" + TODATE.innerText());

        } else {
            System.out.println(">>>>  There is issue in  'entertoDate' method ");
        }
    }


    public boolean  veifyisActiveCheckbox(){

    Locator ISACTIVECHECKBOX=page.locator(isActiveCheckbox);
        boolean result =ISACTIVECHECKBOX.isVisible();
        return result;
    }


    /*
    Use this method to verify the grid record after submit the form
    */

    public String verifyGridRecord(String gridValue) {
        Locator gridListLocator = page.locator(gridList);

        if (gridListLocator.isVisible()) {
            String elementText = ProjectUtils.seeListingPageData(gridListLocator, gridValue);
            return elementText;
        } else {
            System.out.println("GRIDLIST is not present or not visible.");
            return null;
        }
    }



    public String verifyResetButton(){
     Locator RESETBUTTON=page.locator(resetButton);
        String text=RESETBUTTON.textContent();
        return text;
  }


  public void clickOnSubmitButton(){
        Locator SUBMITBUTTON=page.locator(submitButton);
        ProjectUtils.clickOnAnyButton(SUBMITBUTTON);
  }



}
