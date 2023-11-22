package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;


@Configuration
public class JpaConfig {


    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("lsh"); // TODO: 2023-11-21 스프링 시큐리티로 인증 기능을 붙힐 떄 수정 예정
    }
}
