package contacts;

public class Person extends Contact {

    private String name;
    private String surname;
    private String phoneNumber;
    private String birthDate;
    private String gender;

    @Override
    public String getDescription() {

        return name + " " + surname;
    }

    private Person(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.number;
        this.birthDate = builder.birthDate;
        this.gender = builder.gender;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String number;
        private String birthDate;
        private String gender;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public String getbDate() {
            return birthDate;
        }

        public Builder setBirthDate(String bDate) {
            this.birthDate = bDate;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
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

            if (phoneNumber.matches(regex)) {
                this.number = phoneNumber;
            } else {
                System.out.println("Wrong number format!");
                this.number = "[no number]";

                // System.exit(0);
            }
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}


