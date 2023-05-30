package gui.atualizar;

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

public class AtualizarFunc extends JFrame implements ActionListener{
    JLabel lnome, lrg, lcpf; 
    JTextField tnome, trg, tcpf;
    JButton ok, cancelar;
    int id;
    OperacaoFuncionario op = new OperacaoFuncionario();
    
    public AtualizarFunc(String[] dados) {
        id = Integer.parseInt(dados[0]);
        setTitle("Atualizar Funcionario");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screen.getWidth() / 2.5f), ((int) (screen.getHeight() / 2.5f)));
        setResizable(false);
        getContentPane().setLayout(new GridLayout(4, 2, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lnome = criarLabel("Nome: ");
        tnome = criarTextField(dados[1]);

        lrg = criarLabel("RG:");
        trg = criarTextField(dados[2]);

        lcpf = criarLabel("CPF: ");
        tcpf = criarTextField(dados[3]);
        
        ok = criarButton("Ok");        
        cancelar = criarButton("Voltar");

        centralizar();
        setVisible(true);
        
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

    public JTextField criarTextField(String texto){
        JTextField jt = new JTextField();
        jt.setText(texto);
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
        if(e.getSource() == ok){
            try {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Confirmar Dados?", "Confirmação", 0);
                if (confirmacao == 0) {
                    if(!tnome.getText().isEmpty() && !trg.getText().isEmpty() && !tcpf.getText().isEmpty()){
                        String nome = tnome.getText();
                        String rg = trg.getText();
                        String cpf = tcpf.getText();
                        if(!op.isCPF(cpf)){
                            JOptionPane.showMessageDialog(rootPane, "O CPF informado não é válido.");
                        }else if(!op.isRg(rg)){
                            JOptionPane.showMessageDialog(rootPane, "O RG informado não é válido.");
                        }else{
                            op.atualizarFuncionario(id, nome, rg, cpf);
                            dispose();
                            new TelaInicial();
                        }

                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Preencha todos os campos antes de continuar");
                    }
                }
            }catch (Exception ed) {
                // TODO: handle exception
            }
        }
        if(e.getSource() == cancelar){
            int confirmacao = JOptionPane.showConfirmDialog(rootPane,"Voltar ao Menu?","Confirmação",0);
            if(confirmacao == 0){
                dispose();
                new TelaInicial();
            }
        }
    }
}
