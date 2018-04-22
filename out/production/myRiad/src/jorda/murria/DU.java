package jorda.murria;

import java.io.File;
import java.util.*;

public class DU {

    public static void main(String[] args) {

        new DU(args).listSubDirsAndFiles();
    }

    private File mAbsoluteFilePath;

    public DU(String[] args)
    {
        mAbsoluteFilePath = this.parseAbsolutePath(args);
    }

    /**
     * Parse input arguments
     *
     * @param args CLI arguments used to call the DU java class.
     * @return FIle instance pointing to a directory path passed as a parameter.
     * @throws IllegalArgumentException if file path is not provided or doesn't exist.
     */
    private File parseAbsolutePath(String[] args) throws IllegalArgumentException
    {
        if (args.length != 1)
            throw new IllegalArgumentException("CLI args don't match specifications," +
                    " please provide ONE arg with a valid absolute directory path");

        String absoluteFilePath = args[0];
        File dirPath = new File(args[0]);

        if (!dirPath.isDirectory())
            throw new IllegalArgumentException("The CLI Arg passed: (" + absoluteFilePath + ") is not a valid directory.");

        return dirPath;
    }

    /**
     * Using the member variable mAbsoluteFilePath as an absolute path to a valid directory on you system,
     * it prints out on the console the 1st level of files/subdirectories with its size.
     */
    public ArrayList<String> listSubDirsAndFiles()
    {
        Map<String,Integer> filesData = new HashMap<>();

        File[] directories = this.mAbsoluteFilePath.listFiles();

        for (File dir : Objects.requireNonNull(directories)) {
            String prefix = dir.isDirectory()? "DIR " : "FILE ";
            filesData.put(prefix + dir.getAbsolutePath(),(int)dir.length());
        }

        ArrayList<String> outPutResult = new ArrayList<>();

        filesData.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(v -> {
                    String result = v.getKey() + " (" + v.getValue() + ")";
                    outPutResult.add(result);
                    System.out.println(result);
                });

        return outPutResult;
    }
}
