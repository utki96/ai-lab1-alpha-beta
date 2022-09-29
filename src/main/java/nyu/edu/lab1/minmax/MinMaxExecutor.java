package nyu.edu.lab1.minmax;

import nyu.edu.lab1.minmax.dto.TreeNode;
import nyu.edu.lab1.minmax.service.InputParser;
import nyu.edu.lab1.minmax.service.MinMaxSolver;

import java.util.List;

public class MinMaxExecutor {
    public static void main(String[] args) {
        try {
            InputParser parser = new InputParser("/home/utkarshtyg/Documents/test.txt");
            MinMaxSolver minMax = new MinMaxSolver(false);
            TreeNode root = parser.createTreeFromInputFile();
            minMax.executeMinMaxSolver(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.exit(1);
    }
}