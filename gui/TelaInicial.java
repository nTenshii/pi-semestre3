package gui;

/*
 * Turma: A
    Autores: João Victor Matulis || Id de aluno: 1142445416
            Bernardo Galvão de Souza || Id de aluno: 1142473154
            Gabriel de Melo Silva || Id de aluno: 1141267353
            Kevin de Sousa dos Santos || Id de aluno: 1142168549

    Professor: Marcos
 */
import javax.swing.*;

import bd.OperacaoFuncionario;

import java.awt.*;
import java.awt.event.*;

public class TelaInicial extends JFrame implements ActionListener {
    OperacaoFuncionario operacao = new OperacaoFuncionario();
    JLabel bemVindo;
    JMenuBar barraMenu;
    JMenu menu, exibir, inserirMenu, atualizarMenu, removerMenu, funcMenu, fornceMenu;
    JMenuItem inserirFunc, inserirFornce, atualizarFunc, atualizarFornce, removerFunc, removerFornce, funcEsp,
            funcTodos, fornceEsp, fornceTodos;

    public TelaInicial() {
        setTitle("Exibir Moradores");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screen.getWidth() / 2.5f), ((int) (screen.getHeight() / 2.5f)));
        setResizable(false);
        getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bemVindo = criarLabel("Mercado do Bernardo");

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
        fornceMenu = criarSubMenu("Fornecedor",exibir);
        // itens do Menu Inserir
        inserirFunc = criarItemMenu("Funcionario",inserirMenu);
        inserirFornce = criarItemMenu("Fornecedor",inserirMenu);
        // itens do Menu Atualizar
        atualizarFunc = criarItemMenu("Funcionario",atualizarMenu);
        atualizarFornce = criarItemMenu("Fornecedor",atualizarMenu);
        // itens do Menu Remover
        removerFunc = criarItemMenu("Funcionario",removerMenu);
        removerFornce = criarItemMenu("Fornecedor",removerMenu);
        // itens do Menu Exibir Funcionario
        funcEsp = criarItemMenu("Funcionario Especifico",funcMenu);
        funcTodos = criarItemMenu("Todos Funcionarios",funcMenu);
        // itens do Menu Exibir Fornecedor
        fornceEsp = criarItemMenu("Fornecedor Especifico",fornceMenu);
        fornceTodos = criarItemMenu("Todos Fornecedores",fornceMenu);

        centralizar();
        setVisible(true);
    }

    public JLabel criarLabel(String texto) {
        JLabel l1 = new JLabel(texto);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(l1);
        return l1;
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

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inserirFunc){
            setVisible(false);
            new InserirFunc();
        }
        if(e.getSource() == funcTodos){
            OperacaoFuncionario.exibirTodosFuncionarios();
        }
    }

}
