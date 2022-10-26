package edu.miu.cs590.userservice.config;

import edu.miu.cs590.userservice.dto.UserDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfigTopic {

    @Value("${user.auth-info.service.kafka.topic}")
    private String userTopic;


    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${user.service.kafka.group}")
    private String userServiceGroupId;


    @Bean
    public NewTopic createBookingTopic() {
        return TopicBuilder.name(userTopic)
                .partitions(10)
                .build();
    }


    @Bean
    public ProducerFactory<String, UserDto> userProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS, "userDto:edu.miu.cs590.userservice.dto.UserDto");

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, UserDto> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }
}
