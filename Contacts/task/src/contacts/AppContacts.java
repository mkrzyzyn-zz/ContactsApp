package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class AppContacts {

        ArrayList<Contact> addContacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        public void add(Boolean isPerson){

                String name;
                String surname;
                String phoneNumber;
                String birthDate;
                String gender;
                String address;

                if(isPerson) {

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

                        addContacts.add(person);

                } else {

                        System.out.println("Enter the organization name: ");

                        name = scanner.nextLine();

                        System.out.println("Enter the address: ");

                        address = scanner.nextLine();

                        System.out.println("Enter the number: ");

                        phoneNumber = scanner.nextLine();

                        Organization org = new Organization.Builder()
                                .setName(name)
                                .setAddress(address)
                                .setPhoneNumber(phoneNumber)
                                .setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                                .setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                                .build();

                        addContacts.add(org);

                        System.out.println("The record added. \n");

                        System.out.println(org.getName());

                }


        }

        public void Actions() {

                String action;
                String type;
                Boolean isPerson;

                System.out.println("Enter action (add, remove, edit, count, info, exit): ");

                action = scanner.nextLine();

                switch (action) {

                        case "add":
                                System.out.println("Enter the type (person, organization): ");
                                type = scanner.nextLine();
                                if(type.equals("person")){
                                        isPerson = true;
                                        add(isPerson);
                                }
                                else if (type.equals("organization")){
                                        isPerson = false;
                                        add(isPerson);}
                                Actions();
                                break;
                        case "info":
                                info();
                                Actions();
                                break;
                        case "edit":
                                edit();
                                Actions();
                                break;
                        case "count":
                                count();
                                Actions();
                                break;
                        case "remove":
                                remove();
                                Actions();
                                break;
                        case "exit":
                                System.exit(0);
                                break;

                }
        }

        public void info() {


                int recordNum;

                for(int i = 0; i< addContacts.size(); i++){

                        Contact temp = addContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

                System.out.println("Enter index to show info: ");

                recordNum = Integer.parseInt(scanner.nextLine());

                System.out.println(addContacts.get(recordNum-1).toString());

        }

        public void count() {

                System.out.println(addContacts.size() + " records");



        }

        public void remove(){

                for(int i = 0; i< addContacts.size(); i++){

                        Contact temp = addContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

                System.out.println("Select a record: >");

                String record = scanner.nextLine();

                if (record.matches("[a-zA-Z]+")) {

                        System.out.println("No records to remove");
                        System.exit(0);
                }

                int recordNumber = Integer.parseInt(record);

                addContacts.remove(recordNumber-1);

                System.out.println("The record removed!");

        }

        public void edit() {

                System.out.println("Select a record: >");

                String record = scanner.nextLine();

                if (record.matches("[a-zA-Z]+")) {

                        System.out.println("No records to edit");
                        System.exit(0);
                }

                int recordNumber = Integer.parseInt(record);

                if(addContacts.get(recordNumber - 1) instanceof Person) {

                        String tempName;
                        String tempSurname;
                        String tempNumber;
                        String tempBirthDate;
                        String tempGender;
                        Person.Builder builderPerson = new Person.Builder();

                        Person tempPerson = (Person) addContacts.get(recordNumber - 1);
                        tempName = tempPerson.getName();
                        tempSurname = tempPerson.getSurname();
                        tempNumber = tempPerson.getPhoneNumber();
                        tempBirthDate = tempPerson.getBirthDate();
                        tempGender = tempPerson.getGender();

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

                        tempPerson = builderPerson
                                .setModifiedAt(LocalDateTime.now())
                                .build();

                        addContacts.set(recordNumber - 1, tempPerson);
                }
                else {
                        String tempName;
                        String tempNumber;
                        String tempAddress;
                        LocalDateTime tempCreatedAt;

                        Organization.Builder builderOrganization = new Organization.Builder();

                        Organization tempOrganization = (Organization) addContacts.get(recordNumber - 1);

                        tempName = tempOrganization.getName();
                        tempNumber = tempOrganization.getPhoneNumber();
                        tempAddress = tempOrganization.getAddress();
                        tempCreatedAt = tempOrganization.getCreatedAt();

                        System.out.println("Select a field (name, number, address): >");

                        String fieldName = scanner.nextLine();

                        builderOrganization
                                .setName(tempName)
                                .setAddress(tempAddress)
                                .setPhoneNumber(tempNumber)
                                .setCreatedAt(tempCreatedAt);


                        switch (fieldName) {

                                case "name":
                                        System.out.println("Enter name: >");
                                        tempName = scanner.nextLine();
                                        builderOrganization.setName(tempName);
                                        break;

                                case "number":
                                        System.out.println("Enter number: >");
                                        tempNumber = scanner.nextLine();
                                        builderOrganization.setPhoneNumber(tempNumber);
                                        break;
                                case "address":
                                        System.out.println("Enter address: >");
                                        tempAddress = scanner.nextLine();
                                        builderOrganization.setAddress(tempAddress);
                                        break;
                        }

                       tempOrganization = builderOrganization
                               .setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                                .build();

                        addContacts.set(recordNumber - 1, tempOrganization);


                }

        }

}
