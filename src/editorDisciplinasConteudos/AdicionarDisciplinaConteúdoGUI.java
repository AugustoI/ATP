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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
public class AdicionarDisciplinaConteúdoGUI extends javax.swing.JFrame {

    /**
     * Creates new form AdicionarDisciplinaConteúdoGUI
     */
    DefaultTableModel modelTabelaDisciplina, modelTabelaConteudo;
    ResultSet rs;
    DisciConteudos disciConteudos = new DisciConteudos();
    List<String> strList = new ArrayList<String>();  
    DefaultComboBoxModel modelComboBox;
    int idDisc;
    boolean tabbed1, tabbed2;
    
    public AdicionarDisciplinaConteúdoGUI() {
        initComponents();
        tabbedPane.setTitleAt(0, "Disciplinas");
        tabbedPane.setTitleAt(1, "Conteúdos");        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Cadastro de Disciplinas e Conteúdos");
        this.getRootPane().setDefaultButton(jbSalvar);
        jtDisciplina.requestFocus();
        
        tabbed1 = true;
        tabbed2 = false;
        
        //Carregar combobox conteúdos
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
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
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
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
                jmiEditar.setEnabled(true);
                jmiExcluir.setEnabled(true);
            }
        });
        
        jtPesquisarDisciplina.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                PesquisarDisciplina();
                if (tabbedPane.getSelectedIndex()==1)
                    PesquisarConteudos(0);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesquisarDisciplina.getText().isEmpty()) {
                    MostrarDisciplinas();
                    if (tabbedPane.getSelectedIndex()==1)
                        MostrarConteudos();
                } else {
                    PesquisarDisciplina();
                    if (tabbedPane.getSelectedIndex()==1)
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
        
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    tabbed1 = true;
                    tabbed2 = false;
                    jmiSalvar.setText("Salvar Disciplina");
                    jmiEditar.setText("Editar Disciplina");
                    jmiExcluir.setText("Excluir Disciplina");
                }
                if (tabbedPane.getSelectedIndex() == 1) {
                    tabbed1 = false;
                    tabbed2 = true;
                    jmiSalvar.setText("Salvar Conteúdo");
                    jmiEditar.setText("Editar Conteúdo");
                    jmiExcluir.setText("Excluir Conteúdo");
                }
                CarregarComboBox();
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
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel11 = new javax.swing.JLabel();
        jtPesquisarDisciplina = new javax.swing.JTextField();
        jbPesquisarDisciplina1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbMenu = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtDisciplina = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDisciplina = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbDisciplina = new javax.swing.JComboBox();
        jtConteudo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtSerie = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaConteudo = new javax.swing.JTable();
        jtPesqConteudo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jmArquivo2 = new javax.swing.JMenu();
        jmiSalvar = new javax.swing.JMenuItem();
        jmiEditar = new javax.swing.JMenuItem();
        jmiExcluir = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jbEditar.setBackground(new java.awt.Color(178, 203, 243));
        jbEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editFile.png"))); // NOI18N
        jbEditar.setToolTipText("Editar Questões");
        jbEditar.setEnabled(false);
        jbEditar.setFocusable(false);
        jbEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbEditar);

        jbExcluir.setBackground(new java.awt.Color(178, 203, 243));
        jbExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeFile.png"))); // NOI18N
        jbExcluir.setEnabled(false);
        jbExcluir.setFocusable(false);
        jbExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(jbExcluir);
        jToolBar1.add(jSeparator5);

        jLabel11.setText("Filtrar Disciplinas:");
        jToolBar1.add(jLabel11);

        jtPesquisarDisciplina.setColumns(40);
        jToolBar1.add(jtPesquisarDisciplina);

        jbPesquisarDisciplina1.setBackground(new java.awt.Color(178, 203, 243));
        jbPesquisarDisciplina1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/search.png"))); // NOI18N
        jbPesquisarDisciplina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarDisciplina1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbPesquisarDisciplina1);
        jToolBar1.add(jSeparator1);

        jbMenu.setBackground(new java.awt.Color(178, 203, 243));
        jbMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit.png"))); // NOI18N
        jbMenu.setToolTipText("Menu Principal");
        jbMenu.setFocusable(false);
        jbMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMenuActionPerformed(evt);
            }
        });
        jToolBar1.add(jbMenu);

        tabbedPane.setBackground(new java.awt.Color(209, 224, 248));

        jPanel2.setBackground(new java.awt.Color(209, 224, 248));

        jLabel1.setText("Disciplina:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nova disciplina");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Disciplinas cadastradas");

        tabelaDisciplina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaDisciplina.setForeground(new java.awt.Color(51, 51, 51));
        tabelaDisciplina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaDisciplina.setGridColor(new java.awt.Color(255, 255, 255));
        tabelaDisciplina.setRowHeight(20);
        tabelaDisciplina.setSelectionBackground(new java.awt.Color(122, 203, 243));
        jScrollPane1.setViewportView(tabelaDisciplina);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtDisciplina))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(209, 224, 248));

        jLabel6.setText("Disciplina:");

        jLabel7.setText("Conteúdo:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Novo conteúdo");

        jcbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisciplinaActionPerformed(evt);
            }
        });

        jLabel8.setText("Série:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Conteúdos cadastrados");

        tabelaConteudo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaConteudo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaConteudo.setGridColor(new java.awt.Color(255, 255, 255));
        tabelaConteudo.setRowHeight(20);
        tabelaConteudo.setSelectionBackground(new java.awt.Color(122, 203, 243));
        jScrollPane2.setViewportView(tabelaConteudo);

        jLabel12.setText("Filtrar Conteúdos:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtConteudo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPesqConteudo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jtConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPesqConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab2", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane))
        );

        jmArquivo2.setText("Arquivo");

        jmiSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/saveFile.png"))); // NOI18N
        jmiSalvar.setText("Salvar Disciplina");
        jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalvarActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiSalvar);

        jmiEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editFile.png"))); // NOI18N
        jmiEditar.setText("Editar Disciplina");
        jmiEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditarActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiEditar);

        jmiExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeFile.png"))); // NOI18N
        jmiExcluir.setText("Excluir Disciplina");
        jmiExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExcluirActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiExcluir);
        jmArquivo2.add(jSeparator7);

        jmiMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit.png"))); // NOI18N
        jmiMenu.setText("Menu Principal");
        jmiMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMenuActionPerformed(evt);
            }
        });
        jmArquivo2.add(jmiMenu);

        jMenuBar3.add(jmArquivo2);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:
        if (tabbed1) {
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

                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                jmiEditar.setEnabled(false);
                jmiExcluir.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "O nome da disciplina não pode ser vazio!");
            }   
        }
        if (tabbed2) {
            if ((!jtConteudo.getText().isEmpty())&&(!jtSerie.getText().isEmpty())) {
                if (jcbDisciplina.getSelectedIndex()!= 0) {
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

                    jbEditar.setEnabled(false);
                    jbExcluir.setEnabled(false);
                    jmiEditar.setEnabled(false);
                    jmiExcluir.setEnabled(false);
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
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        if (tabbed1) {
            int row = tabelaDisciplina.getSelectedRow();
            int numero = tabelaDisciplina.getSelectedRowCount();
            if (numero == 1) {              
                int id = Integer.parseInt(tabelaDisciplina.getModel().getValueAt(row, 0).toString());            
                String disciplina = tabelaDisciplina.getModel().getValueAt(row, 1).toString();

                EditarDisciplinaGUI editor = new EditarDisciplinaGUI(this, true, id, disciplina);
                editor.setVisible(true);   

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

                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                jmiEditar.setEnabled(false);
                jmiExcluir.setEnabled(false);
            }
        }
        if (tabbed2) {
            int row = tabelaConteudo.getSelectedRow();
            int numero = tabelaConteudo.getSelectedRowCount();
            if (numero == 1) {              
                int id = Integer.parseInt(tabelaConteudo.getModel().getValueAt(row, 0).toString());   

                EditarConteudosGUI editor = new EditarConteudosGUI(this, true, id);
                editor.setVisible(true);   

                for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                    modelTabelaConteudo.removeRow(i);
                }   

                try {
                    if (jcbDisciplina.getSelectedIndex()==0) {
                        rs = disciConteudos.pegarTUDO();
                    } else {
                        rs = disciConteudos.pegarConteudos(jcbDisciplina.getSelectedItem().toString());  
                    }
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

                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                jmiEditar.setEnabled(false);
                jmiExcluir.setEnabled(false);          
            }    
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMenuActionPerformed
        // TODO add your handling code here:
        MenuGUI menu = new MenuGUI();
        if (tabbed1) {
            if (!jtDisciplina.getText().isEmpty()) {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    jbSalvarActionPerformed(evt);
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
                if (x==1) {
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
            } else {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja realmente voltar ao menu?", "Voltar ao MENU",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
            }
        }
        if (tabbed2) {
            if ((!jtConteudo.getText().isEmpty())&&(!jtSerie.getText().isEmpty())) {
                if (jcbDisciplina.getItemCount() != 0) {
                    int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                    if (x==0) {
                        jbSalvarActionPerformed(evt);
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                    if (x==1) {
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                }
            } else {
                int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja realmente voltar ao menu?", "Voltar ao MENU",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (x==0) {
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    dispose();
                }
            }
        }    
    }//GEN-LAST:event_jbMenuActionPerformed

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here:
        jbSalvarActionPerformed(evt);
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jmiEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditarActionPerformed
        // TODO add your handling code here:
        jbEditarActionPerformed(evt);
    }//GEN-LAST:event_jmiEditarActionPerformed

    private void jmiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMenuActionPerformed
        // TODO add your handling code here:
        jbMenuActionPerformed(evt);
    }//GEN-LAST:event_jmiMenuActionPerformed

    private void jcbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisciplinaActionPerformed
        // TODO add your handling code here:        
        jtConteudo.requestFocus();
        
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

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        // TODO add your handling code here:
        if (tabbed1) {
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

                for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                    modelTabelaConteudo.removeRow(i);
                }   

                try {
                    if (jcbDisciplina.getSelectedIndex()==0) {
                        rs = disciConteudos.pegarTUDO();
                    } else {
                        rs = disciConteudos.pegarConteudos(jcbDisciplina.getSelectedItem().toString());  
                    }
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

                CarregarComboBox();

                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                jmiEditar.setEnabled(false);
                jmiExcluir.setEnabled(false);
            }   
        }
        if (tabbed2) {
            int numero = tabelaConteudo.getSelectedRowCount();
            String mensagem;               
            if (numero == 1) {
                mensagem = "Tem certeza que deseja excluir este conteúdo?";
            } else { 
                mensagem = "Tem certeza que deseja excluir "+numero+" conteúdos?";
            }

            int[] row = tabelaConteudo.getSelectedRows();
            int x = JOptionPane.showConfirmDialog(this.getContentPane(), mensagem, "Excluir dados", 
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (x==0) {          
                for (int i=0; i<numero; i++) {
                    try {
                        int id = Integer.parseInt(tabelaConteudo.getModel().getValueAt(row[i], 0).toString());
                        disciConteudos.excluirConteudoPeloId(id);
                        if ((i==0)&&((i+1)==numero)) {
                            JOptionPane.showMessageDialog(this, "Conteúdo excluido com sucesso!");
                        } else if ((i+1)==numero) {
                            JOptionPane.showMessageDialog(this, "Conteúdos excluidos com sucesso!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir.\nErro: "+ex);
                    }
                }            

                for (int i=(modelTabelaConteudo.getRowCount()-1); i>=0; i--) {
                    modelTabelaConteudo.removeRow(i);
                }   

                try {
                    if (jcbDisciplina.getSelectedIndex()==0) {
                        rs = disciConteudos.pegarTUDO();
                    } else {
                        rs = disciConteudos.pegarConteudos(jcbDisciplina.getSelectedItem().toString());  
                    }
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

                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                jmiEditar.setEnabled(false);
                jmiExcluir.setEnabled(false);
            }    
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jmiExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExcluirActionPerformed
        // TODO add your handling code here:
        jbExcluirActionPerformed(evt);
    }//GEN-LAST:event_jmiExcluirActionPerformed

    private void jbPesquisarDisciplina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarDisciplina1ActionPerformed
        // TODO add your handling code here:
        PesquisarDisciplina();
    }//GEN-LAST:event_jbPesquisarDisciplina1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdicionarDisciplinaConteúdoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdicionarDisciplinaConteúdoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdicionarDisciplinaConteúdoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarDisciplinaConteúdoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdicionarDisciplinaConteúdoGUI().setVisible(true);
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
        if (tabbedPane.getSelectedIndex() == 0) {
            String enunciado = jtPesquisarDisciplina.getText();
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
        
        if (tabbedPane.getSelectedIndex() == 1) {
            String enunciado = jtPesquisarDisciplina.getText();
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
        if (tabbedPane.getSelectedIndex() == 0) {
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
        
        if (tabbedPane.getSelectedIndex() == 1) {
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbMenu;
    private javax.swing.JButton jbPesquisarDisciplina1;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox jcbDisciplina;
    private javax.swing.JMenu jmArquivo2;
    private javax.swing.JMenuItem jmiEditar;
    private javax.swing.JMenuItem jmiExcluir;
    private javax.swing.JMenuItem jmiMenu;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JTextField jtConteudo;
    private javax.swing.JTextField jtDisciplina;
    private javax.swing.JTextField jtPesqConteudo;
    private javax.swing.JTextField jtPesquisarDisciplina;
    private javax.swing.JTextField jtSerie;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tabelaConteudo;
    private javax.swing.JTable tabelaDisciplina;
    // End of variables declaration//GEN-END:variables
}
