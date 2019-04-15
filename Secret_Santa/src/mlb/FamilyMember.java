package mlb;

//FamilyMember class

public class FamilyMember {

    //Attributes
    String firstName;
    String lastName;
    FamilyMember[] pastSantas = new FamilyMember[3];

    //Constructor
    public FamilyMember(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        pastSantas[0] = new FamilyMember();
        pastSantas[1] = new FamilyMember();
        pastSantas[2] = new FamilyMember( );
    }

    //Constructor
    public FamilyMember() {
        this.firstName = "";
    }


    //Getters
    public String getFirstName() {
        return firstName;
    }

    public FamilyMember[] getPastSantas() {
        return pastSantas;
    }

    public String getLastName() {
        return lastName;
    }


    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPastSantas(FamilyMember[] pastSantas) {
        this.pastSantas = pastSantas;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}