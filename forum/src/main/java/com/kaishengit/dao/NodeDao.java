package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by Acer on 2016/12/20.
 */
public class NodeDao {
    /**
     * 读取数据库中的发布节点
     * @return
     */
    public List<Node> readNode() {
        String sql = "select * from t_node";
        return Dbhelp.query(sql,new BeanListHandler<>(Node.class));
    }

    /**
     * 通过node找到对应的nodeId
     * @param node
     * @return
     */
    public Integer findNodeIdByNode(String node) {
        String sql = "select * from t_node where node = ?";
        return Dbhelp.insert(sql,node);
    }

    /**
     * 根据nodeId查询对应的node节点
     * @param nodeId
     * @return
     */
    public Node findByNodeId(Integer nodeid) {
        String sql = "select * from t_node where id = ?";
        return Dbhelp.query(sql,new BeanHandler<>(Node.class),nodeid);
    }

    /**
     * 用户发新贴需要更新节点对应的帖子的数量
     * @param nodeCount
     */
    public void update(Node node) {
        String sql = "update t_node set nodename=?,nodecount=? where id=?";
        Dbhelp.update(sql,node.getNodename(),node.getNodecount(),node.getId());
    }

    /**
     * 根据nodeId删除对应的节点
     * @param nodeid
     */
    public void deleteNode(Integer nodeid) {
        String sql = "delete from t_node where id = ?";
        Dbhelp.update(sql,nodeid);
    }
    //根据nodeName查询对应的Node是否存在
    public Node findNodeByNodeName(String nodeName) {
        String sql = "select * from t_node where nodename = ?";
        return Dbhelp.query(sql,new BeanHandler<>(Node.class),nodeName);
    }
    //添加新节点
    public void addNode(String newNode) {
        String sql = "insert into t_node(nodename) values(?)";
        Dbhelp.update(sql,newNode);
    }
    //查询某一节点下面的额数量当前页面数据量不正确
//    public int findNodeIdNum(Integer nodeid) {
//        String sql = "select nodenum where id = ?";
//
//    }
}
