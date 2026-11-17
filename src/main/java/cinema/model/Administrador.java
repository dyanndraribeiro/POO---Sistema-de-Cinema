package cinema.model;

import java.util.List;

public class Administrador extends Base implements GerenciaFilmes {

    private double salario;
    private int id;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioFilmes repositorioFilmes;

    public Administrador() {
        this.repositorioUsuarios = new RepositorioUsuarios();
        this.repositorioFilmes = new RepositorioFilmes();
    }

    public Administrador(String nome, int idade, String email,
                         double salario, int id) {
        this(nome, idade, email, salario, id,
             new RepositorioUsuarios(), new RepositorioFilmes());
    }

    public Administrador(String nome, int idade, String email,
                         double salario, int id,
                         RepositorioUsuarios repositorioUsuarios,
                         RepositorioFilmes repositorioFilmes) {
        super(nome, idade, email);
        this.salario = salario;
        this.id = id;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioFilmes = repositorioFilmes;
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

    public RepositorioUsuarios getRepositorioUsuarios() {
        return repositorioUsuarios;
    }

    public void setRepositorioUsuarios(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public RepositorioFilmes getRepositorioFilmes() {
        return repositorioFilmes;
    }

    public void setRepositorioFilmes(RepositorioFilmes repositorioFilmes) {
        this.repositorioFilmes = repositorioFilmes;
    }

    public List<Usuario> getUsuarios() {
        return repositorioUsuarios.listar();
    }

    public List<Filme> getFilmes() {
        return repositorioFilmes.listar();
    }

    @Override
    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null) return false;
        return repositorioUsuarios.incluir(usuario);
    }

    @Override
    public boolean alterarUsuario(Usuario usuario) {
        if (usuario == null) return false;
        return repositorioUsuarios.alterar(usuario.getCpf(), usuario);
    }

    public boolean excluirUsuario(String cpf) {
        return repositorioUsuarios.excluir(cpf);
    }

    @Override
    public boolean incluirFilme(Filme filme) {
        if (filme == null) return false;
        return repositorioFilmes.incluir(filme);
    }

    @Override
    public boolean excluirFilme(String nome) {
        return repositorioFilmes.excluir(nome);
    }

    @Override
    public boolean alterarFilme(String nome, Filme novoFilme) {
        if (novoFilme == null) return false;
        return repositorioFilmes.alterar(nome, novoFilme);
    }
}
