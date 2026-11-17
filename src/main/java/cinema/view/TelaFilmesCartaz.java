package cinema.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaFilmesCartaz extends JFrame {

    private final String[][] filmes;
    private final JTable tabela;
    private final JTextArea detalhes = new JTextArea(8, 40);

    public TelaFilmesCartaz(FilmesGateway gateway, Runnable abrirCompra) {
        super("Filmes em cartaz");
        this.filmes = gateway.filmes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] colunas = {"Filme", "Duração", "Valor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        for (String[] f : filmes) modelo.addRow(new Object[]{f[0], f[1], f[2]});

        tabela = new JTable(modelo);
        tabela.setRowHeight(26);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) mostrarDetalhes();
        });

        detalhes.setEditable(false);
        detalhes.setLineWrap(true);
        detalhes.setWrapStyleWord(true);
        detalhes.setMargin(new Insets(8, 8, 8, 8));

        JSplitPane divisor = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tabela), new JScrollPane(detalhes));
        divisor.setResizeWeight(0.5);
        add(divisor, BorderLayout.CENTER);

        JButton comprar = new JButton("Comprar ingresso");
        comprar.addActionListener(e -> abrirCompra.run());
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(comprar);
        add(rodape, BorderLayout.SOUTH);

        if (filmes.length > 0) tabela.setRowSelectionInterval(0, 0);
        else detalhes.setText("Nenhum filme em cartaz no momento.");

        setPreferredSize(new Dimension(640, 480));
        pack();
        setLocationRelativeTo(null);
    }

    private void mostrarDetalhes() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;
        String[] f = filmes[linha];
        detalhes.setText(f[0] + "\n\nSinopse:\n" + f[4] + "\n\nSessões:\n" + f[3]);
        detalhes.setCaretPosition(0);
    }
}
