package project.api.callcount.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import project.api.callcount.core.web.outter.ExternalPort;
import project.api.callcount.core.web.presentation.ExternalApiCallController;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ExternalApiCallCountIntegrationTest {

    @Value("${external}")
    private String externalApiUrl;

    @MockBean
    private ExternalPort externalPort;

    @Autowired
    private ExternalApiCallController controller;

    @Test
    @DisplayName("@Cacheable이 있다면 외부 API를 여러번 호출하더라도 실제로는 한 번만 호출된다.")
    void external_api_call_count_test() {
        String expected = externalApiUrl;

        when(externalPort.callExternal()).thenReturn(expected);

        for (int index = 1; index <= 1_000; index++) {
            controller.callExternalAPI();
        }

        verify(externalPort, times(1)).callExternal();
    }
}
