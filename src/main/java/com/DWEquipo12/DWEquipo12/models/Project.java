package com.DWEquipo12.DWEquipo12.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Date;
import java.util.List;

public class Project {
    @Id
    private String id_proyecto;

    private String nombre;
    private List<String> integrantes ;
    private String director;
    private Integer presupuesto;
    private String objetivos;
    private String estado;
    private Date fecha_inicio;
    private Date fecha_final;
    private String estadoFase;
    private List<String> avances;
    private String notas_desempeno;


    public Project() {
    }

    public String getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(String id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Integer presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotas_desempeno() {
        return notas_desempeno;
    }

    public void setNotas_desempeno(String notas_desempeno) {
        this.notas_desempeno = notas_desempeno;
    }

    public String getEstadoFase() {
        return estadoFase;
    }

    public void setEstadoFase(String estadoFase) {
        this.estadoFase = estadoFase;
    }

    public List<String> getAvances() {
        return avances;
    }

    public void setAvances(List<String> avances) {
        this.avances = avances;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }
}
