import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/8/16
 * DESC TODO
 */
public class Product {

    public static void  main(String[] a) throws MQClientException {
        DefaultMQProducer dm = new DefaultMQProducer("test-group");
        dm.setNamesrvAddr("127.0.0.1:9876");
        dm.setInstanceName("producer-test");
        dm.start();
        try {
            for (int i = 0; i < 10; i++) {
                Message msg = new Message("producer-topic", // topic
                        "tag-test", // tag自定义可以理解为根据次在进行过滤
                        i+"",
                        ("pushmsg-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//内容
                );
                SendResult sendResult = dm.send(msg);
                System.out.println(sendResult.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dm.shutdown();
    }

}
