package cinema.view;

import cinema.controller.FilmeController;
import cinema.model.Filme;
import cinema.model.Sala;
import cinema.model.Sessao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilmesGatewayImpl implements FilmesGateway {

    private final List<Sala> salas;
    private final FilmeController filmeController;

    public FilmesGatewayImpl(List<Sala> salas, FilmeController filmeController) {
        this.salas = salas;
        this.filmeController = filmeController;
    }

    @Override
    public String[][] filmes() {
        Map<String, List<String>> horarios = new LinkedHashMap<>();

        for (Sala sala : salas) {
            for (Sessao sessao : sala.getSessoes()) {
                if (sessao == null || sessao.getFilme() == null || sessao.getFilme().getNome() == null) continue;
                String chave = sessao.getFilme().getNome().toLowerCase();
                horarios.computeIfAbsent(chave, k -> new ArrayList<>())
                        .add(sala.getNome() + " - " + sessao.getHorario());
            }
        }

        List<String[]> linhas = new ArrayList<>();
        for (Filme f : filmeController.listarFilmesEmCartaz()) {
            List<String> sessoes = horarios.get(String.valueOf(f.getNome()).toLowerCase());
            String textoSessoes = sessoes == null ? "Nenhuma sessão cadastrada." : String.join("\n", sessoes);
            linhas.add(new String[]{
                    f.getNome(),
                    String.valueOf(f.getDuracao()),
                    "R$ " + f.getValor(),
                    textoSessoes,
                    String.valueOf(f.getSinopse())
            });
        }
        return linhas.toArray(new String[0][]);
    }
}
