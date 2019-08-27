package pl.sda.gdajava25;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    private static final LoadDataFromUser SCANNER = new LoadDataFromUser();

    public static void main(String[] args) {
        StudentDao studentDao = null;
        try {
            studentDao = new StudentDao();
        } catch (SQLException e) {
            System.err.println("Student dao cannot be created. Mysql error.");
            System.err.println("Error: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Configuration file error. ");
            System.err.println("Error: "+e.getMessage());
            return;
        }
        System.out.println("Witam w DB jdbc_students.");
        String komenda;
        try {
            do {
                komenda = SCANNER.askCommend();
                if (komenda.equalsIgnoreCase("Wstaw")) {
                    studentDao.insertStudent(makeStudent());
                } else if (komenda.equalsIgnoreCase("Usun")) {
                    studentDao.deleteStudent(SCANNER.askId());
                } else if (komenda.equalsIgnoreCase("Listuj")) {
                    studentDao.getListAllStudent().forEach(System.out::println);
                } else if (komenda.toUpperCase().contains("ID")) {
                    studentDao.getStudentById(SCANNER.askId()).ifPresent(System.out::println);
                } else if (komenda.toUpperCase().contains("IMIE") || komenda.toUpperCase().contains("IMIENIU")) {
                    String name = "%" + SCANNER.askName() + "%";
                    studentDao.getStudentByName(name).forEach(System.out::println);
                } else if (komenda.toUpperCase().contains("OD") || komenda.toUpperCase().contains("DO")) {
                    studentDao.getStudentByAgeAndAge(SCANNER.askAge(), SCANNER.askAge())
                            .forEach(System.out::println);
                }
            } while (!komenda.equalsIgnoreCase("q"));

        } catch (SQLException e) {
            System.err.println("Error executing command: " + e.getMessage());
        }
    }

    private static Student makeStudent() {
        Student student = new Student();
        student.setName(SCANNER.askName());
        student.setAge(SCANNER.askAge());
        student.setAverage(SCANNER.askAverage());
        student.setAlive(SCANNER.askIsAlive());
        return student;
    }
}
