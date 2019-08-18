package us.luckylu.dev.common.model.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * author: lu
 * date: 2018/1/7
 */
public class BooleanToIntegerSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Integer result = object instanceof Boolean && ((Boolean) object) ? 1 : 0;
        serializer.write(result);
    }

}
