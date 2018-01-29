package practice.workQueues;

import com.rabbitmq.client.*;
import utils.RabbitConnectionUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author keshawn
 * @date 2018/1/26
 */
public class Worker {

    private static final String NEW_TASK = "New Task";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;
        channel.queueDeclare(NEW_TASK, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = buildConsumer(channel);
        boolean autoAck = true;
        channel.basicConsume(NEW_TASK, autoAck, consumer);

    }

    private static Consumer buildConsumer(Channel channel) throws UnsupportedEncodingException {
        return new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
