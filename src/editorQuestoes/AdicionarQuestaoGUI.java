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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import telas.MenuGUI;

/**
 *
 * @author Couth
 */
public class AdicionarQuestaoGUI extends javax.swing.JFrame {

    /**
     * Creates new form AdicionarQuestaoGUI
     */  
    
    JFrame frame = new JFrame();
    Questoes questoesBanco = new Questoes();
    ResultSet resultSet;
    DefaultComboBoxModel modelComboBox;
    int idConteudo, idQuestao, posicaoImagem, ind = 0;
    List<String> strList = new ArrayList<String>();  
    boolean img, t1, t2, t3, t4, t5, t6, t7;
    FileInputStream input;
    String fileName, findString;    
    StringBuffer sbufer;
    
    UndoManager undo = new UndoManager();
    UndoAction undoAction = new UndoAction();
    RedoAction redoAction = new RedoAction();
    public AdicionarQuestaoGUI() {
        initComponents();
        tabbedPane.setTitleAt(0, "Questão ABERTA");
        tabbedPane.setTitleAt(1, "Questão FECHADA");
        
        frame = this;
        
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    frame.setSize(new Dimension(frame.getSize().width, 420));
                    frame.setLocationRelativeTo(null);
                }
                if (tabbedPane.getSelectedIndex() == 1) {
                    frame.setSize(new Dimension(frame.getSize().width, 740));
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    Dimension tamanhoTela = kit.getScreenSize();
                    int width = tamanhoTela.width;
                    int height = tamanhoTela.height;
                    frame.setLocation(width / 4, 20);
                }
                CarregarComboBox();
            }
        });
        
        this.setResizable(false);
        this.setSize(new Dimension(this.getSize().width, 420)); 
        this.setLocationRelativeTo(null);
        this.setTitle("Editor de Questões");
        
        img = false;
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = false;
        
        jlImagem1.setMaximumSize(new Dimension(14, 225));
        //jlImagem2.setMaximumSize(new Dimension(14, 225));
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem e Botão Negrito
        Action boldAction = new AdicionarQuestaoGUI.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        //jmTexto.add(boldAction);  
        jbNegrito1.addActionListener(boldAction);        
        //jbNegrito2.addActionListener(boldAction); 
        
        //MenuItem e Botão Itálico
        Action italicAction = new AdicionarQuestaoGUI.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        //jmTexto.add(italicAction);
        jbItalico1.addActionListener(italicAction);   
        //jbItalico2.addActionListener(italicAction); 

        //MenuItem e Botão Sublinhado
        Action underlineAction = new AdicionarQuestaoGUI.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        //jmTexto.add(underlineAction);
        jbSublinhado1.addActionListener(underlineAction);   
        //jbSublinhado2.addActionListener(underlineAction); 

        //MenuItem e Botão Cor
        Action foregroundAction = new AdicionarQuestaoGUI.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        //jmTexto.add(foregroundAction);
        jbCor1.addActionListener(foregroundAction);   
        //jbCor2.addActionListener(foregroundAction);  

        //MenuItem e Botão Fonte
        Action formatTextAction = new AdicionarQuestaoGUI.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        //jmTexto.add(formatTextAction);
        jbFonte1.addActionListener(formatTextAction);   
        //jbFonte2.addActionListener(formatTextAction); 
        
        jtpEnunciado.requestFocus();
        
        jtpEnunciado.getDocument().addUndoableEditListener(new MyUndoableEditListener());
        
        jbDesfazer1.addActionListener(undoAction);
        jbRefazer1.addActionListener(redoAction);
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
        jbSalvar1 = new javax.swing.JButton();
        jbEditar1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jbCarregar1 = new javax.swing.JButton();
        jbRemover1 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jbMenu1 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jbNegrito1 = new javax.swing.JButton();
        jbItalico1 = new javax.swing.JButton();
        jbSublinhado1 = new javax.swing.JButton();
        jbFonte1 = new javax.swing.JButton();
        jbCor1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbDesfazer1 = new javax.swing.JButton();
        jbRefazer1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbRecortar1 = new javax.swing.JButton();
        jbCopiar1 = new javax.swing.JButton();
        jbColar1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jbLocalizar1 = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jcbDisciplina = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbConteudo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbDificuldade = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpEnunciado = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jlImagem1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(178, 203, 243));

        jToolBar1.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar1.setRollover(true);

        jbSalvar1.setBackground(new java.awt.Color(178, 203, 243));
        jbSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/saveFile.png"))); // NOI18N
        jbSalvar1.setToolTipText("Salvar Questão");
        jbSalvar1.setFocusable(false);
        jbSalvar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSalvar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvar1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbSalvar1);

        jbEditar1.setBackground(new java.awt.Color(178, 203, 243));
        jbEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editFile.png"))); // NOI18N
        jbEditar1.setToolTipText("Editar Questões");
        jbEditar1.setFocusable(false);
        jbEditar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEditar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditar1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbEditar1);
        jToolBar1.add(jSeparator4);

        jbCarregar1.setBackground(new java.awt.Color(178, 203, 243));
        jbCarregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/addImage.png"))); // NOI18N
        jbCarregar1.setToolTipText("Adicionar Imagem");
        jbCarregar1.setFocusable(false);
        jbCarregar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCarregar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCarregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCarregar1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbCarregar1);

        jbRemover1.setBackground(new java.awt.Color(178, 203, 243));
        jbRemover1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeImage.png"))); // NOI18N
        jbRemover1.setToolTipText("Remover Imagem");
        jbRemover1.setFocusable(false);
        jbRemover1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRemover1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRemover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemover1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbRemover1);
        jToolBar1.add(jSeparator5);

        jbMenu1.setBackground(new java.awt.Color(178, 203, 243));
        jbMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/exit.png"))); // NOI18N
        jbMenu1.setToolTipText("Menu Principal");
        jbMenu1.setFocusable(false);
        jbMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMenu1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jbMenu1);

        jToolBar2.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar2.setRollover(true);

        jbNegrito1.setBackground(new java.awt.Color(178, 203, 243));
        jbNegrito1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/bold.png"))); // NOI18N
        jbNegrito1.setToolTipText("Negrito");
        jbNegrito1.setFocusable(false);
        jbNegrito1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbNegrito1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbNegrito1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNegrito1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbNegrito1);

        jbItalico1.setBackground(new java.awt.Color(178, 203, 243));
        jbItalico1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/italic.png"))); // NOI18N
        jbItalico1.setToolTipText("Itálico");
        jbItalico1.setFocusable(false);
        jbItalico1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbItalico1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbItalico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbItalico1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbItalico1);

        jbSublinhado1.setBackground(new java.awt.Color(178, 203, 243));
        jbSublinhado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/underline.png"))); // NOI18N
        jbSublinhado1.setToolTipText("Sublinhado");
        jbSublinhado1.setFocusable(false);
        jbSublinhado1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSublinhado1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbSublinhado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSublinhado1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbSublinhado1);

        jbFonte1.setBackground(new java.awt.Color(178, 203, 243));
        jbFonte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/font.png"))); // NOI18N
        jbFonte1.setToolTipText("Fonte");
        jbFonte1.setFocusable(false);
        jbFonte1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbFonte1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbFonte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFonte1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbFonte1);

        jbCor1.setBackground(new java.awt.Color(178, 203, 243));
        jbCor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/color.png"))); // NOI18N
        jbCor1.setToolTipText("Cor do Texto");
        jbCor1.setFocusable(false);
        jbCor1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCor1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCor1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbCor1);
        jToolBar2.add(jSeparator1);

        jbDesfazer1.setBackground(new java.awt.Color(178, 203, 243));
        jbDesfazer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/undo.png"))); // NOI18N
        jbDesfazer1.setToolTipText("Desfazer");
        jbDesfazer1.setEnabled(false);
        jbDesfazer1.setFocusable(false);
        jbDesfazer1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbDesfazer1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbDesfazer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDesfazer1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbDesfazer1);

        jbRefazer1.setBackground(new java.awt.Color(178, 203, 243));
        jbRefazer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/redo.png"))); // NOI18N
        jbRefazer1.setToolTipText("Refazer");
        jbRefazer1.setEnabled(false);
        jbRefazer1.setFocusable(false);
        jbRefazer1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRefazer1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRefazer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRefazer1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbRefazer1);
        jToolBar2.add(jSeparator2);

        jbRecortar1.setBackground(new java.awt.Color(178, 203, 243));
        jbRecortar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cut.png"))); // NOI18N
        jbRecortar1.setToolTipText("Recortar");
        jbRecortar1.setFocusable(false);
        jbRecortar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRecortar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRecortar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRecortar1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbRecortar1);

        jbCopiar1.setBackground(new java.awt.Color(178, 203, 243));
        jbCopiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/copy.png"))); // NOI18N
        jbCopiar1.setToolTipText("Copiar");
        jbCopiar1.setFocusable(false);
        jbCopiar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCopiar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCopiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCopiar1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbCopiar1);

        jbColar1.setBackground(new java.awt.Color(178, 203, 243));
        jbColar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/paste.png"))); // NOI18N
        jbColar1.setToolTipText("Colar");
        jbColar1.setFocusable(false);
        jbColar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbColar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbColar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbColar1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbColar1);
        jToolBar2.add(jSeparator3);

        jbLocalizar1.setBackground(new java.awt.Color(178, 203, 243));
        jbLocalizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/binoculars.png"))); // NOI18N
        jbLocalizar1.setToolTipText("Localizar");
        jbLocalizar1.setFocusable(false);
        jbLocalizar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbLocalizar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbLocalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocalizar1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jbLocalizar1);

        tabbedPane.setBackground(new java.awt.Color(209, 224, 248));

        jPanel2.setBackground(new java.awt.Color(209, 224, 248));

        jLabel1.setText("Disciplina:");

        jLabel2.setText("Conteúdo:");

        jLabel3.setText("Dificuldade:");

        jLabel4.setText("Enunciado:");

        jScrollPane1.setViewportView(jtpEnunciado);

        jLabel5.setText("Imagem:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlImagem1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jlImagem1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(209, 224, 248));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        tabbedPane.addTab("tab2", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
        );

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ajuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void jbSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvar1ActionPerformed
        // TODO add your handling code here:
        try {
            if (!jtpEnunciado.getText().isEmpty()) {
                if (jcbConteudo.getItemCount() != 0) {
                    int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar esta questão?", "Salvar questão",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                    if (x==0) {
                        String enunciado = jtpEnunciado.getText();                        
                        int dificuldade = Integer.parseInt(jcbDificuldade.getSelectedItem().toString());
                        String multipla = "N";
                        resultSet = questoesBanco.pegarConteudosID(jcbConteudo.getSelectedItem().toString());
                        if (resultSet!=null) {
                            do {
                                //idConteudo = Integer.parseInt(resultSet.getString("Conteudos_ID"));
                                idConteudo = resultSet.getInt("Conteudos_ID");                        
                            } while (resultSet.next());
                            SalvarQuestaoAberta(enunciado, dificuldade, multipla, idConteudo); 
                        }

                        resultSet = questoesBanco.pegarUltimaQuestao();
                        if (resultSet!=null) {
                            do {
                                idQuestao = resultSet.getInt("ult");                      
                            } while (resultSet.next());
                            AddImagem(idQuestao);
                        }

                        jtpEnunciado.setText("");
                        JOptionPane.showMessageDialog(this, "Questão adicionada com sucesso!");
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
    }//GEN-LAST:event_jbSalvar1ActionPerformed

    private void jbNegrito1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNegrito1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbNegrito1ActionPerformed

    private void jbItalico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbItalico1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbItalico1ActionPerformed

    private void jbSublinhado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSublinhado1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbSublinhado1ActionPerformed

    private void jbFonte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFonte1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbFonte1ActionPerformed

    private void jbCor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCor1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.requestFocus();
    }//GEN-LAST:event_jbCor1ActionPerformed

    private void jbCarregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCarregar1ActionPerformed
        // TODO add your handling code here:
        TesteImagem a = new TesteImagem(this, true);
        a.setVisible(true); 
        img = a.certo;
        posicaoImagem = a.posicao;
        input = a.input;
        fileName = a.fileName;
        if (tabbedPane.getSelectedIndex()==0) {
            jlImagem1.setText(fileName);
            jbRemover1.setEnabled(true);
        }
        if (tabbedPane.getSelectedIndex()==1) {
            //jlImagem2.setText(fileName);
            //jbRemoverImagem2.setEnabled(true);
        }   
    }//GEN-LAST:event_jbCarregar1ActionPerformed

    private void jbRemover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemover1ActionPerformed
        // TODO add your handling code here:
        img = false;
        fileName = "Nenhuma imagem selecionada";
        if (tabbedPane.getSelectedIndex()==0) {
            jlImagem1.setText(fileName);
            jbRemover1.setEnabled(false);
        }
        if (tabbedPane.getSelectedIndex()==1) {
            //jlImagem2.setText(fileName);
            //jbRemoverImagem2.setEnabled(false);
        } 
    }//GEN-LAST:event_jbRemover1ActionPerformed

    private void jbMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMenu1ActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            if (tabbedPane.getSelectedIndex() == 0) {
                jbSalvar1ActionPerformed(evt);
                if (!jtpEnunciado.getText().isEmpty()) {
                    if (jcbConteudo.getItemCount() != 0) {
                        MenuGUI menu = new MenuGUI();
                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        dispose();
                    }
                }
            }
            if (tabbedPane.getSelectedIndex() == 1) {
                //jbSalvar2ActionPerformed(evt);
                //if ((!jtpEnunciado2.getText().isEmpty())&&(!jtpLetraA1.getText().isEmpty())&&(!jtpLetraB1.getText().isEmpty())
                //    &&(!jtpLetraC1.getText().isEmpty())&&(!jtpLetraD1.getText().isEmpty())) {
                //    if (((!jtpLetraE1.getText().isEmpty())&&(jrbSimE1.isSelected()))||(jrbNãoE1.isSelected())) {
                //        if (((!jtpLetraF1.getText().isEmpty())&&(jrbSimF1.isSelected()))||(jrbNãoF1.isSelected())) {
                //            if (jcbConteudo.getItemCount() != 0) {
                //                MenuGUI menu = new MenuGUI();
                //                menu.setVisible(true);
                //                menu.setLocationRelativeTo(null);
                //                dispose();
                //            }
                //        }
                //    }
                //}
            }            
        }
        if (x==1) {
            MenuGUI menu = new MenuGUI();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            dispose();
        }
    }//GEN-LAST:event_jbMenu1ActionPerformed

    private void jbEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditar1ActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            if (tabbedPane.getSelectedIndex() == 0) {
                jbSalvar1ActionPerformed(evt);
                if (!jtpEnunciado.getText().isEmpty()) {
                    if (jcbConteudo.getItemCount() != 0) {
                        TelaDeQuestoes editor = new TelaDeQuestoes();
                        editor.setVisible(true);
                        editor.setLocationRelativeTo(null);
                        dispose();
                    }
                }
            }
            if (tabbedPane.getSelectedIndex() == 1) {
                //jbSalvar2ActionPerformed(evt);
                //if ((!jtpEnunciado2.getText().isEmpty())&&(!jtpLetraA1.getText().isEmpty())&&(!jtpLetraB1.getText().isEmpty())
                //    &&(!jtpLetraC1.getText().isEmpty())&&(!jtpLetraD1.getText().isEmpty())) {
                //    if (((!jtpLetraE1.getText().isEmpty())&&(jrbSimE1.isSelected()))||(jrbNãoE1.isSelected())) {
                //        if (((!jtpLetraF1.getText().isEmpty())&&(jrbSimF1.isSelected()))||(jrbNãoF1.isSelected())) {
                //            if (jcbConteudo.getItemCount() != 0) {
                //                TelaDeQuestoes editor = new TelaDeQuestoes();
                //                editor.setVisible(true);
                //                editor.setLocationRelativeTo(null);
                //                dispose();
                //            }
                //        }
                //    }
                //}
            }            
        }
        if (x==1) {
            TelaDeQuestoes editor = new TelaDeQuestoes();
            editor.setVisible(true);
            editor.setLocationRelativeTo(null);
            dispose();
        }  
    }//GEN-LAST:event_jbEditar1ActionPerformed

    private void jbRecortar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRecortar1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.cut();
    }//GEN-LAST:event_jbRecortar1ActionPerformed

    private void jbCopiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCopiar1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.copy();
    }//GEN-LAST:event_jbCopiar1ActionPerformed

    private void jbColar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbColar1ActionPerformed
        // TODO add your handling code here:
        jtpEnunciado.paste();
    }//GEN-LAST:event_jbColar1ActionPerformed

    private void jbLocalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizar1ActionPerformed
        // TODO add your handling code here:
        try {
            sbufer = new StringBuffer(jtpEnunciado.getText());
            findString = JOptionPane.showInputDialog(null, "Localizar");
            ind = sbufer.indexOf(findString);
            jtpEnunciado.setCaretPosition(ind);
            jtpEnunciado.setSelectionStart(ind);
            jtpEnunciado.setSelectionEnd(ind+findString.length());
        } catch (IllegalArgumentException npe) {
            JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
        } catch (NullPointerException nfe) {
        
        }
    }//GEN-LAST:event_jbLocalizar1ActionPerformed

    private void jbRefazer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRefazer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRefazer1ActionPerformed

    private void jbDesfazer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDesfazer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbDesfazer1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdicionarQuestaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdicionarQuestaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdicionarQuestaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarQuestaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdicionarQuestaoGUI().setVisible(true);
            }
        });
    }

    //SALVAR IMAGEM
    public void AddImagem(int idDestaQuestao) {        
        if (img) {
            try {
                //PASSAR FILENAME
                questoesBanco.inserirImagem(idDestaQuestao, input, posicaoImagem, fileName);              
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro, tente novamente!");
            }
        }
    }
    
    //SALVAR QUESTÃO ABERTA
    public void SalvarQuestaoAberta(String e, int d, String m, int idConteudo) {        
        try {
            questoesBanco.inserirQuestaoAberta(e, d, m, idConteudo);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex);
        }
    }
    
    //SALVAR QUESTÃO FECHADA
    public void SalvarQuestaoFechada(String e, int d, String m, String letraA, String letraB, String letraC,
                              String letraD, String letraE, String letraF, int idConteudo) {        
        try {
            questoesBanco.inserirQuestaoFechada(e, d, m, letraA, letraB, letraC, letraD, letraE, letraF, idConteudo);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex);
        }
    }    
    
    //CARREGAR COMBOBOXES
    public void CarregarComboBox() {     
        try {
            //Disciplinas   
            strList.removeAll(strList);
            resultSet = questoesBanco.pegarDisciplinas();
            if (resultSet!=null) {
                do {
                    strList.add(
                            resultSet.getString("NomeDisciplinas"));
                } while (resultSet.next());
            }
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbDisciplina.setModel(modelComboBox);
            //jcbDisciplina3.setModel(modelComboBox);
            
            //Conteúdos
            strList.removeAll(strList);
            resultSet = questoesBanco.pegarConteudos(jcbDisciplina.getSelectedItem().toString());
            if (resultSet!=null) {
                do {
                    strList.add(
                            resultSet.getString("NomeConteudos"));
                } while (resultSet.next());                   
            }             
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbConteudo.setModel(modelComboBox);            
            //jcbConteudo3.setModel(modelComboBox);    
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
    
    //UNDO AND REDOACTION CLASSES
    //THIS PASRT OF CODE WAS TAKEN FROM THE NOTEPAD DEMO FOUND IN THE JDK1.4.1 DEMO DIRECTORY
    class UndoAction extends AbstractAction {
	public UndoAction() {
	    super("Undo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.undo();
	    } catch (CannotUndoException ex) {
		System.out.println("Unable to undo: " + ex);
	    }
	    update();
	    redoAction.update();
	}

	protected void update() {
	    if (undo.canUndo()) {
		jbDesfazer1.setEnabled(true);
		putValue("Undo", undo.getUndoPresentationName());
	    }
	    else {
		jbDesfazer1.setEnabled(false);
		putValue(Action.NAME, "Undo");
	    }
	}
    }

    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
            }
            update();
            undoAction.update();
        }

        protected void update() {
            if (undo.canRedo()) {
                jbRefazer1.setEnabled(true);
                putValue("Redo", undo.getRedoPresentationName());
            } else {
                jbRefazer1.setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
    
    //CLASS FOR UNDOLISTENER
    public class MyUndoableEditListener implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            //Remember the edit and update the menus
            undo.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbCarregar1;
    private javax.swing.JButton jbColar1;
    private javax.swing.JButton jbCopiar1;
    private javax.swing.JButton jbCor1;
    private javax.swing.JButton jbDesfazer1;
    private javax.swing.JButton jbEditar1;
    private javax.swing.JButton jbFonte1;
    private javax.swing.JButton jbItalico1;
    private javax.swing.JButton jbLocalizar1;
    private javax.swing.JButton jbMenu1;
    private javax.swing.JButton jbNegrito1;
    private javax.swing.JButton jbRecortar1;
    private javax.swing.JButton jbRefazer1;
    private javax.swing.JButton jbRemover1;
    private javax.swing.JButton jbSalvar1;
    private javax.swing.JButton jbSublinhado1;
    private javax.swing.JComboBox jcbConteudo;
    private javax.swing.JComboBox jcbDificuldade;
    private javax.swing.JComboBox jcbDisciplina;
    private javax.swing.JLabel jlImagem1;
    private javax.swing.JTextPane jtpEnunciado;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
