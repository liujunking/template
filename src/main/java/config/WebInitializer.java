package config;

import java.nio.charset.StandardCharsets;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * servlet容器自动的时候，会找到此类并调用OnStartup方法
 * 此类相当于Web容器的入口方法，并取代web.xml文件
 * （1）tomcat（web容器在启动时候会自己找WebAPPllicationInitializer接口实现）
 * 		并且调用onStartUp方法
 * （2）构造spring容器
 * （3）把ServletContext交给Spring管理
 * （4）在ServletContext中添加一个servlet，这是SpringMVC的核心Servlet
 * 		它就是MVC模式中的C 控制器
 * 		以后就不写Servlet，写SpringMVC的Controller ，写好Controller
 * 		SpringMVC框架就会用自己的核心控制器（Servlet）进行转发相应的请求
 * @author dell
 *
 */
public class WebInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext) throws ServletException {
		//-- 1.构造Spring容器
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		//-- 2.spring容器加载配置
		ctx.register(AppConfig.class);
		//-- 3.Spring容器接管ServletContext应用上下文对象
		ctx.setServletContext(servletContext);
		//-- 4.添加Servlet(至少添加一个Servlet springMVC框架实现纳入servlet)
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		//-- 添加过滤器 (处理字符编码)
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
		
	}
}
	
