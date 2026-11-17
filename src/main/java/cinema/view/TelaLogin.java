package cinema.view;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;

public class TelaLogin extends JFrame {

    private final LoginGateway login;
    private final IntConsumer aoEntrar;
    private final JComboBox<String> cbUsuario = new JComboBox<>();
    private final JPasswordField campoSenha = new JPasswordField(15);

    public TelaLogin(LoginGateway login, IntConsumer aoEntrar) {
        super("Entrar");
        this.login = login;
        this.aoEntrar = aoEntrar;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Sistema de Cinema", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        add(titulo, BorderLayout.NORTH);

        for (String u : login.usuarios()) cbUsuario.addItem(u);

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.add(new JLabel("Usuário:"));
        form.add(cbUsuario);
        form.add(new JLabel("Senha:"));
        form.add(campoSenha);
        add(form, BorderLayout.CENTER);

        JButton entrar = new JButton("Entrar");
        entrar.addActionListener(e -> entrar());
        getRootPane().setDefaultButton(entrar);
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rodape.add(entrar);
        add(rodape, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void entrar() {
        int indice = cbUsuario.getSelectedIndex();
        if (indice < 0) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário cadastrado.");
            return;
        }
        String senha = new String(campoSenha.getPassword());
        if (!login.autenticar(indice, senha)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoSenha.setText("");
            return;
        }
        dispose();
        aoEntrar.accept(indice);
    }
}
