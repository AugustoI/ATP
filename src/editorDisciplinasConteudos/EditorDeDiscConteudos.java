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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import telas.MenuGUI;

/**
 *
 * @author Couth
 */
public class EditorDeDiscConteudos extends javax.swing.JFrame {

    /**
     * Creates new form EditorDeDiscConteudos
     */
    DefaultTableModel modelTabelaDisciplina, modelTabelaConteudo;
    ResultSet rs;
    DisciConteudos disciConteudos = new DisciConteudos();
    List<String> strList = new ArrayList<String>();  
    DefaultComboBoxModel modelComboBox;
    int idDisc;
    //Questoes questoes = new Questoes();
    public EditorDeDiscConteudos() {
        initComponents();
        jTabbedPane1.setTitleAt(0, "Disciplinas");
        jTabbedPane1.setTitleAt(1, "Conteúdos");        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Cadastro de Disciplinas e Conteúdos");
        this.getRootPane().setDefaultButton(jbSalvar);
        jtDisciplina.requestFocus();
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //TABELA DISCIPLINAS
        modelTabelaDisciplina = new DefaultTableModel();
        tabelaDisciplina.setModel(modelTabelaDisciplina);
        modelTabelaDisciplina.addColumn("Id");
        modelTabelaDisciplina.addColumn("Disciplina");     
        
        try {
            rs = disciConteudos.pegarDisciplinas();
            if (rs!=null) {
                do {
                    modelTabelaDisciplina.addRow(new Object[]{                        
                        rs.getString("Disciplinas_ID"),
                        rs.getString("NomeDisciplinas"),
                    });
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }
        
        tabelaDisciplina.getColumnModel().getColumn(0).setMinWidth(40);        
        tabelaDisciplina.getColumnModel().getColumn(0).setMaxWidth(40);
        
        tabelaDisciplina.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jbEditarDisc.setEnabled(true);
                jbExcluirDisc.setEnabled(true);
                jbEditar2.setEnabled(true);
                jbExcluir2.setEnabled(true);
                jmiEditar.setEnabled(true);
                jmiExcluir.setEnabled(true);
            }
        });
        
