package testing_files_test.model;

public class CharacterModel {
    private int id;
    private String firstName;
    private String lastName;
    private String pseudonym;
    private int born;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPseudonym() {
        return pseudonym;
    }
    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
    public int getBorn() {
        return born;
    }
    public void setBorn(int born) {
        this.born = born;
    }
}
