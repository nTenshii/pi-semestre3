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
    ResultSet result = null;

    /**
     * Retorna um Array com os dados de todos os funcionários
     * 
     * @return
     */
    public String[] exibirTodosFuncionarios() {
        int tamanho = tamanhoTabelaFuncionarios();
        String[] listaFuncionarios = new String[tamanho];
        String sql = "select * from Funcionario";
        int aux = 0;
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String rg = result.getString("rg");
                String cpf = result.getString("cpf");
                listaFuncionarios[aux] = ("ID: " + id + ", Nome: " + nome + ", RG: " + rg + ", CPF: " + cpf + "\n");
                aux++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaFuncionarios;
    }

    /**
     * Exibe um funcionário específico com base no ID
     * 
     * @param id
     * @return
     */
    public String exibirFuncionarioEspecifico(int id) {
        String sql = "select * from Funcionario where id = {id}";
        String funcionarioEspecifico = "";
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            while (result.next()) {
                String nome = result.getString("nome");
                String rg = result.getString("rg");
                String cpf = result.getString("cpf");
                funcionarioEspecifico = ("ID: " + id + ", Nome: " + nome + ", RG: " + rg + ", CPF: " + cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarioEspecifico;

    }

    /**
     * 
     * @param id
     */
    public void removerFuncionario(int id) {
        String sql = "delete from Funcionario where id = {id}";
        String funcionarioRemovido = "O funcionário abaixo foi removido do BD: \nexibirFuncionarioEspecifico(id)";
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            JOptionPane.showConfirmDialog(null, funcionarioRemovido);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Erro na lista: " + e.getMessage());
        }
    }

    /**
     * Retorna o total de funcionários cadastrados na tabela
     * 
     * @return
     */
    public int tamanhoTabelaFuncionarios() {
        int tamanho = 0;
        try {
            String minhaQuery = "SELECT COUNT(*) AS total FROM Funcionario";
            result = ConectarBD.getStatement().executeQuery(minhaQuery);
            while (result.next()) {
                tamanho = result.getInt("total");
            }
            return tamanho;
        } catch (Exception e) {
            System.out.println("Erro na lista: " + e.getMessage());
        }
        return tamanho;
    }

    /**
     * Atualiza um cadastro de Funcionário já existente
     * 
     * @param id
     * @param nome
     * @param rg
     * @param cpf
     */
    public void atualizarFuncionario(int id, String nome, String rg, String cpf) {
        String sql = "update Funcionario set nome = '{nome}', rg = '{rg}', cpf = '{cpf}' where id = {id}";
        String funcionarioAntes = exibirFuncionarioEspecifico(id);
        String funcionarioDepois = null;

        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            JOptionPane.showMessageDialog(null, "Funcionário Atualizado!");
            JOptionPane.showMessageDialog(null,
                    "Funcionário antigo: " + funcionarioAntes + "\n" + "Funcionário atualizado: " + funcionarioDepois);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Erro na lista: " + e.getMessage());
        }
    }

    public void inserirFuncionario(String nome, String rg, String cpf) {
        String sql = "insert into Funcionario (nome, rg, cpf) values('{nome}', '{rg}', '{cpf}')";
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            JOptionPane.showMessageDialog(null, "Funcionário Cadastrado com sucesso!" + "\n" + "");
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    /**
     * Valida o RG com base nos critérios do SSP-SP
     * 
     * @param rg
     * @return
     */
    public boolean isRg(String rg) {
        if (rg.length() != 9) {
            return false;
        }

        int aux = 2;
        int soma = 0;
        for (int i = 0; i < rg.length() - 1; i++) {
            int digRg = Character.getNumericValue(rg.charAt(i));
            soma += digRg * aux;
            aux++;
        }

        int resto = 11 - (soma % 11);
        if (resto == Character.getNumericValue(rg.charAt(rg.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Confirma se o CPF informado é valido ou não
     * 
     * @param cpf
     * @return
     */
    public boolean isCPF(String cpf) {
        if (cpf.length() != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222"
                || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666"
                || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999" || cpf == "01234567890") {

            return false;
        }

        // Validação do primeiro dígito do CPF
        int aux = 10;
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            int digCpf = Character.getNumericValue(cpf.charAt(i));
            soma1 += (digCpf * aux);
            aux--;
        }

        int resto1 = (soma1 * 10) % 11;
        if (resto1 == 10) {
            resto1 = 0;
        }

        // Validação do segundo dígito do CPF
        aux = 11;
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            int digCpf = Character.getNumericValue(cpf.charAt(i));
            soma2 += (digCpf * aux);
            aux--;
        }
        int resto2 = (soma2 * 10) % 11;
        if (resto2 == 10) {
            resto2 = 0;
        }

        if (resto1 == Character.getNumericValue(cpf.charAt(9)) && resto2 == Character.getNumericValue(cpf.charAt(10))) {
            return true;
        } else {
            return false;
        }
    }

}
