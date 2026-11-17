package cinema;

import cinema.controller.AdministracaoFilmesController;
import cinema.model.Administrador;
import cinema.model.RepositorioUsuarios;
import cinema.view.TelaAdministracaoFilmes;
import cinema.controller.CompraController;
import cinema.controller.FilmeController;
import cinema.model.TipoSala;
import cinema.model.Critico;
import cinema.model.Estudante;
import cinema.model.Filme;
import cinema.model.RepositorioFilmes;
import cinema.model.Sala;
import cinema.model.Sessao;
import cinema.model.Usuario;
import cinema.view.CompraGateway;
import cinema.view.CompraGatewayImpl;
import cinema.view.FilmesGateway;
import cinema.view.FilmesGatewayImpl;
import cinema.view.LoginGatewayImpl;
import cinema.view.TelaCompra;
import cinema.view.TelaFilmesCartaz;
import cinema.view.TelaLogin;
import cinema.view.TelaPrincipal;

import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Persistência: os repositórios são criados uma única vez e compartilhados
            RepositorioFilmes repositorioFilmes = new RepositorioFilmes();
            FilmeController filmeController = new FilmeController(repositorioFilmes);

            // Criação dos Filmes de exemplo (ficam salvos em filmes.txt)
            Filme filme1 = garantirFilme(repositorioFilmes, new Filme("Gato de Botas 2", 102, "O Gato de Botas descobre que sua paixão pela aventura cobrou seu preço.", 30.0));
            Filme filme2 = garantirFilme(repositorioFilmes, new Filme("Jurassic Park", 127, "Dinossauros clonados fogem do controle em um parque temático.", 28.0));

            Filme filmeAntigo = new Filme("Filme Antigo", 95, "Este filme já saiu de exibição.", 20.0);
            filmeAntigo.setEmCartaz(false); // Testa o erro de filme fora de cartaz
            Filme filmeForaCartaz = garantirFilme(repositorioFilmes, filmeAntigo);

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
            Sala sala1 = new Sala("Sala 1 - 3D", TipoSala.SALA_3D);
            sala1.definirSessao(0, sessao1);
            sala1.definirSessao(1, sessaoExpirada);

            Sala sala2 = new Sala("Sala 2 - XD", TipoSala.XD);
            sala2.definirSessao(0, sessao2);
            sala2.definirSessao(1, sessaoForaCartaz);

            List<Sala> salas = new ArrayList<>();
            salas.add(sala1);
            salas.add(sala2);

            // Usuários disponíveis no login (um de cada tipo)
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario(
                    "Ana Souza", "11111111111", "1234",
                    30, "Feminino", "ana@email.com",
                    "Nubank", "1111", "111"));
            usuarios.add(new Estudante(
                    "Carlos Silva", "12345678900", "1234",
                    21, "Masculino", "carlos@email.com",
                    "Nubank", "1234", "123"));
            usuarios.add(new Critico(
                    "Marina Lima", "22222222222", "1234",
                    35, "Feminino", "marina@email.com",
                    "Inter", "2222", "222", "Jornal do Cinema"));

            // Inicialização do MVC
            CompraController compraController = new CompraController();
            FilmesGateway filmesGateway = new FilmesGatewayImpl(salas, filmeController);

            // Administração de filmes: o Administrador usa o MESMO repositório de filmes do sistema
            Administrador administrador = new Administrador(
                    "Administrador", 40, "admin@cinema.com", 5000.0, 1,
                    new RepositorioUsuarios(), repositorioFilmes);
            Runnable abrirAdministracao = () -> {
                TelaAdministracaoFilmes tela = new TelaAdministracaoFilmes();
                new AdministracaoFilmesController(administrador, tela);
                tela.setVisible(true);
            };
            // Login: depois de escolher o usuário e a senha, abre a tela principal dele
            LoginGatewayImpl login = new LoginGatewayImpl(usuarios);
            new TelaLogin(login, indice -> {
                Usuario usuarioLogado = login.obter(indice);
                CompraGateway gateway = new CompraGatewayImpl(compraController, usuarioLogado, salas);

                Runnable abrirCompra = () -> new TelaCompra(gateway).setVisible(true);
                Runnable abrirFilmes = () -> new TelaFilmesCartaz(filmesGateway, abrirCompra).setVisible(true);

               new TelaPrincipal(gateway, abrirFilmes, abrirAdministracao).setVisible(true);
            }).setVisible(true);
        });
    }

    private static Filme garantirFilme(RepositorioFilmes repositorio, Filme filme) {
        repositorio.incluir(filme);
        return repositorio.buscarPorNome(filme.getNome());
    }
}