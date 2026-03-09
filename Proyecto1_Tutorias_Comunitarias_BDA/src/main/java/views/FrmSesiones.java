package views;

import controllers.EstudianteController;
import controllers.MateriaController;
import controllers.SesionTutoriaController;
import controllers.TutorController;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import models.Estudiante;
import models.Materia;
import models.SesionTutoria;
import models.Tutor;

/**
 *
 * @author Ricardo
 */
public class FrmSesiones extends javax.swing.JPanel {
    
    SesionTutoriaController seController;
    private final EstudianteController clEstudiante;
    private Estudiante estudiante;
    private final TutorController tuController;
    private Tutor tutor;
    private final MateriaController maController;
    private Materia materia;
    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> rowSorter;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JLabel lblFiltro;
    
    /**
     * Creates new form FrmSesiones
     */
    public FrmSesiones() {
        initComponents();
        clEstudiante= new EstudianteController();
        seController = new SesionTutoriaController();
        tuController = new TutorController();
        maController = new MateriaController();
        btnEliminar.setVisible(false);
        cargarTablaSesiones();
    }
    
    private void cargarTablaSesiones() {
        String[] columnas = {"ID", "FECHA", "HORA", "ESTADO SESION", "TUTOR", "ESTUDIANTE", "MATERIA"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        List<SesionTutoria> lista = seController.listarSesiones();
        System.out.println("Cantidad de sesiones: " + lista.size());

        for (SesionTutoria s : lista) {
            modelo.addRow(new Object[]{
                s.getId_sesion(),
                s.getFecha(),
                s.getHora(),
                s.getEstado_sesion(),
                s.getNombreTutor(),
                s.getNombreEstudiante(),
                s.getNombreMateria()
            });
        }   

        tblSesiones.setModel(modelo);
        
        rowSorter = new javax.swing.table.TableRowSorter<>(modelo);
        tblSesiones.setRowSorter(rowSorter);
    }
    
    private void buscar(){
        String texto = txtFiltro.getText().trim();
        if (texto.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            // (?i) makes the regex case-insensitive
            rowSorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto)));
        }
    }
    
    private void guardarMateria(){
        try{
            
        if (dateSesion.getDate() == null || timeSesion.getTime() == null) {
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar fecha y hora.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
            
        Date fecha = Date.valueOf(dateSesion.getDate());
        Time hora = Time.valueOf(timeSesion.getTime());            
        String estado = cmbEstado.getSelectedItem().toString();
        
            

            if (estado.isEmpty()||fecha==null||hora==null) {
                JOptionPane.showMessageDialog(
                            this,
                            "Todos los campos son obligatorios.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
            }

            if (btnGuardar.getText().equals("Guardar")) {
                int idTutor = this.getTutor().getId_tutor();
                int idEstudiante = this.getEstudiante().getId_estudiante();
                int idMateria = this.getMateria().getId_materia();
                
                if (idTutor<=0||idEstudiante<=0||idMateria<=0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Todos los campos son obligatorios.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                    
                }
                
                boolean exito = seController.programarSesion(fecha, hora, estado,idTutor,idEstudiante,idMateria);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Sesion guardada correctamente.");
                }else{
                    JOptionPane.showMessageDialog(
                                this,
                                "Ocurrio un error al guardar la sesion",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            }else{
                //Actualizar
                int id = Integer.parseInt(txtIdSesion.getText());
                
                

                boolean exito = seController.cambiarEstadoSesion(id, estado);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Sesion actualizada correctamente.");
                }else{
                    JOptionPane.showMessageDialog(
                                this,
                                "Ocurrio un error al actualizar los datos de la sesion.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            }
            cargarTablaSesiones();
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
    
    private void limpiarCampos(){
        txtIdSesion.setText("0");
        dateSesion.setText("");
        timeSesion.setText("");
        cmbEstado.setSelectedIndex(0);
        txtTutor.setText("");
        txtEstudiante.setText("");
        txtMateria.setText("");
        btnGuardar.setText("Guardar");
        btnEliminar.setVisible(false);
    }
        
    private void cargarCampos(){
        int fila = tblSesiones.getSelectedRow();
        if (fila>=0) {
            txtIdSesion.setText(tblSesiones.getValueAt(fila, 0).toString());
            dateSesion.setDate(LocalDate.parse(tblSesiones.getValueAt(fila,1).toString()));
            timeSesion.setTime(LocalTime.parse(tblSesiones.getValueAt(fila,2).toString()));
            cmbEstado.setSelectedItem(tblSesiones.getValueAt(fila,3).toString());
            txtTutor.setText(tblSesiones.getValueAt(fila, 4).toString());
            txtEstudiante.setText(tblSesiones.getValueAt(fila, 5).toString());
            txtMateria.setText(tblSesiones.getValueAt(fila, 6).toString());
            
            btnGuardar.setText("Actuailizar");
            btnEliminar.setVisible(true);
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
        txtIdSesion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateSesion = new com.github.lgooddatepicker.components.DatePicker();
        timeSesion = new com.github.lgooddatepicker.components.TimePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtTutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEstudiante = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMateria = new javax.swing.JTextField();
        btnBuscarMateria = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        scrContenido = new javax.swing.JScrollPane();
        tblSesiones = new javax.swing.JTable();
        btnBuscarEstudiante = new javax.swing.JButton();
        btnBuscarTutor = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU SESIONES");
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(37, 99, 235));

        jLabel2.setText("ID Sesion:");

        txtIdSesion.setEnabled(false);

        jLabel3.setText("Fecha:");

        jLabel4.setText("Hora:");

        jLabel5.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En curso", "Completada", "Programada" }));

        jLabel6.setText("Tutor:");

        jLabel7.setText("Estudiante:");

        jLabel8.setText("Materia:");

        btnBuscarMateria.setText("Buscar");
        btnBuscarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMateriaActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setBackground(new java.awt.Color(37, 99, 235));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(75, 85, 99));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setBackground(new java.awt.Color(220, 38, 38));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));

        tblSesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSesionesMouseClicked(evt);
            }
        });
        scrContenido.setViewportView(tblSesiones);

        btnBuscarEstudiante.setText("Buscar");
        btnBuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstudianteActionPerformed(evt);
            }
        });

        btnBuscarTutor.setText("Buscar");
        btnBuscarTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTutorActionPerformed(evt);
            }
        });

        jLabel9.setText("Lista de sesiones");
        jLabel9.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarTutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEstudiante))
                            .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTutor))))
                        .addGap(61, 61, 61)
                        .addComponent(scrContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdSesion)
                            .addComponent(timeSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(261, 261, 261)
                        .addComponent(jLabel9)
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnGuardar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnCancelar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminar))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtMateria)
                                    .addGap(656, 656, 656))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(36, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdSesion)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarTutor)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarEstudiante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarMateria)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar))
                .addGap(101, 101, 101))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSesionesMouseClicked
        // TODO add your handling code here:
        cargarCampos();
    }//GEN-LAST:event_tblSesionesMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarMateria();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstudianteActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DlgEstudiante dlg = new DlgEstudiante(frame, true); // modal
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true); // esto bloquea hasta cerrar el diálogo

        // Después de cerrar, obtener el cliente seleccionado
        Integer idEstudiante = dlg.getEstudianteSeleccionado();
        if (idEstudiante != null) {
            this.estudiante = clEstudiante.obtenerEstudianteID(idEstudiante);
            txtEstudiante.setText(this.estudiante.getNombre());
        }
    }//GEN-LAST:event_btnBuscarEstudianteActionPerformed

    private void btnBuscarTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTutorActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DlgTutor dlg = new DlgTutor(frame, true); // modal
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true); // esto bloquea hasta cerrar el diálogo

        // Después de cerrar, obtener el cliente seleccionado
        Integer idTutor = dlg.getTutorSeleccionado();
        if (idTutor != null) {
            this.tutor = tuController.obtenerTutorID(idTutor);
            txtTutor.setText(this.tutor.getNombre());
        }
    }//GEN-LAST:event_btnBuscarTutorActionPerformed

    private void btnBuscarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMateriaActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DlgMateria dlg = new DlgMateria(frame, true); // modal
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true); // esto bloquea hasta cerrar el diálogo

        // Después de cerrar, obtener el cliente seleccionado
        Integer idMateria = dlg.getMateriaSeleccionada();
        if (idMateria != null) {
            this.materia = maController.obtenerMateriaID(idMateria);
            txtMateria.setText(this.materia.getNombre());
        }
    }//GEN-LAST:event_btnBuscarMateriaActionPerformed

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Materia getMateria() {
        return materia;
    }
    
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEstudiante;
    private javax.swing.JButton btnBuscarMateria;
    private javax.swing.JButton btnBuscarTutor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbEstado;
    private com.github.lgooddatepicker.components.DatePicker dateSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane scrContenido;
    private javax.swing.JTable tblSesiones;
    private com.github.lgooddatepicker.components.TimePicker timeSesion;
    private javax.swing.JTextField txtEstudiante;
    private javax.swing.JTextField txtIdSesion;
    private javax.swing.JTextField txtMateria;
    private javax.swing.JTextField txtTutor;
    // End of variables declaration//GEN-END:variables
}
