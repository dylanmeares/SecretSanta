package mlb;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSantaGenerator {

    public List<SantaMatch> generateMatches(List<FamilyMember> familyMembers) {

        validateList(familyMembers);

        System.out.println(System.lineSeparator() + "Generating Secret Santas......" + System.lineSeparator());

        //Copying list of family members into Santas list
        List<FamilyMember> santas = new ArrayList<>(familyMembers);

        //Shuffling family members and santas lists until none of the indexes match (i.e. we know that
        // no one is their own santa)
        shuffleLists(familyMembers, santas);

        //Outputting the lists concurrently by index. Santas are paired with family members based on the index, since
        //we know that none of the indexes are the same.
        List<SantaMatch> matches = new ArrayList<>();
        for(int i = 0; i < santas.size(); i++){
            SantaMatch santaMatch = new SantaMatch();
            santaMatch.santa = santas.get(i);
            santaMatch.receiver = familyMembers.get(i);
            matches.add(santaMatch);
        }
        return matches;
    }

    private void validateList(List<FamilyMember> familyMembers) {
        if(familyMembers.size() < 3){
            System.err.println("Must have more than 3 participants to play!");
            System.exit(0);
        }
    }

    private void shuffleLists(List<FamilyMember> familyMembers, List<FamilyMember> santas) {
        //Shuffling both Santas list and Family members list until none of the indexes match between the lists
        boolean unique = false;
        while(unique == false){
            Collections.shuffle(santas);
            Collections.shuffle(familyMembers);
            unique = true;
            for(int i = 0; i < santas.size(); i++){
                if(santas.get(i).name == familyMembers.get(i).name){
                    unique = false;
                }
            }
        }
    }
}

