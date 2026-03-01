/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Materia;

/**
 *
 * @author jalt2
 */
public interface IMateriaDAO {
    boolean insertar(Materia materia);
    Materia obtenerPorId(int idMateria);
    List<Materia> obtenerTodos();
    List<Materia> obtenerTodosPorFiltro(String filtro);
    boolean actualizar(Materia materia);
    boolean eliminar(int idMateria);
}
