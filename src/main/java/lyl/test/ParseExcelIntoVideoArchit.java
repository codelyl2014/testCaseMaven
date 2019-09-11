package lyl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.gson.Gson;

/**
 * @author loong
 *
 */
public class ParseExcelIntoVideoArchit {
	
	public static void main(String[] args) {
		try {
			List<VideoInfoVo> list = readVideoArchitExcelFile();
			int count = 0;
			for(VideoInfoVo vo:list) {	
				operateVideo(vo);
				System.out.println(new Gson().toJson(vo));
				count ++;
			}
			System.out.println("total:" + count);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:" + e);
		}
	}
	
	public static void operateVideo(VideoInfoVo vo) {
		try {
		    String driverName = "com.mysql.jdbc.Driver";
//			String url = "jdbc:mysql://mysql-a.hfjy.com:3306/hls_test2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
//	        String user = "hls";
//	        String password = "hls@192.168.0.200#123456";
		    String url = "jdbc:mysql://192.168.0.166:3306/hfjydb?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	        String user = "qa_study";
	        String password = "SVZRbA6GQT8HgA2nebJt";
	        Connection conn = null;
	        PreparedStatement statement = null;
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, user, password);
	        System.out.println("连接数据库成功..");
	        String study_basics_video_insert = "insert into study_video_archit_info(menu_code,menu_name,menu_father_code,menu_level,is_del)values(?,?,?,?,?)";
	        statement = conn.prepareStatement(study_basics_video_insert);//获得预置对象
			statement.setString(1, vo.getMenu_code());//设置占位符的值
			statement.setString(2, vo.getMenu_first());
			statement.setString(3, vo.getMenu_father_code());
			statement.setString(4, vo.getMenu_level());
			statement.setString(5, vo.getType().equals("1.0") ? "0" : "1");		
			int res=statement.executeUpdate();//执行sql语句
			if(res>0){
				System.out.println("study_basics_video数据录入成功");
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
	 * 读取后缀为xlsx的excel文件的数据
	 * 
	 * @param path
	 * @throws Exception
	 * @throws IOException
	 * @throws BizException
	 */
	@SuppressWarnings("resource")
	public static List<VideoInfoVo> readVideoArchitExcelFile(){
		List<VideoInfoVo> list = new ArrayList<VideoInfoVo>();
		try {
			// 路径写入
			InputStream is = new FileInputStream("E:\\Desktop\\0425培训部微课视频调整.xlsx");
			// 解析文件
			Workbook book = new XSSFWorkbook(is);
			if (book != null) {
				// Read the Sheet
				Sheet xssfSheet = book.getSheetAt(0);	
				// Read the Row
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					Row xssfRow = xssfSheet.getRow(rowNum);						
					if (xssfRow != null) {
						VideoInfoVo vo = new VideoInfoVo();
						vo.setType(getValue(xssfRow.getCell(0)));							
						vo.setMenu_code(getValue(xssfRow.getCell(1)));							
						vo.setMenu_father_code(getValue(xssfRow.getCell(2)));	
						vo.setMenu_level(getValue(xssfRow.getCell(3)));
						Cell menu_first = xssfRow.getCell(4);
						if(menu_first != null) {
							vo.setMenu_first(getValue(menu_first));
						}
//						Cell menu_second = xssfRow.getCell(5);
//						if(menu_second != null) {
//							vo.setMenu_second(getValue(menu_second));
//						}						
//						Cell menu_third = xssfRow.getCell(6);
//						if(menu_third != null) {
//							vo.setMenu_third(getValue(menu_third));
//						}					
//						Cell menu_fourth = xssfRow.getCell(7);
//						if(menu_fourth != null) {
//							vo.setMenu_fourth(getValue(menu_fourth));
//						}
//						Cell menu_fifth = xssfRow.getCell(8);
//						if(menu_fifth != null) {
//							vo.setMenu_fifth(getValue(menu_fifth));
//						}						
						list.add(vo);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException:" + e);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException:" + e);
		}
		return list;
	}
    
	/**
	 * 判断后缀为xlsx的excel文件的数据类型
	 * 
	 * @param xssfRow
	 */
	@SuppressWarnings("static-access")
	private static String getValue(Cell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}
	
}
