package us.luckylu.dev.common.util.excel;

import us.luckylu.dev.common.util.DateTimeUtil;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author lu
 * @date 2019-04-12 9:28
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnType {

    // 是否整形
    boolean isInteger() default false;

    // 用于Integer类型的转换
    Class<? extends Enum> convertEnum() ;

    // 是否为数值
    boolean isDecimal() default false;

    // 数值保留多少位数
    int scale() default 2;

    // 是否时间日期
    boolean isDateTime() default false;

    // 时间格式
    String dateTimeFormatPattrn() default DateTimeUtil.GENERAL_DATE_TIME_PATTERN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
