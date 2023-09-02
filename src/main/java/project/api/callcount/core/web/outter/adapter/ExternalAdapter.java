package project.api.callcount.core.web.outter.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.api.callcount.core.web.outter.ExternalPort;

@Component
public class ExternalAdapter implements ExternalPort {

    private final RestTemplate restTemplate;
    private final String externalURL;

    public ExternalAdapter(
        @Value("${external}")
        String externalURL,
        RestTemplate restTemplate
    ) {
        this.externalURL = externalURL;
        this.restTemplate = restTemplate;
    }

    @Override
    public String callExternal() {
        return restTemplate.getForObject(
            externalURL,
            String.class
        );
    }
}
