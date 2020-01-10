package Sun.SAD;

import source_unit. *;
import database_unit. *;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configuration.properties")
public class SpringConfiguration {
	
    @Bean
    public SourceDisk sourceDisk() throws IOException {
        return new SourceDisk();
    }
    
    @Bean
    public DatabaseOne database() throws IOException {
        return new DatabaseOne();
    }
    
    @Bean
    public Recognize recognize() throws IOException {
        return new Recognize(sourceDisk(), database());
    }

}