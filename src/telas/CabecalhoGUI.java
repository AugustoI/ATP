/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import banco.Cabecalho;
import editorQuestoes.TesteImagem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class CabecalhoGUI extends javax.swing.JFrame {

    /**
     * Creates new form CabecalhoGUI
     */
    JFrame frame = new JFrame();
    Cabecalho cabecalho = new Cabecalho();
    
    FileInputStream input;
    String fileName;
    boolean t1, t2, t3, t4, t5, t6;
    public CabecalhoGUI() {
        initComponents();
        this.setTitle("Cadastro de Cabeçalho");
        this.setLocationRelativeTo(null);
        
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        
        jlImagem.setMaximumSize(new Dimension(14, 254));
        
        //MenuItem Negrito
        Action boldAction = new CabecalhoGUI.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jmTexto.add(boldAction);  
        jbNegrito.addActionListener(boldAction);
        
        //MenuItem Itálico
        Action italicAction = new CabecalhoGUI.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jmTexto.add(italicAction);
        jbItalico.addActionListener(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new CabecalhoGUI.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jmTexto.add(underlineAction);
        jbSublinhado.addActionListener(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new CabecalhoGUI.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jmTexto.add(foregroundAction);
        jbCor.addActionListener(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new CabecalhoGUI.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jmTexto.add(formatTextAction);
        jbFonte.addActionListener(formatTextAction);
        
        jtNome.requestFocus();
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
        jtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbNegrito = new javax.swing.JButton();
        jbItalico = new javax.swing.JButton();
        jbSublinhado = new javax.swing.JButton();
        jbCor = new javax.swing.JButton();
        jbFonte = new javax.swing.JButton();
        jbImagem = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbVoltar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jlImagem = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtTitulo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtSubtitulo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpInstrucoes = new javax.swing.JTextPane();
        jtSerie = new javax.swing.JTextField();
        jtValor = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiImagem = new javax.swing.JMenuItem();
        jmiSalvar = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();
        jmTexto = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome da instituição:");

        jtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtNomeFocusGained(evt);
            }
        });

        jLabel2.setText("Série:");

        jLabel3.setText("Valor:");

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

        jbImagem.setText("Carregar imagem");
        jbImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImagemActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbVoltar.setText("Voltar ao menu");
        jbVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoltarActionPerformed(evt);
            }
        });

        jLabel4.setText("Imagem:");

        jlImagem.setText("Não há imagem selecionada");

        jLabel6.setText("Título:");

        jtTitulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtTituloFocusGained(evt);
            }
        });

        jLabel7.setText("Subtítulo:");

        jtSubtitulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtSubtituloFocusGained(evt);
            }
        });

        jLabel8.setText("Instruções:");

        jtpInstrucoes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpInstrucoesFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtpInstrucoes);

        jtSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtSerieFocusGained(evt);
            }
        });

        jtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtValorFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                        .addComponent(jbFonte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jbVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbImagem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSubtitulo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSerie)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNegrito)
                    .addComponent(jbItalico)
                    .addComponent(jbSublinhado)
                    .addComponent(jbCor)
                    .addComponent(jbFonte)
                    .addComponent(jbSalvar)
                    .addComponent(jbVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbImagem)
                    .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jtSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlImagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        );

        jMenu1.setText("Arquivo");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jmiImagem.setText("Carregar imagem");
        jmiImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImagemActionPerformed(evt);
            }
        });
        jMenu1.add(jmiImagem);

        jmiSalvar.setText("Salvar");
        jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSalvar);

        jmiSair.setText("Menu principal");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSair);

        jMenuBar1.add(jMenu1);

        jmTexto.setText("Texto");
        jmTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuBar1.add(jmTexto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:
        try {
            String nome = jtNome.getText();
            String instrucoes = jtpInstrucoes.getText();
            String titulo = jtTitulo.getText();
            String subtitulo = jtSubtitulo.getText();
            String serie = jtSerie.getText();
            String valor = jtSerie.getText();
            if ((!nome.isEmpty())&&(!instrucoes.isEmpty())) {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Os dados estão corretos? Salvar agora?", "Salvar cabeçalho",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    cabecalho.inserirCabecalho(nome, instrucoes, titulo, subtitulo, serie, valor, input, fileName);
                    JOptionPane.showMessageDialog(this, "Cabeçalho cadastrado com sucesso!");
                    MenuGUI menu = new MenuGUI();
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
            } else {
                if ((nome.isEmpty())&&(instrucoes.isEmpty())) {
                    JOptionPane.showMessageDialog(this, "Nome da instituição e a instrução não podem ser vazias!");
                } else if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome da instituição não pode ser vazio!");
                } else if (instrucoes.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "A instrução não pode ser vazia!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex);
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImagemActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(
                "Imagens", "png", "jpg");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(fileNameExtensionFilter);
        chooser.setDialogTitle("Selecione a imagem");
        int resposta = chooser.showOpenDialog(null);
        if (resposta == JFileChooser.APPROVE_OPTION) {
            try {
                File file = new File(chooser.getSelectedFile().getAbsolutePath());      
                fileName = file.getName();
                input = new FileInputStream(file);  
                jlImagem.setText(fileName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: "+e);
            }
        }
    }//GEN-LAST:event_jbImagemActionPerformed

    private void jbVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoltarActionPerformed
        // TODO add your handling code here:
        jmiSairActionPerformed(evt);
    }//GEN-LAST:event_jbVoltarActionPerformed

    private void jmiImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiImagemActionPerformed
        // TODO add your handling code here:
        jbImagemActionPerformed(evt);
    }//GEN-LAST:event_jmiImagemActionPerformed

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here:
        jbSalvarActionPerformed(evt);
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        // TODO add your handling code here:        
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            jbSalvarActionPerformed(evt);
            if (!jtNome.getText().isEmpty()) {
                if (!jtpInstrucoes.getText().isEmpty()) {
                    dispose();
                }
            }
        }
        if (x==1) {
            dispose();
        }
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jbFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFonteActionPerformed
        // TODO add your handling code here:
        jtpInstrucoes.requestFocus();
    }//GEN-LAST:event_jbFonteActionPerformed

    private void jbCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCorActionPerformed
        // TODO add your handling code here:
        jtpInstrucoes.requestFocus();
    }//GEN-LAST:event_jbCorActionPerformed

    private void jbSublinhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSublinhadoActionPerformed
        // TODO add your handling code here:
        jtpInstrucoes.requestFocus();
    }//GEN-LAST:event_jbSublinhadoActionPerformed

    private void jbItalicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbItalicoActionPerformed
        // TODO add your handling code here:
        jtpInstrucoes.requestFocus();
    }//GEN-LAST:event_jbItalicoActionPerformed

    private void jbNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNegritoActionPerformed
        // TODO add your handling code here:
        jtpInstrucoes.requestFocus();
    }//GEN-LAST:event_jbNegritoActionPerformed

    private void jtNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtNomeFocusGained
        // TODO add your handling code here:
        t1 = true;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
    }//GEN-LAST:event_jtNomeFocusGained

    private void jtTituloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtTituloFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = true;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
    }//GEN-LAST:event_jtTituloFocusGained

    private void jtSubtituloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtSubtituloFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = true;
        t4 = false;
        t5 = false;
        t6 = false;
    }//GEN-LAST:event_jtSubtituloFocusGained

    private void jtSerieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtSerieFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = true;
        t5 = false;
        t6 = false;
    }//GEN-LAST:event_jtSerieFocusGained

    private void jtValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtValorFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = true;
        t6 = false;
    }//GEN-LAST:event_jtValorFocusGained

    private void jtpInstrucoesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpInstrucoesFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = true;
    }//GEN-LAST:event_jtpInstrucoesFocusGained

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
            java.util.logging.Logger.getLogger(CabecalhoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CabecalhoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CabecalhoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CabecalhoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CabecalhoGUI().setVisible(true);
            }
        });
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
            formatText.setLocationRelativeTo(frame.getContentPane());
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCor;
    private javax.swing.JButton jbFonte;
    private javax.swing.JButton jbImagem;
    private javax.swing.JButton jbItalico;
    private javax.swing.JButton jbNegrito;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSublinhado;
    private javax.swing.JButton jbVoltar;
    private javax.swing.JLabel jlImagem;
    private javax.swing.JMenu jmTexto;
    private javax.swing.JMenuItem jmiImagem;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTextField jtSerie;
    private javax.swing.JTextField jtSubtitulo;
    private javax.swing.JTextField jtTitulo;
    private javax.swing.JTextField jtValor;
    private javax.swing.JTextPane jtpInstrucoes;
    // End of variables declaration//GEN-END:variables
}
