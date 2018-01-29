package practice.workQueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitConnectionUtil;

/**
 * @author keshawn
 * @date 2018/1/26
 */
public class NewTask {

    private static final String NEW_TASK = "New Task";
    private static final String BLANK = "";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;
        channel.queueDeclare(NEW_TASK, durable, false, false, null);
        String message = getMessage(args);
        channel.basicPublish(BLANK, NEW_TASK, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello World!";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
