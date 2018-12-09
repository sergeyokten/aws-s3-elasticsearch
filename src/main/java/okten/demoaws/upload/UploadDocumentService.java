package okten.demoaws.upload;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import okten.demoaws.models.Document;
import okten.demoaws.models.Ticket;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableConfigurationProperties({UploadDocumentService.Properties.class})
public class UploadDocumentService {


    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());
    Properties properties;
    AmazonS3 amazonS3;

    public Ticket createTicket(Document document) {
        log.info("Generate URL for '{}'", document);

        String bucket = properties.getBucket();
        String key = document.getOwner() + "/" + document.getId();
        log.info("Use key '{}' and bucket '{}'", key, bucket);
        Instant expiration = Instant.now().plusSeconds(properties.getTimeout());
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(Date.from(expiration));
        String psurl = amazonS3.generatePresignedUrl(urlRequest).toString();
        Ticket ticket = Ticket.builder().url(psurl).expiration(DATE_TIME_FORMATTER.format(expiration)).build();
        return ticket;
    }

    @Getter
    @Setter
    @ConfigurationProperties("documents")
    static class Properties {
        String bucket;
        int timeout;

    }
}
