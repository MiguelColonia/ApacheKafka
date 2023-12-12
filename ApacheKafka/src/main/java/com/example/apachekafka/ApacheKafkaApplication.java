package com.example.apachekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ApacheKafkaApplication implements ApplicationRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ApacheKafkaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Aquí puedes colocar la lógica que deseas ejecutar al iniciar la aplicación
    }
}

@Component
class KafkaMessageSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("tutorialspoint", msg);
    }
}

@Component
class KafkaMessageListener {

    @KafkaListener(topics = "tutorialspoint", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Message in group - group-id: " + message);
    }
}
