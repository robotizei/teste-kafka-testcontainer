package br.com.pom;

import br.com.pom.GeralPage;
import org.openqa.selenium.By;

public class LoginPage extends GeralPage {

    /*
    Login SSO
     */

    private final By divKcHeader = By.id("kc-header");
    private final By html = By.cssSelector("html");
    private final By divKcHeaderWrapper = By.id("kc-header-wrapper");
    private final By h1KcPageTitle = By.id("kc-page-title");
    private final By divKcContent = By.id("kc-content");
    private final By divKcContentWrapper = By.id("kc-content-wrapper");
    private final By divKcForm = By.id("kc-form");
    private final By divKcFormWrapper = By.id("kc-form-wrapper");
    private final By formKcFormLogin = By.id("kc-form-login");
    private final By inputUsername = By.id("username");
    private final By inputPassword = By.id("password");
    private final By divKcFormOptions = By.id("kc-form-options");
    private final By divKcFormButtons = By.id("kc-form-buttons");
    private final By inputIdHiddenInput = By.id("id-hidden-input");
    private final By inputKcLogin = By.id("kc-login");

    public LoginPage() {
        super();
    }

    public void efetuarLoginPrisma(String user, String pass) {
        expectElementVisible(inputUsername);
        sendKeys(inputUsername, user);
        sendKeys(inputPassword, pass);
        clickAndHighlight(inputKcLogin);
    }
}
