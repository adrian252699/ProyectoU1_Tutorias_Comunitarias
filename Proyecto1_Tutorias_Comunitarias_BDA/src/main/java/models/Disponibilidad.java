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
public class Disponibilidad {
    private int id_disponibilidad;
    private Date dia;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_tutor;

    public Disponibilidad() {
    }

    public Disponibilidad(int id_disponibilidad, Date dia, Time hora_inicio, Time hora_fin, int id_tutor) {
        this.id_disponibilidad = id_disponibilidad;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_tutor = id_tutor;
    }

    public int getId_disponibilidad() {
        return id_disponibilidad;
    }

    public void setId_disponibilidad(int id_disponibilidad) {
        this.id_disponibilidad = id_disponibilidad;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_disponibilidad;
        hash = 53 * hash + Objects.hashCode(this.dia);
        hash = 53 * hash + Objects.hashCode(this.hora_inicio);
        hash = 53 * hash + Objects.hashCode(this.hora_fin);
        hash = 53 * hash + this.id_tutor;
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
        final Disponibilidad other = (Disponibilidad) obj;
        if (this.id_disponibilidad != other.id_disponibilidad) {
            return false;
        }
        if (this.id_tutor != other.id_tutor) {
            return false;
        }
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        if (!Objects.equals(this.hora_inicio, other.hora_inicio)) {
            return false;
        }
        return Objects.equals(this.hora_fin, other.hora_fin);
    }

    @Override
    public String toString() {
        return "Disponibilidad{" + "id_disponibilidad=" + id_disponibilidad + ", dia=" + dia + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", id_tutor=" + id_tutor + '}';
    }
    
    
}
