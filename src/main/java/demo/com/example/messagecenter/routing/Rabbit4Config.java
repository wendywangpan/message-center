package demo.com.example.messagecenter.routing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rabbit4","routing"})
@Configuration
public class Rabbit4Config {
    @Bean
    public DirectExchange direct(){
        return new DirectExchange("rabbit.direct");
    }
    @Profile("receiver")
    private static class ReceiverConfig{
        @Bean
        public Queue autoDeleteQueue1(){
            return new AnonymousQueue();
        }
        @Bean
        public Queue autoDeleteQueue2(){
            return new AnonymousQueue();
        }
        @Bean
        public Binding binding1a(DirectExchange direct,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
        }
        @Bean Binding binding1b(DirectExchange direct,Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
        }
        @Bean
        public Binding binding2a(DirectExchange direct,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
        }
        @Bean
        public Binding binding2b(DirectExchange direct,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
        }
        @Bean
        public Receiver4 receiver(){
            return new Receiver4();
        }
    }
    @Profile("sender")
    @Bean
    public Sender4 sender(){
        return new Sender4();
    }
}
