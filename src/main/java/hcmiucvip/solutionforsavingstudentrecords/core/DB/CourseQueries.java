package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public CourseQueries() {

        super(connection, "Course");
    }

    public ObservableList<CourseInformation> getCoursesList() {
        ObservableList<CourseInformation> coursesInformation = FXCollections.observableArrayList();
        try {
            String SQL = "Select Course_Id,Course_name, Credits, Description,Teacher_Id, Section,Size, Remaining from Course;";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return coursesInformation;
            while (res.next()) {
                coursesInformation.add(new CourseInformation(
                        res.getString("Course_Id"),
                        res.getString("Course_name"),
                        res.getInt("Credits"),
                        res.getString("Teacher_Id"),
                        res.getString("Description"),
                        res.getString("Section"),
                        res.getInt("Size"),
                        res.getInt("Remaining")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return coursesInformation;
    }

    public void addCourse(String courseId, String courseName, Integer credits, String descriptions, String teacherId,
                          String section, Integer size, Integer remaining) {
        if (descriptions == null) {
            descriptions = "";
        }
        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("Course_name", courseName));
        courseInsert.add(new Pair<>("Description", descriptions));
        courseInsert.add(new Pair<>("Teacher_Id", teacherId));
        courseInsert.add(new Pair<>("Section", section));
        courseInsert.add(new Pair<>("Credits", credits));
        courseInsert.add(new Pair<>("Size", size));
        courseInsert.add(new Pair<>("Remaining", remaining));
        this.insertMultiValues(this.tableName, courseInsert);
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
        String SQL = String.format("DELETE FROM Course Where Course_Id='%s'",courseId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

}
