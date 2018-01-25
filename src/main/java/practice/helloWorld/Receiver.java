package practice.helloWorld;

import com.rabbitmq.client.*;
import utils.RabbitConnectionUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author keshawn
 * @date 2018/1/25
 */
public class Receiver {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = buildConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static Consumer buildConsumer(Channel channel) throws UnsupportedEncodingException {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
    }
}
