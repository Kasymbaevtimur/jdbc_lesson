package org.peaksoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
//        Student student = new Student("Kumarbek","Asanov",16);
//        addStudent(student);
//        addStudent(new Student("Nurtegin","Sagynaliev",16));
        getAllStudents().forEach(System.out::println);
    }

    public static void createTable() {
        try (Connection connection = DateBase.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students(" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(50) UNIQUE," +
                    "lastName VARCHAR(50) NOT NULL ," +
                    "age INTEGER);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTable() {
        String sql = "DROP TABLE IF EXISTS students";
        try (Connection connection = DateBase.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addStudent(Student student) {
        try (Connection connection = DateBase.connection();
             PreparedStatement preparedStatement = connection.prepareStatement("" +
                     "INSERT INTO students(name,lastname,age)" +
                     "VALUES (?,?,?);")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.executeUpdate();
            System.out.println("Success added student " + student.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void getStudentById(int id) {
        try (Connection connection = DateBase.connection();
             PreparedStatement preparedStatement = connection.prepareStatement("" +
                     "SELECT * FROM students WHERE students.id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getInt(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        try(Connection connection = DateBase.connection();
        Statement statement = connection.createStatement()) {
           ResultSet resultSet =  statement.executeQuery("SELECT * FROM students");
           while (resultSet.next()){
               Student student = new Student();
               student.setId(resultSet.getInt("id"));
               student.setName(resultSet.getString("name"));
               student.setLastName(resultSet.getString(3));
               student.setAge(resultSet.getInt("age"));
               students.add(student);
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return students;
    }

}
