package mlb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParticipantParser {

    List<FamilyMember> familyMembers = new ArrayList<>();

    public List<FamilyMember> getParticpants(String filename) {

        try {
            File inputFile = new File(filename);
            if (!inputFile.exists()) {
                throw new FileNotFoundException("File is not found");
            }

            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()) {

                String name = scanner.nextLine();

                if (!name.equals("")) {
                    FamilyMember familyMember = new FamilyMember(name);
                    familyMembers.add(familyMember);
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }


        return familyMembers;

    }

}
