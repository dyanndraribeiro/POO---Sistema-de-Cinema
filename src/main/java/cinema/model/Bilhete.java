package cinema.model;

public class Bilhete {

    private String user;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private double valor; // calculado com base no filme e na sala escolhidos
    private String cadeiraComprada; // ex: "A1", "J15"

    public Bilhete() {
    }

    public Bilhete(String user, String cpf, Sala sala, Sessao sessao, Filme filme,
                    double valor, String cadeiraComprada) {
        this.user = user;
        this.cpf = cpf;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = valor;
        this.cadeiraComprada = cadeiraComprada;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCadeiraComprada() {
        return cadeiraComprada;
    }

    public void setCadeiraComprada(String cadeiraComprada) {
        this.cadeiraComprada = cadeiraComprada;
    }
}
