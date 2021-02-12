package vistaVentas;

import java.sql.*;
import modelo.Categoria;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Ventas;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import controlador.BaseDatos;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import modelo.Clientes;
import modelo.Conexion;
import modelo.Presupuesto;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class vistaFrame extends javax.swing.JFrame {

    DefaultTableModel modeloTablaProductosArriba = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;

        }
    };

    DefaultTableModel modeloTablaProductosAbajo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;

        }
    };

    Producto productoSeleccionado = null;
    Categoria categoriaSeleccionada = null;
    Proveedor proveedorSeleccionado = null;
    String categoria = "";
    String proveedor = "";
    Connection conn = null;
    Conexion Conexion = null;
    private final double IVA = 0.21;
    DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();

    DefaultListModel<Producto> modeloListaProductos = new DefaultListModel<Producto>();
    BaseDatos base = new BaseDatos();
    int filaSeleccionada;
    boolean Seleccion = false;
    private double sumatoria = 0;
    private double importeTotal = 0;
    private DecimalFormat df = new DecimalFormat("0.00");

    public vistaFrame() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        initComponents();
        Conexion = new Conexion();   
        cargarColumnasTablaAbajo();
        cargarColumnasTablaArriba();
        cargarModeloTablaArriba();        
        cargarClientesEnComboBox();

        AutoCompleteDecorator.decorate(comboClientes);
        jPanel3.setLayout(null);
        jPanel3.add(jPanel1);
        jPanel3.add(jPanel7);
        jPanel3.add(jPanel6);
        jPanel3.add(jScrollPane1);
        jPanel3.add(btnAceptar);
        jPanel3.add(btnCancelar);
        jPanel3.add(btnQuitarProd);
        
        
    

    
    tablaArriba.setModel(modeloTablaProductosArriba);
    tablaArriba.getSelectionModel().addListSelectionListener(

        new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event){

                if(!event.getValueIsAdjusting() &&  (tablaArriba.getSelectedRow()>=0)){
                    Producto  producto =(Producto)modeloTablaProductosArriba.getValueAt(tablaArriba.getSelectedRow() , 1);
                    desplegarFoto(producto);
                    productoSeleccionado = producto;

                }

            }
        }
    );
    tablaArriba.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // NOI18N
    tablaArriba.setModel(modeloTablaProductosArriba);
    tablaArriba.setToolTipText("");
    tablaArriba.setGridColor(new java.awt.Color(204, 204, 204));
    tablaArriba.setRowHeight(25);
    tablaArriba.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tablaArriba.setShowGrid(false);
    lblSumatoria = new javax.swing.JTextField();
    lblSumatoria.setBounds(750, 316, 200, 63);
    jPanel3.add(lblSumatoria);
    
            lblSumatoria.setFont(new java.awt.Font("DejaVu Sans", 0, 36)); // NOI18N
            lblSumatoria.setForeground(Color.RED);
            lblSumatoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            lblSumatoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblSumatoria.setPreferredSize(new java.awt.Dimension(200, 51));
         
    tablaArriba.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            tablaArribaKeyReleased(evt);
        }
    });
}
    private double getImporteTotal() {

        int numFilas = modeloTablaProductosAbajo.getRowCount();
        double sumatoria = 0;
        for (int i = 0; i < numFilas; i++) {
            double importe = (double) modeloTablaProductosAbajo.getValueAt(i, 6);
            sumatoria += (importe);

        }

        return sumatoria;

    }

    //CARGAR PRESUPUESTO EN TABLA DE ABAJO    
    private void cargarPresupuesto() {

        int presupuesto = Integer.parseInt(txtPresupuesto.getText());

        ArrayList<Presupuesto> listaProductoAbajo = base.getPresupuestoPorID(presupuesto);
        int numeroProducto = listaProductoAbajo.size();

        for (int i = 0; i < numeroProducto; i++) {

            Presupuesto producto = listaProductoAbajo.get(i);
            //String nomBre = producto.getNomProd();
         
            int idClave = producto.getCodigo_prod();
            int cant = producto.getCant_productos();
            Double importe = producto.getpNeto();
            double desc = producto.getDescuento();
            int stock = producto.getStock();
            double precioVenta = producto.getPrecio_unitario();

            //INSERTA FILAS Y NO BORRA LAS ANTERIORES
            modeloTablaProductosAbajo.insertRow(i, new Object[]{idClave, producto, stock, desc, cant, precioVenta, importe});
            importeTotal = (double) modeloTablaProductosAbajo.getValueAt(i, 6);
            sumatoria += (importeTotal);

        }
        lblSumatoria.setText(String.valueOf(sumatoria));

    }
    
    private void tablaArribaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaArribaKeyReleased

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                int filaSelec = modeloTablaProductosArriba.getRowCount();
                if (filaSelec <= 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo");

                } else {

                    filaSeleccionada = tablaArriba.getSelectedRow();
                    modeloTablaProductosArriba = (DefaultTableModel) tablaArriba.getModel();
                    String codigo = tablaArriba.getValueAt(filaSeleccionada, 0).toString();
                    Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 1);
                    String codigoProve = tablaArriba.getValueAt(filaSeleccionada, 2).toString();
                    String Pventa = tablaArriba.getValueAt(filaSeleccionada, 3).toString();
                    String iva = String.valueOf(p.getIva());
                    txtDesc.setText(p.getNomProducto());
                    txtCodigo.setText(codigo);
                    txt_codigo_prove.setText(codigoProve);
                    txtIva.setText(iva);
                    txtIva.setEnabled(false);
                    txtCantidad.requestFocus();
                    
                    
                  

                }

            }
        } catch (Exception e) {

        }


    }//GEN-LAST:event_tablaArribaKeyReleased

   
    private void cargarModeloTablaArriba() {

        ArrayList<Producto> listaProductoAbajo = base.obtenerProductos();
        int numeroProducto = listaProductoAbajo.size();
        modeloTablaProductosArriba.setNumRows(numeroProducto);

        for (int i = 0; i < numeroProducto; i++) {

            Producto producto = listaProductoAbajo.get(i);
            String nomBre = producto.getNomProducto();
            String idClave = producto.getIdProducto();
            String idFabricaProd = producto.getIdProveedorProducto();
            Double pventa = producto.getPrecioVentaProducto();
            int stock = producto.getStockProducto();

            modeloTablaProductosArriba.setValueAt(idClave, i, 0);
            modeloTablaProductosArriba.setValueAt(producto, i, 1);
            modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
            modeloTablaProductosArriba.setValueAt(stock, i, 3);
            modeloTablaProductosArriba.setValueAt(pventa, i, 4);

        }

    }

    private void cargarColumnasTablaAbajo() {
        modeloTablaProductosAbajo.addColumn("Codigo");
        modeloTablaProductosAbajo.addColumn("Nombre");
        modeloTablaProductosAbajo.addColumn("Cant");        
        modeloTablaProductosAbajo.addColumn("Desc%");       
        modeloTablaProductosAbajo.addColumn("P.Venta");
        modeloTablaProductosAbajo.addColumn("Importe");

        TableColumn ColCodigo = tablaAbajo.getColumn("Codigo");    
        TableColumn ColNombre = tablaAbajo.getColumn("Nombre");
        TableColumn Colcant = tablaAbajo.getColumn("Cant");
        TableColumn ColProve = tablaAbajo.getColumn("Desc%");        
        TableColumn ColVen = tablaAbajo.getColumn("P.Venta");
        TableColumn Colimport = tablaAbajo.getColumn("Importe");

        //ColCodigo.setPreferredWidth(1);
        ColCodigo.setMaxWidth(80);
        ColCodigo.setMinWidth(50);      
        ColNombre.setMaxWidth(300);
        ColNombre.setMinWidth(500);
        Colcant.setMaxWidth(60);
        Colcant.setMinWidth(60);
        ColProve.setMaxWidth(90);
        ColProve.setMinWidth(90);
        ColVen.setMaxWidth(120);
        ColVen.setMinWidth(10);
        Colimport.setMaxWidth(120);
        Colimport.setMinWidth(10);

    }

    private void cargarClientesEnComboBox() {

        modeloComboClientes.removeAllElements();
        ArrayList<Clientes> listaReservas = base.getClientes();

        for (Clientes r : listaReservas) {
            modeloComboClientes.addElement(r);

        }
    }

    private void cargarColumnasTablaArriba() {
        modeloTablaProductosArriba.addColumn("codigo");
        modeloTablaProductosArriba.addColumn("nombre");
        modeloTablaProductosArriba.addColumn("c.prov");
        modeloTablaProductosArriba.addColumn("stock");          
        modeloTablaProductosArriba.addColumn("p.venta");
        

        TableColumn ColCodigo = tablaArriba.getColumn("codigo");        
        TableColumn ColNombre = tablaArriba.getColumn("nombre");        
        TableColumn ColProve = tablaArriba.getColumn("c.prov"); 
        TableColumn Colcant = tablaArriba.getColumn("stock");
        TableColumn ColVen = tablaArriba.getColumn("p.venta");
       
        
        ColCodigo.setMaxWidth(80);
        ColCodigo.setMinWidth(10);

        ColNombre.setMinWidth(300);
        ColNombre.setMaxWidth(500);

        ColProve.setMaxWidth(150);
        ColProve.setMinWidth(50);
        
        Colcant.setMaxWidth(50);
        Colcant.setMinWidth(50);
        
        ColVen.setMaxWidth(80);
        ColVen.setMinWidth(10);

       

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBusquedaCriterio = new javax.swing.ButtonGroup();
        grupo_boletas = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBounds(12, 306, 686, 301);
        tablaAbajo = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel6.setBounds(12, 0, 958, 52);
        radioCtaCorriente = new javax.swing.JRadioButton();
        radioCtaCorriente.setFont(new Font("Fira Sans", Font.PLAIN, 10));
        radioPresupuesto = new javax.swing.JRadioButton();
        radioPresupuesto.setFont(new Font("Fira Sans", Font.PLAIN, 10));
        radioFactura = new javax.swing.JRadioButton();
        radioFactura.setFont(new Font("Fira Sans", Font.PLAIN, 10));
        comboClientes = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel10.setFont(new Font("Fira Code", Font.PLAIN, 12));
        txtPresupuesto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setFont(new Font("Fira Code", Font.PLAIN, 12));
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel7.setBounds(1297, 383, 0, 0);
        btnQuitarProd = new javax.swing.JButton();
        btnQuitarProd.setBounds(730, 420, 220, 42);
        btnCancelar = new javax.swing.JButton();
        btnCancelar.setBounds(730, 474, 220, 40);
        btnAceptar = new javax.swing.JButton();
        btnAceptar.setBounds(730, 526, 220, 53);
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBounds(12, 58, 958, 246);
        txtDesc = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo_prove = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblIMAGEN = new javax.swing.JLabel();
        lblIMAGEN.setForeground(Color.DARK_GRAY);

        setTitle("Ventas");
        setPreferredSize(new Dimension(990, 650));
       
        getContentPane().setLayout(null);

        tablaAbajo.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // NOI18N
        tablaAbajo.setRowHeight(25);
        jScrollPane1.setViewportView(tablaAbajo);
        tablaAbajo.setModel(modeloTablaProductosAbajo);

        grupo_boletas.add(radioCtaCorriente);
        radioCtaCorriente.setText("C.Corriente");

        grupo_boletas.add(radioPresupuesto);
        radioPresupuesto.setSelected(true);
        radioPresupuesto.setText("Presupuesto");

        grupo_boletas.add(radioFactura);
        radioFactura.setText("Factura");

        comboClientes.setModel(modeloComboClientes       );

        jLabel10.setText("Num Presupuesto");

        txtPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPresupuestoKeyReleased(evt);
            }
        });

        jLabel11.setText("Cliente");

        jButton1.setText("Nuevo cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6Layout.setHorizontalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addComponent(jLabel11)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(comboClientes, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel10)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txtPresupuesto, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(radioPresupuesto)
        			.addGap(18)
        			.addComponent(radioCtaCorriente)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(radioFactura)
        			.addGap(113))
        );
        jPanel6Layout.setVerticalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addContainerGap(13, Short.MAX_VALUE)
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel11)
        				.addComponent(comboClientes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton1)
        				.addComponent(jLabel10)
        				.addComponent(txtPresupuesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(radioPresupuesto)
        				.addComponent(radioCtaCorriente, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        				.addComponent(radioFactura, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        jPanel6.setLayout(jPanel6Layout);

        jPanel7.setLayout(new java.awt.BorderLayout());

        btnQuitarProd.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnQuitarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/close1.png"))); // NOI18N
        btnQuitarProd.setText("Borrar Articulo");
        btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProdActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/close1.png"))); // NOI18N
        btnCancelar.setText("Borrar Todo");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/import.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(null);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescKeyReleased(evt);
            }
        });
        jPanel10.setLayout(new BoxLayout(jPanel10, BoxLayout.X_AXIS));

        jLabel5.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel5.setText("Código:");
        jPanel10.add(jLabel5);

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        jPanel10.add(txtCodigo);

        jLabel6.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel6.setText("Cantidad:");
        jPanel10.add(jLabel6);

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });
        jPanel10.add(txtCantidad);

        jLabel7.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel7.setText("Desc:%");
        jPanel10.add(jLabel7);

        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
        });
        jPanel10.add(txtDescuento);

        jLabel1.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel1.setText("C. Provee");
        jPanel10.add(jLabel1);

        txt_codigo_prove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_codigo_proveKeyReleased(evt);
            }
        });
        jPanel10.add(txt_codigo_prove);

        jLabel9.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel9.setText("Iva");
        jPanel10.add(jLabel9);
        jPanel10.add(txtIva);

        jLabel8.setFont(new Font("Fira Code", Font.PLAIN, 12)); // NOI18N
        jLabel8.setText("Desc:");

        lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMAGEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/camera.png"))); // NOI18N
        lblIMAGEN.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
        
        jScrollPane2 = new JScrollPane();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel10, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createParallelGroup(Alignment.LEADING)
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(txtDesc, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)))
        			.addGap(24)
        			.addComponent(lblIMAGEN, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
        			.addGap(20))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtDesc, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(26)
        					.addComponent(lblIMAGEN, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel10, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        tablaArriba = new JTable();
        tablaArriba.setRowHeight(25);
       // tablaArriba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jScrollPane2.setViewportView(tablaArriba);
        jPanel1.setLayout(jPanel1Layout);
        tablaArriba.setModel(modeloTablaProductosArriba);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, -11, 1380, 760);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void LimpiarListaProductos() {
        modeloListaProductos.clear();
    }



         private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
             int cantidadFilas = modeloTablaProductosAbajo.getRowCount();
             if (cantidadFilas > 0) {
                 int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea eliminar cancelar la venta ?");
                 if (quitar == 0) {
                     for (int i = cantidadFilas - 1; i >= 0; i--) {
                         modeloTablaProductosAbajo.removeRow(i);

                     }
                 }
             }
             double sumatoria = getImporteTotal();
             lblSumatoria.setText(String.valueOf(sumatoria));
         }//GEN-LAST:event_btnCancelarActionPerformed

         private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

             if (radioFactura.isSelected()) {
                imprimirFactura();
             }

             if (radioPresupuesto.isSelected()) {
                 insertarPresupuesto();
             }


         }//GEN-LAST:event_btnAceptarActionPerformed


    private void btnQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProdActionPerformed
        int numFilas = tablaAbajo.getSelectedRow();
        int filaSelec = modeloTablaProductosAbajo.getRowCount();
        if (filaSelec > 0) {
            int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea eliminar el articulo seleccionado ?");
            if (quitar == 0) {
                if (numFilas == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar el articulo que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    modeloTablaProductosAbajo.removeRow(numFilas);
                }
            }
        }
        double sumatoria = getImporteTotal();
        lblSumatoria.setText(String.valueOf(sumatoria));
    }//GEN-LAST:event_btnQuitarProdActionPerformed

    //BUSCA POR CODIGO DEL PRODUCTO/ID_PROD
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtDesc.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           
        	 LimpiarLista();
        	String id = txtCodigo.getText();
           

            try {
                if (!id.isEmpty()) {

                    ArrayList<Producto> listaProductos = base.obtenerProductosPorCriterio(id);

                    int numeroProducto = listaProductos.size();
                    modeloTablaProductosArriba.setNumRows(numeroProducto);
                    for (int i = 0; i < numeroProducto; i++) {
                        Producto producto = listaProductos.get(i);
                        String nomBre = producto.getNomProducto();
                        String idClave = producto.getIdProducto();
                        String idFabricaProd = producto.getIdProveedorProducto();
                        Double pventa = producto.getPrecioVentaProducto();
                        int stock = producto.getStockProducto();

                        modeloTablaProductosArriba.setValueAt(idClave, i, 0);
                        modeloTablaProductosArriba.setValueAt(producto.getNomProducto(), i, 1);
                        modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
                        modeloTablaProductosArriba.setValueAt(stock, i, 3);
                        modeloTablaProductosArriba.setValueAt(pventa, i, 4);
                        
                    }

                    tablaArriba.requestFocus();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Carácter inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_txtCodigoKeyPressed

    //BUSCA POR CODIGO DEL PROVEEDOR / STRING
    private void txt_codigo_proveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_proveKeyReleased

        LimpiarLista();

        //if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        String cadena = txt_codigo_prove.getText();

        ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigoProveedor(cadena);

        int numeroProducto = listaProductos.size();
        modeloTablaProductosArriba.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {
            Producto producto = listaProductos.get(i);
             String nomBre = producto.getNomProducto();
            String idClave = producto.getIdProducto();
            String idFabricaProd = producto.getIdProveedorProducto();
            Double pventa = producto.getPrecioVentaProducto();
            int stock = producto.getStockProducto();

            modeloTablaProductosArriba.setValueAt(idClave, i, 0);
            modeloTablaProductosArriba.setValueAt(producto, i, 1);
            modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
            modeloTablaProductosArriba.setValueAt(pventa, i, 3);
            modeloTablaProductosArriba.setValueAt(stock, i, 4);

        }

        hacerFoco(evt, null, tablaArriba);


    }//GEN-LAST:event_txt_codigo_proveKeyReleased
//BUSCAR POR DESCRIPCION DE PRODUCTO 
    private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtCodigo.requestFocus();
        }

        String cadena = txtDesc.getText();

        ArrayList<Producto> listaProductos = base.obtenerProductosPorCadenaTexto(cadena);

        int numeroProducto = listaProductos.size();
        modeloTablaProductosArriba.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {

            Producto producto = listaProductos.get(i);
            String clave = producto.getIdProducto();
            String nomBre = producto.getNomProducto();
            String idFabricaProd = producto.getIdProveedorProducto();
            Double pventa = producto.getPrecioVentaProducto();
            int stock = producto.getStockProducto();

            modeloTablaProductosArriba.setValueAt(clave, i, 0);
            modeloTablaProductosArriba.setValueAt(producto, i, 1);
            modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
            modeloTablaProductosArriba.setValueAt(stock, i, 3);
            modeloTablaProductosArriba.setValueAt(pventa, i, 4);

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            tablaArriba.requestFocus();

        }
    }//GEN-LAST:event_txtDescKeyReleased

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //DATOS OBTENIDOS DE LOS CAMPOS DE TEXTO
            String descripcion = txtDesc.getText();
            String codigoProd = txtCodigo.getText();
            String cp = txt_codigo_prove.getText();
            String cantidad = txtCantidad.getText();
            if (descripcion.isEmpty() || codigoProd.isEmpty() || cp.isEmpty() || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no deben estar vacios");
                if(!codigoProd.isEmpty() && cantidad.isEmpty()) {
                	 txtCantidad.requestFocus();
                }
            } else {

                String codigo = (String) tablaArriba.getValueAt(filaSeleccionada, 0);
                Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 1);
                String codigoProve = tablaArriba.getValueAt(filaSeleccionada, 2).toString();               
                String Pventa = tablaArriba.getValueAt(filaSeleccionada, 4).toString();
                String iva = String.valueOf(p.getIva());
                int cant = Integer.parseInt(txtCantidad.getText());
                String descuento = txtDescuento.getText();

                if (descuento.isEmpty()) {

                    double importe = Double.parseDouble(Pventa) * cant;
                    modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

                    Object ListaProductos[] = {codigo,p,cant, 0, Pventa, importe};

                    modeloTablaProductosAbajo.addRow(ListaProductos);

                    txtDesc.setText("");
                    txtCodigo.setText("");
                    txt_codigo_prove.setText("");
                    txtCantidad.setText("");
                    txtIva.setText("");
                    txtIva.setEnabled(false);
                    sumatoria += importe;
                    lblSumatoria.setText(String.valueOf(df.format(sumatoria)));
                    lblSumatoria.setText(String.valueOf(sumatoria));
                } else {
                	
                    double Precioventa = Double.parseDouble(Pventa);
                    double desc = Double.parseDouble(txtDescuento.getText());
                    double descuentaso = (Precioventa / 100) * desc;
                    double PrecioConDescuento = Precioventa - descuentaso;
                    double importe = PrecioConDescuento * cant;

                    modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

                    Object ListaProductos[] = {codigo,p,cant, desc, Pventa, importe};

                    modeloTablaProductosAbajo.addRow(ListaProductos);

                    txtDesc.setText("");
                    txtCodigo.setText("");
                    txt_codigo_prove.setText("");
                    txtCantidad.setText("");
                    txtDescuento.setText("");
                    txtIva.setText("");
                    txtIva.setEnabled(false);

                    sumatoria += importe;
                    lblSumatoria.setText(String.valueOf(df.format(sumatoria)));
                    
                }
            }

            txtCodigo.requestFocus();

        }
    }//GEN-LAST:event_txtDescuentoKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescuento.requestFocus();
        }

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String txtPres = txtPresupuesto.getText();
            if (!txtPres.isEmpty()) {
                cargarPresupuesto();
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que insertar un numero de presupuesto");
            }
            // getImporteTotal();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo cliente");

        Clientes c = new Clientes(nombre);
        base.insertarCliente(c);
        cargarClientesEnComboBox();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void desplegarFoto(Producto prod) {

        ImageIcon ImagenProducto = null;

        try {
            InputStream is = base.buscarFoto(prod);

            BufferedImage bi = ImageIO.read(is);
            ImagenProducto = new ImageIcon(bi);

            ImageIcon icono2 = new ImageIcon(ImagenProducto.getImage().getScaledInstance(lblIMAGEN.getWidth(), lblIMAGEN.getHeight(), Image.SCALE_DEFAULT));

            lblIMAGEN.setIcon(icono2);

        } catch (Exception e) {

           
        }

    }

    public void limpiarCampoSiguiente(JComponent Actual, JComponent Sucesor) {

        if (Actual == txtCantidad) {
            txtCodigo.setText("");
            txt_codigo_prove.setText("");
            txtCantidad.setText("");
            txtIva.setText("");
            Sucesor.requestFocus();

        }
        if (Actual == txtCodigo) {
            txt_codigo_prove.setText("");
            txtIva.setText("");
            Sucesor.requestFocus();
        }

    }

    //CAMBIAR DE COMPONENTE
    public void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {

            sucesor.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
            antecesor.requestFocus();

        }
    }

    public void hacerFoco(Component campoDeTexto) {
        campoDeTexto.setFocusable(true);
    }

    private void LimpiarLista() {
        int numFilas = modeloTablaProductosArriba.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i < 0; i--) {
                modeloTablaProductosArriba.removeRow(i);
            }
        }
    }
 
    //Imprimir factura
    
    
    public void imprimirFactura() {
    	
    	 ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();

         String sumatoriaStr = lblSumatoria.getText();
         double montoVenta = Double.parseDouble(sumatoriaStr);

         double cambio = 0;

         Calendar calendarioLocal = Calendar.getInstance();
         java.util.Date fechaActual = calendarioLocal.getTime();
         long fechaMailSegun = fechaActual.getTime();
         java.util.Date fecha = new Date(fechaMailSegun);

         Ventas venta = new Ventas(montoVenta, fecha);
         Long idVenta = base.insertarVenta(venta);

         int numRows = modeloTablaProductosAbajo.getRowCount();

         for (int i = 0; i < numRows; i++) {
             String idProducto = (String) modeloTablaProductosAbajo.getValueAt(i, 0);
             String idCantidad = (String) modeloTablaProductosAbajo.getValueAt(i, 3);
             double cantidad = Double.parseDouble(idCantidad);
             DetalleVenta detalle = new DetalleVenta(idVenta, idProducto, idCantidad);

             detalles.add(detalle);
         }

         for (int i = numRows - 1; i >= 0; i--) {
             modeloTablaProductosAbajo.removeRow(i);

         }

         lblSumatoria.setText("0.00");

     }
        
    
