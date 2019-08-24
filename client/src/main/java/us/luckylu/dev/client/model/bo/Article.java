package us.luckylu.dev.client.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.luckylu.dev.common.base.BaseModelWithId;

import java.time.LocalDateTime;

/**
 * @author lu
 * @create 2019-03-26 9:49
 */
@Data
@AllArgsConstructor
public class Article extends BaseModelWithId {

    private String title;
    private String content;
    private String url;

    private LocalDateTime publishdate;
    private String source;
    private String author;

    public Article() {
        super();
    }

}
