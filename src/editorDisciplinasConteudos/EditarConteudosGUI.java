/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorDisciplinasConteudos;

import banco.DisciConteudos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Couth
 */
public class EditarConteudosGUI extends javax.swing.JDialog {

    /**
     * Creates new form EditarConteudosGUI
     */
    DisciConteudos disciConteudos = new DisciConteudos();
    List<String> strList = new ArrayList<String>();  
    DefaultComboBoxModel modelComboBox;
    ResultSet rs;
    String nomeConteudo, serie, disciplina;
    int idDisc, idCont;
    public EditarConteudosGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public EditarConteudosGUI(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Atualizar Conteúdo");
        
        idCont = id;
        
        CarregarComboBox();
        
        try {
            rs = disciConteudos.pegarConteudoPeloConteudosID(id);
            if (rs!=null) {
                nomeConteudo = rs.getString("NomeConteudos");
                serie = rs.getString("CodSerie");
                idDisc = rs.getInt("ID_Disciplinas");
                
                jtConteudo.setText(nomeConteudo);
                jtSerie.setText(serie);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }
        
        try {
            rs = disciConteudos.pegarNomeDisciplinaPeloId(idDisc);
            if (rs!=null) {
                disciplina = rs.getString("NomeDisciplinas");                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }
        
        modelComboBox.setSelectedItem(disciplina);
        
        
        jtPesquisar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                PesquisarDisciplina();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesquisar.getText().isEmpty()) {
                    MostrarDisciplinas();
                } else {
                    PesquisarDisciplina();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //
            }
        });
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
        jbSalvar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jtPesquisar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcbDisciplina = new javax.swing.JComboBox();
        jtConteudo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtSerie = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(178, 203, 243));

        jToolBar1.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);

        jbSalvar.setBackground(new java.awt.Color(178, 203, 243));
        jbSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/saveFile.png"))); // NOI18N
        jbSalvar.setToolTipText("Salvar Questão");
        jbSalvar.setFocusable(false);
        jbSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbSalvar);
        jToolBar1.add(jSeparator5);

        jLabel1.setText("Filtrar Disciplinas:");
        jToolBar1.add(jLabel1);

        jtPesquisar.setColumns(14);
        jToolBar1.add(jtPesquisar);
        jToolBar1.add(jSeparator1);

        jbCancelar.setBackground(new java.awt.Color(178, 203, 243));
        jbCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.png"))); // NOI18N
        jbCancelar.setToolTipText("Cancelar");
        jbCancelar.setFocusable(false);
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbCancelar);

        jPanel2.setBackground(new java.awt.Color(209, 224, 248));

        jLabel5.setText("Disciplina:");

        jcbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisciplinaActionPerformed(evt);
            }
        });

        jLabel2.setText("Conteúdo:");

        jLabel3.setText("Série:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:
        if ((!jtConteudo.getText().isEmpty())&&(!jtSerie.getText().isEmpty())) {
            if (jcbDisciplina.getItemCount() != 0) {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja alterar este conteúdo?", "Alterar conteúdo",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    try {
                        String conteudo = jtConteudo.getText();
                        String serie = jtSerie.getText();
                        rs = disciConteudos.pegarIdDisciplina(jcbDisciplina.getSelectedItem().toString());
                        if (rs!=null) {
                            int idDisciplina = rs.getInt("Disciplinas_ID");
                            disciConteudos.alterarConteudo(conteudo, serie, idDisciplina, idCont);   
                        }
                        JOptionPane.showMessageDialog(this, "Conteúdo atualizado com sucesso!");
                        dispose();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                    }
                } else if (x==1) {
                    dispose();
                }           
            } else {
                JOptionPane.showMessageDialog(this, "Disciplina inválida!");
            }
        } else {
            if ((jtConteudo.getText().isEmpty())&&(jtSerie.getText().isEmpty())) {
                JOptionPane.showMessageDialog(this, "O conteúdo e a série não podem ser vazios!");
            } else if (jtConteudo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O conteúdo não pode ser vazio!");
            } else {
                JOptionPane.showMessageDialog(this, "A série não pode ser vazia!");
            }
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jcbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDisciplinaActionPerformed

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
            java.util.logging.Logger.getLogger(EditarConteudosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarConteudosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarConteudosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarConteudosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarConteudosGUI dialog = new EditarConteudosGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    //CARREGAR COMBOBOX
    public void CarregarComboBox() {     
        try {
            //Disciplinas   
            strList.removeAll(strList);
            rs = disciConteudos.pegarDisciplinas();
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeDisciplinas"));
                } while (rs.next());
            }
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbDisciplina.setModel(modelComboBox);         
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }  
    }
    
    public void CarregarComboBox(String disciplina) {     
        CarregarComboBox();
        modelComboBox.setSelectedItem(disciplina);        
    }
    
    //PESQUISAR DISCIPLINA
    public void PesquisarDisciplina() {        
        String enunciado = jtPesquisar.getText();
        if (!enunciado.isEmpty()) {
            try {                    
                strList.removeAll(strList);
                rs = disciConteudos.pesquisarDisciplina(enunciado);
                if (rs!=null) {
                    do {
                        strList.add(
                                rs.getString("NomeDisciplinas"));
                    } while (rs.next());
                }
                modelComboBox = new DefaultComboBoxModel(strList.toArray());
                jcbDisciplina.setModel(modelComboBox);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não digitou nada no campo de pesquisa!");
        }         
    }  
    
    //MOSTRAR TODAS DISCIPLINAS
    public void MostrarDisciplinas() {        
        CarregarComboBox(disciplina);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox jcbDisciplina;
    private javax.swing.JTextField jtConteudo;
    private javax.swing.JTextField jtPesquisar;
    private javax.swing.JTextField jtSerie;
    // End of variables declaration//GEN-END:variables
}