//INSERTAR PRESUPUESTO.
    public void insertarPresupuesto() {
        String txtPresu = txtPresupuesto.getText();
        Map parametros = new HashMap();
        conn = Conexion.getConexion();
        JasperReport reporte = null;
        String path = "/home/ferc//sistemaFerreteria/src/vista/Reportes.jasper";
        JasperPrint jprint = null;
        JasperViewer view = null;
        if (txtPresu.isEmpty()) {
            int rows = modeloTablaProductosAbajo.getRowCount();
            Calendar calendarioLocal = Calendar.getInstance();
            java.util.Date fechaActual = calendarioLocal.getTime();
            long fechaMailSegun = fechaActual.getTime();
            java.util.Date fecha = new Date(fechaMailSegun);
            String fecha1 = String.valueOf(fecha);

            ArrayList<Presupuesto> id = base.getPresupuesto();
            int numPresu;
            Presupuesto p = null;
            for (int j = 0; j < id.size(); j++) {
                p = id.get(j);
            }
            numPresu = p.getNum_presupuesto() + 1;
            for (int i = 0; i < rows; i++) {

                int codigoProd = (int) modeloTablaProductosAbajo.getValueAt(i, 0);
                int cantidadProd = (int) modeloTablaProductosAbajo.getValueAt(i, 4);
                int idCliente = comboClientes.getSelectedIndex() + 1;
                int idProd = (int) modeloTablaProductosAbajo.getValueAt(i, 0);

                Presupuesto presu = new Presupuesto(numPresu, codigoProd, fecha1, cantidadProd, idCliente, idProd);
                base.insertarPresupuesto(presu);
            }
            int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea imprimir el presupuesto?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (seleccion == 0) {
                parametros.put("numPresupuesto", numPresu);
                try {

                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                    jprint = JasperFillManager.fillReport(reporte, parametros, conn);
                    view = new JasperViewer(jprint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            parametros.put("numPresupuesto", Integer.parseInt(txtPresu));
            try {

                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                jprint = JasperFillManager.fillReport(reporte, parametros, conn);
                view = new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        int seleccion2 = JOptionPane.showConfirmDialog(null, "¿Desea descontar del stock estos artículos?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (seleccion2 == 0) {

            restarStock();
        }
    }

    public void restarStock() {
        int rows = modeloTablaProductosAbajo.getRowCount();

        for (int i = 0; i < rows; i++) {

            String idProd =  (String)modeloTablaProductosAbajo.getValueAt(i, 0);
            int stock = (int) modeloTablaProductosAbajo.getValueAt(i, 2);
            int cant = (int) modeloTablaProductosAbajo.getValueAt(i, 4);

            int stockActualizado = stock - cant;
            Producto p = new Producto();
            p.setIdProducto(idProd);
            p.setStockProducto(stockActualizado);

            base.actualizarInventario(p);

        }
        cargarModeloTablaArriba();
    }

    private javax.swing.JTextField campoAgregarExistencia;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoProveedor;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoStock;
    private boolean seleccion;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnQuitarProd;
    public javax.swing.JComboBox<String> comboClientes;
    private javax.swing.ButtonGroup grupoBusquedaCriterio;
    private javax.swing.ButtonGroup grupo_boletas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIMAGEN;
    private javax.swing.JTextField lblSumatoria;
    public javax.swing.JRadioButton radioCtaCorriente;
    public javax.swing.JRadioButton radioFactura;
    private javax.swing.JRadioButton radioPresupuesto;
    private javax.swing.JTable tablaAbajo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtPresupuesto;
    private javax.swing.JTextField txt_codigo_prove;
    private JScrollPane jScrollPane2;
    private JTable tablaArriba;
}
