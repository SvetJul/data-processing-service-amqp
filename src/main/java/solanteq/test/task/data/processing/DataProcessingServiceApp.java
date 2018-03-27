package solanteq.test.task.data.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableBinding
@EnableAsync
@SpringBootApplication
public class DataProcessingServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingServiceApp.class, args);
    }

    @Bean
    public Map<String, ProcessingContext> processingStorage() {
        return new ConcurrentHashMap<>();
    }
}
