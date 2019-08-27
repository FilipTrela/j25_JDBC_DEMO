package pl.sda.gdajava25;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class StudentDao { // data access object -
    private MySqlConnection mysqlConnection;

    public StudentDao() throws SQLException, IOException {
        mysqlConnection = new MySqlConnection();
        createTableIfNotExist();
    }

    private void createTableIfNotExist() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }

    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, student.getName());
                statement.setInt(2, student.getAge());
                statement.setDouble(3, student.getAverage());
                statement.setBoolean(4, student.isAlive());


                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Student został utworzony z id: " + generatedId);
                }
            }
        }
    }

    public boolean deleteStudent(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.DELETE_STUDENT)) {
                statement.setLong(1, id);

                int affectedRecords = statement.executeUpdate();

                if(affectedRecords>0){
                    return true;
                }

            }
        }
        return false;
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong(1));
        student.setName(resultSet.getString(2));
        student.setAge(resultSet.getInt(3));
        student.setAverage(resultSet.getDouble(4));
        student.setAlive(resultSet.getBoolean(5));
        return student;
    }

    public List<Student> getListAllStudent() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.SHOW_ALL_STUDENTS)) {
                ResultSet resultSet = statement.executeQuery();
                loadMulitpleStudentsResultSet(students, resultSet);
            }
        }
        return students;
    }

    public Optional<Student> getStudentById(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.SHOW_STUDENT_BY_ID)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Student student = getStudent(resultSet);
                    return Optional.of(student);
                } else {
                    System.err.println("Nie udało sie odnaleźć studenta");
                }
            }

        }
        return Optional.empty();
    }

    public List<Student> getStudentByName(String name) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.SHOW_STUDENT_BY_NAME)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                loadMulitpleStudentsResultSet(students, resultSet);
            }
        }
        return students;
    }

    public List<Student> getStudentByAgeAndAge(int askAge, int askAge1) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentQueries.SHOW_BY_AGE)) {
                statement.setInt(1, askAge);
                statement.setInt(2, askAge1);
                ResultSet resultSet = statement.executeQuery();
                loadMulitpleStudentsResultSet(students, resultSet);
            }
        }
        return students;

    }

    private void loadMulitpleStudentsResultSet(List<Student> students, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Student student = getStudent(resultSet);
            students.add(student);
        }
    }

}
