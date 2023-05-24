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
public class OperacaoFuncionario {
    ResultSet rs = null;
    public void exibirTodosFuncionarios(){
        String sql = "select * from Funcionario";
        try {
            rs = ConectarBD.getStatement().executeQuery(sql);
            while(rs.next()){
                String nome = rs.getString("nome");
                String rg = rs.getString("rg");
                String cpf = rs.getString("cpf");
                JOptionPane.showMessageDialog(null, "Nome: " + nome + ", RG: " + rg + ", CPF: " + cpf);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
