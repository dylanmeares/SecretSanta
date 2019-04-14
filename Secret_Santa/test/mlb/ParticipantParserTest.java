package mlb;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParticipantParserTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }


    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
     * TEST FILES
     */

    private static final String FAMILYLIST1 = "Susan" + System.lineSeparator() +  System.lineSeparator() +
            "Dylan" + System.lineSeparator() + System.lineSeparator() + "Logan" + System.lineSeparator();

    private static final String FAMILYLIST2 = "Susan";

    private static final String FAMILYLIST3 = "Susan" + System.lineSeparator() + "Sally" + System.lineSeparator() +
            "Sam" + System.lineSeparator() + "Sandy" + System.lineSeparator() + "Seth" + System.lineSeparator() +
            "Sue" + System.lineSeparator() + "Solomon" + System.lineSeparator() + "Simon" + System.lineSeparator() +
            "Shane" + System.lineSeparator() + "Shelby" + System.lineSeparator() + "Stephany";


    //Testing when file is not found that we get back the appropriate exception
    @Test
    public void fileDoesNotExist() {

        //Creating instance of Participant parser
        ParticipantParser participantParser = new ParticipantParser();

        //Passing a file that does not exist to getParticipants
        participantParser.getParticpants("nosuchfile.txt");

        //Asserting that we get File Not Found exception
        assertEquals("File Not Found", errStream.toString().trim());
    }

    //Testing that ParticipantParse handles blank lines in files correctly (should ignore them)
    @Test
    public void fileContainsBlankLines() throws Exception{
        //Creating input file
        File inputFile = createInputFile(FAMILYLIST1);

        //Creating instance of Participant parser
        ParticipantParser participantParser = new ParticipantParser();

        //Passing the file to getParticpants function, and storing the result as a list of
        //family members
        List<FamilyMember> actual = participantParser.getParticpants(inputFile.getPath());

        //Converting list of family members into list of Strings for comparison
        List<String> actualOutput = new ArrayList<>();

        for(int i = 0; i < actual.size(); i++){

            actualOutput.add(actual.get(i).getName());
        }

        //Expected output as a list of strings
        List<String> expectedOutput = new ArrayList<>(
                Arrays.asList("Susan", "Dylan", "Logan")
        );


        //Comparing expected and actual outputs
        assertEquals(actualOutput, expectedOutput);
    }


    //Testing that ParticipantParse handles one line
    @Test
    public void fileContainsOneLine() throws Exception{
        //Creating input file
        File inputFile = createInputFile(FAMILYLIST2);

        //Creating instance of Participant parser
        ParticipantParser participantParser = new ParticipantParser();

        //Passing the file to getParticpants function, and storing the result as a list of
        //family members
        List<FamilyMember> actual = participantParser.getParticpants(inputFile.getPath());

        //Converting list of family members into list of Strings for comparison
        List<String> actualOutput = new ArrayList<>();

        for(int i = 0; i < actual.size(); i++){

            actualOutput.add(actual.get(i).getName());
        }

        //Expected output as a list of strings
        List<String> expectedOutput = new ArrayList<>(
                Arrays.asList("Susan")
        );


        //Comparing expected and actual outputs
        assertEquals(actualOutput, expectedOutput);
    }


    //Testing that ParticipantParse handles empty file
    @Test
    public void fileIsEmpty() throws Exception{
        //Creating input file
        File inputFile = createTmpFile();

        //Creating instance of Participant parser
        ParticipantParser participantParser = new ParticipantParser();

        //Passing the file to getParticpants function, and storing the result as a list of
        //family members
        List<FamilyMember> actual = participantParser.getParticpants(inputFile.getPath());

        //Converting list of family members into list of Strings for comparison
        List<String> actualOutput = new ArrayList<>();

        for(int i = 0; i < actual.size(); i++){

            actualOutput.add(actual.get(i).getName());
        }

        //Expected output as a list of strings
        List<String> expectedOutput = new ArrayList<>(
                Arrays.asList());


        //Comparing expected and actual outputs
        assertEquals(actualOutput, expectedOutput);
    }


    //Testing that ParticipantParse handles many lines
    @Test
    public void fileContainsManyLines() throws Exception{
        //Creating input file
        File inputFile = createInputFile(FAMILYLIST3);

        //Creating instance of Participant parser
        ParticipantParser participantParser = new ParticipantParser();

        //Passing the file to getParticpants function, and storing the result as a list of
        //family members
        List<FamilyMember> actual = participantParser.getParticpants(inputFile.getPath());

        //Converting list of family members into list of Strings for comparison
        List<String> actualOutput = new ArrayList<>();

        for(int i = 0; i < actual.size(); i++){

            actualOutput.add(actual.get(i).getName());
        }

        //Expected output as a list of strings
        List<String> expectedOutput = new ArrayList<>(
                Arrays.asList("Susan", "Sally" , "Sam" , "Sandy", "Seth" , "Sue",
                        "Solomon", "Simon", "Shane", "Shelby","Stephany")
        );


        //Comparing expected and actual outputs
        assertEquals(actualOutput, expectedOutput);
    }








}