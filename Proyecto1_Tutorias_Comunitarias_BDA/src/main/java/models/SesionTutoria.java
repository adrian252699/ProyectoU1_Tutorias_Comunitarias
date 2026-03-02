/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author jalt2
 */
public class SesionTutoria {
    private int id_sesion;
    private Date fecha;
    private Time hora;
    private String estado_sesion;
    private int id_tutor;
    private int id_estudiante;
    private int id_materia;
    
    private String nombreTutor;
    private String nombreEstudiante;
    private String nombreMateria;

    public SesionTutoria() {
    }

    public SesionTutoria(int id_sesion, Date fecha, Time hora, String estado_sesion, int id_tutor, int id_estudiante, int id_materia) {
        this.id_sesion = id_sesion;
        this.fecha = fecha;
        this.hora = hora;
        this.estado_sesion = estado_sesion;
        this.id_tutor = id_tutor;
        this.id_estudiante = id_estudiante;
        this.id_materia = id_materia;
    }

    public SesionTutoria(int id_sesion, String estado_sesion) {
        this.id_sesion = id_sesion;
        this.estado_sesion = estado_sesion;
    }
    
    

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEstado_sesion() {
        return estado_sesion;
    }

    public void setEstado_sesion(String estado_sesion) {
        this.estado_sesion = estado_sesion;
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_sesion;
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.hora);
        hash = 97 * hash + Objects.hashCode(this.estado_sesion);
        hash = 97 * hash + this.id_tutor;
        hash = 97 * hash + this.id_estudiante;
        hash = 97 * hash + this.id_materia;
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
        final SesionTutoria other = (SesionTutoria) obj;
        if (this.id_sesion != other.id_sesion) {
            return false;
        }
        if (this.id_tutor != other.id_tutor) {
            return false;
        }
        if (this.id_estudiante != other.id_estudiante) {
            return false;
        }
        if (this.id_materia != other.id_materia) {
            return false;
        }
        if (!Objects.equals(this.estado_sesion, other.estado_sesion)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return Objects.equals(this.hora, other.hora);
    }

    @Override
    public String toString() {
        return "SesionTutoria{" + "id_sesion=" + id_sesion + ", fecha=" + fecha + ", hora=" + hora + ", estado_sesion=" + estado_sesion + ", id_tutor=" + id_tutor + ", id_estudiante=" + id_estudiante + ", id_materia=" + id_materia + '}';
    }
    
    
}
