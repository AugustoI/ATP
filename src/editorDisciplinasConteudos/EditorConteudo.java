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
public class EditorConteudo extends javax.swing.JDialog {

    /**
     * Creates new form EditorConteudo
     */
    DisciConteudos disciConteudos = new DisciConteudos();
    List<String> strList = new ArrayList<String>();  
    DefaultComboBoxModel modelComboBox;
    ResultSet rs;
    String nomeConteudo, serie, disciplina;
    int idDisc, idCont;
    public EditorConteudo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public EditorConteudo(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Atualizar conteúdo");
        
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

        jLabel1 = new javax.swing.JLabel();
        jtConteudo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbSalvar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jcbDisciplina = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jtSerie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtPesquisar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Disciplina:");

        jLabel2.setText("Conteúdo:");

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Série:");

        jLabel4.setText("Pesquisar disciplina:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSerie))
                            .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(EditorConteudo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorConteudo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorConteudo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorConteudo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditorConteudo dialog = new EditorConteudo(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox jcbDisciplina;
    private javax.swing.JTextField jtConteudo;
    private javax.swing.JTextField jtPesquisar;
    private javax.swing.JTextField jtSerie;
    // End of variables declaration//GEN-END:variables
}
