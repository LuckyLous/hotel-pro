package us.luckylu.dev.client.util;

import java.util.regex.Pattern;

/**
 * @author lu
 * @create 2019-03-28 11:13
 */
public class PatternUtil {

    /**
     * 完全匹配
     */
    Pattern patternComplete = Pattern.compile("^测试$", Pattern.CASE_INSENSITIVE);
    /**
     * 右匹配
     */
    Pattern patternRight = Pattern.compile("^.*测试$", Pattern.CASE_INSENSITIVE);
    /**
     * 左匹配
     */
    Pattern patternLeft = Pattern.compile("^测试.*$", Pattern.CASE_INSENSITIVE);

    /**
     * 模糊匹配 ^$开始、结束标志，.*任意字符
     * @param value
     * @return
     */
    public static Pattern blurry(String value){
        return Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
    }
}
