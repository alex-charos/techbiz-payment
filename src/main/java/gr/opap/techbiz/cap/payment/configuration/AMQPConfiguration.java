package gr.opap.techbiz.cap.payment.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Declarables antifraudConfig(){
        Exchange antifraudExchange = ExchangeBuilder
                                        .topicExchange("paymentCheckExchange")
                                        .durable(true)
                                    .build();

        Queue successResponseQueue = QueueBuilder
                                        .durable("successCheck")
                                    .build();

        Queue failureResponseQueue = QueueBuilder
            .durable("failureCheck")
            .build();

        TopicExchange resultsPublished = ExchangeBuilder.topicExchange("antifraudResults").build();
        Binding success = BindingBuilder.bind(successResponseQueue).to(resultsPublished).with("success");
        Binding failure = BindingBuilder.bind(failureResponseQueue).to(resultsPublished).with("failure");


        return  new Declarables(antifraudExchange,successResponseQueue,success,failure, successResponseQueue,failureResponseQueue );


    }

}
