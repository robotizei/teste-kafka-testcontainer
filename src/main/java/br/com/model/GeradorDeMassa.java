package br.com.model;

import br.com.model.informacoes.AtualizacaoDoSistema;
import br.com.model.informacoes.BlocoDeTexto;
import br.com.model.informacoes.MembroEquipe;
import br.com.model.informacoes.SobreOSetor;

import static br.com.model.GeradorUtils.getRandomCharacters;

public class GeradorDeMassa {

    public static String featureName;
    private static String descricao;
    private static String conclusao;
    private static String comentario4000Char;
    private static String comentario2000Char;
    private static SobreOSetor sobreOSetor;
    private static MembroEquipe membroEquipe;
    private static AtualizacaoDoSistema atualizacaoDoSistema;
    private static BlocoDeTexto blocoDeTexto;

    public static void updateValuesMassas() {
        descricao = null;
        conclusao = null;
        comentario4000Char = null;
        comentario2000Char = null;
        sobreOSetor = null;
        membroEquipe = null;
        atualizacaoDoSistema = null;
        blocoDeTexto = null;
    }

    public static BlocoDeTexto getNewBlocoDeTexto() {
        return new BlocoDeTexto();
    }

    public static BlocoDeTexto getBlocoDeTexto() {
        if (blocoDeTexto == null) {
            blocoDeTexto = getNewBlocoDeTexto();
        }
        return blocoDeTexto;
    }

    public static MembroEquipe getNewMembroEquipe() {
        return new MembroEquipe();
    }

    public static MembroEquipe getMembroEquipe() {
        if (membroEquipe == null) {
            membroEquipe = getNewMembroEquipe();
        }
        return membroEquipe;
    }


    public static AtualizacaoDoSistema getNewAtualizacaoDoSistema() {
        return new AtualizacaoDoSistema();
    }

    public static AtualizacaoDoSistema getAtualizacaoDoSistema() {
        if (atualizacaoDoSistema == null) {
            atualizacaoDoSistema = getNewAtualizacaoDoSistema();
        }
        return atualizacaoDoSistema;
    }

    public static SobreOSetor getNewSobreOSetor() {
        return new SobreOSetor();
    }

    public static SobreOSetor getSobreOSetor() {
        if (sobreOSetor == null) {
            sobreOSetor = getNewSobreOSetor();
        }
        return sobreOSetor;
    }

    public static String getNewComentario4000Char() {
        return getRandomCharacters(4000);
    }

    public static String getComentario4000Char() {
        if (comentario4000Char == null) {
            comentario4000Char = getNewComentario4000Char();
        }
        return comentario4000Char;
    }

    public static String getNewComentario2000Char() {
        return getRandomCharacters(2000);
    }

    public static String getComentario2000Char() {
        if (comentario2000Char == null) {
            comentario2000Char = getNewComentario2000Char();
        }
        return comentario2000Char;
    }

    public static String getNewDescricao() {
        return getRandomCharacters(2000);
    }

    public static String getDescricao() {
        if (descricao == null) {
            descricao = getNewDescricao();
        }
        return descricao;
    }

    public static String getNewConclusao() {
        return getRandomCharacters(2000);
    }

    public static String getConclusao() {
        if (conclusao == null) {
            conclusao = getNewConclusao();
        }
        return conclusao;
    }

    public static String getHTMLText() {
        String aHTML = "<p><strong>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</strong> ;</p><p><i>Aliquam luctus turpis lectus, at sollicitudin purus molestie et</i>. ;</p><p><a href=\"https://br.lipsum.com/feed/html\">Aliquam interdum eros a velit pretium molestie eget a ante</a>. ;</p><ol><li>Praesent ligula mi, lobortis vel neque at, euismod fringilla justo. ;</li></ol><ul><li>Morbi sit amet placerat diam, sit amet vestibulum tellus.<ul><li>;Nam suscipit lectus ut scelerisque dapibus. Cras ut sem eu orci semper aliquam. Quisque porttitor ligula arcu. Curabitur pulvinar ipsum quam, sed pharetra eros dapibus a. ;</li><li>Cras lacinia, leo et feugiat cursus, lectus tortor facilisis turpis, in lobortis eros nunc in ante. Aliquam nec sodales ipsum, et semper ipsum. Aliquam eu blandit lacus. Etiam.</li></ul></li></ul>";
        return aHTML;
    }

}