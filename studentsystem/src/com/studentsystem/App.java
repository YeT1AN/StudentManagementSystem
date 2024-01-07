package com.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    static ArrayList<User> list = new ArrayList<>();

    // add a user information
    static {
        list.add(new User("chang", "123456", "141989228", "4379000000"));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-----Welcome to the Student Management System-----");
            System.out.println("Please choose an operation: 1. Login, 2. Register, 3. Forgot Password, 4. Exit");

            String choose = sc.next();

            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("Thank you for using the system. Goodbye.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }


    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        // only have 3 attempts
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter your username:");
            String username = sc.next();
            // check if the username exists
            boolean flag = contains(list, username);
            if (!flag) {
                System.out.println("Username " + username + " is not registered. Please register before logging in.\n");
                return;
            }

            System.out.println("Please enter your password:");
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("The current correct code is: " + rightCode);
                System.out.println("Please enter the code:");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightCode)) { // ignore case
                    System.out.println("Code is correct.");
                    break; // no need to re-enter
                } else {
                    System.out.println("Incorrect code.");
                    continue; // need to re-enter
                }
            }

            // encapsulate data to an object
            User useInfo = new User(username, password, null, null);
            // pass the object as a whole instead of data
            boolean result = checkUserInfo(list, useInfo);
            if (result) {
                System.out.println("Login successful. You can now use the Student Management System.\n");
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                if (i == 2) {
                    System.out.println("The account " + username + " is locked. Please contact IT Help Service for support\n");
                    return;
                } else {
                    System.out.println("Incorrect username or password. You have " + (2 - i) + " attempts remaining.");
                }
            }
        }
    }

    private static boolean checkUserInfo(ArrayList<User> list, User useInfo) {
        // check if user exists
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(useInfo.getUsername()) && user.getPassword().equals(useInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    // add user information to the collection
    private static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("Please enter your username:");
            username = sc.next();
            boolean flag1 = checkUsername(username);
            if (!flag1) {
                System.out.println("Username does not meet the requirements. Please re-enter.");
                continue;
            }

            boolean flag2 = contains(list, username);
            if (flag2) {
                System.out.println("Username " + username + " already exists. Please choose another username.");
            } else {
                System.out.println("Username " + username + " is available.");
                break;
            }
        }

        String password;
        while (true) {
            System.out.println("Please enter your password:");
            password = sc.next();
            System.out.println("Please enter your password again:");
            String reenterPassword = sc.next();
            if (!password.equals(reenterPassword)) {
                System.out.println("Passwords do not match. Please re-enter.");
                continue;
            } else {
                System.out.println("Passwords match. Please continue entering other data.");
                break;
            }
        }

        String employeeID;
        while (true) {
            System.out.println("Please enter your employee number:");
            employeeID = sc.next();
            boolean flag = checkemployeeID(employeeID);
            if (flag) {
                System.out.println("employee number is valid.");
                break;
            } else {
                System.out.println("Invalid employee number format. Please re-enter.");
                continue;
            }
        }

        String phoneNumber;
        while (true) {
            System.out.println("Please enter your phone number:");
            phoneNumber = sc.next();
            boolean flag = checkPhoneNumber(phoneNumber);
            if (flag) {
                System.out.println("Phone number is valid.");
                break;
            } else {
                System.out.println("Invalid phone number format. Please re-enter.");
                continue;
            }
        }

        User u = new User(username, password, employeeID, phoneNumber);
        // add the user object to the collection
        list.add(u);
        System.out.println("Registration successful.");

        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getUsername() + ", " + user.getPassword() + ", "
                    + user.getemployeeID() + ", " + user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        // check length
        if (phoneNumber.length() != 10) {
            return false;
        }

        // cannot start with 0
        if (phoneNumber.startsWith("0")) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkemployeeID(String employeeID) {
        // check length
        if (employeeID.length() != 9) {
            return false;
        }

        // cannot start with 0
        if (employeeID.startsWith("0")) {
            return false;
        }

        for (int i = 0; i < employeeID.length() - 1; i++) {
            char c = employeeID.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }

        char endChar = employeeID.charAt(employeeID.length() - 1);
        if ((endChar >= '0' && endChar <= '9') || (endChar == 'x') || (endChar == 'X')) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean contains(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String correctUsername = user.getUsername();
            if (correctUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkUsername(String username) {
        // check length
        int len = username.length();
        if (len < 3 || len > 15) {
            return false;
        }

        // check every single char is valid or not
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        // count how many chars
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }
        return count > 0;
    }

    private static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (!flag) {
            System.out.println("The user " + username + " is not registered. Please register first.\n");
            return;
        }

        System.out.println("Please enter your employee number:");
        String employeeID = sc.next();
        System.out.println("Please enter your phone number:");
        String phoneNumber = sc.next();

        int index = findIndex(list, username);
        // get the user object by using index
        User user = list.get(index);
        if (!(user.getemployeeID().equalsIgnoreCase(employeeID) && user.getPhoneNumber().equals(phoneNumber))) {
            System.out.println("Incorrect employee number or phone number. Cannot change password.\n");
            return;
        }

        String password;
        while (true) {
            System.out.println("Please enter the new password:");
            password = sc.next();
            System.out.println("Please enter the new password again:");
            String reenterPassword = sc.next();
            if (password.equals(reenterPassword)) {
                System.out.println("Passwords match.");
                break;
            } else {
                System.out.println("Passwords do not match. Please re-enter.");
                continue;
            }
        }

        user.setPassword(password);
        System.out.println("Password changed successfully.\n");
    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    private static String getCode() {
        // create a collection and add all lowercase and uppercase chars
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }

        StringBuilder sb = new StringBuilder();
        // get 4 random chars
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            // get random index
            int index = r.nextInt(list.size());
            // use the random index to get char
            char c = list.get(index);
            // add the random char to the sb
            sb.append(c);
        }

        // append a random number
        int number = r.nextInt(10);
        sb.append(number);

        // to modify the content of a string need to convert the string to a character array, make modifications in the array, then create a new string again
        char[] arr = sb.toString().toCharArray();
        // take the last index and swap it with a random index
        int randomIndex = r.nextInt(arr.length);
        // swap the element at the maximum index with the element at the random index
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;

        // create a new string
        return new String(arr);
    }
}


