public class Estudante extends Usuario {

    public Estudante(String usuario, String senha, int idade,
                     String sexo, String email) {

        super(usuario, senha, idade, sexo, email);
    }

    @Override
    public double comprarBilhete(double valor) {

        return valor / 2;
    }
}