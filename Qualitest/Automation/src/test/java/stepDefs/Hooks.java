package stepDefs;

import io.cucumber.java.After;
import webdriverSupport.DriverManager;

public class Hooks {
    @After
    public void tearDown() {
        DriverManager.removeDriver();
    }
}
