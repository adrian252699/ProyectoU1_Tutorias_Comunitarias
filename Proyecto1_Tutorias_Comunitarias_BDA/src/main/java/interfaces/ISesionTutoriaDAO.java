/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.Date;
import java.sql.Time;
import models.SesionTutoria;

/**
 *
 * @author jalt2
 */
public interface ISesionTutoriaDAO {
    boolean programarTutoria(Date fecha, Time hora, String estado, int id_tutor, int id_estudiante, int id_materia);
    boolean cambiarEstadoTutoria(SesionTutoria sesion);
    
}
