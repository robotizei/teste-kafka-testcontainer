package br.com.pom.administracao;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.model.informacoes.BlocoDeTexto;
import br.com.pom.GeralPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.IntFunction;

import static br.com.utils.ReporterUtils.addScreenshotToReport;

public class DocumentacaoInformacoesCDPAdministracaoPage extends GeralPage {

    private final By tabDocumentacao = By.id("tab-documentacao");

    private final By adminTopicSeparatorAtualizacaoDoSistema = By.name("Atualização do Sistema");
    private final By thTableHeaderVersao = By.id("table-header-versao");
    private final By thTableHeaderData = By.id("table-header-data");
    private final By thTableHeaderRealizadoPor = By.id("table-header-realizadoPor");
    private final By thTableHeaderDescricao = By.id("table-header-descricao");
    private final IntFunction<By> tdTableCellVersao = (index) -> By.id("table-cell-versao-" + index);
    private final IntFunction<By> tdTableCellData = (index) -> By.id("table-cell-data-" + index);
    private final IntFunction<By> tdTableCellRealizadoPor = (index) -> By.id("table-cell-realizadoPor-" + index);
    private final IntFunction<By> tdTableCellDescricao = (index) -> By.id("table-cell-descricao-" + index);
    private final IntFunction<By> tdTableCellActions = (index) -> By.id("table-cell-actions-" + index);
    private final IntFunction<By> buttonTableBtnEditar = (index) -> By.id("table-btn-editar-" + index);
    private final IntFunction<By> buttonTableBtnExcluir = (index) -> By.id("table-btn-excluir-" + index);
    private final By buttonAddVersaoBtn = By.id("add-versao-btn");
    private final By adminTopicSeparatorDocumentacaoOficialDoProjeto = By.name("Documentação Oficial do Projeto");
    private final By ckeditorDocumentacaoModulo = By.cssSelector("#documentacaoModulo .ck-editor__editable");


    private final By buttonButtonExcluirBloco0 = By.id("button-excluir-bloco-0");
    private final By buttonButtonAdicionarBloco = By.id("button-adicionar-bloco");
    private final By buttonButtonSalvar = By.id("button-salvar");
    private final By buttonButtonCancelar = By.id("button-cancelar");

    /*
    Modal adicionar versao
     */

    private final By h4DialogHeader = By.id("dialog-header");
    private final By buttonDialogButtonClose = By.id("dialog-button-close");
    private final By formFormVersao = By.id("form-versao");
    private final By inputVersaoSistema = By.id("versao-sistema");
    private final By textareaDescricaoSistema = By.id("descricao-sistema");
    private final By inputDataLancamento = By.id("dataLancamento");
    private final By inputRealizadoPor = By.id("realizado-por");
    private final By buttonDialogVersaoCancelarBtn = By.id("dialog-versao-cancelar-btn");
    private final By buttonDialogVersaoSalvarBtn = By.id("dialog-versao-salvar-btn");

    /*
    Modal confirmar excluir versão
     */

    private final By divDialogConfirmationMessage = By.id("dialog-confirmation-message");
    private final By buttonDialogButtonNo = By.id("dialog-button-no");
    private final By buttonDialogButtonYes = By.id("dialog-button-yes");

    /*
    Bloco de texto
     */

    private final By adminTopicSeparatorBlocoDeTexto = By.name("Bloco de Texto");
    private final IntFunction<By> inputTituloBlocoTexto = (index) -> By.id("tituloBlocoTexto-" + index);
    private final IntFunction<By> ckeditorBlocoTexto = (index) -> By.cssSelector("#blocoTexto-" + index + " .ck-editor__editable");
    private final IntFunction<By> buttonButtonExcluirBloco = (index) -> By.id("button-excluir-bloco-" + index);


    public void acessarTABDocumentacao() {
        clickAndHighlight(tabDocumentacao);
    }

    public void preencherModalVersao(AtualizacaoDoSistema atualizacaoDoSistema) {
        sendKeys(inputVersaoSistema, atualizacaoDoSistema.getVersao());
        sendKeysWithJavaScript(inputDataLancamento, atualizacaoDoSistema.getData());
        sendKeysWithJavaScript(inputRealizadoPor, atualizacaoDoSistema.getRealizadoPor());
        sendKeysWithJavaScript(textareaDescricaoSistema, atualizacaoDoSistema.getDescricao());
    }

