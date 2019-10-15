import javafx.scene.effect.Light;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public void sendMessage(String content) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建 session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建队列
            Destination destination = session.createQueue("testHelloWorld");
            // 创建生产者
            MessageProducer messageProducer = session.createProducer(destination);
            // 设置消息
            Message message = session.createTextMessage(content);
            // 发送消息
            messageProducer.send(message);
            // 释放资源
            messageProducer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Producer().sendMessage("Hello World");
    }
}
