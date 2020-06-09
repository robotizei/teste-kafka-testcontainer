package br.com.pom;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.Function;

import static br.com.utils.ReporterUtils.addScreenshotToReport;

public class ContextoPage extends GeralPage {

    private final By h4DialogHeader = By.id("dialog-header");
    private final By buttonDialogButtonClose = By.id("dialog-button-close");
    private final By divDialogConfirmationMessage = By.id("dialog-confirmation-message");
    private final Function<String, By> buttonDialogButton = (text) -> By.xpath("//button//span[text()='" + text + "']");

    /*
    Procurar
     */
    private final By inputSearch = By.id("input-search");
    private final By buttonClear = By.id("button-clear-search");
    private final By labelMsgTable = By.className("empty-label");

    public void validaModal(String titulo, String msg, String button1, String button2) {
        expectElementVisible(h4DialogHeader);
        Assert.assertEquals(getText(h4DialogHeader), titulo);
        Assert.assertEquals(getText(divDialogConfirmationMessage), msg);
        Assert.assertEquals(getText(buttonDialogButton.apply(button1)).toLowerCase(), button1.toLowerCase());
        Assert.assertEquals(getText(buttonDialogButton.apply(button2)).toLowerCase(), button2.toLowerCase());
    }

    public void clicarNoBotao(String button) {
        clickAndHighlight(buttonDialogButton.apply(button));
    }

    public void preencherProcurar(String texto) {
        clearForce(inputSearch);
        sendKeys(inputSearch, texto);
    }

    public void validaMensagemTable(String texto) {
        expectTableLoading();
        Assert.assertEquals(getText(labelMsgTable), texto);
        addScreenshotToReport("Validei a mensagem.");
        clickAndHighlight(buttonClear);
        expectTableLoading();
    }
}
