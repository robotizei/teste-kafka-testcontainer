#language: pt

@CCAO @UC13 @WEB @SPRINT01 @FROTAS @NOVAFROTA
Funcionalidade: UC13 - Frotas - Criação e Edição de frotas

  Cenario:  CT01 - Criar nova Frota
    Dado que eu navegue até o Circulação
    E efetue login com perfil com permissão de Criação
    E acesso a TAB Frotas
    Quando clico em Criar nova frota
    E preencho todos os dados da frota
    E clico em Criar nova Frota
    Entao deve exibir o modal com:
      | TITLE                 | CONTENT                 | BUTTON1  | BUTTON2   |
      | CRIAÇÃO DE NOVA FROTA | Deseja cria nova frota? | Cancelar | Confirmar |
    E clico em "Confirmar" no modal
    E deve exibir a snackBar com a mensagem "Nova frota criado com sucesso!"
    E a nova frota deve ser exibida na listagem de frotas

  Esquema do Cenario: <CT02> - Editar uma frota
    Dado que eu navegue até o Circulação
    * efetue login com perfil com permissão de Edição
    * acesso a TAB Frotas
    * que eu tenha uma frota do tipo "<TIPO>" cadastrado
    Quando clico em editar a frota
    Entao deve exibir os detalhes da fronta
    Mas eu edito os dados da frota
    E clico em Salvar frota
    Entao deve exibir o modal com:
      | TITLE           | CONTENT                      | BUTTON1  | BUTTON2   |
      | EDIÇÃO DA FROTA | Deseja salvar as alterações? | Cancelar | Confirmar |
    E clico em "Confirmar" no modal
    E deve exibir a snackBar com a mensagem "Alterações salvas com sucesso!"

    Exemplos:
      | CT   | TIPO       |
      | CT02 | VAGAO      |
      | CT03 | LOCOMOTIVA |

  Esquema do Cenario:  <CT> - Procurar frota pelo campo <CAMPO>
    E acesso a TAB Frotas
    Quando eu busque por "<CAMPO>" na listagem de Frotas
    Então deve exibir o resultado da busca no campo "<CAMPO>" na listagem de Frotas

    Exemplos:
      | CT   | CAMPO              |
      | CT04 | CÓDIGO DA FROTA    |
      | CT05 | DESCRIÇÃO DA FROTA |

  Esquema do Cenario: <CT> - Validar filtro da modal com resultado válidos para Frotas - <FILTRO>
    E acesso a TAB Frotas
    Quando clico no botão Filtrar de Frota
    E insiro um filtro para "<FILTRO>" no filtro de Frota
    E clico no botão Filtrar da modal de filtro de Frota
    Entao deve ser exibido os registros que se enquadra com o filtro "<FILTRO>" na lista de Frotas

    Exemplos:
      | CT   | FILTRO           |
      | CT06 | CÓDIGO           |
      | CT07 | SEGMENTO         |
      | CT08 | UNIDADE_PRODUÇÃO |

  Esquema do Cenario:  <CT> - Procurar Frota com texto que não existe
    Quando eu busque por "<CAMPO>" na listagem de Frotas
    Então não deve exibir o resultado da busca na listagem de Frota

    Exemplos:
      | CT   | CAMPO    |
      | CT09 | SEGMENTO |
