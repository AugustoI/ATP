/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorQuestoes;

import banco.Questoes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Couth
 */
public class EditorQuestaoAberta extends javax.swing.JDialog {

    /**
     * Creates new form EditorQuestaoAberta
     */
    Questoes questoesBanco = new Questoes();
    JDialog dialog = new JDialog();
    List<String> strList = new ArrayList<String>(); 
    
    ResultSet rs;
    DefaultComboBoxModel modelComboBox;  
    FileInputStream input;
    String enunciado, disciplina, conteudo, fileName, fileNameAntigo;
    int idQuestao, dificuldade, idConteudo, posicaoImagem;      
    boolean img, img2, remover;        
    
    public EditorQuestaoAberta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Editor de Questão ABERTA");
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem Negrito
        Action boldAction = new EditorQuestaoAberta.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jmTexto.add(boldAction);  
        jbNegrito.addActionListener(boldAction);
        
        //MenuItem Itálico
        Action italicAction = new EditorQuestaoAberta.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jmTexto.add(italicAction);
        jbItalico.addActionListener(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new EditorQuestaoAberta.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jmTexto.add(underlineAction);
        jbSublinhado.addActionListener(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new EditorQuestaoAberta.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jmTexto.add(foregroundAction);
        jbCor.addActionListener(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new EditorQuestaoAberta.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jmTexto.add(formatTextAction);
        jbFonte.addActionListener(formatTextAction);
        
        jtpEnunciado.requestFocus();
    }
    
    public EditorQuestaoAberta(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);        
        this.setTitle("Editor de Questão ABERTA");
        jlImagem.setMaximumSize(new Dimension(14, 225));
        jbRemover.setEnabled(false);
        remover = false;
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem Negrito
        Action boldAction = new EditorQuestaoAberta.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jmTexto.add(boldAction);  
        
        //MenuItem Itálico
        Action italicAction = new EditorQuestaoAberta.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jmTexto.add(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new EditorQuestaoAberta.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jmTexto.add(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new EditorQuestaoAberta.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jmTexto.add(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new EditorQuestaoAberta.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jmTexto.add(formatTextAction);
        
        jtpEnunciado.requestFocus();
        
        try {            
            rs = questoesBanco.pegarQuestaoPeloId(id);
            if (rs!=null) {                                
                idQuestao = rs.getInt("Questoes_ID");
                enunciado = rs.getString("Enunciado");
                dificuldade = rs.getInt("Dificuldade");
                idConteudo = rs.getInt("ID_Conteudos");                
                
                jtpEnunciado.setText(enunciado);
                jcbDificuldade.setSelectedIndex(dificuldade);
            }
            
            rs = questoesBanco.pegarNomeDisciplinasPeloConteudosID(idConteudo);
            if (rs!=null) {                                
                disciplina = rs.getString("NomeDisciplinas");                
                jcbDisciplina.setSelectedItem(disciplina);
            }
            
            rs = questoesBanco.pegarNomeConteudosPeloConteudosID(idConteudo);
            if (rs!=null) {                                
                conteudo = rs.getString("NomeConteudos");                
                jcbConteudo.setSelectedItem(conteudo);
            }
                                    
            rs = questoesBanco.pegarNomeImagem(idQuestao);
            if (rs!=null) {                                
                fileNameAntigo = fileName = rs.getString("NomeImagem");
                jlImagem.setText(fileName);
                jbRemover.setEnabled(true);
                img2 = true;
            } else {
                img2 = false;
            }
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
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
        jLabel1 = new javax.swing.JLabel();
        jcbDificuldade = new javax.swing.JComboBox<String>();
        jcbDisciplina = new javax.swing.JComboBox<String>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpEnunciado = new javax.swing.JTextPane();
        jbSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbConteudo = new javax.swing.JComboBox<String>();
        jbImagem = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jbNegrito = new javax.swing.JButton();
        jbItalico = new javax.swing.JButton();
        jbSublinhado = new javax.swing.JButton();
        jbCor = new javax.swing.JButton();
        jbFonte = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jlImagem = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmiImagem = new javax.swing.JMenuItem();
        jmiSalvar = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();
        jmTexto = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Enunciado da questão:");

        jcbDificuldade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jcbDificuldade.setToolTipText("");

        jcbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisciplinaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jtpEnunciado);

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jLabel2.setText("Disciplina:");

        jLabel3.setText("Dificuldade:");

        jLabel4.setText("Conteúdo:");

        jbImagem.setText("Carregar imagem");
        jbImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImagemActionPerformed(evt);
            }
        });

        jbRemover.setText("Remover imagem");
        jbRemover.setEnabled(false);
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });

