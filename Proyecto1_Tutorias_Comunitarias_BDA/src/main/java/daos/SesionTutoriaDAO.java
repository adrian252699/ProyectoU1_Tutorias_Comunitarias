/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import configDB.ConexionDB;
import interfaces.ISesionTutoriaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import models.SesionTutoria;

/**
 *
 * @author jalt2
 */
public class SesionTutoriaDAO implements ISesionTutoriaDAO{

    @Override
    public boolean programarTutoria(Date fecha, Time hora, String estado, int id_tutor, int id_estudiante, int id_materia) {
        String sql = """
                     INSERT INTO sesiontutoria (fecha, hora, estado_sesion,id_tutor,id_estudiante,id_materia) VALUES (?, ?, ?, ?, ?, ?)
                     """;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, fecha);
            ps.setTime(2, hora);
            ps.setString(3, "programada");
            ps.setInt(4, id_tutor);
            ps.setInt(5, id_estudiante);
            ps.setInt(6, id_materia);
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al programar sesion de tutoria: " + e.getMessage());
            return false;
        }
    }
    
    //Metodo para cambiar estado de la sesion de programada a en curso, o de en curso a completada
    @Override
    public boolean cambiarEstadoTutoria(SesionTutoria sesion) {
        String sql = "UPDATE sesiontutoria SET estado_sesion = ? WHERE id_sesion = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, sesion.getEstado_sesion());
            ps.setInt(2, sesion.getId_sesion());

            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al actualizar sesion de tutoria: " + e.getMessage());
            return false;
        }
    }
    
}
