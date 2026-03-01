/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.EstudianteDAO;
import interfaces.IEstudianteDAO;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Estudiante;

/**
 *
 * @author jalt2
 */
public class EstudianteController {
    private final IEstudianteDAO estudianteDAO;

    public EstudianteController() {
        this.estudianteDAO = new EstudianteDAO();
    }
    
    public boolean insertarEstudiante(String nombre, String grado_escolar, String escuela_procedencia, String telefono, Date fecha_nacimiento){
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del estudiante no puede estar vacío.");
            return false;
        }
        
        if (grado_escolar == null || grado_escolar.trim().isEmpty()) {
            System.err.println("El grado escolar del estudiante no puede estar vacío.");
            return false;
        }
        
        if (escuela_procedencia == null || escuela_procedencia.trim().isEmpty()) {
            System.err.println("El estudiante debe provenir de una escuela.");
            return false;
        }
        
        if (telefono == null || telefono.trim().isEmpty()) {
            System.err.println("El estudiante debe tener una forma de contacto.");
            return false;
        }
        
        if (fecha_nacimiento == null || fecha_nacimiento.toString().trim().isEmpty()) {
            System.err.println("El estudiante debe tener una fecha de nacimiento.");
            return false;
        }
        
        Estudiante estudianteNuevo = new Estudiante(nombre, grado_escolar, escuela_procedencia, telefono, fecha_nacimiento);
        
        return estudianteDAO.insertar(estudianteNuevo);
    }
    
    public Estudiante obtenerEstudianteID(int idEstudiante){
        if (idEstudiante <= 0) {
            System.err.println("ID de estudiante inválido.");
            return null;
        }
        return estudianteDAO.obtenerPorId(idEstudiante);
    }
    
    public List<Estudiante> listarEstudiantes(){
        return estudianteDAO.obtenerTodos();
    }
    
    public boolean actualizarEstudiante(int idEstudiante ,String nombre, String grado_escolar, String escuela_procedencia, String telefono){
        if (idEstudiante <= 0) {
            System.err.println("ID de estudiante inválido.");
            return false;
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del estudiante no puede estar vacío.");
            return false;
        }
        
        if (grado_escolar == null || grado_escolar.trim().isEmpty()) {
            System.err.println("El grado escolar del estudiante no puede estar vacío.");
            return false;
        }
        
        if (escuela_procedencia == null || escuela_procedencia.trim().isEmpty()) {
            System.err.println("El estudiante debe provenir de una escuela.");
            return false;
        }
        
        if(telefono == null) telefono = "";
        
        Estudiante estudianteActualizar = new Estudiante();
        estudianteActualizar.setNombre(nombre);
        estudianteActualizar.setGrado_escolar(grado_escolar);
        estudianteActualizar.setEscuela_procedencia(escuela_procedencia);
        estudianteActualizar.setTelefono_contacto(telefono);
        estudianteActualizar.setId_estudiante(idEstudiante);
        
        return estudianteDAO.actualizar(estudianteActualizar);
        
    }
    
    public boolean eliminarEstudiante(int idEstudiante){
        if (idEstudiante <= 0) {
            System.err.println("ID de estudiante inválido.");
            return false;
        }
        
        return estudianteDAO.eliminar(idEstudiante);
    }
    
    public DefaultTableModel obtenerTablaEstudiantes() {
        String[] columnas = {"ID", "NOMBRE", "GRADO ESCOLAR", "ESCUELA PROCEDENCIA","FECHA NACIMIENTO", "TELEFONO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Estudiante> lista = estudianteDAO.obtenerTodos();
        for (Estudiante e : lista) {
            modelo.addRow(new Object[]{e.getId_estudiante(), e.getNombre(), e.getGrado_escolar(), e.getEscuela_procedencia(),e.getFecha_nacimiento(),e.getTelefono_contacto()});
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaEstudiantesFiltro(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "GRADO ESCOLAR", "ESCUELA PROCEDENCIA","FECHA NACIMIENTO", "TELEFONO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Estudiante> lista = estudianteDAO.obtenerTodosPorFiltro(filtro);
        for (Estudiante e : lista) {
            modelo.addRow(new Object[]{e.getId_estudiante(), e.getNombre(), e.getGrado_escolar(), e.getEscuela_procedencia(),e.getFecha_nacimiento(),e.getTelefono_contacto()});
        }
        return modelo;
    }
}
