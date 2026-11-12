package cinema.model;

public class Filme {

    private String nome;
    private int duracao;
    private String sinopse;
    private double valor;
    private double nota;
    private int quantidadeCriticos;
    private Critica[] critica = new Critica[100];
    private int totalCriticas;
    private boolean emCartaz = true;

    public Filme() {
    }

    public Filme(String nome, int duracao, String sinopse, double valor) {
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getQuantidadeCriticos() {
        return quantidadeCriticos;
    }

    public void setQuantidadeCriticos(int quantidadeCriticos) {
        this.quantidadeCriticos = quantidadeCriticos;
    }

    public Critica[] getCritica() {
        return critica;
    }

    public void setCritica(Critica[] critica) {
        this.critica = critica;
    }

    public int getTotalCriticas() {
        return totalCriticas;
    }

    public void setTotalCriticas(int totalCriticas) {
        this.totalCriticas = totalCriticas;
    }

    public boolean isEmCartaz() {
        return emCartaz;
    }

    public void setEmCartaz(boolean emCartaz) {
        this.emCartaz = emCartaz;
    }

    public void adicionarCritica(Critica novaCritica) {
        if (totalCriticas >= critica.length) {
            throw new IllegalStateException("Limite de críticas do filme atingido.");
        }
        critica[totalCriticas] = novaCritica;
        totalCriticas++;
    }

    @Override
    public String toString() {
        return nome + " (" + duracao + " min) - R$ " + valor;
    }
}
