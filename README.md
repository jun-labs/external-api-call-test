# External Api call count test

Test the actual number of external API calls using caffeine cache. With caffeine cache, you don't have to actually call an external api. This allows you to use the cache without any other external dependencies. However, you must have the ability to refresh the cache, and if real-time data is important, make sure you find [another](https://redis.io/) [way](https://memcached.org/). It should be used only for data that does not change well.




<br/><br/><br/><br/>


## Getting Started

Put the html web page URL in application.yml, application-test.yml before running the application.

> You should install jdk 17 or higher. <br/>

<br/><br/><br/>

## Run Application

````text
$ ./gradlew bootRun
````

<br/><br/>

## Run Test

````text
$ ./gradlew test
````

<br/><br/>

## Run Build

````text
$ ./gradlew build
````

<br/><br/>

## Result

Even if the external API is called multiple times, it is actually called only once.


````java
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
    void external_api_call_count_test() {
        String expected = externalApiUrl;

        when(externalPort.callExternal())
            .thenReturn(expected);

        for (int index = 1; index <= 1_000; index++) {
            controller.callExternalAPI();
        }

        verify(externalPort, times(1)).callExternal();
    }
}
````

<br/><br/>

## Env
&nbsp;&nbsp; - Java 17 <br/>
&nbsp;&nbsp;- SpringBoot 3.0 <br/>
&nbsp;&nbsp;- Caffeine 3.1.5
