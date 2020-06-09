package gherkin.stepdefinition.informacoes;

import br.com.model.informacoes.MembroEquipe;
import br.com.model.informacoes.SobreOSetor;
import br.com.pom.circulacao.informacoes.SobreInformacoesPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;

import static br.com.model.GeradorDeMassa.getMembroEquipe;
import static br.com.model.GeradorDeMassa.getSobreOSetor;


public class SobreInformacoesSteps extends ReporterUtils {

    SobreInformacoesPage sobreInformacoesPage = new SobreInformacoesPage();
    SobreOSetor sobreOSetor = getSobreOSetor();
    MembroEquipe membroEquipe = getMembroEquipe();

    @E("acesso a TAB SOBRE no CDP")
    public void acessoATABSOBRENoCDP() {
        sobreInformacoesPage.clicarTABSobre();
        addLogToReport("Cliquei na TAB Sobre");
    }

    @Então("valido as informações sobre o setor no CDP")
    public void validoAsInformaçõesSobreOSetorNoCDP() {
        sobreInformacoesPage.validarDadosSobreOSetor(sobreOSetor);
        addScreenshotToReport("Validei os dados sobre o setor no CDP");
    }

    @E("valida o preenchimento da Exibição do membro de Equipe na linha {int}")
    public void validaOPreenchimentoDaExibiçãoDoMembroDeEquipeNaLinha(int linha) {
        sobreInformacoesPage.validaDadosPreenchidosNoAdministracao(linha, membroEquipe);
        addScreenshotToReport("Validei os dados sobre a Equipe no CDP");

    }
}
