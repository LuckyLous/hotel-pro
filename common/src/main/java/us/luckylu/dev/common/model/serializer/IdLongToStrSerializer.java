package us.luckylu.dev.common.model.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author lu
 * @date 2019-04-18 14:26
 */
public class IdLongToStrSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        String result = object instanceof Long ? object + "" : "";
        serializer.write(result);
    }
}
