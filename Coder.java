package Coder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Base64;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;


public class  Coder
{
	private MD5Util  md5u = new MD5Util();
	private Des3 des3u = new Des3();
	
	private JFrame jf;	
	private String coding_scheme = "gbk";
	
	//tab1
	private JButton but1,but2;   
	private MJTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
	
	//tab2
	private JButton rot13but,vg_but1,vg_but2,t2but2;
	private MJTextField t2tf1,t2tf2,t2tf3,t2tf4,t2tf5,t2tf6,t2tf7;
	
	//tab3
	private JButton des_but1,des_but2,des_but3;
	private MJTextField des_tf1,des_tf2,des_tf3,des_tf4;
	private JRadioButton des_jrb1,des_jrb2,des_jrb3,des_jrb4;
	private ButtonGroup  des_bg1,des_bg2;
	private int cipher_algorithm = 0;  //0: AES    1: 3DES
	private int encrypt_mode = 0;   //0: CBC   1: ECB
	private String tmpIV = "";
	
	Coder()
	{
		init();
	}

	public void init()
	{
		jf = new JFrame("Coder 1.0.0");

		jf.setBounds(300,300,600,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tp = new JTabbedPane();
		jf.setContentPane(tp);
		
		JPanel jpl1 = new JPanel();	
		JPanel jpl2 = new JPanel();
		JPanel jpl3 = new JPanel();
		jpl1.setLayout(new FlowLayout());
		jpl2.setLayout(new FlowLayout());
		jpl3.setLayout(new FlowLayout());
		
		tp.addTab("code", jpl1);
		tp.setEnabledAt(0,true);
		tp.addTab("cipher", jpl2);
		tp.setEnabledAt(1,true);
		tp.addTab("AES/3DES", jpl3);
		tp.setEnabledAt(2,true);
        
		//Tab1
		tf1 = new MJTextField(40);
		tf2 = new MJTextField(40);
		tf3 = new MJTextField(40);
		tf4 = new MJTextField(40);
		tf5 = new MJTextField(40);
		tf6 = new MJTextField(40);
		tf7 = new MJTextField(40);
		but1 = new JButton("Code/Decode");
		but2 = new JButton("Clear");
		
		jpl1.add(new JLabel("      原       文 "));
		jpl1.add(tf1);
		jpl1.add(new JLabel("     32位MD5"));
		jpl1.add(tf2);
		jpl1.add(new JLabel("     16位MD5"));
		jpl1.add(tf3);
		jpl1.add(new JLabel("Base64编码"));
		jpl1.add(tf4);
		jpl1.add(new JLabel("Base64解码"));
		jpl1.add(tf5);
		jpl1.add(new JLabel(" URLEncode"));
		jpl1.add(tf6);
		jpl1.add(new JLabel(" URLDecode"));
		jpl1.add(tf7);
		jpl1.add(but1);
		jpl1.add(but2);

		//Tab2
		t2tf1 = new MJTextField(40);
		t2tf2 = new MJTextField(40);
		t2tf3 = new MJTextField(40);
		rot13but = new JButton("Rot13");
		vg_but1 = new JButton("维吉尼亚加密");
		vg_but2 = new JButton("维吉尼亚解密");
		t2but2 = new JButton("Clear");
		
		jpl2.add(new JLabel("    Plaintext   "));
		jpl2.add(t2tf1);
		jpl2.add(new JLabel(" Ciphertext   "));
		jpl2.add(t2tf2);
		jpl2.add(new JLabel("             Key   "));
		jpl2.add(t2tf3);
		jpl2.add(rot13but);
		jpl2.add(vg_but1);
		jpl2.add(vg_but2);
		jpl2.add(t2but2);

		
		//Tab3  DES
		des_tf1 = new MJTextField(40);
		des_tf2 = new MJTextField(40);
		des_tf3 = new MJTextField(40);
		des_tf4 = new MJTextField(40);
		
		des_jrb1 = new JRadioButton("AES",true);
		des_jrb2 = new JRadioButton("3DES");
		des_jrb3 = new JRadioButton("CBC",true);
		des_jrb4 = new JRadioButton("ECB");
		
		des_but1 = new JButton("Encrypt");
		des_but2 = new JButton("Decrypt");
		des_but3 = new JButton("Clear");
		
		jpl3.add(new JLabel("    Plaintext   "));
		jpl3.add(des_tf1);
		jpl3.add(new JLabel(" Ciphertext   "));
		jpl3.add(des_tf2);
		jpl3.add(new JLabel("             Key   "));
		jpl3.add(des_tf3);
		jpl3.add(new JLabel("               Iv    "));
		jpl3.add(des_tf4);
		
		jpl3.add(des_but1);
		jpl3.add(des_but2);
		jpl3.add(des_but3);
		
		des_bg1 = new ButtonGroup();
		des_bg1.add(des_jrb1);
		des_bg1.add(des_jrb2);
		des_bg2 = new ButtonGroup();
		des_bg2.add(des_jrb3);
		des_bg2.add(des_jrb4);
		jpl3.add(des_jrb1);
		jpl3.add(des_jrb2);
		jpl3.add(des_jrb3);
		jpl3.add(des_jrb4);
		
		
		//加载一下窗体上事件。
		tab1_Event();
		
		tab2_Event();
		
		tab3_Event();

		//显示窗体;
		jf.setVisible(true);

	}
	
	private void tab1_Event()
	{
		but1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				String str = tf1.getText();
				String md5str = md5u.string2MD5(str);
				String base64str = Base64.getEncoder().encodeToString(str.getBytes());
				String decodebase64str;
				String urlEncode,urlDecode;
				
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
				try {
				    urlEncode = URLEncoder.encode(str, coding_scheme);				    
				} catch (UnsupportedEncodingException e2) {
					urlEncode = "";
				}
				tf6.setText(urlEncode);
				try {
				    urlDecode = URLDecoder.decode(str, coding_scheme);				    
				} catch (UnsupportedEncodingException e2) {
					urlDecode = "";
				}
				//urlDecode = System.getProperty("file.encoding");
				tf7.setText(urlDecode);
				
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
				tf6.setText("");
				tf7.setText("");
			}
		});
	}
	
