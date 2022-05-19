package TimeLeads.model;

public class HorasComplementaresModel {

    private Integer id;
    private String nome;
    private String horas;
    private Integer aluno_id;

    public HorasComplementaresModel() {
    }

    public HorasComplementaresModel(Integer id, String nome, String horas, Integer aluno_id) {
        this.id = id;
        this.nome = nome;
        this.horas = horas;
        this.aluno_id = aluno_id;
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

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public Integer getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Integer aluno_id) {
        this.aluno_id = aluno_id;
    }
}
