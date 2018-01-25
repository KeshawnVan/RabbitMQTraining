package utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author keshawn
 * @date 2018/1/25
 */
public final class RabbitConnectionUtil {

    private static final String LOCALHOST = "localhost";

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConnectionUtil.class);

    private static final ConnectionFactory CONNECTION_FACTORY = new ConnectionFactory();

    private static Connection CONNECTION = null;

    static {
        try {
            CONNECTION_FACTORY.setHost(LOCALHOST);
            CONNECTION = CONNECTION_FACTORY.newConnection();
        } catch (IOException | TimeoutException e) {
            LOGGER.error("cannot get RabbitMQ connect", e);
            throw new RuntimeException(e);
        }
    }

    private RabbitConnectionUtil() {
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
