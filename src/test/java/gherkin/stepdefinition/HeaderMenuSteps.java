package gherkin.stepdefinition;

import br.com.pom.circulacao.HeaderMenuPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class HeaderMenuSteps extends ReporterUtils {

    HeaderMenuPage headerMenuPage = new HeaderMenuPage();

    @Quando("acesso a TAB Dashboard")
    public void acessoATABDashboard() {
        headerMenuPage.clicarTABDashboard();
        addScreenshotToReport("Cliquei na TAB Dashboard");
    }

    @E("acesso a TAB Informações")
    public void acessoATABInformações() {
        headerMenuPage.clicarTABInformacoes();
        addScreenshotToReport("Cliquei na TAB Informações");
    }

    @E("acesso a TAB Configurações")
    public void acessoATABConfigurações() {
        headerMenuPage.clicarTABConfiguracoes();
        addScreenshotToReport("Cliquei na TAB Configurações");
    }

    @Entao("não visualizo a TAB de Configurações")
    public void nãoVisualizoATABDeConfigurações() {
        headerMenuPage.validaQueATABConfiguracaoNaoExibe();
        addScreenshotToReport();
    }
}
