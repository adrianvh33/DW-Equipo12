package com.equipo12.equipo12.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Date;
import java.util.List;

public class Project {
    @Id
    private String id_proyecto;

    private String nombre;
    private List<String> integrantes ;
    private Integer director;
    private Integer presupuesto;
    private List<String> objetivos;
    private String estado;
    private List<Date> fechas;
    private List<Fase> fases;
    private List<String> notas_desempeno;


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

    public Integer getDirector() {
        return director;
    }

    public void setDirector(Integer director) {
        this.director = director;
    }

    public Integer getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Integer presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<String> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<String> objetivos) {
        this.objetivos = objetivos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }

    public List<String> getNotas_desempeno() {
        return notas_desempeno;
    }

    public void setNotas_desempeno(List<String> notas_desempeno) {
        this.notas_desempeno = notas_desempeno;
    }
}
