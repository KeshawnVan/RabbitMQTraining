package practice.helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitConnectionUtil;

/**
 * @author keshawn
 * @date 2018/1/25
 */
public class Send {

    private static final String QUEUE_NAME = "hello";
    private static final String HELLO_WORLD = "Hello World";
    private static final String BLANK = "";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = HELLO_WORLD;
        channel.basicPublish(BLANK, QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
