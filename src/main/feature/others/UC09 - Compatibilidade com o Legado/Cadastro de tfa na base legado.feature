#language: pt

@CDP @UC09 @DB @SPRINT05 @KAFKA
Funcionalidade:  UC09 - Compatibilidade com o Legado - Validar view translogic

  Cenario:  CT01 - Cadastrar novo TFA e validar View VW_PRISMA_TFA_KAFKA_INCREMENT
#  SELECT * FROM VW_PRISMA_TFA_KAFKA_INCREMENT WHERE DESPACHO = '64594';
    Dado que eu cadastro um novo TFA no translogic
    Então deve exibir esse TFA na View "VW_PRISMA_TFA_KAFKA_INCREMENT"

  Cenario:  CT02 - Editar TFA no translogic e validar View VW_PRISMA_TFA_KAFKA_TIMESTAMP
#  SELECT * FROM VW_PRISMA_TFA_KAFKA_TIMESTAMP WHERE DESPACHO = '64594';
    Dado que eu edito um TFA no translogic
    Então deve exibir esse TFA na View "VW_PRISMA_TFA_KAFKA_TIMESTAMP"