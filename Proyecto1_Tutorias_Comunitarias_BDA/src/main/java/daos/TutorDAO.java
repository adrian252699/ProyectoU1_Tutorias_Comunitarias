/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import configDB.ConexionDB;
import interfaces.ITutorDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import models.Disponibilidad;
import models.Tutor;

/**
 *
 * @author jalt2
 */
public class TutorDAO implements ITutorDAO{

    @Override
    public boolean insertar(Tutor tutor) {
        String sql = """
                     INSERT INTO tutor (nombre, especialidad, telefono, correo) VALUES (?, ?, ?, ?)
                     """;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, tutor.getNombre());
            ps.setString(2, tutor.getEspecialidad());
            ps.setString(3, tutor.getCorreo());
            ps.setString(4, tutor.getTelefono());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al insertar tutor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Tutor obtenerPorId(int idTutor) {
        String sql = "SELECT id_tutor,nombre, especialidad, correo,telefono FROM tutor WHERE id_tutor = ?";
        Tutor tutor = null;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idTutor);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tutor = new Tutor();
                tutor.setId_tutor(rs.getInt("id_tutor"));
                tutor.setNombre(rs.getString("nombre"));
                tutor.setEspecialidad(rs.getString("especialidad"));
                tutor.setCorreo(rs.getString("correo"));
                tutor.setTelefono(rs.getString("telefono"));
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener tutor por ID: " + e.getMessage());
        }
        
        return tutor;
    }

    @Override
    public List<Tutor> obtenerTodos() {
        String sql = "SELECT id_tutor,nombre, especialidad, correo,telefono FROM tutor";
        List<Tutor> listaTutores = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Tutor tutor = new Tutor();
                tutor.setId_tutor(rs.getInt("id_tutor"));
                tutor.setNombre(rs.getString("nombre"));
                tutor.setEspecialidad(rs.getString("especialidad"));
                tutor.setCorreo(rs.getString("correo"));
                tutor.setTelefono(rs.getString("telefono"));
                listaTutores.add(tutor);
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener todos los tutores: " + e.getMessage());
        }
        return listaTutores;
        
    }

    @Override
    public List<Tutor> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT id_tutor,nombre, especialidad, correo,telefono FROM tutor WHERE nombre LIKE ?";
        List<Tutor> listaTutores = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Tutor tutor = new Tutor();
                tutor.setId_tutor(rs.getInt("id_tutor"));
                tutor.setNombre(rs.getString("nombre"));
                tutor.setEspecialidad(rs.getString("especialidad"));
                tutor.setCorreo(rs.getString("correo"));
                tutor.setTelefono(rs.getString("telefono"));
                listaTutores.add(tutor);
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener tutor por filtro: " + e.getMessage());
        }
        
        return listaTutores;
        
    }

    @Override
    public boolean actualizar(Tutor tutor) {
        String sql = "UPDATE tutor SET nombre = ?, especialidad = ?, correo = ?, telefono = ? WHERE id_tutor = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, tutor.getNombre());
            ps.setString(2, tutor.getEspecialidad());
            ps.setString(3, tutor.getCorreo());
            ps.setString(4, tutor.getTelefono());
            ps.setInt(5, tutor.getId_tutor());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al actualizar tutor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idTutor) {
        String sql = "DELETE FROM tutor WHERE id_tutor = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idTutor);
            
            return ps.executeUpdate() > 0;
            
            
        }catch(SQLException e){
            System.err.println("Error al eliminar tutor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean agregarDisponibilidad(Disponibilidad disponibilidad) {
        String sql = """
                     INSERT INTO disponibilidad (dia, hora_inicio, hora_fin, id_tutor) VALUES (?, ?, ?, ?)
                     """;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, disponibilidad.getDia());
            ps.setTime(2, disponibilidad.getHora_inicio());
            ps.setTime(3, disponibilidad.getHora_fin());
            ps.setInt(4, disponibilidad.getId_tutor());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al agregar disponibilidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Disponibilidad> consultarDisponibilidades(int idTutor) {
        String sql = "SELECT id_dsiponibilidad,dia, hora_inicio, hora_fin,id_tutor FROM disponibilidad WHERE id_tutor = ?";
        List<Disponibilidad> listaDisponibilidadesTutor = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idTutor);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Disponibilidad disp = new Disponibilidad();
                disp.setId_disponibilidad(rs.getInt("id_disponibilidad"));
                disp.setDia(rs.getDate("dia"));
                disp.setHora_inicio(rs.getTime("hora_inicio"));
                disp.setHora_fin(rs.getTime("hora_fin"));
                disp.setId_tutor(rs.getInt("id_tutor"));
                listaDisponibilidadesTutor.add(disp);
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener las disponibilidades del tutor: " + e.getMessage());
        }
        
        return listaDisponibilidadesTutor;
    }

    @Override
    public boolean modificarDisponibilidad(Disponibilidad disponibilidad) {
        String sql = "UPDATE disponibilidad SET dia = ?, hora_inicio = ?, hora_fin = ?, WHERE id_disponibilidad = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, disponibilidad.getDia());
            ps.setTime(2, disponibilidad.getHora_inicio());
            ps.setTime(3, disponibilidad.getHora_fin());
            ps.setInt(4, disponibilidad.getId_disponibilidad());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al modificar disponibilidad: " + e.getMessage());
            return false;
        }
    }

}
