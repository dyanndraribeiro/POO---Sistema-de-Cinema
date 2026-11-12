package cinema;

import cinema.model.Filme;
import cinema.model.Sala;
import cinema.model.Sessao;
import cinema.model.Usuario;
import cinema.model.Compra;
import cinema.model.VendasException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Filme filme = new Filme("Gato de Botas 2", 102, "Sinopse de exemplo", 20.0);
        Sessao sessao = new Sessao(filme, "08:00 - 10:00");

        // Define a sessão para amanhã (horário válido)
        sessao.setDataHoraSessao(LocalDateTime.now().plusDays(1));

        Sala sala = new Sala("Sala 1");
        sala.definirSessao(0, sessao);

        // Criando usuário e objeto de compra
        Usuario carlos = new Usuario("Carlos", "12345678900", "senha123", 22, "Masculino", "carlosb@email.com", "Nubank", "1234", "123");
        Compra compra = new Compra();

        System.out.println("--- TESTES DE COMPRA DE BILHETES ---");

        // TESTE 1: Compra bem-sucedida
        try {
            System.out.println("\nTentando comprar poltrona [0][0]...");
            compra.comprarBilhete(carlos, sala, sessao, 0, 0);
            System.out.println("Sucesso: Bilhete comprado!");
        } catch (VendasException e) {
            System.out.println(e.getMessage());
        }

        // TESTE 2: Erro de poltrona já selecionada
        try {
            System.out.println("\nTentando comprar a MESMA poltrona [0][0]...");
            compra.comprarBilhete(carlos, sala, sessao, 0, 0);
        } catch (VendasException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        // TESTE 3: Erro de horário passado
        try {
            System.out.println("\nTentando comprar para uma sessão que já passou...");
            sessao.setDataHoraSessao(LocalDateTime.now().minusDays(1)); // Volta o tempo para ontem
            compra.comprarBilhete(carlos, sala, sessao, 0, 1);
        } catch (VendasException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        // TESTE 4: Erro de filme fora de cartaz
        try {
            System.out.println("\nTentando comprar filme fora de cartaz...");
            sessao.setDataHoraSessao(LocalDateTime.now().plusDays(1)); // Conserta a data
            filme.setEmCartaz(false); // Tira o filme de cartaz
            compra.comprarBilhete(carlos, sala, sessao, 0, 1);
        } catch (VendasException e) {
            System.out.println("Capturado: " + e.getMessage());
        }
    }
}
