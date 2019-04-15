package mlb;

//FamilyMember class

public class FamilyMember {

    //Attributes
    String name;
    FamilyMember[] pastSantas = new FamilyMember[3];

    //Constructor
    public FamilyMember(String name) {
        this.name = name;
        pastSantas[0] = new FamilyMember();
        pastSantas[1] = new FamilyMember();
        pastSantas[2] = new FamilyMember( );
    }

    //Constructor
    public FamilyMember() {
        this.name = "";
    }


    //Getters
    public String getName() {
        return name;
    }

    public FamilyMember[] getPastSantas() {
        return pastSantas;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPastSantas(FamilyMember[] pastSantas) {
        this.pastSantas = pastSantas;
    }
}