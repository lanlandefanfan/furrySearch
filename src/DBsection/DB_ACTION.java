package DBsection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBsection.*;

;

public class DB_ACTION {

	public String queryPic(String key) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();// 连接数据库

		String sql = "" + " select pic from mysql.PicAndText " + " where allkey like '%' ? '%' ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, key);

		ResultSet rs = ptmt.executeQuery();

		// return rs.getString("pic");
		if (rs.next()) {
			String pic = rs.getString("pic");
			return pic;
		}
		return null;
	}

	public String queryText(String key) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();// 连接数据库

		String sql = "" + " select text from mysql.PicAndText " + " where allkey like '%' ? '%' ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, key);

		ResultSet rs = ptmt.executeQuery();

		if (rs.next()) {
			String text = rs.getString("text");
			return text;
		}
		return null;
	}

	public String queryName(String key) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();// 连接数据库

		String sql = "" + " select name from mysql.PicAndText " + " where allkey like '%' ? '%' ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, key);
		
		ResultSet rs = ptmt.executeQuery();
		
		if (rs.next()) {
			String name = rs.getString("name");
			return name;
		}
		return null;
	}
	
	public String queryAllKey(String key) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();// 连接数据库

		String sql = "" + " select allkey from mysql.PicAndText " + " where allkey like '%' ? '%' ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, key);
		
		ResultSet rs = ptmt.executeQuery();
		
		if (rs.next()) {
			String allkey = rs.getString("allkey");
			return allkey;
		}
		return null;
	}
	
	public String queryEnglish(String key) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();// 连接数据库

		String sql = "" + " select English from mysql.PicAndText " + " where allkey like '%' ? '%' ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, key);
		
		ResultSet rs = ptmt.executeQuery();
		
		if (rs.next()) {
			String english = rs.getString("English");
			return english;
		}
		return null;
	}

	public void registeUser(DB_Entity db_user) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();
		String sql = "" + "insert into mysql.PicSearchUser" + "(user,password)" +
		// "create_user,create_date,update_user,update_date,isdel)" +
				"values(" + "?,?);";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, db_user.getUser());
		ptmt.setString(2, db_user.getPassword());

		ptmt.execute();
	}

	public String searchUser(String user) throws SQLException {

		Connection conn = DB_Link_Driver.getConnection();
		String sql = "" + " select password from mysql.PicSearchUser " + " where user=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, user);

		ResultSet rs = ptmt.executeQuery();

		if (rs.next()) {
			String inputPassword = rs.getString("password");
			return inputPassword;
		} else {
			return null;
		}
	}

	

	// public static void main(String[] args) throws Exception {
	// DB_ACTION action = new DB_ACTION();
	// System.out.println(action.queryPic("带轮"));
	// System.out.println(action.queryText("齿轮"));
	//
	// // DB_Entity db = new DB_Entity();
	// // db.setUser("xsj123");
	// // db.setPassword("123");
	// // action.registeUser(db);
	// if(action.searchUser("ykq")!=null){
	// System.out.println("xsj存在,密码为："+action.searchUser("ykq"));
	// }else{
	// System.out.println("xsj不存在");
	// }
	//
	// }

}
