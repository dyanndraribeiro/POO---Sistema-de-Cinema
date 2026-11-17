package cinema.controller;

import cinema.model.Administrador;
import cinema.model.Filme;
import cinema.view.TelaAdministracaoFilmes;

public class AdministracaoFilmesController {

    private Administrador administrador;
    private TelaAdministracaoFilmes tela;

    public AdministracaoFilmesController(
            Administrador administrador,
            TelaAdministracaoFilmes tela) {

        this.administrador = administrador;
        this.tela = tela;

        atualizarLista();

        tela.getBtnIncluir().addActionListener(e -> incluirFilme());
        tela.getBtnExcluir().addActionListener(e -> excluirFilme());
        tela.getBtnAlterar().addActionListener(e -> alterarFilme());

        tela.getListaFilmes().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                carregarFilme();
            }
        });
    }

    private Filme criarFilme() {
        String nome = tela.getNome();
        int duracao = Integer.parseInt(tela.getDuracao());
        String descricao = tela.getDescricao();
        double preco = Double.parseDouble(tela.getPreco());

        Filme filme = new Filme(nome, duracao, descricao, preco);
        filme.setEmCartaz(tela.isEmCartaz());
        return filme;
    }

    private void incluirFilme() {
        try {
            administrador.incluirFilme(criarFilme());

            atualizarLista();
            tela.limparCampos();
            tela.mensagem("Filme incluído com sucesso!");

        } catch (NumberFormatException e) {
            tela.mensagem("Informe uma duração e um preço válidos.");
        }
    }

    private void excluirFilme() {
        String nome = tela.getFilmeSelecionado();

        if (nome == null) {
            tela.mensagem("Selecione um filme para excluir.");
            return;
        }

        administrador.excluirFilme(nome);

        atualizarLista();
        tela.limparCampos();
        tela.mensagem("Filme excluído com sucesso!");
    }

    private void alterarFilme() {
        String nomeAntigo = tela.getFilmeSelecionado();

        if (nomeAntigo == null) {
            tela.mensagem("Selecione um filme para alterar.");
            return;
        }

        try {
            Filme novoFilme = criarFilme();

            administrador.alterarFilme(nomeAntigo, novoFilme);

            atualizarLista();
            tela.limparCampos();
            tela.mensagem("Filme alterado com sucesso!");

        } catch (NumberFormatException e) {
            tela.mensagem("Informe uma duração e um preço válidos.");
        }
    }

    private void carregarFilme() {
        String nome = tela.getFilmeSelecionado();

        if (nome == null) return;

        for (Filme filme : administrador.getFilmes()) {
            if (filme.getNome().equals(nome)) {
                tela.preencherCampos(filme);
                break;
            }
        }
    }

    private void atualizarLista() {
        tela.atualizarLista(administrador.getFilmes());
    }
}
