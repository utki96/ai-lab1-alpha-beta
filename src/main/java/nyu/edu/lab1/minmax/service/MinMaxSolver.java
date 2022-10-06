package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.TreeNode;

import java.util.*;

public class MinMaxSolver {

    private final String PRINT_NODE_VALUE = "%s(%s) chooses %s for %d\n";
    private final String PRINT_PRUNED_NODE = "%s(%s) is pruned and it's subtrees won't be visited\n";

    private boolean enablePruning;
    private boolean isRootMax;
    private boolean isVerbose;

    public MinMaxSolver(boolean enablePruning, boolean isRootMax, boolean isVerbose) {
        this.enablePruning = enablePruning;
        this.isRootMax = isRootMax;
        this.isVerbose = isVerbose;
    }

    public void executeMinMaxSolver(TreeNode root) {
        int alpha = Integer.MAX_VALUE;
        int beta = Integer.MIN_VALUE;
        if (this.enablePruning) {
            alpha = Integer.MIN_VALUE;
            beta = Integer.MAX_VALUE;
        }
        minMaxSolver(root, this.isRootMax, alpha, beta);
    }

    private Integer minMaxSolver(TreeNode node, boolean isMax, Integer alpha, Integer beta) {
        if (node.getChildren().isEmpty()) {
            return node.getNodeValue();
        }
        int nodeValue = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        node.setIsMax(isMax);
        for (TreeNode child : node.getChildren()) {
            if (alpha > beta && this.enablePruning) {
                break;
            }
            int childValue = minMaxSolver(child, !isMax, alpha, beta);
            if (isMax) {
                if (childValue >= nodeValue) {
                    nodeValue = childValue;
                    node.setNodeValue(nodeValue);
                    node.setMoveNextPath(child);
                }
                alpha = Math.max(alpha, childValue);
            } else {
                if (childValue <= nodeValue) {
                    nodeValue = childValue;
                    node.setNodeValue(nodeValue);
                    node.setMoveNextPath(child);
                }
                beta = Math.min(beta, childValue);
            }
        }
        if (alpha > beta && this.enablePruning) {
            node.setPruned(true);
        }
        printNodeAction(node);
        node.setPruned(false);
        return nodeValue;
    }

    private void printNodeAction(TreeNode node) {
        if (this.isVerbose) {
            if (node.isPruned()) {
                System.out.printf(PRINT_PRUNED_NODE, node.getNodeType(), node.getLabel());
            } else {
                String nextMove = node.getMoveNextPath() != null ? node.getMoveNextPath().getLabel() : null;
                System.out.printf(PRINT_NODE_VALUE, node.getNodeType(), node.getLabel(), nextMove, node.getNodeValue());
            }
        } else {
            if (node.getParents().size() == 0) {
                String nextMove = node.getMoveNextPath() != null ? node.getMoveNextPath().getLabel() : null;
                System.out.printf(PRINT_NODE_VALUE, node.getNodeType(), node.getLabel(), nextMove, node.getNodeValue());
            }
        }
    }
}
