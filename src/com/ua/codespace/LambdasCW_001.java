package com.ua.codespace;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Lambda basics examples
 */
public class LambdasCW_001 {
    interface Job {
        void perform();
    }

    static class LambdaVsAnonymousClass1 {
        public static void main(String[] args) {
            Job anonymousClassJob = new Job() {
                @Override
                public void perform() {
                    System.out.println("Lambdas are awesome!");
                }
            };

            anonymousClassJob.perform();


            /*
               Implement the same logic with lambda expression and block lambda
            */
            Job lambdaJob;

            Job blockLambdaJob;
        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    interface StringOperator {
        String apply(String s);
    }

    static class LambdaVsAnonymousClass2 {
        public static void main(String[] args) {
            StringOperator anonymousClassWhiteSpaceReplacer = new StringOperator() {
                @Override
                public String apply(String s) {
                    return s.replace(' ', '_');
                }
            };

            String result = anonymousClassWhiteSpaceReplacer.apply("I need some space");
            System.out.println(result);

            /*
                Implement the same logic with lambda expression and block lambda
            */
            StringOperator lambdaWhiteSpaceReplacer;

            StringOperator blockLambdaWhiteSpaceReplacer;
        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    static class Actor {
        private String firstName;
        private String lastName;

        public Actor(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    static List<Actor> defaultActorList = Arrays.asList(
            new Actor("Matthew", "Perry"),
            new Actor("Will", "Smith"),
            new Actor("Russell", "Crowe"),
            new Actor("Robert", "De Niro"),
            new Actor("Margot", "Robbie")
    );

    interface ActorProcessor {
        void process(List<Actor> actors);
    }

    static class LambdaVsAnonymousClass3 {

        public static void main(String[] args) {
            ActorProcessor anonymousClassActorProcessor = new ActorProcessor() {
                @Override
                public void process(List<Actor> actors) {
                    for (Actor actor : actors) {
                        System.out.println(actor.getFirstName().charAt(0) + ". " + actor.getLastName());
                    }
                }
            };

            anonymousClassActorProcessor.process(defaultActorList);

             /*
                Implement the same logic with block lambda
            */


            List<String> firstNames = defaultActorList.stream()
                    .map(Actor::getFirstName)
                    .collect(Collectors.toList());


        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    interface CodeProvider<T> {
        Callable<T> getCode();
    }

    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    static class LambdaVsAnonymousClass4 {

        public static void main(String[] args) throws Exception {
            CodeProvider<LocalTime> anonymousClassCodeProvider = new CodeProvider<LocalTime>() {
                @Override
                public Callable<LocalTime> getCode() {
                    return new Callable<LocalTime>() {
                        @Override
                        public LocalTime call() throws Exception {
                            return LocalTime.now();
                        }
                    };
                }
            };

            LocalTime localTime = anonymousClassCodeProvider.getCode().call();
            System.out.println(localTime.format(timeFormatter));

             /*
                1. Implement the same logic with lambda expression
            */

            CodeProvider<LocalTime> lambdaCodeProvider;

            /*
                2. Implement the same logic method reference
            */
            CodeProvider<LocalTime> labbdaCodeProvider;
        }
    }

    //    -------------------------------------------------------------------------------------------------------------

    static class User {
        private Long id;
        private String email;
        private List<User> friends;

        public User(Long id, String email) {
            this.id = id;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<User> getFriends() {
            return friends;
        }

        public void setFriends(List<User> friends) {
            this.friends = friends;
        }

        public boolean isFriend(User user) {
            return friends.contains(user);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (id != null ? !id.equals(user.id) : user.id != null) return false;
            return email.equals(user.email);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + email.hashCode();
            return result;
        }
    }

    interface UserRelationshipValidator {
        boolean validate(User u1, User u2);
    }

    /**
     *
     */
    static class LambdaVsAnonymousClass5 {

        public static void main(String[] args) throws Exception {
            UserRelationshipValidator anonymousClassIsFriendValidator = new UserRelationshipValidator() {
                @Override
                public boolean validate(User u1, User u2) {
                    return u1.isFriend(u2);
                }
            };

             /*
                1. Implement the same logic with lambda expression
            */

            UserRelationshipValidator lambdaIsFriendValidator;

            /*
                2. Implement the same logic method reference
            */
            UserRelationshipValidator methodReferenceIsFriendValidator;
        }
    }


}