    public void clicarBtnSalvarModalVersao() {
        clickAndHighlight(buttonDialogVersaoSalvarBtn);
    }

    public void validarDadosDaTabelaDeVersao(AtualizacaoDoSistema atualizacaoDoSistema, int linha, boolean editado) {
        linha--;
        if (!editado) {
            Assert.assertEquals(getText(tdTableCellVersao.apply(linha)), atualizacaoDoSistema.getVersao());
        }
        Assert.assertEquals(getText(tdTableCellData.apply(linha)), atualizacaoDoSistema.getData());
        Assert.assertEquals(getText(tdTableCellRealizadoPor.apply(linha)), atualizacaoDoSistema.getRealizadoPor());
        Assert.assertEquals(getText(tdTableCellDescricao.apply(linha)), atualizacaoDoSistema.getDescricao());
    }

    public void editarModalVersao(AtualizacaoDoSistema atualizacaoDoSistemaEditado) {
        clearForce(inputDataLancamento);
        sendKeysWithJavaScript(inputDataLancamento, atualizacaoDoSistemaEditado.getData());
        sendKeysWithJavaScript(inputRealizadoPor, atualizacaoDoSistemaEditado.getRealizadoPor());
        sendKeysWithJavaScript(textareaDescricaoSistema, atualizacaoDoSistemaEditado.getDescricao());
    }

    public void clicarBtnExcluirVersao(int linha) {
        linha--;
        clickAndHighlight(buttonTableBtnExcluir.apply(linha));
    }

    public void clicarBtnConfirmarExclusaoModalVersao() {
        clickAndHighlight(buttonDialogButtonYes);
    }

    public void clicarBtnAdicionarVersao() {
        clickAndHighlight(buttonAddVersaoBtn);
    }

    public void clicarBtnEditarVersao(int linha) {
        linha--;
        clickAndHighlight(buttonTableBtnEditar.apply(linha));
    }

    public void preencherDocumentacaoOficialDoProjeto(AtualizacaoDoSistema atualizacaoDoSistema) {
        scrollToElement(adminTopicSeparatorDocumentacaoOficialDoProjeto);
        sendKeysWithJavaScriptInCKEditor(ckeditorDocumentacaoModulo, atualizacaoDoSistema.getDocumentacaoOficialDoProjeto());
    }

    public void validarDocumentacaoOficialDoProjeto(AtualizacaoDoSistema atualizacaoDoSistema) {
        Assert.assertEquals(getDataCKEditor(ckeditorDocumentacaoModulo), atualizacaoDoSistema.getDocumentacaoOficialDoProjeto());
    }

    public void clicarBtnAdicionarBlocoDeTexto() {
        clickAndHighlight(buttonButtonAdicionarBloco);
    }

    public void preencherBlocoDeTexto(BlocoDeTexto blocoDeTexto, int linha) {
        linha--;
        sendKeys(inputTituloBlocoTexto.apply(linha), blocoDeTexto.getTitulo());
        sendKeysWithJavaScriptInCKEditor(ckeditorBlocoTexto.apply(linha), blocoDeTexto.getDescricao());
    }

    public void validarBlocoDeTexto(BlocoDeTexto blocoDeTexto, int linha) {
        linha--;
        Assert.assertEquals(getValue(inputTituloBlocoTexto.apply(linha)), blocoDeTexto.getTitulo());
        Assert.assertEquals(getDataCKEditor(ckeditorBlocoTexto.apply(linha)), blocoDeTexto.getDescricao());
    }

    public void clicarBtnExcluirBlocoDeTexto(int linha) {
        linha--;
        clickAndHighlight(buttonButtonExcluirBloco.apply(linha));
    }

    public void clicarBtnConfirmarExcluirBlocoDeTexto() {
        expectElementVisible(buttonDialogButtonYes);
        addScreenshotToReport("Validei o modal de excluir bloco de texto");
        clickAndHighlight(buttonDialogButtonYes);
    }
}
