package project.api.callcount.core.web.presentation;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.api.callcount.core.web.outter.ExternalPort;

@Controller
public class ExternalApiCallController {

    private final ExternalPort externalURL;

    public ExternalApiCallController(ExternalPort externalURL) {
        this.externalURL = externalURL;
    }

    @GetMapping("/external")
    @Cacheable(cacheNames = "apiCache")
    public ResponseEntity<String> callExternalAPI() {
        String htmlWebPage = externalURL.callExternal();
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body(htmlWebPage);
    }
}


