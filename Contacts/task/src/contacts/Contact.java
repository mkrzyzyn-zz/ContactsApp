package contacts;

public class Contact {

    private String name;
    private String surname;
    private String phoneNumber;

    Contact(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        if(phoneNumber.matches("^\\(*(\\+*\\w+)\\)*-\\(*(\\w{2,})\\)*"));
            this.phoneNumber = phoneNumber;
    }
}
