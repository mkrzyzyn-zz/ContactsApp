package contacts;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppContacts {

        ArrayList<Contact> appContacts = new ArrayList<>();
        ArrayList<Contact> searchContacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        public void actions() {

                String action;
                String type;

                while(true) {

                        System.out.println("\n[menu] Enter action (add, edit, list, search, count, exit): ");

                        action = scanner.nextLine();

                        switch (action) {

                                case "add":
                                        System.out.println("Enter the type (person, organization): ");
                                        type = scanner.nextLine();
                                        if (type.equals("person")) {
                                                appContacts.add(new Person.Builder().build().add());
                                        } else if (type.equals("organization")) {
                                                appContacts.add(new Organization.Builder().build().add());
                                        }
                                        break;
                                case "list":
                                        list(appContacts);
                                        break;
                                case "edit":
                                        edit();
                                        break;
                                case "count":
                                        count();
                                        break;
                                case "remove":
                                        remove();
                                        break;
                                case "exit":
                                        System.exit(0);
                                        break;
                                case "menu":
                                        System.out.println("\n");
                                        break;
                                case "search":
                                        search();
                                        actions();
                                        break;

                        }
                }
        }

        private void search() {

                System.out.println("Enter search query: ");

                String query = scanner.nextLine();

                searchContacts = appContacts.stream()
                        .filter(contact -> contact.search(query))
                        .collect(Collectors.toCollection(ArrayList::new));

                System.out.println("Found " + searchContacts.size() + " results:\n");

                for(int i = 0; i< searchContacts.size(); i++) {

                        Contact temp = searchContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

                System.out.println("[search] Enter action ([number], back, again): ");

                String action = scanner.nextLine();

                if (action.matches("\\d+")){

                        int recordNum = Integer.parseInt(action);

                        System.out.println(appContacts.get(recordNum-1).toString());

                        System.out.println("[record] Enter action (edit, delete, menu): ");

                        action = scanner.nextLine();

                        switch (action){

                                case "edit":
                                        edit();
                                        break;
                                case "delete":
                                        remove();
                                        break;
                                case "menu":
                                        actions();

                        }

                } else if (action.equals("back")) {
                        actions();
                } else if (action.equals("again")){
                        search();
                } else System.out.println("\n");

                }




        public void edit(){

                int recordNumber = 1;

                if (appContacts.size() > 1){

                        System.out.println("Select a record: >");

                        String record = scanner.nextLine();

                        if (record.matches("[a-zA-Z]+")) {

                                System.out.println("No records to edit");
                                System.exit(0);
                        }

                        recordNumber = Integer.parseInt(record);

                }

        Contact temp = appContacts.get(recordNumber-1).edit();
                appContacts.set(recordNumber-1,temp);

        }

        public void list(ArrayList<Contact> appContacts) {

                int recordNum;

                for(int i = 0; i< appContacts.size(); i++){

                        Contact temp = appContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

                System.out.println("[list] Enter action ([number], back):");

                String action = scanner.nextLine();

                if (action.matches("\\d+")){

                        recordNum = Integer.parseInt(action);

                        System.out.println(appContacts.get(recordNum-1).toString());

                } else if (action.equals("back")) {

                };
        }

        public void count() {

                System.out.println(appContacts.size() + " records");

        }

        public void remove(){

                for(int i = 0; i< appContacts.size(); i++){

                        Contact temp = appContacts.get(i);

                        System.out.println(i + 1 + ". " + temp.getDescription());
                }

                System.out.println("Select a record: >");

                String record = scanner.nextLine();

                if (record.matches("[a-zA-Z]+")) {

                        System.out.println("No records to remove");
                        System.exit(0);
                }

                int recordNumber = Integer.parseInt(record);

                appContacts.remove(recordNumber-1);

                System.out.println("The record removed!");

        }

        }


