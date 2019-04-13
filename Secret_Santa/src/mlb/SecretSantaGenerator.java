package mlb;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSantaGenerator {

    public List<SantaMatch> generateMatches(List<FamilyMember> familyMembers) {
        System.out.println(System.lineSeparator() + "Generating Secret Santas......" + System.lineSeparator());

        //Copying list of family members into Santas list
        List<FamilyMember> santas = new ArrayList<>(familyMembers);

        shuffleLists(familyMembers, santas);

        //Outputting the lists concurrently by index. Santas are paired with family members bases on the index, since
        //we know that none of the indexes are the same.
        List<SantaMatch> returnValues = new ArrayList<>();
        for(int i = 0; i < santas.size(); i++){
            SantaMatch santaMatch = new SantaMatch();
            santaMatch.buyer = santas.get(i);
            santaMatch.receiver = familyMembers.get(i);
            returnValues.add(santaMatch);
            System.out.println(santas.get(i).name + " is the Secret Santa for " + familyMembers.get(i).name);
        }
        return returnValues;
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
