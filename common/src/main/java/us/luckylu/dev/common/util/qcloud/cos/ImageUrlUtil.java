package us.luckylu.dev.common.util.qcloud.cos;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lu
 * @create 2019-01-29 17:29
 */
@Component
public class ImageUrlUtil {

    @Resource(name = "publicQcloudCosUtil")
    private QcloudCosUtil publicQcloudCosUtil;

    @Resource(name = "privateQcloudCosUtil")
    private QcloudCosUtil privateQcloudCosUtil;

    /**
     * 根据私有读转换
     */
    public String convertToUrlByPrivate(String imgKey){
        return convertToUrl(imgKey, privateQcloudCosUtil);
    }

    /**
     * 根据私有读转换为list
     */
    public List<String> convertToUrlsByPrivate(String imgKeys){
        return convertToUrls(imgKeys, privateQcloudCosUtil);
    }

    /**
     * 根据公有读转换
     */
    public String convertToUrlByPublic(String imgKey){
        return convertToUrl(imgKey, publicQcloudCosUtil);
    }

    /**
     * 根据公有读转换为list
     */
    public List<String> convertToUrlsByPublic(String imgKeys){
        return convertToUrls(imgKeys, publicQcloudCosUtil);
    }

    /**
     * key - url
     */
    public static String convertToUrl(String imgKey, QcloudCosUtil qcloudCosUtil){
        if(StringUtils.isNotBlank(imgKey)){
            return qcloudCosUtil.getFileUrl(imgKey);
        }
        return "";
    }

    /**
     * keys - urls
     */
    public static List<String> convertToUrls(String imgKeys, QcloudCosUtil qcloudCosUtil){
        List<String> imageUrlList = new ArrayList<>();
        if (StringUtils.isNotBlank(imgKeys)) {
            List<String> imageKeyList = JSON.parseArray(imgKeys, String.class);
            for (String imageKey : imageKeyList) {
                imageUrlList.add(qcloudCosUtil.getFileUrl(imageKey));
            }
        }
        return imageUrlList;
    }
}
