#language: pt

@CCAO @UC13 @KAFKA @SPRINT01 @FROTAS @FROTAKAFKA
Funcionalidade: UC13 - Frotas - Compatibilidade com o Legado

  Cenario:  CT01 - Cadastrar nova FROTA no translogic e validar no prisma
    Dado que eu cadastro uma nova FROTA no translogic
    Então deve exibir essa FROTA na View "VW_PRISMA_FROTA_KAFKA_INCREMENT"
    E adiciono os connectors da FROTA
    Entao a frota deve ser adicionado na base do Circulação
    E removo os connectors da FROTA

  Cenario:  CT02 - Editar FROTA no translogic e validar no prisma
    Dado que eu edito uma FROTA no translogic
    Então deve exibir esse FROTA na View "VW_PRISMA_FROTA_KAFKA_TIMESTAMP"
    E adiciono os connectors da FROTA
    Então a frota deve ser adicionado na base do Circulação
    E removo os connectors da FROTA

  Cenario:  CT03 - Cadastrar nova FROTA no prisma e validar no translogic
    Dado que eu cadastro uma nova FROTA no prisma
    Então deve exibir esse FROTA na View "VW_PRISMA_FROTA_KAFKA_INCREMENT"
    E adiciono os connectors da FROTA
    Então a frota deve ser adicionado na base do translogic
    E removo os connectors da FROTA

  Cenario:  CT04 - Editar FROTA no prisma e validar no translogic
    Dado que eu edito um FROTA no prisma
    Então deve exibir esse FROTA na View "VW_PRISMA_FROTA_KAFKA_TIMESTAMP"
    E adiciono os connectors da FROTA
    Então a frota deve ser adicionado na base do translogic
    E removo os connectors da FROTA