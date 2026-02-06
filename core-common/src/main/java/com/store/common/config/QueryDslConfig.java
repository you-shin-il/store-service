package com.store.common.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDSL 설정
 *
 * CRITICAL: Hibernate 6 (Spring Boot 3)에서는 반드시 JPAQueryFactory를 사용해야 한다.
 *   - HibernateQueryFactory는 Hibernate 6과 호환되지 않아 런타임 에러 발생
 *   - JPAQueryFactory는 JPA 표준 인터페이스를 사용하므로 Hibernate 버전에 무관하게 동작
 */
@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
