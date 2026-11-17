package cinema.view;

import javax.swing.*;
import java.awt.*;


public class TelaPrincipal extends JFrame {

        public TelaPrincipal(CompraGateway ctrl, Runnable abrirFilmes) {
        this(ctrl, abrirFilmes, null);
    }

    public TelaPrincipal(CompraGateway ctrl, Runnable abrirFilmes, Runnable abrirAdministracao) {
        super("Cinema");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(abrirAdministracao == null ? 5 : 6, 1, 10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel("Sistema de Cinema", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        add(titulo);
        JLabel logado = new JLabel("Usu\u00e1rio: " + ctrl.nomeUsuario(), SwingConstants.CENTER);
        add(logado);

        JButton filmes = new JButton("Filmes em cartaz");
        filmes.addActionListener(e -> abrirFilmes.run());
        add(filmes);

        JButton comprar = new JButton("Comprar bilhete");
        comprar.addActionListener(e -> new TelaCompra(ctrl).setVisible(true));
        add(comprar);
        if (abrirAdministracao != null) {
            JButton administracao = new JButton("Administra\u00e7\u00e3o de filmes");
            administracao.addActionListener(e -> abrirAdministracao.run());
            add(administracao);
        }

        JButton sair = new JButton("Sair");
        sair.addActionListener(e -> dispose());
        add(sair);

        pack();
        setLocationRelativeTo(null);
    }
}
