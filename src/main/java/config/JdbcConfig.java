package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * jdbc配置
 * 
 * @author dell
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:jdbc.properties", "classpath:mybatis.properties" })
@MapperScan("dao")
public class JdbcConfig {
	@Value("${jdbc.driverClass}")
	private String driver;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String user;

	@Value("${jdbc.password}")
	private String password;

	// --mybatis 属性
	@Value("${mybatis.config.path}")
	private String myBatisConfigPath;
	@Value("${mybatis.mapper.xml.config.path}")
	private String mapperXMLConfigPath;
	@Value("${mybatis.alias.package.path}")
	private String aliasPackagePath;

	/**
	 * Bean的注解:该注解只能写再方法上，表明用此方法创建一个对象，并且放入Spring容器。
	 * name属性：给当前@bean的注解方法创建的对象指定一个名称 如bean的id
	 */
	@Bean(name = "dataSource")
	public DataSource createDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		// -- 1.sqlSessionFactoryBean是sqlSessionFactoru的代理类
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// -- 2.配置映射文件的路径
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// -- 3.映射文件的地址
		String packageXMLConfigPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ mapperXMLConfigPath;
		// -- 设置mybatis配置文件的路径
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(myBatisConfigPath));
		// -- 设置mapper对于的XML 文件的路径
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageXMLConfigPath));
		// 设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		// --设置实体别名包路径
		sqlSessionFactoryBean.setTypeAliasesPackage(aliasPackagePath);

		return sqlSessionFactoryBean.getObject();
	}
	
	//事务管理器
	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
		
		return new DataSourceTransactionManager(dataSource);
		
	}
	
	
}
