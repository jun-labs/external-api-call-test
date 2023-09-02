package project.api.callcount.core.web.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.api.callcount.core.web.outter.ExternalPort;

@Controller
public class ExternalApiCallController {

    private static final Logger log = LoggerFactory.getLogger(ExternalApiCallController.class);
    private final ExternalPort externalURL;

    public ExternalApiCallController(ExternalPort externalURL) {
        this.externalURL = externalURL;
    }

    @GetMapping("/external")
    @Cacheable(cacheNames = "apiCache", key = "#root.method.name")
    public ResponseEntity<String> callExternalAPI() {
        log.info("Call external api.");
        String htmlWebPage = externalURL.callExternal();
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body(htmlWebPage);
    }

    @GetMapping("/evict-cache")
    @CacheEvict(value = "apiCache", key = "'callExternalAPI'")
    public ResponseEntity<Void> evictCache() {
        log.info("Evict apiCache.");
        return ResponseEntity.ok()
            .build();
    }
}


