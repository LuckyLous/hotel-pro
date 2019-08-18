package us.luckylu.dev.client.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DbUtil {
	
	private DbUtil(){}
	
	private static String driver ;
	private static String url ;
	private static String user ;
	private static String password ;
	
	static{
		try {
			getProperties();	
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private static void getProperties() throws IOException{
		FileReader fr = new FileReader("src\\db.properties");
		Properties pro = new Properties();
		pro.load(fr);
		fr.close();
		
		driver = pro.getProperty("driver");
		url = pro.getProperty("url");
		user = pro.getProperty("user");
		password = pro.getProperty("password");
	}
	
	
	/**
	 * ��ȡ��ݿ����Ӷ���
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection()throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
		
	
	/**
	 * ִ����ݿ���� ������Ӱ���������true��false
	 * @throws SQLException 
	 */
	public static boolean executeUpdate(PreparedStatement ps) throws SQLException{
		
		return ps.executeUpdate() == 1 ? true : false;
	}
	
	/**
	 * �ر���Դ
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn,Statement ps, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �л������ύģʽ������������
	 * @param conn
	 * @param autoCommit
	 */
	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		try {
			if (conn != null) {
				//����봫�����Ĳ���͸�
				if (conn.getAutoCommit() != autoCommit) {
					conn.setAutoCommit(autoCommit);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ύ����
	 * @param conn
	 * @throws SQLException
	 */
	public static void commit(Connection conn) throws SQLException{
		if(conn != null){
			conn.commit();
		}

	}
	
	/**
	 * ����ع�
	 * @param conn
	 * @throws SQLException
	 */
	public static void rollback(Connection conn){
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	 
}
