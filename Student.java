package Lab2;

import java.util.Scanner;
import java.util.ArrayList;

public class Student {

    private int year;
    private String program;
    private float averageGrade;
    static ArrayList <Student> studentRegister = new ArrayList <Student>();
    static Scanner scan = new Scanner(System.in);
    private static String delimiters = "[ ]+";

    //constructor
    public Student(String programOfStudent, int yearOfStudent, float studentAverageGrade){
        this.setProgram(programOfStudent);
        this.setYear(yearOfStudent);
        this.setAverageGrade(studentAverageGrade);
    }

    //set and get for student average grade
    public float getAverageGrade(){
        return averageGrade;
    }
    public void setAverageGrade(float enteredValue) {
        this.averageGrade = enteredValue;
    }

    //set and get for student program
    public String getProgram(){
        return program;
    }
    public void setProgram(String enteredString) {
        this.program = enteredString;
    }

    //set and get for student year
    public int getYear(){
        return year;
    }
    public void setYear(int enteredInt) {
        this.year = enteredInt; 
    }

    //displaying the menu
    private static void printMenu(){
        System.out.println("===================================================================================");
        System.out.println("Menu:");
        System.out.println("1) Enter information for a new student");
        System.out.println("2) Print out all student information  entered so far");
        System.out.println("3) Print the average of all students grades");
        System.out.println("4) Enter a specific program and print all student information for that program");
        System.out.println("5) End the Program");
        System.out.println("===================================================================================");
    }

    //seperatting the program and the year of the student
    private static String[] seperate (String enteredString){
        String invalid;
        String [] parts = enteredString.split(delimiters);
        //checks if the correct number of inputs were given and if not loops until they are
        while(parts [0].isEmpty() || parts.length != 2){
            System.out.println("Sorry that was not a valid program and or year, Enter student Program and Year:");
            invalid = scan.nextLine();
            parts = invalid.split(delimiters);
        }
        return parts;
    }

    //setting the average grade of the student
    private static float averageGradeSetter(String inputString){
        float tempAverage;
        //checking if a value was entered if not defaulting to 0%
        if(inputString.isEmpty()){
            tempAverage = 0;
        }
        else{
            tempAverage = Float.parseFloat(inputString);
        }
        //if entered value is too large or too small, defaulting to max or min value
        if(tempAverage > 100){
            System.out.println("Sorry the entered value was larger than 100%, average has defaulted to 100%");
            tempAverage = 100;
        }
        else if(tempAverage < 0){
            System.out.println("Sorry the entered value was less than 0%, average has defaulted to 0%");
            tempAverage = 0;
        }
        return tempAverage;
    }

    //checking if the student register arraylist is empty
    private static boolean checkStudentRegister(){
        boolean isEmpty;
        if(studentRegister.isEmpty()){
            System.out.println("Sorry, class list is currently empty");
            System.out.println("Please enter some information and try again");
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
        return isEmpty;
    }
    
    //displaying the students information
    private static void printStudents(){
        for(Student temp: studentRegister){
            System.out.println("Program: " + temp.getProgram());
            System.out.println("Year: " + temp.getYear());
            System.out.printf("Average: %.1f%%\n", temp.getAverageGrade());
            System.out.println();
        }
    }

    //suming all the average grades and averaging them for one overhead average grade
    private static void averageOfAverageGrades() {
        double overallAverage = 0;
        int i = 0;
        for(Student temp: studentRegister){
            overallAverage += temp.getAverageGrade();
            i++;
        }
        overallAverage /= i;
        System.out.printf("The overall average grade for all students is: %.1f%%\n", overallAverage);
        System.out.println("Across a total of " + i + " students"); 
        System.out.println();
    }

    //displaying all information relevant the the specified program
    private static void displaySpecificInformation(){
        boolean found = false;
        String specifiedProgram;
        System.out.println("What program would you like to look for: ");
        specifiedProgram = scan.nextLine();
        for(Student temp: studentRegister){
            if(temp.getProgram().equals(specifiedProgram)){
                System.out.println("Program: " + temp.getProgram());
                System.out.println("Year: " + temp.getYear());
                System.out.printf("Average: %.1f%%\n", temp.getAverageGrade());
                System.out.println();
                found = true;
            }
        }
        if(found == false){
            System.out.println("Sorry, No information found for the specified program");
        }        
    }

    public static void main(String[] args){
        boolean isEmpty;
        int tempYear;
        String tempProgram;
        String inputString;
        String tempParsed[] = new String[2];
        float tempAverage;
        int menuOption = 0;
        boolean valid = true;
        while(menuOption != 5){
            //printing the menu
            printMenu();
            //getting user choice for program option
            do{
                if(valid == true){
                    System.out.print("Enter choice: ");
                    menuOption = Integer.parseInt(scan.nextLine());
                }
                else if(valid == false){
                    System.out.print("Sorry entered option was not valid, Enter choice: ");
                    menuOption = Integer.parseInt(scan.nextLine());
                }
                valid = false;
            }while(menuOption > 5 || menuOption < 1);
            valid = true;
            
            switch(menuOption){
                case 1:
                    //getting year and program
                    System.out.println("Enter student Program and Year: ");
                    inputString = scan.nextLine();
                    tempParsed = seperate(inputString);
                    tempProgram = tempParsed[0];
                    tempYear = Integer.parseInt(tempParsed[1]);

                    //gettting average grade
                    System.out.println("Enter Average grade, or leave blank:");
                    inputString = scan.nextLine();
                    tempAverage = averageGradeSetter(inputString);

                    //creating a new student object and adding it to the arraylist
                    Student tempStudent = new Student(tempProgram, tempYear, tempAverage);
                    studentRegister.add(tempStudent);
                    break;
                case 2:
                    //displaying the students information
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        printStudents();
                    }
                    break;
                case 3:
                    //suming all the average grades and averaging them for one overhead average grade
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        averageOfAverageGrades();
                    }
                    break;
                case 4:
                    //displaying all information relevant the the specified program
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        displaySpecificInformation();
                    }
                    break;
                case 5:
                    //ends the program
                    break;
            }

        }
        scan.close();
    }
}
