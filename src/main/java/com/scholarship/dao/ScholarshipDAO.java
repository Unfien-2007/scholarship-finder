package com.scholarship.dao;

import com.scholarship.exception.DatabaseException;
import com.scholarship.model.DisabilityScholarship;
import com.scholarship.model.GovernmentScholarship;
import com.scholarship.model.PrivateScholarship;
import com.scholarship.model.Scholarship;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScholarshipDAO {

    public List<Scholarship> findAll() throws DatabaseException {
        List<Scholarship> scholarships = new ArrayList<>();
        String sql = "SELECT id, name, provider, description, benefits, min_grade_level, max_grade_level, max_family_income, deadline, type FROM scholarships";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Scholarship scholarship = createScholarship(rs.getString("type"));
                scholarship.setId(rs.getInt("id"));
                scholarship.setName(rs.getString("name"));
                scholarship.setProvider(rs.getString("provider"));
                scholarship.setDescription(rs.getString("description"));
                scholarship.setBenefits(rs.getString("benefits"));
                scholarship.setMinGradeLevel(rs.getInt("min_grade_level"));
                scholarship.setMaxGradeLevel(rs.getInt("max_grade_level"));
                scholarship.setMaxFamilyIncome(rs.getDouble("max_family_income"));
                String deadline = rs.getString("deadline");
                scholarship.setDeadline(deadline != null ? LocalDate.parse(deadline) : null);
                scholarships.add(scholarship);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException("Could not load scholarships: " + e.getMessage(), e);
        }
        return scholarships;
    }

    private Scholarship createScholarship(String type) {
        switch (type) {
            case "GOVERNMENT":
                return new GovernmentScholarship();
            case "PRIVATE":
                return new PrivateScholarship();
            case "DISABILITY":
                return new DisabilityScholarship();
            default:
                return new PrivateScholarship();
        }
    }
}
