package TimeLeads.model;

public class ChamadoModel {

    private Integer id;
    private String titulo;
    private String data_envio;
    private String arquivo;
    private String descricao;
    private String status;
    private Integer aluno_id;


    public ChamadoModel() {
    }

    public ChamadoModel(Integer id, String data_envio, String arquivo, String descricao, String status, Integer aluno_id, String titulo) {
        this.id = id;
        this.data_envio = data_envio;
        this.arquivo = arquivo;
        this.descricao = descricao;
        this.status = status;
        this.aluno_id = aluno_id;
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData_envio() {
        return data_envio;
    }

    public void setData_envio(String data_envio) {
        this.data_envio = data_envio;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
