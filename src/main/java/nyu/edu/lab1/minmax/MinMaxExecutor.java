package nyu.edu.lab1.minmax;

import nyu.edu.lab1.minmax.dto.TreeNode;
import nyu.edu.lab1.minmax.exception.InvalidInputException;
import nyu.edu.lab1.minmax.service.InputParser;
import nyu.edu.lab1.minmax.service.MinMaxSolver;

public class MinMaxExecutor {
    public static void main(String[] args) {
        try {
            if (args.length > 4 || args.length < 2) {
                throw new InvalidInputException("Invalid no of arguments provided: " + args.length);
            }
            boolean isVerbose = false, enablePruning = false;
            String filePath = args[args.length - 1];
            boolean isRootMax = isRootMaxRequesed(args[args.length - 2]);
            for (int i = args.length - 3; i >= 0; i--) {
                if (isPruningRequested(args[i])) {
                    enablePruning = true;
                }
                if (isVerboseRequested(args[i])) {
                    isVerbose = true;
                }
            }

            InputParser parser = new InputParser(filePath);
            MinMaxSolver minMax = new MinMaxSolver(enablePruning, isRootMax, isVerbose);
            TreeNode root = parser.createTreeFromInputFile();
            minMax.executeMinMaxSolver(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.exit(1);
    }

    private static boolean isRootMaxRequesed(String config) {
        if (config.equalsIgnoreCase("max")) {
            return true;
        } else if (config.equalsIgnoreCase("min")) {
            return false;
        }
        throw new InvalidInputException("Invalid root node configuration requested: " + config);
    }

    private static boolean isVerboseRequested(String config) {
        return config.equalsIgnoreCase("-v") || config.equalsIgnoreCase("v");
    }

    private static boolean isPruningRequested(String config) {
        return (config.equalsIgnoreCase("-ab") || config.equalsIgnoreCase("ab"));
    }
}