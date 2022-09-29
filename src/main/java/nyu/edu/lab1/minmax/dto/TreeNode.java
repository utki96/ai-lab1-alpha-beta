package nyu.edu.lab1.minmax.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public TreeNode(String label) {
        this.label = label;
        this.children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                " label='" + label + '\'' +
                ", parent=" + parent +
                ", nodeValue=" + nodeValue +
                ", isPruned=" + isPruned +
                '}';
    }

    private List<TreeNode> children;
    private TreeNode parent;
    private String label;
    private Integer nodeValue;
    private boolean isPruned;
    private TreeNode moveNextPath;
    private Integer moveValue;

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Integer nodeValue) {
        this.nodeValue = nodeValue;
    }

    public boolean isPruned() {
        return isPruned;
    }

    public void setPruned(boolean pruned) {
        isPruned = pruned;
    }

    public TreeNode getMoveNextPath() {
        return moveNextPath;
    }

    public void setMoveNextPath(TreeNode moveNextPath) {
        this.moveNextPath = moveNextPath;
    }

    public Integer getMoveValue() {
        return moveValue;
    }

    public void setMoveValue(Integer moveValue) {
        this.moveValue = moveValue;
    }
}
