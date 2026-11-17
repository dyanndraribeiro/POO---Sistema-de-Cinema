package cinema.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** View: mapa de cadeiras clicável + cupom. Sem regra de negócio: o valor (inclusive o
 *  preço de estudante e o cupom) vem calculado pelo Model e aparece na mensagem da compra. */
public class TelaCompra extends JFrame {

    private static final int LINHAS = 10, COLUNAS = 15;
    private static final Color LIVRE = Color.WHITE, OCUPADA = new Color(220, 80, 80),
                               ESCOLHIDA = new Color(80, 180, 100);

    private final CompraGateway ctrl;
    private final JComboBox<String> cbSala = new JComboBox<>();
    private final JComboBox<String> cbSessao = new JComboBox<>();
    private final JComboBox<String> cbCupom = new JComboBox<>();
    private final JToggleButton[][] cadeiras = new JToggleButton[LINHAS][COLUNAS];
    private final JLabel lblQtd = new JLabel("Cadeiras escolhidas: 0");
    private boolean carregando = false;

    public TelaCompra(CompraGateway ctrl) {
        super("Comprar bilhete");
        this.ctrl = ctrl;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(criarTopo(), BorderLayout.NORTH);
        add(criarMapa(), BorderLayout.CENTER);
        add(criarRodape(), BorderLayout.SOUTH);

        for (String s : ctrl.salas()) cbSala.addItem(s);
        cbCupom.addItem("Sem cupom");
        for (String c : ctrl.cupons()) cbCupom.addItem(c);

        cbSala.addActionListener(e -> carregarSessoes());
        cbSessao.addActionListener(e -> recarregarMapa());

        carregarSessoes();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel criarTopo() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.add(new JLabel("Sala:"));
        p.add(cbSala);
        p.add(new JLabel("Filme/sessão:"));
        p.add(cbSessao);
        return p;
    }

    private JPanel criarMapa() {
        JPanel grade = new JPanel(new GridLayout(LINHAS, COLUNAS + 1, 3, 3));
        for (int l = LINHAS - 1; l >= 0; l--) {          // J em cima, A embaixo (perto da tela)
            grade.add(new JLabel(String.valueOf((char) ('A' + l)), SwingConstants.CENTER));
            for (int c = 0; c < COLUNAS; c++) {
                JToggleButton b = new JToggleButton(String.valueOf(c + 1));
                b.setMargin(new Insets(2, 2, 2, 2));
                b.setOpaque(true);
                b.setFocusPainted(false);
                b.addItemListener(e -> { pintar(b); atualizarQtd(); });
                cadeiras[l][c] = b;
                grade.add(b);
            }
        }
        JLabel tela = new JLabel("TELA", SwingConstants.CENTER);
        tela.setOpaque(true);
        tela.setBackground(Color.DARK_GRAY);
        tela.setForeground(Color.WHITE);

        JPanel p = new JPanel(new BorderLayout(0, 6));
        p.add(grade, BorderLayout.CENTER);
        p.add(tela, BorderLayout.SOUTH);
        return p;
    }

    private JPanel criarRodape() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        p.add(lblQtd);
        p.add(new JLabel("Cupom:"));
        p.add(cbCupom);
        JButton confirmar = new JButton("Confirmar compra");
        confirmar.addActionListener(e -> confirmar());
        p.add(confirmar);
        return p;
    }

    private void carregarSessoes() {
        carregando = true;
        cbSessao.removeAllItems();
        if (cbSala.getSelectedIndex() >= 0)
            for (String s : ctrl.sessoes(cbSala.getSelectedIndex())) cbSessao.addItem(s);
        carregando = false;
        recarregarMapa();
    }

    private void recarregarMapa() {
        if (carregando) return;
        carregando = true;
        int sala = cbSala.getSelectedIndex(), sessao = cbSessao.getSelectedIndex();
        boolean valido = sala >= 0 && sessao >= 0;
        for (int l = 0; l < LINHAS; l++)
            for (int c = 0; c < COLUNAS; c++) {
                JToggleButton b = cadeiras[l][c];
                b.setSelected(false);
                boolean ocupada = valido && ctrl.cadeiraOcupada(sala, sessao, l, c);
                b.setEnabled(valido && !ocupada);
                b.setBackground(ocupada ? OCUPADA : LIVRE);
            }
        carregando = false;
        atualizarQtd();
    }

    private void pintar(JToggleButton b) {
        if (b.isEnabled()) b.setBackground(b.isSelected() ? ESCOLHIDA : LIVRE);
    }

    private List<int[]> selecionadas() {
        List<int[]> lista = new ArrayList<>();
        for (int l = 0; l < LINHAS; l++)
            for (int c = 0; c < COLUNAS; c++)
                if (cadeiras[l][c].isSelected()) lista.add(new int[]{l, c});
        return lista;
    }

    private void atualizarQtd() {
        if (!carregando) lblQtd.setText("Cadeiras escolhidas: " + selecionadas().size());
    }

    private void confirmar() {
        List<int[]> escolhidas = selecionadas();
        if (escolhidas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escolha pelo menos uma cadeira.");
            return;
        }
        int sala = cbSala.getSelectedIndex(), sessao = cbSessao.getSelectedIndex();
        int cupom = cbCupom.getSelectedIndex() - 1;       // -1 = sem cupom
        StringBuilder mensagens = new StringBuilder();
        for (int[] p : escolhidas)
            mensagens.append(ctrl.comprarBilhete(sala, sessao, p[0], p[1], cupom)).append("\n\n");
        JOptionPane.showMessageDialog(this, mensagens.toString().trim(), "Resultado da compra",
                JOptionPane.INFORMATION_MESSAGE);
        recarregarMapa();
    }
}
