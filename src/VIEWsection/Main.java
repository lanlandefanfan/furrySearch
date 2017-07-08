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
		 * �����������
		 * */
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
		/**
		 * �����汳��
		 * */
		String path = "pic/background.jpg";  
        // ����ͼƬ  
        ImageIcon background = new ImageIcon(path);  
        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
        JLabel label = new JLabel(background);  
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        label.setBounds(0, 0, 800,500);  
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) frame.getContentPane();  
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
        /**
         * �������ǩ
         * */
        String label_text1 = "����ģ�����۵�";
        JLabel label_1 = new JLabel(label_text1);
        label_1.setFont(new  java.awt.Font("΢���ź�",   1,   35));
        label_1.setForeground(Color.white);
        label_1.setBounds(75, 46, 281, 48);
        frame.getContentPane().add(label_1);
        
        String label_text2 = "��е�������ϵͳ";
        JLabel lblNewLabel = new JLabel(label_text2);
        lblNewLabel.setFont(new  java.awt.Font("΢���ź�",   1,   35));
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setBounds(53, 104, 355, 57);
        frame.getContentPane().add(lblNewLabel);
 
        
        String label_text3 = "�𾴵��û����¼";
        JLabel lblNewLabel_1 = new JLabel(label_text3);
        lblNewLabel_1.setFont(new  java.awt.Font("��Բ",   1,   20));
        lblNewLabel_1.setForeground(Color.white);
        lblNewLabel_1.setBounds(110, 204, 189, 29);
        frame.getContentPane().add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(110, 258, 170, 29);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel label_2 = new JLabel("�û���");
        label_2.setFont(new  java.awt.Font("����",   1,   20));
        label_2.setForeground(Color.white);
        label_2.setBounds(28, 258, 72, 29);
        frame.getContentPane().add(label_2);
        
        JLabel label_3 = new JLabel("����");
        label_3.setFont(new  java.awt.Font("����",   1,   20));
        label_3.setForeground(Color.white);
        label_3.setBounds(28, 310, 72, 29);
        frame.getContentPane().add(label_3);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 310, 170, 29);
        frame.getContentPane().add(passwordField);
        
        logLabel= new JLabel("");
        logLabel.setFont(new  java.awt.Font("΢���ź�",   1,   15));
        logLabel.setForeground(Color.red);
        logLabel.setBounds(110, 338, 170, 29);
        frame.getContentPane().add(logLabel);
        
        JButton button = new JButton("��¼");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		/**
        		 * ��¼���߼�
        		 * 1.�Ȼ�ȡ�û������ж��Ƿ����
        		 * 2.��ȡ���룬�ж��Ƿ����û������
        		 * */
        		String inputUser = textField.getText();
        		String inputPass = passwordField.getText();
        		
        		DB_ACTION db =  new DB_ACTION();
        		try {
					if(db.searchUser(inputUser)!=null){
						//�û�������
						if(db.searchUser(inputUser).equals(inputPass)){
							//������ȷ
							frame.dispose();
			        		new SearchResult(inputUser);
						}else{
							//�������
							logLabel.setText("�������������");
							passwordField.setText("");
						}
					}else{
						//�û���������
						logLabel.setText("�û��������ڣ���ע��");
						//����ı���������
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
        button.setFont(new  java.awt.Font("����",   1,   20));
        button.setForeground(Color.white);
        button.setBounds(75, 368, 93, 29);
        frame.getContentPane().add(button);
        
        JButton button_1 =  new JButton("ע��");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		/**
        		 * ע����߼���
        		 * 1.֮ǰû�е��û���������ע��
        		 * 2.֮ǰ�Ѿ����ڵ��û��ã�������ע�ᣬ���·���ʾ�û����Ѵ���
        		 * ��Ҫ��������
        		 * 1.��ѯ��䣬��ѯ���û����Ƿ����
        		 * */
        		DB_ACTION db = new DB_ACTION();
        		//��ȡ�ı�������
        		String inputUser = textField.getText();
				String inputPass = passwordField.getText();
        		try {
					if(db.searchUser(inputUser)!=null){
						logLabel.setText("�û����Ѵ���");		
						//����ı���������
						textField.setText("");
						passwordField.setText("");
					}else{
						//�û���������
						if(passwordField.getPassword().length!=0){
							/**
							 * ע��ɹ���
							 * ��ȡ�û��������룬��ӵ����ݿ�
							 * ����ı����������û�������������
							 * */
							
							//��ȡ�û��������룬��ӵ����ݿ�		
							DB_Entity db_user = new DB_Entity();
							db_user.setUser(inputUser);
							db_user.setPassword(inputPass);
							db.registeUser(db_user);
							
							//����ı���������
							textField.setText("");
							passwordField.setText("");
							
							logLabel.setText("ע��ɹ������¼");
						}else{
							logLabel.setText("����������");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        button_1.setBackground(Color.blue);
        button_1.setFont(new  java.awt.Font("����",   1,   20));
        button_1.setForeground(Color.white);
        button_1.setBounds(216, 368, 93, 29);
        frame.getContentPane().add(button_1);
        
        JLabel lblSdu = new JLabel("SDU");
        lblSdu.setFont(new  java.awt.Font("΢���ź�",   1,   50));
        lblSdu.setForeground(Color.gray);
        lblSdu.setBounds(510, 258, 140, 48);
        frame.getContentPane().add(lblSdu);
        
        JLabel label_4 = new JLabel("���ӻ�е");
        label_4.setFont(new  java.awt.Font("΢���ź�",   1,   25));
        label_4.setForeground(Color.white);
        label_4.setBounds(510, 305, 179, 34);
        frame.getContentPane().add(label_4);
        
        
		
	}
}
