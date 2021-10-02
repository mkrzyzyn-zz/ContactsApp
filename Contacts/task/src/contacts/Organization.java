package contacts;

import java.time.LocalDateTime;

public class Organization extends Contact{

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


