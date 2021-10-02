package contacts;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Person extends Contact {

    ArrayList<Contact> addContacts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private String surname;
    private String birthDate;
    private String gender;

    Person(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.number;
        this.birthDate = builder.birthDate;
        this.gender = builder.gender;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String number;
        private String birthDate;
        private String gender;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setBirthDate(String bDate) {

            String regex = "\\d{4}-\\d{2}-\\d{2}";

            if (bDate != null && bDate.matches(regex)) {
                this.birthDate = bDate;
            } else if (!bDate.equals("[no data]")){
                System.out.println("Bad birth date!");
                this.birthDate = "[no data]";
            } else {
                this.birthDate = "[no data]";
            }
            return this;
        }

        public Builder setGender(String gender) {

            String regex = "^(M|F){1}$";

            if (gender != null && gender.matches(regex)) {
                this.gender = gender;
            } else if (!gender.equals("[no data]")){
                System.out.println("Bad gender!");
                this.gender = "[no data]";
            } else {
                this.gender = "[no data]";
            }
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            String oneGroup = "\\+?\\w+";
            String oneGroupParentheses = "\\+?\\(\\w+\\)(\\w{2,})*|\\+?\\w+\\(\\w{2,}\\)\\w*";
            String noParentheses = "\\+?\\w+([\\s-]\\w{2,})+";
            String firstGroupParentheses = "\\+?\\(\\w+\\)([\\s-]\\w{2,})+";
            String secondGroupParentheses = "\\+?\\w+[\\s-]\\(\\w{2,}\\)([\\s-]\\w{2,})*";

            String regex = oneGroup + "|" + oneGroupParentheses + "|" +
                    noParentheses + "|" +
                    firstGroupParentheses + "|" + secondGroupParentheses;

            if (phoneNumber != null && phoneNumber.matches(regex)) {
                this.number = phoneNumber;
            } else if (!phoneNumber.equals("[no data]")){
                System.out.println("Wrong number format!");
                this.number = "[no number]";
            } else {
                this.number = "[no data]";
            }
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public Boolean search(String query){
        return this.name.toLowerCase().contains(query.toLowerCase())
                || this.surname.toLowerCase().contains(query.toLowerCase())
                || this.phoneNumber.toLowerCase().contains(query.toLowerCase());
    }

    public Contact add() {

        String name;
        String surname;
        String phoneNumber;
        String birthDate;
        String gender;

        Person.Builder builderPerson = new Person.Builder();

        System.out.println("Enter the name of the person: ");

        name = scanner.nextLine();

        System.out.println("Enter the surname of the person: ");

        surname = scanner.nextLine();

        System.out.println("Enter the birthdate: ");

        birthDate = scanner.nextLine();

        builderPerson.setBirthDate(birthDate);

        System.out.println("Enter the gender (M, F): ");

        gender = scanner.nextLine();

        builderPerson.setGender(gender);

        System.out.println("Enter the number: ");

        phoneNumber = scanner.nextLine();

        Person person = builderPerson
                .setName(name)
                .setSurname(surname)
                .setPhoneNumber(phoneNumber)
                .setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .build();

        System.out.println("The record added. \n");


        return person;

    }

    public Contact edit(){

        int recordNumber = 1;

        if (addContacts.size() > 1){

            System.out.println("Select a record: >");

            String record = scanner.nextLine();

            if (record.matches("[a-zA-Z]+")) {

                System.out.println("No records to edit");
                System.exit(0);
            }

            recordNumber = Integer.parseInt(record);

        }

            String tempName;
            String tempSurname;
            String tempNumber;
            String tempBirthDate;
            String tempGender;

            Person.Builder builderPerson = new Person.Builder();

            tempName = this.getName();
            tempSurname = this.getSurname();
            tempNumber = this.getPhoneNumber();
            tempBirthDate = this.getBirthDate();
            tempGender = this.getGender();

            System.out.println("Select a field (name, surname, birth, gender, number): >");

            String fieldName = scanner.nextLine();

            builderPerson
                    .setName(tempName)
                    .setSurname(tempSurname)
                    .setBirthDate(tempBirthDate)
                    .setGender(tempGender)
                    .setPhoneNumber(tempNumber);

            switch (fieldName) {

                case "name":
                    System.out.println("Enter name: >");
                    tempName = scanner.nextLine();
                    builderPerson.setName(tempName);
                    break;
                case "surname":
                    System.out.println("Enter surname: >");
                    tempSurname = scanner.nextLine();
                    builderPerson.setName(tempSurname);
                    break;
                case "number":
                    System.out.println("Enter number: >");
                    tempNumber = scanner.nextLine();
                    builderPerson.setPhoneNumber(tempNumber);
                    break;
                case "birth":
                    System.out.println("Enter birth date: >");
                    tempBirthDate = scanner.nextLine();
                    builderPerson.setBirthDate(tempBirthDate);
                    break;
                case "gender":
                    System.out.println("Enter gender: >");
                    tempGender = scanner.nextLine();
                    builderPerson.setGender(tempGender);
                    break;
            }

            Person tempPerson = builderPerson
                    .setModifiedAt(LocalDateTime.now())
                    .build();

            //addContacts.set(recordNumber - 1, tempPerson);

            System.out.println("\n");

            return tempPerson;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getDescription() {

        return name + " " + surname;
    }


    @Override
    public String toString() {
        return
                "Name: " + name + '\n' +
                        "Surname: " + surname + '\n' +
                        "Birth date: " + birthDate + '\n' +
                        "Gender: " + gender + '\n' +
                        "Number: " + phoneNumber + '\n' +
                        "Time created: " + createdAt + '\n' +
                        "Time last edit: " + modifiedAt + '\n'

                ;
    }


}


