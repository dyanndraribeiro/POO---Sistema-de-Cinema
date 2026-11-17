package cinema.view;

import cinema.controller.CompraController;
import cinema.model.Sala;
import cinema.model.Usuario;

import javax.swing.SwingUtilities;
import java.util.List;

public class IniciarInterface {

    public static void abrir(CompraController controller, List<Usuario> usuarios, List<Sala> salas) {
        SwingUtilities.invokeLater(() -> mostrarLogin(controller, usuarios, salas));
    }

    private static void mostrarLogin(CompraController controller, List<Usuario> usuarios, List<Sala> salas) {
        LoginGatewayImpl login = new LoginGatewayImpl(usuarios);
        new TelaLogin(login, indice -> {
            Usuario usuario = login.obter(indice);
            CompraGateway compra = new CompraGatewayImpl(controller, usuario, salas);
            FilmesGateway filmes = new FilmesGatewayImpl(salas);
            new TelaPrincipal(usuario.getUser(), compra, filmes,
                    () -> mostrarLogin(controller, usuarios, salas)).setVisible(true);
        }).setVisible(true);
    }
}
