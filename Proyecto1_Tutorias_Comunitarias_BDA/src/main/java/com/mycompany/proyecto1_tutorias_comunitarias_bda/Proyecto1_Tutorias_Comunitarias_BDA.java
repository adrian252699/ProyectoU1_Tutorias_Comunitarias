/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1_tutorias_comunitarias_bda;

import configDB.ConexionDB;
import daos.EstudianteDAO;
import interfaces.IEstudianteDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import models.Estudiante;

/**
 *
 * @author jalt2
 */
public class Proyecto1_Tutorias_Comunitarias_BDA {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try (Connection con = ConexionDB.getConnection()) {
            
            if (con != null && !con.isClosed()) {
                System.out.println("✅ Conexión establecida correctamente.");
            } else {
                System.out.println("❌ No se pudo establecer la conexión.");
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
        }
        
        IEstudianteDAO dao = new EstudianteDAO();
        
        // =============================
        // 1️⃣ INSERTAR
        // =============================
        Estudiante nuevo = new Estudiante();
        nuevo.setNombre("Adrian Luzanilla");
        nuevo.setGrado_escolar("3er Año");
        nuevo.setEscuela_procedencia("Secundaria 101");
        nuevo.setTelefono_contacto("6441234567");
        nuevo.setFecha_nacimiento(Date.valueOf("2010-05-15"));

        boolean insertado = dao.insertar(nuevo);
        System.out.println("Insertado: " + insertado);


        // =============================
        // 2️⃣ OBTENER TODOS
        // =============================
        List<Estudiante> lista = dao.obtenerTodos();
        System.out.println("\nLista completa:");
        for (Estudiante e : lista) {
            System.out.println(e.getId_estudiante() + " - " + e.getNombre());
        }


        // =============================
        // 3️⃣ OBTENER POR ID
        // =============================
        Estudiante encontrado = dao.obtenerPorId(2); // cambia el ID si es necesario
        if (encontrado != null) {
            System.out.println("\nEstudiante encontrado:");
            System.out.println(encontrado.getNombre());
        } else {
            System.out.println("\nNo se encontró estudiante");
        }


        // =============================
        // 4️⃣ FILTRAR POR NOMBRE
        // =============================
        List<Estudiante> filtrados = dao.obtenerTodosPorFiltro("Juan");
        System.out.println("\nFiltrados por nombre 'Juan':");
        for (Estudiante e : filtrados) {
            System.out.println(e.getId_estudiante() + " - " + e.getNombre());
        }


        // =============================
        // 5️⃣ ACTUALIZAR
        // =============================
        if (encontrado != null) {
            encontrado.setTelefono_contacto("6449999999");
            boolean actualizado = dao.actualizar(encontrado);
            System.out.println("\nActualizado: " + actualizado);
        }


        // =============================
        // 6️⃣ ELIMINAR
        // =============================
        boolean eliminado = dao.eliminar(1); // cambia el ID
        System.out.println("\nEliminado: " + eliminado);
        
    }
}
