package cinema.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import cinema.model.Filme;

public class TelaAdministracaoFilmes extends JFrame {

    private JTextField txtNome, txtDuracao, txtDescricao, txtPreco;
    private JCheckBox chkEmCartaz;
    private JList<String> listaFilmes;

    private JButton btnIncluir, btnExcluir, btnAlterar;

    public TelaAdministracaoFilmes() {

        setTitle("Administração de Filmes");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(0, 2, 5, 5));

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Duração:"));
        txtDuracao = new JTextField();
        painel.add(txtDuracao);

        painel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painel.add(txtDescricao);

        painel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        painel.add(txtPreco);

        painel.add(new JLabel("Em cartaz:"));
        chkEmCartaz = new JCheckBox("", true);
        painel.add(chkEmCartaz);

        btnIncluir = new JButton("Incluir");
        btnExcluir = new JButton("Excluir");
        btnAlterar = new JButton("Alterar");

        painel.add(btnIncluir);
        painel.add(btnExcluir);
        painel.add(btnAlterar);

        listaFilmes = new JList<>();
        
        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(listaFilmes), BorderLayout.CENTER);
    }

    public String getNome() {
        return txtNome.getText();
    }

    public String getDuracao() {
        return txtDuracao.getText();
    }

    public String getDescricao() {
        return txtDescricao.getText();
    }

    public String getPreco() {
        return txtPreco.getText();
    }

    public boolean isEmCartaz() {
        return chkEmCartaz.isSelected();
    }

    public String getFilmeSelecionado() {
        return listaFilmes.getSelectedValue();
    }

    public void preencherCampos(Filme filme) {
        txtNome.setText(filme.getNome());
        txtDuracao.setText(String.valueOf(filme.getDuracao()));
        txtDescricao.setText(filme.getSinopse()); 
        txtPreco.setText(String.valueOf(filme.getValor())); 
        chkEmCartaz.setSelected(filme.isEmCartaz());
    }

    public void atualizarLista(List<Filme> filmes) {
        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (Filme filme : filmes) {
            modelo.addElement(filme.getNome());
        }

        listaFilmes.setModel(modelo);
    }

    public JButton getBtnIncluir() {
        return btnIncluir;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnAlterar() {
        return btnAlterar;
    }

    public JList<String> getListaFilmes() {
        return listaFilmes;
    }

    public void limparCampos() {
        txtNome.setText("");
        txtDuracao.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        chkEmCartaz.setSelected(true);
    }

    public void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
