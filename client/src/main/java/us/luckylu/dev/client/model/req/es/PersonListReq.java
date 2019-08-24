package us.luckylu.dev.client.model.req.es;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import us.luckylu.dev.client.model.bo.Person;

import java.util.List;

/**
 * @author lu
 * @date 2019-08-24 11:59
 */
@ApiModel
@Data
public class PersonListReq {

    private List<Person> personList;
}
