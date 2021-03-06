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
/**
 *
 * @author Couth
 */
public class EditarQuestaoFechadaGUI extends javax.swing.JDialog {

    /**
     * Creates new form EditarQuestaoFechadaGUI
     */
    Questoes questoesBanco = new Questoes();
    JDialog dialog = new JDialog();
    List<String> strList = new ArrayList<String>();
    
    ResultSet rs;
    DefaultComboBoxModel modelComboBox;  
    FileInputStream input;
    String fileName, fileNameAntigo, enunciado, disciplina, conteudo, alternativaA, alternativaB, alternativaC, alternativaD, alternativaE, alternativaF, findString;
    int idQuestao, dificuldade, idConteudo, posicaoImagem, ind = 0;      
    boolean img, img2, remover, t1, t2, t3, t4, t5, t6, t7;        
    StringBuffer sbufer;
    
    UndoManager undo = new UndoManager();
    UndoAction undoAction = new UndoAction();
    RedoAction redoAction = new RedoAction();
    public EditarQuestaoFechadaGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public EditarQuestaoFechadaGUI(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Editor de Questão FECHADA");
        jlImagem.setMaximumSize(new Dimension(14, 225));
        jbRemover.setEnabled(false);
        jmiRemover.setEnabled(false);
        
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = false;
        remover = false;
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem Negrito
        Action boldAction = new EditarQuestaoFechadaGUI.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jbNegrito.addActionListener(boldAction);
        
        //MenuItem Itálico
        Action italicAction = new EditarQuestaoFechadaGUI.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jbItalico.addActionListener(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new EditarQuestaoFechadaGUI.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jbSublinhado.addActionListener(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new EditarQuestaoFechadaGUI.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jbCor.addActionListener(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new EditarQuestaoFechadaGUI.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jbFonte.addActionListener(formatTextAction);
        
        jtpEnunciado.requestFocus();
        
        try {            
            rs = questoesBanco.pegarQuestaoPeloId(id);
            if (rs!=null) {                                
                idQuestao = rs.getInt("Questoes_ID");
                enunciado = rs.getString("Enunciado");
                dificuldade = rs.getInt("Dificuldade");
                idConteudo = rs.getInt("ID_Conteudos");

                alternativaA = rs.getString("AlternativaA");
                alternativaB = rs.getString("AlternativaB");
                alternativaC = rs.getString("AlternativaC");
                alternativaD = rs.getString("AlternativaD");
                alternativaE = rs.getString("AlternativaE");
                alternativaF = rs.getString("AlternativaF");
                
                jtpEnunciado.setText(enunciado);
                jtpA.setText(alternativaA);
                jtpB.setText(alternativaB);
                jtpC.setText(alternativaC);
                jtpD.setText(alternativaD);
                jtpE.setText(alternativaE);
                jtpF.setText(alternativaF);
                jcbDificuldade.setSelectedIndex(dificuldade);
                
                if (!alternativaE.isEmpty()) {
                    jtpE.setEnabled(true);
                    jlE.setEnabled(true);
                    jrbSimE.setSelected(true);
                }
                if (!alternativaF.isEmpty()) {
                    jtpF.setEnabled(true);
                    jlF.setEnabled(true);
                    jrbSimF.setSelected(true);
                }
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
                jmiRemover.setEnabled(true);
                img2 = true;
            } else {
                img2 = false;
            }
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        }
        
        jtpEnunciado.getDocument().addUndoableEditListener(new EditarQuestaoFechadaGUI.MyUndoableEditListener());
        
        jbDesfazer.addActionListener(undoAction);
        jbRefazer.addActionListener(redoAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        letraE = new javax.swing.ButtonGroup();
        letraF = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jbSalvar = new javax.swing.JButton();
        jbAbrir = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jbCarregar = new javax.swing.JButton();
        jbRemover = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jbMenu = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jbNegrito = new javax.swing.JButton();
        jbItalico = new javax.swing.JButton();
        jbSublinhado = new javax.swing.JButton();
        jbFonte = new javax.swing.JButton();
        jbCor = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbDesfazer = new javax.swing.JButton();
        jbRefazer = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbRecortar = new javax.swing.JButton();
        jbCopiar = new javax.swing.JButton();
        jbColar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jbLocalizar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jcbDisciplina = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jcbConteudo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jcbDificuldade = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpEnunciado = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jlImagem = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtpA = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtpB = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtpC = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtpD = new javax.swing.JTextPane();
        jlE = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtpE = new javax.swing.JTextPane();
        jlF = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtpF = new javax.swing.JTextPane();
        jrbSimE = new javax.swing.JRadioButton();
        jrbNaoE = new javax.swing.JRadioButton();
        jrbSimF = new javax.swing.JRadioButton();
        jrbNaoF = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmiAbrir = new javax.swing.JMenuItem();
        jmiSalvar = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmiCarregar = new javax.swing.JMenuItem();
        jmiRemover = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiMenu = new javax.swing.JMenuItem();

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

        jbAbrir.setBackground(new java.awt.Color(178, 203, 243));
        jbAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/openFile.png"))); // NOI18N
        jbAbrir.setToolTipText("Abrir Arquivo .txt");
        jbAbrir.setEnabled(false);
        jbAbrir.setFocusable(false);
        jbAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(jbAbrir);
        jToolBar1.add(jSeparator4);

        jbCarregar.setBackground(new java.awt.Color(178, 203, 243));
        jbCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/addImage.png"))); // NOI18N
        jbCarregar.setToolTipText("Adicionar Imagem");
        jbCarregar.setFocusable(false);
        jbCarregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCarregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCarregarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbCarregar);

        jbRemover.setBackground(new java.awt.Color(178, 203, 243));
        jbRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeImage.png"))); // NOI18N
        jbRemover.setToolTipText("Remover Imagem");
        jbRemover.setFocusable(false);
        jbRemover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRemover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverActionPerformed(evt);
            }
        });
        jToolBar1.add(jbRemover);
        jToolBar1.add(jSeparator5);

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

        jToolBar2.setBackground(new java.awt.Color(178, 203, 243));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setBorderPainted(false);

        jbNegrito.setBackground(new java.awt.Color(178, 203, 243));
        jbNegrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/bold.png"))); // NOI18N
        jbNegrito.setToolTipText("Negrito");
        jbNegrito.setFocusable(false);
        jbNegrito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbNegrito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbNegrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNegritoActionPerformed(evt);
            }
        });
        jToolBar2.add(jbNegrito);

        jbItalico.setBackground(new java.awt.Color(178, 203, 243));
        jbItalico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/italic.png"))); // NOI18N
        jbItalico.setToolTipText("Itálico");
        jbItalico.setFocusable(false);
        jbItalico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbItalico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbItalico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbItalicoActionPerformed(evt);
            }
        });
        jToolBar2.add(jbItalico);

        jbSublinhado.setBackground(new java.awt.Color(178, 203, 243));
        jbSublinhado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/underline.png"))); // NOI18N
        jbSublinhado.setToolTipText("Sublinhado");
        jbSublinhado.setFocusable(false);
        jbSublinhado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSublinhado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbSublinhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSublinhadoActionPerformed(evt);
            }
        });
        jToolBar2.add(jbSublinhado);

        jbFonte.setBackground(new java.awt.Color(178, 203, 243));
        jbFonte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/font.png"))); // NOI18N
        jbFonte.setToolTipText("Fonte");
        jbFonte.setFocusable(false);
        jbFonte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbFonte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFonteActionPerformed(evt);
            }
        });
        jToolBar2.add(jbFonte);

        jbCor.setBackground(new java.awt.Color(178, 203, 243));
        jbCor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/color.png"))); // NOI18N
        jbCor.setToolTipText("Cor do Texto");
        jbCor.setFocusable(false);
        jbCor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCorActionPerformed(evt);
            }
        });
        jToolBar2.add(jbCor);
        jToolBar2.add(jSeparator1);

        jbDesfazer.setBackground(new java.awt.Color(178, 203, 243));
        jbDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/undo.png"))); // NOI18N
        jbDesfazer.setToolTipText("Desfazer");
        jbDesfazer.setEnabled(false);
        jbDesfazer.setFocusable(false);
        jbDesfazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbDesfazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbDesfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDesfazerActionPerformed(evt);
            }
        });
        jToolBar2.add(jbDesfazer);

        jbRefazer.setBackground(new java.awt.Color(178, 203, 243));
        jbRefazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/redo.png"))); // NOI18N
        jbRefazer.setToolTipText("Refazer");
        jbRefazer.setEnabled(false);
        jbRefazer.setFocusable(false);
        jbRefazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRefazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRefazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRefazerActionPerformed(evt);
            }
        });
        jToolBar2.add(jbRefazer);
        jToolBar2.add(jSeparator2);

        jbRecortar.setBackground(new java.awt.Color(178, 203, 243));
        jbRecortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cut.png"))); // NOI18N
        jbRecortar.setToolTipText("Recortar");
        jbRecortar.setFocusable(false);
        jbRecortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbRecortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbRecortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRecortarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbRecortar);

        jbCopiar.setBackground(new java.awt.Color(178, 203, 243));
        jbCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/copy.png"))); // NOI18N
        jbCopiar.setToolTipText("Copiar");
        jbCopiar.setFocusable(false);
        jbCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCopiarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbCopiar);

        jbColar.setBackground(new java.awt.Color(178, 203, 243));
        jbColar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/paste.png"))); // NOI18N
        jbColar.setToolTipText("Colar");
        jbColar.setFocusable(false);
        jbColar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbColar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbColar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbColarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbColar);
        jToolBar2.add(jSeparator3);

        jbLocalizar.setBackground(new java.awt.Color(178, 203, 243));
        jbLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/binoculars.png"))); // NOI18N
        jbLocalizar.setToolTipText("Localizar");
        jbLocalizar.setFocusable(false);
        jbLocalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbLocalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocalizarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbLocalizar);

        jPanel3.setBackground(new java.awt.Color(209, 224, 248));

        jcbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisciplinaActionPerformed(evt);
            }
        });

        jLabel6.setText("Disciplina:");

        jLabel7.setText("Conteúdo:");

        jLabel8.setText("Dificuldade:");

        jcbDificuldade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel9.setText("Enunciado:");

        jtpEnunciado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpEnunciadoFocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(jtpEnunciado);

        jLabel10.setText("Imagem:");

        jLabel11.setText("Alternativa A:");

        jLabel12.setText("Alternativa B:");

        jtpA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpAFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(jtpA);

        jtpB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpBFocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(jtpB);

        jLabel13.setText("Alternativa C:");

        jtpC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpCFocusGained(evt);
            }
        });
        jScrollPane5.setViewportView(jtpC);

        jLabel14.setText("Alternativa D:");

        jtpD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpDFocusGained(evt);
            }
        });
        jScrollPane6.setViewportView(jtpD);

        jlE.setText("Alternativa E:");
        jlE.setEnabled(false);

        jtpE.setEnabled(false);
        jtpE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpEFocusGained(evt);
            }
        });
        jScrollPane7.setViewportView(jtpE);

        jlF.setText("Alternativa F:");
        jlF.setEnabled(false);

        jtpF.setEnabled(false);
        jtpF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtpFFocusGained(evt);
            }
        });
        jScrollPane8.setViewportView(jtpF);

        jrbSimE.setBackground(new java.awt.Color(209, 224, 248));
        letraE.add(jrbSimE);
        jrbSimE.setText("Sim");
        jrbSimE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSimEActionPerformed(evt);
            }
        });

        jrbNaoE.setBackground(new java.awt.Color(209, 224, 248));
        letraE.add(jrbNaoE);
        jrbNaoE.setSelected(true);
        jrbNaoE.setText("Não");
        jrbNaoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNaoEActionPerformed(evt);
            }
        });

        jrbSimF.setBackground(new java.awt.Color(209, 224, 248));
        letraF.add(jrbSimF);
        jrbSimF.setText("Sim");
        jrbSimF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSimFActionPerformed(evt);
            }
        });

        jrbNaoF.setBackground(new java.awt.Color(209, 224, 248));
        letraF.add(jrbNaoF);
        jrbNaoF.setSelected(true);
        jrbNaoF.setText("Não");
        jrbNaoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNaoFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlImagem)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(246, 246, 246))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jlE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbSimE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbNaoE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbSimF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbNaoF))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel14))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addComponent(jScrollPane6)
                        .addComponent(jScrollPane8))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jcbConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jcbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jlImagem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlE)
                        .addComponent(jrbSimE)
                        .addComponent(jrbNaoE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlF)
                        .addComponent(jrbSimF)
                        .addComponent(jrbNaoF)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jmArquivo.setText("Arquivo");

        jmiAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jmiAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/openFile.png"))); // NOI18N
        jmiAbrir.setText("Abrir Arquivo");
        jmiAbrir.setEnabled(false);
        jmArquivo.add(jmiAbrir);

        jmiSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/saveFile.png"))); // NOI18N
        jmiSalvar.setText("Salvar Questão");
        jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalvarActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiSalvar);
        jmArquivo.add(jSeparator6);

        jmiCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/addImage.png"))); // NOI18N
        jmiCarregar.setText("Carregar Imagem");
        jmiCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCarregarActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiCarregar);

        jmiRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/removeImage.png"))); // NOI18N
        jmiRemover.setText("Remover Imagem");
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

        jMenuBar1.add(jmArquivo);

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

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here:
        jbSalvarActionPerformed(evt);
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jmiCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCarregarActionPerformed
        // TODO add your handling code here:
        jbCarregarActionPerformed(evt);
    }//GEN-LAST:event_jmiCarregarActionPerformed

    private void jmiRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoverActionPerformed
        // TODO add your handling code here:
        jbRemoverActionPerformed(evt);
    }//GEN-LAST:event_jmiRemoverActionPerformed

    private void jmiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMenuActionPerformed
        // TODO add your handling code here:
        jbMenuActionPerformed(evt);
    }//GEN-LAST:event_jmiMenuActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        // TODO add your handling code here:
        try {
            if ((!jtpEnunciado.getText().isEmpty())&&(!jtpA.getText().isEmpty())&&(!jtpB.getText().isEmpty())
                    &&(!jtpC.getText().isEmpty())&&(!jtpD.getText().isEmpty())) {
                if (((!jtpE.getText().isEmpty())&&(jrbSimE.isSelected()))||(jrbNaoE.isSelected())) {
                    if (((!jtpF.getText().isEmpty())&&(jrbSimF.isSelected()))||(jrbNaoF.isSelected())) {
                        if (jcbConteudo.getItemCount() != 0) {
                            int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar esta questão?", "Salvar questão",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                            if (x==0) {
                                enunciado = jtpEnunciado.getText();
                                dificuldade = Integer.parseInt(jcbDificuldade.getSelectedItem().toString());
                                alternativaA = jtpA.getText();
                                alternativaB = jtpB.getText();
                                alternativaC = jtpC.getText();
                                alternativaD = jtpD.getText();
                                alternativaE = "";
                                alternativaF = "";
                                if (jrbSimE.isSelected()) {
                                    alternativaE = jtpE.getText();
                                }
                                if (jrbSimF.isSelected()) {
                                    alternativaF = jtpF.getText();
                                }

                                rs = questoesBanco.pegarConteudosID(jcbConteudo.getSelectedItem().toString());
                                if (rs!=null) {
                                    do {
                                        idConteudo = rs.getInt("Conteudos_ID");    
                                    } while (rs.next());
                                    SalvarQuestao(enunciado, dificuldade, alternativaA, alternativaB, alternativaC, 
                                            alternativaD, alternativaE, alternativaF, idConteudo, idQuestao);
                                    AddImagem(idQuestao);
                                    RemoverImagem(idQuestao);
                                }                            

                                jtpEnunciado.setText("");
                                jtpA.setText("");
                                jtpB.setText("");
                                jtpC.setText("");
                                jtpD.setText("");
                                jtpE.setText("");
                                jtpF.setText("");

                                jlE.setEnabled(false);
                                jlF.setEnabled(false);
                                jtpE.setEnabled(false);
                                jtpF.setEnabled(false);

                                jrbNaoE.setSelected(true);
                                jrbNaoF.setSelected(true); 
                                
                                JOptionPane.showMessageDialog(this, "Questão atualizada com sucesso!");
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Não há conteúdo cadastrado nesta disciplina!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "A letra F está vazia!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A letra E está vazia!");
                }
            } else {
                if (jtpEnunciado.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O enunciado da questão não pode ser vazio!");
                } else {
                    JOptionPane.showMessageDialog(this, "Uma ou mais opções estão vazias!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro. Tente novamente");
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAbrirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAbrirActionPerformed

    private void jbCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCarregarActionPerformed
        // TODO add your handling code here:
        AdicionarImagemGUI a = new AdicionarImagemGUI(null, true);
        a.setVisible(true); 
        img = a.certo;
        posicaoImagem = a.posicao;
        input = a.input;
        fileName = a.fileName;
        jlImagem.setText(fileName);
        jbRemover.setEnabled(true);
        jmiRemover.setEnabled(true);
    }//GEN-LAST:event_jbCarregarActionPerformed

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
                jmiRemover.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jbRemoverActionPerformed

    private void jbMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMenuActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            jbSalvarActionPerformed(evt);
            if ((!jtpEnunciado.getText().isEmpty())&&(!jtpA.getText().isEmpty())&&(!jtpB.getText().isEmpty())
                &&(!jtpC.getText().isEmpty())&&(!jtpD.getText().isEmpty())) {
                if (((!jtpE.getText().isEmpty())&&(jrbSimE.isSelected()))||(jrbNaoE.isSelected())) {
                    if (((!jtpF.getText().isEmpty())&&(jrbSimF.isSelected()))||(jrbNaoF.isSelected())) {
                        if (jcbConteudo.getItemCount() != 0) {
                            dispose();
                        }
                    }
                }
            }
        }
        if (x==1) {
            dispose();
        }
    }//GEN-LAST:event_jbMenuActionPerformed

    private void jbNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNegritoActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.requestFocus();
        }
        if (t2) {
            jtpA.requestFocus();
        }
        if (t3) {
            jtpB.requestFocus();
        }
        if (t4) {
            jtpC.requestFocus();
        }
        if (t5) {
            jtpD.requestFocus();
        }
        if (t6) {
            jtpE.requestFocus();
        }
        if (t7) {
            jtpF.requestFocus();
        }
    }//GEN-LAST:event_jbNegritoActionPerformed

    private void jbItalicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbItalicoActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.requestFocus();
        }
        if (t2) {
            jtpA.requestFocus();
        }
        if (t3) {
            jtpB.requestFocus();
        }
        if (t4) {
            jtpC.requestFocus();
        }
        if (t5) {
            jtpD.requestFocus();
        }
        if (t6) {
            jtpE.requestFocus();
        }
        if (t7) {
            jtpF.requestFocus();
        }
    }//GEN-LAST:event_jbItalicoActionPerformed

    private void jbSublinhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSublinhadoActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.requestFocus();
        }
        if (t2) {
            jtpA.requestFocus();
        }
        if (t3) {
            jtpB.requestFocus();
        }
        if (t4) {
            jtpC.requestFocus();
        }
        if (t5) {
            jtpD.requestFocus();
        }
        if (t6) {
            jtpE.requestFocus();
        }
        if (t7) {
            jtpF.requestFocus();
        }
    }//GEN-LAST:event_jbSublinhadoActionPerformed

    private void jbFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFonteActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.requestFocus();
        }
        if (t2) {
            jtpA.requestFocus();
        }
        if (t3) {
            jtpB.requestFocus();
        }
        if (t4) {
            jtpC.requestFocus();
        }
        if (t5) {
            jtpD.requestFocus();
        }
        if (t6) {
            jtpE.requestFocus();
        }
        if (t7) {
            jtpF.requestFocus();
        }
    }//GEN-LAST:event_jbFonteActionPerformed

    private void jbCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCorActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.requestFocus();
        }
        if (t2) {
            jtpA.requestFocus();
        }
        if (t3) {
            jtpB.requestFocus();
        }
        if (t4) {
            jtpC.requestFocus();
        }
        if (t5) {
            jtpD.requestFocus();
        }
        if (t6) {
            jtpE.requestFocus();
        }
        if (t7) {
            jtpF.requestFocus();
        }
    }//GEN-LAST:event_jbCorActionPerformed

    private void jbDesfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDesfazerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbDesfazerActionPerformed

    private void jbRefazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRefazerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRefazerActionPerformed

    private void jbRecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRecortarActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.cut();
        }
        if (t2) {
            jtpA.cut();
        }
        if (t3) {
            jtpB.cut();
        }
        if (t4) {
            jtpC.cut();
        }
        if (t5) {
            jtpD.cut();
        }
        if (t6) {
            jtpE.cut();
        }
        if (t7) {
            jtpF.cut();
        }
    }//GEN-LAST:event_jbRecortarActionPerformed

    private void jbCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCopiarActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.copy();
        }
        if (t2) {
            jtpA.copy();
        }
        if (t3) {
            jtpB.copy();
        }
        if (t4) {
            jtpC.copy();
        }
        if (t5) {
            jtpD.copy();
        }
        if (t6) {
            jtpE.copy();
        }
        if (t7) {
            jtpF.copy();
        }
    }//GEN-LAST:event_jbCopiarActionPerformed

    private void jbColarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbColarActionPerformed
        // TODO add your handling code here:
        if (t1) {
            jtpEnunciado.paste();
        }
        if (t2) {
            jtpA.paste();
        }
        if (t3) {
            jtpB.paste();
        }
        if (t4) {
            jtpC.paste();
        }
        if (t5) {
            jtpD.paste();
        }
        if (t6) {
            jtpE.paste();
        }
        if (t7) {
            jtpF.paste();
        }
    }//GEN-LAST:event_jbColarActionPerformed

    private void jbLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizarActionPerformed
        // TODO add your handling code here:
        try {
            if (t1) {
                sbufer = new StringBuffer(jtpEnunciado.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpEnunciado.setCaretPosition(ind);
                jtpEnunciado.setSelectionStart(ind);
                jtpEnunciado.setSelectionEnd(ind+findString.length());
            }
            if (t2) {
                sbufer = new StringBuffer(jtpA.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpA.setCaretPosition(ind);
                jtpA.setSelectionStart(ind);
                jtpA.setSelectionEnd(ind+findString.length());
            }
            if (t3) {
                sbufer = new StringBuffer(jtpB.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpB.setCaretPosition(ind);
                jtpB.setSelectionStart(ind);
                jtpB.setSelectionEnd(ind+findString.length());
            }
            if (t4) {
                sbufer = new StringBuffer(jtpC.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpC.setCaretPosition(ind);
                jtpC.setSelectionStart(ind);
                jtpC.setSelectionEnd(ind+findString.length());
            }
            if (t5) {
                sbufer = new StringBuffer(jtpD.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpD.setCaretPosition(ind);
                jtpD.setSelectionStart(ind);
                jtpD.setSelectionEnd(ind+findString.length());
            }
            if (t6) {
                sbufer = new StringBuffer(jtpE.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpE.setCaretPosition(ind);
                jtpE.setSelectionStart(ind);
                jtpE.setSelectionEnd(ind+findString.length());
            }
            if (t7) {
                sbufer = new StringBuffer(jtpF.getText());
                findString = JOptionPane.showInputDialog(null, "Localizar");
                ind = sbufer.indexOf(findString);
                jtpF.setCaretPosition(ind);
                jtpF.setSelectionStart(ind);
                jtpF.setSelectionEnd(ind+findString.length());
            }
        } catch (IllegalArgumentException npe) {
            JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
        } catch (NullPointerException nfe) {

        }
    }//GEN-LAST:event_jbLocalizarActionPerformed

    private void jtpEnunciadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpEnunciadoFocusGained
        // TODO add your handling code here:
        t1 = true;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = false;
    }//GEN-LAST:event_jtpEnunciadoFocusGained

    private void jtpAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpAFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = true;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = false;
    }//GEN-LAST:event_jtpAFocusGained

    private void jtpBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpBFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = true;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = false;
    }//GEN-LAST:event_jtpBFocusGained

    private void jtpCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpCFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = true;
        t5 = false;
        t6 = false;
        t7 = false;
    }//GEN-LAST:event_jtpCFocusGained

    private void jtpDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpDFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = true;
        t6 = false;
        t7 = false;
    }//GEN-LAST:event_jtpDFocusGained

    private void jtpEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpEFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = true;
        t7 = false;
    }//GEN-LAST:event_jtpEFocusGained

    private void jtpFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtpFFocusGained
        // TODO add your handling code here:
        t1 = false;
        t2 = false;
        t3 = false;
        t4 = false;
        t5 = false;
        t6 = false;
        t7 = true;
    }//GEN-LAST:event_jtpFFocusGained

    private void jrbSimEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSimEActionPerformed
        // TODO add your handling code here:
        jtpE.setEnabled(true);
        jlE.setEnabled(true);
    }//GEN-LAST:event_jrbSimEActionPerformed

    private void jrbNaoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNaoEActionPerformed
        // TODO add your handling code here:
        jtpE.setEnabled(false);
        jlE.setEnabled(false);
        jtpF.setEnabled(false);
        jlF.setEnabled(false);
        jrbNaoF.setSelected(true);
    }//GEN-LAST:event_jrbNaoEActionPerformed

    private void jrbSimFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSimFActionPerformed
        // TODO add your handling code here:
        if (jrbSimE.isSelected()) {
            jtpF.setEnabled(true);
            jlF.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Não é possível adicionar a letra F sem antes adicionar a letra E!");
            jrbNaoF.setSelected(true);
        }
    }//GEN-LAST:event_jrbSimFActionPerformed

    private void jrbNaoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNaoFActionPerformed
        // TODO add your handling code here:
        jtpF.setEnabled(false);
        jlF.setEnabled(false);
    }//GEN-LAST:event_jrbNaoFActionPerformed

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
            java.util.logging.Logger.getLogger(EditarQuestaoFechadaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarQuestaoFechadaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarQuestaoFechadaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarQuestaoFechadaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarQuestaoFechadaGUI dialog = new EditarQuestaoFechadaGUI(new javax.swing.JFrame(), true);
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
    public void SalvarQuestao(String e, int d, String letraA, String letraB, String letraC,
                              String letraD, String letraE, String letraF, int idConteudo, int idQuestao) {        
        try {
            questoesBanco.inserirQuestaoFechadaEditada(
                    e, d, letraA, letraB, letraC, letraD, letraE, letraF, idConteudo, idQuestao);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(this, "Error SQL: "+sqlEx);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex);
        }
    }
    
    //SALVAR IMAGEM
    public void AddImagem(int idDestaQuestao) {        
        if (img) {
            try {
                rs = questoesBanco.confereSeTemImagem(idDestaQuestao);
                if (rs!=null) {
                    //PASSAR FILENAME
                    questoesBanco.inserirImagemEditada(input, posicaoImagem, idDestaQuestao, fileName);              
                } else {
                    //PASSAR FILENAME
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
		jbDesfazer.setEnabled(true);
		putValue("Undo", undo.getUndoPresentationName());
	    }
	    else {
		jbDesfazer.setEnabled(false);
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
                jbRefazer.setEnabled(true);
                putValue("Redo", undo.getRedoPresentationName());
            } else {
                jbRefazer.setEnabled(false);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbAbrir;
    private javax.swing.JButton jbCarregar;
    private javax.swing.JButton jbColar;
    private javax.swing.JButton jbCopiar;
    private javax.swing.JButton jbCor;
    private javax.swing.JButton jbDesfazer;
    private javax.swing.JButton jbFonte;
    private javax.swing.JButton jbItalico;
    private javax.swing.JButton jbLocalizar;
    private javax.swing.JButton jbMenu;
    private javax.swing.JButton jbNegrito;
    private javax.swing.JButton jbRecortar;
    private javax.swing.JButton jbRefazer;
    private javax.swing.JButton jbRemover;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSublinhado;
    private javax.swing.JComboBox jcbConteudo;
    private javax.swing.JComboBox jcbDificuldade;
    private javax.swing.JComboBox jcbDisciplina;
    private javax.swing.JLabel jlE;
    private javax.swing.JLabel jlF;
    private javax.swing.JLabel jlImagem;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenuItem jmiAbrir;
    private javax.swing.JMenuItem jmiCarregar;
    private javax.swing.JMenuItem jmiMenu;
    private javax.swing.JMenuItem jmiRemover;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JRadioButton jrbNaoE;
    private javax.swing.JRadioButton jrbNaoF;
    private javax.swing.JRadioButton jrbSimE;
    private javax.swing.JRadioButton jrbSimF;
    private javax.swing.JTextPane jtpA;
    private javax.swing.JTextPane jtpB;
    private javax.swing.JTextPane jtpC;
    private javax.swing.JTextPane jtpD;
    private javax.swing.JTextPane jtpE;
    private javax.swing.JTextPane jtpEnunciado;
    private javax.swing.JTextPane jtpF;
    private javax.swing.ButtonGroup letraE;
    private javax.swing.ButtonGroup letraF;
    // End of variables declaration//GEN-END:variables
}
