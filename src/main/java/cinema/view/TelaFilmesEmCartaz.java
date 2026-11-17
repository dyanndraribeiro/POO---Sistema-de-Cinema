package cinema.view;

import cinema.controller.FilmeController;
import cinema.model.Filme;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class TelaFilmesEmCartaz extends JFrame {

    private FilmeController controller;
    private DefaultListModel<Filme> modeloLista;
    private JList<Filme> listaFilmes;
    private JTextArea areaDetalhes;
    private JTextField campoBusca;

    public TelaFilmesEmCartaz() {
        this(new FilmeController());
    }

    public TelaFilmesEmCartaz(FilmeController controller) {
        this.controller = controller;
        montarTela();
        carregarFilmes(controller.listarFilmesEmCartaz());
    }

    private void montarTela() {
        setTitle("Filmes em cartaz");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        campoBusca = new JTextField();
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoAtualizar = new JButton("Atualizar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoBuscar);
        painelBotoes.add(botaoAtualizar);

        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        painelTopo.setBorder(new EmptyBorder(10, 10, 0, 10));
        painelTopo.add(new JLabel("Filme:"), BorderLayout.WEST);
        painelTopo.add(campoBusca, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.EAST);

        modeloLista = new DefaultListModel<>();
        listaFilmes = new JList<>(modeloLista);
        listaFilmes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setLineWrap(true);
        areaDetalhes.setWrapStyleWord(true);
        areaDetalhes.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel painelCentro = new JPanel(new GridLayout(1, 2, 10, 0));
        painelCentro.setBorder(new EmptyBorder(0, 10, 10, 10));
        painelCentro.add(new JScrollPane(listaFilmes));
        painelCentro.add(new JScrollPane(areaDetalhes));

        add(painelTopo, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);

        botaoBuscar.addActionListener(e -> buscar());
        campoBusca.addActionListener(e -> buscar());
        botaoAtualizar.addActionListener(e -> atualizar());
        listaFilmes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalhes();
            }
        });
    }

    private void buscar() {
        carregarFilmes(controller.buscarEmCartaz(campoBusca.getText()));
    }

    private void atualizar() {
        controller.recarregar();
        campoBusca.setText("");
        carregarFilmes(controller.listarFilmesEmCartaz());
    }

    private void carregarFilmes(List<Filme> filmes) {
        modeloLista.clear();
        for (Filme filme : filmes) {
            modeloLista.addElement(filme);
        }
        if (filmes.isEmpty()) {
            areaDetalhes.setText("Nenhum filme em cartaz encontrado.");
        } else {
            areaDetalhes.setText("Selecione um filme para ver os detalhes.");
        }
    }

    private void mostrarDetalhes() {
        Filme filme = listaFilmes.getSelectedValue();
        if (filme == null) {
            return;
        }
        StringBuilder texto = new StringBuilder();
        texto.append(filme.getNome()).append("\n\n");
        texto.append("Duração: ").append(filme.getDuracao()).append(" min\n");
        texto.append("Valor: R$ ").append(String.format("%.2f", filme.getValor())).append("\n");
        if (filme.getQuantidadeCriticos() == 0) {
            texto.append("Nota da crítica: sem avaliações\n");
        } else {
            texto.append("Nota da crítica: ").append(String.format("%.1f", filme.getNota()))
                    .append(" (").append(filme.getQuantidadeCriticos()).append(" críticos)\n");
        }
        texto.append("\nSinopse:\n").append(filme.getSinopse()).append("\n");
        if (filme.getTotalCriticas() > 0) {
            texto.append("\nCríticas:\n");
            for (int i = 0; i < filme.getTotalCriticas(); i++) {
                texto.append("- ").append(filme.getCritica()[i]).append("\n");
            }
        }
        areaDetalhes.setText(texto.toString());
        areaDetalhes.setCaretPosition(0);
    }
}
