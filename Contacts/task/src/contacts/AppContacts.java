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

        public void Actions() {

                String action;
                String type;
                Boolean isPerson;

                System.out.println("Enter action (add, remove, edit, count, list, search, exit): ");

                action = scanner.nextLine();

                switch (action) {

                        case "add":
                                System.out.println("Enter the type (person, organization): ");
                                type = scanner.nextLine();
                                if(type.equals("person")){
                                       addContacts.add(new Person.Builder().build().add()) ;
                                }
                                else if (type.equals("organization")){
                                        addContacts.add(new Organization.Builder().build().add()) ;
                                }
                                Actions();
                                break;
                        case "list":
                                list();
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

        public void edit(){

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

        Contact temp = addContacts.get(recordNumber-1).edit();
                addContacts.set(recordNumber-1,temp);

        }

        public void list() {

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

        }


