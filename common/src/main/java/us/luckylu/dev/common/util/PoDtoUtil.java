package us.luckylu.dev.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import us.luckylu.dev.common.model.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lu
 * @create 2019-03-29 18:04
 */
@Slf4j
public class PoDtoUtil {

    public static <T,R> R copy(T source, Class<R> clazz) {
        R result = null;
        try {
            if (source != null) {
                result = clazz.newInstance();
                BeanUtils.copyProperties(source, result);
            }
        } catch (Exception e) {
            log.error("copy有误，为{}", e.getMessage());
        }
        return result;
    }

    public static <T,R> List<R> copyToList(List<T> sourceList, Class<R> clazz) {
        List<R> rspList = null;
        if (!CollectionUtils.isEmpty(sourceList)) {
            rspList = new ArrayList<>();
            for (T source : sourceList) {
                rspList.add(copy(source, clazz));
            }
        }
        return rspList;
    }

    public static <T,R> Pager<R> copyToPager(Pager<T> source, Class<R> clazz) {
        Pager<R> rspPager = null;
        if (source != null) {
            rspPager = new Pager<>();
            BeanUtils.copyProperties(source, rspPager);
            rspPager.setArray(copyToList(source.getArray(), clazz));
        }
        return rspPager;
    }

}
