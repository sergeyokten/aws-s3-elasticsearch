package okten.demoaws.index;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class IndexClientConfiguration {


    @Bean
    public RestHighLevelClient indexClient(


            @Value("${es.url}") String usl) {


        RestClientBuilder builder = RestClient.builder(HttpHost.create(usl));

        return new RestHighLevelClient(builder);
    }
}
