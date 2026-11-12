package cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Compra {

    private List<String> itens; // pipoca, refrigerante, chocolate, etc.
    private List<Bilhete> bilhetes;

    public Compra() {
        this.itens = new ArrayList<>();
        this.bilhetes = new ArrayList<>();
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(List<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public void adicionarItem(String item) {
        itens.add(item);
    }

    
    /** 
    *Compra um único bilhete, escolhendo uma cadeira livre dentro da sessão.*/
    /** TODO: implementar lógica de escolha de cadeira única.*/ 
     
       public Bilhete comprarBilhete(Usuario usuario, Sala sala, Sessao sessao, int linha, int coluna) throws VendasException{
        // cadeira inexistente
        if (linha < 0 || linha >= Sessao.LINHAS || coluna < 0 || coluna >= Sessao.COLUNAS) {
            return null;
        }

        Filme filme = sessao.getFilme();

        if(!filme.isEmCartaz()){
            throw new VendasException("Erro: o filme não está mais em cartaz");
        }

        if (sessao.isHorarioPassado()) {
            throw new VendasException("Erro: O horário do filme já passou.");
        }

        if (!sessao.cadeiraLivre(linha, coluna)) {
            throw new VendasException("Erro: A poltrona já foi selecionada.");
        }

        // polimorfismo: Usuario paga inteiro, Estudante paga meia
        double valor = usuario.calcularValorBilhete(filme.getValor());

        sessao.ocuparCadeira(linha, coluna);
        String cadeira = (char) ('A' + linha) + String.valueOf(coluna + 1); // ex: "A1", "J15"

        Bilhete bilhete = new Bilhete(usuario.getUser(), usuario.getCpf(), sala, sessao, filme, valor, cadeira);
        bilhetes.add(bilhete);
        return bilhete;
    }

   
     /* Compra vários bilhetes de uma vez, sugerindo cadeiras juntas para o grupo*/
     /* TODO: implementar sugestão de cadeiras adjacentes.*/
   
    public List<Bilhete> comprarBilhetes(Usuario usuario, Sala sala, Sessao sessao, int quantidade) {
        return new ArrayList<>();
    }

    // Sobrecargas: 
    public Bilhete comprarBilhete(Usuario usuario, Sala sala, Sessao sessao, int linha, int coluna, CupomPromocional cupom) throws VendasException{
        Bilhete bilhete = comprarBilhete(usuario, sala, sessao, linha, coluna); 
        if (bilhete != null && cupom != null) {
            bilhete.setValor(cupom.aplicarDesconto(bilhete.getValor())); // Aplica o desconto proporcionado
        }
        return bilhete;
    }

    public void adicionarItem(String item, CupomPromocional cupom) {
        if (cupom == null) {
            this.adicionarItem(item);
            return;
        }

        for (Produto produto : Produto.values()) {
            if (produto.getNome().equalsIgnoreCase(item) || produto.name().equalsIgnoreCase(item)) {
                double valorDescontado = cupom.aplicarDesconto(produto.getValor());
                this.itens.add(produto.getNome() + " - R$ " + String.format("%.2f", valorDescontado));
                return;
            }
        }
        this.itens.add(item + " (Cupom " + cupom.getNome() + " associado)");
    }
}
