package cinema.model;

public class Usuario {

    private String user;
    private String cpf;
    private String senha;
    private int idade;
    private String sexo;
    private String email;
    private String nomeCartao;
    private String numeroCartao;
    private String codigoVerificadorCartao;

    public Usuario() {
    }

    public Usuario(String user, String cpf, String senha, int idade, String sexo, String email,
                    String nomeCartao, String numeroCartao, String codigoVerificadorCartao) {
        this.user = user;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.nomeCartao = nomeCartao;
        this.numeroCartao = numeroCartao;
        this.codigoVerificadorCartao = codigoVerificadorCartao;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCodigoVerificadorCartao() {
        return codigoVerificadorCartao;
    }

    public void setCodigoVerificadorCartao(String codigoVerificadorCartao) {
        this.codigoVerificadorCartao = codigoVerificadorCartao;
    }

    /**
     * Realiza uma nova compra para este usuário.
     * TODO: implementar integração com a classe Compra.
     */
    public Compra realizarCompra(Compra compra) {
        return compra;
    }

    /**
     * Altera uma compra já existente.
     * TODO: implementar regra de alteração.
     */
    public void alterarCompra(Compra compra) {
    }

    /**
     * Cancela uma compra já existente.
     * TODO: implementar regra de cancelamento.
     */
    public void cancelarCompra(Compra compra) {
    }
}
