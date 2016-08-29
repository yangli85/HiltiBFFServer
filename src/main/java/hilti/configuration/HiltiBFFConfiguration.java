package hilti.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "hilti")
public class HiltiBFFConfiguration {
    @Bean(name="multipartResolver")
    public StandardServletMultipartResolver resolver(){
        return new StandardServletMultipartResolver();
    }

}
