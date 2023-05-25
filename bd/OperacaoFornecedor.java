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

public class OperacaoFornecedor {
    ResultSet result = null;

    public String[] exibirTodosFornecedores() {
        int tamanho = tamanhoTabelaFornecedor();
        String[] listaFornecedores = new String[tamanho];
        String sql = "select * from Fornecedor";
        int aux = 0;
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String cnpj = result.getString("cnpj");
                String ie = result.getString("ie");
                listaFornecedores[aux] = ("ID: " + id + ", Nome: " + nome + ", CNPJ: " + cnpj + "Endereço: " + cep"\n");
                aux++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaFornecedores;
    }

    /**
     * Retorna o total de funcionários cadastrados na tabela
     * 
     * @return
     */
    public int tamanhoTabelaFornecedor() {
        int tamanho = 0;
        try {
            String minhaQuery = "SELECT COUNT(*) AS total FROM Fornecedor";
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
     * Verifica se o CNPJ informado é válido ou não
     * 
     * @param cnpj
     * @return
     */
    public boolean isCnpj(String cnpj) {
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14)) {
            return false;
        }

        int dig1 = Character.getNumericValue(cnpj.charAt(12));
        int dig2 = Character.getNumericValue(cnpj.charAt(13));

        // Validação do primeiro dígito verificador do CNPJ
        int aux = 2;
        int soma1 = 0;

        for (int i = 11; i >= 0; i--) {
            if (aux > 9) {
                aux = 2;
            }
            soma1 += Character.getNumericValue(cnpj.charAt(i)) * aux;
            aux++;
        }

        int resto1;
        if (soma1 % 11 == 0 || soma1 % 11 == 1) {
            resto1 = 0;
        } else {
            resto1 = 11 - (soma1 % 11);
        }

        aux = 2;
        int soma2 = 0;
        for (int i = 12; i >= 0; i--) {
            if (aux > 9) {
                aux = 2;
            }
            soma2 += Character.getNumericValue(cnpj.charAt(i)) * aux;
            aux++;
        }

        int resto2;
        if (soma2 % 11 == 0 || soma2 % 11 == 1) {
            resto2 = 0;
        } else {
            resto2 = 11 - (soma2 % 11);
        }

        if (resto1 == dig1 && resto2 == dig2) {
            return true;
        } else {
            return false;
        }

    }
}
