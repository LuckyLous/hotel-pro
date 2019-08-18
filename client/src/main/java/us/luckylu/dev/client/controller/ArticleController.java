package us.luckylu.dev.client.controller;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.*;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.*;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.Constant;
import us.luckylu.dev.client.model.bo.Article;
import us.luckylu.dev.client.model.req.DocumentDeleteReq;
import us.luckylu.dev.client.model.req.DocumentSearchReq;
import us.luckylu.dev.client.model.req.DocumentUpdateReq;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;
import us.luckylu.dev.common.util.ResponseUtil;
import us.luckylu.dev.common.util.DateTimeUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lu
 * @create 2019-03-26 9:53
 */
@Api
@Validated
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private JestClient jestClient;

    @PostMapping("bulk")
    public void bulkIndex() throws Exception {
        Bulk bulk = new Bulk.Builder()
                .defaultIndex("article")
                .defaultType("article")
                .addAction(Arrays.asList(
                        new Index.Builder(new Article()).build(),
                        new Index.Builder(new Article()).build(),
                        new Index.Builder(new Article()).build(),
                        new Index.Builder(new Article()).build()
                )).build();
        jestClient.execute(bulk);
    }


    @PostMapping("/create")
    public void createIndex() throws Exception {
        Index index1 = new Index.Builder(new Article()).index("article").type("article").build();
        Index index2 = new Index.Builder(new Article()).index("article").type("article").build();
        Index index3 = new Index.Builder(new Article()).index("article").type("article").build();
        JestResult jestResult1 = jestClient.execute(index1);
        System.out.println(jestResult1.getJsonString());
        JestResult jestResult2 = jestClient.execute(index2);
        System.out.println(jestResult2.getJsonString());
        JestResult jestResult3 = jestClient.execute(index3);
        System.out.println(jestResult3.getJsonString());
    }

    /**
     * 更新Document
     * @throws Exception
     */
    @PostMapping("update")
    public void updateDocument(@RequestBody DocumentUpdateReq req) throws Exception {
        Article article = new Article();
        article.setId(req.getId());
        article.setTitle("中国3颗卫星拍到阅兵现场高清照");
        article.setContent("据中国资源卫星应用中心报道，9月3日，纪念中国人民抗日战争暨世界反法西斯战争胜利70周年大阅兵在天安门广场举行。资源卫星中心针对此次盛事，综合调度在轨卫星，9月1日至3日连续三天持续观测首都北京天安门附近区域，共计安排5次高分辨率卫星成像。在阅兵当日，高分二号卫星、资源三号卫星及实践九号卫星实现三星联合、密集观测，捕捉到了阅兵现场精彩瞬间。为了保证卫星准确拍摄天安门及周边区域，提高数据处理效率，及时制作合格的光学产品，资源卫星中心运行服务人员从卫星观测计划制定、复核、优化到系统运行保障、光学产品图像制作，提前进行了周密部署，并拟定了应急预案，为圆满完成既定任务奠定了基础。");
        article.setPubdate(LocalDateTime.now());
        article.setAuthor("匿名");
        article.setSource("新华网");
        article.setUrl("http://news.163.com/15/0909/07/B32AGCDT00014JB5.html");
        String script = "{" +
                "    \"doc\" : {" +
                "        \"title\" : \""+article.getTitle()+"\"," +
                "        \"content\" : \""+article.getContent()+"\"," +
                "        \"author\" : \""+article.getAuthor()+"\"," +
                "        \"source\" : \""+article.getSource()+"\"," +
                "        \"url\" : \""+article.getUrl()+"\"," +
                "        \"pubdate\" : \""+ DateTimeUtil.format(article.getPubdate(), "yyyy-MM-dd'T'HH:mm:ss") +"\"" +
                "    }" +
                "}";
        Update update = new Update.Builder(script).index(req.getIndex()).type(req.getType()).id(String.valueOf(req.getId())).build();
        JestResult result = jestClient.execute(update);
        System.out.println(result.getJsonString());
    }


    /**
     * 删除Document
     * @throws Exception
     */
    @PostMapping("delete")
    public void deleteDocument(DocumentDeleteReq req) throws Exception {
        Delete delete = new Delete.Builder(req.getId()).index(req.getIndex()).type(req.getType()).build();
        JestResult result = jestClient.execute(delete);
        System.out.println(result.getJsonString());
    }


    /**
     * 获取Document
     * @param index
     * @param type
     * @param id
     * @throws Exception
     */
    @GetMapping("get")
    public ResponseDto<Article> getDocument(String index, String type, String id) throws Exception {
        Get get = new Get.Builder(index, id).type(type).build();
        JestResult result = jestClient.execute(get);
        Article article = result.getSourceAsObject(Article.class);
        System.out.println(article.getTitle()+","+article.getContent());
        return ResponseUtil.getSuccessRsp(article);
    }

    /**
     * 查询全部
     * @throws Exception
     */
    @GetMapping("list")
    public ListResponseDto<Article> list() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 可以与example一样排序、分页
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
                .fetchSource(new String[]{"title", "content"}, null)
                .sort("author", SortOrder.DESC)
                .from(1).size(5);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("article")
                .addType("article")
                .build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        // 原生处理，将Map转换为List
        List<Hit<Article,Void>> hits = result.getHits(Article.class);
        List<Article> list = new ArrayList<>();
        hits.stream().forEach(map -> list.add(map.source));
        return ResponseUtil.getSuccessListRsp(list);
    }

    /**
     * 查询
     * @throws Exception
     */
    @GetMapping("search")
    public ListResponseDto<Article> search(DocumentSearchReq req) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // match：匹配查询，模糊查询。可以加分析器等。如果不加中文解析器，将搜索词分割后，可能与预想的搜索不一致
        // QueryBuilders.matchQuery("source", req.getContent())
        searchSourceBuilder.query(QueryBuilders.matchQuery("title","新华网").analyzer(Constant.ANALYZER));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("article")
                .build();
        SearchResult result = jestClient.execute(search);

        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        // jest代处理
        List<Article> list = result.getSourceAsObjectList(Article.class, true);
        return ResponseUtil.getSuccessListRsp(list);
    }

    /**
     * 将删除所有的索引
     * @throws Exception
     */
    public void deleteIndex() throws Exception {
        DeleteIndex deleteIndex = new DeleteIndex.Builder("article").build();
        JestResult result = jestClient.execute(deleteIndex);
        System.out.println(result.getJsonString());
    }

    /**
     * 清缓存
     * @throws Exception
     */
    public void clearCache() throws Exception {
        ClearCache closeIndex = new ClearCache.Builder().build();
        JestResult result = jestClient.execute(closeIndex);
        System.out.println(result.getJsonString());
    }



    /**
     * 关闭索引
     * @throws Exception
     */
    public void closeIndex() throws Exception {
        CloseIndex closeIndex = new CloseIndex.Builder("article").build();
        JestResult result = jestClient.execute(closeIndex);
        System.out.println(result.getJsonString());
    }

    /**
     * 优化索引
     * @throws Exception
     */
    public void optimize() throws Exception {
        Optimize optimize = new Optimize.Builder().build();
        JestResult result = jestClient.execute(optimize);
        System.out.println(result.getJsonString());
    }

    /**
     * 刷新索引
     * @throws Exception
     */
    public void flush() throws Exception {
        Flush flush = new Flush.Builder().build();
        JestResult result = jestClient.execute(flush);
        System.out.println(result.getJsonString());
    }

    /**
     * 判断索引目录是否存在
     * @throws Exception
     */
    public void indicesExists() throws Exception {
        IndicesExists indicesExists = new IndicesExists.Builder("article").build();
        JestResult result = jestClient.execute(indicesExists);
        System.out.println(result.getJsonString());
    }

    /**
     * 查看节点信息
     * @throws Exception
     */
    public void nodesInfo() throws Exception {
        NodesInfo nodesInfo = new NodesInfo.Builder().build();
        JestResult result = jestClient.execute(nodesInfo);
        System.out.println(result.getJsonString());
    }


    /**
     * 查看集群健康信息
     * @throws Exception
     */
    public void health() throws Exception {
        Health health = new Health.Builder().build();
        JestResult result = jestClient.execute(health);
        System.out.println(result.getJsonString());
    }

    /**
     * 节点状态
     * @throws Exception
     */
    public void nodesStats() throws Exception {
        NodesStats nodesStats = new NodesStats.Builder().build();
        JestResult result = jestClient.execute(nodesStats);
        System.out.println(result.getJsonString());
    }


    /**
     * Suggestion
     * @throws Exception
     */
    public void suggest() throws Exception{
        String suggestionName = "my-suggestion";
        Suggest suggest = new Suggest.Builder("{" +
                "  \"" + suggestionName + "\" : {" +
                "    \"text\" : \"the amsterdma meetpu\"," +
                "    \"term\" : {" +
                "      \"field\" : \"body\"" +
                "    }" +
                "  }" +
                "}").build();
        SuggestResult suggestResult = jestClient.execute(suggest);
        System.out.println(suggestResult.isSucceeded());
        List<SuggestResult.Suggestion> suggestionList = suggestResult.getSuggestions(suggestionName);
        System.out.println(suggestionList.size());
        for(SuggestResult.Suggestion suggestion:suggestionList){
            System.out.println(suggestion.text);
        }
    }


    public void createSearch(String queryString) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(queryString));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");//高亮title
        highlightBuilder.field("content");//高亮content
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(200);//高亮内容长度
        searchSourceBuilder.highlight(highlightBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("article")
                .build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        List<Hit<Article,Void>> hits = result.getHits(Article.class);
        for (Hit<Article, Void> hit : hits) {
            Article source = hit.source;
            //获取高亮后的内容
            Map<String, List<String>> highlight = hit.highlight;
            List<String> titlelist = highlight.get("title");//高亮后的title
            if(titlelist!=null){
                source.setTitle(titlelist.get(0));
            }
            List<String> contentlist = highlight.get("content");//高亮后的content
            if(contentlist!=null){
                source.setContent(contentlist.get(0));
            }
            System.out.println("标题："+source.getTitle());
            System.out.println("内容："+source.getContent());
            System.out.println("url："+source.getUrl());
            System.out.println("来源："+source.getSource());
            System.out.println("作者："+source.getAuthor());
        }
    }

}
