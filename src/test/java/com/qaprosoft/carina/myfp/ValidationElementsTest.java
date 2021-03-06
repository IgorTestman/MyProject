package com.qaprosoft.carina.myfp;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.myfp.common.*;
import com.qaprosoft.carina.myfp.utils.enums.ActivityLevelEnum;
import com.qaprosoft.carina.myfp.utils.enums.GoalsEnum;
import com.qaprosoft.carina.myfp.utils.constants.TextConstants;
import com.qaprosoft.carina.myfp.utils.enums.YouPageEnum;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ValidationElementsTest implements IAbstractTest, IMobileUtils, TextConstants {

    @TestRailCases(testCasesId = "00001-00009")
    @Test()
    @MethodOwner(owner = "IgorB")
    @TestLabel(name = "'First test'", value = {"mobile", "regression"})
    public void validationGoalButtonsTest() {
        //*TC #1 (Verify ability to switch the Goal)
        StartPageBase startPage = initPage(getDriver(), StartPageBase.class);
        SignUpPageBase signUpPage = startPage.clickOnSignUpButton();
        GoalsPageBase goalsPage = signUpPage.clickOnContinueButton();
        goalsPage.clickOnButton(GoalsEnum.LOSE_WEIGHT);
        Assert.assertTrue(goalsPage.isButtonActive(GoalsEnum.LOSE_WEIGHT.getName()),
                "Lose Weight button isn't selected");
        goalsPage.clickOnButton(GoalsEnum.MODIFY_MY_DIET);
        Assert.assertTrue(goalsPage.isButtonActive(GoalsEnum.MODIFY_MY_DIET.getName()),
                "Modify My diet button isn't selected");
        goalsPage.clickOnNextButton();
        goalsPage.clickOnNextButton();
        goalsPage.clickOnButton(GoalsEnum.LACK_OF_TIME);
        Assert.assertTrue(goalsPage.isButtonActive(GoalsEnum.LACK_OF_TIME.getName()),
                "Lack of time button isn't selected");
        Assert.assertTrue(goalsPage.isAchieveOfResultPageOpened(), "Achieve page isn't opened");
        goalsPage.clickOnNextButton();
        Assert.assertTrue(goalsPage.isHelpToLookYouWantPageOpened(), "Recommended help page isn't opened");
        goalsPage.clickOnNextButton();
        goalsPage.clickOnButton(GoalsEnum.LESS_SUGAR);
        Assert.assertTrue(goalsPage.isButtonActive(GoalsEnum.LESS_SUGAR.getName()),
                "'Less sugar' button isn't selected");
        goalsPage.clickOnNextButton();
        Assert.assertTrue(goalsPage.isMoreDetailsPageOpened(), "More Details page isn't opened");
        ActivityLevelPageBase activityLevelPage = goalsPage.clickOnNextButton();

        //*TC #2 (Verify ability to switch the Activity Level)
        YouPageBase youPage = activityLevelPage.selectActivity(ActivityLevelEnum.VERY_ACTIVE);
        youPage.clickOnBackButton();
        Assert.assertTrue(activityLevelPage.isButtonActive(ActivityLevelEnum.VERY_ACTIVE.getName()),
                "Very Active level button isn't selected");
        youPage = activityLevelPage.selectActivity(ActivityLevelEnum.ACTIVE);
        youPage.clickOnBackButton();
        Assert.assertTrue(activityLevelPage.isButtonActive(ActivityLevelEnum.ACTIVE.getName()),
                "Active level button isn't selected");
        activityLevelPage.clickOnNextButton();

        //*TC #3 (Verify ability to switch Gender)
        youPage.clickOnGenderButton(YouPageEnum.MALE);
        Assert.assertTrue(youPage.isGenderSelected(YouPageEnum.MALE.getName()), "Male gender isn't selected");
        youPage.clickOnGenderButton(YouPageEnum.FEMALE);
        Assert.assertTrue(youPage.isGenderSelected(YouPageEnum.FEMALE.getName()), "Female gender isn't selected");

        //*TC #4 (Verify valid age values)
        youPage.typeAge("64");
        Assert.assertEquals(youPage.getInputAge(), "64", "Age is incorrect");

        //*TC #5 (Verify location address via dropdown)
        youPage.selectCountryFromDropdown(YouPageEnum.ZAMBIA);
        Assert.assertTrue(youPage.isCountrySelected(YouPageEnum.ZAMBIA.getName()), "Country is not selected");
        youPage.clickOnNextButton();

        //*TC #6 (Verify input valid values on Height)
        //* 6.1 (Verify input values in Feet Inches)
        youPage.clickOnHeightButton();
        youPage.typeFirstHeight("5");
        Assert.assertEquals(youPage.getInputFirstHeight(), "5", "First height is wrong");
        youPage.typeSecondHeight("6");
        Assert.assertEquals(youPage.isInputSecondHeightPresent(), "6", "Second height is wrong");
        youPage.selectHeightUnitFromDropdown(YouPageEnum.FEET_INCHES);
        Assert.assertTrue(youPage.isHeightUnitSelected(YouPageEnum.FEET_INCHES.getName()),
                "Feet Inches units is not selected");

        //* 6.2 (Verify input values in Centimeters)
        youPage.selectHeightUnitFromDropdown(YouPageEnum.CENTIMETERS);
        Assert.assertTrue(youPage.isHeightUnitSelected(YouPageEnum.CENTIMETERS.getName()),
                "Units in centimeters are not selected");
        youPage.typeHeightInCentimeters("200");
        Assert.assertEquals(youPage.getInputFirstHeight(),
                "200", "Height in centimeters is wrong");
        youPage.clickOnSetButton();

        //*TC #7 (Verify input valid values on current Weight)
        //* 7.1 (Verify input values in 'Pounds')
        youPage.clickOnWeightButton();
        youPage.typeWeight(30.0);
        ///////////////* parsing String to int...
        Assert.assertEquals(youPage.getInputValue("30 lbs"), 30, "Weight is incorrect");
        youPage.clickOnWeightButton();
        youPage.selectWeightUnitFromDropdown(YouPageEnum.POUNDS);
        Assert.assertTrue(youPage.isWeightUnitSelected(YouPageEnum.POUNDS.getName()),
                "Weight Pounds unit is not selected");

        //* 7.2 (Verify input values in 'Kilograms')
        youPage.selectWeightUnitFromDropdown(YouPageEnum.KILOGRAMS);
        Assert.assertTrue(youPage.isWeightUnitSelected(YouPageEnum.KILOGRAMS.getName()),
                "Weight unit is not selected");
        youPage.typeWeight(400.0);
        Assert.assertEquals(youPage.getInputValue("400 kg"), 400, "Weight is incorrect");
        //////youPage.clickOnSetButton();

        //*TC #8 (Verify input values in Goal Weight)
        //* #8.1 (Verify input values in 'Kilograms')
        WeeklyGoalPageBase weeklyGoalPage = youPage.followToGoalWeight();
        weeklyGoalPage.typeWeight("400");
        Assert.assertEquals(weeklyGoalPage.getInputWeightText(),
                "400", "Weight value is incorrect");
        weeklyGoalPage.selectWeightUnitFromDropdown(YouPageEnum.KILOGRAMS);
        Assert.assertTrue(weeklyGoalPage.isWeightUnitSelected(YouPageEnum.KILOGRAMS.getName()),
                "Weekly Goal Weight in Kilogram unit is not selected");
        weeklyGoalPage.clickOnSetButton();

        //*TC #9.1 (Verify the ability to toggle the Weekly goal 'Kilograms')
        weeklyGoalPage.switchToWeeklyGoal(YouPageEnum.LOSE_ONE_KG_PER_WEEK);
        Assert.assertTrue(weeklyGoalPage.isWeeklyGoalSelected(YouPageEnum.LOSE_ONE_KG_PER_WEEK.getName()),
                "Weekly Goal Weight in kilogram is not switched");
        weeklyGoalPage.switchToWeeklyGoal(YouPageEnum.LOSE_ONE_POINT_TWENTY_FIVE_KG_PER_WEEK);
        Assert.assertTrue(weeklyGoalPage.isWeeklyGoalSelected
                        (YouPageEnum.LOSE_ONE_POINT_TWENTY_FIVE_KG_PER_WEEK.getName()),
                "Weekly Goal Weight in Kilogram is not switched");

        //*TC #8.2 (Verify input values in 'Pounds')
        weeklyGoalPage.clickToOpenDropdownWeight();
        weeklyGoalPage.selectWeightUnitFromDropdown(YouPageEnum.POUNDS);
        Assert.assertTrue(weeklyGoalPage.isWeightUnitSelected(YouPageEnum.POUNDS.getName()),
                "Weekly Goal Weight in Pounds unit is not selected");
        weeklyGoalPage.typeWeightAgain("170");
        Assert.assertEquals(weeklyGoalPage.getInputWeightText(),
                "170", "Pounds Weight is incorrect");
        weeklyGoalPage.clickOnSetButton();

        //*TC #9.2 (Verify the ability to toggle the Weekly goal 'Pounds')
        weeklyGoalPage.switchToWeeklyGoal(YouPageEnum.LOSE_ZERO_POINT_FIVE_LBS_PER_WEEK);
        Assert.assertTrue(weeklyGoalPage.isWeeklyGoalSelected(YouPageEnum.LOSE_ZERO_POINT_FIVE_LBS_PER_WEEK.getName()),
                "Weekly Goal Weight in Pounds is not switched");
        weeklyGoalPage.switchToWeeklyGoal(YouPageEnum.LOSE_ONE_POINT_FIVE_LBS_PER_WEEK);
        Assert.assertTrue(weeklyGoalPage.isWeeklyGoalSelected
                        (YouPageEnum.LOSE_ONE_POINT_FIVE_LBS_PER_WEEK.getName()),
                "Weekly Goal Weight in Pounds is not switched");


        //*TC #10 (Verify that password can include any category of symbols)
        CreateAccountPageBase createAccountPage = weeklyGoalPage.followToCreateAccountPage();
        createAccountPage.typePassword(VALUE_MIX);
        Assert.assertEquals(createAccountPage.getPassword(), VALUE_MIX, "Password is incorrect");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        createAccountPage.typePassword(LOWER_CASE_LETTERS);
        Assert.assertEquals(createAccountPage.getPassword(), LOWER_CASE_LETTERS, "Password is incorrect");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        createAccountPage.typePassword(UPPER_CASE_LETTERS);
        Assert.assertEquals(createAccountPage.getPassword(), UPPER_CASE_LETTERS, "Password is incorrect");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        createAccountPage.typePassword(DIGITS);
        Assert.assertEquals(createAccountPage.getPassword(), DIGITS, "Password is incorrect");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        createAccountPage.typePassword(SIGNS);
        Assert.assertEquals(createAccountPage.getPassword(), SIGNS, "Password is incorrect");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        //*TC #11 (Verify that password can include less than 10 characters)
        createAccountPage.typePassword(TOO_SHORT_PASSWORD);
        Assert.assertEquals(createAccountPage.getPassword(), TOO_SHORT_PASSWORD, "Password is too" +
                " short");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();

        //*TC #12 (Verify that email can include wrong format)
        createAccountPage.typeEmail(INVALID_EMAIL);
        Assert.assertEquals(createAccountPage.getEmail(), INVALID_EMAIL, "Email has a wrong format");
        createAccountPage.clickOnSignUpButton();
        Assert.assertTrue(createAccountPage.isErrorMessagePresent(), "Error message is not shown");
        createAccountPage.clickOnOkButton();
    }

}


