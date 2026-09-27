package com.scholarship.dao;

import com.scholarship.exception.DatabaseException;
import com.scholarship.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {

    public int saveStudent(Student student) throws DatabaseException {
        String sql = "INSERT INTO students (full_name, age, grade_level, family_income, has_disability) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFullName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getGradeLevel());
            ps.setDouble(4, student.getFamilyIncome());
            ps.setBoolean(5, student.hasDisability());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }
            keys.close();
            ps.close();
            return id;
        } catch (SQLException e) {
            throw new DatabaseException("Could not save student: " + e.getMessage(), e);
        }
    }
}
