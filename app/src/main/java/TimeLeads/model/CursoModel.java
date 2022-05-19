package TimeLeads.model;

public class CursoModel {

    private Integer id;
    private String nome;
    private String horas_neces;
    private String periodos;
    private Integer coordenador_id;

    public CursoModel() {
    }

    public CursoModel(Integer id, String nome, String horas_neces, String periodos, Integer coordenador_id) {
        this.id = id;
        this.nome = nome;
        this.horas_neces = horas_neces;
        this.periodos = periodos;
        this.coordenador_id = coordenador_id;
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

    public String getHoras_neces() {
        return horas_neces;
    }

    public void setHoras_neces(String horas_neces) {
        this.horas_neces = horas_neces;
    }

    public String getPeriodos() {
        return periodos;
    }

    public void setPeriodos(String periodos) {
        this.periodos = periodos;
    }

    public Integer getCoordenador_id() {
        return coordenador_id;
    }

    public void setCoordenador_id(Integer coordenador_id) {
        this.coordenador_id = coordenador_id;
    }
}
