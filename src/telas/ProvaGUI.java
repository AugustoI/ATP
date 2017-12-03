/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import banco.Banco;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.mapping.Map;
import org.hsqldb.lib.HashMap;



/**
 *
 * @author GUSTAVO
 */
public class ProvaGUI extends javax.swing.JFrame {

    /**
     * Creates new form ProvaGUI
     */
    public ProvaGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("TesteReport");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jButton1)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(137, 137, 137))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Banco n = new Banco();
        JRResultSetDataSource relatResult = null;
        try {
            relatResult = new JRResultSetDataSource(n.executaSQL("SELECT\n" +
"	Questoes.Questoes_ID,\n" +
"	Questoes.Enunciado,\n" +
"	Questoes.Dificuldade,\n" +
"	Questoes.ID_Conteudos,\n" +
"	Questoes.MultiplaEscolha,\n" +
"	CASE WHEN Questoes.AlternativaA <> '' THEN CONCAT('A) ', Questoes.AlternativaA) END AS AlternativaA,\n" +
"	CASE WHEN Questoes.AlternativaB <> '' THEN CONCAT('B) ', Questoes.AlternativaB) END AS AlternativaB,\n" +
"	CASE WHEN Questoes.AlternativaC <> '' THEN CONCAT('C) ', Questoes.AlternativaC) END AS AlternativaC,\n" +
"	CASE WHEN Questoes.AlternativaD <> '' THEN CONCAT('D) ', Questoes.AlternativaD) END AS AlternativaD,\n" +
"	CASE WHEN Questoes.AlternativaE <> '' THEN CONCAT('E) ', Questoes.AlternativaE) END AS AlternativaE,\n" +
"	CASE WHEN Questoes.AlternativaF <> '' THEN CONCAT('F) ', Questoes.AlternativaF) END AS AlternativaF,\n" +
"	ImagemQuest.ImagemQuest_ID,\n" +
"	ImagemQuest.ID_Questao,\n" +
"	ImagemQuest.Imagem,\n" +
"	ImagemQuest.POSICAO,\n" +
"	ImagemQuest.NomeImagem\n" +
"FROM\n" +
"	Questoes INNER JOIN ImagemQuest ON Questoes.Questoes_ID = ImagemQuest.ID_Questao"));
        } catch (SQLException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jpPrint = null;
        
        HashMap a = new HashMap();
        try {//Teste //Nova Versão
            jpPrint = JasperFillManager.fillReport("C:\\Users\\GUSTAVO\\Documents\\GitHub\\ATP\\src\\Provas.jasper", null, relatResult);
        } catch (JRException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer jv = new JasperViewer(jpPrint,false);
        jv.setVisible(true);     
        jv.toFront();       
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProvaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProvaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProvaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProvaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProvaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
