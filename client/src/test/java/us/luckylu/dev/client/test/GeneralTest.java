package us.luckylu.dev.client.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import us.luckylu.dev.common.util.DateTimeUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lu
 * @date 2019-04-11 17:21
 */
public class GeneralTest {

    @Test
    public void testStamp(){
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(DateTimeUtil.toStamp(LocalDateTime.now()));

        List<Integer> statusProcess = Arrays.asList(0, 1, 2, 3, 4, 5);
        String status = JSON.toJSONString(statusProcess);
        System.out.println(status);

        List<Integer> statusList = JSON.parseArray(status, Integer.class);
        System.out.println(statusList);
    }

    @Test
    public void testPattern(){
    }

    @Test
    public void testEncode(){
        try {
            System.out.println(URLDecoder.decode("ORDER_PAY", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPwd() {
        String salt = RandomStringUtils.randomAlphabetic(16);
        salt = "nuxHQVhgSqwVqaAy";
        String pwd = DigestUtils.sha512Hex("123456@L" + salt);
        System.out.println(salt);
        System.out.println(pwd);
    }

    @Test
    public void testBoolean() {
        Object object = Boolean.FALSE;
        Boolean bo = object instanceof Boolean && (Boolean) object;
        System.out.println(Objects.equals(bo, false));
    }

    @Test
    public void testFile() throws IOException {
        File localFile = new File("C:\\Users\\Administrator\\Downloads\\shanghai.png");
        if (localFile.exists()) {
            String key = localFile.getName();
            System.out.println(key.substring(0, key.lastIndexOf(".")));
            System.out.println(localFile.getPath());
            System.out.println(localFile.getAbsolutePath());
            System.out.println(localFile.getCanonicalPath());
        }
    }

    @Test
    public void testDecimal() {
        BigDecimal result = new BigDecimal(100).multiply(new BigDecimal(100))
                .divide(new BigDecimal(14600), 0, BigDecimal.ROUND_UP);
        System.out.println(result.round(MathContext.DECIMAL32));
    }

    @Test
    public void mapToList(){
        Map<String, Long> map = Maps.newHashMap();
        map.put("10", 10L);
        map.put("7", 7L);
        map.put("1", 1L);
        map.put("3", 3L);
        map.put("5", 5L);

        List<Long> sortIds = map.entrySet().stream()
                // 根据value值倒序排序
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // 获取key，转换为Long
                .map(entry -> Long.parseLong(entry.getKey()))
                .collect(Collectors.toList());
        System.out.println(sortIds);
    }
}
