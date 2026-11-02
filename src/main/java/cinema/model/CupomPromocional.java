package cinema.model;

/**
 * Cupons promocionais. O desconto é guardado como porcentagem (ex.: 10.0 = 10%).
 */
public enum CupomPromocional {
    CINE10("CINE10", 10.0),
    CINE20("CINE20", 20.0),
    CINE30("CINE30", 30.0);

    private final String nome;
    private final double desconto; // em porcentagem

    CupomPromocional(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
    }

    public String getNome() {
        return nome;
    }

    /** Desconto em porcentagem (ex.: 10.0). */
    public double getDesconto() {
        return desconto;
    }

    /** Retorna o valor já com o desconto aplicado. */
    public double aplicarDesconto(double valor) {
        return valor - (valor * desconto / 100.0);
    }

    @Override
    public String toString() {
        return String.format("%s (%.0f%% de desconto)", nome, desconto);
    }
}
