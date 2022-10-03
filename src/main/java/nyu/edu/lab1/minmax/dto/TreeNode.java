package nyu.edu.lab1.minmax.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public TreeNode(String label) {
        this.label = label;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    private List<TreeNode> children;
    private List<TreeNode> parents;
    private String label;
    private Integer nodeValue;
    private boolean isMax;
    private boolean isPruned;
    private TreeNode moveNextPath;

    public List<TreeNode> getChildren() {
        return children;
    }

    public List<TreeNode> getParents() {
        return parents;
    }

    public void setParents(TreeNode parents) {
        this.parents.add(parents);
    }

    public String getLabel() {
        return label;
    }

    public Integer getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Integer nodeValue) {
        this.nodeValue = nodeValue;
    }

    public void setIsMax(boolean max) {
        isMax = max;
    }

    public String getNodeType() {
        return isMax ? Constants.MAX_NODE : Constants.MIN_NODE;
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

}
