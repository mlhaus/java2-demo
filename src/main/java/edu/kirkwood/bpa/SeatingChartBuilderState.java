package edu.kirkwood.bpa;// Contestant: K0519415

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.text.DecimalFormat;

public class SeatingChartBuilderState {

   // SC2: Create Scanner and ArrayList objects
   private static Scanner scanner = new Scanner(System.in);
   private static List<Students> students = new ArrayList<>();

   public static void main(String args[]) {
      inputManager();
   }


   // SC3: Finds and prints the highest GPA from all the students
   private static void printHighestGPA() {
      if(students.size() == 0) {
         return;
      }
      // Students highest = students.get(0);
      // for(Students student: students) {
      //    if(student.getGPA() > highest.getGPA()) {
      //       highest = student;
      //    }
      // }
      Students highest = students.stream().max(Comparator.comparingDouble(Students::getGPA)).get();
      System.out.println("\n\nMax GPA: " + highest.getGPAStr() + " " + highest.getWholeName());
   }

   // SC4: Finds and prints the lowest GPA from all the students
   private static void printLowestGPA() {
      if(students.size() == 0) {
         return;
      }
      // Students lowest = students.get(0);
      // for(Students student: students) {
      //    if(student.getGPA() < lowest.getGPA()) {
      //       lowest = student;
      //    }
      // }
      Students lowest = students.stream().min(Comparator.comparingDouble(Students::getGPA)).get();
      System.out.println("\n\nMin GPA: " + lowest.getGPAStr() + " " + lowest.getWholeName());
   }

   // Call this method to generate a random list of students
   private static void setStudents(int nc) {
      Random rand = new Random();
      Students s;
      DecimalFormat df = new DecimalFormat("#.00");
      int nameCount = nc;
      double gpa;
      int grade;
      String fn;
      String ln;

      String[] allNames = {"Walter", "Jones", "Rose", "Wilson", "Jack", "Rodriguez", "Elizabeth",
            "Smith", "Earl", "Carter", "Linda", "Ward", "Christopher", "Turner", "Martin", "Murphy",
            "Betty", "Garcia", "Shawn", "Taylor", "Sean", "Simmons", "Joshua", "Evans", "Norma",
            "Mitchell", "Brenda", "Johnson", "Donna", "Clark", "Irene", "Diaz", "Marilyn",
            "Coleman", "Arthur", "Collins", "Henry", "Hall", "Howard", "Robinson", "Jerry", "Green",
            "Maria", "Price", "Evelyn", "Bell", "Janet", "Moore", "Susan", "Foster"};

      for (int i = 0; i < nameCount; i++) {

         do {

            fn = allNames[rand.nextInt(allNames.length)];
            ln = allNames[rand.nextInt(allNames.length)];
         } while (fn.equals(ln));
         grade = rand.nextInt(4) + 9;
         double tempGPA = rand.nextDouble() * 3.0 + 1.0;
         String tempString = df.format(tempGPA);
         gpa = Double.parseDouble(tempString);
         s = new Students(fn, ln, grade, gpa);
         students.add(s);
      }

   }

   // Given
   private static void printStudents() {
      int i = 1;
      System.out.println("\n---------------------------------------------------------------------");
      for (Students n : students) {
         System.out.println(i + ") " + n.getWholeName() + " | Grade Level: " + n.getGradeLevel()
               + " | GPA: " + n.getGPAStr());
         i++;
      }

      printHighestGPA();
      printLowestGPA();
   }

   // Gets user input commands and checks for entry errors
   private static void inputManager() {
      loop: while(true) {
         System.out.println("\n---------------------------------------------------------------------");
         System.out.println("Hello. What would you like to do today?");
         System.out.println("Press [1] to create random students OR Press [2] to create a single student OR Press [3] to end the program");
         int choice = 0;
         // SC5: Code with try/catch exception for invalid data error
         try {
            choice = scanner.nextInt();
         } catch(InputMismatchException e) {
            System.out.println("ERROR: Please enter valid data type");
            continue;
         } finally {
            scanner.nextLine();
         }
      
         switch(choice) {
            case 1:
               int numStudents = option1Step2();
               setStudents(numStudents);
               printStudents();
               break;
            case 2:
               createSingleStudent();
               break;
            case 3:
               // SC11: Code that exits the program
               break loop;
            default:
               // SC5 Code for range violation
               System.out.println("Your entry is out of range.");
         }
      }
      System.out.println("Goodbye!");
   }

