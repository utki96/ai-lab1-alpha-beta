package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.TreeNode;
import nyu.edu.lab1.minmax.exception.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

public class TreeValidator {

    /**
     * This method checks all nodes of the Tree. A root node will not have a parent -> (node.getParent() == null)
     * However, the input is invalid if there are more than 1 root nodes
     */
    public static TreeNode getTreeRootNode(Map<String, TreeNode> nodeMap) throws InvalidInputException {
        List<TreeNode> rootNodes = new ArrayList<>();
        for (String label : nodeMap.keySet()) {
            if (nodeMap.get(label).getParents().size() == 0) {
                rootNodes.add(nodeMap.get(label));
            }
        }
        if (rootNodes.size() == 0) {
            throw new InvalidInputException("No root node found");
        }
        if (rootNodes.size() > 1) {
            String nodes = rootNodes.stream().map(node -> "\"" + node.getLabel() + "\" and ").collect(Collectors.joining());
            throw new InvalidInputException("Multiple roots: " + nodes.substring(0, nodes.lastIndexOf("and")).trim());
        }
        return rootNodes.get(0);
    }

    public static void validateTree(TreeNode root) {
        Set<TreeNode> visitedNodes = new HashSet<>(), recStack = new HashSet<>();
        if (isCycleInTree(root, visitedNodes, recStack)) {
            throw new InvalidInputException("Cycle detected in tree");
        }
    }

    private static boolean isCycleInTree(TreeNode node, Set<TreeNode> visitedNodes, Set<TreeNode> recStack) {
        if (recStack.contains(node)) {
            return true;
        }
        if (visitedNodes.contains(node)) {
            return false;
        }
        isValidNode(node);      // validating the node
        visitedNodes.add(node);
        recStack.add(node);
        for (TreeNode child : node.getChildren()) {
            if (isCycleInTree(child, visitedNodes, recStack)) {
                return true;
            }
        }
        recStack.remove(node);
        return false;
    }

    private static void isValidNode(TreeNode node) throws InvalidInputException {
        if (node.getChildren().size() == 0) {       // leaf nodes should have a value
            if (node.getNodeValue() == null) {
                String parent = node.getParents().size() > 0 ? node.getParents().get(0).getLabel() : "none";
                throw new InvalidInputException(String.format("Child node \"%s\" of \"%s\" not found",
                        node.getLabel(), parent));
            }
        } else {            // Intermediate nodes should not have a value
            if (node.getNodeValue() != null) {
                throw new InvalidInputException(String.format("Intermediate node \"%s\" should not have the value: \"%s\"",
                        node.getLabel(), node.getNodeValue()));
            }
        }
    }
}
