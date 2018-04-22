package jorda.murria;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class DUTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDUConstructorTooManyArgs() {
        String[] args = {"./Assets/TestDir1/", "fakeDire2", "fakeDire3"};
        new DU(args);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testDUConstructorFakeDir() {
        String[] args = {"/Assets/FakeDir"};
        new DU(args);
    }

    @Test()
    public void testDUConstructorWorks() {
        String[] args = {"./Assets/TestDir1/"};
        new DU(args);
    }

    @Test()
    public void testListSubDirsAndFiles() {
        String[] args = {"./Assets/TestDir2/"};
        new DU(args).listSubDirsAndFiles();

        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }
}