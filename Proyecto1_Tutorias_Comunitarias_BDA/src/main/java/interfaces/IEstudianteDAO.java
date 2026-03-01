/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Estudiante;

/**
 *
 * @author jalt2
 */
public interface IEstudianteDAO {
    //CRUD
    boolean insertar(Estudiante estudiante);
    Estudiante obtenerPorId(int idEstudiante);
    List<Estudiante> obtenerTodos();
    List<Estudiante> obtenerTodosPorFiltro(String filtro);
    boolean actualizar(Estudiante estudiante);
    boolean eliminar(int idEstudiante);
}
