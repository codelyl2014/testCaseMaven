/**
 * 
 */
package lyl.test.sqlServer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author ASUS
 *
 */
public class TimestampTest {

	public static void main(String[] args) {
		Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(updateTimestamp));
	}
}