        jbNegrito.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbNegrito.setText("N");
        jbNegrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNegritoActionPerformed(evt);
            }
        });

        jbItalico.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jbItalico.setText("I");
        jbItalico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbItalicoActionPerformed(evt);
            }
        });

        jbSublinhado.setText("S");
        jbSublinhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSublinhadoActionPerformed(evt);
            }
        });

        jbCor.setText("Cor do texto");
        jbCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCorActionPerformed(evt);
            }
        });

        jbFonte.setText("Fonte");
        jbFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFonteActionPerformed(evt);
            }
        });

        jLabel5.setText("Imagem:");

        jlImagem.setText("Não há imagem cadastrada");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbNegrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbItalico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSublinhado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbCor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbFonte))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbRemover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbImagem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbImagem)
                    .addComponent(jbSalvar)
                    .addComponent(jbRemover)
                    .addComponent(jbNegrito)
                    .addComponent(jbItalico)
                    .addComponent(jbSublinhado)
                    .addComponent(jbCor)
                    .addComponent(jbFonte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlImagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jmArquivo.setText("Arquivo");
        jmArquivo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jmiImagem.setText("Carregar imagem");
        jmiImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImagemActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiImagem);

        jmiSalvar.setText("Salvar");
        jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalvarActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiSalvar);

        jmiSair.setText("Sair");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiSair);

        jMenuBar1.add(jmArquivo);

        jmTexto.setText("Texto");
        jmTexto.setToolTipText("");
        jmTexto.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jMenuBar1.add(jmTexto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisciplinaActionPerformed
        // TODO add your handling code here:
        try {
            jcbConteudo.removeAllItems();
            strList.removeAll(strList);
            rs = questoesBanco.pegarConteudos(jcbDisciplina.getSelectedItem().toString());
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeConteudos"));
                } while (rs.next());                   
            }             
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbConteudo.setModel(modelComboBox);
        } catch (SQLException e) {

        }
    }//GEN-LAST:event_jcbDisciplinaActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here: 
        try {
            if (!jtpEnunciado.getText().isEmpty()) {
                if (jcbConteudo.getItemCount() != 0) {
                    int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar esta questão?", "Salvar questão",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                    if (x==0) {
                        enunciado = jtpEnunciado.getText();
                        dificuldade = Integer.parseInt(jcbDificuldade.getSelectedItem().toString());
                        rs = questoesBanco.pegarConteudosID(jcbConteudo.getSelectedItem().toString());
                        if (rs!=null) {
                            do {
                                idConteudo = rs.getInt("Conteudos_ID");                        
                            } while (rs.next());
                            SalvarQuestao(enunciado, dificuldade, idConteudo, idQuestao); 
                            AddImagem(idQuestao);
                            RemoverImagem(idQuestao);
                        }
                        
                        jtpEnunciado.setText("");
                        JOptionPane.showMessageDialog(this, "Questão atualizada com sucesso!");
                        dispose();
                    } else if (x==1) {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Não há conteúdo cadastrado nesta disciplina!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "O enunciado da questão não pode ser vazio!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro. Tente novamente");
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImagemActionPerformed
        // TODO add your handling code here:   
        TesteImagem a = new TesteImagem(null, true);
        a.setVisible(true); 
        img = a.certo;
        posicaoImagem = a.posicao;
        input = a.input;
        fileName = a.fileName;
        jlImagem.setText(fileName);
        jbRemover.setEnabled(true);
    }//GEN-LAST:event_jbImagemActionPerformed

    private void jbRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja remover a imagem?", "Remover imagem",
        JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            if (img) {
                img = false;
                if (!img2) {
                    fileName = "Não há imagem cadastrada";
                    jlImagem.setText(fileName);            
                } else {
                    jlImagem.setText(fileNameAntigo);     
                }
            } else if (img2) {
                img2 = false;
                fileName = "Não há imagem cadastrada";
                jlImagem.setText(fileName);   
                remover = true;
            } 
            if ((!img)&&(!img2)) {
                jbRemover.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here: 
        jbSalvarActionPerformed(evt);
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jmiImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiImagemActionPerformed
        // TODO add your handling code here:     
        jbImagemActionPerformed(evt);
    }//GEN-LAST:event_jmiImagemActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            jbSalvarActionPerformed(evt);
            if (!jtpEnunciado.getText().isEmpty()) {
                if (jcbConteudo.getItemCount() != 0) {
                    dispose();
                }
            }
        }
        if (x==1) {
            dispose();
        }
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jbNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNegritoActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbNegritoActionPerformed

    private void jbItalicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbItalicoActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbItalicoActionPerformed

    private void jbSublinhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSublinhadoActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbSublinhadoActionPerformed

    private void jbCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCorActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbCorActionPerformed

    private void jbFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFonteActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbFonteActionPerformed

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
            java.util.logging.Logger.getLogger(EditorQuestaoAberta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoAberta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoAberta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoAberta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditorQuestaoAberta dialog = new EditorQuestaoAberta(new javax.swing.JFrame(), true);
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
    
    //SALVAR QUESTÃO
    public void SalvarQuestao(String e, int d, int idConteudo, int idQuestao) {        
        try {
            questoesBanco.inserirQuestaoAbertaEditada(e, d, idConteudo, idQuestao);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
    }
    
    //SALVAR IMAGEM
    public void AddImagem(int idDestaQuestao) {        
        if (img) {
            try {
                rs = questoesBanco.confereSeTemImagem(idDestaQuestao);
                if (rs!=null) {
                    questoesBanco.inserirImagemEditada(input, posicaoImagem, idDestaQuestao, fileName);              
                } else {
                    questoesBanco.inserirImagem(idDestaQuestao, input, posicaoImagem, fileName);              
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro, tente novamente!");
            }
        }
    }
    
    //REMOVER IMAGEM
    public void RemoverImagem(int idDestaQuestao) {        
        if (remover) {            
            try {
                questoesBanco.removerImagem(idDestaQuestao);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover imagem.\nErro:"+e);
            }
        }
    }    
    
    //CARREGAR COMBOBOXES
    public void CarregarComboBox() {     
        try {
            //Disciplinas   
            strList.removeAll(strList);
            rs = questoesBanco.pegarDisciplinas();
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeDisciplinas"));
                } while (rs.next());
            }
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbDisciplina.setModel(modelComboBox);
            
            //Conteúdos
            strList.removeAll(strList);
            rs = questoesBanco.pegarConteudos(jcbDisciplina.getSelectedItem().toString());
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeConteudos"));
                } while (rs.next());                   
            }             
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbConteudo.setModel(modelComboBox);     
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex);
        }      
    }
    
    //NEGRITO
    class BoldAction extends StyledEditorKit.StyledTextAction {
        private static final long serialVersionUID = 9174670038684056758L;

        public BoldAction() {
            super("font-bold");
        }

        public String toString() {
            return "Bold";
        }

        public void actionPerformed(ActionEvent e) {
            JEditorPane editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean bold = (StyleConstants.isBold(attr)) ? false : true;
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setBold(sas, bold);
                setCharacterAttributes(editor, sas, false);
            }
        }
    }
    
    //ITÁLICO
    class ItalicAction extends StyledEditorKit.StyledTextAction {
        private static final long serialVersionUID = -1428340091100055456L;

        public ItalicAction() {
            super("font-italic");
        }

        public String toString() {
            return "Italic";
        }

        public void actionPerformed(ActionEvent e) {
            JEditorPane editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean italic = (StyleConstants.isItalic(attr)) ? false : true;
                 SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setItalic(sas, italic);
                setCharacterAttributes(editor, sas, false);
            }
        }
    }

    //SUBLINHADO
    class UnderlineAction extends StyledEditorKit.StyledTextAction {
        private static final long serialVersionUID = -1428340091100055456L;

        public UnderlineAction() {
            super("font-underline");
        }

        public String toString() {
            return "Underline";
        }

        public void actionPerformed(ActionEvent e) {
            JEditorPane editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean underline = (StyleConstants.isUnderline(attr)) ? false : true;
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setUnderline(sas, underline);
                setCharacterAttributes(editor, sas, false);
            }
        }
    }

    //COR
    class ForegroundAction extends StyledEditorKit.StyledTextAction {
        private static final long serialVersionUID = 6384632651737400352L;

        JColorChooser colorChooser = new JColorChooser();

        JDialog dialog = new JDialog();

        boolean noChange = false;

        boolean cancelled = false;

        public ForegroundAction() {
            super("foreground");
        }

        public void actionPerformed(ActionEvent e) {
            JTextPane editor = (JTextPane) getEditor(e);

            if (editor == null) {
                JOptionPane.showMessageDialog(null,
                    "You need to select the editor pane before you can change the color.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            int p0 = editor.getSelectionStart();
            StyledDocument doc = getStyledDocument(editor);
            Element paragraph = doc.getCharacterElement(p0);
            AttributeSet as = paragraph.getAttributes();
            fg = StyleConstants.getForeground(as);
            if (fg == null) {
                fg = Color.BLACK;
            }
            colorChooser.setColor(fg);

            JButton accept = new JButton("OK");
            accept.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fg = colorChooser.getColor();
                    dialog.dispose();
                }
            });

            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    cancelled = true;
                    dialog.dispose();
                }
            });

            JButton none = new JButton("None");
            none.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    noChange = true;
                    dialog.dispose();
                }
            });

            JPanel buttons = new JPanel();
            buttons.add(accept);
            buttons.add(none);
            buttons.add(cancel);

            dialog.getContentPane().setLayout(new BorderLayout());
            dialog.getContentPane().add(colorChooser, BorderLayout.CENTER);
            dialog.getContentPane().add(buttons, BorderLayout.SOUTH);
            dialog.setModal(true);
            dialog.pack();
            dialog.setVisible(true);

            if (!cancelled) {
                MutableAttributeSet attr = null;
                if (editor != null) {
                    if (fg != null && !noChange) {
                        attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr, fg);
                        setCharacterAttributes(editor, attr, false);
                    }
                }
            }
            noChange = false;
            cancelled = false;
        }

        private Color fg;
    }

    //FONTE E TAMANHO
    class FontAndSizeAction extends StyledEditorKit.StyledTextAction {
        private static final long serialVersionUID = 584531387732416339L;

        private String family;

        private float fontSize;

        JDialog formatText;

        private boolean accept = false;

        JComboBox fontFamilyChooser;

        JComboBox fontSizeChooser;


        public FontAndSizeAction() {
            super("Font and Size");
        }

        public String toString() {
            return "Font and Size";
        }

        public void actionPerformed(ActionEvent e) {
            JTextPane editor = (JTextPane) getEditor(e);
            int p0 = editor.getSelectionStart();
            StyledDocument doc = getStyledDocument(editor);
            Element paragraph = doc.getCharacterElement(p0);
            AttributeSet as = paragraph.getAttributes();

            family = StyleConstants.getFontFamily(as);
            fontSize = StyleConstants.getFontSize(as);

            formatText = new JDialog(new JFrame(), "Font and Size", true);
            formatText.getContentPane().setLayout(new BorderLayout());
            formatText.setLocationRelativeTo(dialog.getContentPane());
            //format aq de centralizar a tela

            JPanel choosers = new JPanel();
            choosers.setLayout(new GridLayout(2, 1));

            JPanel fontFamilyPanel = new JPanel();
            fontFamilyPanel.add(new JLabel("Font"));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fontNames = ge.getAvailableFontFamilyNames();

            fontFamilyChooser = new JComboBox();
            for (int i = 0; i < fontNames.length; i++) {
                fontFamilyChooser.addItem(fontNames[i]);
            }
            fontFamilyChooser.setSelectedItem(family);
            fontFamilyPanel.add(fontFamilyChooser);
            choosers.add(fontFamilyPanel);

            JPanel fontSizePanel = new JPanel();
            fontSizePanel.add(new JLabel("Size"));
            fontSizeChooser = new JComboBox();
            fontSizeChooser.setEditable(true);
            fontSizeChooser.addItem(new Float(4));
            fontSizeChooser.addItem(new Float(8));
            fontSizeChooser.addItem(new Float(12));
            fontSizeChooser.addItem(new Float(16));
            fontSizeChooser.addItem(new Float(20));
            fontSizeChooser.addItem(new Float(24));
            fontSizeChooser.setSelectedItem(new Float(fontSize));
            fontSizePanel.add(fontSizeChooser);        
            choosers.add(fontSizePanel);

            JButton ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    accept = true;
                    formatText.dispose();
                    family = (String) fontFamilyChooser.getSelectedItem();
                    fontSize = Float.parseFloat(fontSizeChooser.getSelectedItem().toString());
                }
            });

            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    formatText.dispose();
                }
            });

            JPanel buttons = new JPanel();
            buttons.add(ok);
            buttons.add(cancel);
            formatText.getContentPane().add(choosers, BorderLayout.CENTER);
            formatText.getContentPane().add(buttons, BorderLayout.SOUTH);
            formatText.pack();
            formatText.setVisible(true);

            MutableAttributeSet attr = null;
            if (editor != null && accept) {
                attr = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attr, family);
                StyleConstants.setFontSize(attr, (int) fontSize);
                setCharacterAttributes(editor, attr, false);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCor;
    private javax.swing.JButton jbFonte;
    private javax.swing.JButton jbImagem;
    private javax.swing.JButton jbItalico;
    private javax.swing.JButton jbNegrito;
    private javax.swing.JButton jbRemover;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSublinhado;
    private javax.swing.JComboBox<String> jcbConteudo;
    private javax.swing.JComboBox<String> jcbDificuldade;
    private javax.swing.JComboBox<String> jcbDisciplina;
    private javax.swing.JLabel jlImagem;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenu jmTexto;
    private javax.swing.JMenuItem jmiImagem;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JTextPane jtpEnunciado;
    // End of variables declaration//GEN-END:variables
}
