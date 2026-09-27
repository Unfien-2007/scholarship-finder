package com.scholarship.model;

public class Student extends Person {

    private int studentId;
    private int gradeLevel;
    private double familyIncome;
    private boolean hasDisability;

    public Student() {
    }

    public Student(String fullName, int age, int gradeLevel, double familyIncome, boolean hasDisability) {
        super(fullName, age);
        this.gradeLevel = gradeLevel;
        this.familyIncome = familyIncome;
        this.hasDisability = hasDisability;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public double getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(double familyIncome) {
        this.familyIncome = familyIncome;
    }

    public boolean hasDisability() {
        return hasDisability;
    }

    public void setHasDisability(boolean hasDisability) {
        this.hasDisability = hasDisability;
    }
}
