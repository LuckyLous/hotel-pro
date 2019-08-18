package us.luckylu.dev.client.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lu
 * @create 2019-03-25 17:24
 */
@Data
public class Gem {

    private String name;

    private String brief;

    private BigDecimal price;

    private BigDecimal size;

    private String color;

    private String category;

    private String type;

    public Gem() {
    }

    public Gem(String name, String brief, BigDecimal price, BigDecimal size, String color, String category, String type) {
        this.name = name;
        this.brief = brief;
        this.price = price;
        this.size = size;
        this.color = color;
        this.category = category;
        this.type = type;
    }


}