   private static void createSingleStudent() {
      System.out.println("You are now creating a single student");
      // Option 2, Step 2
      System.out.println("Enter in the first name");
      String firstName = scanner.nextLine();
      // Option 2, Step 3
      System.out.println("Enter in the last name");
      String lastName = scanner.nextLine();
      // Option 2, Step 4
      int gradeLevel = option2Step4();
      // Option 2, Step 5
      double gpa = option2Step5();
      students.add(new Students(firstName, lastName, gradeLevel, gpa));
      printStudents();
   }
    
   private static double option2Step5() {
      double gpa = 0;
      while(true) {
         System.out.println("Enter in the GPA in its proper format (#.##)");
         // SC8: Code try-catch for invalid data
         try {
            gpa = scanner.nextDouble();
         } catch(InputMismatchException e) {
            System.out.println("ERROR: Please enter valid data type");
            continue;
         } finally {
            scanner.nextLine();
         }
         // SC8: Code range violation
         if(gpa < 0 || gpa > 4) {
            System.out.println("Your entry is out of range.");
            continue;
         }
         break;
      }
      return gpa;
   }
    
   private static int option2Step4() {
      int gradeLevel = 0;
      while(true) {
         System.out.println("Enter in the student grade level");
         // SC7: Code try-catch for invalid data
         try {
            gradeLevel = scanner.nextInt();
         } catch(InputMismatchException e) {
            System.out.println("ERROR: Please enter valid data type");
            continue;
         } finally {
            scanner.nextLine();
         }
         // SC7: Code for range violation
         if(gradeLevel < 9 || gradeLevel > 12) {
            System.out.println("Your entry is out of range.");
            continue;
         }
         break;
      }
      return gradeLevel;
   }

   	
private static int option1Step2() {
   int val = 0;
   loop: while(true) {
      System.out.print("\nPlease enter in a value between 1 and 50: ");
      // SC6 Code with try/catch exception for invalid data error
      try {
         val = scanner.nextInt();
      } catch(InputMismatchException e) {
         System.out.println("ERROR: Please enter valid data type");
         continue;
      } finally {
         scanner.nextLine();
      }
      // SC6 Code for range violation
      if(val < 1 || val > 50) {
         System.out.println("Your entry is out of range.");
         continue;
      }
      break;
   }
   return val;
}




}


// SC1: All constructors, methods, and variables are appropriately created.
class Students {
   // Create appropriately variables based upon constructor parameters
   private String firstName;
   private String lastName;
   private int gradeLevel;
   private double gpa;

   // Generic Constructor
   public Students() {

   }

   // Constructor with Parameters
   public Students(String firstName, String lastName, int gradeLevel, double gpa) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.gradeLevel = gradeLevel;
      this.gpa = gpa;
   }

   // SC10: Returns "last name, first name" with required convention
   public String getWholeName() {
      return String.format("%s%s, %s%s", 
      lastName.substring(0, 1).toUpperCase(), 
      lastName.substring(1).toLowerCase(),  
      firstName.substring(0, 1).toUpperCase(),
      firstName.substring(1).toLowerCase());
   }



   // Returns first name
   public String getFirstName() {
      return firstName;
   }

   // Returns last name
   public String getLastName() {
      return lastName;
   }

   // Returns the GPA
   public double getGPA() {
      return gpa;
   }

   // SC9: Returns the GPA formatted with proper DecimalFormat object
   public String getGPAStr() {
      DecimalFormat df = new DecimalFormat("#.00");
      return df.format(gpa);
   }

   // Returns grade level
   public int getGradeLevel() {
      return gradeLevel;
   }

   @Override
   public String toString() {
      return "Students [firstName=" + firstName + ", lastName=" + lastName + ", gradeLevel="
            + gradeLevel + ", gpa=" + gpa + "]";
   }



}
