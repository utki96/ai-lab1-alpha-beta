package nyu.edu.lab1.minmax.service;

import nyu.edu.lab1.minmax.dto.Constants;
import nyu.edu.lab1.minmax.exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    public static List<String> readInputFile(String filePath) throws InvalidInputException {
        List<String> internalNodeInstr = new ArrayList<>(), leafNodeInstr = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                if (line.contains(Constants.LEAF_NODE_DELIMITER)) {
                    leafNodeInstr.add(line.trim());
                } else {
                    internalNodeInstr.add(line.trim());
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            throw new InvalidInputException("Invalid path for file: " + filePath + ". Error msg: " + fnfEx.getMessage());
        } catch (Exception ex) {
            throw new InvalidInputException("Failed to read Input File. Error msg: " + ex.getMessage());
        }

        internalNodeInstr.addAll(leafNodeInstr);
        return internalNodeInstr;
    }
}
