/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.TutorDAO;
import interfaces.ITutorDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Tutor;

/**
 *
 * @author jalt2
 */
public class TutorController {
    private final ITutorDAO tutorDAO;

    public TutorController() {
        this.tutorDAO = new TutorDAO();
    }

    public boolean insertarTutor(String nombre, String especialidad, String telefono, String correo){
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del tutor no puede estar vacío.");
            return false;
        }
        
        if (especialidad == null || especialidad.trim().isEmpty()) {
            System.err.println("La especialidad del tutor no puede estar vacía.");
            return false;
        }
        
        if (telefono == null || telefono.trim().isEmpty()) {
            System.err.println("El telefono del tutor no puede estar vacío.");
            return false;
        }
        
        if (correo == null || correo.trim().isEmpty()) {
            System.err.println("El correo del tutor no puede estar vacío.");
            return false;
        }
        
        Tutor tutorNuevo = new Tutor(nombre, especialidad, telefono, correo);
        
        return tutorDAO.insertar(tutorNuevo);
    }
    
    public Tutor obtenerTutorID(int idTutor){
        if (idTutor <= 0) {
            System.err.println("ID de tutor inválido.");
            return null;
        }
        return tutorDAO.obtenerPorId(idTutor);
    }
    
    public List<Tutor> listarTutores(){
        return tutorDAO.obtenerTodos();
    }
    
    public boolean actualizarTutor(String nombre, String especialidad, String telefono, String correo){
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del tutor no puede estar vacío.");
            return false;
        }
        
        if (especialidad == null || especialidad.trim().isEmpty()) {
            System.err.println("La especialidad del tutor no puede estar vacía.");
            return false;
        }
        
        if (telefono == null) telefono = "";
        
        if (correo == null) correo = "";
            
        Tutor tutorActualizado = new Tutor(nombre, especialidad, telefono, correo);
        
        return tutorDAO.actualizar(tutorActualizado);
    }
    
    public boolean eliminarTutor(int idTutor){
        if (idTutor <= 0) {
            System.err.println("ID de tutor inválido.");
            return false;
        }
        
        return tutorDAO.eliminar(idTutor);
    }
    
    public DefaultTableModel obtenerTablaTutores() {
        String[] columnas = {"ID", "NOMBRE", "ESPECIALIDAD", "TELEFONO","CORREO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Tutor> lista = tutorDAO.obtenerTodos();
        for (Tutor t : lista) {
            modelo.addRow(new Object[]{t.getId_tutor(),t.getNombre(),t.getEspecialidad(),t.getTelefono(),t.getCorreo()});
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaTutoresFiltro(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "ESPECIALIDAD", "TELEFONO","CORREO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Tutor> lista = tutorDAO.obtenerTodosPorFiltro(filtro);
        for (Tutor t : lista) {
            modelo.addRow(new Object[]{t.getId_tutor(),t.getNombre(),t.getEspecialidad(),t.getTelefono(),t.getCorreo()});
        }
        return modelo;
    }
    
}
