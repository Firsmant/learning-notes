package Queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点对点
 * 消费者
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
            Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE);
            // 创建目的地 Queue
            Destination destination = session.createQueue("testQueue");
            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage message = (TextMessage) consumer.receive();
            int num = 0;
            // 接收消息
            System.out.print(num++ + ":");
            // 输出消息
            System.out.println(message.getText());
            message.acknowledge();
            // 释放资源
            consumer.close();
            session.close();
            connection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Consumer().getMessage();
    }
}
