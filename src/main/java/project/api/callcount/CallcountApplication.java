package project.api.callcount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CallcountApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallcountApplication.class, args);
    }

}