//Tab2 Event
	private void tab2_Event()
	{
		rot13but.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = null;
				String str1 = t2tf1.getText();
				String str2 = t2tf2.getText();
				StringBuffer strbuf = new StringBuffer();
				
				if (str1.length() != 0) {
					str = str1;
				} 
				else if (str2.length() != 0) {
					str = str2;
				} else return;
				
				for (int i = 0; i < str.length(); i++) {
		            char c = str.charAt(i);
		            if       (c >= 'a' && c <= 'm') c += 13;
		            else if  (c >= 'A' && c <= 'M') c += 13;
		            else if  (c >= 'n' && c <= 'z') c -= 13;
		            else if  (c >= 'N' && c <= 'Z') c -= 13;
		            strbuf.append(c);
		        }
				if (str1.length() != 0) {
					t2tf2.setText(strbuf.toString());
				} 
				else if (str2.length() != 0) {
					t2tf1.setText(strbuf.toString());
				}
				
			}
		});
		
		// Vigenère encrypt
		vg_but1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
							
				String str1 = t2tf1.getText();
				String key  = t2tf3.getText();
				StringBuffer strbuf = new StringBuffer();
				char[] kbuf = new char[100];
				int i,j,keylen;
				
				if (str1.length() == 0 ) return;
				
				//构造密钥对照表  
				keylen = key.length();
				if (keylen == 0) {
					t2tf2.setText("----Need Key!----");
				    return;
				}
				
				for (i=0; i<keylen; i++) {
				   if  (key.charAt(i)>='a'&&key.charAt(i)<='z')
				        kbuf[i]=(char)(key.charAt(i)-97);
				   else if  (key.charAt(i)>='A'&&key.charAt(i)<='Z')
				        kbuf[i]=(char)(key.charAt(i)-65);
				   else {
					    t2tf2.setText("----Key Error!----");
					    return;
				   }
				}
				
				j = 0;
				for (i=0; i<str1.length(); i++) {
					j = j%keylen;
				    if (str1.charAt(i)>='a' && str1.charAt(i)<='z') {				    	
				    	strbuf.append((char)((str1.charAt(i)-97+kbuf[j])%26+97));
				    	j++;
				    } else if (str1.charAt(i)>='A' && str1.charAt(i)<='Z') {
				    	strbuf.append((char)((str1.charAt(i)-65+kbuf[j])%26+65));
				    	j++;
				    } else strbuf.append(str1.charAt(i));
				}				
				t2tf2.setText(strbuf.toString());
			}
		});
		
		//// Vigenère decrypt
		vg_but2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String str1 = t2tf2.getText();
				String key  = t2tf3.getText();
				StringBuffer strbuf = new StringBuffer();
				char[] kbuf = new char[100];
				int i,j,keylen;
				
				if (str1.length() == 0 ) return;	
				keylen = key.length();
				if (keylen == 0) {
					t2tf1.setText("----Need Key!----");
				    return;
				}
				
				//构造密钥对照表  
				for (i=0; i<keylen; i++) {
				   if  (key.charAt(i)>='a'&&key.charAt(i)<='z')
				        kbuf[i]=(char)(key.charAt(i)-97);
				   else if  (key.charAt(i)>='A'&&key.charAt(i)<='Z')
				        kbuf[i]=(char)(key.charAt(i)-65);
				   else {
					    t2tf2.setText("----Key Error!----");
					    return;
				   }
				}
				
				j = 0;
				for (i=0; i<str1.length(); i++) {
					j = j%keylen;
				    if (str1.charAt(i)>='a' && str1.charAt(i)<='z') {
				    	strbuf.append((char)((str1.charAt(i)-97-kbuf[j]+26)%26+97));
				    	j++;
				    } else if (str1.charAt(i)>='A' && str1.charAt(i)<='Z') {
				    	strbuf.append((char)((str1.charAt(i)-65-kbuf[j]+26)%26+65));
				    	j++;
				    } else strbuf.append(str1.charAt(i));
				}
				
				t2tf1.setText(strbuf.toString());
			}
		});
		
		t2but2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
							
				t2tf2.setText("");
				t2tf3.setText("");
				
			}
		});
	}

	
	//Tab3 Event
	private void tab3_Event()
	{	
			// des3 encrypt
			des_but1.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
				    String strKey = des_tf3.getText();
					String strIv = des_tf4.getText();
					byte[] data,key,iv,ciphertext;
						
					switch (cipher_algorithm) {   
					    case 0:   // AES
					        break;
					    
					    case 1:  // 3DES
					        if (encrypt_mode == 0)  {  //CBC
					            if (strKey.length() != 24) {
						            des_tf2.setText("---The length of the key must be 24!----");
						            return;
					            }
					            if (strIv.length() != 8) {
						            des_tf2.setText("---The length of the IV must be 8!----");
						            return;
					            }
					
					            try {
					                data = des_tf1.getText().getBytes(coding_scheme);
					                key = strKey.getBytes(coding_scheme);
					                iv = strIv.getBytes(coding_scheme);
					                ciphertext = des3u.des3EncodeCBC(key, iv, data); 
					                des_tf2.setText(Base64.getEncoder().encodeToString(ciphertext));
					            } catch (Exception e1) { return; }
					        }
					        else if (encrypt_mode == 1) { //ECB
					        	if (strKey.length() != 24) {
						            des_tf2.setText("---The length of the key must be 24!----");
						            return;
					            }
					
					            try {
					                data = des_tf1.getText().getBytes(coding_scheme);
					                key = strKey.getBytes(coding_scheme);
					                ciphertext = des3u.des3EncodeECB(key, data); 
					                des_tf2.setText(Base64.getEncoder().encodeToString(ciphertext));
					            } catch (Exception e1) { return; }
					        }
					        break;
					        
					    default:
					    	return;
					}   //end case
				}
			});
			
			//// des3 decrypt
			des_but2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				    String strKey = des_tf3.getText();
					String strIv = des_tf4.getText();
					byte[] data,key,iv,ciphertext;
						
					switch (cipher_algorithm) {   
					    case 0:   // AES
					        break;
					    
					    case 1:  // 3DES
					        if (encrypt_mode == 0)  {  //CBC
					            if (strKey.length() != 24) {
						            des_tf1.setText("---The length of the key must be 24!----");
						            return;
					            }
					            if (strIv.length() != 8 )  {
						            des_tf1.setText("---The length of the IV must be 8 !----");
						            return;
					            }
					
					            try {
					                key = strKey.getBytes(coding_scheme);
					                iv = strIv.getBytes(coding_scheme);
					                data = Base64.getDecoder().decode(des_tf2.getText());
								    byte[] plaintext = des3u.des3DecodeCBC(key, iv, data); 
								    des_tf1.setText(new String(plaintext));
					            } catch (Exception e1) { return; }
					        }
					        else if (encrypt_mode == 1) { //ECB
					        	if (strKey.length() != 24) {
						            des_tf1.setText("---The length of the key must be 24!----");
						            return;
					            }
					
					            try {
					                key = strKey.getBytes(coding_scheme);
					                data = Base64.getDecoder().decode(des_tf2.getText());
								    byte[] plaintext = des3u.des3DecodeECB(key, data); 
								    des_tf1.setText(new String(plaintext));
					            } catch (Exception e1) { return; }
					        }
					        break;
					        
					    default:
					    	return;
					}   //end case				
				}
			});
			
			des_but3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					des_tf1.setText("");
					des_tf2.setText("");
				}
			});
			
			des_jrb1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{			
					cipher_algorithm = 0;  //AES
				}
			});
			
			des_jrb2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{			
					cipher_algorithm = 1;   //3DES
				}
			});
			
			des_jrb3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{			
					encrypt_mode = 0;  //CBC
					des_tf4.setEditable(true);
					des_tf4.setText(tmpIV);
				}
			});
			
			des_jrb4.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{			
					encrypt_mode = 1;   //ECB
					des_tf4.setEditable(false);
					tmpIV = des_tf4.getText();
					des_tf4.setText("");
				}
			});
		}

	public static void main(String[] args)  throws Exception 
	{
		new Coder();
	}
}
