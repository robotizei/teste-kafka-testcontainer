package br.com.model.informacoes;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

import static br.com.model.GeradorDeMassa.getHTMLText;
import static br.com.model.GeradorUtils.getDayMinus;
import static br.com.model.GeradorUtils.getRandomCharacters;

@Getter
@Setter
public class AtualizacaoDoSistema {
    private Faker faker;
    private String versao;
    private String descricao;
    private String data;
    private String realizadoPor;
    private String documentacaoOficialDoProjeto;

    public AtualizacaoDoSistema() {
        faker = new Faker(new Locale("pt-BR"));
        versao = faker.name().name();
        descricao = getRandomCharacters(256);
        data = getDayMinus(1);
        realizadoPor = getRandomCharacters(256);
        documentacaoOficialDoProjeto = getHTMLText();
    }
}