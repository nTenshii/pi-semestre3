/*
 * Turma: A
    Autores: João Victor Matulis || Id de aluno: 1142445416
            Bernardo Galvão de Souza || Id de aluno: 1142473154
            Gabriel de Melo Silva || Id de aluno: 1141267353
            Kevin de Sousa dos Santos || Id de aluno: 1142168549

    Professor: Marcos
 */
package gui.atualizar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.exibir.*;
import gui.inserir.*;
import gui.remover.*;

public class AtualizarFunc extends JFrame implements ActionListener{
    JMenuBar barraMenu;
    JMenu menu, exibir, inserirMenu, atualizarMenu, removerMenu, funcMenu, ForneMenu;
    JMenuItem inserirFunc, inserirForne, atualizarFunc, atualizarForne, removerFunc, removerForne, funcEsp,
            funcTodos, ForneEsp, ForneTodos;
    JLabel lnome, lrg, lcpf; 
    JTextField tnome, trg, tcpf;
    JButton ok, cancelar;
    
    public AtualizarFunc() {
        setTitle("Atualizar Funcionario");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screen.getWidth() / 2.5f), ((int) (screen.getHeight() / 2.5f)));
        setResizable(false);
        getContentPane().setLayout(new GridLayout(4, 2, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        // Criação dos Menus Principais
        menu = criarMenuPrincipal("Menu", barraMenu);
        exibir = criarMenuPrincipal("Exibir", barraMenu);
        //Criação dos Sub Menus
        inserirMenu = criarSubMenu("Inserir",menu);
        atualizarMenu = criarSubMenu("Atualizar",menu);
        removerMenu = criarSubMenu("Remover",menu);
        funcMenu = criarSubMenu("Funcionário",exibir);
        ForneMenu = criarSubMenu("Fornecedor",exibir);
        // itens do Menu Inserir
        inserirFunc = criarItemMenu("Funcionario",inserirMenu);
        inserirForne = criarItemMenu("Fornecedor",inserirMenu);
        // itens do Menu Atualizar
        atualizarFunc = criarItemMenu("Funcionario",atualizarMenu);
        atualizarForne = criarItemMenu("Fornecedor",atualizarMenu);
        // itens do Menu Remover
        removerFunc = criarItemMenu("Funcionario",removerMenu);
        removerForne = criarItemMenu("Fornecedor",removerMenu);
        // itens do Menu Exibir Funcionario
        funcEsp = criarItemMenu("Funcionario Especifico",funcMenu);
        funcTodos = criarItemMenu("Todos Funcionarios",funcMenu);
        // itens do Menu Exibir Fornecedor
        ForneEsp = criarItemMenu("Fornecedor Especifico",ForneMenu);
        ForneTodos = criarItemMenu("Todos Fornecedores",ForneMenu);

        lnome = criarLabel("Nome: ");
        tnome = criarTextField();

        lrg = criarLabel("RG:");
        trg = criarTextField();

        lcpf = criarLabel("CPF: ");
        tcpf = criarTextField();
        
        ok = criarButton("Ok");        
        cancelar = criarButton("Cancelar");

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

    public JTextField criarTextField(){
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
    
    private JMenu criarMenuPrincipal(String texto, JMenuBar barra) {
        JMenu menu = new JMenu(texto);
        barra.add(menu);
        return menu;
    }

    private JMenu criarSubMenu(String texto, JMenu menuPrinc) {
        JMenu menu = new JMenu(texto);
        menuPrinc.add(menu);
        return menu;
    }

    private JMenuItem criarItemMenu(String texto, JMenu menu) {
        JMenuItem itemMenu = new JMenuItem(texto);
        itemMenu.addActionListener(this);
        menu.add(itemMenu);
        return itemMenu;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inserirFunc) {
            setVisible(false);
            new InserirFunc();
        }
        if (e.getSource() == inserirForne) {
            setVisible(false);
            new InserirForne(null,null,null);
        }
        if (e.getSource() == atualizarFunc) {
            setVisible(false);
            new AtualizarFunc();
        }
        if (e.getSource() == atualizarForne) {
            setVisible(false);
            new AtualizarForne(null,null,null);
        }
        if (e.getSource() == removerFunc) {
            setVisible(false);
            new RemoverFunc();
        }
        if (e.getSource() == removerForne) {
            setVisible(false);
            new RemoverForne();
        }
        if (e.getSource() == funcEsp) {

        }
        if (e.getSource() == funcTodos) {
            setVisible(false);
            String[] a = {"a"};
            new FuncTodos(a);
        }
        if (e.getSource() == ForneEsp) {

        }
        if (e.getSource() == ForneTodos) {
            setVisible(false);
            String[] a = {"T"};
            new ForneTodos(a);
        }
        if(e.getSource() == ok){
            try {
                int confirmacao = JOptionPane.showConfirmDialog(rootPane, "Confirmar Dados?", "Confirmação", 2);
                if (confirmacao == 0) {
                    if(!tnome.getText().isEmpty() && !trg.getText().isEmpty() && !tcpf.getText().isEmpty()){
                        String nome = tnome.getText();
                        String rg = trg.getText();
                        String cpf = tcpf.getText();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Algum campo está vazio");
                    }
                }
            }catch (Exception ed) {
                // TODO: handle exception
            }
        }
        if(e.getSource() == cancelar){
            int confirmacao = JOptionPane.showConfirmDialog(rootPane,"Cancelar?","Confirmação",2);
            if(confirmacao == 0){
                tnome.setText("");
                trg.setText("");
                tcpf.setText("");
            }
        }
    }
}
