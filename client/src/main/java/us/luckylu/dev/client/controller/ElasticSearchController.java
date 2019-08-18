package us.luckylu.dev.client.controller;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.Constant;
import us.luckylu.dev.client.model.Gem;
import us.luckylu.dev.client.model.req.DocumentSearchReq;
import us.luckylu.dev.client.model.req.GemCreateReq;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;
import us.luckylu.dev.exception.ErrorCode;
import us.luckylu.dev.exception.GemException;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lu
 * @create 2019-03-28 13:53
 */
@Slf4j
@RestController
@RequestMapping("es")
public class ElasticSearchController {

    @Resource
    private JestClient jestClient;

    @PostMapping("create")
    public ResponseDto batchCreate(@Validated @RequestBody GemCreateReq req){
        Index index = new Index.Builder(req).index(Constant.Gem.INDEX_NAME).type(Constant.Gem.TYPE_NAME).build();
        try {
            JestResult jestResult = jestClient.execute(index);
            log.info(jestResult.getJsonString());
            return ResponseUtil.getSuccessRsp(jestResult.getJsonString());
        } catch (IOException e) {
            log.error(ErrorCode.GEM_CREATE_DOCUMENT_ERROR.getMessage(), e);
            throw new GemException(ErrorCode.GEM_CREATE_DOCUMENT_ERROR);
        }
    }

    @GetMapping("search")
    public ListResponseDto<Gem> search(DocumentSearchReq documentSearchReq) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 必须
//        QueryBuilder queryBuilder1 = QueryBuilders.matchPhraseQuery("name", "绿扳指");
        // 搜索范围标题、内容，搭配中文解析器
        QueryBuilder queryBuilder2 = QueryBuilders.multiMatchQuery("绿扳指", "name", "brief");
        // 过滤
        QueryBuilder queryBuilder3 = QueryBuilders.rangeQuery("size").lte(3);
        // 可能有，多选
        QueryBuilder queryBuilder4 = QueryBuilders.termsQuery("color", "绿色");
        QueryBuilder queryBuilder5 = QueryBuilders.termsQuery("category", "项链");
        // 涉及到多条件查询用boolQuery，组合多个query. must表示and，mustNot表示not，should表示or
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(queryBuilder2))
                .postFilter(queryBuilder3);

        // 输出查询语句
        log.info(searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(Constant.Gem.INDEX_NAME)
                .build();
        SearchResult result = jestClient.execute(search);

        log.info("本次查询共查到："+result.getTotal()+"个结果！");
        // jest代处理
        List<Gem> list = result.getSourceAsObjectList(Gem.class, true);
        list.stream().filter(source -> Objects.nonNull(source.getColor())).collect(Collectors.toList());
        return ResponseUtil.getSuccessListRsp(list);
    }
}
