# Sistema de Cinema — Projeto de POO

Projeto da disciplina de Programação Orientada a Objetos (UNICAP) — implementação de um sistema de compra de bilhetes de cinema.

**Professor:** Jheymesson A. Cavalcanti
**Equipe:** 4 integrantes

## Cronograma de entregas

| Etapa | Conteúdo | Prazo |
|---|---|---|
| 1 | Classes e encapsulamento | 2026 |
| 2 | Herança e sobrecarga | 2026 |
| 3 | Classe abstrata e exceções | 2026 |
| 4 | Padrões de projeto | — |

## Estrutura do projeto

```
src/main/java/cinema/
├── Main.java
└── model/
    ├── Usuario.java
    ├── Sala.java
    ├── Sessao.java
    ├── Filme.java
    ├── Bilhete.java
    └── Compra.java
```

## Classes (Etapa 1)

- **Usuario** — dados de quem compra os bilhetes (user, CPF, senha, idade, sexo, email, dados do cartão) e pode realizar/alterar/cancelar uma compra.
- **Sala** — contém uma lista de sessões (grade de horário de tamanho fixo).
- **Sessao** — filme em exibição, horário e matriz de cadeiras (10 linhas x 15 colunas).
- **Filme** — nome, duração, sinopse e valor.
- **Bilhete** — dados da compra individual: usuário, sala, sessão, filme, valor e cadeira escolhida.
- **Compra** — itens de bomboniere (pipoca, refrigerante, chocolate, ...) e lista de bilhetes.

Todos os atributos são `private`, com getters/setters e construtores `public`, conforme especificado no projeto.

## Como rodar

```bash
cd src/main/java
javac cinema/model/*.java cinema/Main.java
java cinema.Main
```

## Fluxo de trabalho do grupo

- Cada integrante trabalha em uma branch própria (ex: `feature/classe-usuario`).
- Abrir Pull Request antes de mesclar na `main`.
- Combinar quem fica responsável por qual classe antes de começar.
