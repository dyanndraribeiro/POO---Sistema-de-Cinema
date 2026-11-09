
package cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Base implements GerenciaFilmes {

    private double salario;
    private int id;
    private List<Usuario> usuarios;
    private List<Filme> filmes;

    public Administrador() {
        usuarios = new ArrayList<>();
        filmes = new ArrayList<>();
    }

    public Administrador(String nome, int idade, String email,
                         double salario, int id) {
        super(nome, idade, email);
        this.salario = salario;
        this.id = id;
        usuarios = new ArrayList<>();
        filmes = new ArrayList<>();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    @Override
    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null) return false;

        for (Usuario u : usuarios) {
            if (u.getCpf() != null &&
                u.getCpf().equals(usuario.getCpf())) {
                return false;
            }
        }

        usuarios.add(usuario);
        return true;
    }

    @Override
    public boolean alterarUsuario(Usuario usuario) {
        if (usuario == null) return false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf() != null &&
                usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                usuarios.set(i, usuario);
                return true;
            }
        }

        return false;
    }

    public boolean excluirUsuario(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf() != null && u.getCpf().equals(cpf)) {
                usuarios.remove(u);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean incluirFilme(Filme filme) {
        if (filme == null) return false;

        for (Filme f : filmes) {
            if (f.getNome().equalsIgnoreCase(filme.getNome())) {
                return false;
            }
        }

        filmes.add(filme);
        return true;
    }

    @Override
    public boolean excluirFilme(String nome) {
        for (Filme f : filmes) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                filmes.remove(f);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean alterarFilme(String nome, Filme novoFilme) {
        if (novoFilme == null) return false;

        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equalsIgnoreCase(nome)) {
                filmes.set(i, novoFilme);
                return true;
            }
        }

        return false;
    }
}
