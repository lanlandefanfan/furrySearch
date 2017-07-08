package VIEWsection;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import DBsection.DB_ACTION;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Panel;

public class SearchResult {

	JFrame frame;
	private JTextField textField;
	private JLabel picLabel;
	private String pathpic;
	private String user;
	private JLabel userLabel;
	private JLabel nameLabel;
	private JLabel keyLabel;
	private JLabel textLabel;
	private JLabel redNamelabel;

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SearchResult window = new SearchResult("xsj");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SearchResult(String user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		String path = "pic/showback.jpg";
		// 背景图片
		ImageIcon background = new ImageIcon(path);
		// 把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		// 把标签的大小位置设置为图片刚好填充整个面板
		label.setBounds(0, 0, 800, 500);
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) frame.getContentPane();
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(93, 10, 595, 73);
		panel.setBackground(Color.blue);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		String comText[] = { "零件", "机床", "模具" };
		JComboBox comboBox = new JComboBox(comText);
		comboBox.setForeground(Color.GRAY);
		comboBox.setFont(new Font("幼圆", Font.BOLD, 18));
		comboBox.setBounds(10, 30, 69, 33);
		panel.add(comboBox);

		JLabel lblNewLabel = new JLabel("零件    机床     模具");
		lblNewLabel.setFont(new java.awt.Font("微软雅黑", 1, 20));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(26, 0, 292, 30);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(80, 30, 389, 33);
		panel.add(textField);
		textField.setColumns(10);

		picLabel = new JLabel();
		picLabel.setBorder(new LineBorder(Color.white));
		picLabel.setBounds(95, 140, 320, 280);
		frame.getContentPane().add(picLabel);

		JPanel textPanel = new JPanel();
		textPanel.setForeground(Color.GRAY);
		textPanel.setOpaque(false);
		textPanel.setBorder(new LineBorder(Color.white));
		textPanel.setBounds(486, 140, 256, 311);
		frame.getContentPane().add(textPanel);
		textPanel.setLayout(null);
		
		nameLabel= new JLabel("名称：");
		nameLabel.setBounds(10, 10, 236, 25);
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(new Font("幼圆", Font.BOLD, 20));
		textPanel.add(nameLabel);
		
		keyLabel = new JLabel("关键词：");
		keyLabel.setForeground(Color.white);
		keyLabel.setFont(new Font("幼圆", Font.BOLD, 15));
		keyLabel.setBounds(10, 34, 236, 25);
		textPanel.add(keyLabel);
		
		textLabel = new JLabel("");
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("幼圆", Font.BOLD, 15));
		textLabel.setBounds(10, 58, 236, 243);
		textPanel.add(textLabel);
		
		redNamelabel = new JLabel("三维零件图：");
		redNamelabel.setForeground(Color.red);
		redNamelabel.setFont(new Font("幼圆", Font.BOLD, 20));
		redNamelabel.setBounds(93, 430, 322, 31);
		frame.getContentPane().add(redNamelabel);
		
		JButton btnNewButton = new JButton("检索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = "";
				String search = textField.getText();
				DB_ACTION db = new DB_ACTION();

				try {
					pathpic = db.queryPic(search);
					if(pathpic!=null){
						ImageIcon picresult = new ImageIcon(pathpic);
						// 把图片设置为原来格式的一半，从而完整显示图片
						int cw = picresult.getIconWidth() / 2;
						int ch = picresult.getIconHeight() / 2;
						picresult.setImage(picresult.getImage().getScaledInstance(cw, ch, Image.SCALE_DEFAULT));
						picLabel.setIcon(picresult);
						
						text = db.queryText(search);
						JlabelSetText(textLabel, text);
						String name = db.queryName(search);
						nameLabel.setText("名称："+name);
						String allKey = db.queryAllKey(search);
						keyLabel.setText("关键词："+allKey);
						String English = db.queryEnglish(search);
						redNamelabel.setText("三维零件图："+name+"("+English+")");
					}else{
						clear();
						picLabel.setText("未找到与输入关键词相匹配的图片");
						picLabel.setFont(new Font("宋体", 1, 20));
						picLabel.setForeground(Color.red);
					}
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new java.awt.Font("幼圆", 1, 20));
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBounds(468, 30, 117, 33);
		panel.add(btnNewButton);
		
		userLabel= new JLabel("尊敬的用户："+user);
		userLabel.setFont(new java.awt.Font("幼圆", 1, 18));
		userLabel.setForeground(Color.CYAN);
		userLabel.setBounds(376, 0, 209, 26);
		panel.add(userLabel);

		
		JLabel lblNewLabel_1 = new JLabel("零件图展示：");
		lblNewLabel_1.setFont(new java.awt.Font("幼圆", 1, 15));
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setBounds(95, 112, 245, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("零件介绍");
		lblNewLabel_2.setFont(new java.awt.Font("幼圆", 1, 15));
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_2.setBounds(486, 116, 191, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("小提示：输入关键字以查询相应内容");
		lblNewLabel_3.setFont(new java.awt.Font("幼圆", 1, 15));
		lblNewLabel_3.setForeground(Color.orange);
		lblNewLabel_3.setBounds(207, 87, 293, 22);
		frame.getContentPane().add(lblNewLabel_3);
	
		
		imagePanel.setOpaque(false);
		// 把背景图片添加到分层窗格的最底层作为背景
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	// 实现label自动换行
	private void JlabelSetText(JLabel jLabel, String longString) throws InterruptedException {
		StringBuilder builder = new StringBuilder("<html>");
		char[] chars = longString.toCharArray();
		FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
		int start = 0;
		int len = 0;
		while (start + len < longString.length()) {
			while (true) {
				len++;
				if (start + len > longString.length())
					break;
				if (fontMetrics.charsWidth(chars, start, len) > jLabel.getWidth()) {
					break;
				}
			}
			builder.append(chars, start, len - 1).append("<br/>");
			start = start + len - 1;
			len = 0;
		}
		builder.append(chars, start, longString.length() - start);
		builder.append("</html>");
		jLabel.setText(builder.toString());
	}
	
	private void clear(){
		picLabel.setIcon(null);
		nameLabel.setText("");
		keyLabel.setText("");
		textLabel.setText("");
		redNamelabel.setText("三维零件图：");
	}
}
