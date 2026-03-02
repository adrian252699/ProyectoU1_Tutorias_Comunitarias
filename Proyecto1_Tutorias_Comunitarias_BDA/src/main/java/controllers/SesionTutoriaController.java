/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.SesionTutoriaDAO;
import interfaces.ISesionTutoriaDAO;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import models.SesionTutoria;

/**
 *
 * @author jalt2
 */
public class SesionTutoriaController {
    private final ISesionTutoriaDAO sesionDAO;
    

    public SesionTutoriaController() {
        this.sesionDAO = new SesionTutoriaDAO();
    }
    
    public boolean programarSesion(Date fecha, Time hora, String estado, int idTutor, int idEstudiante, int idMateria){
        if (fecha==null || fecha.toString().trim().isEmpty()) {
            System.err.println("La fecha de la sesion no puede estar vacía.");
            return false;
        }
        
        if (hora==null || hora.toString().trim().isEmpty()) {
            System.err.println("La hora de la sesion no puede estar vacía.");
            return false;
        }
        
        if (estado==null || estado.trim().isEmpty()) {
            System.err.println("El estado de la sesion no puede estar vacía.");
            return false;
        }
        
        if (idTutor <= 0) {
            System.err.println("ID de tutor inválido.");
            return false;
        }
        
        if (idEstudiante <= 0) {
            System.err.println("ID de Estudiante inválido.");
            return false;
        }
        
        if (idMateria <= 0) {
            System.err.println("ID de Materia inválido.");
            return false;
        }
        
        return sesionDAO.programarTutoria(fecha, hora, estado, idTutor, idEstudiante, idMateria);
    }
    
    public boolean cambiarEstadoSesion(int id,String estado){
        
//        if (sesion == null) {
//            System.err.println("La sesion no puede estar vacía.");
//        }
        
        
        
        return sesionDAO.cambiarEstadoTutoria(id,estado);
    }
    
    public List<SesionTutoria> listarSesiones(){
        return sesionDAO.obtenerTodos();
    }
    
//    public DefaultTableModel obtenerTablaSesiones() {
//        String[] columnas = {"ID", "FECHA", "HORA", "ESTADO SESION", "TUTOR", "ESTUDIANTE", "MATERIA"};
//        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
//        List<SesionTutoria> lista = sesionDAO.obtenerTodos();
//        for (SesionTutoria s : lista) {
//            modelo.addRow(new Object[]{s.getId_sesion(),s.getFecha(),s.getHora(),s.getEstado_sesion(),frmSesiones.getTutor().getNombre(),frmSesiones.getEstudiante().getNombre(),frmSesiones.getMateria().getNombre()});
//        }
//        return modelo;
//    }
    
}
