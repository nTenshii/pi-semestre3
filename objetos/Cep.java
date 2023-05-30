package objetos;
/*
 * Turma: A
    Autores: João Victor Matulis || Id de aluno: 1142445416
            Bernardo Galvão de Souza || Id de aluno: 1142473154
            Gabriel de Melo Silva || Id de aluno: 1141267353
            Kevin de Sousa dos Santos || Id de aluno: 1142168549

    Professor: Marcos Monteiro
 */
public class Cep {
    private String nomeLogradouro, uf, cidade, bairro, numCep, complemento;

    public Cep(String nomeLogradouro, String uf, String cidade, String bairro, String numCep, String complemento) {
        this.nomeLogradouro = nomeLogradouro;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numCep = numCep;
        this.complemento = complemento;
    }

    public String getNomeLogradouro() {
        return this.nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumCep() {
        return this.numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
