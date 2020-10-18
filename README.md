# Testes Automatizados 

Automação do projeto xxxxxxxx, aplicando linguagem gherkin.

## PRÉ-REQUISITOS

*   Java 1.8 SDK
*   Maven 3.5.*
*   Plugin do Cucumber
*   Plugin do Gherkin
*   Plugin do Lombok

## EXECUTANDO OS TESTES

```
Para executar os testes é necessário estar com os projetos de administração e controle em execução.
  
*   Os testes de informações acessam o front-end do Controle de Perdas e do Administração.

*   Demais testes utilizam apenas o controle de perda.

Excutar o teste via maven:

            mvn clean compile verify -Plocal

    Obs: O parâmetro -P indica o profile(dev, qas ou local) a ser utilizado.

Para gerar o report html quando executado os testes pela IDE, deve ser executado o comando:
        
        mvn cluecumber-report:reporting
        
```

## AUTORES

* **Deovan Zanol**
