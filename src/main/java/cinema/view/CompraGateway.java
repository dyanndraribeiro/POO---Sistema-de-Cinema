package cinema.view;

/**
 * O que a View precisa para funcionar. Tudo por índice (sala, sessão, cupom), assim as telas
 * não conhecem as classes do Model. Quem traduz para os objetos reais é a CompraGatewayImpl.
 */
public interface CompraGateway {

    String[] salas();

    /** Só as sessões que não são nulas, ex.: "08:00 - 10:00 | Gato de botas 2". */
    String[] sessoes(int sala);

    /** Nomes dos cupons do enum CupomPromocional. */
    String[] cupons();

    /** linha 0 = A ... 9 = J; coluna 0..14. */
    boolean cadeiraOcupada(int sala, int sessao, int linha, int coluna);

    /** cupom = -1 significa sem cupom. Devolve a mensagem pronta do CompraController. */
    String comprarBilhete(int sala, int sessao, int linha, int coluna, int cupom);
}
