package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.TreeNode;

public class MinMaxSolver {

    private boolean enablePruning;

    public MinMaxSolver(boolean enablePruning) {
        this.enablePruning = enablePruning;
    }

    public void executeMinMaxSolver(TreeNode root) {
        int alpha = Integer.MAX_VALUE;
        int beta = Integer.MIN_VALUE;
        if (this.enablePruning) {
            alpha = Integer.MIN_VALUE;
            beta = Integer.MAX_VALUE;
        }
        minMaxSolver(root, alpha, beta);
    }

    private void minMaxSolver(TreeNode root, Integer alpha, Integer beta) {
        // TODO: Implement
    }
}
