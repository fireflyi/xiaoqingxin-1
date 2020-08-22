package service.impl;


import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import service.ZkService;

import java.net.InetAddress;
import java.util.List;
import java.util.Random;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/8/6
 * DESC zk操作实现类
 */
public class ZkServiceImpl implements ZkService {


    private ZkClient zkc;

    private String serverPort;


    private String rootNode = "/gerant/nioServer";


    private String zkPort = 2181+"";

    public static void main(String[] aaa){
        System.out.println("112");
        //ZkClient zkc2 = new ZkClient("11.24.121.237:2181");
        ZkClient zkc2 = new ZkClient("127.0.0.1:2181");
        System.out.println("222");
        List<String> res = zkc2.getChildren("/");
        System.out.println(res+"333");

    }

    public ZkServiceImpl(){
        String localIp;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
            //xxxxxxxx
            //zkc = new ZkClient(localIp+":"+zkPort);
            zkc = new ZkClient("127.0.0.1:"+zkPort);
            //检查root节点
            checkRootNode();

        } catch (Exception e) {
            System.out.println("e->{}"+e.getMessage());
        }

    }

    @Override
    public void checkRootNode() {
        boolean ex = zkc.exists(rootNode);
        if(!ex){
            addNode(rootNode);
        }
    }

    @Override
    public void addNode(String var) {
        zkc.createPersistent(var);
    }

    @Override
    public void addLocalNode(String path, String value) {
        zkc.createEphemeral(path, value);
    }

    @Override
    public void zkSubscribeEvent(String var) {
        zkc.subscribeChildChanges(var, new IZkChildListener() {
            @Override
            public void handleChildChange(String path, List<String> nodes) throws Exception {
                System.out.println("节点发送变化path->{},nodes->{}"+ path+nodes.toString());
            }
        });
    }

    @Override
    public String getOneServer() {
        List<String> cs = getAll();

        Random random = new Random();
        int n = random.nextInt(cs.size());
        String var1 = cs.get(n);
        return var1;
    }

    @Override
    public List<String> getAll() {
        List<String> cs = zkc.getChildren(rootNode);
        return cs;
    }
}
