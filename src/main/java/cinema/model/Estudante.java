package cinema.model;

public class Estudante extends Usuario {

    public Estudante(String user, String cpf, String senha, int idade,
                     String sexo, String email, String nomeCartao,
                     String numeroCartao, String codigoVerificadorCartao) {

        super(user, cpf, senha, idade, sexo, email, nomeCartao,
              numeroCartao, codigoVerificadorCartao);
    }

    @Override
    public double calcularValorBilhete(double valor) {
        return valor / 2;
    }
}