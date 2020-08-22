import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/8/16
 * DESC TODO
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("route_group_default2");
        consumer.setInstanceName("route_instance_default");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("mcenter_all_messages_topic", "tagIP");//只获取此topic和tag的信息

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    
                try{
                        MessageExt msg = msgs.get(0);
                        //业务需要做好幂等处理
                        System.out.println("消息id:" + msg.getMsgId() + "---" + new String(msg.getBody()));
                        String msgBody = StringUtils.toEncodedString(msg.getBody(), Charset.forName("utf-8"));
                        ///
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }catch (Exception e){
                    //RECONSUME_LATER返回失败 并且每过几秒会重新获取，重新获取时间会随着失败次数增加
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            }

        });
        consumer.start();
    }

}
