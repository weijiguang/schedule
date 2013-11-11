/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytest;

import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Weir
 */
//@Named
//@Scope("view")
@ManagedBean
@ViewScoped
public class TreeBean {

    private TreeElement<String> root;
    private HashSet<TreeElement<String>> selected;

    @PostConstruct
    public void init() {
//        if (root == null) {
//            TreeElement<String> branch = new TreeElement<String>(false, "First branch");
//
//            for (int i = 0; i < 5; i++) {
//                TreeElement<String> leaf = new TreeElement<String>(true, "Leaf " + (i + 1));
//                branch.addChild(i, leaf);
//            }
//
//            root = new TreeElement<String>();
//            root.addChild(0, branch);
//            TreeElement<String> bra = new TreeElement<String>(false, "Second branch");
//            for (int i = 0; i < 3; i++) {
//                bra.addChild(i, new TreeElement(true,"我来也..."));
//            }
//            branch.addChild(-1, bra);
//            root.addChild(2, new TreeElement<String>(true, "Leaf directly linked to the root"));
//        }
    }

    public TreeElement<String> getRoot() {
        return root;
    }

    public HashSet<TreeElement<String>> getSelected() {
        return selected;
    }

    public void setSelected(HashSet<TreeElement<String>> selected) {
        this.selected = selected;
    }

    public TreeElement<String> getSingleSelected() {
        if (!selected.isEmpty()) {
            return selected.iterator().next();
        }

        return null;
    }
}
