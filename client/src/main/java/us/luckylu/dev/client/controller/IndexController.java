package us.luckylu.dev.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.Constant;
import us.luckylu.dev.client.model.req.es.PersonListReq;
import us.luckylu.dev.client.service.EsService;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lu
 * @date 2019-08-24 9:47
 */
@Api
@Validated
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private TransportClient client;

    @Resource
    private EsService esService;

    @ApiOperation(value = "新增")
    @PostMapping("create")
    public ResponseDto batchCreate(@Validated @RequestBody PersonListReq req) throws Exception {
        /*BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (Person person : req.getPersonList()) {
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex(Constant.Person.INDEX_NAME, Constant.Person.TYPE_NAME).setSource(
                    XContentFactory.jsonBuilder()
                            .startObject()
                            .field("about", person.getAbout())
                            .field("address", person.getAddress() )
                    .endObject()
            );

            bulkRequestBuilder.add(indexRequestBuilder);
        }


        BulkResponse response = bulkRequestBuilder.get();
        if(response.hasFailures()) {
            System.out.println("操作失败");
        }*/
        esService.testIndex();
//        System.out.println("status"+response.status());
//        System.out.println("type"+response.getType());
//        System.out.println("id"+response.getId());
//        System.out.println("status"+response.status());
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("list")
    public ResponseDto list() throws Exception {
        // 构造search request .在这里无参，查询全部索引
        SearchRequest searchRequest  = new SearchRequest(Constant.Film.INDEX_NAME);
        searchRequest.types(Constant.Film.TYPE_NAME);

        // 大多数查询参数要写在searchSourceBuilder里
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // 配置好searchSourceBuilder后，将它传入searchReques
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest).get();
        System.out.println(searchResponse);
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("paginate")
    public ResponseDto paginate() throws Exception {
        // 构造search request .在这里无参，查询全部索引
        SearchRequest searchRequest  = new SearchRequest(Constant.Person.INDEX_NAME);
        searchRequest.types(Constant.Person.TYPE_NAME);

        // 大多数查询参数要写在searchSourceBuilder里
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("address", "Lois");
        searchSourceBuilder.query(termsQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 配置好searchSourceBuilder后，将它传入searchReques
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest).get();
        System.out.println(searchResponse);
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "详情")
    @GetMapping("testGet")
    public ResponseDto testGet() throws Exception {
        esService.testGet();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("testDelete")
    public ResponseDto testDelete() throws Exception {
        esService.testDelete();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "更新")
    @PutMapping("testUpdate")
    public ResponseDto testUpdate() throws Exception {
        esService.testUpdate();
        return ResponseUtil.getSuccessRsp();
    }


    @ApiOperation(value = "列表")
    @GetMapping("searchAll")
    public ResponseDto searchAll() throws Exception {
        esService.searchAll();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("paginateFilm")
    public ResponseDto paginateFilm() throws Exception {
        esService.paginate();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("searchSort")
    public ResponseDto searchSort() throws Exception {
        esService.searchSort();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("searchInclude")
    public ResponseDto searchInclude() throws Exception {
        esService.searchInclude();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("searchByCondition")
    public ResponseDto searchByCondition() throws Exception {
        esService.searchByCondition();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("searchHighlight")
    public ResponseDto searchHighlight() throws Exception {
        esService.searchHighlight();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("search")
    public ResponseDto search() throws Exception {
        esService.search();
        return ResponseUtil.getSuccessRsp();
    }

    @ApiOperation(value = "列表")
    @GetMapping("searchAnalyzer")
    public ResponseDto searchAnalyzer() throws Exception {
        esService.searchAnalyzer();
        return ResponseUtil.getSuccessRsp();
    }
}
