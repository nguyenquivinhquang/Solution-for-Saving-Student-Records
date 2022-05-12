package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.Classroom;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public CourseQueries() {

        super(connection, "Course");
    }

    String tempTable = "CourseRegistrationTemporary";

    public ObservableList<CourseInformation> getClassList() {
        ObservableList<CourseInformation> classrooms = FXCollections.observableArrayList();
        try {
            String SQL = "Select Course_Id, Teacher_Id, Section, Size, Remaining from Class;";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return classrooms;
            while (res.next()) {
                classrooms.add(new CourseInformation(
                        res.getString("Course_Id").trim(),
                        res.getString("Teacher_Id").trim(),
                        res.getString("Section").trim(),
                        res.getInt("Size"),
                        res.getInt("Remaining")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classrooms;
    }

    public ObservableList<CourseInformation> getCoursesList() {
        ObservableList<CourseInformation> coursesInformation = FXCollections.observableArrayList();
        try {
            String SQL = "Select Course_Id,Course_name, Credits, Description from Course;";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return coursesInformation;
            while (res.next()) {
                coursesInformation.add(new CourseInformation(
                        res.getString("Course_Id").trim(),
                        res.getString("Course_name").trim(),
                        res.getInt("Credits"),
                        res.getString("Description").trim()

                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getCoursesList");
        }

        return coursesInformation;
    }

    public ArrayList<String> getCourseRegistration(String studentId) {
        ArrayList<String> courseRegistered = new ArrayList<>();
        try {
            String SQL = "Select Course_Id,Credit from Enrolled_Course where Student_Id='%s';";
            SQL = String.format(SQL, studentId);

            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return courseRegistered;
            while (res.next()) {
//                coursesInformation.add(new CourseInformation(
//                        res.getString("Course_Id"),
//                        res.getString("Course_name"),
//                        res.getInt("Credits"),
//                        res.getString("Teacher_Id"),
//                        res.getString("Description"),
//                        res.getString("Section"),
//                        res.getInt("Size"),
//                        res.getInt("Remaining")
//                ));
                courseRegistered.add(res.getString("Course_Id").trim());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(courseRegistered);
        return courseRegistered;
    }

    public void addClass(String courseId, String teacherId,
                         String section, Integer size, Integer remaining) {

        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("Teacher_Id", teacherId));
        courseInsert.add(new Pair<>("Section", section));
        courseInsert.add(new Pair<>("Size", size));
        courseInsert.add(new Pair<>("Remaining", remaining));
        this.insertMultiValues("Class", courseInsert);
    }
    public void deleteClass(String courseId, String teacherId, String section) {
        String SQL = "delete from %s WHERE %s='%s' and %s='%s' and %s='%s' ";
        SQL = String.format(SQL, "Class", "Course_Id", courseId,
                "Teacher_Id", teacherId, "Section", section);
        System.out.println(SQL);
        runSetQuery(SQL);
    }
    public void addCourse(String courseId, String courseTitle, String descriptions, Integer credits) {
        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("Description", descriptions));
        courseInsert.add(new Pair<>("Course_name", courseTitle));
        courseInsert.add(new Pair<>("Credits", credits));
        this.insertMultiValues(this.tableName, courseInsert);
    }

    public void addCourseStudentRegistered(String studentId, String courseId) {
        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("Student_Id", studentId));
        this.insertMultiValues(this.tempTable, courseInsert);
    }

    public void updateCourseId(String courseId, String newValue) {
        changeValueColumn(courseId, "Course_Id", newValue);
    }

    public void updateCourseName(String courseId, String newValue) {
        changeValueColumn(courseId, "Course_name", newValue);
    }

    public void updateCourseCredits(String courseId, Integer newValue) {
        updateRowInteger("Course_Id", courseId, "Credits", newValue);
    }

    public void updateCourseDescription(String courseId, String newValue) {
        changeValueColumn(courseId, "Description", newValue);
    }

    public void updateCourseTeacherId(String courseId, String newValue) {
        changeValueColumn(courseId, "Teacher_Id", newValue);
    }

    public void updateCourseSection(String courseId, String newValue) {
        changeValueColumn(courseId, "Section", newValue);
    }

    public void updateCourseSize(String courseId, String teacherId, String section, Integer size) {
        String SQL = "UPDATE %s SET %s='%s' WHERE %s='%s' and %s='%s' and %s='%s' ";
        SQL = String.format(SQL, "Class", "Size", size, "Course_Id", courseId,
                "Teacher_Id", teacherId, "Section", section);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void updateCourseSize(String courseId, Integer newValue) {
        updateRowInteger("Course_Id", courseId, "Size", newValue);

    }

    public void updateCourseRemaining(String courseId, Integer newValue) {
        updateRowInteger("Course_Id", courseId, "Size", newValue);

    }

    private void changeValueColumn(String courseId, String columnName, String newValue) {
        updateRowString("Course_Id", courseId, columnName, newValue);
    }

    public void deleteCourse(String courseId) {
        String SQL = String.format("DELETE FROM Course Where Course_Id='%s'", courseId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public int getRemainSlot(String courseId) {
        String SQL = String.format("select Remaining " +
                "FROM Course Where Course_Id='%s'", courseId);
        System.out.println(SQL);
        ResultSet result = runGetQuery(SQL);
        if (result == null) return 0;
        try {
            result.next();
            return result.getInt("Remaining");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getCourseSection(String courseId) {
        return getCourseValStringType("Section", courseId);

    }

    public String getCourseName(String courseId) {
        return getCourseValStringType("Course_name", courseId);

    }

    public Integer getCourseCredit(String courseId) {
        return getCourseValIntType("Credits", courseId);
    }

    private String getCourseValStringType(String column, String courseId) {
        return getCourseValStringType("Course", column, "Course_Id", courseId);
    }

    private Integer getCourseValIntType(String column, String courseId) {
        return getCourseValIntType("Course", column, "Course_Id", courseId);

    }

    public boolean deleteCourseRegistered(String studentId, String courseId) {
        String SQL = "Delete from %s where Student_Id='%s' and Course_Id='%s'";
        SQL = String.format(SQL, this.tempTable, studentId, courseId);
        return runSetQuery(SQL);
    }
}
