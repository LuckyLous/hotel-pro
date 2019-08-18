package us.luckylu.dev.client.util;

import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 英文：包括数字、空格、(.)、(')、(,)、(;)、(、)、(_)、(-)、(？)、(！)
	 */
	private static final String ALPHA_REGEX = "^[\\s0-9a-zA-Z,:;?!。’+=<>'\"_/.-]*$";

	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		if(str != null && !"".equals(str.trim())){
			return true;
		}
		return false;
	}

	public static boolean isAlpha(String str) {
		Pattern pattern = Pattern.compile(ALPHA_REGEX);
		return pattern.matcher(str).matches();
	}
	
	
}
