package com.scholarship.model;

public class PrivateScholarship extends Scholarship {

    @Override
    public boolean isEligible(Student student) {
        return isGradeInRange(student);
    }

    @Override
    public String getType() {
        return "Private";
    }
}
