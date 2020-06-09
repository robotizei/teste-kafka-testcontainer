package br.com.pom.circulacao.informacoes;

import br.com.model.informacoes.MembroEquipe;
import br.com.model.informacoes.SobreOSetor;
import br.com.pom.GeralPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.IntFunction;

public class SobreInformacoesPage extends GeralPage {

    private final By separatorSobreOSetor = By.name("SOBRE O SETOR");
    private final By divSobreSetor = By.id("sobre-setor");
    private final By strongStrongOndeEstamos = By.id("strong-onde-estamos");
    private final By strongStrongTelefone = By.id("strong-telefone");
    private final By tabSobre = By.id("tab-sobre");
    private final By separatorEquipe = By.name("EQUIPE");
    private final IntFunction<By> imgMembroEquipeFoto = (index) -> By.id("membro-equipe-foto-" + index);
    private final IntFunction<By> spanMembroEquipeNome = (index) -> By.id("membro-equipe-nome-" + index);
    private final IntFunction<By> pMembroEquipeCargo = (index) -> By.id("membro-equipe-cargo-" + index);
    private final IntFunction<By> spanMembroEquipeEmail = (index) -> By.id("membro-equipe-email-" + index);
    private final IntFunction<By> spanMembroEquipeTelefone = (index) -> By.id("membro-equipe-telefone-" + index);

    public void clicarTABSobre() {
        clickAndHighlight(tabSobre);
    }

    public void validarDadosSobreOSetor(SobreOSetor sobreOSetor) {
        Assert.assertEquals(getAttribute(divSobreSetor, "innerHTML"), sobreOSetor.getSobreSetor());
        Assert.assertEquals(getText(strongStrongOndeEstamos), sobreOSetor.getLocalizacaoFisica());
        Assert.assertEquals(getText(strongStrongTelefone), sobreOSetor.getTelefoneDoSetor());
    }

    public void validaDadosPreenchidosNoAdministracao(int linha, MembroEquipe membroEquipe) {
        linha--;
        if (membroEquipe.isExibirDadosEquipe()) {
            scrollToElement(separatorEquipe);
            expectElementVisible(imgMembroEquipeFoto.apply(linha));
            Assert.assertEquals(getText(spanMembroEquipeNome.apply(linha)), membroEquipe.getNome());
            Assert.assertEquals(getText(pMembroEquipeCargo.apply(linha)), membroEquipe.getCargo());
            Assert.assertEquals(getText(spanMembroEquipeEmail.apply(linha)), membroEquipe.getEmail());
            Assert.assertEquals(getText(spanMembroEquipeTelefone.apply(linha)), membroEquipe.getTelefone());
        } else {
            expectElementNotVisible(separatorEquipe);
        }
    }
}
