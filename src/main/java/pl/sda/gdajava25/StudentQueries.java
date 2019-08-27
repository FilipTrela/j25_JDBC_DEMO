package pl.sda.gdajava25;

public interface StudentQueries {
    String INSERT_QUERY = "insert into `students` \n" +
            "(`name`,`age`,`average`,`alive`)\n" +
            "values( ? , ? , ? , ? );";
    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `students`(\n" +
            "`id` int not null auto_increment primary key,\n" +
            "`name` varchar(255) not null,\n" +
            "`age` int not null,\n" +
            "`average` double not null,\n" +
            "`alive` tinyint not null\n" +
            ");";
    String DELETE_STUDENT = "DELETE FROM students\n" +
            "WHERE `id` = ?;";
    String SHOW_ALL_STUDENTS = "SELECT * FROM jdbc_students.students;";
    String SHOW_STUDENT_BY_ID = "SELECT * FROM jdbc_students.students \n" +
            "where `id` = ?;";
    String SHOW_STUDENT_BY_NAME = "SELECT * FROM jdbc_students.students \n" +
            "where `name` LIKE ?;";
    String SHOW_BY_AGE = "SELECT * FROM jdbc_students.students\n" +
            "WHERE `age`>= ? AND `age` <= ?;";
}
