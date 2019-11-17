package Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布/订阅消息
 * 订阅者
 */
public class Consumer {

    public void getMessage() {
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
            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage message = null;
            int num = 0;
            // 接收消息
            while (true) {
                // 此处未接收到消息时会一直等待
                message = (TextMessage) consumer.receive();
                message.acknowledge();
                System.out.print(num++ + ":");
                // 输出消息
                System.out.println(message.getText());
            }
            // 释放资源
//            messageConsumer.close();
//            session.close();
//            connection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Consumer().getMessage();
    }
}
