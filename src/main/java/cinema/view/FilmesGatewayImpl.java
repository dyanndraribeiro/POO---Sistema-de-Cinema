package cinema.view;

import cinema.model.Filme;
import cinema.model.Sala;
import cinema.model.Sessao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilmesGatewayImpl implements FilmesGateway {

    private final List<Sala> salas;

    public FilmesGatewayImpl(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public String[][] filmes() {
        Map<String, Filme> filmes = new LinkedHashMap<>();
        Map<String, List<String>> horarios = new LinkedHashMap<>();

        for (Sala sala : salas) {
            for (Sessao sessao : sala.getSessoes()) {
                if (sessao == null || sessao.getFilme() == null) continue;
                String nome = sessao.getFilme().getNome();
                filmes.putIfAbsent(nome, sessao.getFilme());
                horarios.computeIfAbsent(nome, k -> new ArrayList<>())
                        .add(sala.getNome() + " - " + sessao.getHorario());
            }
        }

        List<String[]> linhas = new ArrayList<>();
        for (Map.Entry<String, Filme> e : filmes.entrySet()) {
            Filme f = e.getValue();
            linhas.add(new String[]{
                    e.getKey(),
                    String.valueOf(f.getDuracao()),
                    "R$ " + f.getValor(),
                    String.join("\n", horarios.get(e.getKey())),
                    String.valueOf(f.getSinopse())
            });
        }
        return linhas.toArray(new String[0][]);
    }
}
