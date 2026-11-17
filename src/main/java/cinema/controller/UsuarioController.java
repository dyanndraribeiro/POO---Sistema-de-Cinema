package cinema.controller;

import cinema.model.Critico;
import cinema.model.Estudante;
import cinema.model.RepositorioUsuarios;
import cinema.model.Usuario;

public class UsuarioController {

    private RepositorioUsuarios repositorio;

    public UsuarioController() {
        this(new RepositorioUsuarios());
    }

    public UsuarioController(RepositorioUsuarios repositorio) {
        this.repositorio = repositorio;
    }

    public RepositorioUsuarios getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepositorioUsuarios repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarUsuario(String tipo, String user, String cpf, String senha, String idadeTexto,
                                 String sexo, String email, String nomeCartao, String numeroCartao,
                                 String codigoVerificadorCartao, String origem) {
        if (vazio(user) || vazio(cpf) || vazio(senha) || vazio(email)) {
            throw new IllegalArgumentException("Preencha usuário, CPF, senha e e-mail.");
        }
        if (vazio(idadeTexto)) {
            throw new IllegalArgumentException("Informe a idade.");
        }
        int idade;
        try {
            idade = Integer.parseInt(idadeTexto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A idade deve ser um número inteiro.");
        }
        if (idade <= 0 || idade > 120) {
            throw new IllegalArgumentException("Idade inválida.");
        }

        Usuario usuario;
        if ("CRITICO".equalsIgnoreCase(tipo)) {
            if (vazio(origem)) {
                throw new IllegalArgumentException("Informe o órgão de origem do crítico.");
            }
            usuario = new Critico(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao,
                    codigoVerificadorCartao, origem);
        } else if ("ESTUDANTE".equalsIgnoreCase(tipo)) {
            usuario = new Estudante(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao,
                    codigoVerificadorCartao);
        } else {
            usuario = new Usuario(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao,
                    codigoVerificadorCartao);
        }

        if (!repositorio.incluir(usuario)) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com esse CPF.");
        }
    }

    public Usuario autenticar(String cpf, String senha) {
        Usuario usuario = repositorio.buscarPorCpf(cpf);
        if (usuario != null && usuario.getSenha() != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf) {
        return repositorio.buscarPorCpf(cpf);
    }

    public void recarregar() {
        repositorio.carregar();
    }

    private boolean vazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
