/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.MateriaDAO;
import interfaces.IMateriaDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Materia;

/**
 *
 * @author jalt2
 */
public class MateriaController {
    private final IMateriaDAO materiaDAO;

    public MateriaController() {
        this.materiaDAO = new MateriaDAO();
    }
    
    public boolean insertarMateria(String nombre, String nivel_educativo, String descripcion){
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre de la materia no puede estar vacío.");
            return false;
        }
        
        if (nivel_educativo == null || nivel_educativo.trim().isEmpty()) {
            System.err.println("El nivel educativo no puede estar vacío.");
            return false;
        }
        
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.err.println("La descripcion no puede estar vacía.");
            return false;
        }
        
        Materia materiaNueva = new Materia(nombre, nivel_educativo, descripcion);
        
        return materiaDAO.insertar(materiaNueva);
        
        
    }
    
    public Materia obtenerMateriaID(int idMateria){
        if (idMateria <= 0) {
            System.err.println("ID de materia inválido.");
            return null;
        }
        
        return materiaDAO.obtenerPorId(idMateria);
    }
    
    public List<Materia> listarMaterias(){
        return materiaDAO.obtenerTodos();
    }
    
    public boolean actualizarMateria(int idMateria,String nombre, String nivel_educativo, String descripcion){
        if (idMateria <= 0) {
            System.err.println("ID de materia inválido.");
            return false;
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre de la materia no puede estar vacío.");
            return false;
        }
        
        if (nivel_educativo == null || nivel_educativo.trim().isEmpty()) {
            System.err.println("El nivel educativo no puede estar vacío.");
            return false;
        }
        
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.err.println("La descripcion no puede estar vacía.");
            return false;
        }
        
        Materia materiaActualizada = new Materia(idMateria, nombre, nivel_educativo, descripcion);
        
        return materiaDAO.actualizar(materiaActualizada);
    }
    
    public boolean eliminarMateria(int idMateria){
        if (idMateria <= 0) {
            System.err.println("ID de materia inválido.");
            return false;
        }
        
        return materiaDAO.eliminar(idMateria);
    }
    
    public DefaultTableModel obtenerTablaMaterias() {
        String[] columnas = {"ID", "NOMBRE", "NIVEL EDUCATIVO", "DESCRIPCION"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Materia> lista = materiaDAO.obtenerTodos();
        for (Materia m : lista) {
            modelo.addRow(new Object[]{m.getId_materia(),m.getNombre(),m.getNivel_educativo(),m.getDescripcion()});
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaMateriasFiltro(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "NIVEL EDUCATIVO", "DESCRIPCION"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Materia> lista = materiaDAO.obtenerTodosPorFiltro(filtro);
        for (Materia m : lista) {
            modelo.addRow(new Object[]{m.getId_materia(),m.getNombre(),m.getNivel_educativo(),m.getDescripcion()});
        }
        return modelo;
    }
}
