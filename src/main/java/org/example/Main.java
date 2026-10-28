
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    static final String URL =
            "jdbc:mysql://localhost:3306/student";
    static final String USER = "root";
    static final String PASSWORD = "@Chitti.12";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public static void insertStudent(
            String name, int age, String gender, String addhar)
            throws Exception {

        String sql = "INSERT INTO student(name, age, gender, addhar) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, addhar);

            ps.executeUpdate();
            System.out.println("Student inserted successfully!");
        }
    }

    // READ
    public static void displayStudents() throws Exception {
        String sql = "SELECT * FROM student";

        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id")
                                + ", Name: " + rs.getString("name")
                                + ", Age: " + rs.getInt("age")
                                + ", Gender: " + rs.getString("gender")
                                + ", Aadhaar: " + rs.getString("addhar")
                );
            }
        }
    }

    // UPDATE
    public static void updateStudent(int id, int age)
            throws Exception {

        String sql = "UPDATE student SET age = ? WHERE id = ?";

        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, age);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " student record(s) updated.");
        }
    }

    // DELETE
    public static void deleteStudent(int id) throws Exception {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " student record(s) deleted.");
        }
    }

    public static void main(String[] args) {
        try {
            insertStudent("Mamatha", 21, "Female", "987654321098");

            System.out.println("\nStudent Details:");
            displayStudents();

            // Update the student with ID 1.
            // Uncomment only if ID 1 is the intended record.
            // updateStudent(1, 22);

            // Uncomment only when you want to delete a record.
            // deleteStudent(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
