package ives.agregginvestment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgreggInvestmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgreggInvestmentApplication.class, args);
    }

}
