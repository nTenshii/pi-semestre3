/*
 * Turma: A
    Autores: João Victor Matulis || Id de aluno: 1142445416
            Bernardo Galvão de Souza || Id de aluno: 1142473154
            Gabriel de Melo Silva || Id de aluno: 1141267353
            Kevin de Sousa dos Santos || Id de aluno: 1142168549

    Professor: Carlos Veríssimo
 */
package gui.exibir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.inserir.*;
import gui.atualizar.*;
import gui.remover.*;

public class ForneTodos extends JFrame implements ActionListener{

    JPanel p1,p2;
    JScrollPane scroll;
    JLabel[] label;
    JButton anterior, proximo;
    JMenuBar barraMenu;
    JMenu menu, exibir, inserirMenu, atualizarMenu, removerMenu, funcMenu, ForneMenu;
    JMenuItem inserirFunc, inserirForne, atualizarFunc, atualizarForne, removerFunc, removerForne, funcEsp,
            funcTodos, forneEsp, forneTodos;
    int contadorElementosTela = 5, tela = 1;
    int morador = 5;
    int linha = 0;
    int coluna = 0;
    String[] result;

    public ForneTodos(String[] result) {
        this.result = result;
        label = new JLabel[result.length];
        for(int i = 0; i < result.length; i++){
            label[i] = criarLabel(result[i]);
        }
        setTitle("Exibir Fornecedores");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(((int)screen.getWidth()/2)-100, ((int)screen.getHeight()/2)-100);
        getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gl1 = new GridLayout(5, 1, 0, 0);
        GridLayout gl2 = new GridLayout(1, 2, 0, 0);

        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        // Criação dos Menus Principais
        menu = criarMenuPrincipal("Menu", barraMenu);
        exibir = criarMenuPrincipal("Exibir", barraMenu);
        // Criação dos Sub Menus
        inserirMenu = criarSubMenu("Inserir", menu);
        atualizarMenu = criarSubMenu("Atualizar", menu);
        removerMenu = criarSubMenu("Remover", menu);
        funcMenu = criarSubMenu("Funcionário", exibir);
        ForneMenu = criarSubMenu("Fornecedor", exibir);
        // itens do Menu Inserir
        inserirFunc = criarItemMenu("Funcionario", inserirMenu);
        inserirForne = criarItemMenu("Fornecedor", inserirMenu);
        // itens do Menu Atualizar
        atualizarFunc = criarItemMenu("Funcionario", atualizarMenu);
        atualizarForne = criarItemMenu("Fornecedor", atualizarMenu);
        // itens do Menu Remover
        removerFunc = criarItemMenu("Funcionario", removerMenu);
        removerForne = criarItemMenu("Fornecedor", removerMenu);
        // itens do Menu Exibir Funcionario
        funcEsp = criarItemMenu("Funcionario Especifico", funcMenu);
        funcTodos = criarItemMenu("Todos Funcionarios", funcMenu);
        // itens do Menu Exibir Fornecedor
        forneEsp = criarItemMenu("Fornecedor Especifico", ForneMenu);
        forneTodos = criarItemMenu("Todos Fornecedores", ForneMenu);


        anterior = criarButton("Anterior",'a');
        anterior.setEnabled(false);
        
        proximo = criarButton("Proximo",'d');
        //Se tiver mais de 5 moradores registrados, o botão proximo fica disponivel para ser clicado
        if(label.length>5){
            proximo.setEnabled(true);
        }else{
            proximo.setEnabled(false);
        }

        p1 = criarPainel(Color.white, gl1, anterior);
        //Verifica se tem mais de 5 morados para exibir na tela
        if(label.length<=5){
            for(int i = 0; i < label.length;i++){
                p1.add(label[i]);
            }
        }else{
            for(int i = 0; i < 5;i++){
                p1.add(label[i]);
            }
        }
        scroll = new JScrollPane(p1);
        add(scroll);
        p2 = criarPainel(Color.white, gl2, anterior);
        p2.add(proximo);
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
        l1.setFont(new Font("Arial", Font.BOLD, 18));
        return l1;
    }
    
    private JPanel criarPainel(Color cor, GridLayout gl, JButton botao1) {
        JPanel p1 = new JPanel();
		p1.setLayout(gl);
		p1.setBackground(cor);
		p1.add(botao1);
		add(p1);
		return p1;
	}

    public JButton criarButton(String texto, char botao) {
        JButton b1 = new JButton(texto);
        b1.addActionListener(this);
        b1.setFont(new Font("Arial", Font.BOLD, 24));
        b1.setMnemonic(botao);
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
        if(e.getSource() == proximo){
            p1.removeAll();
            p1.revalidate();
            p1.repaint();
            anterior.setEnabled(true);
            if(label.length-(contadorElementosTela*tela) <= 5){
                for(int i = 0; i < (label.length-(contadorElementosTela*tela));i++){
                    p1.add(label[morador++]);
                }
                proximo.setEnabled(false);
            }
            else{
                for(int i = 0; i < 5;i++){
                    p1.add(label[morador++]);
                }
            }
            tela++;
        }
        if(e.getSource() == anterior){
            p1.removeAll();
            p1.revalidate();
            p1.repaint();
            proximo.setEnabled(true);
            tela--;
            morador = (contadorElementosTela * tela)-5;
            if(tela == 1){
                for(int i = 0; i < 5; i++){
                    p1.add(label[morador++]);
                }
                anterior.setEnabled(false);
            }else{
                for(int i = 0; i<5;i++){
                    p1.add(label[morador++]);
                }                
            }
        }
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
        if (e.getSource() == forneEsp) {

        }
        if (e.getSource() == forneTodos) {
            setVisible(false);
            String[] a = {"T"};
            new ForneTodos(a);
        }
    }
}
