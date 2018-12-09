package okten.demoaws.upload;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import okten.demoaws.models.Document;
import okten.demoaws.models.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UploadDocumentResource {

    UploadDocumentService uploadDocumentService;


    @PostMapping("/_upload")
    public Ticket upload(
            @RequestBody Document document
    ) {
        return uploadDocumentService.createTicket(document);
    }
}
