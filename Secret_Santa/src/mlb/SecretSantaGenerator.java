package mlb;

import java.util.*;

//This class is responsible for creating the Santa matches. It returns a list of SantaMatch objects
public class SecretSantaGenerator {

    public List<SantaMatch> generateMatches(List<FamilyMember> familyMembers) {

        //Call the method to validate that there are 3 or more participants
        validateList(familyMembers);

        System.out.println(System.lineSeparator() + "Generating Secret Santas......" + System.lineSeparator());

        //Copying list of family members into Santas list
        List<FamilyMember> santas = new ArrayList<>(familyMembers);

        //Rotate past Santas of family members
        for(int index = 0; index < familyMembers.size(); index++) {
            familyMembers.get(index).pastSantas[2] = familyMembers.get(index).pastSantas[1];
            familyMembers.get(index).pastSantas[1] = familyMembers.get(index).pastSantas[0];
        }

        //Shuffling family members and santas lists until none of the indexes match (i.e. we know that
        // no one is their own santa), and also that no on has one of their past 3 santas
        shuffleLists(familyMembers, santas);

        //Outputting the lists concurrently by index. Santas are paired with family members based on the index, since
        //we know that none of the indexes are the same.
        List<SantaMatch> matches = new ArrayList<>();
        for (int i = 0; i < santas.size(); i++) {
            //Create the santa match
            SantaMatch santaMatch = new SantaMatch();
            //Add the index from the santas list as the santa to the match
            santaMatch.santa = santas.get(i);
            //Add the corresponding index from the family members list as the receiver to the match
            santaMatch.receiver = familyMembers.get(i);
            //Add this santa as a past Santa for this family member
            familyMembers.get(i).pastSantas[0] = santas.get(i);
            //Add this match to the list of Santa matches to return
            matches.add(santaMatch);
        }
        return matches;
    }

    //Method to validate that the list has at least 3 participants. If not, throw an error and
    //exit the program
    private void validateList(List<FamilyMember> familyMembers) {
        if (familyMembers.size() < 3) {
            System.err.println("Must have more than 3 participants to play!");
        }
    }

    //Method to shuffle both Santas list and Family members list until none of the indexes match between the lists
    private void shuffleLists(List<FamilyMember> familyMembers, List<FamilyMember> santas) {
        boolean possibleToSeparateFamilies = true;
        //Seeing if it's possible to have immediate family constraint
        int highestFrequency = countFrequencies(familyMembers);{
            if(highestFrequency > (familyMembers.size() / 2)){
                possibleToSeparateFamilies = false;
            }
        }

        //Flag to keep track of if indexes are unique
        boolean unique = false;
        //Flag to keep track of if a santa is from previous 3 years
        boolean noPastSantas = false;
        //Flag to keep track of if a santa is from same immediate family
        boolean notImmediateFamily = false;
        //Shuffle the lists until the indexes of the list are all unique
        while (!unique || !noPastSantas || !notImmediateFamily) {
            Collections.shuffle(santas);
            Collections.shuffle(familyMembers);
            unique = true;
            noPastSantas = true;
            notImmediateFamily = true;
            //For each family member, check to see if the corresponding index in the santas list is themselves, in their immediate family, and also
            // if their santa  is one of their past 3 santas.
            for (int i = 0; i < familyMembers.size(); i++) {
                //Checking to see if indexes are unique, if not set flag to false
                if (santas.get(i).getFirstName().equals(familyMembers.get(i).getFirstName())) {
                    unique = false;
                    break;
                }
                //Checking to see if matches are from same immediate family
                if(santas.get(i).getLastName().equals(familyMembers.get(i).getLastName()) && possibleToSeparateFamilies){
                    notImmediateFamily = false;
                    break;
                }
                //Checking to see that matches are not one of the previous 3 santas
                FamilyMember[] pastSantas = familyMembers.get(i).getPastSantas();
                for (int j = 0; j < pastSantas.length; j++) {
                    if (santas.get(i).getFirstName().equals(pastSantas[j].getFirstName())) {
                        noPastSantas = false;
                        break;
                    }
                }

            }
        }
    }

    //Counting instances of each last name in the list
    private int countFrequencies(List<FamilyMember> familyMembers) {
        int highestFrequency = 0;

        TreeMap<String, Integer> tmap = new TreeMap<>();
        for (FamilyMember familyMember : familyMembers) {
            Integer c = tmap.get(familyMember.getLastName());
            tmap.put(familyMember.getLastName(), (c == null) ? 1 : c + 1);
        }

        for (Map.Entry m : tmap.entrySet()){
            if(Integer.parseInt(m.getValue().toString()) > highestFrequency){
                highestFrequency = Integer.parseInt(m.getValue().toString());
            }
        }

        return highestFrequency;
    }
}

