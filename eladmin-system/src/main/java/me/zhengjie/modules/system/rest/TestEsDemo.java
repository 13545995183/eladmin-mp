package me.zhengjie.modules.system.rest;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import net.dreamlu.mica.core.result.R;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Configuration
public class TestEsDemo {
    public static RestHighLevelClient esClient;
    static {
        esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
    }

    public static void main(String[] args) throws IOException{
        System.out.println("connect success");
        //createIndex();
        //为user索引添加mapping数据信息
        //createMapDocument();
        //更新操作
        //updateMapDocument();
        //删除操作
        //deleteMapDocument();
        /*GetRequest getRequest=new GetRequest("userinfo","3");
        GetResponse getResponse = esClient.get(getRequest, RequestOptions.DEFAULT);
        Map responseSourceAsMap= getResponse.getSourceAsMap();
        System.out.println("索引为userinfo的数据："+responseSourceAsMap);*/
        //addMapDocumentList();
        deleteList();
        //updateByList();
        queryInfo();
        esClient.close();
    }
    public static Object queryInfo() throws IOException {
        SearchRequest searchRequest=new SearchRequest("userinfo");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //sourceBuilder.query(QueryBuilders.termQuery("sex","1"));
        sourceBuilder.size(10);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=esClient.search(searchRequest,RequestOptions.DEFAULT);
        SearchHit[] hits=searchResponse.getHits().getHits();
        List<Object> result=new ArrayList<Object>();
        List<User> userList=new ArrayList<User>();
        for (SearchHit hit :
                hits) {
            //System.out.println(hit.getSourceAsString());
            String hitinfo=hit.getSourceAsString();
            JSONObject jsonObject= JSONObject.parseObject(hitinfo);
            userList.add(JSONObject.parseObject(jsonObject.toJSONString(), User.class));
            result.add(hitinfo);
        }
        //System.out.println(result);
        System.out.println(userList);
        return userList;
    }
    //创建索引
    public static void  createIndex() throws IOException{
        CreateIndexRequest createIndexRequest=new CreateIndexRequest("data");
        CreateIndexResponse createIndexResponse=esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean ack=createIndexResponse.isAcknowledged();
        System.out.println("创建索引状态为："+ack);
    }
    //Map添加文档,将索引user中添加信息
    public static void  createMapDocument() throws IOException{
        IndexRequest indexRequest=new IndexRequest("data");
        indexRequest.id("3");
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        map.put("name","qiansheng");
        map.put("sex",1);
        map.put("disctions","新增数据");
        indexRequest.source(map);
        IndexResponse response=esClient.index(indexRequest,RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }
    //更新map中的数据
    public static void  updateMapDocument() throws IOException{
        UpdateRequest updateRequest=new UpdateRequest("userinfo","3");
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("name","qdd"+3);
        objectMap.put("sex",3);
        objectMap.put("disctions","no datasource");
        updateRequest.doc(objectMap);
        UpdateResponse response=esClient.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }
    //删除操作
    public static void  deleteMapDocument() throws IOException{
        //两种写法都可以
        DeleteRequest request=new DeleteRequest("userinfo").id("2");
        DeleteResponse response=esClient.delete(request,RequestOptions.DEFAULT);
        System.out.println(response.status());
    }
    //批量操作添加
    public static void  addMapDocumentList() throws IOException{
        List<User> userList=new ArrayList<>();
        int id=1;
        User use=new User();
        use.setSex(0);
        use.setId(4);
        use.setName("qdd");
        User user1=new User();
        user1.setName("qianduoduo");
        user1.setSex(1);
        user1.setId(5);
        User user2=new User();
        user2.setName("qiansheng");
        user2.setSex(1);
        user2.setId(6);
        userList.add(use);
        userList.add(user1);
        userList.add(user2);
        BulkRequest bulkRequest=new BulkRequest();
        if(userList.stream().count()>1){
            for (int i = 0; i < userList.size(); i++) {
                IndexRequest indexRequest=new IndexRequest("userinfo").id(String.valueOf(userList.get(i).getId()));
                Map<String,Object>map=BeanUtil.beanToMap(userList.get(i));
                indexRequest.source(map);
                bulkRequest.add(indexRequest);
            }
        }
        BulkResponse bulkResponse=esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("批量添加状态："+bulkResponse.status());
    }
    //批量操作删除
    public static void deleteList() throws IOException{
        String ids="4,5,6";
        if(ids.isEmpty()){
            System.out.println("没数据");
        }
        List<Object> deleteListByIds=Arrays.asList(ids);
        BulkRequest bulkRequest=new BulkRequest();
        if (deleteListByIds.stream().count()>0){
            for (Object object:
                 deleteListByIds) {
                DeleteRequest deleteRequest=new DeleteRequest().index("userinfo").id(object.toString());
                bulkRequest.add(deleteRequest);
            }
        }
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        BulkResponse bulkResponse=esClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println("批量删除状态："+bulkResponse.status()+";  "+ Arrays.stream(bulkResponse.getItems()).toArray());
    }
    public static void updateByList()throws IOException{
        String ids="4,5,6";
        if(ids.isEmpty()){
            System.out.println("没数据");
        }
        List<Object> updateList=Arrays.asList(ids);
        BulkRequest bulkRequest=new BulkRequest();
        for (Object obj :
                updateList) {
            Map<String,Object> objectMap=new HashMap<>();
            objectMap.put("name","qianduoduo"+obj);
            objectMap.put("sex",2);
            UpdateRequest updateRequest=new UpdateRequest().index("userinfo").id(obj.toString()).doc(objectMap);
            bulkRequest.add(updateRequest);
        }
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        BulkResponse bulkResponse=esClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println("批量更新状态："+bulkResponse.status()+";  "+ Arrays.stream(bulkResponse.getItems()).toArray());
    }
}
