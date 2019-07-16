package config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/**
 * 单元测试用
 * @author dell
 *
 */
@Configuration
@ComponentScan({"dao","service","controller"})
@Import(JdbcConfig.class)
public class TestConfig {

}
