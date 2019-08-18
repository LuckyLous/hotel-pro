package us.luckylu.dev.client.model.bo;

import io.searchbox.annotations.JestId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lu
 * @create 2019-03-26 9:49
 */
@Data
public class Article {

    @JestId
    private int id;
    private String title;
    private String content;
    private String url;

    private LocalDateTime pubdate;
    private String source;
    private String author;

    public Article() {
        super();
    }

    public Article(int id, String title, String content, String url, LocalDateTime pubdate, String source, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.pubdate = pubdate;
        this.source = source;
        this.author = author;
    }
}
