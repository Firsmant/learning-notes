package Queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点
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
            Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE);
            // 创建队列
            Destination destination = session.createQueue("testQueue");
            // 创建生产者
            MessageProducer producer = session.createProducer(destination);
            // 设置消息
            Message message = session.createTextMessage(content);
            // 发送消息
            producer.send(message);
            // 释放资源
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Producer().sendMessage("Hello World Queue");
    }
}
