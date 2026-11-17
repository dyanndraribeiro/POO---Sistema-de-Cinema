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

public class RepositorioUsuarios {

    private String caminhoArquivo;
    private List<Usuario> usuarios;

    public RepositorioUsuarios() {
        this("usuarios.txt");
    }

    public RepositorioUsuarios(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.usuarios = new ArrayList<>();
        carregar();
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void carregar() {
        usuarios.clear();
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
                if (campos.length < 11) {
                    continue;
                }
                int idade = Integer.parseInt(campos[4]);
                if (campos[0].equals("CRITICO")) {
                    usuarios.add(new Critico(campos[1], campos[2], campos[3], idade, campos[5], campos[6],
                            campos[7], campos[8], campos[9], campos[10]));
                } else if (campos[0].equals("ESTUDANTE")) {
                    usuarios.add(new Estudante(campos[1], campos[2], campos[3], idade, campos[5], campos[6],
                            campos[7], campos[8], campos[9]));
                } else {
                    usuarios.add(new Usuario(campos[1], campos[2], campos[3], idade, campos[5], campos[6],
                            campos[7], campos[8], campos[9]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalStateException("Erro ao carregar os usuários do arquivo: " + caminhoArquivo, e);
        }
    }

    public void salvar() {
        try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            for (Usuario usuario : usuarios) {
                String tipo = "USUARIO";
                String origem = "";
                if (usuario instanceof Critico) {
                    tipo = "CRITICO";
                    origem = limpar(((Critico) usuario).getOrigem());
                } else if (usuario instanceof Estudante) {
                    tipo = "ESTUDANTE";
                }
                escritor.write(tipo + "|" + limpar(usuario.getUser()) + "|" + limpar(usuario.getCpf()) + "|"
                        + limpar(usuario.getSenha()) + "|" + usuario.getIdade() + "|"
                        + limpar(usuario.getSexo()) + "|" + limpar(usuario.getEmail()) + "|"
                        + limpar(usuario.getNomeCartao()) + "|" + limpar(usuario.getNumeroCartao()) + "|"
                        + limpar(usuario.getCodigoVerificadorCartao()) + "|" + origem);
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao salvar os usuários no arquivo: " + caminhoArquivo, e);
        }
    }

    public boolean incluir(Usuario usuario) {
        if (buscarPorCpf(usuario.getCpf()) != null) {
            return false;
        }
        usuarios.add(usuario);
        salvar();
        return true;
    }

    public boolean excluir(String cpf) {
        Usuario usuario = buscarPorCpf(cpf);
        if (usuario == null) {
            return false;
        }
        usuarios.remove(usuario);
        salvar();
        return true;
    }

    public boolean alterar(String cpfAtual, Usuario novoUsuario) {
        Usuario antigo = buscarPorCpf(cpfAtual);
        if (antigo == null) {
            return false;
        }
        usuarios.set(usuarios.indexOf(antigo), novoUsuario);
        salvar();
        return true;
    }

    public Usuario buscarPorCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf() != null && usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    private String limpar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("|", "/").replace("\r", " ").replace("\n", " ");
    }
}
