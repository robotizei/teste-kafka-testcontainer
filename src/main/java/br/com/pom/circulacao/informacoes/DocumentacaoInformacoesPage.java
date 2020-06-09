package br.com.pom.circulacao.informacoes;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.model.informacoes.BlocoDeTexto;
import br.com.pom.GeralPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.function.IntFunction;

public class DocumentacaoInformacoesPage extends GeralPage {

    private final By tabDocumentacao = By.id("tab-documentacao");
    private final By divDocumentacaoModulo = By.id("documentacaoModulo");
    private final By separatorDocumentacaoOficialDoProjeto = By.name("Documentação Oficial do Projeto");

    private final IntFunction<By> sroTopicSeparatorBlocoTextoTitulo = (index) -> By.id("bloco-texto-titulo-" + index);
    private final IntFunction<By> divBlocoTextoDescricao = (index) -> By.id("bloco-texto-descricao-" + index);


    public void clicarTABDocumentacao() {
        clickAndHighlight(tabDocumentacao);
    }

    public void validaInformacoesDeDocumentacaoNoCDP(AtualizacaoDoSistema atualizacaoDoSistema) {
        scrollToElement(separatorDocumentacaoOficialDoProjeto);
        Assert.assertEquals(getAttribute(divDocumentacaoModulo, "innerHTML"), atualizacaoDoSistema.getDocumentacaoOficialDoProjeto());
    }

    public void validaDadosBlocoDeTexto(BlocoDeTexto blocoDeTexto, int linha) {
        linha--;
        scrollToElement(sroTopicSeparatorBlocoTextoTitulo.apply(linha));
        Assert.assertEquals(getText(sroTopicSeparatorBlocoTextoTitulo.apply(linha)), blocoDeTexto.getTitulo().toUpperCase());
        Assert.assertEquals(getAttribute(divBlocoTextoDescricao.apply(linha), "innerHTML"), blocoDeTexto.getDescricao());
    }
}
