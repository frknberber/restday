package restday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import restday.connection.ConnectToDB;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestdayApplication {

    public static String language;

    public static void main(String[] args) {
        SpringApplication.run(RestdayApplication.class, args);

        ConnectToDB connectToDB = new ConnectToDB();
        connectToDB.connect();

        language = System.getProperty("user.language") ;
        System.out.println("System Language : " + System.getProperty("user.language"));
    }

}
