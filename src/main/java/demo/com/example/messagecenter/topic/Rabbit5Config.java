package demo.com.example.messagecenter.topic;

import demo.com.example.messagecenter.Receiver;
import demo.com.example.messagecenter.Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rabbit5","topic"})
@Configuration
public class Rabbit5Config {
    @Bean
    public TopicExchange topic(){
        return new TopicExchange("rabbit.topic");
    }
    @Profile("receiver")
    private static class ReceiverConfig{
        @Bean
        public Receiver5 receiver(){
            return new Receiver5();
        }
        @Bean
        public Queue autoDeleteQueue1(){
            return new AnonymousQueue();
        }
        @Bean
        public Queue autoDeleteQueue2(){
            return new AnonymousQueue();
        }
        @Bean
        public Binding binding1a(TopicExchange topic,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*");
        }
        @Bean
        public Binding binding1b(TopicExchange topic,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit");
        }
        @Bean
        public Binding binding2a(TopicExchange topic,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
        }
    }
    @Profile("sender")
    @Bean
    public Sender5 sender(){
        return new Sender5();
    }
}
