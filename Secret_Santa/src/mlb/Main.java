package mlb;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        if(args == null || args.length == 0){
            usage();
            return;
        }

        String filename = args[0];

        //Creating instance of ParticipantParser class
        ParticipantParser participantParser = new ParticipantParser();

        //Calling method to read the input file and return the list
        // of family members to newly created variable
        List<FamilyMember> familyMembers = participantParser.getParticpants(filename);

        //Creating instance of SecretSantaGenerator class
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

        //Calling function to generate Secret Santas
        //Results will be stored in matches variables and then
        //displayed to the console.
        List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

        //Outputting result to console
        for(int i = 0; i < matches.size(); i++){
            System.out.println(matches.get(i).santa.getName() + " is the Secret Santa for " + matches.get(i).receiver.getName());
        }



    }


    private static void usage() {
        System.err.println("Usage: Secret_Santa <filename>");
    }


}

