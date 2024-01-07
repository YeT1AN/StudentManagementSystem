package com.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {

        ArrayList<Student> list = new ArrayList<>();

        loop:
        while (true) { // give the loop a name so the following break will break from both switch and while loop
            System.out.println("-----------------Welcome to Student Management System-------------------");
            System.out.println("1: Add Student");
            System.out.println("2: Delete Student");
            System.out.println("3: Update Student");
            System.out.println("4: Query Student");
            System.out.println("5: Exit");
            System.out.println("Please enter your choice:");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose) {
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("Exiting");
                    break loop;
                }
                default -> System.out.println("Invalid option\n");
            }
        }
    }

    // add a student
    public static void addStudent(ArrayList<Student> list) {
        // use empty constructor to create a new student object
        Student s = new Student();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the student's ID:");
            String id = sc.next();
            boolean flag = contains(list, id);
            if (flag) {
                System.out.println("ID already exists. Please re-enter.");
            } else {
                s.setId(id);
                break;
            }
        }

        sc.nextLine(); // consume the newline character

        System.out.println("Please enter the student's name:");
        String name = sc.nextLine(); // Use nextLine() to read the whole line
        s.setName(name);

        System.out.println("Please enter the student's age:");
        int age = sc.nextInt();
        s.setAge(age);

        System.out.println("Please enter the student's city:");
        String city = sc.next();
        s.setCity(city);

        // Add the student object to the list
        list.add(s);

        // Provide a success message to the user
        System.out.println("Student information added successfully.\n");
    }

    // delete a student
    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the ID to delete:");
        String id = sc.next();

        // Find the index of the ID in the list
        int index = getIndex(list, id);

        // Check the value of index
        // If -1, it means the ID doesn't exist; end the function and return to the initial menu
        if (index >= 0) {
            // If index is greater than or equal to 0, it means the ID exists; proceed with deletion
            list.remove(index);
            System.out.println("Student with ID: " + id + " has been deleted successfully.\n");
        } else {
            System.out.println("ID doesn't exist. Deletion failed.\n");
        }
    }

    // update a student
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the ID of the student you want to update:");
        String id = sc.next();

        int index = getIndex(list, id);

        if (index == -1) {
            System.out.println("The ID " + id + " you want to update does not exist. Please re-enter.\n");
            return;
        }

        // Get the student object to be updated
        Student stu = list.get(index);

        // Input other information and perform updates
        System.out.println("Please enter the new name of the student:");
        String newName = sc.next();
        stu.setName(newName);

        System.out.println("Please enter the new age of the student:");
        int newAge = sc.nextInt();
        stu.setAge(newAge);

        System.out.println("Please enter the new city of the student:");
        String newCity = sc.next();
        stu.setCity(newCity);

        System.out.println("Student information updated successfully.\n");
    }

    // search a student
    public static void queryStudent(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("No student information available. Please add information before querying.\n");
            return;
        }

        System.out.println("ID\t\t\tName\t\t\tAge\t\tCity");

        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.printf("%-11s %-15s %-7d %-20s%n", stu.getId(), stu.getName(), stu.getAge(), stu.getCity());
        }
    }

    // check if ID already in the collection, if yes return true
    public static boolean contains(ArrayList<Student> list, String id) {
        return getIndex(list, id) >= 0;
    }

    // get index by id
    public static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i); // get every single student object
            String sid = stu.getId(); // get every single ID of the student object
            if (sid.equals(id)) {
                return i;
            }
        }
        return -1; // after the loop if still cannot find, means do not exist
    }
}