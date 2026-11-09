package cinema.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Funcionário do cinema: gerencia usuários e filmes.
 * Por enquanto os dados ficam em listas na memória; a persistência em arquivo é feita à parte.
 */
public class Funcionario extends Base implements GerenciaFilmes {

    private double salario;
    private List<Usuario> usuarios;
    private List<Filme> filmes;

    public Funcionario() {
        this.usuarios = new ArrayList<>();
        this.filmes = new ArrayList<>();
    }

    public Funcionario(String nome, int idade, String email, double salario) {
        super(nome, idade, email);
        this.salario = salario;
        this.usuarios = new ArrayList<>();
        this.filmes = new ArrayList<>();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    // ---- Métodos abstratos de Base ----

    @Override
    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null || buscarUsuario(usuario.getCpf()) != null) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    @Override
    public boolean alterarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                usuarios.set(i, usuario);
                return true;
            }
        }
        return false;
    }

    // ---- Interface GerenciaFilmes ----

    @Override
    public boolean incluirFilme(Filme filme) {
        if (filme == null || buscarFilme(filme.getNome()) != null) {
            return false;
        }
        filmes.add(filme);
        return true;
    }

    @Override
    public boolean excluirFilme(String nome) {
        Filme filme = buscarFilme(nome);
        return filme != null && filmes.remove(filme);
    }

    @Override
    public boolean alterarFilme(String nome, Filme novoFilme) {
        if (novoFilme == null) {
            return false;
        }
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equalsIgnoreCase(nome)) {
                filmes.set(i, novoFilme);
                return true;
            }
        }
        return false;
    }

    // ---- Auxiliares ----

    private Usuario buscarUsuario(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf() != null && u.getCpf().equals(cpf)) {
                return u;
            }
        }
        return null;
    }

    private Filme buscarFilme(String nome) {
        for (Filme f : filmes) {
            if (f.getNome() != null && f.getNome().equalsIgnoreCase(nome)) {
                return f;
            }
        }
        return null;
    }
}
