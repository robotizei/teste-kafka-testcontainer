package gherkin.stepdefinition.administracao;

import br.com.model.informacoes.MembroEquipe;
import br.com.model.informacoes.SobreOSetor;
import br.com.pom.administracao.SobreInformacoesCDPAdministracaoPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;

import static br.com.model.GeradorDeMassa.*;

public class SobreInformacoesDoCDPAdministracaoSteps extends ReporterUtils {

    MembroEquipe membroEquipe = getMembroEquipe();
    MembroEquipe membroEquipeEditado = getNewMembroEquipe();
    MembroEquipe outroMembroEquipe = getNewMembroEquipe();
    SobreInformacoesCDPAdministracaoPage sobreInformacoesCDPAdministracaoPage = new SobreInformacoesCDPAdministracaoPage();
    SobreOSetor sobreOSetor = getSobreOSetor();

    @E("acesso a TAB SOBRE no Administração")
    public void acessoATABSOBRENoAdministração() {
        sobreInformacoesCDPAdministracaoPage.acessarTABSobre();
        addScreenshotToReport("Acessou a TAB SOBRE no Administração");
    }

    @E("clico no botão Adicionar membro")
    public void clicoNoBotãoAdicionarMembro() {
        sobreInformacoesCDPAdministracaoPage.clicarBtnAdicionarMembro();
        addLogToReport("Clicou no botão Adicionar Membro");
    }

    @Entao("preencho a modal de membro")
    public void preenchoAModalDeMembro() {
        sobreInformacoesCDPAdministracaoPage.preencherModalAddMembro(membroEquipe);
        addScreenshotToReport("Preencheu a modal de Membro de equipe");
    }

    @E("clico no botão Salvar do dialog de adicionar membro")
    public void clicoNoBotãoSalvarDoDialogDeAdicionarMembro() {
        sobreInformacoesCDPAdministracaoPage.clicarBtnSalvarDialog();
        addLogToReport("Clicou no botão Saçvar do dialog de adicionar membro");
    }

    @Dado("clico no botão editar do Membro de equipe da linha {int}")
    public void clicoNoBotãoEditarDoMembroDeEquipeDaLinha(int linha) {
        sobreInformacoesCDPAdministracaoPage.clicarBtnEditarMembroEquipe(linha);
        addLogToReport("Clicou no botão editar do membro de equipe da linha " + linha);
    }

    @E("editos os dados da modal de membro")
    public void editosOsDadosDaModalDeMembro() {
        sobreInformacoesCDPAdministracaoPage.preencherModalAddMembro(membroEquipeEditado);
        addScreenshotToReport("Editou os dados do membro de equipe");
    }

    @E("valido o membro de equipe adicionado na lista na linha {int}")
    public void validoOMembroDeEquipeAdicionadoNaListaNaLinha(int linha) {
        sobreInformacoesCDPAdministracaoPage.validaMembroEquipeAdicionadoNaLista(linha, membroEquipe);
        addScreenshotToReport("Validou os dados do membro de equipe adicionado na linha " + linha);
    }

    @E("valido o membro de equipe editado na lista na linha {int}")
    public void validoOMembroDeEquipeEditadoNaListaNaLinha(int linha) {
        sobreInformacoesCDPAdministracaoPage.validaMembroEquipeAdicionadoNaLista(linha, membroEquipeEditado);
        addScreenshotToReport("Validou os dados do membro de equipe editado na linha " + linha);
    }

    @Dado("clico no botão remover do Membro de equipe da linha {int}")
    public void clicoNoBotãoRemoverDoMembroDeEquipeDaLinha(int linha) {
        sobreInformacoesCDPAdministracaoPage.clicarBtnRemoverMembroEquipe(linha);
        addLogToReport("Clicou no botão editar do membro de equipe da linha " + linha);
    }

