package com.scholarship.service;

import com.scholarship.model.Scholarship;
import com.scholarship.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EligibilityChecker {

    public List<Scholarship> findMatches(List<Scholarship> scholarships, Student student) {
        List<Scholarship> matches = new ArrayList<>();
        for (Scholarship scholarship : scholarships) {
            if (scholarship.isEligible(student)) {
                matches.add(scholarship);
            }
        }
        matches.sort(Comparator.comparing(Scholarship::getDeadline));
        return matches;
    }
}
