package mlb;

import java.util.*;

//Created by Dylan Meares

public class Main {

    public static void main(String[] args) {

        //Number of years to run for
        int years = 1;
        //Variable to store family members
        List<FamilyMember> familyMembers = new ArrayList<>();


        //If args is null or there are no args, call usage()
        if(args == null || args.length == 0){
            usage();
            return;
        }

        //If args is greater than 1, check to see what year was included. If second argument is not an integer
        //show message and throw error
        if(args.length > 1) {
            try{
                years = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException nfe){
                System.out.println("The second argument must be a number.");
                usage();
                return;
            }
        }

        //Generating santas for number of years specified
        for(int i = 0; i < years; i++){

            //Only do file processing on first iteration
            if(i == 0) {
                //Set filename to first argument passed
                String filename = args[0];

                //Creating instance of ParticipantParser class
                ParticipantParser participantParser = new ParticipantParser();

                //Calling method to read the input file and return the list
                // of family members to newly created variable
                familyMembers = participantParser.getParticpants(filename);

            }

            //Creating instance of SecretSantaGenerator class
            SecretSantaGenerator secretSantaGenerator = new SecretSantaGenerator();

            //Calling function to generate Secret Santas
            //Results will be stored in matches variables and then
            //displayed to the console.
            List<SantaMatch> matches = secretSantaGenerator.generateMatches(familyMembers);

            //Outputting result to console
            for(int j = 0; j < matches.size(); j++){
                System.out.println(matches.get(j).santa.getFirstName() + " " + matches.get(j).santa.getLastName()
                        + " is the Secret Santa for "
                        + matches.get(j).receiver.getFirstName() + " " + matches.get(j).receiver.getLastName());
            }

        }

    }


    //Message to display to user when wrong arguments are passed
    private static void usage() {
        System.err.println("Usage: Secret_Santa <filename> <# of years to generate>");
    }


}

