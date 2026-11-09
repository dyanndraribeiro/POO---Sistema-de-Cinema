
package cinema.model;

import cinema.model.Administrador;
import cinema.model.Usuario;
import cinema.model.Filme;

public class Main {

    public static void main(String[] args) {

        Administrador admin = new Administrador(
            "Carlos", 35, "admin@cinema.com", 5000.0, 1
        );

        System.out.println("=== ADMINISTRADOR ===");
        System.out.println("Nome: " + admin.getNome());
        System.out.println("Salario: " + admin.getSalario());
        System.out.println("ID: " + admin.getId());

        Usuario usuario = new Usuario(
            "joao", "12345678900", "123",
            20, "Masculino", "joao@email.com",
            "Joao Silva", "123456789", "123"
        );

        System.out.println("\n=== USUARIOS ===");

        System.out.println("Adicionar: " +
            admin.adicionarUsuario(usuario));

        usuario.setEmail("novo@email.com");

        System.out.println("Alterar: " +
            admin.alterarUsuario(usuario));

        System.out.println("Excluir: " +
            admin.excluirUsuario("12345678900"));

        System.out.println("\n=== FILMES ===");

        Filme filme = new Filme(
            "Gato de Botas 2", 102, "Aventura", 20.0
        );

        System.out.println("Incluir: " +
            admin.incluirFilme(filme));

        Filme novoFilme = new Filme(
            "Gato de Botas 3", 110, "Nova aventura", 25.0
        );

        System.out.println("Alterar: " +
            admin.alterarFilme("Gato de Botas 2", novoFilme));

        System.out.println("Filmes cadastrados: " +
            admin.getFilmes());

        System.out.println("Excluir: " +
            admin.excluirFilme("Gato de Botas 3"));

        System.out.println("\n=== FIM DA DEMONSTRACAO ===");
    }
}
