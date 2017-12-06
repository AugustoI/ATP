/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import banco.Banco;
import banco.Cabecalho;
import banco.DisciConteudos;
import banco.DisciplinasDAO;
import banco.Questoes;
import java.awt.Component;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import nickyb.sqleonardo.common.gui.CheckBoxCellRenderer;
import org.hsqldb.lib.HashMap;



/**
 *
 * @author GUSTAVO
 */
public class ProvaGUI extends javax.swing.JFrame {
    

    /**
     * Creates new form ProvaGUI
     */   
    Questoes questoes = new Questoes();
    public ProvaGUI() {
        initComponents();
        setLocationRelativeTo(this);        
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
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbCabecalho = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaQuestoes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbDisciplinas = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaQuestoes1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbConteudos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emitir Provas");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/printer.png"))); // NOI18N
        jButton1.setText("Emitir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cabecalho:");

        tabelaQuestoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelaQuestoes);

        jLabel2.setText("Disciplinas:");

        cbDisciplinas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDisciplinasItemStateChanged(evt);
            }
        });

        jLabel3.setText("Conteudos:");

        tabelaQuestoes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabelaQuestoes1);

        tbConteudos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionado", "ID", "Nome Conteudo", "Série"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbConteudos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbCabecalho, javax.swing.GroupLayout.Alignment.LEADING, 0, 246, Short.MAX_VALUE)
                                .addComponent(cbDisciplinas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        cbCabecalho.getAccessibleContext().setAccessibleDescription("teste");

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
"	Questoes INNER JOIN ImagemQuest ON Questoes.Questoes_ID = ImagemQuest.ID_Questao"+
                    " WHERE Questoes.Questoes_ID = 1 "));
        } catch (SQLException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jpPrint = null;
                
        //private java.util.HashMap<Object, Object> parametros;
        //Map<String, String> parametros;
        Map<String, Object> parametros;
        parametros = new java.util.HashMap<>();
        parametros.clear();
        //parametros.put("NomeCabecalho", txtCodCabecalho.getText().toString());
        try {//Teste //Nova Versão
            jpPrint = JasperFillManager.fillReport("C:\\Users\\GUSTAVO\\Documents\\GitHub\\ATP\\src\\Provas.jasper", parametros, relatResult);
        } catch (JRException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer jv = new JasperViewer(jpPrint,false);
        jv.setVisible(true);     
        jv.toFront();       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuGUI mn = new MenuGUI();
        mn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        PreencheCabecalho();
        PreencheDisciplinas();
        PreencheConteudos();
    }//GEN-LAST:event_formComponentShown

    private void cbDisciplinasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDisciplinasItemStateChanged
        PreencheConteudos();
    }//GEN-LAST:event_cbDisciplinasItemStateChanged

    List<String> strList = new ArrayList<String>(); 
    ResultSet rs;    
    DefaultComboBoxModel modelComboBox;
    private void PreencheCabecalho(){
        try {
            //Cabecalhos
            Cabecalho a = new Cabecalho();
            strList.removeAll(strList);
            rs = a.pegarCabecalho();
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("Titulo"));
                } while (rs.next());
            }
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            cbCabecalho.setModel(modelComboBox);         
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
    }
    DisciConteudos a = new DisciConteudos();
    private void PreencheDisciplinas(){
        try {
            //Disciplinas              
            strList.removeAll(strList);
            rs = a.pegarDisciplinas();
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeDisciplinas"));
                } while (rs.next());
            }
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            cbDisciplinas.setModel(modelComboBox);         
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
    }
    private void PreencheConteudos(){
        DefaultTableModel modelConteudos = (DefaultTableModel)tbConteudos.getModel();
        try {
            //Conteudos              
            modelConteudos.setNumRows(0);
            rs = a.pegarConteudos(cbDisciplinas.getSelectedItem().toString());
            if (rs!=null) {
                do {
                    modelConteudos.addRow(new Object[]{
                        false,
                        rs.getString("Conteudos_ID"),
                        rs.getString("NomeConteudos"),
                        rs.getString("CodSerie"),
                    });
                } while (rs.next());
            }
            tbConteudos.setModel(modelConteudos);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
    }
    
    //Funcionalidades relacionadas aos conteudos
    

    //Funcionalidades relacionadas à selecionar questões
    private DefaultTableModel modelQuestoes;
    private DefaultTableModel modelSelecionadas;
    
        
    
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
    private javax.swing.JComboBox cbCabecalho;
    private javax.swing.JComboBox cbDisciplinas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabelaQuestoes;
    private javax.swing.JTable tabelaQuestoes1;
    private javax.swing.JTable tbConteudos;
    // End of variables declaration//GEN-END:variables
}
