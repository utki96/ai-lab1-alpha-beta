package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.TreeNode;
import nyu.edu.lab1.minmax.exception.InvalidInputException;

import java.util.Map;

public class TreeValidator {

    /**
     * This method checks all nodes of the Tree. A root node will not have a parent -> (node.getParent() == null)
     * However, the input is invalid if there are more than 1 root nodes
     */
    public static TreeNode validateSingleRootNode(Map<String, TreeNode> nodeMap) throws InvalidInputException {
        TreeNode rootNode = null;
        for (String label : nodeMap.keySet()) {
            System.out.println(nodeMap.get(label).toString());
            if (nodeMap.get(label).getParent() == null) {
                if (rootNode == null) {
                    rootNode = nodeMap.get(label).getParent();
                } else {
                    throw new InvalidInputException("Input creates tree with more than 1 root nodes");
                }
            }
        }
        return rootNode;
    }

    public static void setParentNode(TreeNode childNode, TreeNode parentNode) throws InvalidInputException {
        if (childNode.getParent() != null) {
            throw new InvalidInputException("Cycle Detected in the graph at node: " + childNode.getLabel());  // If a node has more than 1 parent
        }
        childNode.setParent(parentNode);
    }
}
