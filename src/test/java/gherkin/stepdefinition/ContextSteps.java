package gherkin.stepdefinition;

import br.com.pom.ContextoPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ContextSteps extends ReporterUtils {

    ContextoPage contextoPage = new ContextoPage();

    @Então("deve exibir o modal com o título {string}, a mensagem {string} e os botões {string} e {string}")
    public void deveExibirOModalComOTituloAMensagemEOsBotõesE(String titulo, String msg, String button1, String button2) {
        contextoPage.validaModal(titulo, msg, button1, button2);
        addScreenshotToReport("Validei a modal");
    }

    @E("clico em {string} no modal")
    public void clicoEmNoModal(String button) {
        contextoPage.clicarNoBotao(button);
    }

    @Quando("eu busco pelo texto {string}")
    public void euBuscoPeloTexto(String texto) {
        contextoPage.preencherProcurar(texto);
        addScreenshotToReport("Preenchi a busca");
    }

    @Entao("deve exibir a mensagem informando {string}")
    public void deveExibirAMensagemInformando(String texto) {
        contextoPage.validaMensagemTable(texto);
    }

}
