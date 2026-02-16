# Sistema de Gestão de Agricultura Familiar 🌿👨‍👩‍👧

Sistema via console (terminal) criado para apoiar o resgistro e controle de informações na agricultura familiar, com fogo em organização de dados e aplicações de regras de negócio diárias (plantio, manejo, colheita e vendas).

Projeto desenvolvido na Linguagem de progamação java em conjunto com a ferramenta de gerenciamento Maven, sendo estruturado em camadas (`domain`, `service`, `app`, `ui`).

## Funcionalidades

O sistema permite as operações de `cadastro`, `remoção`. `listagem`, `busca` e `atualização` de:

- Produtores
- Áreas de Cultivo
- Culturas
- Plantios
- Manejos
- Colheitas
- Vendas

## Regras de Negócio Implementadas

- IDs sempre positivos para todas as entidades.
- Datas não podem ser futuras.
- Valores numéricos devem ser válidos e positivos quando aplicável.
- Perdas de colheita não podem ser negativas, nem maiores que a quantidade colhida.
- Não é possível vender mais do que o disponível (quantidade colhida - perda - total já vendido).

## Funcionamento da Atualização de Dados
O sistema permite manter os valores antigos sem a necessidade de redigitação:
- Campos de texto: deixar em branco mantém o valor
- IDs/quantidades: usar 0 para manter o valor
- Data: deixar em branco mantém a data
- BigDecimal: deixar em branco mantém o valor

## Estrutura do projeto
```bash

src/main/java/
   ├──br.com.gabriel.gestaoagricola
       ├──domain
       ├──service
       └──app
       	  ├──ui
    	  └──Main.java 
```

## Tecnologias utilizadas
- Java
- Maven
- LocalDate
- BigDecimal
- Executado via terminal/IDE

## Próximos passos 
- Migrar para Spring Boot (API REST)
- Persistência com MySQL
- Padronizar tratamento de exceções
- Adicionar testes unitários
