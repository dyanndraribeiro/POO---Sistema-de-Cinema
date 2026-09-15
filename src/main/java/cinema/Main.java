package cinema;

import cinema.model.Filme;
import cinema.model.Sala;
import cinema.model.Sessao;

public class Main {

    public static void main(String[] args) {
        Filme filme = new Filme("Gato de Botas 2", 102, "Sinopse de exemplo", 20.0);
        Sessao sessao = new Sessao(filme, "08:00 - 10:00");

        Sala sala = new Sala("Sala 1");
        sala.definirSessao(0, sessao);

        System.out.println("Sala: " + sala.getNome());
        System.out.println("Sessão 0: " + sala.getSessoes()[0].getFilme());
    }
}
