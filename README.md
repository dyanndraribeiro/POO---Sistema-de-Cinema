# Sistema de Cinema — Projeto de POO

Projeto da disciplina de Programação Orientada a Objetos (UNICAP) — sistema de compra de bilhetes de cinema, com interface gráfica (Java Swing).

**Professor:** Jheymesson A. Cavalcanti
**Equipe:** 4 integrantes

## Cronograma de entregas

| Etapa | Conteúdo |
|---|---|
| 1 | Classes e encapsulamento |
| 2 | Herança e sobrecarga |
| 3 | Classe abstrata, interface e exceções |
| 4 | Padrão de projeto (MVC) e interface gráfica |

## Como compilar e rodar

Pré-requisito: JDK instalado (comandos `javac` e `java`).

```bash
cd src/main/java
javac -encoding UTF-8 -d ../../../out cinema/*.java cinema/model/*.java cinema/controller/*.java cinema/view/*.java
java -cp ../../../out cinema.Main
```

Ao rodar, abre a tela de login. Usuários de exemplo (senha de todos: `1234`):

| Usuário | Tipo | Preço do bilhete |
|---|---|---|
| Ana Souza | Usuario | valor inteiro |
| Carlos Silva | Estudante | meia-entrada (50%) |
| Marina Lima | Critico | gratuito |

Os filmes são salvos no arquivo `filmes.txt`, criado na pasta onde o programa é executado.

## Padrão de projeto: MVC

O projeto está organizado em Model, View e Controller:

```
src/main/java/cinema/
├── Main.java            monta o sistema e liga as camadas
├── model/               regras de negócio e dados
├── controller/          recebe as ações da tela e chama o model
└── view/                telas (Swing); só exibem e leem dados
```

- **Model** (`cinema/model`): Usuario, Estudante, Critico, Filme, Sala, Sessao, Bilhete, Compra, enums (Produto, CupomPromocional, TipoSala), exceção `VendasException` e os repositórios que gravam em arquivo (`RepositorioFilmes`, `RepositorioUsuarios`). Não conhece nenhuma tela.
- **View** (`cinema/view`): TelaLogin, TelaPrincipal, TelaCompra, TelaFilmesCartaz, TelaAdministracaoFilmes. As telas falam com o sistema por interfaces (`CompraGateway`, `FilmesGateway`, `LoginGateway`) e não acessam o model diretamente.
- **Controller** (`cinema/controller`): CompraController, FilmeController, UsuarioController, AdministracaoFilmesController. Recebem o pedido da tela, aplicam o model e devolvem o resultado.

## Conceitos de POO usados

- **Encapsulamento:** atributos `private` com getters/setters em todas as classes.
- **Herança e polimorfismo:** `Estudante` e `Critico` estendem `Usuario` e sobrescrevem `calcularValorBilhete` (meia-entrada e gratuidade).
- **Sobrecarga:** `comprarBilhete` existe com e sem cupom promocional.
- **Classe abstrata:** `Base`, que `Administrador` e `Funcionario` estendem.
- **Interface:** `GerenciaFilmes`, implementada por `Administrador` e `Funcionario`.
- **Exceções:** `VendasException` para filme fora de cartaz, sessão já passada e poltrona ocupada.
- **Enums:** `Produto`, `CupomPromocional` e `TipoSala`.

## Fluxo de trabalho do grupo

- Cada integrante trabalha em uma branch própria (ex: `feature/nome-da-tarefa`).
- Abrir Pull Request antes de mesclar na `main`.
- Combinar quem fica responsável por qual classe antes de começar.