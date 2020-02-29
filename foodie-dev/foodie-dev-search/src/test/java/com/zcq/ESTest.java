package com.zcq;

import com.zcq.es.pojo.Stu;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ESTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;


    /**
     * 不建议使用ElasticsearchTemplate 对索引进行管理【增、删、改】
     * 索引就像是数据库，或者表。。
     * 只会对数据做CRUD操作【对文档数据使用ElasticsearchTemplate】
     */
    @Test
    public void createIndexStu() {
        Stu stu = new Stu();
//        stu.setStuId(10032l);
        stu.setName("bb man");
        stu.setAge(66);
        stu.setMoney(66);
        stu.setDescription("bb man");
        stu.setSign("1");
        //没有索引创建索引会根据stu里设置的属性来映射es索引的mappings
        //没有索引会创建索引并生成文档。有索引会生成文档，不会覆盖。
        IndexQuery indexQuery=new IndexQueryBuilder().withObject(stu).build();
        esTemplate.index(indexQuery);
    }

    @Test
    public void deleteIndexStu(){
        esTemplate.deleteIndex(Stu.class);
    }


}
















