package edu.kirkwood.learnx.model;

public class Student extends LearnxUser {
    private int gradeLevel;
    private double gpa;

    public Student() {
        super();
    }

    public Student(int userId, String firstName, String lastName, int gradeLevel, double gpa) {
        super(userId, firstName, lastName);
        this.gradeLevel = gradeLevel;
        this.gpa = gpa;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student [gradeLevel=" + gradeLevel + ", gpa=" + gpa + ", getUserId()=" + getUserId()
                + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + "]";
    }

    

    
}
