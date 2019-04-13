package mlb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SecretSantaGeneratorTest {

    @Test
    public void shouldRequireMoreThanOneFamilyMember() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        List<FamilyMember> familyMembers = new ArrayList<>();

        assertEquals(0, secretSantaGenerator.generateMatches(familyMembers).size());
    }

    @Test
    public void shouldMatchPeople() {
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        FamilyMember matt = new FamilyMember("Matt");
        FamilyMember dylan = new FamilyMember("Dylan");

        List<FamilyMember> familyMembers = new ArrayList<>();
        familyMembers.add(matt);
        familyMembers.add(dylan);

        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);
        assertEquals(2, matches.size());

    }

}