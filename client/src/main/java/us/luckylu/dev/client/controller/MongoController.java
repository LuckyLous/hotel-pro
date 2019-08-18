package us.luckylu.dev.client.controller;

import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.Gem;
import us.luckylu.dev.client.model.req.GemCreateReq;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author lu
 * @create 2019-03-28 13:52
 */
@Slf4j
@RestController
@RequestMapping("mongo")
public class MongoController {

    @Resource
    private MongoTemplate mongoTemplate;

    @PostMapping("save")
    public ResponseDto saveRecord(@Valid @RequestBody GemCreateReq req){
        mongoTemplate.save(req, "gem");
        return ResponseUtil.getSuccessRsp();
    }

    @GetMapping("find")
    public ResponseDto<DBObject> find(){
        DBObject grade = mongoTemplate.getCollection("grade").findOne();
        return ResponseUtil.getSuccessRsp(grade);
    }

    @GetMapping("query")
    public ListResponseDto<Gem> queryRecord(@RequestBody Map<String, Object> query) {
        Criteria criteria = null;
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            if (criteria == null) {
                // 精确匹配
                if(Objects.equals(entry.getKey(), "age")){
                    criteria = Criteria.where(entry.getKey()).is(entry.getValue());
                }
                if(Objects.equals(entry.getKey(), "name")){
                    //criteria = Criteria.where(entry.getKey()).regex(PatternUtil.blurry(entry.getValue().toString()));
                    criteria = Criteria.where(entry.getKey()).regex(entry.getValue().toString());
                }
            } else {
                criteria.and(entry.getKey()).is(entry.getValue());
            }
        }

        Query q = new Query(criteria);
        List<Gem> result = mongoTemplate.find(q, Gem.class, "gem");
        log.info("query is {}, result is {}", query, result);
        return ResponseUtil.getSuccessListRsp(result);
    }
}
