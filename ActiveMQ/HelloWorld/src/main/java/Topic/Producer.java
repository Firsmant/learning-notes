package Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布/订阅消息
 * 消息生产者
 */
public class Producer {

    public void sendMessage(String content) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建 session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建目的地 Topic
            Destination destination = session.createTopic("testTopicHelloWorld");
            // 创建生产者
            MessageProducer producer = session.createProducer(destination);
            // 消息持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 设置消息
            Message message = session.createTextMessage(content);
            // 发送消息
            producer.send(message);
            session.commit();
            // 释放资源
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Producer().sendMessage("Hello World Topic");
    }
}
