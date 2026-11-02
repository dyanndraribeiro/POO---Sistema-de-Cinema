package cinema.model;

/**
 * Produtos de balcão do cinema.
 * Cada produto tem nome (String) e valor (double).
 */
public enum Produto {
    PIPOCA_PEQUENA("Pipoca Pequena", 15.00),
    PIPOCA_GRANDE("Pipoca Grande", 25.00),
    REFRIGERANTE("Refrigerante 500ml", 10.00),
    SUCO("Suco Natural", 12.00),
    CHOCOLATE("Barra de Chocolate", 8.00),
    COMBO_CASAL("Combo Casal (pipoca grande + 2 refrigerantes)", 42.00);

    private final String nome;
    private final double valor;

    Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", nome, valor);
    }
}