    @E("confirmo a exclusão do membro de equipe")
    public void confirmoAExclusãoDoMembroDeEquipe() {
        sobreInformacoesCDPAdministracaoPage.confirmaExclusaoDoMembroDeEquipe();
        addLogToReport("Clicou no botão para confirmar a exclusão do membro de equipe");
    }

    @Então("preencho os dados sobre o setor no Administração")
    public void preenchoOsDadosSobreOSetorNoAdministração() {
        sobreInformacoesCDPAdministracaoPage.preencherDadosSobreOSetor(sobreOSetor);
        addScreenshotToReport("Preenchi os dados de sobre o Setor.");
    }

    @E("clico no botão Salvar Informações do Sobre a CDP")
    public void clicoNoBotãoSalvarInformaçõesDoSobreACDP() {
        sobreInformacoesCDPAdministracaoPage.clicarBtnSalvarInformacoes();
        addLogToReport("Cliquei no botão Salvar Informações.");
    }

    @Então("valido que as informações de sobre o setor no Administração persistiram")
    public void validoQueAsInformaçõesDeSobreOSetorNoAdministraçãoForamPersistiram() {
        sobreInformacoesCDPAdministracaoPage.validarDadosSobreOSetor(sobreOSetor);
        addScreenshotToReport("Validado que os dados persistiram na base.");
    }

    @E("clico no botão Salvar e adicionar do dialog de adicionar membro")
    public void clicoNoBotãoSalvarEAdicionarDoDialogDeAdicionarMembro() {
        sobreInformacoesCDPAdministracaoPage.clicarBtnSalvarEAdicionarOutroDialog();
        addLogToReport("Clicou no botão Salvar");
    }

    @Entao("preencho a modal de membro com outro membro")
    public void preenchoAModalDeMembroComOutroMembro() {
        sobreInformacoesCDPAdministracaoPage.preencherModalAddMembro(outroMembroEquipe);
        addScreenshotToReport("Preencheu a modal de membro de equipe com outro membro de equipe");
    }

    @E("valido que o número de membro de equipe na lista é igual a {int}")
    public void validoQueONúmeroDeMembroDeEquipeNaListaÉIgualA(int qtdMembro) {
        sobreInformacoesCDPAdministracaoPage.validaQtdMembrosNaLista(qtdMembro);
        addScreenshotToReport("Valida a lista de membro de equipe");
        /*
        Apenas para limpar a lista de membros.
         */
        if (qtdMembro == 2) {
            sobreInformacoesCDPAdministracaoPage.clicarBtnRemoverMembroEquipe(1);
            sobreInformacoesCDPAdministracaoPage.confirmaExclusaoDoMembroDeEquipe();
            sobreInformacoesCDPAdministracaoPage.validaMensagemSnackBar("Registro excluído com sucesso.");
            sobreInformacoesCDPAdministracaoPage.clicarBtnRemoverMembroEquipe(1);
            sobreInformacoesCDPAdministracaoPage.confirmaExclusaoDoMembroDeEquipe();
            sobreInformacoesCDPAdministracaoPage.validaMensagemSnackBar("Registro excluído com sucesso.");
            sobreInformacoesCDPAdministracaoPage.clicarBtnSalvarInformacoes();
        }
    }

    @E("seleciono opção para exibir dados da equipe")
    public void selecionoOpçãoParaExibirDadosDaEquipe() {
        sobreInformacoesCDPAdministracaoPage.selecionaOpcaoParaMostrarDadosDaEquipe(membroEquipe);
        addScreenshotToReport("Selecionou opção de Exibir dados da equipe");
    }

    @E("valida o preenchimento da Exibição do membro de Equipe")
    public void validaOPreenchimentoDaExibiçãoDoMembroDeEquipe() {
        sobreInformacoesCDPAdministracaoPage.validaOpcaoParaMostrarDadosDaEquipe(membroEquipe);
        addScreenshotToReport("Valida o preenchimento da Exibição do membro de Equipe");
    }
}
