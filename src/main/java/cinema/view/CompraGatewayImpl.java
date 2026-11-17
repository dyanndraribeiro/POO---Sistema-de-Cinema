package cinema.view;

import cinema.controller.CompraController;
import cinema.model.CupomPromocional;
import cinema.model.Sala;
import cinema.model.Sessao;
import cinema.model.Usuario;

import java.util.ArrayList;
import java.util.List;


 
public class CompraGatewayImpl implements CompraGateway {

    private final CompraController controller;
    private final Usuario usuario;         
    private final List<Sala> salas;

    public CompraGatewayImpl(CompraController controller, Usuario usuario, List<Sala> salas) {
        this.controller = controller;
        this.usuario = usuario;
        this.salas = salas;
    }

    private List<Sessao> visiveis(int sala) {
        List<Sessao> lista = new ArrayList<>();
        for (Sessao s : salas.get(sala).getSessoes())
            if (s != null) lista.add(s);
        return lista;
    }

    @Override
    public String[] salas() {
        String[] nomes = new String[salas.size()];
        for (int i = 0; i < nomes.length; i++)
            nomes[i] = salas.get(i).getNome();
        return nomes;
    }

    @Override
    public String[] sessoes(int sala) {
        List<Sessao> lista = visiveis(sala);
        String[] nomes = new String[lista.size()];
        for (int i = 0; i < nomes.length; i++) {
            Sessao s = lista.get(i);
            nomes[i] = s.getHorario() + " | " + s.getFilme().getNome();
        }
        return nomes;
    }

    @Override
    public String[] cupons() {
        CupomPromocional[] todos = CupomPromocional.values();
        String[] nomes = new String[todos.length];
        for (int i = 0; i < nomes.length; i++) nomes[i] = todos[i].toString();   
        return nomes;
    }

    @Override
    public boolean cadeiraOcupada(int sala, int sessao, int linha, int coluna) {
        return !visiveis(sala).get(sessao).cadeiraLivre(linha, coluna);
    }

    @Override
    public String comprarBilhete(int sala, int sessao, int linha, int coluna, int cupom) {
        CupomPromocional c = cupom < 0 ? null : CupomPromocional.values()[cupom];
        return controller.realizarCompraBilhete(
                usuario, salas.get(sala), visiveis(sala).get(sessao), linha, coluna, c);
    }
        @Override
    public String nomeUsuario() {
        return usuario.getUser();
    }
}
