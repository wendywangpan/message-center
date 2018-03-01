package demo.com.example.messagecenter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue queue;
    int dots = 0;
    int count = 0;

    @Scheduled(fixedDelay = 1000,initialDelay =  500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3){
            dots = 1;
        }
        for (int i = 0 ; i < dots; i++){
            builder.append(".");
        }
        builder.append(Integer.toString(++count));
        String message = builder.toString();
        this.rabbitTemplate.convertAndSend(queue.getName(),message);
        System.out.println("[x] Sent '" + message + " '" );
    }
}
