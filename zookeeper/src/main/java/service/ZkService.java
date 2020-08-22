package service;

import java.util.List;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/8/6
 * DESC TODO
 */
public interface ZkService {

    /**
     * 检查root节点是否存在，否则创建
     */
    void checkRootNode();

    /**
     * 添加一个节点
     */
    void addNode(String var);

    /**
     * 将本机服务节点加入
     */
    void addLocalNode(String path, String value);

    /**
     * 客户端监听节点
     * @param var
     */
    void zkSubscribeEvent(String var);

    /**
     * 获取一个服务节点
     * @return
     */
    String getOneServer();

    /**
     * 获取所有节点
     * @return
     */
    List<String> getAll();



}
