package contacts;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Organization extends Contact{

    ArrayList<Contact> addContacts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getDescription(){
        return name;
    }

    private Organization(Builder builder) {
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public Contact add(){

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

        return org;
    }

    public static class Builder {
        private String name;
        private String address;
        private String phoneNumber;
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

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getPhoneNumber() {
            return phoneNumber;
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
                this.phoneNumber = phoneNumber;
            } else if (!phoneNumber.equals("[no data]")){
                System.out.println("Wrong number format!");
                this.phoneNumber = "[no number]";
            } else {
                this.phoneNumber = "[no number]";
            }
            return this;
        }

        public Organization build() {
            return new Organization(this);
        }
    }

    public Boolean search(String query) {
        return this.name.toLowerCase().contains(query.toLowerCase())
    || this.phoneNumber.toLowerCase().contains(query.toLowerCase());
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
        String tempNumber;
        String tempAddress;
        LocalDateTime tempCreatedAt;

        Organization.Builder builderOrganization = new Organization.Builder();

        tempName = this.getName();
        tempNumber = this.getPhoneNumber();
        tempAddress = this.getAddress();
        tempCreatedAt = this.getCreatedAt();

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

        Organization tempOrganization = builderOrganization
                .setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .build();

        System.out.println("\n");

        return tempOrganization;

    }

    @Override
    public String toString() {
        return
                "Organization name: " + name + '\n' +
                        "Address: " + address + '\n' +
                        "Number: " + phoneNumber + '\n' +
                        "Time created: " + createdAt + '\n' +
                        "Time last edit: " + modifiedAt + '\n'
                ;
    }

    }


