package Coder;

import java.awt.*;
import java.awt.event.*;

import java.io.UnsupportedEncodingException;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  

import java.util.Base64;

/** 
 * 采用MD5加密解密 
 * @author tfq 
 * @datetime 2011-10-13 
 */  
class MD5Util {  
  
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
}

public class  Coder
{

	//定义该图形中所需的组件的引用。
	private Frame f;
	private Button but1,but2;
	private TextField tf1, tf2,tf3,tf4,tf5,tf6;
	private MD5Util  md5u = new MD5Util();
	

	Coder()
	{
		init();
	}

	public void init()
	{
		f = new Frame("Coder");

		//对frame进行基本设置。
		f.setBounds(300,100,450,500);
		f.setLayout(new FlowLayout());
		
		tf1 = new TextField("",40);
		tf2 = new TextField("",40);
		tf3 = new TextField("",40);
		tf4 = new TextField("",40);
		tf5 = new TextField("",40);
		tf6 = new TextField("",40);
		but1 = new Button("Code/Decode");
		but2 = new Button("Clear");
		
		//将组件添加到frame中
		f.add(new Label("      原       文 "));
		f.add(tf1);
		f.add(new Label("     32位MD5"));
		f.add(tf2);
		f.add(new Label("     16位MD5"));
		f.add(tf3);
		f.add(new Label("Base64编码"));
		f.add(tf4);
		f.add(new Label("Base64解码"));
		f.add(tf5);
		f.add(but1);
		f.add(but2);


		//加载一下窗体上事件。
		myEvent();

		//显示窗体;
		f.setVisible(true);

	}
	private void myEvent()
	{
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		but1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				String str = tf1.getText();
				String md5str = md5u.string2MD5(str);
				String base64str = Base64.getEncoder().encodeToString(str.getBytes());
				String decodebase64str;
				
				tf2.setText(md5str);
				tf3.setText(md5str.substring(8,24));
				tf4.setText(base64str);
				try {
					byte[] base64decodedBytes = Base64.getDecoder().decode(str);
					decodebase64str = new String(base64decodedBytes);
				}catch(IllegalArgumentException e1) {
					decodebase64str = new String("");
				}
				tf5.setText(decodebase64str);
				
			}
		});
		
		but2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
							
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");	
			}
		});
	}



	public static void main(String[] args) 
	{
		new Coder();
	}
}
