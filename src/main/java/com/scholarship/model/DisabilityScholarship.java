package com.scholarship.model;

public class DisabilityScholarship extends Scholarship {

    @Override
    public boolean isEligible(Student student) {
        return student.hasDisability() && isGradeInRange(student);
    }

    @Override
    public String getType() {
        return "Disability";
    }
}
