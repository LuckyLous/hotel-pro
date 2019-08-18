package us.luckylu.dev.common.util.excel;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author lu
 * @create 2019-03-01 17:32
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

    String headName() default "xxx表";

    String sheetName() default "xxx表";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
