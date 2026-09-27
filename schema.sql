CREATE DATABASE IF NOT EXISTS scholarship_db;
USE scholarship_db;

DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS scholarships;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    grade_level INT NOT NULL,
    family_income DOUBLE NOT NULL,
    has_disability BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE scholarships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    provider VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    benefits VARCHAR(255) NOT NULL,
    min_grade_level INT NOT NULL,
    max_grade_level INT NOT NULL,
    max_family_income DOUBLE NOT NULL DEFAULT 0,
    deadline DATE NOT NULL,
    type VARCHAR(20) NOT NULL
);

INSERT INTO students (full_name, age, grade_level, family_income, has_disability) VALUES
('Maria Santos', 16, 11, 250000, FALSE),
('Juan Dela Cruz', 17, 12, 900000, FALSE),
('Ana Reyes', 15, 9, 150000, TRUE),
('Carlos Lim', 18, 12, 300000, FALSE);

INSERT INTO scholarships (name, provider, description, benefits, min_grade_level, max_grade_level, max_family_income, deadline, type) VALUES
('CHED Full Merit Scholarship', 'Commission on Higher Education', 'Full tuition grant for academically qualified students from low-income families.', 'Full tuition and monthly stipend', 11, 12, 500000, '2026-03-15', 'GOVERNMENT'),
('DOST-SEI Undergraduate Scholarship', 'Department of Science and Technology', 'Scholarship for students pursuing science and technology degrees.', 'Tuition, book allowance, and stipend', 11, 12, 750000, '2026-02-28', 'GOVERNMENT'),
('TESDA Financial Assistance', 'TESDA', 'Financial assistance for technical-vocational students.', 'Training allowance', 9, 12, 300000, '2026-04-10', 'GOVERNMENT'),
('LGU Local Scholar Grant', 'Local Government Unit', 'Grant for junior high school students from the local community.', 'School supplies and allowance', 9, 10, 350000, '2026-07-01', 'GOVERNMENT'),
('Ayala Foundation Scholarship', 'Ayala Foundation', 'Merit-based scholarship for deserving students.', 'Full tuition', 10, 12, 0, '2026-05-01', 'PRIVATE'),
('SM Foundation College Scholarship', 'SM Foundation', 'Scholarship program for college students with financial need.', 'Tuition and monthly allowance', 11, 12, 0, '2026-03-30', 'PRIVATE'),
('Aboitiz College Scholarship', 'Aboitiz Foundation', 'Scholarship for students who demonstrate leadership potential.', 'Full tuition and books', 9, 12, 0, '2026-06-15', 'PRIVATE'),
('Alumni Legacy Scholarship', 'Alumni Association', 'Scholarship funded by alumni for graduating senior high school students.', 'One-time cash grant', 12, 12, 0, '2026-03-01', 'PRIVATE'),
('PDAO Educational Assistance', 'Persons with Disability Affairs Office', 'Educational assistance for students with disabilities.', 'Tuition subsidy and allowance', 9, 12, 0, '2026-04-20', 'DISABILITY'),
('Inclusive Education Grant', 'Inclusive Education NGO', 'Grant supporting students with disabilities in inclusive education.', 'Assistive devices and tuition support', 11, 12, 0, '2026-05-10', 'DISABILITY');
