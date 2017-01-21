package Coder;

import java.awt.datatransfer.Clipboard;  
import java.awt.datatransfer.DataFlavor;  
import java.awt.datatransfer.Transferable;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.InputEvent;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
  
import javax.swing.JMenuItem;  
import javax.swing.JPopupMenu;  
import javax.swing.JTextField;  
import javax.swing.KeyStroke;  
import javax.swing.text.Document;  
  
//ʵ��JTextfield �ĸ��ơ����С�ճ�����ܡ�  
  
public class MJTextField extends JTextField implements MouseListener {  
  
    private JPopupMenu pop = null; // �����˵�  
    private JMenuItem copy = null, paste = null, cut = null; // �������ܲ˵�  
  
    public MJTextField() {  
        super();  
        init();  
    }  
    public MJTextField(int columns){  
        super(columns);  
        init();  
    }  
    public MJTextField(String text){  
        super(text);  
        init();  
    }  
    public MJTextField(String text, int columns){  
        super(text, columns);  
        init();  
    }  
    public MJTextField(Document doc, String text, int columns){  
        super(doc, text, columns);  
        init();  
    }  
      
    private void init() {  
        this.addMouseListener(this);  
        pop = new JPopupMenu();  
        pop.add(copy = new JMenuItem("����"));  
        pop.add(paste = new JMenuItem("ճ��"));  
        pop.add(cut = new JMenuItem("����"));  
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));  
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));  
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));  
        copy.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        paste.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        cut.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                action(e);  
            }  
        });  
        this.add(pop);  
    }  
  
    public void action(ActionEvent e) {  
        String str = e.getActionCommand();  
        if (str.equals(copy.getText())) { // ����  
            this.copy();  
        } else if (str.equals(paste.getText())) { // ճ��  
            this.paste();  
        } else if (str.equals(cut.getText())) { // ����  
            this.cut();  
        }  
    }  
  
    public JPopupMenu getPop() {  
        return pop;  
    }  
  
    public void setPop(JPopupMenu pop) {  
        this.pop = pop;  
    }  
  
    /** 
     * ���а����Ƿ����ı����ݿɹ�ճ�� 
     *  
     * @return trueΪ���ı����� 
     */  
    public boolean pastable() {  
        boolean b = false;  
        Clipboard clipboard = this.getToolkit().getSystemClipboard();  
        Transferable content = clipboard.getContents(this);  
        try {  
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {  
                b = true;  
            }  
        } catch (Exception e) {  
        }  
        return b;  
    }  
  
    /** 
     * �ı�������Ƿ�߱����Ƶ����� 
     *  
     * @return trueΪ�߱� 
     */  
    public boolean copyable() {  
        boolean b = false;  
        int start = this.getSelectionStart();  
        int end = this.getSelectionEnd();  
        if (start != end)  
            b = true;  
        return b;  
    }  
  
    public void mouseClicked(MouseEvent e) {  
    }  
  
    public void mouseEntered(MouseEvent e) {  
    }  
  
    public void mouseExited(MouseEvent e) {  
    }  
  
    public void mousePressed(MouseEvent e) {  
        if (e.getButton() == MouseEvent.BUTTON3) {  
            copy.setEnabled(copyable());  
            paste.setEnabled(pastable());  
            cut.setEnabled(copyable());  
            pop.show(this, e.getX(), e.getY());  
        }  
    }  
  
    public void mouseReleased(MouseEvent e) {  
    }  
  
}  