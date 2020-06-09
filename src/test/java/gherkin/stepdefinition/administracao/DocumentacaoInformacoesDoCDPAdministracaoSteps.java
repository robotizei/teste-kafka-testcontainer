package gherkin.stepdefinition.administracao;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.model.informacoes.BlocoDeTexto;
import br.com.pom.administracao.DocumentacaoInformacoesCDPAdministracaoPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;

import static br.com.model.GeradorDeMassa.*;

public class DocumentacaoInformacoesDoCDPAdministracaoSteps extends ReporterUtils {

    DocumentacaoInformacoesCDPAdministracaoPage documentacaoInformacoesCDPAdministracaoPage = new DocumentacaoInformacoesCDPAdministracaoPage();
    AtualizacaoDoSistema atualizacaoDoSistema = getAtualizacaoDoSistema();
    AtualizacaoDoSistema atualizacaoDoSistemaEditado = getNewAtualizacaoDoSistema();
    BlocoDeTexto blocoDeTexto = getBlocoDeTexto();

    @E("acesso a TAB Documentação no Administração")
    public void acessoATABDocumentaçãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.acessarTABDocumentacao();
        addLogToReport("Acessei a TAB documentação.");
    }

    @Então("edito a nova versão no Administração")
    public void editoANovaVersãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.editarModalVersao(atualizacaoDoSistemaEditado);
        addScreenshotToReport("Editei a versão");
    }

    @E("clico no botão Salvar no modal de Adicionar Versão")
    public void clicoNoBotãoSalvarNoModalDeAdicionarVersão() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnSalvarModalVersao();
        addLogToReport("Cliquei no botão Salvar");
    }

    @E("valido que as informações editadas da atualização do sistema persistiram no Administração")
    public void validoQueAsInformaçõesEditadasDaAtualizaçãoDoSistemaPersistiramNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.validarDadosDaTabelaDeVersao(atualizacaoDoSistemaEditado, 1, true);
        addScreenshotToReport("Validei os dados editados");
    }

    @E("valido que as informações da atualização do sistema persistiram no Administração")
    public void validoQueAsInformaçõesDaAtualizaçãoDoSistemaPersistiramNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.validarDadosDaTabelaDeVersao(atualizacaoDoSistema, 1, false);
        addScreenshotToReport("Validei os dados inseridos");
    }

    @Então("adiciono uma nova versão no Administração")
    public void adicionoUmaNovaVersãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.preencherModalVersao(atualizacaoDoSistema);
        addScreenshotToReport("Preenchi os dados.");
    }

    @Então("clico no botão para excluir a versão da linha {int} no Administração")
    public void clicoNoBotãoParaExcluirAVersãoDaLinhaNoAdministração(int linha) {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnExcluirVersao(linha);
        addLogToReport("Cliquei no botão de excluir versão.");
    }

    @E("clico no botão Confirmar no modal de Adicionar Versão")
    public void clicoNoBotãoConfirmarNoModalDeAdicionarVersão() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnConfirmarExclusaoModalVersao();
        addLogToReport("Confirmei a exclusão.");
    }

    @E("clico para adicionar uma nova versão no Administração")
    public void clicoParaAdicionarUmaNovaVersãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnAdicionarVersao();
        addLogToReport("Cliquei no botão adicionar nova versão.");
    }

    @E("clico para editar a versão da linha {int} no Administração")
    public void clicoParaEditarAVersãoDaLinhaNoAdministração(int linha) {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnEditarVersao(linha);
        addLogToReport("Cliquei no botão editar versão.");
    }

    @Então("preencho a Documentação oficial do projeto")
    public void preenchoADocumentaçãoOficialDoProjeto() {
        documentacaoInformacoesCDPAdministracaoPage.preencherDocumentacaoOficialDoProjeto(atualizacaoDoSistema);
        addScreenshotToReport("Preencho a Documentação oficial do projeto");
    }

    @E("valido que as informações de Documentação oficial do projeto no Administração persistiram")
    public void validoQueAsInformaçõesDeDocumentaçãoOficialDoProjetoNoAdministraçãoPersistiram() {
        documentacaoInformacoesCDPAdministracaoPage.validarDocumentacaoOficialDoProjeto(atualizacaoDoSistema);
        addScreenshotToReport("Valido que as informações de Documentação oficial do projeto no Administração persistiram");
    }

    @E("clico em Adicionar bloco de texto na Documentação no Administração")
    public void clicoEmAdicionarBlocoDeTextoNaDocumentaçãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnAdicionarBlocoDeTexto();
        addLogToReport("Cliquei no botão");
    }

    @Entao("preencho os dados do bloco de texto Documentação no Administração")
    public void preenchoOsDadosDoBlocoDeTextoDocumentaçãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.preencherBlocoDeTexto(blocoDeTexto, 1);
        addScreenshotToReport("Preenchi os dados do bloco de texto.");
    }

    @E("valido que o bloco de texto Documentação do projeto no Administração persistiram")
    public void validoQueOBlocoDeTextoDocumentaçãoDoProjetoNoAdministraçãoPersistiram() {
        documentacaoInformacoesCDPAdministracaoPage.validarBlocoDeTexto(blocoDeTexto, 1);
        addScreenshotToReport("Validei os dados do bloco de texto.");
    }

    @Entao("clico em Excluir bloco de texto na Documentação no Administração")
    public void clicoEmExcluirBlocoDeTextoNaDocumentaçãoNoAdministração() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnExcluirBlocoDeTexto(1);
        addScreenshotToReport("Cliquei no botão excluir bloco de texto");
    }

    @E("confirmo a exclusão do bloco de texto no modal")
    public void confirmoAExclusãoDoBlocoDeTextoNoModal() {
        documentacaoInformacoesCDPAdministracaoPage.clicarBtnConfirmarExcluirBlocoDeTexto();
        addScreenshotToReport("Cliquei no botão excluir bloco de texto");
    }
}
