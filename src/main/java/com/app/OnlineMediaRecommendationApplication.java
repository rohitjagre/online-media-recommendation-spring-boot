package com.app;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class OnlineMediaRecommendationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineMediaRecommendationApplication.class, args);
    }
}
