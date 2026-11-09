package cinema.model;

/**
 * Classe abstrata base para quem gerencia o sistema (Funcionario e Administrador).
 */
public abstract class Base {

    private String nome;
    private int idade;
    private String email;

    public Base() {
    }

    public Base(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** Adiciona um usuário. Retorna false se não for possível (nulo ou CPF já cadastrado). */
    public abstract boolean adicionarUsuario(Usuario usuario);

    /** Altera os dados de um usuário já cadastrado (identificado pelo CPF). Retorna false se não encontrar. */
    public abstract boolean alterarUsuario(Usuario usuario);
}
