package com.ua.codespace;

import java.util.*;

import static com.ua.codespace.LambdasCE_001.Gender.FEMALE;
import static com.ua.codespace.LambdasCE_001.Gender.MALE;
import static java.util.Comparator.comparing;


public class LambdasCE_001 {
    enum Gender {MALE, FEMALE}

    static class Person {
        String name;
        int age;
        Gender gender;

        public Person(String name, int age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }
    }

    static List<Person> persons = Arrays.asList(
            new Person("John", 27, MALE),
            new Person("Sarah", 29, FEMALE),
            new Person("Peter", 44, MALE),
            new Person("Donald", 70, MALE),
            new Person("Hillary", 69, FEMALE)
    );

    /**
     * Process list element using external iteration (imperative style)
     */
    public static class PersonListImperativeProcessing {
        public static void main(String[] args) {
            int maxAge = 0;
            for (Person p : persons) {
                if (p.getGender() == FEMALE) {
                    if (p.getAge() > maxAge) {
                        maxAge = p.getAge();
                    }
                }
            }
            System.out.println(maxAge);
        }
    }

    /**
     * Process list element using internal iteration (declarative style)
     */
    public static class PersonListDeclarativeProcessing {
        public static void main(String[] args) {
            OptionalInt maxAge = persons.stream()
                    .filter(p -> p.getGender() == FEMALE)
                    .mapToInt(Person::getAge)
                    .max();

        }
    }


    //    -------------------------------------------------------------------------------------------------------------

    static class User {
        private String firstName;

        public User(String firstName) {
            this.firstName = firstName;
        }

        public String getfirstName() {
            return firstName;
        }

        public void setfirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String toString() {
            return firstName;
        }
    }

    public static class Summary_001 {

        public static void main(String[] args) throws Exception {
            List<User> users = Arrays.asList(new User("Jimmy"), new User("Peter"), new User("Sarah"), new User("Bob"));

            Collections.shuffle(users);

        /* The first, and very verbose example of sorting list of users*/
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return u1.getfirstName().compareTo(u2.getfirstName());
                }
            });

        /* Use lambda expression instead*/
            Collections.sort(users, (u1, u2) -> u1.getfirstName().compareTo(u2.getfirstName()));

        /* Use static helper method */
            Collections.sort(users, comparing(u -> u.getfirstName()));

        /* Apply method reference */
            Collections.sort(users, comparing(User::getfirstName));

        /* Use default sort method for List*/
            users.sort(comparing(User::getfirstName));

            users.forEach(System.out::println);

        }

    }


}

