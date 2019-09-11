/**
 * 
 */
package lyl.test.sqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lyl.test.BookInfoVo;

/**
 * @author ASUS
 *
 */
public class SqlServerTest {
	
	public static void insert(String sql) {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://mysql-a.hfjy.com:3306/hls_test2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String user = "hls";
        String password = "hls@192.168.0.200#123456";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功..");
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            System.out.println("打印执行结果:" + rs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	try {
				if(rs != null)
				    rs.close();
				if(statement != null)
					statement.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
        }
	}
	
	public static void insertBookInfo(BookInfoVo vo) {
		try {
		    String driverName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://mysql-a.hfjy.com:3306/hls_test2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	        String user = "hls";
	        String password = "hls@192.168.0.200#123456";
	        Connection conn = null;
	        PreparedStatement statement = null;
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, user, password);
	        System.out.println("连接数据库成功..");
			String sql = "insert into book_info(subject_id,grade_code,name,author,publisher,recommend_index,recommend_reason) values(?,?,?,?,?,?,?)";
			statement = conn.prepareStatement(sql);//获得预置对象
			statement.setString(1, vo.getSubject_id());//设置占位符的值
			statement.setString(2, vo.getGrade_code());
			statement.setString(3, vo.getName());
			statement.setString(4, vo.getAuthor());
			statement.setString(5, vo.getPublisher());
			statement.setString(6, vo.getRecommend_index());
			statement.setString(7, vo.getRecommend_reason());
			
			int res=statement.executeUpdate();//执行sql语句
			if(res>0){
				System.out.println("数据录入成功");
			}
			statement.close();//关闭资源
			conn.close();//关闭资源					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://mysql-a.hfjy.com:3306/hls_test2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String user = "hls";
        String password = "hls@192.168.0.200#123456";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            // 1.url
            // 2.user
            // 3.password
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功..");
            String sql = "select * from book_info";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            int cnt = 0;
            while(rs != null && rs.next()){
            	System.out.println(rs.getString("id"));
            	cnt++;
            }
            System.out.println("ttlSize:"+cnt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	try {
				if(rs != null)
				    rs.close();
				if(statement != null)
					statement.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
        }
	}

}
