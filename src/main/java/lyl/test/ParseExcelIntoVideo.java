package lyl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.converter.core.utils.StringUtils;

import com.google.gson.Gson;

/**
 * @author loong
 *
 */
public class ParseExcelIntoVideo {

	public static void main(String[] args) {
		try {
			List<VideoInfoVo> list = readVideoExcelFile();
			dataAssemble(list);
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
//			String url = "jdbc:mysql://192.168.0.166:3306/hfjydb?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
//	        String user = "qa_study";
//	        String password = "SVZRbA6GQT8HgA2nebJt";
			String url = "jdbc:mysql://mysql-a.hfjy.com:3306/hls_test2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	        String user = "hls";
	        String password = "hls@192.168.0.200#123456";
	        Connection conn = null;
	        PreparedStatement statement = null;
	        ResultSet rs = null;
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, user, password);
	        System.out.println("连接数据库成功..");
	        String study_basics_video_insert = "insert into study_basics_video(title,sub_title,description,description_url,video_url,thumbnail_url,video_time,channel_type,channel_id)values(?,?,?,?,?,?,?,?,?)";
	        statement = conn.prepareStatement(study_basics_video_insert);//获得预置对象
			statement.setString(1, StringUtils.isEmpty(vo.getTitle()) ? "" : vo.getTitle());//设置占位符的值
			statement.setString(2, StringUtils.isEmpty(vo.getSub_title()) ? "" : vo.getSub_title());
			statement.setString(3, StringUtils.isEmpty(vo.getDescription()) ? "" : vo.getDescription());
			statement.setString(4, StringUtils.isEmpty(vo.getDescription_url()) ? "" :vo.getDescription_url());
			statement.setString(5, StringUtils.isEmpty(vo.getVideo_url()) ? "" : vo.getVideo_url());
			statement.setString(6, StringUtils.isEmpty(vo.getThumbnail_url()) ? "" : vo.getThumbnail_url());
			statement.setString(7, StringUtils.isEmpty(vo.getVideo_time()) ? "0" :vo.getVideo_time() );
			statement.setString(8, vo.getChannel_type());
			statement.setString(9, StringUtils.isEmpty(vo.getChannel_id()) ? "" : vo.getChannel_id());			
			int res=statement.executeUpdate();//执行sql语句
			if(res>0){
				System.out.println("study_basics_video数据录入成功");
			}
			statement = conn.prepareStatement("select id from study_basics_video where channel_id = ? ORDER BY create_time asc"  );
			statement.setString(1, vo.getChannel_id());
            rs = statement.executeQuery();
            int study_basics_video_id = 0;
            while(rs != null && rs.next()){
//            	System.out.println(rs.getString("id"));
            	study_basics_video_id = rs.getInt("id");
            }
	        //	String study_video_archit_info_insert = "insert into study_video_archit_info(menu_code,menu_name,menu_father_code,menu_level,is_del) values(?,?,?,?,?)";
	        String study_video_teacher_attribute = "insert into study_video_teacher_attribute(study_basics_video_id,teacher_name,menu_first,menu_second,menu_third,menu_fourth,menu_fourth_add,menu_fifth,subject_id,grade_level_start,grade_level_end,is_del,basic_watch_count)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        statement = conn.prepareStatement(study_video_teacher_attribute);//获得预置对象
	        statement.setInt(1, study_basics_video_id);//设置占位符的值
	        statement.setString(2, StringUtils.isEmpty(vo.getTeacher_name()) ? "" : vo.getTeacher_name());
			statement.setString(3, vo.getMenu_first());
			statement.setString(4, vo.getMenu_second());
			statement.setString(5, vo.getMenu_third());
			statement.setString(6, vo.getMenu_fourth());
			statement.setString(7, vo.getMenu_fourth_second());
			statement.setString(8, vo.getMenu_fifth());
			statement.setString(9, StringUtils.isEmpty(vo.getSubject_id()) ? "0" : vo.getSubject_id());			
			statement.setString(10, StringUtils.isEmpty(vo.getGrade_level_start()) ? "0" : vo.getGrade_level_start());
			statement.setString(11, StringUtils.isEmpty(vo.getGrade_level_end()) ? "0" : vo.getGrade_level_end());
			statement.setString(12, vo.getMenu_first().equals("1") ? "0" : "1");
			statement.setString(13, vo.getBasic_watch_count());
			int num = statement.executeUpdate();//执行sql语句
			if(num>0){
				System.out.println("study_video_teacher_attribute 数据录入成功");
			}
			if(StringUtils.isNotEmpty(vo.getRecommend()) || StringUtils.isNotEmpty(vo.getSort())) {
				String study_video_teacher_extend = "insert into study_video_teacher_extend(study_basics_video_id,sort,recommend)values(?,?,?)";
				statement = conn.prepareStatement(study_video_teacher_extend);//获得预置对象
		        statement.setInt(1, study_basics_video_id);//设置占位符的值
		        statement.setString(2, StringUtils.isEmpty(vo.getSort()) ? "0" : vo.getSort());
				statement.setString(3, StringUtils.isEmpty(vo.getRecommend()) ? "0" : vo.getRecommend());
				int extendNum = statement.executeUpdate();//执行sql语句
				if(extendNum>0){
					System.out.println("study_video_teacher_extend 数据录入成功");
				}
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
	public static List<VideoInfoVo> readVideoExcelFile(){
		List<VideoInfoVo> list = new ArrayList<VideoInfoVo>();
		try {
			// 路径写入
			InputStream is = new FileInputStream("E:\\Desktop\\微课视频导入0703.xlsx");
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
						Cell menu_first = xssfRow.getCell(0);
						if(menu_first != null) {
							vo.setMenu_first(getValue(xssfRow.getCell(0)));	
						}
															
						Cell menu_second = xssfRow.getCell(2);
						if(menu_second != null) {
							vo.setMenu_second(getValue(menu_second));
						}
						
						Cell menu_third = xssfRow.getCell(3);
						if(menu_third != null) {
							vo.setMenu_third(getValue(menu_third));
						}
					
						Cell menu_fourth = xssfRow.getCell(4);
						if(menu_fourth != null) {
							vo.setMenu_fourth(getValue(menu_fourth));
						}
						
						Cell menu_fourth_second = xssfRow.getCell(5);
						if(menu_fourth_second != null) {
							vo.setMenu_fourth_second(getValue(menu_fourth_second));
						}

//						Cell menu_fifth = xssfRow.getCell(5);
//						if(menu_fifth != null) {
//							vo.setMenu_fifth(getValue(menu_fifth));
//						}
						
						Cell title = xssfRow.getCell(7);
						if(title != null) {
							vo.setTitle(getValue(title));
						}
					
						Cell sub_title = xssfRow.getCell(8);
						if(sub_title != null) {
							vo.setSub_title(getValue(sub_title));
						}
						
						Cell description = xssfRow.getCell(9);
						if(description != null) {
							vo.setDescription(getValue(description));
						}
						
						Cell thumbnail_url = xssfRow.getCell(10);
						if(thumbnail_url != null) {
							vo.setThumbnail_url(getValue(thumbnail_url));
						}
					
						Cell video_url = xssfRow.getCell(11);
						if(video_url != null) {
							vo.setVideo_url(getValue(video_url));
						}
						
						Cell video_time = xssfRow.getCell(12);
						if(video_time != null) {
							vo.setVideo_time(getValue(video_time));
						}
					
//						Cell channel_type = xssfRow.getCell(13);
//						if(channel_type != null) {
//							vo.setChannel_type(getValue(channel_type));
//						}
						
						Cell channel_id = xssfRow.getCell(13);
						if(channel_id != null) {
							vo.setChannel_id(getValue(channel_id));
						}
					
//						Cell basic_watch_count = xssfRow.getCell(15);
//						if(basic_watch_count != null) {
//							vo.setBasic_watch_count(getValue(basic_watch_count));
//						}
					
//						Cell teacher_id = xssfRow.getCell(16);
//						if(teacher_id != null) {
//							vo.setTeacher_id(getValue(teacher_id));
//						}
					
//						Cell subject_id = xssfRow.getCell(13);
//						if(subject_id != null) {
//							vo.setSubject_id(getValue(subject_id));
//						}
//						
//						Cell grade_level = xssfRow.getCell(14);
//						if(grade_level != null) {
//							vo.setGrade_level(getValue(grade_level));
//						}
//						
//						Cell basic_watch_count = xssfRow.getCell(15);
//						if(basic_watch_count != null) {
//							vo.setBasic_watch_count(getValue(basic_watch_count));
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
	
	private static void dataAssemble(List<VideoInfoVo> videoInfoVos){
		for(VideoInfoVo vo:videoInfoVos) {
			if(StringUtils.isNotEmpty(vo.getGrade_level())) {
				if("小学".equals(vo.getGrade_level())) {
					vo.setGrade_level_start("1");
					vo.setGrade_level_end("6");
				}
				if("初中".equals(vo.getGrade_level())) {
					vo.setGrade_level_start("7");
					vo.setGrade_level_end("9");
				}
				if("高中".equals(vo.getGrade_level())) {
					vo.setGrade_level_start("10");
					vo.setGrade_level_end("12");
				}
			}
			vo.setChannel_type("1");
			vo.setTeacher_name("海风教育");
			int ran = (int)(150*Math.random() + 50);
			vo.setBasic_watch_count(String.valueOf(ran));
	        if(StringUtils.isNotEmpty(vo.getMenu_fifth())) {
	        	vo.setMenu_level("5");
		        // 一级目录1 二级目录2 三级目录1 四级目录1 2 3: （1.综合素质 2.教育知识与能力 3.学科教学知识与能力 4.面试）
		        if("综合素质1".equals(vo.getMenu_fifth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
		        	vo.setMenu_fourth("1");
		        	vo.setMenu_fifth("1");	
		        }
		        if("教育知识与能力1".equals(vo.getMenu_fifth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
		        	vo.setMenu_fourth("1");
		        	vo.setMenu_fifth("2");	
		        }
		        if("学科教学知识与能力1".equals(vo.getMenu_fifth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
		        	vo.setMenu_fourth("1");
		        	vo.setMenu_fifth("3");	
		        }
		        if("面试1".equals(vo.getMenu_fifth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
		        	vo.setMenu_fourth("1");
		        	vo.setMenu_fifth("4");	
		        }	        
		        continue;
	        }	        
	        vo.setMenu_fifth("0");
	        if(StringUtils.isNotEmpty(vo.getMenu_fourth_second()) && StringUtils.isNotEmpty(vo.getMenu_fourth())) {
	        	vo.setMenu_level("4");	
	        	vo.setMenu_first("1");
	        	vo.setMenu_second("2");
	        	vo.setMenu_third("1");
	        	if("综合素质".equals(vo.getMenu_fourth_second())) {
	        		vo.setMenu_fourth_second("1");
	        	}
	        	if("教育知识与能力".equals(vo.getMenu_fourth_second())) {
	        		vo.setMenu_fourth_second("2");
	        	}
	        	if("学科教学知识与能力".equals(vo.getMenu_fourth_second())) {
	        		vo.setMenu_fourth_second("3");
	        	}
	        	if("面试".equals(vo.getMenu_fourth_second())) {
	        		vo.setMenu_fourth_second("4");
	        	}
	        	if("小学".equals(vo.getMenu_fourth())) {
	        		vo.setMenu_fourth("1");
	        	}
	        	if("初中".equals(vo.getMenu_fourth())) {
	        		vo.setMenu_fourth("2");
	        	}
	        	if("高中".equals(vo.getMenu_fourth())) {
	        		vo.setMenu_fourth("3");
	        	}
	        	continue;
	        }
	        vo.setMenu_fourth_second("0");
	        if(StringUtils.isNotEmpty(vo.getMenu_fourth())) {
	        	vo.setMenu_level("4");	        
	        	// 一级目录1 二级目录1 三级目录4：（1诊断报告 2.课后反馈 3.作业布置与批改 4.阶段反馈 5.师生沟通） 
	        	if("诊断报告".equals(vo.getMenu_fourth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
		        	vo.setMenu_fourth("1");
	        	}
	        	if("课后反馈".equals(vo.getMenu_fourth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
		        	vo.setMenu_fourth("2");
	        	}
	        	if("作业布置与批改".equals(vo.getMenu_fourth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
		        	vo.setMenu_fourth("3");
	        	}
	        	if("阶段反馈".equals(vo.getMenu_fourth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
		        	vo.setMenu_fourth("4");
	        	}
	        	if("师生沟通".equals(vo.getMenu_fourth())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
		        	vo.setMenu_fourth("5");
	        	}
	        	//  一级目录1 二级目录2 三级目录1：（1.小学 2.初中 3.高中）
//		        if("小学".equals(vo.getMenu_fifth())) {
//		        	vo.setMenu_first("1");
//		        	vo.setMenu_second("2");
//		        	vo.setMenu_third("1");
//		        	vo.setMenu_fourth("1");	        	
//		        }
//		        if("初中".equals(vo.getMenu_fifth())) {
//		        	vo.setMenu_first("1");
//		        	vo.setMenu_second("2");
//		        	vo.setMenu_third("1");
//		        	vo.setMenu_fourth("2");
//		        }
//		        if("高中".equals(vo.getMenu_fifth())) {
//		        	vo.setMenu_first("1");
//		        	vo.setMenu_second("2");
//		        	vo.setMenu_third("1");
//		        	vo.setMenu_fourth("3");
//		        }
	        	continue;
	        }
	        vo.setMenu_fourth("0");
	        if(StringUtils.isNotEmpty(vo.getMenu_third())) {
	        	vo.setMenu_level("3");	 
	        	if("优秀正式课参考".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("1");
	        	}
	        	if("优秀试听课参考".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("2");
	        	}
	        	if("优秀教学示范过程".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("3");
	        	}
	        	if("优秀教学服务展示".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("4");
	        	}
	        	// 一级目录1 二级目录2：（1 教师资格证 2 名师分享讲座 3 新教师通识培训 4 互联网教师必备技能 5 其他）
	        	if("教师资格证".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
	        	}
	        	if("名师分享讲座".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("2");
	        	}
	        	if("新教师通识培训".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("3");
	        	}
	        	if("互联网教师必备技能".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("4");
	        	}
	        	if("亲子课堂".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("5");
	        	}
	        	// 一级目录2 二级目录1：（1 系统操作 2 制度讲解 ）
	        	if("系统操作".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("1");
	        	}
	        	if("制度讲解".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("1");
		        	vo.setMenu_third("2");
	        	}
	        	//  一级目录2 二级目录2：（1 讲义制作 2 课前沟通 3 授课过程 4 课尾沟通 5 课后跟进）
	        	if("讲义制作".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("1");
	        	}
	        	if("课前沟通".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("2");
	        	}
	        	if("授课过程".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("3");
	        	}
	        	if("课尾沟通".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("4");
	        	}
	        	if("课后跟进".equals(vo.getMenu_third())) {
		        	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
		        	vo.setMenu_third("5");
	        	}
	        	continue;
	        }
	        vo.setMenu_third("0");
	        if(StringUtils.isNotEmpty(vo.getMenu_second())) {
	        	vo.setMenu_level("2");
	        	// 一级目录1：（1.专业类教学示范 2.通用类教师培训） 一级目录2：（1.通识培训 2.学科专业培训）
	        	if("专业类教学示范".equals(vo.getMenu_second())) {
	            	vo.setMenu_first("1");
		        	vo.setMenu_second("1");
	 	        }
	 	        if("通用类教师培训".equals(vo.getMenu_second())) {
	            	vo.setMenu_first("1");
		        	vo.setMenu_second("2");
	 	        }
	 	        if("通识培训".equals(vo.getMenu_second())) {
	            	vo.setMenu_first("2");
		        	vo.setMenu_second("1");
	 	        }
	 	        if("学科专业培训".equals(vo.getMenu_second())) {
	            	vo.setMenu_first("2");
		        	vo.setMenu_second("2");
	 	        }	
	 	        continue;
	        }
	        vo.setMenu_second("0");
	        if(StringUtils.isNotEmpty(vo.getMenu_first())) {
	        	vo.setMenu_level("1");
	        }
		}  
	}
	
}
