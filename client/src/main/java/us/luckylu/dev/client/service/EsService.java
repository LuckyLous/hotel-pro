package us.luckylu.dev.client.service;

/**
 * @author lu
 * @create 2019-08-25 23:38
 */
public interface EsService {

    void testIndex() throws Exception;

    void testGet() throws Exception;

    void testUpdate() throws Exception;

    void testDelete()throws Exception;

    void searchAll()throws Exception;

    void search()throws Exception;

    void searchAnalyzer()throws Exception;

    void paginate()throws Exception;

    void searchSort() throws Exception;

    void searchInclude()throws Exception;

    void searchByCondition() throws Exception;

    void searchHighlight() throws Exception;
}
