package lyl.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
/**
 * @author loong
 *
 */
public class ReadTxtFile {
	 public static String readTxt(String filePath) {
	        List<String> list = new ArrayList<>();
	        try {
	            File file = new File(filePath);
	            if (file.isFile() && file.exists()) {
	                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
	                BufferedReader br = new BufferedReader(isr);
	                String lineTxt = null;
	                while ((lineTxt = br.readLine()) != null) {
	                    System.out.println(lineTxt);
	                    list.add(lineTxt);
	                }
	                br.close();
	            } else {
	                System.out.println("文件不存在!");
	            }
	        } catch (Exception e) {
	            System.out.println("文件读取错误!");
	        }
	        return new Gson().toJson(list);
	    }

	    public static void main(String[] args) {
	        String filePath = "hfjydb-to-ods/src/hfjydb_table_detail.txt";
	        readTxt(filePath);
	    }
}
