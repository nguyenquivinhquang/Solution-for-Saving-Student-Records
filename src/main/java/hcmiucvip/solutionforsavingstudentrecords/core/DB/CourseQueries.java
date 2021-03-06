package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.SchoolYear;
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

    public CourseInformation getCourseFromCourseId(String courseId) {
        CourseInformation course = new CourseInformation();
        try {
            String SQL = "SELECT [Course_Id]\n" +
                    "      ,[Course_name]\n" +
                    "      ,[Credits]\n" +
                    "      ,[Description]\n" +
                    "  FROM [StudentRecord].[dbo].[Course]\n" +
                    "  where Course_Id='%s'";
            SQL = String.format(SQL, courseId);
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);

            while (res.next()) {
                course.setCourseTitle(res.getString("Course_name").trim());
                course.setCourseId(courseId);
                course.setCourseDescription(res.getString("Description").trim());
                course.setCourseCredits(res.getInt("Credits"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    public ObservableList<CourseInformation> getClassesList() {
        ObservableList<CourseInformation> coursesInformation = FXCollections.observableArrayList();
        try {
            String SQL = "Select Course_Id, Section,Teacher_Id,Size,Remaining from Class;";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return coursesInformation;
            while (res.next()) {
                CourseInformation course = new CourseInformation();
                course.setCourseId(res.getString("Course_Id").trim());
                course.setCourseSection(res.getString("Section").trim());
                course.setTeacherId(res.getString("Teacher_Id").trim());
                course.setRemaining(res.getInt("Remaining"));
                course.setSize(res.getInt("Size"));
                coursesInformation.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getCoursesList");
        }

        return coursesInformation;
    }

    public ObservableList<CourseInformation> getCoursesList() {
        ObservableList<CourseInformation> coursesInformation = FXCollections.observableArrayList();
        try {
            String SQL = "Select Course_Id, Description,Course_name,Credits from Course;";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return coursesInformation;
            while (res.next()) {
                CourseInformation course = new CourseInformation();
                course.setCourseId(res.getString("Course_Id").trim());
                course.setCourseDescription(res.getString("Description").trim());
                course.setCourseTitle(res.getString("Course_name").trim());
                course.setCourseCredits(res.getInt("Credits"));
                coursesInformation.add(course);
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
            String SQL = "Select Course_Id from Enrolled_Class where Student_Id='%s';";
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
        courseInsert.add(new Pair<>("Midterm_percentage", 30));
        courseInsert.add(new Pair<>("[Inclass_percentage]", 30));
        courseInsert.add(new Pair<>("[Final_percentage]", 40));
        String year = String.valueOf(SchoolYear.Year), semester = String.valueOf(SchoolYear.Semester);
        courseInsert.add(new Pair<>("[Year-Semester]", year+'-'+semester));
        this.insertMultiValues("Class", courseInsert);
    }

    public void deleteClass(String courseId, String teacherId, String section) {
        String SQL = "delete from %s WHERE %s='%s' and %s='%s' and %s='%s' ";
        SQL = String.format(SQL, "Class", "Course_Id", courseId,
                "Teacher_Id", teacherId, "Section", section);
        System.out.println(SQL);
        runSetQuery(SQL);
    }
    public void runDelQuery(String SQL) {

    }
    public void addCourse(String courseId, String courseTitle, String descriptions, Integer credits) {
        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("Description", descriptions));
        courseInsert.add(new Pair<>("Course_name", courseTitle));
        courseInsert.add(new Pair<>("Credits", credits));
        this.insertMultiValues(this.tableName, courseInsert);
    }

    public void addCourseStudentRegistered(String studentId, String courseId, String teacherId, String section) {
        ArrayList<Pair<String, Object>> courseInsert = new ArrayList<>();
        courseInsert.add(new Pair<>("Course_Id", courseId));
        courseInsert.add(new Pair<>("In_class", 0));
        courseInsert.add(new Pair<>("Midterm", 0));
        courseInsert.add(new Pair<>("Final", 0));
//        courseInsert.add(new Pair<>("Total", 0));
        courseInsert.add(new Pair<>("Student_Id", studentId));
        courseInsert.add(new Pair<>("Teacher_Id", teacherId));
        courseInsert.add(new Pair<>("Section", section));
        String year = String.valueOf(SchoolYear.Year), semester = String.valueOf(SchoolYear.Semester);
        courseInsert.add(new Pair<>("[Year-Semester]", year+'-'+semester));

        this.insertMultiValues("Enrolled_Class", courseInsert);
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



    public void updateCourseRemaining(String courseId, String teacherId, String section, Integer newValue) {
        String SQL = "UPDATE Class SET Remaining=%s WHERE [Course_Id]='%s' and [Teacher_Id]='%s' and [Section]='%s' ";
        SQL = String.format(SQL, newValue, courseId, teacherId, section);
        System.out.println(SQL);
        runSetQuery(SQL);

    }
    public void increaseCourseRemaining(String courseId, String teacherId, String section) {
        Integer remaining = getRemainSlot(courseId, teacherId, section);
        updateCourseRemaining(courseId, teacherId, section, remaining + 1);

    }
    public void decreaseCourseRemaining(String courseId, String teacherId, String section) {
        Integer remaining = getRemainSlot(courseId, teacherId, section);
        if (remaining > 0)
            updateCourseRemaining(courseId, teacherId, section, remaining - 1);

    }
    private void changeValueColumn(String courseId, String columnName, String newValue) {
        updateRowString("Course_Id", courseId, columnName, newValue);
    }

    public void deleteCourse(String courseId) {
        String SQL = String.format("DELETE FROM Course Where Course_Id='%s'", courseId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public int getRemainSlot(String courseId, String teacherId, String section) {
        String SQL = String.format("select Remaining " +
                "FROM Class Where Course_Id='%s' and Teacher_Id='%s' and Section='%s'", courseId, teacherId, section);
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


    public double getPercentage(String courseId, String section, String teacherId, String percentageRow) {
        String SQL = "SELECT [Section]\n" +
                "      ,[Size]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Teacher_Id]\n" +
                "      ,[Midterm_percentage]\n" +
                "      ,[Inclass_percentage]\n" +
                "      ,[Final_percentage]\n" +
                "  FROM [StudentRecord].[dbo].[Class]\n" +
                "  where Course_Id = '%s' and Teacher_Id = '%s' and Section = '%s';";

        SQL = String.format(SQL, courseId, teacherId, section );
        ResultSet res = runGetQuery(SQL);
        try {
            res.next();
            return res.getInt(percentageRow);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getPercentage-" + percentageRow);
        }
        return 0;
    }

    public String getCourseSection(String courseId) {
        return getCourseValStringType("Section", courseId);

    }

    public String getCourseName(String courseId) {
        return getCourseValStringType("Course_name", courseId);

    }

    public String getCourseTitle(String courseId) {
        return getCourseValStringType("Course", "Course_name", "Course_Id", courseId).trim();

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
        SQL = String.format(SQL, "Enrolled_Class", studentId, courseId);
        return runSetQuery(SQL);
    }
}
