package br.com.pom.circulacao;

import br.com.pom.GeralPage;
import org.openqa.selenium.By;

import java.util.List;

import static br.com.utils.ReporterUtils.addScreenshotToReport;

public class HeaderMenuPage extends GeralPage {

    public By btnMenuDashboard = By.id("menu-dashboard");
    public By btnMenuSinistros = By.id("menu-sinistro");
    public By btnMenuSindicancias = By.id("menu-sindicancia");
    public By btnMenuIncidente = By.id("menu-incidente");
    public By btnMenuAcoes = By.id("menu-acoes");
    public By btnMenuInfo = By.id("menu-info");
    public By btnMenuConfiguracoes = By.id("menu-configuracoes");

    public void validaNavegacaoDoMenu(List<String> rotas) {
        clickAndHighlight(btnMenuDashboard);
        assert expectTextInUrl(rotas.get(0));
        addScreenshotToReport("Validado navegação Dashboard.");

        clickAndHighlight(btnMenuSinistros);
        assert expectTextInUrl(rotas.get(1));
        addScreenshotToReport("Validado navegação Sinistros.");

        clickAndHighlight(btnMenuSindicancias);
        assert expectTextInUrl(rotas.get(2));
        addScreenshotToReport("Validado navegação Sindicâncias.");

        clickAndHighlight(btnMenuIncidente);
        assert expectTextInUrl(rotas.get(3));
        addScreenshotToReport("Validado navegação Incidentes.");

        clickAndHighlight(btnMenuAcoes);
        assert expectTextInUrl(rotas.get(4));
        addScreenshotToReport("Validado navegação Centrar de Ações.");

        clickAndHighlight(btnMenuInfo);
        assert expectTextInUrl(rotas.get(5));
        addScreenshotToReport("Validado navegação Informações");
    }

    public void clicarTABDashboard() {
        clickAndHighlight(btnMenuDashboard);
    }

    public void clicarTABInformacoes() {
        clickAndHighlight(btnMenuInfo);
    }

    public void clicarTABConfiguracoes() {
        clickAndHighlight(btnMenuConfiguracoes);
    }

    public void validaQueATABConfiguracaoNaoExibe() {
        expectElementVisible(btnMenuDashboard);
        expectElementNotVisible(btnMenuConfiguracoes);
    }
}
