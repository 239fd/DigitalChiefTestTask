package com.spring.taskgradle.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.spring.taskgradle.Entity.Product;
import com.spring.taskgradle.Entity.Sku;
import com.spring.taskgradle.config.ElasticSearchFilterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final ElasticSearchFilterConfig config;

    @Autowired
    public ProductSearchService(ElasticsearchClient elasticsearchClient, ElasticSearchFilterConfig config) {
        this.elasticsearchClient = elasticsearchClient;
        this.config = config;
    }

    public List<Product> search(String query) throws IOException {
        SearchRequest searchRequest = createSearchRequest(query);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(searchRequest, Product.class);
        List<Product> products = new ArrayList<>(searchResponse.hits().hits().stream()
                .map(Hit::source)
                .toList());
        if(config.isActive()){
            products.forEach(product ->{
                List<Sku> filteredSkus = product.getSkuList()
                        .stream()
                        .filter(sku -> sku.getColor().equals(config.getColor()) && sku.isAvailable())
                        .toList();
                product.setSkuList(filteredSkus);

            });
            for (Product product : products) {
                List<Sku> skuList = product.getSkuList();
                if(skuList == null){
                    products.remove(product);
                }
                else{
                    for(int j = 0; j < skuList.size(); j++){
                        if (skuList.get(j) == null) {
                            skuList.remove(j);
                        }
                    }
                }
                if(skuList == null){
                    products.remove(product);
                }
            }
        }
        return products;

    }
    private SearchRequest createSearchRequest(String query) {
        return SearchRequest.of(s -> s
                .index("products")
                .query(q -> q
                        .multiMatch(m -> m
                                .query(query)
                                .fields("name", "description")
                        )
                )
        );
    }
}


