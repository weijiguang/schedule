/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Weir
 */
public class List2TreeSample {

    public List<NodeModel> list = new ArrayList<NodeModel>();

    public void init() {
        NodeModelImpl m1 = new NodeModelImpl();
        m1.setId(new Long(1));
        m1.setParent(new NodeModelImpl());
        list.add(m1);

        NodeModelImpl m2;
        for (int i = 2; i < 5; i++) {
            m2 = new NodeModelImpl();
            m2.setId(new Long(i));
            m2.setParent(m1);
            list.add(m2);
        }

        NodeModelImpl m3 = new NodeModelImpl();
        m3.setId(new Long(10));
        m3.setParent(new NodeModelImpl());
        list.add(m3);

        for (int j = 11; j < 15; j++) {
            m2 = new NodeModelImpl();
            m2.setId(new Long(j));
            m2.setParent(m3);
            list.add(m2);
        }

        NodeModelImpl m4 = new NodeModelImpl();
        m4.setId(new Long(20));
        m4.setParent(new NodeModelImpl());
        list.add(m4);

        for (int j = 21; j < 28; j++) {
            m2 = new NodeModelImpl();
            m2.setId(new Long(j));
            if (j > 24) {
                m2.setParent(new NodeModelImpl(new Long(23)));
            } else {
                m2.setParent(m4);
            }

            list.add(m2);
        }
    }

    public void getTree() {
        NodeModelImpl parent = new NodeModelImpl();
        createTree("", list, parent);
    }

    public void createTree(String node, List<? extends NodeModel> treeElemnts, NodeModel parent) {
        for (NodeModel m : treeElemnts) {
            //获得下级成员
            if (m.getParent().equals(parent)) {
                System.out.println(node + m.getId());
//               将当前成员作为上级成员递归。
                createTree(node + "   ", treeElemnts, m);
            }
        }
    }

    public static void main(String[] args) {
        List2TreeSample t = new List2TreeSample();
        t.init();
        t.getTree();
        
        
//        oracle.jdbc.driver.OracleDriver;
    }
}
