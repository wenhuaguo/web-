package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Acer on 2016/12/28.
 */
public class NodeService {
    private NodeDao nodedao = new NodeDao();
    //根据nodeId查询对应节点是否存在
    public Node findNodeByNodeid(String nodeId) {
        if(StringUtils.isNotEmpty(nodeId) && StringUtils.isNumeric(nodeId)){
            Node node = nodedao.findByNodeId(Integer.valueOf(nodeId));
            if(node != null){
                return node;
            }else {
                throw new ServiceException("该节点不存在");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }
    //根据nodeName查询对应的Node节点是否存在
    public Boolean findNodeByNodeName(String nodeName) {
        Node node = nodedao.findNodeByNodeName(nodeName);
        return node == null;
    }
    //对节点名字进行更新
    public void updateNodeName(String nodeId, String nodeName) {
        if(StringUtils.isNotEmpty(nodeId) && StringUtils.isNumeric(nodeId) ){
            Node node = nodedao.findByNodeId(Integer.valueOf(nodeId));
            if(node != null){
                node.setNodename(nodeName);
                nodedao.update(node);
            }else {
                throw new ServiceException("该节点不存在");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }
    //添加新节点
    public void addNewNode(String newNode) {
        nodedao.addNode(newNode);
    }
    //根据nodeId删除对应的node节点
    public void deleteNodeByNodeId(String nodeid) {
        if(StringUtils.isNotEmpty(nodeid) && StringUtils.isNumeric(nodeid)){
            Node node = nodedao.findByNodeId(Integer.valueOf(nodeid));
            if(node != null && node.getNodecount() == 0){
                nodedao.deleteNode(Integer.valueOf(nodeid));
            }else {
                throw new ServiceException("节点下有帖子不允许删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }
}
