package jorda.murria;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DUTest {

    @Test(expected = IllegalArgumentException.class)
    public void testDUConstructorTooManyArgs() {
        String[] args = {"Assets/TestDir1/", "fakeDire2", "fakeDire3"};
        new DU(args);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testDUConstructorFakeDir() {
        String[] args = {"Assets/FakeDir"};
        new DU(args);
    }

    @Test()
    public void testDUConstructorWorks() {
        String[] args = {"Assets/TestDir1/"};
        new DU(args);
    }

    @Test()
    public void testListSubDirsAndFiles() {
        String[] args = {"Assets/TestDir1/"};
        ArrayList<String> expectedResult = new ArrayList<>();

        expectedResult.add("DIR Assets/TestDir1/dir1 (4096)");
        expectedResult.add("FILE Assets/TestDir1/file6.dat (850)");
        expectedResult.add("FILE Assets/TestDir1/file1.dat (600)");

        ArrayList<String> result = new DU(args).listSubDirsAndFiles();

        compareArrayList(expectedResult, result);
    }

    @Test()
    public void testListSubDirsAndFiles2() {
        String[] args = {"Assets/TestDir2/"};
        ArrayList<String> expectedResult = new ArrayList<>();

        expectedResult.add("DIR Assets/TestDir2/dir1 (4096)");
        expectedResult.add("FILE Assets/TestDir2/file1.dat (600)");

        ArrayList<String> result = new DU(args).listSubDirsAndFiles();

        compareArrayList(expectedResult, result);
    }

    private void compareArrayList(ArrayList<String> expectedResult, ArrayList<String> result) {
        if (result.size() != expectedResult.size())
            fail("Test failed, expected array size is different from the received one.");
        else
        {
            int size = expectedResult.size();

            for(int i=0; i< size; i++)
            {
                String[] expectedArr = expectedResult.get(i).split(" ");
                String[] resultArr = result.get(i).split(" ");
                int restArrSize = resultArr.length-1;

                if(!expectedArr[0].equals(resultArr[0])
                        || !expectedArr[2].equals(resultArr[restArrSize])
                        || !resultArr[restArrSize-1].contains(expectedArr[1])) {
                    fail("Returned result is different from the expected one");
                }
            }
        }
    }
}