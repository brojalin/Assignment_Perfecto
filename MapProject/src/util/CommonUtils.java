package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	/**
	 * 
	 * @return timestampString in the format yyyyMMddhhmmssSSS
	 */
	public static String getTimeStamp()
	{
		return new SimpleDateFormat ("yyyyMMddhhmmssSSS").format(new Date( ));
	}

}
