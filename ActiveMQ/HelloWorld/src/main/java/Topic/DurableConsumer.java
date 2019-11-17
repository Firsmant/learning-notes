package Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布/订阅消息
 * 持久化订阅
 */
public class DurableConsumer {

    public void getMessage() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 1. 向服务中注册客户端唯一 ID
            connection.setClientID("Client-001");
            // 启动连接
            connection.start();
            // 创建 session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 2. 创建目的地类型为 Topic
            Topic destination = session.createTopic("testTopicHelloWorld");
            // 3. 创建消费者,传递客户端 ID,可以获取该客户离线时发布到 testTopicHelloWorld 的消息
            MessageConsumer consumer = session.createDurableSubscriber(destination, "Clinet-001");
            TextMessage message = null;
            int num = 0;
            // 接收消息
            // 此处未接收到消息时会一直等待
            message = (TextMessage) consumer.receive();
            System.out.print(num++ + ":");
            // 输出消息
            System.out.println(message.getText());
            session.commit();

            // 释放资源
            consumer.close();
            session.close();
            connection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new DurableConsumer().getMessage();
    }
}
