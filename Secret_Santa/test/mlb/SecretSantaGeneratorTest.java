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
                new FamilyMember("Nathan"),
                new FamilyMember("Sally"))
        );

        secretSantaGenerator.generateMatches(familyMembers);

        assertEquals("Must have more than 3 participants to play!", errStream.toString().trim());
    }

    //Testing that 3 participants produces 3 matches
    @Test
    public void threeFamilyMembers() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Nathan"),
                new FamilyMember("Sally"),
                new FamilyMember("Billy"))
        );

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        assertEquals(3, matches.size());
    }

    //Testing many participants (48 to be exact) produces the correct number of matches
    @Test
    public void giantFamily() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(
                new FamilyMember("Nathan"),
                new FamilyMember("Sally"),
                new FamilyMember("Billy"),
                new FamilyMember("Jerry"),
                new FamilyMember("Tim"),
                new FamilyMember("Terry"),
                new FamilyMember("Bill"),
                new FamilyMember("Jack"),
                new FamilyMember("Aaron"),
                new FamilyMember("Erin"),
                new FamilyMember("Damon"),
                new FamilyMember("Craig"),
                new FamilyMember("Dylan"),
                new FamilyMember("Beth"),
                new FamilyMember("Tom"),
                new FamilyMember("Logan"),
                new FamilyMember("Molly"),
                new FamilyMember("Jimmy"),
                new FamilyMember("Phil"),
                new FamilyMember("Kyle"),
                new FamilyMember("Brendan"),
                new FamilyMember("David"),
                new FamilyMember("Jake"),
                new FamilyMember("Nick"),
                new FamilyMember("Mason"),
                new FamilyMember("Max"),
                new FamilyMember("Sandy"),
                new FamilyMember("Haylie"),
                new FamilyMember("Brittany"),
                new FamilyMember("Wylie"),
                new FamilyMember("Lauren"),
                new FamilyMember("Morgan"),
                new FamilyMember("Molly"),
                new FamilyMember("Bella"),
                new FamilyMember("Marissa"),
                new FamilyMember("Jose"),
                new FamilyMember("Carlos"),
                new FamilyMember("Miguel"),
                new FamilyMember("Peter"),
                new FamilyMember("Paul"),
                new FamilyMember("Linda"),
                new FamilyMember("Lonnie"),
                new FamilyMember("Lucy"),
                new FamilyMember("Jen"),
                new FamilyMember("Jackie"),
                new FamilyMember("Joe"),
                new FamilyMember("Karl"),
                new FamilyMember("Rick")
        ));

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        //Asserting number of matches
        assertEquals(48, matches.size());

        //Asserting past santas were assigned
        assertEquals(matches.get(0).receiver.pastSantas[0].getName().trim(), matches.get(0).santa.getName().trim());
    }

}