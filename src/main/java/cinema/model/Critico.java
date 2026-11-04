package cinema.model;

public class Critico extends Usuario {

    private String origem;

    public Critico() {
    }

    public Critico(String user, String cpf, String senha, int idade, String sexo, String email,
                    String nomeCartao, String numeroCartao, String codigoVerificadorCartao,
                    String origem) {
        super(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao, codigoVerificadorCartao);
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void atribuirNota(double nota, Filme filme) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        int quantidade = filme.getQuantidadeCriticos();
        double novaMedia = ((filme.getNota() * quantidade) + nota) / (quantidade + 1);
        filme.setQuantidadeCriticos(quantidade + 1);
        filme.setNota(novaMedia);
    }

    public void atribuirCritica(String critica, Filme filme) {
        Critica novaCritica = new Critica(getUser(), origem, critica);
        filme.adicionarCritica(novaCritica);
    }

    @Override
    public double calcularValorBilhete(double valor) {
        return 0.0;
    }
}
