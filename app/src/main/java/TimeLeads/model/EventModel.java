package TimeLeads.model;

import java.sql.Blob;

public class EventModel {

    private String titulo;
    private Integer id;
    private String descricao;
    private String horas_validas;
    private String data;
    private String imagem;

    public EventModel(){

    }


    public EventModel(String titulo, Integer id, String descricao, String horas_validas, String data, String imagem) {
        this.titulo = titulo;
        this.id = id;
        this.descricao = descricao;
        this.horas_validas = horas_validas;
        this.data = data;
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHoras_validas() {
        return horas_validas;
    }

    public void setHoras_validas(String horas_validas) {
        this.horas_validas = horas_validas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
