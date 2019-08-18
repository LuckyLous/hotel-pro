package us.luckylu.dev.client.model.req;

import lombok.Data;

/**
 * @author lu
 * @create 2019-03-26 10:58
 */
@Data
public class DocumentDeleteReq {

    private String id;

    private String index;
    
    private String type;
}
