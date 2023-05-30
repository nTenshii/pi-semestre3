package gui.inserir;

/*
 * Turma: A
 *  Autores: João Victor Matulis || Id de aluno: 1142445416
 *           Bernardo Galvão de Souza || Id de aluno: 1142473154
 *           Gabriel de Melo Silva || Id de aluno: 1141267353
 *           Kevin de Sousa dos Santos || Id de aluno: 1142168549
 *
 *  Professor: Marcos Monteiro
 */

import javax.swing.*;
import bd.OperacaoFornecedor;
import java.awt.*;
import java.awt.event.*;
import gui.TelaInicial;

public class InserirForne extends JFrame implements ActionListener {
    JLabel lnome, lcnpj, lIE, lcep, lcomplemento, lnumLocal;
    JTextField tnome, tcnpj, tIE, tcep, tcomplemento, tnumLocal;
    JButton ok, cancelar;
    OperacaoFornecedor op = new OperacaoFornecedor();

    public InserirForne(String nome, String cnpj, String ie) {
        setTitle("Inserir Fornecedor");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screen.getWidth() / 2.5f), ((int) (screen.getHeight() / 2.5f)));
        setResizable(false);
        getContentPane().setLayout(new GridLayout(7, 2, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lnome = criarLabel("Nome: *");
        tnome = criarTextField(nome);

        lcnpj = criarLabel("CNPJ: *");
        tcnpj = criarTextField(cnpj);

        lIE = criarLabel("Inscrição Estadual: ");
        tIE = criarTextField(ie);

        lcep = criarLabel("CEP: *");
        tcep = criarTextField("");

        lcomplemento = criarLabel("Complemento: ");
        tcomplemento = criarTextField("");

        lnumLocal = criarLabel("Número do Local: *");
        tnumLocal = criarTextField("");

        ok = criarButton("Ok");
        add(ok);

        cancelar = criarButton("Voltar");
        add(cancelar);

        setVisible(true);
        centralizar();
    }

    public void centralizar() {
        // obtém a altura e largura da resolução vídeo
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // obtém a altura e largura da minha janela
        Dimension janela = getSize();
        if (janela.height > screen.height)
            setSize(janela.width, screen.height);
        if (janela.width > screen.width)
            setSize(screen.width, janela.height);
        setLocation((screen.width - janela.width) / 2,
                (screen.height - janela.height) / 2);
    }

    public JLabel criarLabel(String texto) {
        JLabel l1 = new JLabel(texto);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        add(l1);
        return l1;
    }

    public JTextField criarTextField(String texto) {
        JTextField jt = new JTextField(texto);
        jt.setHorizontalAlignment(SwingConstants.CENTER);
        jt.setFont(new Font("Arial", Font.PLAIN, 18));
        add(jt);
        return jt;
    }

    public JButton criarButton(String texto) {
        JButton b1 = new JButton(texto);
        b1.addActionListener(this);
        b1.setFont(new Font("Arial", Font.BOLD, 24));
        add(b1);
        return b1;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Confirmar Dados?", "Confirmação", 0);
                if (confirmacao == 0) {
                    if (!tnome.getText().isEmpty() && !tcnpj.getText().isEmpty() && !tcep.getText().isEmpty()
                            && !tnumLocal.getText().isEmpty()) {
                        String nome = tnome.getText();
                        String cnpj = tcnpj.getText();
                        String ie = tIE.getText();
                        String cep = tcep.getText();
                        String complemento = tcomplemento.getText();
                        int numLocal = Integer.parseInt(tnumLocal.getText());
                        if (!op.isCnpj(cnpj)) {
                            JOptionPane.showMessageDialog(rootPane, "O CNPJ informado não é válido.");
                        } else if(!op.isCep(cep)){
                            JOptionPane.showMessageDialog(rootPane, "CEP invalido");
                        } else {
                            op.inserirFornecedor(nome, cnpj, ie, cep, complemento, numLocal);
                            tnome.setText("");
                            tcnpj.setText("");
                            tIE.setText("");
                            tcep.setText("");
                            tcomplemento.setText("");
                            tnumLocal.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                "Preencha todos os campos obrigatórios antes de continuar.");
                    }
                }
            } catch (Exception ed) {
                // TODO: handle exception
            }
        }
        if (e.getSource() == cancelar) {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Cancelar cadastro?", "Confirmação", 0);
            if (confirmacao == 0) {
                tnome.setText("");
                tcnpj.setText("");
                tIE.setText("");
                tcep.setText("");
                tcomplemento.setText("");
                tnumLocal.setText("");
                dispose();
                new TelaInicial();
            }
        }
    }
}
