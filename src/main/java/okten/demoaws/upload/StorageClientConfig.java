package okten.demoaws.upload;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StorageClientConfig {

    @Bean
    public AmazonS3 storageClient(
            @Value("${s3.region}") String region,
            @Value("${s3.endpoint}") String endpoint) {
        log.info("creta client for region {} , endpoint {}", region, endpoint);
        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withPathStyleAccessEnabled(true).build();


    }
}
