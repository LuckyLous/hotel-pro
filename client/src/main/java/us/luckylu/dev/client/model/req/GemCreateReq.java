package us.luckylu.dev.client.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lu
 * @create 2019-03-25 17:51
 */
@Data
public class GemCreateReq {

    @NotBlank
    private String name;

    @NotBlank
    private String brief;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal size;

    @NotBlank
    private String color;

    @NotBlank
    private String category;

    @NotBlank
    private String type;


}
