package com.equipo12.equipo12.models;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class User {

    @Id
    private String id;

    private Integer id_usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String role;
    private Integer telefono;
    private String carrera;
    private Date fecha_ingreso;
    private List<Integer> id_proyectos;


    public User(String id, Integer id_usuario, String nombre, String apellido, String correo, String password, String role, Integer telefono, String carrera, Date fecha_ingreso, List<Integer> id_proyectos) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.role = role;
        this.telefono = telefono;
        this.carrera = carrera;
        this.fecha_ingreso = fecha_ingreso;
        this.id_proyectos = id_proyectos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public List<Integer> getId_proyectos() {
        return id_proyectos;
    }

    public void setId_proyectos(List<Integer> id_proyectos) {
        this.id_proyectos = id_proyectos;
    }
}

