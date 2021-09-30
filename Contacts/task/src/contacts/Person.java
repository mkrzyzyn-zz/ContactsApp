package contacts;

public class Person extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

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

        public String getBirthDate() {
            return birthDate;
        }

        public Builder setBirthDate(String bDate) {

            String regex = "\\d{4}-\\d{2}-\\d{2}";

            if (bDate != null && bDate.matches(regex)) {
                this.birthDate = bDate;
            } else {
                System.out.println("Bad birth date!");
                this.birthDate = "[no data]";

            }
            return this;
        }
        public String getGender() {
            return gender;
        }

        public Builder setGender(String gender) {

            String regex = "^(M|F){1}$";

            if (gender != null && gender.matches(regex)) {
                this.gender = gender;
            } else {
                System.out.println("Bad gender!");
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


