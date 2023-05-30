package objetos;

/*
 * Turma: A
 *  Autores: João Victor Matulis || Id de aluno: 1142445416
 *           Bernardo Galvão de Souza || Id de aluno: 1142473154
 *           Gabriel de Melo Silva || Id de aluno: 1141267353
 *           Kevin de Sousa dos Santos || Id de aluno: 1142168549
 *
 *  Professor: Marcos Monteiro
 */

public class Fornecedor {
    private String nome, cnpj, ie;
    private Cep cep;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj, String ie, Cep cep, String complemento, int numeroDoLocal) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.cep = cep;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return this.ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }


    public Cep getCep() {
        return this.cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }
}
