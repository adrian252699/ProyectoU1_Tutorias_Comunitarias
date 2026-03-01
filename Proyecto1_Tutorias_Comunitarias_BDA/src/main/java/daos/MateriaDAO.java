/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import configDB.ConexionDB;
import interfaces.IMateriaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Materia;

/**
 *
 * @author jalt2
 */
public class MateriaDAO implements IMateriaDAO{

    @Override
    public boolean insertar(Materia materia) {
        String sql = """
                     INSERT INTO materia (nombre, nivel_educativo, descripcion) VALUES (?, ?, ?)
                     """;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getNivel_educativo());
            ps.setString(3, materia.getDescripcion());
            
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al insertar materia: " + e.getMessage());
            return false;
        }
        
        
    }

    @Override
    public Materia obtenerPorId(int idMateria) {
        String sql = "SELECT id_materia,nombre, nivel_educativo, descripcion FROM materia WHERE id_materia = ?";
        Materia materia = null;
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setNivel_educativo(rs.getString("nivel_educativo"));
                materia.setDescripcion(rs.getString("descripcion"));
                
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener materia por ID: " + e.getMessage());
        }
        
        return materia;
    }

    @Override
    public List<Materia> obtenerTodos() {
        String sql = "SELECT id_materia,nombre, nivel_educativo, descripcion FROM materia";
        List<Materia> listaMaterias = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setNivel_educativo(rs.getString("nivel_educativo"));
                materia.setDescripcion(rs.getString("descripcion"));
                listaMaterias.add(materia);
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener todas las materias: " + e.getMessage());
        }
        
        return listaMaterias;
    }

    @Override
    public List<Materia> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT id_materia,nombre, nivel_educativo, descripcion FROM materia WHERE nombre LIKE = ?";
        List<Materia> listaMaterias = new ArrayList<>();
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, "%"+filtro+"%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setNivel_educativo(rs.getString("nivel_educativo"));
                materia.setDescripcion(rs.getString("descripcion"));
                listaMaterias.add(materia);
            }
            
        }catch(SQLException e){
            System.err.println("Error al obtener materia por filtro: " + e.getMessage());
        }
        
        return listaMaterias;
    }

    @Override
    public boolean actualizar(Materia materia) {
        String sql = "UPDATE materia SET nombre = ?, nivel_educativo = ?, descripcion = ? WHERE id_materia = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getNivel_educativo());
            ps.setString(3, materia.getDescripcion());
            ps.setInt(4, materia.getId_materia());
            
            
            return ps.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error al actualizar materia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idMateria) {
        
        String sql = "DELETE FROM materia WHERE id_materia = ?";
        
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, idMateria);
            
            return ps.executeUpdate() > 0;
            
            
        }catch(SQLException e){
            System.err.println("Error al eliminar materia: " + e.getMessage());
            return false;
        }
        
    }
    
}
