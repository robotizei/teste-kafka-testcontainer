package br.com.model.informacoes;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

import static br.com.model.GeradorUtils.getRandomCharacters;

@Getter
@Setter
public class MembroEquipe {
    private Faker faker;
    private boolean exibirDadosEquipe;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;

    public MembroEquipe() {
        faker = new Faker(new Locale("pt-BR"));
        nome = getRandomCharacters(256);
        cargo = getRandomCharacters(256);
        email = faker.options().option("emailtest1@email.com", "emailfake@email.com", "faketeste@email.com");
        telefone = faker.options().option("(41) 15846-2548", "(11) 82468-1547", "(41) 98765-4321");
        exibirDadosEquipe = faker.bool().bool();
    }
}