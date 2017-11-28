/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorQuestoes;

import banco.Questoes;
import java.awt.BorderLayout;
import java.awt.Color;
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
public class EditorQuestaoFechada extends javax.swing.JDialog {

    /**
     * Creates new form EditorQuestaoFechada
     */
    Questoes questoesBanco = new Questoes();
    JDialog dialog = new JDialog();
    List<String> strList = new ArrayList<String>();
    
    ResultSet rs;
    DefaultComboBoxModel modelComboBox;  
    FileInputStream input;
    String fileName, enunciado, disciplina, conteudo, alternativaA, alternativaB, alternativaC, alternativaD, alternativaE, alternativaF;
    int idQuestao, dificuldade, idConteudo, posicaoImagem;      
    boolean img;    
    
    public EditorQuestaoFechada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Editor de Questão FECHADA");
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem Negrito
        Action boldAction = new EditorQuestaoFechada.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jmTexto.add(boldAction);  
        jbNegrito.addActionListener(boldAction);
        
        //MenuItem Itálico
        Action italicAction = new EditorQuestaoFechada.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jmTexto.add(italicAction);
        jbItalico.addActionListener(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new EditorQuestaoFechada.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jmTexto.add(underlineAction);
        jbSublinhado.addActionListener(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new EditorQuestaoFechada.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jmTexto.add(foregroundAction);
        jbCor.addActionListener(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new EditorQuestaoFechada.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jmTexto.add(formatTextAction);
        jbFonte.addActionListener(formatTextAction);
        
        jtpEnunciado2.requestFocus();
    }
    
    public EditorQuestaoFechada(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Editor de Questão FECHADA");
        
        //Carregar os dois combobox (disciplinas e conteúdos)
        CarregarComboBox();
        
        //MenuItem Negrito
        Action boldAction = new EditorQuestaoFechada.BoldAction();
        boldAction.putValue(Action.NAME, "Negrito");
        jmTexto.add(boldAction);  
        
        //MenuItem Itálico
        Action italicAction = new EditorQuestaoFechada.ItalicAction();
        italicAction.putValue(Action.NAME, "Itálico");
        jmTexto.add(italicAction);

        //MenuItem Sublinhado
        Action underlineAction = new EditorQuestaoFechada.UnderlineAction();
        underlineAction.putValue(Action.NAME, "Sublinhado");
        jmTexto.add(underlineAction);

        //MenuItem Cor
        Action foregroundAction = new EditorQuestaoFechada.ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Cor do texto");
        jmTexto.add(foregroundAction);

        //MenuItem Fonte
        Action formatTextAction = new EditorQuestaoFechada.FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Fonte");
        jmTexto.add(formatTextAction);
        
        jtpEnunciado2.requestFocus();
        
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
                
                jtpEnunciado2.setText(enunciado);
                jtpLetraA1.setText(alternativaA);
                jtpLetraB1.setText(alternativaB);
                jtpLetraC1.setText(alternativaC);
                jtpLetraD1.setText(alternativaD);
                jtpLetraE1.setText(alternativaE);
                jtpLetraF1.setText(alternativaF);
                jcbDificuldade3.setSelectedIndex(dificuldade);
                
                if (!alternativaE.isEmpty()) {
                    jtpLetraE1.setEnabled(true);
                    jLabelE1.setEnabled(true);
                    jrbSimE1.setSelected(true);
                }
                if (!alternativaF.isEmpty()) {
                    jtpLetraF1.setEnabled(true);
                    jLabelF1.setEnabled(true);
                    jrbSimF1.setSelected(true);
                }
            }
            
            rs = questoesBanco.pegarNomeDisciplinasPeloConteudosID(idConteudo);
            if (rs!=null) {                                
                disciplina = rs.getString("NomeDisciplinas");                
                jcbDisciplina3.setSelectedItem(disciplina);
            }
            
            rs = questoesBanco.pegarNomeConteudosPeloConteudosID(idConteudo);
            if (rs!=null) {                                
                conteudo = rs.getString("NomeConteudos");                
                jcbConteudo3.setSelectedItem(conteudo);
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

        letraE = new javax.swing.ButtonGroup();
        letraF = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jcbDisciplina3 = new javax.swing.JComboBox<String>();
        jLabel16 = new javax.swing.JLabel();
        jcbConteudo3 = new javax.swing.JComboBox<String>();
        jbVoltar1 = new javax.swing.JButton();
        jbImagem3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jcbDificuldade3 = new javax.swing.JComboBox<String>();
        jbSalvar3 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtpEnunciado2 = new javax.swing.JTextPane();
        jLabelA1 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtpLetraA1 = new javax.swing.JTextPane();
        jLabelC1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtpLetraC1 = new javax.swing.JTextPane();
        jLabelE1 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtpLetraE1 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtpLetraB1 = new javax.swing.JTextPane();
        letraD1 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtpLetraD1 = new javax.swing.JTextPane();
        jLabelF1 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtpLetraF1 = new javax.swing.JTextPane();
        jrbSimE1 = new javax.swing.JRadioButton();
        jrbNãoE1 = new javax.swing.JRadioButton();
        jrbSimF1 = new javax.swing.JRadioButton();
        jrbNãoF1 = new javax.swing.JRadioButton();
        jbNegrito = new javax.swing.JButton();
        jbItalico = new javax.swing.JButton();
        jbSublinhado = new javax.swing.JButton();
        jbCor = new javax.swing.JButton();
        jbFonte = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlImagem = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmiImagem = new javax.swing.JMenuItem();
        jmiSalvar = new javax.swing.JMenuItem();
        jmiSair = new javax.swing.JMenuItem();
        jmTexto = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel14.setText("Disciplina:");

        jLabel15.setText("Enunciado da questão:");

        jLabel16.setText("Conteúdo:");

        jbVoltar1.setText("Cancelar");
        jbVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoltar1ActionPerformed(evt);
            }
        });

