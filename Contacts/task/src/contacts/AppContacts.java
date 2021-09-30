package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class AppContacts {

        ArrayList<Contact> addContacts = new  ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        public void add(Boolean isPerson){

                String name;
                String surname;
                String phoneNumber;
                String birthDate;
                String gender;
                String address;

                if(isPerson) {

                        System.out.println("Enter the name of the person: ");

                        name = scanner.nextLine();

                        System.out.println("Enter the surname of the person: ");

                        surname = scanner.nextLine();

                        System.out.println("Enter the birthdate: ");

                        birthDate = scanner.nextLine();

                        System.out.println("Enter the gender (M,F): ");

                        gender = scanner.nextLine();

                        System.out.println("Enter the number: ");

                        phoneNumber = scanner.nextLine();

                        Person person = new Person.Builder()
                                .setName(name)
                                .setSurname(surname)
                                .setBirthDate(birthDate)
                                .setGender(gender)
                                .setPhoneNumber(phoneNumber)
                                .build();

                        System.out.println("A record created!\n" +
                                "A Phone Book with a single record created!");

                        addContacts.add(person);

                } else{

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
                                .build();


                        addContacts.add(org);

                        System.out.println("The record added.");
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
                                        add(true);
                                }
                                else if (type.equals("org")){
                                        add(false);}
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

                for(int i = 0; i< addContacts.size(); i++){

                        Contact temp = addContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

        }

        public void count() {

                System.out.println(addContacts.size() + " records");



        }

        public void remove(){

                info();

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

                String tempName;
                String tempSurname;
                String tempNumber;

                System.out.println("Select a record: >");

                String record = scanner.nextLine();

                if (record.matches("[a-zA-Z]+")) {

                        System.out.println("No records to edit");
                        System.exit(0);
                }

                int recordNumber = Integer.parseInt(record);

                Contact tempPerson = addContacts.get(recordNumber - 1);
                tempName = tempPerson.getName();
                tempSurname = tempPerson.getSurname();
                tempNumber = tempPerson.getPhoneNumber();


                System.out.println("Select a field (name, surname, number): >");

                String fieldName = scanner.nextLine();

                switch(fieldName){

                        case "name":
                                System.out.println("Enter name: >");
                                tempName = scanner.nextLine();
                                tempPerson.setName(tempName);
                                break;
                        case "surname":
                                System.out.println("Enter surname: >");
                                tempSurname = scanner.nextLine();
                                tempPerson.setName(tempSurname);
                                break;
                        case "number":
                                System.out.println("Enter number: >");
                                tempNumber = scanner.nextLine();
                                tempPerson.setPhoneNumber(tempNumber);
                                break;
                }

                        tempPerson = new Person.Builder()
                        .setName(tempName)
                        .setSurname(tempSurname)
                        .setPhoneNumber(tempNumber)
                        .build();

                addContacts.set(recordNumber-1, tempPerson);


        }

}
