/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import banco.Banco;
import banco.Cabecalho;
import banco.DisciConteudos;
import banco.Questoes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Couth
 */
public class ProvaPrincipalGUI extends javax.swing.JFrame {

    /**
     * Creates new form ProvaPrincipalGUI
     */
    public ProvaPrincipalGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbCabecalho = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        cbDisciplinas = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConteudos = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbQuestoes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSelecionadas = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(209, 224, 248));

        jToolBar1.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);

        jLabel6.setText("   ");
        jToolBar1.add(jLabel6);

        jLabel1.setText("Cabeçalho:");
        jToolBar1.add(jLabel1);

        cbCabecalho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCabecalhoItemStateChanged(evt);
            }
        });
        jToolBar1.add(cbCabecalho);
        jToolBar1.add(jSeparator1);

        jLabel2.setText("Disciplinas:");
        jToolBar1.add(jLabel2);

        cbDisciplinas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDisciplinasItemStateChanged(evt);
            }
        });
        jToolBar1.add(cbDisciplinas);
        jToolBar1.add(jSeparator3);

        jButton1.setBackground(new java.awt.Color(178, 203, 243));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/search.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setBackground(new java.awt.Color(178, 203, 243));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/printer.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator4);

        jButton2.setBackground(new java.awt.Color(178, 203, 243));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jLabel3.setText("Conteúdos:");

        tbConteudos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbConteudos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Selecionado", "ID", "Nome Conteúdo", "Série"
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
        tbConteudos.setGridColor(new java.awt.Color(255, 255, 255));
        tbConteudos.setRowHeight(20);
        tbConteudos.setSelectionBackground(new java.awt.Color(122, 203, 243));
        jScrollPane1.setViewportView(tbConteudos);

        jLabel4.setText("Questões:");

        tbQuestoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbQuestoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbQuestoes.setGridColor(new java.awt.Color(255, 255, 255));
        tbQuestoes.setRowHeight(20);
        tbQuestoes.setSelectionBackground(new java.awt.Color(122, 203, 243));
        jScrollPane2.setViewportView(tbQuestoes);

        jLabel5.setText("Questões Selecionadas:");

        tbSelecionadas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbSelecionadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbSelecionadas.setGridColor(new java.awt.Color(255, 255, 255));
        tbSelecionadas.setRowHeight(20);
        tbSelecionadas.setSelectionBackground(new java.awt.Color(122, 203, 243));
        jScrollPane4.setViewportView(tbSelecionadas);

        jButton4.setBackground(new java.awt.Color(178, 203, 243));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_Fast_Forward_24px.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(178, 203, 243));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_Sort_Right_24px.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(178, 203, 243));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_Sort_Left_24px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(178, 203, 243));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons8_Rewind_24px.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(257, 257, 257))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Banco n = new Banco();/*
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
                    " WHERE Questoes.Questoes_ID IN " + QuestoesSelecionadas()));
        } catch (SQLException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jpPrint = null;
                
        Map<String, Object> parametros;
        parametros = new java.util.HashMap<>();
        parametros.clear();
        parametros.put("NomeCabecalho", cbCabecalho.getSelectedItem().toString());
        try {
            jpPrint = JasperFillManager.fillReport("C:\\Users\\GUSTAVO\\Documents\\GitHub\\ATP\\src\\Provas.jasper", parametros, relatResult);
        } catch (JRException ex) {
            Logger.getLogger(ProvaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer jv = new JasperViewer(jpPrint,false);
        jv.setVisible(true);     
        jv.toFront();       */
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tbQuestoes.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Nenhuma Questão selecionadas!");
        }else{            
            for (int i = 0; i < tbQuestoes.getRowCount(); i++) {
                modelSelecionadas.addRow(new Object[]{
                        tbQuestoes.getValueAt(i, 0),
                        tbQuestoes.getValueAt(i, 1),
                        tbQuestoes.getValueAt(i, 2),
                        tbQuestoes.getValueAt(i, 3),
                });
            }
            modelQuestoes.setNumRows(0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (tbQuestoes.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Nenhuma Questão selecionadas!");
        }else{            
            int i = tbQuestoes.getSelectedRow();
            modelSelecionadas.addRow(new Object[]{
                tbQuestoes.getValueAt(i, 0),
                tbQuestoes.getValueAt(i, 1),
                tbQuestoes.getValueAt(i, 2),
                tbQuestoes.getValueAt(i, 3),
            });
            
            modelQuestoes.removeRow(i);
        }  
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        PreencherQuestoes();
        int i = tbSelecionadas.getSelectedRow();
        modelSelecionadas.removeRow(i);   
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        PreencherQuestoes();
        modelSelecionadas.setNumRows(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PreencherQuestoes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MenuPrincipalGUI menu = new MenuPrincipalGUI();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        PreencheCabecalho();
        PreencheDisciplinas();
        PreencheConteudos();
        ConfiguraSelecionadas();
    }//GEN-LAST:event_formComponentShown

    private void cbCabecalhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCabecalhoItemStateChanged
        // TODO add your handling code here:        
    }//GEN-LAST:event_cbCabecalhoItemStateChanged

    private void cbDisciplinasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDisciplinasItemStateChanged
        // TODO add your handling code here:
        PreencheConteudos();
    }//GEN-LAST:event_cbDisciplinasItemStateChanged

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProvaPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProvaPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProvaPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProvaPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProvaPrincipalGUI().setVisible(true);
            }
        });
    }
    
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
    
    //Funcionalidades relacionadas aos conteudos
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
    
    private String IdConteudosSelecionados(){
        int i = tbConteudos.getRowCount();
        String selecionados = "(0";
        for (int j = 0; j < i; j++) {
            if (tbConteudos.getValueAt(j, 0).equals(true)){
                selecionados =  selecionados + "," + tbConteudos.getValueAt(j, 1);
            }           
        }
        if (selecionados.equals("(0")){
            selecionados = "";
        }else{
            selecionados = selecionados + ")";
        }
        
        return selecionados;
    }
    
    //Funcionalidades relacionadas à selecionar questões
    private DefaultTableModel modelQuestoes;
    private DefaultTableModel modelSelecionadas;
    
    private void PreencherQuestoes(){
        Questoes q = new Questoes();
        try {
            //Questoes                
            modelQuestoes = new DefaultTableModel();
            modelQuestoes.addColumn("ID");
            modelQuestoes.addColumn("Enunciado");
            modelQuestoes.addColumn("Dificuldade");
            modelQuestoes.addColumn("Multipla Escolha");
            tbQuestoes.setModel(modelQuestoes);
            modelQuestoes.setNumRows(0);
            rs = q.pegarQuestaoConteudo(IdConteudosSelecionados());
            if (rs!=null) {
                do {
                    modelQuestoes.addRow(new Object[]{
                        rs.getString("Questoes_ID"),
                        rs.getString("Enunciado"),
                        rs.getString("Dificuldade"),
                        rs.getString("MultiplaEscolha"),
                    });
                } while (rs.next());
            }            
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }        
    }
    
    private void ConfiguraSelecionadas(){
        modelSelecionadas = new DefaultTableModel();
        modelSelecionadas.addColumn("ID");
        modelSelecionadas.addColumn("Enunciado");
        modelSelecionadas.addColumn("Dificuldade");
        modelSelecionadas.addColumn("Multipla Escolha");
        tbSelecionadas.setModel(modelSelecionadas);              
    }
    
    private String QuestoesSelecionadas(){
        String selecionadas = "(0";
        if (tbSelecionadas.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Nenhuma Questão selecionadas!");
        }else{            
            for (int i = 0; i < tbSelecionadas.getRowCount(); i++) {
                selecionadas = selecionadas + "," + tbSelecionadas.getValueAt(i, 0);                            
            }
        }
        if (selecionadas.equals("(0")){
            selecionadas = "(0)";
        }else{
            selecionadas = selecionadas + ")";
        }
        return selecionadas;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCabecalho;
    private javax.swing.JComboBox cbDisciplinas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbConteudos;
    private javax.swing.JTable tbQuestoes;
    private javax.swing.JTable tbSelecionadas;
    // End of variables declaration//GEN-END:variables
}
