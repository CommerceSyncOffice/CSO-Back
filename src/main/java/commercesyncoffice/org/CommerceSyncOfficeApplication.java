package commercesyncoffice.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CommerceSyncOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceSyncOfficeApplication.class, args);
    }

}
