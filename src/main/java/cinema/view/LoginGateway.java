package cinema.view;

public interface LoginGateway {

    String[] usuarios();

    boolean autenticar(int usuario, String senha);
}
