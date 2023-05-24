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
public class Funcionario {
    private String nome, rg, cpf;
    private int id;

    public Funcionario() {
    }

    public Funcionario(String nome, String rg, String cpf) {
        this.nome = nome;
        this.rg = rg;
        if (validaCpf(cpf) != 1) {
            System.out.println("CPF Inválido");
        } else {
            this.cpf = cpf;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Confirma se o CPF informado é valido ou não
     * 
     * @param cpf
     * @return
     */
    public int validaCpf(String cpf) {
        if (cpf.length() != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222"
                || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666"
                || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999" || cpf == "01234567890") {

            return 0;
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
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Valida o RG com base nos critérios do SSP-SP
     * 
     * @param rg
     * @return
     */
    public int validaRg(String rg) {
        if (rg.length() != 9) {
            return 0;
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
            return 1;
        } else {
            return 0;
        }
    }

}