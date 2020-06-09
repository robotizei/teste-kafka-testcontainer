package gherkin.stepdefinition.administracao;

import br.com.pom.administracao.HeaderMenuAdministracaoPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;

public class HeaderAdministracaoSteps extends ReporterUtils {

    HeaderMenuAdministracaoPage headerMenuAdministracaoPage = new HeaderMenuAdministracaoPage();

    @E("acesso a TAB CDP")
    public void acessoATABCDP() {
        headerMenuAdministracaoPage.acessarTABCDP();
        addScreenshotToReport("Acessei a TAB CDP");
    }
}
