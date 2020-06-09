package br.com.pom.administracao;

import br.com.model.informacoes.MembroEquipe;
import br.com.model.informacoes.SobreOSetor;
import br.com.pom.GeralPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.IntFunction;

public class SobreInformacoesCDPAdministracaoPage extends GeralPage {

    private final IntFunction<By> btnEditarMembroEquipe = (index) -> By.id("button-editar-membro-equipe-" + index);
    private final IntFunction<By> btnRemoverMembroEquipe = (index) -> By.id("button-remover-membro-equipe-" + index);
    private final IntFunction<By> btnDownloadFotoMembroEquipe = (index) -> By.id("button-foto-membro-equipe-" + index);

    private final By separatorEquipe = By.name("Equipe");
    private final By buttonButtonSalvar = By.id("button-salvar");
    private final By buttonButtonCancelar = By.id("button-cancelar");
    private final By tabSobre = By.id("tab-sobre");
    private final By btnAdicionarMembro = By.id("button-add-membro-equipe");
    private final By nomeMembroEquipeDialog = By.id("nome");
    private final By cargoMembroEquipeDialog = By.id("cargo");
    private final By emailMembroEquipeDialog = By.id("email");
    private final By telefoneMembroEquipeDialog = By.id("telefone");
    private final By inputFoto = By.id("input-file-upload");
    private final By btnSalvarDialog = By.id("dialog-button-save");
    private final By btnCancelarDialog = By.id("dialog-button-cancel");
    private final By btnSalvarEAdicionarOutroDialog = By.id("dialog-button-save-add-other");
    private final By btnConfirmarExclusao = By.id("dialog-button-yes");
    private final By tableMembros = By.id("cdk-drop-list-0");

    private final IntFunction<By> nomeMembroEquipeLista = (index) -> By.id("membro-equipe-nome-" + index);
    private final IntFunction<By> cargoMembroEquipeLista = (index) -> By.id("membro-equipe-cargo-" + index);
    private final IntFunction<By> emailMembroEquipeLista = (index) -> By.id("membro-equipe-email-" + index);
    private final IntFunction<By> telefoneMembroEquipeLista = (index) -> By.id("membro-equipe-telefone-" + index);


    private final By ckeditorSobreSetor = By.cssSelector("#sobreSetor .ck-editor__editable");
    private final By inputTelefoneSetor = By.id("telefoneSetor");
    private final By inputLocalizacaoFisicaSetor = By.id("localizacaoFisicaSetor");

    private final By checkBoxExibirEquipeSim = By.id("exibirEquipe-sim");
    private final By checkBoxExibirEquipeNao = By.id("exibirEquipe-nao");

    public void clicarBtnSalvarInformacoes() {
        clickAndHighlight(buttonButtonSalvar);
    }

    public void clicarBtnCancelar() {
        clickAndHighlight(buttonButtonCancelar);
    }

    public void acessarTABSobre() {
        clickAndHighlight(tabSobre);
    }

    public void preencherDadosSobreOSetor(SobreOSetor sobreOSetor) {
        sendKeysWithJavaScriptInCKEditor(ckeditorSobreSetor, sobreOSetor.getSobreSetor());
        sendKeys(inputTelefoneSetor, sobreOSetor.getTelefoneDoSetor());
        sendKeysWithJavaScript(inputLocalizacaoFisicaSetor, sobreOSetor.getLocalizacaoFisica());
    }

    public void validarDadosSobreOSetor(SobreOSetor sobreOSetor) {
        Assert.assertEquals(getDataCKEditor(ckeditorSobreSetor), sobreOSetor.getSobreSetor());
        Assert.assertEquals(getValue(inputTelefoneSetor), sobreOSetor.getTelefoneDoSetor());
        Assert.assertEquals(getValue(inputLocalizacaoFisicaSetor), sobreOSetor.getLocalizacaoFisica());
    }

    public void clicarBtnAdicionarMembro() {
        clickAndHighlight(btnAdicionarMembro);
    }

    public void preencherModalAddMembro(MembroEquipe membroEquipe) {
        expectLoading();
        expectElementVisible(nomeMembroEquipeDialog);
        sendKeysWithJavaScript(nomeMembroEquipeDialog, membroEquipe.getNome());
        sendKeysWithJavaScript(cargoMembroEquipeDialog, membroEquipe.getCargo());
        sendKeys(emailMembroEquipeDialog, membroEquipe.getEmail());
        sendKeys(telefoneMembroEquipeDialog, membroEquipe.getTelefone());
        uploadImagem(inputFoto);
    }

    public void clicarBtnSalvarDialog() {
        clickAndHighlight(btnSalvarDialog);
    }

    public void clicarBtnEditarMembroEquipe(int linha) {
        linha--;
        clickAndHighlight(btnEditarMembroEquipe.apply(linha));
    }

    public void validaMembroEquipeAdicionadoNaLista(int linha, MembroEquipe membroEquipe) {
        linha--;
        scrollToElement(separatorEquipe);
        expectElementVisible(btnDownloadFotoMembroEquipe.apply(linha));
        expectElementVisible(btnEditarMembroEquipe.apply(linha));
        expectElementVisible(btnRemoverMembroEquipe.apply(linha));
        Assert.assertEquals(getText(nomeMembroEquipeLista.apply(linha)), membroEquipe.getNome());
        Assert.assertEquals(getText(cargoMembroEquipeLista.apply(linha)), membroEquipe.getCargo());
        Assert.assertEquals(getText(emailMembroEquipeLista.apply(linha)), membroEquipe.getEmail());
        Assert.assertEquals(getText(telefoneMembroEquipeLista.apply(linha)), membroEquipe.getTelefone());
    }

    public void clicarBtnRemoverMembroEquipe(int linha) {
        linha--;
        expectLoading();
        clickAndHighlight(btnRemoverMembroEquipe.apply(linha));
    }

    public void confirmaExclusaoDoMembroDeEquipe() {
        clickAndHighlight(btnConfirmarExclusao);
    }

    public void clicarBtnSalvarEAdicionarOutroDialog() {
        clickAndHighlight(btnSalvarEAdicionarOutroDialog);
    }

    public void validaQtdMembrosNaLista(int qtdMembro) {
        Assert.assertEquals(countChildElement(tableMembros, "mat-row"), qtdMembro);
    }

    public void selecionaOpcaoParaMostrarDadosDaEquipe(MembroEquipe membroEquipe) {
        if (membroEquipe.isExibirDadosEquipe()) {
            clickWithAction(checkBoxExibirEquipeSim);
        } else {
            clickWithAction(checkBoxExibirEquipeNao);
        }
    }

    public void validaOpcaoParaMostrarDadosDaEquipe(MembroEquipe membroEquipe) {
        if (membroEquipe.isExibirDadosEquipe()) {
            isCheckboxChecked(checkBoxExibirEquipeSim);
        } else {
            isCheckboxChecked(checkBoxExibirEquipeNao);
        }
    }
}
