#language: pt

@CCAO @UC13 @WEB @SPRINT01 @PERMISSAO @PERMISSAO_FROTAS
Funcionalidade: UC13 - Frotas - Permissionamento de frotas

  Contexto:
    Dado que eu navegue até o Circulação

  Esquema do Cenario:  <CT> - Validar permissão Frotas com perfil <PERFIL>
    Quando  efetue login com perfil com permissão de <PERFIL>
    E acesso a TAB Frotas
    Então valido que o usuário tem permissão de <PERMISSAO> na tab Frotas

    Exemplos:
      | CT   | PERFIL       | PERMISSAO    |
      | CT01 | Admin        | edição       |
      | CT02 | Criação      | criação      |
      | CT03 | Edição       | edição       |
      | CT04 | Visualização | visualização |