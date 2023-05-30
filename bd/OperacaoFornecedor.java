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

    public String[] verificarId(int id) {
        String[] resultados = null;
        try {
            resultados = new String[7];
            String minhaQuery = "select * from Fornecedor, Cep where Fornecedor.fk_idCep=Cep.idCep and Fornecedor.id = '"+id+"'";
            result = ConectarBD.getStatement().executeQuery(minhaQuery);
            int coluna = 0;
            if (result.next()) {
                resultados[coluna++] = result.getString("id");
                resultados[coluna++] = result.getString("nome");
                resultados[coluna++] = result.getString("cnpj");
                resultados[coluna++] = result.getString("ie");
                resultados[coluna++] = result.getString("numCep");
                resultados[coluna++] = result.getString("complemento");
                resultados[coluna++] = result.getString("numeroDoLocal");
                return resultados;
            }
        } catch (Exception e) {
            System.out.println("Erro na Inclusao: " + e.getMessage());
        }
        return resultados;
    }

    /**
     * Retorna um array com todos os Fornecedores do BD.
     * 
     * @return String[]
     */
    public String[] exibirTodosFornecedores() {
        int tamanho = tamanhoTabelaFornecedor();
        String[] listaFornecedores = new String[tamanho];
        String sql = "select * from Fornecedor, Cep where Fornecedor.fk_idCep=Cep.idCep ORDER BY id;";
        int aux = 0;
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String cnpj = result.getString("cnpj");
                String ie = result.getString("ie");
                String logradouro = result.getString("nomeLogradouro");
                Integer numero = result.getInt("numeroDoLocal");
                String complemento = result.getString("complemento");
                String bairro = result.getString("bairro");
                String cidade = result.getString("cidade");
                String uf = result.getString("uf");
                String cep = result.getString("numCep");

                listaFornecedores[aux] = ("ID: " + id + " | Nome: " + nome + " | CNPJ: " + cnpj + " | IE: " + ie
                        + " | Endereço: " + logradouro + " " +
                        numero + ", " + complemento + ", " + bairro + ", " + cidade + ", " + uf + ", " + cep + "\n");
                aux++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listaFornecedores;
    }

    /**
     * Retorna uma String com os dados do Fornecedor específico.
     * 
     * @param idFornecedor int
     * @return String
     */
    public String exibirFornecedorEspecifico(int idFornecedor) {
        String fornecedorEspecifico = "";
        String sql = "select * from Fornecedor, Cep where Fornecedor.fk_idCep=Cep.idCep and Fornecedor.id = '"+idFornecedor+"';";
        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String cnpj = result.getString("cnpj");
                String ie = result.getString("ie");
                String logradouro = result.getString("nomeLogradouro");
                Integer numero = result.getInt("numeroDoLocal");
                String complemento = result.getString("complemento");
                String bairro = result.getString("bairro");
                String cidade = result.getString("cidade");
                String uf = result.getString("uf");
                String cep = result.getString("numCep");

                fornecedorEspecifico = ("ID: " + id + "| Nome: " + nome + "| CNPJ: " + cnpj + "| IE: " + ie
                        + "\nEndereço: " + logradouro +
                        numero + ", " + complemento + ", " + bairro + ", " + cidade + ", " + uf + ", " + cep + "\n");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fornecedorEspecifico;
    }

    /**
     * Retorna o total de Fornecedores cadastrados na tabela
     * 
     * @return int
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
     * Atualiza o fornecedor do id informado
     * 
     * @param id int
     * @param nome String
     * @param cnpj String
     * @param ie String
     * @param cep String
     * @param complemento String
     * @param numLocal int
     */
    public void atualizarFornecedor(int id, String nome, String cnpj, String ie, String cep, String complemento,
            int numLocal) {
        String sql = "update Fornecedor set nome = '"+nome+"', cnpj = '"+cnpj+"', ie = '"+ie+"', (select idCep from Cep where numCep = '"+cep+"'), complemento = '"+complemento+"', numeroDoLocal = '"+numLocal+"' where id = '"+id+"'";
        String fornecedorAntes = exibirFornecedorEspecifico(id);

        try {
            result = ConectarBD.getStatement().executeQuery(sql);
            String fornecedorDepois = exibirFornecedorEspecifico(id);
            JOptionPane.showMessageDialog(null, "Fornecedor Atualizado!");
            JOptionPane.showMessageDialog(null,
                    "Fornecedor antigo: " + fornecedorAntes + "\n" + "Fornecedor atualizado: " + fornecedorDepois);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Erro na lista: " + e.getMessage());
        }
    }

    /**
     * Insere um novo fornecedor na tabela
     * 
     * @param nome String
     * @param cnpj String
     * @param ie String
     * @param cep String
     * @param complemento String
     * @param numLocal int
     */
    public void inserirFornecedor(String nome, String cnpj, String ie, String cep, String complemento, int numLocal) {
        try {
            String sql = "INSERT INTO Fornecedor(nome, cnpj, ie, fk_idCep, complemento, numeroDoLocal) VALUES ('"+nome+"', '"+cnpj+"', '"+ie+"', (SELECT idCep FROM Cep WHERE numCep = '"+cep+"'), '"+complemento+"', '"+numLocal+"');";
            ConectarBD.getStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com sucesso!");
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    /**
     * Remove o fornecedor da tabela
     * 
     * @param id int
     */
    public void removerFornecedor(int id) {
        String sql = "delete from Fornecedor where id = '"+id+"'";
        String fornecedorRemovido = "O Fornecedor abaixo foi removido do BD: \n" + exibirFornecedorEspecifico(id);
        try {
            ConectarBD.getStatement().executeUpdate(sql);
            JOptionPane.showConfirmDialog(null, fornecedorRemovido);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Erro na lista: " + e.getMessage());
        }
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