        //TABELA CONTEUDOS
        modelTabelaConteudo = new DefaultTableModel();
        tabelaConteudo.setModel(modelTabelaConteudo);
        modelTabelaConteudo.addColumn("Id");
        modelTabelaConteudo.addColumn("Disciplina");     
        modelTabelaConteudo.addColumn("Conteúdo"); 
        modelTabelaConteudo.addColumn("Série"); 
        try {
            rs = disciConteudos.pegarTUDO();
            if (rs!=null) {
                do {
                    modelTabelaConteudo.addRow(new Object[]{                        
                        rs.getString("Conteudos_ID"), //jcbDisciplina.getSelectedItem().toString(),
                        rs.getString("NomeDisciplinas"),
                        rs.getString("NomeConteudos"),
                        rs.getString("CodSerie"),
                    });
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }     
        
        tabelaConteudo.getColumnModel().getColumn(0).setMinWidth(40);        
        tabelaConteudo.getColumnModel().getColumn(0).setMaxWidth(40);
        
        tabelaConteudo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jbEditarDisc.setEnabled(true);
                jbExcluirDisc.setEnabled(true);
                jbEditar2.setEnabled(true);
                jbExcluir2.setEnabled(true);
                jmiEditar.setEnabled(true);
                jmiExcluir.setEnabled(true);
            }
        });
        
        jtPesqDisc1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                PesquisarDisciplina();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesqDisc1.getText().isEmpty()) {
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
        
        jtPesqDisc2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                PesquisarDisciplina();
                PesquisarConteudos(0);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesqDisc2.getText().isEmpty()) {
                    MostrarDisciplinas();
                    MostrarConteudos();
                } else {
                    PesquisarDisciplina();
                    PesquisarConteudos(0);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //
            }
        }); 
        
        jtPesqConteudo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                PesquisarConteudos(1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesqConteudo.getText().isEmpty()) {
                    MostrarConteudos();
                } else {
                    PesquisarConteudos(1);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jbSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbVoltar = new javax.swing.JButton();
        jtDisciplina = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDisciplina = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jtPesqDisc1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jbExcluirDisc = new javax.swing.JButton();
        jbEditarDisc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jcbDisciplina = new javax.swing.JComboBox<String>();
        jbVoltar1 = new javax.swing.JButton();
        jbSalvar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtPesqDisc2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtConteudo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtSerie = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtPesqConteudo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaConteudo = new javax.swing.JTable();
        jbExcluir2 = new javax.swing.JButton();
        jbEditar2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuBar3 = new javax.swing.JMenuBar();
        jmArquivo2 = new javax.swing.JMenu();
        jmiEditar = new javax.swing.JMenuItem();
        jmiExcluir = new javax.swing.JMenuItem();
        jmiSalvar = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jbSalvar.setText("Cadastrar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jLabel2.setText("Disciplina:");

        jbVoltar.setText("Voltar ao menu");
        jbVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoltarActionPerformed(evt);
            }
        });

        tabelaDisciplina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelaDisciplina);

        jLabel7.setText("Pesquisar disciplina:");

        jbExcluirDisc.setText("Excluir");
        jbExcluirDisc.setEnabled(false);
        jbExcluirDisc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirDiscActionPerformed(evt);
            }
        });

        jbEditarDisc.setText("Editar");
        jbEditarDisc.setEnabled(false);
        jbEditarDisc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarDiscActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPesqDisc1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEditarDisc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluirDisc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jbVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar)
                    .addComponent(jbVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtPesqDisc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExcluirDisc)
                    .addComponent(jbEditarDisc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jLabel14.setText("Disciplina:");

        jcbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisciplinaActionPerformed(evt);
            }
        });

        jbVoltar1.setText("Voltar ao menu");
        jbVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoltar1ActionPerformed(evt);
            }
        });

        jbSalvar2.setText("Cadastrar");
        jbSalvar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvar2jbSalvar2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar disciplina:");

        jLabel3.setText("Conteúdo:");

        jLabel4.setText("Série:");

        jLabel5.setText("Pesquisar conteúdo:");

        tabelaConteudo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabelaConteudo);

        jbExcluir2.setText("Excluir");
        jbExcluir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluir2ActionPerformed(evt);
            }
        });

        jbEditar2.setText("Editar");
        jbEditar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbVoltar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPesqDisc2))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPesqConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbEditar2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbExcluir2)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar2)
                    .addComponent(jbVoltar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPesqDisc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPesqConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jbExcluir2)
                    .addComponent(jbEditar2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jmArquivo2.setText("Arquivo");
        jmArquivo2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jmiEditar.setText("Editar Disciplina");
        jmiEditar.setEnabled(false);
        jmiEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditarActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiEditar);

        jmiExcluir.setText("Excluir Disciplina");
        jmiExcluir.setEnabled(false);
        jmiExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExcluirActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiExcluir);

        jmiSalvar.setText("Salvar");
        jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalvarActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiSalvar);

        jmiSair.setText("Menu principal");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiSair);

        jMenuBar3.add(jmArquivo2);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:        
        if (!jtDisciplina.getText().isEmpty()) {
            int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar esta disciplina?", "Salvar disciplina",
                JOptionPane.YES_NO_CANCEL_OPTION);
            if (x==0) {
                String disciplina = jtDisciplina.getText();                                        
                SalvarDisciplina(disciplina);

                jtDisciplina.setText("");
                JOptionPane.showMessageDialog(this, "Disciplina adicionada com sucesso!");
            }
            
            for (int i=(modelTabelaDisciplina.getRowCount()-1); i>=0; i--) {
                modelTabelaDisciplina.removeRow(i);
            }   
            
            try {
                rs = disciConteudos.pegarDisciplinas();
                if (rs!=null) {
                    do {
                        modelTabelaDisciplina.addRow(new Object[]{                        
                            rs.getString("Disciplinas_ID"),
                            rs.getString("NomeDisciplinas"),
                        });
                    } while (rs.next());
                }
                CarregarComboBox();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
            
            jbEditarDisc.setEnabled(false);
            jbExcluirDisc.setEnabled(false);
            
        } else {
            JOptionPane.showMessageDialog(this, "O nome da disciplina não pode ser vazio!");
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoltarActionPerformed
        // TODO add your handling code here:
        jmiSairActionPerformed(evt);
    }//GEN-LAST:event_jbVoltarActionPerformed

    private void jbVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoltar1ActionPerformed
        // TODO add your handling code here:
        jmiSairActionPerformed(evt);
    }//GEN-LAST:event_jbVoltar1ActionPerformed

    private void jbSalvar2jbSalvar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvar2jbSalvar2ActionPerformed
        // TODO add your handling code here:       
        if ((!jtConteudo.getText().isEmpty())&&(!jtSerie.getText().isEmpty())) {
            if (jcbDisciplina.getItemCount() != 0) {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar este conteúdo?", "Salvar conteúdo",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    try {
                        rs = disciConteudos.pegarIdDisciplina(jcbDisciplina.getSelectedItem().toString());
                        String conteudo = jtConteudo.getText();
                        String serie = jtSerie.getText();                        
                        if (rs!=null) {
                            int idDisciplina = rs.getInt("Disciplinas_ID");
                            if (jcbDisciplina.getSelectedIndex()!=0) {
                                SalvarConteudo(conteudo, serie, idDisciplina);
                                jcbDisciplina.setSelectedIndex(0);
                                jtConteudo.setText("");
                                jtSerie.setText(serie);
                                JOptionPane.showMessageDialog(this, "Conteúdo adicionado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(this, "Esta disciplina não é válida!");
                            }
                        }                         
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                    }
                }

                for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                    modelTabelaConteudo.removeRow(i);
                }   

                try {
                    rs = disciConteudos.pegarTUDO();
                    if (rs!=null) {
                        do {
                            modelTabelaConteudo.addRow(new Object[]{                        
                                rs.getString("Conteudos_ID"), //jcbDisciplina.getSelectedItem().toString(),
                                rs.getString("NomeDisciplinas"),
                                rs.getString("NomeConteudos"),
                                rs.getString("CodSerie"),
                            });
                        } while (rs.next());
                    }
                    CarregarComboBox();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                }

                jbEditarDisc.setEnabled(false);
                jbExcluirDisc.setEnabled(false);
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
    }//GEN-LAST:event_jbSalvar2jbSalvar2ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        if (jTabbedPane1.getSelectedIndex() == 0) {
            jtDisciplina.requestFocus();
            getRootPane().setDefaultButton(jbSalvar);
            jmiEditar.setText("Editar Disciplina");
            jbEditarDisc.setEnabled(false);
            jbExcluirDisc.setEnabled(false);
            jbEditar2.setEnabled(false);
            jbExcluir2.setEnabled(false);
            jmiEditar.setEnabled(false);
            jmiExcluir.setEnabled(false);
        }
        if (jTabbedPane1.getSelectedIndex() == 1) {
            jtPesqDisc2.requestFocus();
            getRootPane().setDefaultButton(jbSalvar2);
            jmiEditar.setText("Editar Conteúdo");
            jbEditarDisc.setEnabled(false);
            jbExcluirDisc.setEnabled(false);
            jbEditar2.setEnabled(false);
            jbExcluir2.setEnabled(false);
            jmiEditar.setEnabled(false);
            jmiExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jmiEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditarActionPerformed
        // TODO add your handling code here:
        /*
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            if (jTabbedPane1.getSelectedIndex() == 0) {
                jbSalvarActionPerformed(evt);
                if (!jtpEnunciado.getText().isEmpty()) {
                    if (jcbConteudo.getItemCount() != 0) {
                        TelaDeQuestoes editor = new TelaDeQuestoes();
                        editor.setVisible(true);
                        editor.setLocationRelativeTo(null);
                        dispose();
                    }
                }
            }
            if (jTabbedPane1.getSelectedIndex() == 1) {
                jbSalvar2ActionPerformed(evt);
                if ((!jtpEnunciado2.getText().isEmpty())&&(!jtpLetraA1.getText().isEmpty())&&(!jtpLetraB1.getText().isEmpty())
                    &&(!jtpLetraC1.getText().isEmpty())&&(!jtpLetraD1.getText().isEmpty())) {
                    if (((!jtpLetraE1.getText().isEmpty())&&(jrbSimE1.isSelected()))||(jrbNãoE1.isSelected())) {
                        if (((!jtpLetraF1.getText().isEmpty())&&(jrbSimF1.isSelected()))||(jrbNãoF1.isSelected())) {
                            if (jcbConteudo.getItemCount() != 0) {
                                TelaDeQuestoes editor = new TelaDeQuestoes();
                                editor.setVisible(true);
                                editor.setLocationRelativeTo(null);
                                dispose();
                            }
                        }
                    }
                }
            }
        }
        if (x==1) {
            TelaDeQuestoes editor = new TelaDeQuestoes();
            editor.setVisible(true);
            editor.setLocationRelativeTo(null);
            dispose();
        }
        */
    }//GEN-LAST:event_jmiEditarActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        // TODO add your handling code here:
        if (jTabbedPane1.getSelectedIndex() == 0) {
            if (!jtDisciplina.getText().isEmpty()) {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    jbSalvarActionPerformed(evt);
                    MenuGUI menu = new MenuGUI();
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
                if (x==1) {
                    MenuGUI menu = new MenuGUI();
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1) {
            if ((!jtConteudo.getText().isEmpty())&&(!jtSerie.getText().isEmpty())) {
                if (jcbDisciplina.getItemCount() != 0) {
                    int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar este conteúdo?", "Salvar conteúdo",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                    if (x==0) {
                        jbSalvar2jbSalvar2ActionPerformed(evt);
                        MenuGUI menu = new MenuGUI();
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if (x==1) {
                        MenuGUI menu = new MenuGUI();
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                }
            }
        }            
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here:
        if (jTabbedPane1.getSelectedIndex() == 0) {
            jbSalvarActionPerformed(evt);
        }
        if (jTabbedPane1.getSelectedIndex() == 1) {
            jbSalvar2jbSalvar2ActionPerformed(evt);
        }
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jbEditarDiscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarDiscActionPerformed
        // TODO add your handling code here:
        /*
        int row = tabelaDisciplina.getSelectedRow();
        int numero = tabelaDisciplina.getSelectedRowCount();
        if (numero == 1) {              
            int id = Integer.parseInt(tabelaDisciplina.getModel().getValueAt(row, 0).toString());            
            
            //EditorDisciplina editor = new EditorDisciplina(this, true, id);
            //editor.setVisible(true);   
            
            for (int i=(modelTabelaDisciplina.getRowCount()-1); i>=0; i--) {
                modelTabelaDisciplina.removeRow(i);
            }   
            
            try {
                rs = disciConteudos.pegarDisciplinas();
                if (rs!=null) {
                    do {
                        modelTabelaDisciplina.addRow(new Object[]{                        
                            rs.getString("Disciplinas_ID"),
                            rs.getString("NomeDisciplinas"),
                        });
                    } while (rs.next());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
            
            jbEditarDisc.setEnabled(false);
            jbExcluirDisc.setEnabled(false);
            jmiEditar.setEnabled(false);
            jmiExcluir.setEnabled(false);
        }
        */
    }//GEN-LAST:event_jbEditarDiscActionPerformed

    private void jbExcluirDiscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirDiscActionPerformed
        // TODO add your handling code here:
        /*
        int numero = tabelaDisciplina.getSelectedRowCount();
        String mensagem;               
        if (numero == 1) {
            mensagem = "Tem certeza que deseja excluir esta disciplina?";
        } else { 
            mensagem = "Tem certeza que deseja excluir "+numero+" disciplinas?";
        }
        
        int[] row = tabelaDisciplina.getSelectedRows();
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), mensagem, "Excluir dados", 
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {          
            for (int i=0; i<numero; i++) {
                try {
                    int id = Integer.parseInt(tabelaDisciplina.getModel().getValueAt(row[i], 0).toString());
                    disciConteudos.excluirDisciplinaPeloId(id);
                    if ((i==0)&&((i+1)==numero)) {
                        JOptionPane.showMessageDialog(this, "Disciplina excluida com sucesso!");
                    } else if ((i+1)==numero) {
                        JOptionPane.showMessageDialog(this, "Disciplinas excluidas com sucesso!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir.\nErro: "+ex);
                }
            }            
            
            for (int i=(modelTabelaDisciplina.getRowCount()-1); i>=0; i--) {
                modelTabelaDisciplina.removeRow(i);
            }   
            
            try {
                rs = disciConteudos.pegarDisciplinas();
                if (rs!=null) {
                    do {
                        modelTabelaDisciplina.addRow(new Object[]{                        
                            rs.getString("Disciplinas_ID"),
                            rs.getString("NomeDisciplinas"),
                        });
                    } while (rs.next());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
            
            jbEditarDisc.setEnabled(false);
            jbExcluirDisc.setEnabled(false);
            //jmiEditar.setEnabled(false);
            //jmiExcluir.setEnabled(false);
        }
        */
    }//GEN-LAST:event_jbExcluirDiscActionPerformed

    private void jcbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisciplinaActionPerformed
        // TODO add your handling code here:
        for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
            modelTabelaConteudo.removeRow(i);
        }        
        try {            
            String conteudo = jcbDisciplina.getSelectedItem().toString();
            if (conteudo.equals("Todos os conteúdos")) {
                rs = disciConteudos.pegarTUDO();
                if (rs!=null) {
                    do {
                        modelTabelaConteudo.addRow(new Object[]{                        
                            rs.getString("Conteudos_ID"), //jcbDisciplina.getSelectedItem().toString(),
                            rs.getString("NomeDisciplinas"),
                            rs.getString("NomeConteudos"),
                            rs.getString("CodSerie"),
                        });
                    } while (rs.next());
                }
            } else {
                rs = disciConteudos.pegarConteudos(jcbDisciplina.getSelectedItem().toString());            
                if (rs!=null) {
                    do {
                        modelTabelaConteudo.addRow(new Object[]{                        
                            rs.getString("Conteudos_ID"), jcbDisciplina.getSelectedItem().toString(),
                            rs.getString("NomeConteudos"),
                            rs.getString("CodSerie"),
                        });
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }        
    }//GEN-LAST:event_jcbDisciplinaActionPerformed

    private void jbEditar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbEditar2ActionPerformed

    private void jbExcluir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluir2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbExcluir2ActionPerformed

    private void jmiExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(EditorDeDiscConteudos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorDeDiscConteudos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorDeDiscConteudos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorDeDiscConteudos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorDeDiscConteudos().setVisible(true);
            }
        });
    }
    
    //CARREGAR COMBOBOX
    public void CarregarComboBox() {     
        try {
            //Disciplinas   
            strList.removeAll(strList);
            strList.add("Todos os conteúdos");
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
    
    //SALVAR DISCIPLINA
    public void SalvarDisciplina(String disciplina) {        
        try {
            disciConteudos.inserirDisciplina(disciplina);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } 
    }
    
    //SALVAR CONTEUDO
    public void SalvarConteudo(String conteudo, String serie, int idDisciplina) {        
        try {
            disciConteudos.inserirConteudo(conteudo, serie, idDisciplina);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } 
    }
    
    //PESQUISAR DISCIPLINA
    public void PesquisarDisciplina() {
        if (jTabbedPane1.getSelectedIndex() == 0) {
            String enunciado = jtPesqDisc1.getText();
            if (!enunciado.isEmpty()) {
                try {
                    for (int i=(modelTabelaDisciplina.getRowCount()-1); i>=0; i--) {
                        modelTabelaDisciplina.removeRow(i);
                    }            
                    rs = disciConteudos.pesquisarDisciplina(enunciado);
                    if (rs!=null) {                
                        do {
                            modelTabelaDisciplina.addRow(new Object[]{
                                rs.getString("Disciplinas_ID"),
                                rs.getString("NomeDisciplinas"),
                            });
                        } while (rs.next());
                        tabelaDisciplina.setModel(modelTabelaDisciplina);
                    }  
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Você não digitou nada no campo de pesquisa!");
            }
        }
        
        if (jTabbedPane1.getSelectedIndex() == 1) {
            String enunciado = jtPesqDisc2.getText();
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
    }
    
    //PESQUISAR CONTEUDO
    public void PesquisarConteudos(int abc) {
        if (jcbDisciplina.getItemCount() != 0) {
            if (abc==0) {
                String disciplina = jcbDisciplina.getSelectedItem().toString();
                if (!disciplina.isEmpty()) {
                    try {
                        for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                            modelTabelaConteudo.removeRow(i);
                        }            
                        rs = disciConteudos.pegarIdDisciplina(disciplina);                    
                        if (rs!=null) {
                            idDisc = rs.getInt("Disciplinas_ID");

                            rs = disciConteudos.pesquisarConteudos(idDisc);
                            if (rs!=null) {
                                do {
                                    modelTabelaConteudo.addRow(new Object[]{
                                        rs.getString("Conteudos_ID"), jcbDisciplina.getSelectedItem().toString(),
                                        rs.getString("NomeConteudos"),
                                        rs.getString("CodSerie"),
                                    });
                                } while (rs.next());
                                tabelaConteudo.setModel(modelTabelaConteudo);
                            }  
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Você não digitou nada no campo de pesquisa!");
                }  
            }
            
            if (abc==1) {
                try {
                    String conteudo = jtPesqConteudo.getText();
                    for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                        modelTabelaConteudo.removeRow(i);
                    }            
                    rs = disciConteudos.pesquisarConteudos(conteudo); 
                    if (rs!=null) {
                        do {
                            modelTabelaConteudo.addRow(new Object[]{                        
                                rs.getString("Conteudos_ID"), //jcbDisciplina.getSelectedItem().toString(),
                                rs.getString("NomeDisciplinas"),
                                rs.getString("NomeConteudos"),
                                rs.getString("CodSerie"),
                            });
                        } while (rs.next());
                        tabelaConteudo.setModel(modelTabelaConteudo);                                 
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
                }
            }
        } else {
            for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                modelTabelaConteudo.removeRow(i);
            }  
        }
    }
    
    //MOSTRAR TODAS DISCIPLINAS
    public void MostrarDisciplinas() {
        if (jTabbedPane1.getSelectedIndex() == 0) {
            try {
                for (int i=(modelTabelaDisciplina.getRowCount()-1); i>=0; i--) {
                    modelTabelaDisciplina.removeRow(i);
                }
                rs = disciConteudos.pegarDisciplinas();
                if (rs!=null) {                
                    do {
                        modelTabelaDisciplina.addRow(new Object[]{
                            rs.getString("Disciplinas_ID"),
                            rs.getString("NomeDisciplinas"),
                        });
                    } while (rs.next());
                    tabelaDisciplina.setModel(modelTabelaDisciplina);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
        }
        
        if (jTabbedPane1.getSelectedIndex() == 1) {
            CarregarComboBox();
        }
    }
    
    //MOSTRAR TODOS CONTEUDOS
    public void MostrarConteudos() {
        try {
            for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                modelTabelaConteudo.removeRow(i);
            }
            rs = disciConteudos.pegarTUDO();
            if (rs!=null) {
                do {
                    modelTabelaConteudo.addRow(new Object[]{                        
                        rs.getString("Conteudos_ID"), //jcbDisciplina.getSelectedItem().toString(),
                        rs.getString("NomeDisciplinas"),
                        rs.getString("NomeConteudos"),
                        rs.getString("CodSerie"),
                    });
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbEditar2;
    private javax.swing.JButton jbEditarDisc;
    private javax.swing.JButton jbExcluir2;
    private javax.swing.JButton jbExcluirDisc;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSalvar2;
    private javax.swing.JButton jbVoltar;
    private javax.swing.JButton jbVoltar1;
    private javax.swing.JComboBox<String> jcbDisciplina;
    private javax.swing.JMenu jmArquivo2;
    private javax.swing.JMenuItem jmiEditar;
    private javax.swing.JMenuItem jmiExcluir;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JTextField jtConteudo;
    private javax.swing.JTextField jtDisciplina;
    private javax.swing.JTextField jtPesqConteudo;
    private javax.swing.JTextField jtPesqDisc1;
    private javax.swing.JTextField jtPesqDisc2;
    private javax.swing.JTextField jtSerie;
    private javax.swing.JTable tabelaConteudo;
    private javax.swing.JTable tabelaDisciplina;
    // End of variables declaration//GEN-END:variables
}
