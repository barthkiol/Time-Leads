package TimeLeads.model;

public class AlunoModel {

    private Integer id;
    private String nome;
    private String data_nasc;
    private String cpf;
    private String email;
    private String senha;
    private String foto;
    private String matricula;

    public AlunoModel() {
    }

    public AlunoModel(Integer id, String nome, String data_nasc, String CPF, String email, String senha, String foto, String matricula) {
        this.id = id;
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.cpf = CPF;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
