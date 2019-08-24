package us.luckylu.dev.client.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import us.luckylu.dev.client.model.bo.Article;
import us.luckylu.dev.client.model.req.DocumentDeleteReq;
import us.luckylu.dev.client.model.req.DocumentSearchReq;
import us.luckylu.dev.client.model.req.DocumentUpdateReq;
import us.luckylu.dev.common.model.dto.rsp.ListResponseDto;
import us.luckylu.dev.common.model.dto.rsp.ResponseDto;

/**
 * @author lu
 * @create 2019-03-26 9:53
 */
@Api
@Validated
@RestController
@RequestMapping("article")
public class ArticleController {

    @PostMapping("bulk")
    public void bulkIndex() throws Exception {
    }


    @PostMapping("/create")
    public void createIndex() throws Exception {
    }

    /**
     * 更新Document
     * @throws Exception
     */
    @PostMapping("update")
    public void updateDocument(@RequestBody DocumentUpdateReq req) throws Exception {
    }


    /**
     * 删除Document
     * @throws Exception
     */
    @PostMapping("delete")
    public void deleteDocument(DocumentDeleteReq req) throws Exception {
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
        return null;
    }

    /**
     * 查询全部
     * @throws Exception
     */
    @GetMapping("list")
    public ListResponseDto<Article> list() throws Exception {
        return null;
    }

    /**
     * 查询
     * @throws Exception
     */
    @GetMapping("search")
    public ListResponseDto<Article> search(DocumentSearchReq req) throws Exception {
        return null;
    }

    /**
     * 将删除所有的索引
     * @throws Exception
     */
    public void deleteIndex() throws Exception {
    }

    /**
     * 清缓存
     * @throws Exception
     */
    public void clearCache() throws Exception {
    }



    /**
     * 关闭索引
     * @throws Exception
     */
    public void closeIndex() throws Exception {
    }

    /**
     * 优化索引
     * @throws Exception
     */
    public void optimize() throws Exception {
    }

    /**
     * 刷新索引
     * @throws Exception
     */
    public void flush() throws Exception {
    }

    /**
     * 判断索引目录是否存在
     * @throws Exception
     */
    public void indicesExists() throws Exception {
    }

    /**
     * 查看节点信息
     * @throws Exception
     */
    public void nodesInfo() throws Exception {
    }


    /**
     * 查看集群健康信息
     * @throws Exception
     */
    public void health() throws Exception {
    }

    /**
     * 节点状态
     * @throws Exception
     */
    public void nodesStats() throws Exception {
    }


    /**
     * Suggestion
     * @throws Exception
     */
    public void suggest() throws Exception{
    }


    public void createSearch(String queryString) throws Exception {
    }

}