        jbImagem3.setText("Carregar imagem");
        jbImagem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImagem3jbImagem2ActionPerformed(evt);
            }
        });

        jLabel17.setText("Dificuldade:");

        jcbDificuldade3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jcbDificuldade3.setToolTipText("");

        jbSalvar3.setText("Salvar");
        jbSalvar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvar3jbSalvar2ActionPerformed(evt);
            }
        });

        jScrollPane9.setViewportView(jtpEnunciado2);

        jLabelA1.setText("Alternativa A:");

        jScrollPane10.setViewportView(jtpLetraA1);

        jLabelC1.setText("Alternativa C:");

        jScrollPane11.setViewportView(jtpLetraC1);

        jLabelE1.setText("Alternativa E:");
        jLabelE1.setEnabled(false);

        jtpLetraE1.setEnabled(false);
        jScrollPane12.setViewportView(jtpLetraE1);

        jLabel9.setText("Alternativa B:");

        jScrollPane13.setViewportView(jtpLetraB1);

        letraD1.setText("AlternativaD:");

        jScrollPane14.setViewportView(jtpLetraD1);

        jLabelF1.setText("AlternativaF:");
        jLabelF1.setEnabled(false);

        jtpLetraF1.setEnabled(false);
        jScrollPane15.setViewportView(jtpLetraF1);

        letraE.add(jrbSimE1);
        jrbSimE1.setText("Sim");
        jrbSimE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSimE1ActionPerformed(evt);
            }
        });

        letraE.add(jrbNãoE1);
        jrbNãoE1.setSelected(true);
        jrbNãoE1.setText("Não");
        jrbNãoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNãoE1ActionPerformed(evt);
            }
        });

        letraF.add(jrbSimF1);
        jrbSimF1.setText("Sim");
        jrbSimF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbSimF1ActionPerformed(evt);
            }
        });

        letraF.add(jrbNãoF1);
        jrbNãoF1.setSelected(true);
        jrbNãoF1.setText("Não");
        jrbNãoF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNãoF1ActionPerformed(evt);
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

        jbSublinhado.setText("S");

        jbCor.setText("Cor do texto");

        jbFonte.setText("Fonte");

        jLabel1.setText("Imagem:");

        jlImagem.setText("jlImagem");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelA1)
                            .addComponent(jLabelC1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelE1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbSimE1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbNãoE1))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(jScrollPane10)
                            .addComponent(jScrollPane12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelF1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbSimF1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbNãoF1))
                            .addComponent(jLabel9)
                            .addComponent(letraD1)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jbNegrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbItalico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSublinhado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbCor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbFonte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbVoltar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbImagem3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSalvar3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbDisciplina3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(192, 192, 192)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jcbConteudo3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbDificuldade3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlImagem)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbConteudo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jcbDisciplina3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jcbDificuldade3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbImagem3)
                    .addComponent(jbSalvar3)
                    .addComponent(jbVoltar1)
                    .addComponent(jbNegrito)
                    .addComponent(jbItalico)
                    .addComponent(jbSublinhado)
                    .addComponent(jbCor)
                    .addComponent(jbFonte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1)
                    .addComponent(jlImagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelA1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jScrollPane10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelC1)
                    .addComponent(letraD1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jScrollPane14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelE1)
                    .addComponent(jrbSimE1)
                    .addComponent(jrbNãoE1)
                    .addComponent(jLabelF1)
                    .addComponent(jrbSimF1)
                    .addComponent(jrbNãoF1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jScrollPane15))
                .addGap(0, 11, Short.MAX_VALUE))
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

        jmiSair.setText("Menu principal");
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
            .addGap(0, 657, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoltar1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbVoltar1ActionPerformed

    private void jbImagem3jbImagem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImagem3jbImagem2ActionPerformed
        // TODO add your handling code here:
        TesteImagem a = new TesteImagem(null, true);
        a.setVisible(true); 
        img = a.certo;
        posicaoImagem = a.posicao;
        input = a.input;
        fileName = a.fileName;
    }//GEN-LAST:event_jbImagem3jbImagem2ActionPerformed

    private void jbSalvar3jbSalvar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvar3jbSalvar2ActionPerformed
        // TODO add your handling code here:       
        try {
            if ((!jtpEnunciado2.getText().isEmpty())&&(!jtpLetraA1.getText().isEmpty())&&(!jtpLetraB1.getText().isEmpty())
                    &&(!jtpLetraC1.getText().isEmpty())&&(!jtpLetraD1.getText().isEmpty())) {
                if (((!jtpLetraE1.getText().isEmpty())&&(jrbSimE1.isSelected()))||(jrbNãoE1.isSelected())) {
                    if (((!jtpLetraF1.getText().isEmpty())&&(jrbSimF1.isSelected()))||(jrbNãoF1.isSelected())) {
                        if (jcbConteudo3.getItemCount() != 0) {
                            int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Tem certeza que deseja salvar esta questão?", "Salvar questão",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                            if (x==0) {
                                enunciado = jtpEnunciado2.getText();
                                dificuldade = Integer.parseInt(jcbDificuldade3.getSelectedItem().toString());
                                alternativaA = jtpLetraA1.getText();
                                alternativaB = jtpLetraB1.getText();
                                alternativaC = jtpLetraC1.getText();
                                alternativaD = jtpLetraD1.getText();
                                alternativaE = "";
                                alternativaF = "";
                                if (jrbSimE1.isSelected()) {
                                    alternativaE = jtpLetraE1.getText();
                                }
                                if (jrbSimF1.isSelected()) {
                                    alternativaF = jtpLetraF1.getText();
                                }

                                rs = questoesBanco.pegarConteudosID(jcbConteudo3.getSelectedItem().toString());
                                if (rs!=null) {
                                    do {
                                        idConteudo = rs.getInt("Conteudos_ID");    
                                    } while (rs.next());
                                    SalvarQuestao(enunciado, dificuldade, alternativaA, alternativaB, alternativaC, 
                                            alternativaD, alternativaE, alternativaF, idConteudo, idQuestao);
                                    AddImagem(idQuestao);
                                }                            

                                jtpEnunciado2.setText("");
                                jtpLetraA1.setText("");
                                jtpLetraB1.setText("");
                                jtpLetraC1.setText("");
                                jtpLetraD1.setText("");
                                jtpLetraE1.setText("");
                                jtpLetraF1.setText("");

                                jLabelE1.setEnabled(false);
                                jLabelF1.setEnabled(false);
                                jrbSimE1.setSelected(true);
                                jrbNãoE1.setSelected(true);
                                jrbSimF1.setSelected(true);
                                jrbNãoF1.setSelected(true);
                                jtpLetraE1.setEnabled(false);
                                jtpLetraF1.setEnabled(false);
                                
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
                if (jtpEnunciado2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O enunciado da questão não pode ser vazio!");
                } else {
                    JOptionPane.showMessageDialog(this, "Uma ou mais opções estão vazias!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro. Tente novamente");
        }
    }//GEN-LAST:event_jbSalvar3jbSalvar2ActionPerformed

    private void jrbSimE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSimE1ActionPerformed
        // TODO add your handling code here:
        jtpLetraE1.setEnabled(true);
        jLabelE1.setEnabled(true);
    }//GEN-LAST:event_jrbSimE1ActionPerformed

    private void jrbNãoE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNãoE1ActionPerformed
        // TODO add your handling code here:
        jtpLetraE1.setEnabled(false);
        jLabelE1.setEnabled(false);
        jtpLetraF1.setEnabled(false);
        jLabelF1.setEnabled(false);
        jrbNãoF1.setSelected(true);
    }//GEN-LAST:event_jrbNãoE1ActionPerformed

    private void jrbSimF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbSimF1ActionPerformed
        // TODO add your handling code here:
        if (jrbSimE1.isSelected()) {
            jtpLetraF1.setEnabled(true);
            jLabelF1.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Não é possível adicionar a letra F sem antes adicionar a letra E!");
            jrbNãoF1.setSelected(true);
        }
    }//GEN-LAST:event_jrbSimF1ActionPerformed

    private void jrbNãoF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNãoF1ActionPerformed
        // TODO add your handling code here:
        jtpLetraF1.setEnabled(false);
        jLabelF1.setEnabled(false);
    }//GEN-LAST:event_jrbNãoF1ActionPerformed

    private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalvarActionPerformed
        // TODO add your handling code here:
        jbSalvar3jbSalvar2ActionPerformed(evt);
    }//GEN-LAST:event_jmiSalvarActionPerformed

    private void jmiImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiImagemActionPerformed
        // TODO add your handling code here:
        jbImagem3jbImagem2ActionPerformed(evt);
    }//GEN-LAST:event_jmiImagemActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(this.getContentPane(), "Deseja salvar antes de sair?", "Encerrar",
            JOptionPane.YES_NO_CANCEL_OPTION);
        if (x==0) {
            jbSalvar3jbSalvar2ActionPerformed(evt);
            if ((!jtpEnunciado2.getText().isEmpty())&&(!jtpLetraA1.getText().isEmpty())&&(!jtpLetraB1.getText().isEmpty())
                &&(!jtpLetraC1.getText().isEmpty())&&(!jtpLetraD1.getText().isEmpty())) {
                if (((!jtpLetraE1.getText().isEmpty())&&(jrbSimE1.isSelected()))||(jrbNãoE1.isSelected())) {
                    if (((!jtpLetraF1.getText().isEmpty())&&(jrbSimF1.isSelected()))||(jrbNãoF1.isSelected())) {
                        if (jcbConteudo3.getItemCount() != 0) {
                            dispose();
                        }
                    }
                }
            }
        }
        if (x==1) {
            dispose();
        }
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jbNegritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNegritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbNegritoActionPerformed

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
            java.util.logging.Logger.getLogger(EditorQuestaoFechada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoFechada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoFechada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorQuestaoFechada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditorQuestaoFechada dialog = new EditorQuestaoFechada(new javax.swing.JFrame(), true);
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
                    questoesBanco.inserirImagemEditada(input, posicaoImagem, idDestaQuestao);              
                } else {
                    //PASSAR FILENAME
                    questoesBanco.inserirImagem(idDestaQuestao, input, posicaoImagem);              
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro, tente novamente!");
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
            jcbDisciplina3.setModel(modelComboBox);
            
            //Conteúdos
            strList.removeAll(strList);
            rs = questoesBanco.pegarConteudos(jcbDisciplina3.getSelectedItem().toString());
            if (rs!=null) {
                do {
                    strList.add(
                            rs.getString("NomeConteudos"));
                } while (rs.next());                   
            }             
            modelComboBox = new DefaultComboBoxModel(strList.toArray());
            jcbConteudo3.setModel(modelComboBox);     
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelA1;
    private javax.swing.JLabel jLabelC1;
    private javax.swing.JLabel jLabelE1;
    private javax.swing.JLabel jLabelF1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jbCor;
    private javax.swing.JButton jbFonte;
    private javax.swing.JButton jbImagem3;
    private javax.swing.JButton jbItalico;
    private javax.swing.JButton jbNegrito;
    private javax.swing.JButton jbSalvar3;
    private javax.swing.JButton jbSublinhado;
    private javax.swing.JButton jbVoltar1;
    private javax.swing.JComboBox<String> jcbConteudo3;
    private javax.swing.JComboBox<String> jcbDificuldade3;
    private javax.swing.JComboBox<String> jcbDisciplina3;
    private javax.swing.JLabel jlImagem;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenu jmTexto;
    private javax.swing.JMenuItem jmiImagem;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiSalvar;
    private javax.swing.JRadioButton jrbNãoE1;
    private javax.swing.JRadioButton jrbNãoF1;
    private javax.swing.JRadioButton jrbSimE1;
    private javax.swing.JRadioButton jrbSimF1;
    private javax.swing.JTextPane jtpEnunciado2;
    private javax.swing.JTextPane jtpLetraA1;
    private javax.swing.JTextPane jtpLetraB1;
    private javax.swing.JTextPane jtpLetraC1;
    private javax.swing.JTextPane jtpLetraD1;
    private javax.swing.JTextPane jtpLetraE1;
    private javax.swing.JTextPane jtpLetraF1;
    private javax.swing.JLabel letraD1;
    private javax.swing.ButtonGroup letraE;
    private javax.swing.ButtonGroup letraF;
    // End of variables declaration//GEN-END:variables
}
