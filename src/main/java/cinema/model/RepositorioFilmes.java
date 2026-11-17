package cinema.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFilmes {

    private String caminhoArquivo;
    private List<Filme> filmes;

    public RepositorioFilmes() {
        this("filmes.txt");
    }

    public RepositorioFilmes(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.filmes = new ArrayList<>();
        carregar();
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public void carregar() {
        filmes.clear();
        Path caminho = Paths.get(caminhoArquivo);
        if (!Files.exists(caminho)) {
            return;
        }
        try (BufferedReader leitor = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] campos = linha.split("\\|", -1);
                if (campos.length < 6) {
                    continue;
                }
                Filme filme = new Filme(campos[0], Integer.parseInt(campos[1]), campos[2],
                        Double.parseDouble(campos[3]));
                filme.setNota(Double.parseDouble(campos[4]));
                filme.setQuantidadeCriticos(Integer.parseInt(campos[5]));
                if (campos.length >= 7) {
                    filme.setEmCartaz(Boolean.parseBoolean(campos[6]));
                }
                filmes.add(filme);
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalStateException("Erro ao carregar os filmes do arquivo: " + caminhoArquivo, e);
        }
    }

    public void salvar() {
        try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            for (Filme filme : filmes) {
                escritor.write(limpar(filme.getNome()) + "|" + filme.getDuracao() + "|"
                        + limpar(filme.getSinopse()) + "|" + filme.getValor() + "|"
                        + filme.getNota() + "|" + filme.getQuantidadeCriticos() + "|"
                        + filme.isEmCartaz());
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao salvar os filmes no arquivo: " + caminhoArquivo, e);
        }
    }

    public boolean incluir(Filme filme) {
        if (buscarPorNome(filme.getNome()) != null) {
            return false;
        }
        filmes.add(filme);
        salvar();
        return true;
    }

    public boolean excluir(String nome) {
        Filme filme = buscarPorNome(nome);
        if (filme == null) {
            return false;
        }
        filmes.remove(filme);
        salvar();
        return true;
    }

    public boolean alterar(String nomeAtual, Filme novoFilme) {
        Filme filme = buscarPorNome(nomeAtual);
        if (filme == null) {
            return false;
        }
        Filme existente = buscarPorNome(novoFilme.getNome());
        if (existente != null && existente != filme) {
            return false;
        }
        filme.setNome(novoFilme.getNome());
        filme.setDuracao(novoFilme.getDuracao());
        filme.setSinopse(novoFilme.getSinopse());
        filme.setValor(novoFilme.getValor());
        filme.setEmCartaz(novoFilme.isEmCartaz());
        salvar();
        return true;
    }

    public Filme buscarPorNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome() != null && filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null;
    }

    public List<Filme> listar() {
        return new ArrayList<>(filmes);
    }

    private String limpar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("|", "/").replace("\r", " ").replace("\n", " ");
    }
}
