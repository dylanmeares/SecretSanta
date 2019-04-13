package mlb;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        if(args == null || args.length == 0){
            usage();
            return;
        }

        String filename = args[0];

        //List to hold family members
        List<FamilyMember> familyMembers = new ArrayList<>();

        //Creating instance of ParticipantParser class
        ParticipantParser participantParser = new ParticipantParser();

        //Calling method to read the input file and return the list
        // of family members
        familyMembers = participantParser.getParticpants(filename);

        //Call method to generate Secret Santas
        SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();
        List<SantaMatch> santaMatches = secretSantaGenerator.generateMatches(familyMembers);

    }


    private static void usage() {
        System.err.println("Usage: Secret_Santa <filename>");
    }


}

