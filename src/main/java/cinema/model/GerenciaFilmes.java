package cinema.model;

/**
 * Operações de gerência de filmes.
 * Implementada por Funcionario e Administrador.
 */
public interface GerenciaFilmes {

    /** Inclui um filme. Retorna false se o filme for nulo ou se já existir um filme com o mesmo nome. */
    boolean incluirFilme(Filme filme);

    /** Exclui o filme com o nome informado. Retorna false se não encontrar. */
    boolean excluirFilme(String nome);

    /** Substitui o filme com o nome informado pelo novo filme. Retorna false se não encontrar. */
    boolean alterarFilme(String nome, Filme novoFilme);
}
