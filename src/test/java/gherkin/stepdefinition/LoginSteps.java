package gherkin.stepdefinition;

import br.com.pom.LoginPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;

import static br.com.utils.PropertiesHelper.*;


public class LoginSteps extends ReporterUtils {

    LoginPage loginPage = new LoginPage();

    @Dado("^que eu estou na página de login$")
    public void queEuEstouNaPaginaDeLogin() {
        loginPage.naoImplementado();
    }

    @Quando("preencho com usuário e senha")
    public void preenchoComUsuarioESenha(List<String> list) {
        loginPage.naoImplementado();
    }

    @E("clico em efetuar o login")
    public void clicoEmEfetuarOLogin() {
        loginPage.naoImplementado();
    }

    @Então("deve exibir o módulo do SRO")
    public void deveExibirOModuloDoSRO() {
        loginPage.naoImplementado();
    }

    @Então("deve exibir a mensagem de login inválido")
    public void deveExibirAMensagemDeLoginInvalido() {
        loginPage.naoImplementado();
    }

    @Dado("que eu esteja logado com usuário CCO VIA")
    public void queEuEstejaLogadoComUsuarioCCOVIA() {
        addScreenshotToReport("Estou logado com usuário com perfil CCO");
    }

    @E("efetue login com perfil com permissão de Visualização")
    public void efetueLoginComPerfilComPermissãoDeVisualização() {
        loginPage.efetuarLoginPrisma(userLeitura, passLeitura);
    }

    @E("efetue login com perfil com permissão de Criação")
    public void efetueLoginComPerfilComPermissãoDeCriacao() {
        loginPage.efetuarLoginPrisma(userCriacao, passCriacao);
    }

    @E("efetue login com perfil com permissão de Edição")
    public void efetueLoginComPerfilComPermissãoDeEdicao() {
        loginPage.efetuarLoginPrisma(userEdicao, passEdicao);
    }

    @E("efetue login com perfil com permissão de Admin")
    public void efetueLoginComPerfilComPermissãoDeAdmin() {
        loginPage.efetuarLoginPrisma(userAdmin, passAdmin);
    }
}
