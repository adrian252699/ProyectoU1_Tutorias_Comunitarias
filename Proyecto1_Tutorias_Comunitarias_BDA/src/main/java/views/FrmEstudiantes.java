/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import controllers.EstudianteController;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author jalt2
 */
public class FrmEstudiantes extends javax.swing.JPanel {
    
    private EstudianteController clEstudiante;
    
    /**
     * Creates new form Estudiantes
     */
    public FrmEstudiantes() {
        initComponents();
        clEstudiante = new EstudianteController();
        cargarTablaEstudiantes();
        this.btnEliminar.setVisible(false);
    }
    
    private void guardarEstudiante(){
        try{
            String nombre = txtNombre.getText().trim();
            String gradoEscolar = cmbGradoEscolar.getSelectedItem().toString();
            String escuelaProcedencia = txtEscuela.getText().trim();
            String telefono = txtTelefono.getText().trim();
            Date fechaNacimiento = Date.valueOf(dateFechaNacimiento.getDate());

            if (nombre.isEmpty()||gradoEscolar.isEmpty()||escuelaProcedencia.isEmpty()||telefono.isEmpty()||fechaNacimiento==null) {
                JOptionPane.showMessageDialog(
                            this,
                            "Todos los campos son obligatorios.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
            }

            if (btnGuardar.getText().equals("Guardar")) {
                boolean exito = clEstudiante.insertarEstudiante(nombre, gradoEscolar, escuelaProcedencia, telefono, fechaNacimiento);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Estudiante guardado correctamente.");
                }else{
                    JOptionPane.showMessageDialog(
                                this,
                                "Ocurrio un error al guardar el estudiante",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            }else{
                int id = Integer.parseInt(txtIdEstudiante.getText());

                boolean exito = clEstudiante.actualizarEstudiante(id, nombre, gradoEscolar, escuelaProcedencia, telefono);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente.");
                }else{
                    JOptionPane.showMessageDialog(
                                this,
                                "Ocurrio un error al actualizar los datos del estudiante.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            }
            cargarTablaEstudiantes();
            limpiarCampos();
        }catch(Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void cargarTablaEstudiantes(){
        tblEstudiantes.setModel(clEstudiante.obtenerTablaEstudiantes());
    }
    
    private void limpiarCampos(){
        txtIdEstudiante.setText("0");
        txtNombre.setText("");
        cmbGradoEscolar.setSelectedIndex(0);
        txtEscuela.setText("");
        txtTelefono.setText("");
        btnGuardar.setText("Guardar");
        btnEliminar.setVisible(false);
            
    }
    
    private void cargarCampos(){
        int fila = tblEstudiantes.getSelectedRow();
        if (fila>=0) {
            txtIdEstudiante.setText(tblEstudiantes.getValueAt(fila, 0).toString());
            txtNombre.setText(tblEstudiantes.getValueAt(fila,1).toString());
            cmbGradoEscolar.setSelectedItem(tblEstudiantes.getValueAt(fila,2).toString());
            txtEscuela.setText(tblEstudiantes.getValueAt(fila,3).toString());
            dateFechaNacimiento.setDate(LocalDate.parse(tblEstudiantes.getValueAt(fila,4).toString()));
            txtTelefono.setText(tblEstudiantes.getValueAt(fila,5).toString());
            btnGuardar.setText("Actuailizar");
            btnEliminar.setVisible(true);
        }
    }
    
    private void buscar(){
        String nombre = txtFiltro.getText().trim();
        if (nombre.isEmpty()) {
            cargarTablaEstudiantes();
        }else{
            tblEstudiantes.setModel(clEstudiante.obtenerTablaEstudiantesFiltro(nombre));
        }
    }
    
    private void eliminar(){
        try{
            int id = Integer.parseInt(txtIdEstudiante.getText());
            
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar este Estudiante?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION){
                boolean exito = clEstudiante.eliminarEstudiante(id);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
                    cargarTablaEstudiantes();
                    limpiarCampos(); // limpia y oculta otra vez
                }else{
                    JOptionPane.showMessageDialog(
                            this,
                            "Ocurrio un error al eliminar al estudiante.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdEstudiante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbGradoEscolar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtEscuela = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dateFechaNacimiento = new com.github.lgooddatepicker.components.DatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Menu Estudiantes");

        jLabel2.setText("ID Estudiante:");

        txtIdEstudiante.setEditable(false);
        txtIdEstudiante.setEnabled(false);

        jLabel3.setText("Nombre:");

        jLabel4.setText("Grado Escolar:");

        cmbGradoEscolar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basico", "Superior", "Universitario" }));

        jLabel5.setText("Escuela Procedencia:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Fecha de nacimiento:");

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstudiantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstudiantes);

        jLabel8.setText("Filtrar por nombre:");

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel9.setText("Selecciona uno para editar o eliminar...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEscuela, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbGradoEscolar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtIdEstudiante, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbGradoEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGuardar)
                                    .addComponent(btnCancelar)
                                    .addComponent(btnEliminar)))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarEstudiante();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void tblEstudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstudiantesMouseClicked
        // TODO add your handling code here:
        cargarCampos();
    }//GEN-LAST:event_tblEstudiantesMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbGradoEscolar;
    private com.github.lgooddatepicker.components.DatePicker dateFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTextField txtEscuela;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtIdEstudiante;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
