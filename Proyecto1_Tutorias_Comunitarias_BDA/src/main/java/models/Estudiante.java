/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author jalt2
 */
public class Estudiante {
    private int id_estudiante;
    private String nombre;
    private String grado_escolar;
    private String escuela_procedencia;
    private String telefono_contacto;
    private Date fecha_nacimiento;

    public Estudiante() {
    }

    public Estudiante(int id_estudiante, String nombre, String grado_escolar, String escuela_procedencia, String telefono_contacto, Date fecha_nacimiento) {
        this.id_estudiante = id_estudiante;
        this.nombre = nombre;
        this.grado_escolar = grado_escolar;
        this.escuela_procedencia = escuela_procedencia;
        this.telefono_contacto = telefono_contacto;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Estudiante(String nombre, String grado_escolar, String escuela_procedencia, String telefono_contacto, Date fecha_nacimiento) {
        this.nombre = nombre;
        this.grado_escolar = grado_escolar;
        this.escuela_procedencia = escuela_procedencia;
        this.telefono_contacto = telefono_contacto;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado_escolar() {
        return grado_escolar;
    }

    public void setGrado_escolar(String grado_escolar) {
        this.grado_escolar = grado_escolar;
    }

    public String getEscuela_procedencia() {
        return escuela_procedencia;
    }

    public void setEscuela_procedencia(String escuela_procedencia) {
        this.escuela_procedencia = escuela_procedencia;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_estudiante;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.grado_escolar);
        hash = 29 * hash + Objects.hashCode(this.escuela_procedencia);
        hash = 29 * hash + Objects.hashCode(this.telefono_contacto);
        hash = 29 * hash + Objects.hashCode(this.fecha_nacimiento);
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
        final Estudiante other = (Estudiante) obj;
        if (this.id_estudiante != other.id_estudiante) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.grado_escolar, other.grado_escolar)) {
            return false;
        }
        if (!Objects.equals(this.escuela_procedencia, other.escuela_procedencia)) {
            return false;
        }
        if (!Objects.equals(this.telefono_contacto, other.telefono_contacto)) {
            return false;
        }
        return Objects.equals(this.fecha_nacimiento, other.fecha_nacimiento);
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id_estudiante=" + id_estudiante + ", nombre=" + nombre + ", grado_escolar=" + grado_escolar + ", escuela_procedencia=" + escuela_procedencia + ", telefono_contacto=" + telefono_contacto + ", fecha_nacimiento=" + fecha_nacimiento + '}';
    }
    
    
}
