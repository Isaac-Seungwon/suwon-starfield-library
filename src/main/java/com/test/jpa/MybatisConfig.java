package com.test.jpa;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/*
 * MyBatis 설정 파일입니다.
 * Spring Boot 애플리케이션에서 MyBatis를 구성하고 매퍼 인터페이스를 검색하여 빈으로 등록합니다.
 * 데이터베이스 연결에 사용되는 DataSource를 설정하고, SqlSessionFactory를 생성하여 MyBatis와 Spring을 통합합니다.
 * MyBatis 매퍼 파일의 위치를 지정합니다.
 * 
 * 작성자: 이승원
 * 작성일: 2024.03.06.
 */
@Configuration
@MapperScan(basePackages = { "com.test.jpa.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

    /*
     * SqlSessionFactory 빈을 생성합니다.
     * 
     * @param dataSource 데이터베이스 연결에 사용되는 DataSource
     * @return SqlSessionFactory
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.test.jpa.model");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
