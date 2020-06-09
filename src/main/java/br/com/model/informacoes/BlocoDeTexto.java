package br.com.model.informacoes;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

import static br.com.model.GeradorDeMassa.getHTMLText;

@Getter
@Setter
public class BlocoDeTexto {
    private Faker faker;
    private String titulo;
    private String descricao;

    public BlocoDeTexto() {
        faker = new Faker(new Locale("pt-BR"));
        titulo = faker.name().name();
        descricao = getHTMLText();
    }
}