/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

import mdlaf.MaterialLookAndFeel;
import modelo.Ventas;
import vistaActualizacion.VistaActualizacion;
import vistaCategoria.VistaCategoria;
import vistaDolar.VistaDolar;

import vistaInventario.MarcoArticulo;
import vistaProveedores.VistaProveedores;
import vistaReportes.VistaReportes;
import vistaVentas.vistaFrame;

import javax.swing.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Principal extends javax.swing.JFrame {

	
    public Principal() {
    	setAlwaysOnTop(true);
    	
    	
        initComponents();
    
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 1366, 674);
        ImageIcon imagen = new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/wallppaers.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
        lblNewLabel.setIcon(icono);
        this.repaint();
        getContentPane().setLayout(null);
        
        getContentPane().add(lblNewLabel);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoBotones = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnVentas = new javax.swing.JMenuItem();
        btnArticulos = new javax.swing.JMenuItem();
        menuTemas = new javax.swing.JMenu();
        temaDark = new javax.swing.JMenuItem();
        temaLight = new javax.swing.JMenuItem();
        Dolar = new javax.swing.JMenu();
        btnDolar = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");
        setSize(1390,845);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        
        
     
      
   

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Archivo");

        btnVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        btnVentas.setText("Ventas");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jMenu1.add(btnVentas);

        btnArticulos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        btnArticulos.setText("Articulos");
        btnArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArticulosActionPerformed(evt);
            }
        });
        jMenu1.add(btnArticulos);

        jMenuBar1.add(jMenu1);
        
        JMenuItem mntmProveedores = new JMenuItem("Proveedores");
        mntmProveedores.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
    		        VistaProveedores vistaD = new VistaProveedores();
    		        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    		        vistaD.setVisible(true);
    		        vistaD.setAlwaysOnTop(false);
    		        vistaD.setLocationRelativeTo(new MarcoArticulo());
    			
    			}
        	
        });
        jMenu1.add(mntmProveedores);
        
        JMenuItem mntmActualizar = new JMenuItem("Actualizar");
        mntmActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	
		        VistaActualizacion vistaD = new VistaActualizacion();
		        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        vistaD.setVisible(true);
		        vistaD.setAlwaysOnTop(false);
		        vistaD.setLocationRelativeTo(new MarcoArticulo());
        	}
        });
        
        JMenuItem mntmCategorias = new JMenuItem("Categorias");
        mntmCategorias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        							
        		  VistaCategoria vistaD = new VistaCategoria();
			        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        vistaD.setVisible(true);
			        vistaD.setAlwaysOnTop(false);
			        vistaD.setLocationRelativeTo(new MarcoArticulo());

        	}
        	
        });
        jMenu1.add(mntmCategorias);
        jMenu1.add(mntmActualizar);
        
        JMenuItem mntmReportes = new JMenuItem("Reportes");
        mntmReportes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		VistaReportes vista = new VistaReportes();
        		vista.setLocationRelativeTo(null);
        		vista.setDefaultCloseOperation(vista.DISPOSE_ON_CLOSE);
        		vista.setVisible(true);
        	}
        });
        jMenu1.add(mntmReportes);

        menuTemas.setText("Color ");

        temaDark.setText("Dark");
        temaDark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temaDarkActionPerformed(evt);
            }
        });
        menuTemas.add(temaDark);

        temaLight.setText("Light");
        temaLight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temaLightActionPerformed(evt);
            }
        });
        menuTemas.add(temaLight);

        jMenuBar1.add(menuTemas);

        Dolar.setText("Preferencias");
        
        Dolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DolarActionPerformed(evt);
            }
        });

        btnDolar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        btnDolar.setText("Dolar");
        btnDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDolarActionPerformed(evt);
            }
        });
        Dolar.add(btnDolar);

        btnSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        Dolar.add(btnSalir);

        jMenuBar1.add(Dolar);

        setJMenuBar(jMenuBar1);
        
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArticulosActionPerformed
             
    	 articulos = new MarcoArticulo();
    	articulos.setLocationRelativeTo(null);
    	articulos.setDefaultCloseOperation(articulos.DISPOSE_ON_CLOSE);
    	articulos.setVisible(true);
    }//GEN-LAST:event_btnArticulosActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
   
    	ventas = new vistaFrame();
    	ventas.setLocationRelativeTo(null);
    	ventas.setDefaultCloseOperation(articulos.DISPOSE_ON_CLOSE);
    	ventas.setVisible(true);
     
    }//GEN-LAST:event_btnVentasActionPerformed

    private void temaDarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temaDarkActionPerformed
    
               try {
           
            UIManager.setLookAndFeel(new FlatLightOwlContrastIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
         
        
    
    }//GEN-LAST:event_temaDarkActionPerformed

    private void temaLightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temaLightActionPerformed
         
       
               try {
           
            UIManager.setLookAndFeel(new FlatLightOwlContrastIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
         
    
      
    }//GEN-LAST:event_temaLightActionPerformed

    private void DolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DolarActionPerformed
        
     
    }//GEN-LAST:event_DolarActionPerformed

    private void btnDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDolarActionPerformed
        VistaDolar vistaD = new VistaDolar();
        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        vistaD.setVisible(true);
        vistaD.setAlwaysOnTop(false);
        vistaD.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnDolarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    public static void main(String args[]) throws ParseException, UnsupportedLookAndFeelException {
               try {
           
            UIManager.setLookAndFeel(new FlatArcIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
    }
    
    
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Dolar;
    private javax.swing.ButtonGroup GrupoBotones;
    private javax.swing.JMenuItem btnArticulos;
    private javax.swing.JMenuItem btnDolar;
    private javax.swing.JMenuItem btnSalir;
    private javax.swing.JMenuItem btnVentas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu menuTemas;
    private javax.swing.JMenuItem temaDark;
    private javax.swing.JMenuItem temaLight;
    private MarcoArticulo articulos;    
    private vistaFrame ventas;
    private JLabel lblNewLabel;
}
