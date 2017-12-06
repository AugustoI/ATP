/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorCabecalho;

import banco.Cabecalho;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CabecalhosGUI extends javax.swing.JFrame {

    /**
     * Creates new form CabecalhosGUI
     */
    DefaultTableModel modelTabela;
    ResultSet rs;
    Cabecalho cabecalho = new Cabecalho();
    public CabecalhosGUI() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        //TABELA QUESTÕES
        modelTabela = new DefaultTableModel();
        tabelaCabecalhos.setModel(modelTabela);
        modelTabela.addColumn("Id");
        modelTabela.addColumn("Nome da Instituição");
        modelTabela.addColumn("Título");
        modelTabela.addColumn("Série");
        modelTabela.addColumn("Valor");        
        
        try {
            rs = cabecalho.pegarCabecalho();
            if (rs!=null) {
                do {
                    modelTabela.addRow(new Object[]{                        
                        rs.getString("Cabecalho_ID"),
                        rs.getString("NomeInstituicao"),
                        rs.getString("Titulo"),
                        rs.getString("Serie"),
                        rs.getString("Valor"),
                    });
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
        }
        
        tabelaCabecalhos.getColumnModel().getColumn(0).setMinWidth(40);
        tabelaCabecalhos.getColumnModel().getColumn(1).setMinWidth(175);
        tabelaCabecalhos.getColumnModel().getColumn(2).setMinWidth(125);
        tabelaCabecalhos.getColumnModel().getColumn(3).setMinWidth(75);
        tabelaCabecalhos.getColumnModel().getColumn(4).setMinWidth(100);
        
        tabelaCabecalhos.getColumnModel().getColumn(0).setMaxWidth(40);
        tabelaCabecalhos.getColumnModel().getColumn(3).setMaxWidth(75);
        tabelaCabecalhos.getColumnModel().getColumn(4).setMaxWidth(100);  
        
        tabelaCabecalhos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jbEditar.setEnabled(true);
                jbRemover.setEnabled(true);
                jmiEditar.setEnabled(true);
                jmiRemover.setEnabled(true);
            }
        });
        
        jtPesquisar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jbPesquisarActionPerformed(null);                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (jtPesquisar.getText().isEmpty()) {
                    verTodas();
                } else {
                    jbPesquisarActionPerformed(null);
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
        jToolBar2 = new javax.swing.JToolBar();
        jbNovo = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jbEditar = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtPesquisar = new javax.swing.JTextField();
        jbPesquisar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jbMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCabecalhos = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jMenuBar6 = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmiNovo = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmiEditar = new javax.swing.JMenuItem();
        jmiRemover = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(178, 203, 243));

        jToolBar2.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setBorderPainted(false);

        jbNovo.setBackground(new java.awt.Color(178, 203, 243));
        jbNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/newFile.png"))); // NOI18N
        jbNovo.setToolTipText("Adicionar Questão");
        jbNovo.setFocusable(false);
        jbNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });
        jToolBar2.add(jbNovo);
        jToolBar2.add(jSeparator5);

        jbEditar.setBackground(new java.awt.Color(178, 203, 243));
        jbEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editFile.png"))); // NOI18N
        jbEditar.setToolTipText("Editar Questão");
        jbEditar.setEnabled(false);
        jbEditar.setFocusable(false);
        jbEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbEditar);

        jbRemover.setBackground(new java.awt.Color(178, 203, 243));
        jbRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeFile.png"))); // NOI18N
        jbRemover.setToolTipText("Remover Questão");
        jbRemover.setEnabled(false);
        jbRemover.setFocusable(false);
        jbRemover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRemover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });
        jToolBar2.add(jbRemover);
        jToolBar2.add(jSeparator4);

        jLabel2.setText("Filtrar Cabeçalhos:");
        jToolBar2.add(jLabel2);

        jLabel3.setText("  ");
        jToolBar2.add(jLabel3);

        jtPesquisar.setColumns(25);
        jtPesquisar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtPesquisar.setToolTipText("");
        jToolBar2.add(jtPesquisar);

        jbPesquisar.setBackground(new java.awt.Color(178, 203, 243));
        jbPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/search.png"))); // NOI18N
        jbPesquisar.setToolTipText("Pesquisar");
        jbPesquisar.setFocusable(false);
        jbPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbPesquisar);
        jToolBar2.add(jSeparator3);

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
        jToolBar2.add(jbMenu);

        tabelaCabecalhos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabelaCabecalhos.setForeground(new java.awt.Color(51, 51, 51));
        tabelaCabecalhos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCabecalhos.setGridColor(new java.awt.Color(255, 255, 255));
        tabelaCabecalhos.setRowHeight(20);
        tabelaCabecalhos.setSelectionBackground(new java.awt.Color(122, 203, 243));
        tabelaCabecalhos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabelaCabecalhosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCabecalhos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        jmArquivo.setText("Arquivo");

        jmiNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/newFile.png"))); // NOI18N
        jmiNovo.setText("Adicionar Questão");
        jmiNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovoActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiNovo);
        jmArquivo.add(jSeparator6);

        jmiEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editFile.png"))); // NOI18N
        jmiEditar.setText("Editar Questão");
        jmiEditar.setEnabled(false);
        jmiEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditarActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiEditar);

        jmiRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeFile.png"))); // NOI18N
        jmiRemover.setText("Remover Questão");
        jmiRemover.setEnabled(false);
        jmiRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoverActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiRemover);
        jmArquivo.add(jSeparator7);

        jmiMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit.png"))); // NOI18N
        jmiMenu.setText("Menu Principal");
        jmiMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMenuActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiMenu);

        jMenuBar6.add(jmArquivo);

        setJMenuBar(jMenuBar6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditarActionPerformed
        // TODO add your handling code here:
        jbEditarActionPerformed(evt);
    }//GEN-LAST:event_jmiEditarActionPerformed

    private void jmiRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoverActionPerformed
        // TODO add your handling code here:
        jbRemoverActionPerformed(evt);
    }//GEN-LAST:event_jmiRemoverActionPerformed

    private void jmiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMenuActionPerformed
        // TODO add your handling code here:
        jbMenuActionPerformed(evt);
    }//GEN-LAST:event_jmiMenuActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        // TODO add your handling code here:
        AdicionarCabecalhoGUI cabecalho = new AdicionarCabecalhoGUI();
        cabecalho.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        int row = tabelaCabecalhos.getSelectedRow();
        int numero = tabelaCabecalhos.getSelectedRowCount();
        if (numero == 1) {
            int id = Integer.parseInt(tabelaCabecalhos.getModel().getValueAt(row, 0).toString());
            
            EditarCabecalhoGUI editor = new EditarCabecalhoGUI(this, true, id);
            editor.setVisible(true);
            
            for (int i=(modelTabela.getRowCount()-1); i>=0; i--) {
                modelTabela.removeRow(i);
            }
            
            try {
                rs = cabecalho.pegarCabecalho();
                if (rs!=null) {
                    do {
                        modelTabela.addRow(new Object[]{
                            rs.getString("Cabecalho_ID"),
                            rs.getString("NomeInstituicao"),
                            rs.getString("Titulo"),
                            rs.getString("Serie"),
                            rs.getString("Valor"),
                        });
                    } while (rs.next());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }

            jbEditar.setEnabled(false);
            jbRemover.setEnabled(false);
            jmiEditar.setEnabled(false);
            jmiRemover.setEnabled(false);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverActionPerformed
        // TODO add your handling code here:
        int numero = tabelaCabecalhos.getSelectedRowCount();
        String mensagem;
        if (numero == 1) {
            mensagem = "Tem certeza que deseja excluir este cabeçalho?";
        } else {
            mensagem = "Tem certeza que deseja excluir "+numero+" cabeçalhos?";
        }

        int[] row = tabelaCabecalhos.getSelectedRows();
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), mensagem, "Excluir dados",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            for (int i=0; i<numero; i++) {
                try {
                    int id = Integer.parseInt(tabelaCabecalhos.getModel().getValueAt(row[i], 0).toString());
                    cabecalho.excluirCabecalhoPeloId(id);
                    if ((i==0)&&((i+1)==numero)) {
                        JOptionPane.showMessageDialog(this, "Cabeçalho excluido com sucesso!");
                    } else if ((i+1)==numero) {
                        JOptionPane.showMessageDialog(this, "Cabeçalhos excluidos com sucesso!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir.\nErro: "+ex);
                }
            }

            for (int i=(modelTabela.getRowCount()-1); i>=0; i--) {
                modelTabela.removeRow(i);
            }

            try {
                rs = cabecalho.pegarCabecalho();
                if (rs!=null) {
                    do {
                        modelTabela.addRow(new Object[]{
                            rs.getString("Cabecalho_ID"),
                            rs.getString("NomeInstituicao"),
                            rs.getString("Titulo"),
                            rs.getString("Serie"),
                            rs.getString("Valor"),
                        });
                    } while (rs.next());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }

            jbEditar.setEnabled(false);
            jbRemover.setEnabled(false);
            jmiEditar.setEnabled(false);
            jmiRemover.setEnabled(false);
        }
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        // TODO add your handling code here:
        String enunciado = jtPesquisar.getText();
        if (!enunciado.isEmpty()) {
            try {                
                for (int i=(modelTabela.getRowCount()-1); i>=0; i--) {
                    modelTabela.removeRow(i);
                }
                rs = cabecalho.pesquisarCabecalho(enunciado);                
                if (rs!=null) {
                    do {
                        modelTabela.addRow(new Object[]{
                            rs.getString("Cabecalho_ID"),
                            rs.getString("NomeInstituicao"),
                            rs.getString("Titulo"),
                            rs.getString("Serie"),
                            rs.getString("Valor"),
                        });
                    } while (rs.next());
                    tabelaCabecalhos.setModel(modelTabela);
                }                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não digitou nada no campo de pesquisa!");
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMenuActionPerformed
        // TODO add your handling code here:
        MenuGUI menu = new MenuGUI();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jbMenuActionPerformed

    private void tabelaCabecalhosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaCabecalhosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaCabecalhosFocusLost

    private void jmiNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovoActionPerformed
        // TODO add your handling code here:
        jbNovoActionPerformed(evt);
    }//GEN-LAST:event_jmiNovoActionPerformed

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
            java.util.logging.Logger.getLogger(CabecalhosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CabecalhosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CabecalhosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CabecalhosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CabecalhosGUI().setVisible(true);
            }
        });
    }

    public void verTodas() {
        if (jtPesquisar.getText().isEmpty()) {
            try {
                for (int i=(modelTabela.getRowCount()-1); i>=0; i--) {
                    modelTabela.removeRow(i);
                }
                rs = cabecalho.pegarCabecalho();
                if (rs!=null) {
                    do {
                        modelTabela.addRow(new Object[]{
                            rs.getString("Cabecalho_ID"),
                            rs.getString("NomeInstituicao"),
                            rs.getString("Titulo"),
                            rs.getString("Serie"),
                            rs.getString("Valor"),
                        });
                    } while (rs.next());
                    tabelaCabecalhos.setModel(modelTabela);
                }
                jtPesquisar.setText("");
                jtPesquisar.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro: "+e.getMessage());
            }
        } else {
            jbPesquisarActionPerformed(null);
        }
    }   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbMenu;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbRemover;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenuItem jmiEditar;
    private javax.swing.JMenuItem jmiMenu;
    private javax.swing.JMenuItem jmiNovo;
    private javax.swing.JMenuItem jmiRemover;
    private javax.swing.JTextField jtPesquisar;
    private javax.swing.JTable tabelaCabecalhos;
    // End of variables declaration//GEN-END:variables
}
