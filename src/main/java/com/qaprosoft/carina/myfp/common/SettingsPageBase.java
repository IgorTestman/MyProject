package com.qaprosoft.carina.myfp.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.myfp.utils.enums.SettingsEnum;
import org.openqa.selenium.WebDriver;

public abstract class SettingsPageBase extends AbstractPage {
    public SettingsPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract AbstractPage openPageByName(SettingsEnum page);

}


