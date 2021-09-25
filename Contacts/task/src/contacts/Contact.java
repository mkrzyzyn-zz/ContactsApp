package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String name;
    private String surname;
    private String phoneNumber;


    private Contact(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.number;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String number;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setNumber(String phoneNumber) {
            String oneGroup = "\\+?\\w+";
            String oneGroupParentheses = "\\+?\\(\\w+\\)(\\w{2,})*|\\+?\\w+\\(\\w{2,}\\)\\w*";
            String noParentheses = "\\+?\\w+([\\s-]\\w{2,})+";
            String firstGroupParentheses = "\\+?\\(\\w+\\)([\\s-]\\w{2,})+";
            String secondGroupParentheses = "\\+?\\w+[\\s-]\\(\\w{2,}\\)([\\s-]\\w{2,})*";

            String regex = oneGroup + "|" + oneGroupParentheses + "|" +
                    noParentheses + "|" +
                    firstGroupParentheses + "|" + secondGroupParentheses;

            if(phoneNumber.matches(regex) ) {
                this.number = phoneNumber;
            }
            else{
                System.out.println("Wrong number format!");
                this.number = "[no number]";

                // System.exit(0);
            }
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
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

        }
}

