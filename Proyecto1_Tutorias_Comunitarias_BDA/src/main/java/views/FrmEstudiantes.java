package views;

import controllers.EstudianteController;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class FrmEstudiantes extends javax.swing.JPanel {
    
    private EstudianteController clEstudiante;
    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> rowSorter;
    
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
            LocalDate fechaNacimiento = dateFechaNacimiento.getDate();

            if (nombre.isEmpty()||gradoEscolar.isEmpty()||escuelaProcedencia.isEmpty()||telefono.isEmpty()) {
                JOptionPane.showMessageDialog(
                            this,
                            "Todos los campos son obligatorios.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
            }
            
            if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(
                            this,
                            "La fecha no puede ser mayor a la actual",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                return;    
            }
            
            
            if (btnGuardar.getText().equals("Guardar")) {
                boolean exito = clEstudiante.insertarEstudiante(nombre, gradoEscolar, escuelaProcedencia, telefono, Date.valueOf(fechaNacimiento));

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
        javax.swing.table.DefaultTableModel model = clEstudiante.obtenerTablaEstudiantes();
        tblEstudiantes.setModel(model);
        
        rowSorter = new javax.swing.table.TableRowSorter<>(model);
        tblEstudiantes.setRowSorter(rowSorter);
    }
    
    private void limpiarCampos(){
        txtIdEstudiante.setText("0");
        txtNombre.setText("");
        cmbGradoEscolar.setSelectedIndex(0);
        txtEscuela.setText("");
        txtTelefono.setText("");
        dateFechaNacimiento.setText("");
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
        String filtroNombre = txtFiltro.getText().trim();
        String filtroId = txtFiltroId.getText().trim();

        java.util.List<javax.swing.RowFilter<Object, Object>> filters = new java.util.ArrayList<>();

        // Indice 1 es Nombre
        if (!filtroNombre.isEmpty()) {
            filters.add(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(filtroNombre), 1));
        }

        // Indice 0 es ID Estudiante
        if (!filtroId.isEmpty()) {
            filters.add(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(filtroId), 0));
        }

        if (filters.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(javax.swing.RowFilter.andFilter(filters));
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
        jLabelFiltroId = new javax.swing.JLabel();
        txtFiltroId = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU ESTUDIANTES");
        jLabel1.setBackground(new java.awt.Color(37, 99, 235));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

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

        jLabel8.setText("Buscar por nombre:");

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        jLabelFiltroId.setText("Filtrar por ID:");

        txtFiltroId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setBackground(new java.awt.Color(37, 99, 235));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setBackground(new java.awt.Color(255, 51, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtIdEstudiante)
                                    .addComponent(txtNombre)
                                    .addComponent(cmbGradoEscolar, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                                    .addComponent(txtEscuela, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(39, 39, 39))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(24, 24, 24)
                                    .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnGuardar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnCancelar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnEliminar)))
                                    .addGap(18, 18, 18))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFiltroId)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFiltroId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelFiltroId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltroId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbGradoEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(50, 50, 50))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)
                            .addComponent(btnEliminar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabelFiltroId;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTextField txtEscuela;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtFiltroId;
    private javax.swing.JTextField txtIdEstudiante;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
