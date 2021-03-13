package vistaActualizacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import controlador.Controlador;
import modelo.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class VistaActualizacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public  JTable tablaImportacion;
	private File archivo;
	private JFileChooser selecArchivo;
	public DefaultTableModel model = new DefaultTableModel();
	private  Workbook book;
	private Conexion con;
	private Connection connetion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VistaActualizacion dialog = new VistaActualizacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VistaActualizacion() {
		
		selecArchivo = new JFileChooser();
		

		setBounds(100, 100, 980, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 968, 535);
		contentPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane);
			{
				tablaImportacion = new JTable();
				scrollPane.setViewportView(tablaImportacion);
				tablaImportacion.setModel(new ModeloExcel().modeloTabla);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Importar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						mostrarDatosActionPerformed(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnActualizar = new JButton("Actualizar");
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ActualizarListaDePrecios();
					
					}
				});
				btnActualizar.setActionCommand("Cancel");
				buttonPane.add(btnActualizar);
			}
		}
	}

	private void getFile() {

		if (selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {

			archivo = selecArchivo.getSelectedFile();

			if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {

				JOptionPane.showMessageDialog(null, importar(archivo, tablaImportacion));
			} else {
				JOptionPane.showMessageDialog(null, "Elija un formato válido");
			}
		}

	}

	private void mostrarDatosActionPerformed(ActionEvent e) {
		if (selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {

			archivo = selecArchivo.getSelectedFile();

			if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {

				JOptionPane.showMessageDialog(null,
						importar(archivo, tablaImportacion));
			} else {
				JOptionPane.showMessageDialog(null, "Elija un formato válido");
			}
		}
	}
	
	private String importar(File archivo, JTable tabla) {

	        String respuesta = "Error en la importacion";

	        tabla.setModel(model);

	        Workbook wb;

	        try {

	            wb = WorkbookFactory.create(new FileInputStream(archivo));
	            Sheet hoja = wb.getSheetAt(0);
	            Iterator filaIterator = hoja.rowIterator();
	            int indiceFila = -1;

	            while (filaIterator.hasNext()) {
	                indiceFila++;
	                Row fila = (Row) filaIterator.next();
	                Iterator columnIterator = fila.cellIterator();

	                Object[] listaColumna = new Object[15];
	                int indiceColumna = -1;

	                while (columnIterator.hasNext()) {
	                    indiceColumna++;

	                    Cell cell = (Cell) columnIterator.next();
	                    if (indiceFila == 0) {

	                    	model.addColumn(cell.getStringCellValue());
	                    } else {

	                        if (cell != null) {

	                            switch (cell.getCellType()) {

	                                case Cell.CELL_TYPE_NUMERIC:
	                                    listaColumna[indiceColumna] = (double) cell.getNumericCellValue();

	                                    break;

	                                case Cell.CELL_TYPE_STRING:

	                                    listaColumna[indiceColumna] = cell.getStringCellValue();
	                                    break;

	                                case Cell.CELL_TYPE_BOOLEAN:

	                                    listaColumna[indiceColumna] = cell.getBooleanCellValue();
	                                    break;

	                                default:
	                                
	                                    listaColumna[indiceColumna] ="0";
	                                    break;
	                                    
	                                    

	                            }
	                        }

	                    }

	                }
	                if (indiceFila != 0) model.addRow(listaColumna);
	                    
	                
	            }
	            respuesta = "Importación exitosa";

	        } catch (Exception e) {

	            e.printStackTrace();
	        }

	        return respuesta;
	    }
	
public void ActualizarListaDePrecios() {
		
		try {

			
			int numFila = tablaImportacion.getRowCount();
			int numColumna = tablaImportacion.getColumnCount();

			System.out.println(numFila);
           System.out.println(numColumna);
			
			Connection connection = con.getConexion();

			// PreparedStatement ps = conexion.prepareStatement("UPDATE productos SET
			// PRECIO=? WHERE CODIGO_ARTICULO=?");
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO `db-sistema`.CAT_PRODUCTOS(NOM_PROD, STOCK_PROD, PRECIO_COMPRA_PROD, PRECIO_VENTA_PROD,ID_CATEGORIA,ID_PROVEEDOR,IVA,BON1,BON2,BON3,BON4,FLETE,GANANCIA)"
							+ "VALUES(?,?,?,?,(SELECT ID_CATEGORIA FROM CAT_CATEGORIA WHERE NOM_CATEGORIA=?),(SELECT ID_PROVEEDOR FROM CAT_PROVEEDORES WHERE NOM_PROVEEDOR=?),?,?,?,?,?,?,?)");
			for (int i = 0; i < numFila; i++) {
				
				ps.setString(1, (String) model.getValueAt(i, 0));
				ps.setDouble(2,  (double) model.getValueAt(i, 1));
				ps.setDouble(3,  (double) model.getValueAt(i, 2));
				ps.setDouble(4,  (double) model.getValueAt(i, 3));
				ps.setString(5, (String) model.getValueAt(i, 4));
				ps.setString(6, (String)model.getValueAt(i, 5));
				ps.setDouble(7, (double) model.getValueAt(i, 6));
				ps.setDouble(8,  (double) model.getValueAt(i, 7));
				ps.setDouble(9,  (double) model.getValueAt(i, 8));
				ps.setDouble(10,  (double) model.getValueAt(i, 9));
				ps.setDouble(11,  (double) model.getValueAt(i, 10));
				ps.setDouble(12,  (double) model.getValueAt(i, 11));
				ps.setDouble(13,  (double) model.getValueAt(i, 12));
				
				System.out.println("bucle" + i);

				ps.execute();

				

			}
			JOptionPane.showMessageDialog(null, "FINISH HIM");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
