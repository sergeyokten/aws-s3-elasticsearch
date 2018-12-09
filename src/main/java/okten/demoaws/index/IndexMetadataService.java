package okten.demoaws.index;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import okten.demoaws.models.Document;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableConfigurationProperties({IndexMetadataService.Properties.class})
public class IndexMetadataService {


    Properties properties;
    RestHighLevelClient indexClient;


    @SneakyThrows
    void index(Document document) {
        IndexRequest request = new IndexRequest()
                .type("_doc")
                .index(properties.getIndex())
                .source("owner", document.getOwner(), "id", document.getId(), "title", document.getTitle());

        IndexResponse response = indexClient.index(request);
        if (response.status() != RestStatus.CREATED) {
            throw new IOException("cannot index document");
        }
    }

    @Getter
    @Setter
    @ConfigurationProperties("documents")
    static class Properties {
        String index;


    }
}
