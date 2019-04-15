package mlb;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SecretSantaGeneratorTest {

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

    //Test that checks to see if less than 3 participants are in the file,
    //then an error is thrown
    @Test
    public void shouldRequireMoreThanThreeFamilyMembers() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Nathan", "Smith"),
                new FamilyMember("Sally", "Rader"))
        );

        secretSantaGenerator.generateMatches(familyMembers);

        assertEquals("Must have more than 3 participants to play!", errStream.toString().trim());
    }

    //Testing that 3 participants produces 3 matches
    @Test
    public void threeFamilyMembers() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Nathan", "Smith"),
                new FamilyMember("Sally", "Rader"),
                new FamilyMember("Billy", "Beane"))
        );

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        assertEquals(3, matches.size());
    }

    //Testing several members in the family with some different last names
    @Test
    public void normalSizedFamily() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Dylan", "Meares"),
                new FamilyMember("Bob", "Rader"),
                new FamilyMember("Susan", "Rader"),
                new FamilyMember("Billy", "Meares"),
                new FamilyMember("Molly", "Meares"),
                new FamilyMember("Sandy", "Rader"),
                new FamilyMember("Logan", "Meares"),
                new FamilyMember("Cindy", "Meares"),
                new FamilyMember("Paul", "Rader"),
                new FamilyMember("Blue", "Rader"))
        );

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        assertEquals(10, matches.size());
    }



    //Testing many participants (48 to be exact) produces the correct number of matches
    @Test
    public void giantFamily() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Nathan", "Smith"),
                new FamilyMember("Sally", "Rader"),
                new FamilyMember("Billy", "Beane"),
                new FamilyMember("Jerry", "Rader"),
                new FamilyMember("Tim", "Rader"),
                new FamilyMember("Terry", "Smith"),
                new FamilyMember("Bill", "Smith"),
                new FamilyMember("Jack", "Meares"),
                new FamilyMember("Aaron", "Meares"),
                new FamilyMember("Erin", "Meares"),
                new FamilyMember("Damon", "Meares"),
                new FamilyMember("Craig", "Smith"),
                new FamilyMember("Dylan", "Smith"),
                new FamilyMember("Beth","Jackson"),
                new FamilyMember("Tom","Jackson"),
                new FamilyMember("Logan","Jackson"),
                new FamilyMember("Molly","Jackson"),
                new FamilyMember("Jimmy", "Rader"),
                new FamilyMember("Phil", "Burger"),
                new FamilyMember("Kyle", "Burger"),
                new FamilyMember("Brendan", "Burger"),
                new FamilyMember("David", "Burger"),
                new FamilyMember("Jake", "Burger"),
                new FamilyMember("Nick", "Lindor"),
                new FamilyMember("Mason", "Lindor"),
                new FamilyMember("Max", "Lindor"),
                new FamilyMember("Sandy", "Lindor"),
                new FamilyMember("Haylie", "Lindor"),
                new FamilyMember("Brittany", "Roth"),
                new FamilyMember("Wylie", "Roth"),
                new FamilyMember("Lauren", "Roth"),
                new FamilyMember("Morgan", "Roth"),
                new FamilyMember("Molly", "Roth"),
                new FamilyMember("Bella", "Roth"),
                new FamilyMember("Marissa", "Roth"),
                new FamilyMember("Jose", "Roth"),
                new FamilyMember("Carlos", "Roth"),
                new FamilyMember("Miguel", "Roth"),
                new FamilyMember("Peter", "Roth"),
                new FamilyMember("Paul", "Roth"),
                new FamilyMember("Linda", "Roth"),
                new FamilyMember("Lonnie", "Roth"),
                new FamilyMember("Lucy", "Roth"),
                new FamilyMember("Jen", "Brown"),
                new FamilyMember("Jackie", "Brown"),
                new FamilyMember("Joe","Brown"),
                new FamilyMember("Karl", "Brown"),
                new FamilyMember("Rick", "Brown")
        ));

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        //Asserting number of matches
        assertEquals(48, matches.size());

        //Asserting past santas were assigned
        assertEquals(matches.get(0).receiver.pastSantas[0].getFirstName().trim(), matches.get(0).santa.getFirstName().trim());
    }

}