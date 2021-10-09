package com.equipo12.equipo12.models;

import java.util.Map;

public class Fase {
    private String  estado;
    private Map<String, String> avances;

    public Fase(String estado, Map<String, String> avances) {
        this.estado = estado;
        this.avances = avances;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Map<String, String> getAvances() {
        return avances;
    }

    public void setAvances(Map<String, String> avances) {
        this.avances = avances;
    }
}
