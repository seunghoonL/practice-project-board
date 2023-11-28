package com.fastcampus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@ConfigurationPropertiesScan
@SpringBootApplication
public class PracticeProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeProjectBoardApplication.class, args);
    }

}
