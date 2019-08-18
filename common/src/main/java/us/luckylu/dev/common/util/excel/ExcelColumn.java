package us.luckylu.dev.common.util.excel;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author lu
 * @create 2019-03-01 17:33
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    String head();

    int width() default 4000;

    int sequence();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
