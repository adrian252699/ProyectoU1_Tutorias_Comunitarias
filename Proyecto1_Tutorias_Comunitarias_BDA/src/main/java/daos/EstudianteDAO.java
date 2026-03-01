/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import configDB.ConexionDB;
import interfaces.IEstudianteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Estudiante;

/**
 *
 * @author jalt2
 */
public class EstudianteDAO implements IEstudianteDAO{

    public EstudianteDAO() {
    }
    
    
    
    @Override
    public boolean insertar(Estudiante estudiante) {
        String sql = """
                     INSERT INTO estudiante (nombre, grado_escolar, escuela_procedencia,telefono_contacto,fecha_nacimiento) VALUES (?, ?, ?, ?, ?)
                     """;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getGrado_escolar());
            ps.setString(3, estudiante.getEscuela_procedencia());
            ps.setString(4, estudiante.getTelefono_contacto());
            ps.setDate(5, estudiante.getFecha_nacimiento());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al insertar estudiante: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Estudiante obtenerPorId(int idEstudiante) {
        String sql = "SELECT id_estudiante,nombre, grado_escolar, escuela_procedencia,telefono_contacto,fecha_nacimiento FROM estudiante WHERE id_estudiante = ?";
        Estudiante estudiante = null;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setId_estudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setGrado_escolar(rs.getString("grado_escolar"));
                estudiante.setEscuela_procedencia(rs.getString("escuela_procedencia"));
                estudiante.setTelefono_contacto(rs.getString("telefono_contacto"));
                estudiante.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener estudiante por ID: " + e.getMessage());
        }
        
        return estudiante;
        
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        String sql = "SELECT id_estudiante,nombre, grado_escolar, escuela_procedencia,telefono_contacto,fecha_nacimiento FROM estudiante";
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId_estudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setGrado_escolar(rs.getString("grado_escolar"));
                estudiante.setEscuela_procedencia(rs.getString("escuela_procedencia"));
                estudiante.setTelefono_contacto(rs.getString("telefono_contacto"));
                estudiante.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                listaEstudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los estudiantes: " + e.getMessage());
        }
        return listaEstudiantes;
    }

    @Override
    public List<Estudiante> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT id_estudiante,nombre, grado_escolar, escuela_procedencia,telefono_contacto,fecha_nacimiento FROM estudiante WHERE nombre LIKE ?";
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            
            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId_estudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setGrado_escolar(rs.getString("grado_escolar"));
                estudiante.setEscuela_procedencia(rs.getString("escuela_procedencia"));
                estudiante.setTelefono_contacto(rs.getString("telefono_contacto"));
                estudiante.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                listaEstudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los estudiantes con filtro: " + e.getMessage());
        }
        return listaEstudiantes;
        
    }

    @Override
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, grado_escolar = ?, escuela_procedencia = ?, telefono_contacto = ? WHERE id_estudiante = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getGrado_escolar());
            ps.setString(3, estudiante.getEscuela_procedencia());
            ps.setString(4, estudiante.getTelefono_contacto());
            ps.setInt(5, estudiante.getId_estudiante());
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idEstudiante) {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idEstudiante);
            
            return ps.executeUpdate() > 0;
            
            
        }catch(SQLException e){
            System.err.println("Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }
    
}
