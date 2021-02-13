
package vistaDolar;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Controlador;
import modelo.Conexion;
import modelo.Dolar;



/**
 *
 * @author ferc
 */
public class vistaDolar extends javax.swing.JFrame {

    DefaultTableModel modeloDolar = new DefaultTableModel();

    private Controlador base = new Controlador();
    private Conexion conexion = null;

    public vistaDolar() {
        
        initComponents();
        cargarColumnas();
        cargarModeloTablaDolar();
        conexion = new Conexion();
        txtId.setVisible(false);

        tablaDolar.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {

                try {

                    if (!event.getValueIsAdjusting() && (tablaDolar.getSelectedRow() >= 0)) {

                    	Dolar dolar = (Dolar) modeloDolar.getValueAt(tablaDolar.getSelectedRow(), 1);

                        txtId.setText(String.valueOf(dolar.getIdDolar()));
                        txtValor.setText(String.valueOf(dolar.getValor()));

                        SimpleDateFormat formatoDelTexto1 = new SimpleDateFormat("yyyy-MM-dd");

                        String fecha1 = dolar.getFecha();
                        
                        if(fecha1.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Campo fecha vacio");
                        }

                        Date dato1 = null;

                        dato1 = formatoDelTexto1.parse(fecha1);

                       // txtFecha.setDate(dato1);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        //txtFecha = new com.toedter.calendar.JDateChooser();
        txtId = new javax.swing.JTextField();
        btnInsertarDolar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDolar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dolar");
        jPanel2.add(jLabel1);
        jPanel2.add(txtValor);

    /*  txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setDateFormatString("yyyy-MM-dd");
        txtFecha.setMaxSelectableDate(null);
        jPanel2.add(txtFecha);
        jPanel2.add(txtId);*/

        btnInsertarDolar.setText("Guardar");
        btnInsertarDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnInsertarDolarActionPerformed(evt);
            }
        });
        jPanel2.add(btnInsertarDolar);

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              //  jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        tablaDolar.setModel(modeloDolar);
        jScrollPane1.setViewportView(tablaDolar);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   /*private void btnInsertarDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarDolarActionPerformed

        double valor = Double.parseDouble(txtValor.getText());
        String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();

        Dolar dolar = new Dolar(valor, fecha);

        base.insertValorDolar(dolar);

        cargarModeloTablaDolar();
    }///GEN-LAST:event_btnInsertarDolarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          double valor = Double.parseDouble(txtValor.getText());
          int id = Integer.parseInt(txtId.getText());
          String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();

        Dolar dolar = new Dolar(id,valor, fecha);

        base.actualizarDolar(dolar);

        cargarModeloTablaDolar();
    }///GEN-LAST:event_jButton2ActionPerformed*/

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cargarColumnas() {

        modeloDolar.addColumn("Id");
        modeloDolar.addColumn("Valor");
        modeloDolar.addColumn("Fecha");

        TableColumn colid = tablaDolar.getColumn("Id");
        TableColumn colPrecio = tablaDolar.getColumn("Valor");
        TableColumn colFecha = tablaDolar.getColumn("Fecha");

        colid.setMaxWidth(120);
        colid.setMinWidth(10);

        colPrecio.setMaxWidth(120);
        colPrecio.setMinWidth(20);

        colFecha.setMaxWidth(200);
        colFecha.setMinWidth(60);

    }

    private void cargarModeloTablaDolar() {

        //System.out.println("HOLA");
    	  ArrayList<Dolar> listaDolar = base.obtenerValoresDolar();
        int numeroDolar = listaDolar.size();
        modeloDolar.setNumRows(numeroDolar);

        for (int i = 0; i < numeroDolar; i++) {

        	Dolar dolar = listaDolar.get(i);
            int id = dolar.getIdDolar();
            double valor = dolar.getValor();
            String fecha = dolar.getFecha();

            modeloDolar.setValueAt(id, i, 0);
            modeloDolar.setValueAt(dolar, i, 1);
            modeloDolar.setValueAt(fecha, i, 2);

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsertarDolar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaDolar;
   // public com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtId;
    public javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}