package us.luckylu.dev.common.model.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import us.luckylu.dev.common.util.DateTimeUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * @author lu
 * @date 2019-04-03 14:36
 */
public class DateTimeToLongSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Long result = object instanceof LocalDateTime ? DateTimeUtil.toStamp( (LocalDateTime)object ) : 0;
        serializer.write(result);
    }
}
