package com.fastcampus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class PracticeProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeProjectBoardApplication.class, args);
    }

}
