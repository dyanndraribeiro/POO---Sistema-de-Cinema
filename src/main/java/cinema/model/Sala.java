package cinema.model;

public class Sala {

    public static final int QTD_HORARIOS = 7; // grade de horário de tamanho fixo

    private String nome;
    private TipoSala tipo = TipoSala.COMUM;
    private Sessao[] sessoes; // posições sem sessão ficam null

    public Sala() {
        this.sessoes = new Sessao[QTD_HORARIOS];
    }

    public Sala(String nome) {
        this.nome = nome;
        this.sessoes = new Sessao[QTD_HORARIOS];
    }

    public Sala(String nome, TipoSala tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.sessoes = new Sessao[QTD_HORARIOS];
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao[] sessoes) {
        this.sessoes = sessoes;
    }

    /**
     * Define a sessão em uma posição específica da grade de horário.
     */
    public void definirSessao(int indiceHorario, Sessao sessao) {
        sessoes[indiceHorario] = sessao;
    }
}
