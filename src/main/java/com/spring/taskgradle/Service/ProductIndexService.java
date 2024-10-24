package com.spring.taskgradle.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.spring.taskgradle.Entity.Product;
import com.spring.taskgradle.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductIndexService {

    private final ElasticsearchClient elasticsearchClient;
    private final ProductRepository productRepository;

    @Autowired
    public ProductIndexService(ElasticsearchClient elasticsearchClient, ProductRepository productRepository) {
        this.elasticsearchClient = elasticsearchClient;
        this.productRepository = productRepository;
    }

    public void loadActiveProducts() throws IOException {
        List<Product> activeProducts = productRepository.findByActiveTrue();
        for (Product product : activeProducts) {
            IndexRequest<Product> request = IndexRequest.of(i -> i
                    .index("products")
                    .id(String.valueOf(product.getId()))
                    .document(product)
            );
            elasticsearchClient.index(request);
        }
    }

    public void loadProductsAfterDate(Date date) throws IOException {
        List<Product> products = productRepository.findByStartDateAfter(date);
        for (Product product : products) {
            IndexRequest<Product> request = IndexRequest.of(i -> i
                    .index("products")
                    .id(String.valueOf(product.getId()))
                    .document(product)
            );
            elasticsearchClient.index(request);
        }
    }

    public void createProductIndex() throws IOException {
        Map<String, Property> skuMapping = Map.of(
                "id", Property.of(p -> p.integer(i -> i)),
                "sku_code", Property.of(p -> p.text(t -> t)),
                "quantity", Property.of(p -> p.integer(i -> i)),
                "color", Property.of(p -> p.text(t -> t)),
                "available", Property.of(p -> p.boolean_(b -> b)),
                "weight", Property.of(p -> p.double_(d -> d))
                );

        Map<String, Property> productsMapping = Map.of(
                "id", Property.of(p -> p.integer(i -> i)),
                "name", Property.of(p -> p.text(t -> t)),
                "description", Property.of(p -> p.text(t -> t)),
                "price", Property.of(p -> p.double_(d -> d)),
                "active", Property.of(p -> p.boolean_(b -> b)),
                "startDate", Property.of(p -> p.date(d -> d)),
                "skus", Property.of(p -> p.nested(n -> n
                        .properties(skuMapping)
                ))
        );

        CreateIndexRequest createIndexRequest = CreateIndexRequest.of(i -> i
                .index("products")
                .mappings(TypeMapping.of(m -> m
                        .properties(productsMapping)
                ))
        );

        elasticsearchClient.indices().create(createIndexRequest);
    }

    public void recreateProductIndex() throws IOException {
        boolean indexExists = elasticsearchClient.indices().exists(e -> e.index("products")).value();
        if (indexExists) {
            elasticsearchClient.indices().delete(d -> d.index("products"));
        }
        createProductIndex();
    }

}
