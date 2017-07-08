package VIEWsection;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import DBsection.DB_ACTION;
import DBsection.DB_Entity;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel logLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/**
		 * 界面参数设置
		 * */
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
		/**
		 * 主界面背景
		 * */
		String path = "pic/background.jpg";  
        // 背景图片  
        ImageIcon background = new ImageIcon(path);  
        // 把背景图片显示在一个标签里面  
        JLabel label = new JLabel(background);  
        // 把标签的大小位置设置为图片刚好填充整个面板  
        label.setBounds(0, 0, 800,500);  
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) frame.getContentPane();  
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
        /**
         * 主标题标签
         * */
        String label_text1 = "基于模糊理论的";
        JLabel label_1 = new JLabel(label_text1);
        label_1.setFont(new  java.awt.Font("微软雅黑",   1,   35));
        label_1.setForeground(Color.white);
        label_1.setBounds(75, 46, 281, 48);
        frame.getContentPane().add(label_1);
        
        String label_text2 = "机械零件检索系统";
        JLabel lblNewLabel = new JLabel(label_text2);
        lblNewLabel.setFont(new  java.awt.Font("微软雅黑",   1,   35));
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setBounds(53, 104, 355, 57);
        frame.getContentPane().add(lblNewLabel);
 
        
        String label_text3 = "尊敬的用户请登录";
        JLabel lblNewLabel_1 = new JLabel(label_text3);
        lblNewLabel_1.setFont(new  java.awt.Font("幼圆",   1,   20));
        lblNewLabel_1.setForeground(Color.white);
        lblNewLabel_1.setBounds(110, 204, 189, 29);
        frame.getContentPane().add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(110, 258, 170, 29);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel label_2 = new JLabel("用户名");
        label_2.setFont(new  java.awt.Font("宋体",   1,   20));
        label_2.setForeground(Color.white);
        label_2.setBounds(28, 258, 72, 29);
        frame.getContentPane().add(label_2);
        
        JLabel label_3 = new JLabel("密码");
        label_3.setFont(new  java.awt.Font("宋体",   1,   20));
        label_3.setForeground(Color.white);
        label_3.setBounds(28, 310, 72, 29);
        frame.getContentPane().add(label_3);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 310, 170, 29);
        frame.getContentPane().add(passwordField);
        
        logLabel= new JLabel("");
        logLabel.setFont(new  java.awt.Font("微软雅黑",   1,   15));
        logLabel.setForeground(Color.red);
        logLabel.setBounds(110, 338, 170, 29);
        frame.getContentPane().add(logLabel);
        
        JButton button = new JButton("登录");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		/**
        		 * 登录的逻辑
        		 * 1.先获取用户名，判断是否存在
        		 * 2.获取密码，判断是否与用户名相符
        		 * */
        		String inputUser = textField.getText();
        		String inputPass = passwordField.getText();
        		
        		DB_ACTION db =  new DB_ACTION();
        		try {
					if(db.searchUser(inputUser)!=null){
						//用户名存在
						if(db.searchUser(inputUser).equals(inputPass)){
							//密码正确
							frame.dispose();
			        		new SearchResult(inputUser);
						}else{
							//密码错误
							logLabel.setText("密码错误，请重试");
							passwordField.setText("");
						}
					}else{
						//用户名不存在
						logLabel.setText("用户名不存在，请注册");
						//清空文本框和密码框
						textField.setText("");
						passwordField.setText("");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        });
        button.setBackground(Color.orange);
        button.setFont(new  java.awt.Font("黑体",   1,   20));
        button.setForeground(Color.white);
        button.setBounds(75, 368, 93, 29);
        frame.getContentPane().add(button);
        
        JButton button_1 =  new JButton("注册");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		/**
        		 * 注册的逻辑：
        		 * 1.之前没有的用户名，可以注册
        		 * 2.之前已经存在的用户用，不可以注册，在下方提示用户名已存在
        		 * 需要的条件：
        		 * 1.查询语句，查询该用户名是否存在
        		 * */
        		DB_ACTION db = new DB_ACTION();
        		//获取文本框内容
        		String inputUser = textField.getText();
				String inputPass = passwordField.getText();
        		try {
					if(db.searchUser(inputUser)!=null){
						logLabel.setText("用户名已存在");		
						//清空文本框和密码框
						textField.setText("");
						passwordField.setText("");
					}else{
						//用户名不存在
						if(passwordField.getPassword().length!=0){
							/**
							 * 注册成功，
							 * 获取用户名和密码，添加到数据库
							 * 清空文本框和密码框，用户可以重新输入
							 * */
							
							//获取用户名和密码，添加到数据库		
							DB_Entity db_user = new DB_Entity();
							db_user.setUser(inputUser);
							db_user.setPassword(inputPass);
							db.registeUser(db_user);
							
							//清空文本框和密码框
							textField.setText("");
							passwordField.setText("");
							
							logLabel.setText("注册成功，请登录");
						}else{
							logLabel.setText("请输入密码");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        button_1.setBackground(Color.blue);
        button_1.setFont(new  java.awt.Font("黑体",   1,   20));
        button_1.setForeground(Color.white);
        button_1.setBounds(216, 368, 93, 29);
        frame.getContentPane().add(button_1);
        
        JLabel lblSdu = new JLabel("SDU");
        lblSdu.setFont(new  java.awt.Font("微软雅黑",   1,   50));
        lblSdu.setForeground(Color.gray);
        lblSdu.setBounds(510, 258, 140, 48);
        frame.getContentPane().add(lblSdu);
        
        JLabel label_4 = new JLabel("电子机械");
        label_4.setFont(new  java.awt.Font("微软雅黑",   1,   25));
        label_4.setForeground(Color.white);
        label_4.setBounds(510, 305, 179, 34);
        frame.getContentPane().add(label_4);
        
        
		
	}
}
