package cinema.view;


public interface CompraGateway {
    String nomeUsuario();

    String[] salas();

    
    String[] sessoes(int sala);

    
    String[] cupons();

   
    boolean cadeiraOcupada(int sala, int sessao, int linha, int coluna);

  
    String comprarBilhete(int sala, int sessao, int linha, int coluna, int cupom);
}
