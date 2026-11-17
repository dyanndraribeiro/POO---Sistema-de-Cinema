package cinema.view;

import cinema.model.Usuario;

import java.util.List;

public class LoginGatewayImpl implements LoginGateway {

    private final List<Usuario> usuarios;

    public LoginGatewayImpl(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario obter(int indice) {
        return usuarios.get(indice);
    }

    @Override
    public String[] usuarios() {
        String[] nomes = new String[usuarios.size()];
        for (int i = 0; i < nomes.length; i++) {
            Usuario u = usuarios.get(i);
            nomes[i] = u.getUser() + " (" + u.getClass().getSimpleName() + ")";
        }
        return nomes;
    }

    @Override
    public boolean autenticar(int usuario, String senha) {
        String correta = usuarios.get(usuario).getSenha();
        return correta != null && correta.equals(senha);
    }
}
