package gherkin.stepdefinition.informacoes;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.model.informacoes.BlocoDeTexto;
import br.com.pom.circulacao.informacoes.DocumentacaoInformacoesPage;
import br.com.utils.ReporterUtils;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;

import static br.com.model.GeradorDeMassa.getAtualizacaoDoSistema;
import static br.com.model.GeradorDeMassa.getBlocoDeTexto;


public class DocumentacaoInformacoesSteps extends ReporterUtils {

    DocumentacaoInformacoesPage documentacaoInformacoesPage = new DocumentacaoInformacoesPage();
    AtualizacaoDoSistema atualizacaoDoSistema = getAtualizacaoDoSistema();
    BlocoDeTexto blocoDeTexto = getBlocoDeTexto();

    @E("acesso a TAB DOCUMENTAÇÃO no CDP")
    public void acessoATABDOCUMENTAÇÃONoCDP() {
        documentacaoInformacoesPage.clicarTABDocumentacao();
        addLogToReport("Cliquei na TAB Documentação");
    }

    @Então("valido as informações de Documentação oficial do projeto  no CDP")
    public void validoAsInformaçõesDeDocumentaçãoOficialDoProjetoNoCDP() {
        documentacaoInformacoesPage.validaInformacoesDeDocumentacaoNoCDP(atualizacaoDoSistema);
        addScreenshotToReport("Valido as informações de Documentação oficial do projeto  no CDP");
    }

    @Então("valido que o bloco de texto Documentação do projeto no CDP")
    public void validoQueOBlocoDeTextoDocumentaçãoDoProjetoNoCDP() {
        documentacaoInformacoesPage.validaDadosBlocoDeTexto(blocoDeTexto, 1);
        addScreenshotToReport("Validado dados do bloco do texto.");
    }
}
