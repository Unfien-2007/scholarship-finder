package com.scholarship.dao;

import com.scholarship.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:scholarship.db";

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws DatabaseException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("SQLite driver not found.", e);
        } catch (SQLException e) {
            throw new DatabaseException("Could not connect to the database: " + e.getMessage(), e);
        }
    }

    public static DatabaseConnection getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

    private void initializeDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "full_name TEXT NOT NULL, " +
                    "age INTEGER NOT NULL, " +
                    "grade_level INTEGER NOT NULL, " +
                    "family_income REAL NOT NULL, " +
                    "has_disability INTEGER NOT NULL DEFAULT 0)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS scholarships (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "provider TEXT NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "benefits TEXT NOT NULL, " +
                    "min_grade_level INTEGER NOT NULL, " +
                    "max_grade_level INTEGER NOT NULL, " +
                    "max_family_income REAL NOT NULL DEFAULT 0, " +
                    "deadline TEXT NOT NULL, " +
                    "type TEXT NOT NULL)");

            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM scholarships");
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            if (count == 0) {
                seedScholarships(statement);
            }
        }
    }

    private void seedScholarships(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO scholarships (name, provider, description, benefits, min_grade_level, max_grade_level, max_family_income, deadline, type) VALUES " +
                "('CHED Full Merit Scholarship', 'Commission on Higher Education', 'Full tuition grant for academically qualified students from low-income families.', 'Full tuition and monthly stipend', 11, 12, 500000, '2026-03-15', 'GOVERNMENT')," +
                "('DOST-SEI Undergraduate Scholarship', 'Department of Science and Technology', 'Scholarship for students pursuing science and technology degrees.', 'Tuition, book allowance, and stipend', 11, 12, 750000, '2026-02-28', 'GOVERNMENT')," +
                "('TESDA Financial Assistance', 'TESDA', 'Financial assistance for technical-vocational students.', 'Training allowance', 9, 12, 300000, '2026-04-10', 'GOVERNMENT')," +
                "('LGU Local Scholar Grant', 'Local Government Unit', 'Grant for junior high school students from the local community.', 'School supplies and allowance', 9, 10, 350000, '2026-07-01', 'GOVERNMENT')," +
                "('Ayala Foundation Scholarship', 'Ayala Foundation', 'Merit-based scholarship for deserving students.', 'Full tuition', 10, 12, 0, '2026-05-01', 'PRIVATE')," +
                "('SM Foundation College Scholarship', 'SM Foundation', 'Scholarship program for college students with financial need.', 'Tuition and monthly allowance', 11, 12, 0, '2026-03-30', 'PRIVATE')," +
                "('Aboitiz College Scholarship', 'Aboitiz Foundation', 'Scholarship for students who demonstrate leadership potential.', 'Full tuition and books', 9, 12, 0, '2026-06-15', 'PRIVATE')," +
                "('Alumni Legacy Scholarship', 'Alumni Association', 'Scholarship funded by alumni for graduating senior high school students.', 'One-time cash grant', 12, 12, 0, '2026-03-01', 'PRIVATE')," +
                "('PDAO Educational Assistance', 'Persons with Disability Affairs Office', 'Educational assistance for students with disabilities.', 'Tuition subsidy and allowance', 9, 12, 0, '2026-04-20', 'DISABILITY')," +
                "('Inclusive Education Grant', 'Inclusive Education NGO', 'Grant supporting students with disabilities in inclusive education.', 'Assistive devices and tuition support', 11, 12, 0, '2026-05-10', 'DISABILITY')");
    }
}
