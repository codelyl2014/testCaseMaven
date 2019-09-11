package lyl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lyl.test.sqlServer.SqlServerTest;


public class ParseExcel {
	
	public static void main(String[] args) {
		try {
			List<BookInfoVo> list = readExcelFile();
			int count = 0;
			for(BookInfoVo vo:list) {
				SqlServerTest.insertBookInfo(vo);
				count ++;
			}
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:" + e);
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
	public static List<BookInfoVo> readExcelFile(){
		List<BookInfoVo> list = new ArrayList<BookInfoVo>();
		try {
			// 路径写入
			InputStream is = new FileInputStream("E:\\Desktop\\0425培训部微课视频调整.xlsx");
			// 解析文件
			Workbook book = new XSSFWorkbook(is);
			list = new ArrayList<BookInfoVo>();
			if (book != null) {
				// Read the Sheet
				Sheet xssfSheet = book.getSheetAt(0);	
				// Read the Row
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					Row xssfRow = xssfSheet.getRow(rowNum);						
					if (xssfRow != null) {
						BookInfoVo vo = new BookInfoVo();
						Cell subject_id = xssfRow.getCell(0);
						vo.setSubject_id(getValue(subject_id));	
						Cell grade_code = xssfRow.getCell(1);
						vo.setGrade_code(getValue(grade_code));	
						Cell name = xssfRow.getCell(2);
						vo.setName(getValue(name));												
						Cell author = xssfRow.getCell(3);
						vo.setAuthor(getValue(author));
						Cell publisher = xssfRow.getCell(4);
						vo.setPublisher(getValue(publisher));
						Cell recommend_reason = xssfRow.getCell(5);
						vo.setRecommend_reason(getValue(recommend_reason));
						vo.setRecommend_index("3");
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
	
    /**
	 * 获取文件扩展名
	 * 
	 * @param path
	 */
	@SuppressWarnings("unused")
	private String getExt(String path) {
		if (path == null || path.equals("") || !path.contains(".")) {
			return null;
		} else {
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
	}
}
