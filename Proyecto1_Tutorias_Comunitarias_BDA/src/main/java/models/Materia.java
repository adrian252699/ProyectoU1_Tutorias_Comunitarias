/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;

/**
 *
 * @author jalt2
 */
public class Materia {
   private int id_materia;
   private String nombre;
   private String nivel_educativo;
   private String descripcion;

    public Materia() {
    }

    public Materia(int id_materia, String nombre, String nivel_educativo, String descripcion) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.nivel_educativo = nivel_educativo;
        this.descripcion = descripcion;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_materia;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.nivel_educativo);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        if (this.id_materia != other.id_materia) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nivel_educativo, other.nivel_educativo)) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }

    @Override
    public String toString() {
        return "Materia{" + "id_materia=" + id_materia + ", nombre=" + nombre + ", nivel_educativo=" + nivel_educativo + ", descripcion=" + descripcion + '}';
    }
   
   
}
