package bd;
/*
 * Turma: A
 *  Autores: João Victor Matulis || Id de aluno: 1142445416
 *           Bernardo Galvão de Souza || Id de aluno: 1142473154
 *           Gabriel de Melo Silva || Id de aluno: 1141267353
 *           Kevin de Sousa dos Santos || Id de aluno: 1142168549
 *
 *  Professor: Marcos Monteiro
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class ConectarBD {
    private static Connection con = null;
    protected static Statement stm  = null;

    public ConectarBD(){
        conecta();
        if(estaConectado()){
            JOptionPane.showMessageDialog(null,"Banco de dados conectado");
        }else{
            JOptionPane.showMessageDialog(null,"Erro na conexão com o Banco de dados");
            System.exit(0);
        }
    }

    public void conecta() {
        String servidor = "jdbc:mysql://localhost:3306/mercado_bernardo";
        String usuario = "root";
        String senha = "ehoserverkktlgdnehcachorro";
        String driver = "com.mysql.cj.jdbc.Driver";
        
        try {
            Class.forName(driver);
            ConectarBD.con = DriverManager.getConnection(servidor, usuario, senha);
            ConectarBD.stm = ConectarBD.con.createStatement();
        } catch (Exception e) {
            System.out.println("erro na conexão : " + e.getMessage());
        }
    }
    
    public boolean estaConectado() {
        if (ConectarBD.con != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Statement getStatement(){
        try {
            return con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
