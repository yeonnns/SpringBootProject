package com.test;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@MapperScan(basePackages = "com.test.mapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
@EnableTransactionManagement
public class DatabaseConfigMain {

	@Primary
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource1")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "masterSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));
		return sessionFactory.getObject();
	}

	@Primary
	@Bean(name = "masterSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

	@Bean
	MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}

}
