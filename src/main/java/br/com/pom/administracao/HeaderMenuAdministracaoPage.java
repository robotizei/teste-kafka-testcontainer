package br.com.pom.administracao;

import br.com.pom.GeralPage;
import org.openqa.selenium.By;

public class HeaderMenuAdministracaoPage extends GeralPage {

    private final By aMenuCDP = By.id("menu-controleperdas");
    private final By aMenuInfo = By.id("menu-info");

    public void acessarTABCDP() {
        clickAndHighlight(aMenuCDP);
    }
}
