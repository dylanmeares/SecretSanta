package mlb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParticipantParser {

    //List of family members to return as a result
    List<FamilyMember> familyMembers = new ArrayList<>();

    public List<FamilyMember> getParticpants(String filename) {

        try {
            //Creating the input file and checking to see if it exists. If if doesn't
            //throw an exception
            File inputFile = new File(filename);
            if (!inputFile.exists()) {
                throw new FileNotFoundException("File is not found");
            }

            //Creating Scanner instance to read the file
            Scanner scanner = new Scanner(inputFile);

            //Reading in each line of the file, adding the names to familyMembers
            while (scanner.hasNext()) {

                String name = scanner.nextLine();

                //Ignoring blank lines
                if (!name.equals("")) {
                    FamilyMember familyMember = new FamilyMember(name);
                    familyMembers.add(familyMember);
                }

            }

            //Closing scanner
            scanner.close();

            //File not found exception. When encountered, exit program.
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }

        //Return list
        return familyMembers;

    }

}
