package br.com.pom.circulacao.informacoes;


import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.pom.GeralPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.IntFunction;

public class AtualizacaoInformacoesPage extends GeralPage {

    private final By aTabAtualizacao = By.id("tab-atualizacao");
    private final By separatorAtualizacoesDoSistema = By.name("ATUALIZAÇÕES DO SISTEMA");
    private final By sroTitleLabelAtualizacaoVersao0 = By.id("atualizacao-versao-0");
    private final IntFunction<By> divTitleLabelAtualizacaoVersao = (index) -> By.id("title-label-atualizacao-versao-" + index);
    private final IntFunction<By> strongAtualizacaoDataLancamento = (index) -> By.id("atualizacao-data-lancamento-" + index);
    private final IntFunction<By> strongAtualizacaoDesenvolvidoPor = (index) -> By.id("atualizacao-desenvolvido-por-" + index);
    private final IntFunction<By> strongAtualizacaoDescricao = (index) -> By.id("atualizacao-descricao-" + index);


    public void clicarTABAtualizacao() {
        clickAndHighlight(aTabAtualizacao);
    }

    public void validarDados(AtualizacaoDoSistema atualizacaoDoSistema, int linha) {
        linha--;
        Assert.assertEquals(getText(divTitleLabelAtualizacaoVersao.apply(linha)), atualizacaoDoSistema.getVersao().toUpperCase());
        Assert.assertEquals(getText(strongAtualizacaoDataLancamento.apply(linha)), atualizacaoDoSistema.getData());
        Assert.assertEquals(getText(strongAtualizacaoDesenvolvidoPor.apply(linha)), atualizacaoDoSistema.getRealizadoPor());
        Assert.assertEquals(getText(strongAtualizacaoDescricao.apply(linha)), atualizacaoDoSistema.getDescricao());
    }
}
