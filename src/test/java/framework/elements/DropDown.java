package framework.elements;

import org.openqa.selenium.By;

public class DropDown extends BaseElement {
    public DropDown(By locator, String name) {
        super(locator, name);
    }

    public DropDown(String string, String name) {
        super(string, name);
    }
}
