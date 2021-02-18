/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

import modelo.Ventas;
import vistaDolar.vistaDolar;

import vistaInventario.MarcoArticulo;

import vistaVentas.vistaFrame;

import javax.swing.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class Principal extends javax.swing.JFrame {

	
    public Principal() {
    	setAlwaysOnTop(true);
    	
    	
        initComponents();
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("/home/ferc/Imágenes/b_grafica_1.jpg"));
        
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
        setSize(1090,645);
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
        vistaDolar vistaD = new vistaDolar();
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
           
            UIManager.setLookAndFeel(new FlatAtomOneDarkContrastIJTheme());
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
    // End of variables declaration//GEN-END:variables
}
