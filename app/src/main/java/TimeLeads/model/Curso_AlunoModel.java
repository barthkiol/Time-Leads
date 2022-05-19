package TimeLeads.model;

public class Curso_AlunoModel {

    private Integer id;
    private String periodo;
    private String horas_aluno;
    private String status;
    private Integer aluno_id;
    private Integer curso_id;

    public Curso_AlunoModel() {
    }

    public Curso_AlunoModel(Integer id, String periodo, String horas_aluno, String status, Integer aluno_id, Integer curso_id) {
        this.id = id;
        this.periodo = periodo;
        this.horas_aluno = horas_aluno;
        this.status = status;
        this.aluno_id = aluno_id;
        this.curso_id = curso_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getHoras_aluno() {
        return horas_aluno;
    }

    public void setHoras_aluno(String horas_aluno) {
        this.horas_aluno = horas_aluno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Integer aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }
}
