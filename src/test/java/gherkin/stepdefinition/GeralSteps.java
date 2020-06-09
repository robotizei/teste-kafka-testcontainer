package gherkin.stepdefinition;

import br.com.pom.GeralPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import static br.com.utils.ClipboardUtils.getClipboard;
import static br.com.utils.PropertiesHelper.urlAdministracao;
import static br.com.utils.PropertiesHelper.urlFrontendCirculacao;
import static java.lang.Thread.sleep;


public class GeralSteps extends ReporterUtils {
    GeralPage geralPage = new GeralPage();

    @E("deve copiar a mensagem {string} para a area de transferência")
    public void deveCopiarAMensagemParaAAreaDeTransferência(String urlEsperada) {
        String clipboard = getClipboard();
        Assert.assertEquals(clipboard, urlEsperada);
        addLogToReport("Endereço copiado corretamente: " + clipboard);
    }

    @Entao("deve exibir a snackBar com a mensagem {string}")
    public void deveExibirASnackBarComAMensagem(String msg) {
        geralPage.validaMensagemSnackBar(msg);
    }


    @E("deve exibir o box com a mensagem {string}")
    public void deveExibirOBoxComAMensagem(String mensagemBox) {
        geralPage.validaMensagemBox(mensagemBox);
    }

    @E("clico no expansion da sindicância")
    public void clicoNoExpansionDaSindicância() {
        geralPage.clicarExpansionSindicancia();
        addLogToReport("Cliquei no expansion da sindicância");
    }

    @Dado("que eu navegue até o Administração")
    public void queEuNavegueAtéOAdministração() {
        resetDriver(urlAdministracao);
        addLogToReport("Naveguei até o administração.");
    }

    @Dado("que eu navegue até o Controle de Perdas")
    public void queEuNavegueAtéOControleDePerdas() {
        resetDriver(urlFrontendCirculacao);
        addLogToReport("Naveguei até o Controle de Perdas.");
    }

    @Dado("que eu acesso a url {string}")
    public void queEuAcessoAUrl(String arg0) {
        resetDriverOtherURL(arg0);
    }

    @Quando("faço o drag and drop da tabela {int} para a tabela {int}")
    public void façoODragAndDropDaTabelaParaATabela(int arg0, int arg1) {
        By divCdkDragTodo0 = By.id("cdkDrag-todo-0");
        By divCdkDragDone0 = By.id("cdkDrag-done-0");
        for (int i = 0; i < 4; i++) {
            geralPage.dragAndDropWithAction(divCdkDragTodo0, divCdkDragDone0);

        }
    }

    @Então("o elemento da tabela {int} deve ir para a tabela {int}")
    public void oElementoDaTabelaDeveIrParaATabela(int arg0, int arg1) {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
