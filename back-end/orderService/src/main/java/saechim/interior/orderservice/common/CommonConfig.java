package saechim.interior.orderservice.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import saechim.interior.orderservice.dto.KafkaOrderDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommonConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
