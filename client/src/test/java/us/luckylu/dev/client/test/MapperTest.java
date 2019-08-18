package us.luckylu.dev.client.test;

import com.github.lit.jdbc.JdbcTools;
import com.github.lit.jdbc.statement.select.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.luckylu.dev.client.ClientApplication;
import us.luckylu.dev.model.User;

import javax.annotation.Resource;

/**
 * @author lu
 * @date 2019-04-18 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapperTest {

    @LocalServerPort
    private int port;

    @Resource
    private JdbcTools jdbcTools;

    @Test
    public void testInsert(){
        Select<User> userSelect = jdbcTools.select(User.class);
        System.out.println(userSelect.toString());
    }
}
