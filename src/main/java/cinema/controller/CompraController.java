package cinema.controller;

import cinema.model.Bilhete;
import cinema.model.Compra;
import cinema.model.CupomPromocional;
import cinema.model.Sala;
import cinema.model.Sessao;
import cinema.model.Usuario;
import cinema.model.VendasException;

public class CompraController {

    private Compra compra;

    public CompraController() {
        this.compra = new Compra();
    }

    public String realizarCompraBilhete(Usuario usuario, Sala sala, Sessao sessao, int linha, int coluna, CupomPromocional cupom) {
        try {
            Bilhete bilhete;
            
            // Verifica se a tela enviou um cupom ou não para usar a sobrecarga correta
            if (cupom != null) {
                bilhete = compra.comprarBilhete(usuario, sala, sessao, linha, coluna, cupom);
            } else {
                bilhete = compra.comprarBilhete(usuario, sala, sessao, linha, coluna);
            }

            if (bilhete == null) {
                return "Erro: Cadeira inválida.";
            }

            return String.format("Compra realizada com sucesso!\nFilme: %s | Cadeira: %s | Valor: R$ %.2f",
                    bilhete.getFilme().getNome(),
                    bilhete.getCadeiraComprada(),
                    bilhete.getValor());

        } catch (VendasException e) {
            // Captura os 3 erros (fora de cartaz, horário passado, poltrona ocupada)
            // e devolve a mensagem limpa para a tela exibir num pop-up/alerta
            return e.getMessage();
        }
    }

    public String adicionarProduto(String nomeProduto, CupomPromocional cupom) {
        if (cupom != null) {
            compra.adicionarItem(nomeProduto, cupom);
            return "Produto '" + nomeProduto + "' adicionado com cupom " + cupom.getNome() + "!";
        } else {
            compra.adicionarItem(nomeProduto);
            return "Produto '" + nomeProduto + "' adicionado à compra!";
        }
    }

    public Compra getCompra() {
        return compra;
    }
}