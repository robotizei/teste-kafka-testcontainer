package br.com.model.informacoes;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

import static br.com.model.GeradorDeMassa.getHTMLText;
import static br.com.model.GeradorUtils.getRandomCharacters;

@Getter
@Setter
public class SobreOSetor {
    private Faker faker;
    private String sobreSetor;
    private String telefoneDoSetor;
    private String localizacaoFisica;

    public SobreOSetor() {
        faker = new Faker(new Locale("pt-BR"));
        sobreSetor = getHTMLText();
        telefoneDoSetor = faker.options().option("(12) 31231-2312", "(43) 99999-4949");
        localizacaoFisica = getRandomCharacters(256);
    }
}