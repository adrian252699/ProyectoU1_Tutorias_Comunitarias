/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Tutor;

/**
 *
 * @author jalt2
 */
public interface ITutorDAO {
    boolean insertar(Tutor tutor);
    Tutor obtenerPorId(int idTutor);
    List<Tutor> obtenerTodos();
    List<Tutor> obtenerTodosPorFiltro(String filtro);
    boolean actualizar(Tutor tutor);
    boolean eliminar(int idTutor);
}
