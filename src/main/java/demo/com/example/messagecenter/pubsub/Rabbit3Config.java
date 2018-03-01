package demo.com.example.messagecenter.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rabbit3","pub-sub","publish-subscribe"})
@Configuration
public class Rabbit3Config {
    @Bean
    public FanoutExchange fanout(){
        return new FanoutExchange("rabbit.fanout");
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
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }
        @Bean
        public Binding binding2(FanoutExchange fanout,Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }
        @Bean
        public Receiver3 receiver(){
            return new Receiver3();
        }
    }
    @Profile("sender")
    @Bean
    public Sender3 sender(){
        return new Sender3();
    }
}
