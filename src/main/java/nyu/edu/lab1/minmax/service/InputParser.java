package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.Constants;
import nyu.edu.lab1.minmax.dto.TreeNode;
import nyu.edu.lab1.minmax.exception.InvalidInputException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {

    private String filePath;

    public InputParser(String filePath) {
        this.filePath = filePath;
    }

    public TreeNode createTreeFromInputFile() throws InvalidInputException {
        List<String> instructions = InputFileReader.readInputFile(this.filePath);
        Map<String, TreeNode> nodeMap = new HashMap<>();
        for (String instruction : instructions) {
            System.out.println(instruction);
            processInputLine(instruction, nodeMap);
        }
        return TreeValidator.validateSingleRootNode(nodeMap);
    }

    private void processInputLine(String input, Map<String, TreeNode> nodeMap) throws InvalidInputException {
        boolean isLeaf = input.contains("="), isNode = input.contains(":");
        if (isLeaf && isNode) {
            throw new InvalidInputException("An input line cannot be both an intermediate and leaf node");
        }
        if (! isLeaf && ! isNode) {
            throw new InvalidInputException("An input line has to be either an intermediate or leaf node");
        }
        if (isNode) {
            processNodeInput(input, nodeMap);
        } else {
            processLeafInput(input, nodeMap);
        }
    }

    private void processNodeInput(String input, Map<String, TreeNode> nodeMap) throws InvalidInputException {
        int parentDelimIndex = input.indexOf(Constants.PARENT_NODE_DELIMITER);
        if (parentDelimIndex < 0) {
            throw new InvalidInputException("Invalid input for intermediate node: " + input);
        }

        String parentLabel = input.substring(0, parentDelimIndex).trim();
        TreeNode parent;
        if (nodeMap.containsKey(parentLabel)) {
            parent = nodeMap.get(parentLabel);
        } else {
            parent = new TreeNode(parentLabel);
            nodeMap.put(parentLabel, parent);
        }

        int childStartIndex = input.indexOf(Constants.CHILD_START_INPUT), childEndIndex = input.indexOf(Constants.CHILD_END_INPUT);
        if (childStartIndex < 0 || childEndIndex < 0 || childEndIndex < childStartIndex) {
            throw new InvalidInputException("Invalid input for intermediate node: " + input);
        }

        String[] children = input.substring(childStartIndex + 1, childEndIndex).trim().split(Constants.CHILDREN_DELIMITER);
        if (children.length == 0) {
            throw new InvalidInputException("No child node provided for intermediate node: " + input);
        }

        for (String childLabel : children) {
            childLabel = childLabel.trim();
            TreeNode childNode;
            if (nodeMap.containsKey(childLabel)) {
                childNode = nodeMap.get(childLabel);
            } else {
                childNode = new TreeNode(childLabel);
                nodeMap.put(childLabel, childNode);
            }

            System.out.println(parent.getLabel() + " -> " + childNode.getLabel());

            parent.getChildren().add(childNode);
            TreeValidator.setParentNode(childNode, parent);
        }
    }

    private void processLeafInput(String input, Map<String, TreeNode> nodeMap) throws InvalidInputException {
        String[] leafInput = input.split(Constants.LEAF_NODE_DELIMITER);
        if (leafInput.length != 2) {
            throw new InvalidInputException("Invalid input format for leaf node. Key Value pair separated by '=' expected.");
        }
        String label = leafInput[0].trim();
        if (! nodeMap.containsKey(label)) {
            throw new InvalidInputException("Leaf node: " + label + " is not part of tree but is being assigned a value");
        }
        Integer value = null;
        try {
            value = Integer.parseInt(leafInput[1].trim());
        } catch (Exception ex) {
            throw new InvalidInputException("Invalid value provided for Leaf node: " + label);
        }
        nodeMap.get(label).setNodeValue(value);
    }
}
