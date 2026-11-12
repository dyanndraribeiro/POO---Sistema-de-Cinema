package cinema.model;

import java.time.LocalDateTime;

public class Sessao {

    public static final int LINHAS = 10;
    public static final int COLUNAS = 15;

    private Filme filme;
    private String horario; // ex: "08:00 - 10:00"
    private boolean[][] cadeiras; // true = ocupada, false = livre
    private LocalDateTime dataHoraSessao;

    public Sessao() {
        this.cadeiras = new boolean[LINHAS][COLUNAS];
    }

    public Sessao(Filme filme, String horario) {
        this.filme = filme;
        this.horario = horario;
        this.cadeiras = new boolean[LINHAS][COLUNAS];
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean[][] getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(boolean[][] cadeiras) {
        this.cadeiras = cadeiras;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    /**
     * Verifica se uma cadeira específica está livre.
     */
    public boolean cadeiraLivre(int linha, int coluna) {
        return !cadeiras[linha][coluna];
    }

    /**
     * Marca uma cadeira como ocupada.
     */
    public void ocuparCadeira(int linha, int coluna) {
        cadeiras[linha][coluna] = true;
    }

    // Método auxiliar para verificar se a sessão já expirou
    public boolean isHorarioPassado() {
        if (this.dataHoraSessao == null) return false;
        return LocalDateTime.now().isAfter(this.dataHoraSessao);
    }
}
