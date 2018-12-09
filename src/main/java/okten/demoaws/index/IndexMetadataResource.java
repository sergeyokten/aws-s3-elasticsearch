package okten.demoaws.index;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import okten.demoaws.models.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jar.resources.jar;

@RestController
@RequestMapping(path = "api",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class IndexMetadataResource {


    IndexMetadataService indexMetadataService;


    @PostMapping("/index")
    public void upload(
            @RequestBody Document document
    ) {
        indexMetadataService.index(document);
    }
}

