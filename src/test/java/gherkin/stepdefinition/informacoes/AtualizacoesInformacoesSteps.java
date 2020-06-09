package gherkin.stepdefinition.informacoes;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.pom.circulacao.informacoes.AtualizacaoInformacoesPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;

import static br.com.model.GeradorDeMassa.getAtualizacaoDoSistema;


public class AtualizacoesInformacoesSteps extends ReporterUtils {

    AtualizacaoInformacoesPage atualizacaoInformacoesPage = new AtualizacaoInformacoesPage();
    AtualizacaoDoSistema atualizacaoDoSistema = getAtualizacaoDoSistema();

    @E("acesso a TAB Atualização no CDP")
    public void acessoATABAtualizaçãoNoCDP() {
        atualizacaoInformacoesPage.clicarTABAtualizacao();
        addLogToReport("Cliquei na TAB Atualização");
    }

    @Então("valido as informações da atualização do sistema")
    public void validoAsInformaçõesDaAtualizaçãoDoSistema() {
        atualizacaoInformacoesPage.validarDados(atualizacaoDoSistema, 1);
        addScreenshotToReport("Validei os dados");
    }
}
