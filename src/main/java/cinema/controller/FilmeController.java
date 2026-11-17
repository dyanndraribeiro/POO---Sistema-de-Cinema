package cinema.controller;

import cinema.model.Filme;
import cinema.model.RepositorioFilmes;
import java.util.ArrayList;
import java.util.List;

public class FilmeController {

    private RepositorioFilmes repositorio;

    public FilmeController() {
        this(new RepositorioFilmes());
    }

    public FilmeController(RepositorioFilmes repositorio) {
        this.repositorio = repositorio;
    }

    public RepositorioFilmes getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepositorioFilmes repositorio) {
        this.repositorio = repositorio;
    }

    public List<Filme> listarFilmesEmCartaz() {
        List<Filme> emCartaz = new ArrayList<>();
        for (Filme filme : repositorio.listar()) {
            if (filme.isEmCartaz()) {
                emCartaz.add(filme);
            }
        }
        return emCartaz;
    }

    public List<Filme> buscarEmCartaz(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarFilmesEmCartaz();
        }
        String busca = termo.trim().toLowerCase();
        List<Filme> resultado = new ArrayList<>();
        for (Filme filme : listarFilmesEmCartaz()) {
            if (filme.getNome() != null && filme.getNome().toLowerCase().contains(busca)) {
                resultado.add(filme);
            }
        }
        return resultado;
    }

    public Filme buscarPorNome(String nome) {
        return repositorio.buscarPorNome(nome);
    }

    public void recarregar() {
        repositorio.carregar();
    }
}
