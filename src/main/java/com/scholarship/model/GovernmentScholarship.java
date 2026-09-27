package com.scholarship.model;

public class GovernmentScholarship extends Scholarship {

    @Override
    public boolean isEligible(Student student) {
        return isGradeInRange(student)
                && student.getFamilyIncome() <= getMaxFamilyIncome();
    }

    @Override
    public String getType() {
        return "Government";
    }
}
