package gui.remover;

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
import bd.OperacaoFuncionario;
import gui.TelaInicial;
import java.awt.*;
import java.awt.event.*;

public class RemoverFunc extends JFrame implements ActionListener {
    JLabel lid;
    JTextField tid;
    JButton ok, cancelar;
    OperacaoFuncionario op = new OperacaoFuncionario();

    public RemoverFunc() {
        setTitle("Remover Funcionario");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(((int) screen.getWidth() / 2) - 200, ((int) screen.getHeight() / 2) - 200);
        getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lid = criarLabel("Id do Funcionario: ");
        add(lid);
        tid = criarTextField();
        add(tid);

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

    public JTextField criarTextField() {
        JTextField jt = new JTextField();
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
                int confirmacao = JOptionPane.showConfirmDialog(rootPane,"Confirmar Remoção?","Confirmação",0);
                if(confirmacao == 0){
                    if (tid.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Insira um ID para remoção.");
                    } else if(op.verificarId(Integer.parseInt(tid.getText()))[0] == null){
                        JOptionPane.showMessageDialog(rootPane, "Id invalido.");
                    } 
                    else {
                        int id = Integer.parseInt(tid.getText());
                        op.removerFuncionario(id);
                        tid.setText("");
                    }
            }
                
            } catch (Exception ed) {
                // TODO: handle exception
            }
        }

        if (e.getSource() == cancelar) {
            int confirmacao = JOptionPane.showConfirmDialog(rootPane,"Voltar ao menu?","Confirmação",0);
            if(confirmacao == 0){
                tid.setText("");
                dispose();
                new TelaInicial();
            }
            
        }
    }
}
