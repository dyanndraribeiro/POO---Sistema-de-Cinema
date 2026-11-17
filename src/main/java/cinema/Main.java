package cinema;

import cinema.controller.CompraController;
import cinema.model.Estudante;
import cinema.model.Filme;
import cinema.model.Sala;
import cinema.model.Sessao;
import cinema.model.Usuario;
import cinema.view.CompraGateway;
import cinema.view.CompraGatewayImpl;
import cinema.view.TelaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            // Criação dos Filmes de exemplo 
            Filme filme1 = new Filme("Gato de Botas 2", 102, "O Gato de Botas descobre que sua paixão pela aventura cobrou seu preço.", 30.0);
            Filme filme2 = new Filme("Jurassic Park", 127, "Dinossauros clonados fogem do controle em um parque temático.", 28.0);
            
            Filme filmeForaCartaz = new Filme("Filme Antigo", 95, "Este filme já saiu de exibição.", 20.0);
            filmeForaCartaz.setEmCartaz(false); // Testa o erro de filme fora de cartaz

            // Criação das Sessões 
            Sessao sessao1 = new Sessao(filme1, "14:00 - 16:00");
            sessao1.setDataHoraSessao(LocalDateTime.now().plusHours(3)); 

            Sessao sessao2 = new Sessao(filme2, "19:00 - 21:30");
            sessao2.setDataHoraSessao(LocalDateTime.now().plusDays(1)); 

            Sessao sessaoExpirada = new Sessao(filme1, "08:00 - 10:00");
            sessaoExpirada.setDataHoraSessao(LocalDateTime.now().minusDays(1)); 

            Sessao sessaoForaCartaz = new Sessao(filmeForaCartaz, "16:30 - 18:15");
            sessaoForaCartaz.setDataHoraSessao(LocalDateTime.now().plusHours(5));

            // Configuração das Salas
            Sala sala1 = new Sala("Sala 1 - VIP");
            sala1.definirSessao(0, sessao1);
            sala1.definirSessao(1, sessaoExpirada);

            Sala sala2 = new Sala("Sala 2 - IMAX");
            sala2.definirSessao(0, sessao2);
            sala2.definirSessao(1, sessaoForaCartaz);

            List<Sala> salas = new ArrayList<>();
            salas.add(sala1);
            salas.add(sala2);

            // Usuário comprador
            Usuario usuarioLogado = new Estudante(
                    "Carlos Silva", "12345678900", "senha123",
                    21, "Masculino", "carlos@email.com",
                    "Nubank", "1234", "123", "2026001"
            );

            // Inicialização do MVC 
            CompraController compraController = new CompraController();
            CompraGateway gateway = new CompraGatewayImpl(compraController, usuarioLogado, salas);

            //Ação provisória para o botão da tela de Crystal até ela finalizar
            Runnable abrirFilmes = () -> JOptionPane.showMessageDialog(
                    null,
                    "Tela de filmes em cartaz aguardando integração da Crystal."
            );

            TelaPrincipal telaPrincipal = new TelaPrincipal(gateway, abrirFilmes);
            telaPrincipal.setVisible(true);
        });
    }
}