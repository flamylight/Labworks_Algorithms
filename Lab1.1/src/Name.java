public enum Name {
    Anna("FEMALE"),
    MARIA("FEMALE"),
    IVAN("MALE"),
    PETER("MALE"),
    VASYA("MALE");

    private String gender;

    Name(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }
}
