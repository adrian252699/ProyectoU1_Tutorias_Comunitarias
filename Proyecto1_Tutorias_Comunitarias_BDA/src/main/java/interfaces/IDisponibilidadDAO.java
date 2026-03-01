/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jalt2
 */
public interface IDisponibilidadDAO {
    boolean agregarDisponibilidad(Date dia, Time horaInicio, Time horaFin, int idTutor);
}
