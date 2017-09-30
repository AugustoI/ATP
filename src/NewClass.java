
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class NewClass {
    public static void main(String[] args) {
        //JFrame
        JFrame frame = new JFrame("Editor de questões");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Fim JFrame                
        
        //JToolBar
        JToolBar toolbar = new JToolBar();
        toolbar.setRollover(true);
        
        JComboBox fonte = new JComboBox(new String[]{
        "Arial", "Calibri", "Times New Roman"
        });
        fonte.setMaximumSize(new Dimension(150, 27));
        toolbar.add(fonte);
        toolbar.addSeparator(new Dimension(1, 1));
        
        JToggleButton negrito = new JToggleButton("N");
        toolbar.add(negrito);
        toolbar.addSeparator(new Dimension(1, 1));
        
        JToggleButton italico = new JToggleButton("I");
        toolbar.add(italico);
        toolbar.addSeparator(new Dimension(1, 1));
        
        JToggleButton sublinhado = new JToggleButton("S");
        toolbar.add(sublinhado);
        toolbar.addSeparator(new Dimension(1, 1));
        //Fim JToolBar
        
        Container contentPane = frame.getContentPane();
        contentPane.add(toolbar, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        JScrollPane pane = new JScrollPane(textArea);
        contentPane.add(pane, BorderLayout.CENTER);
        
        frame.setSize(750, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
