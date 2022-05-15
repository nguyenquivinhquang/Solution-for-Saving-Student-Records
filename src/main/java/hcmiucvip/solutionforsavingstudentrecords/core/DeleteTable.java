package hcmiucvip.solutionforsavingstudentrecords.core;

import hcmiucvip.solutionforsavingstudentrecords.core.DB.*;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class DeleteTable extends Querier {
    StudentQueries studentQueries = new StudentQueries();
    TeacherQueries teacherQueries = new TeacherQueries();
    CourseQueries courseQueries = new CourseQueries();
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public DeleteTable() {
        super(connection, "temp");
    }

    public void deleteCourse(String courseId) {
        ObservableList<CourseInformation> classLists = courseQueries.getClassesList();
        for (CourseInformation course : classLists) {
            if (course.getCourseId().trim().equals(courseId)) {
                String teacherId = course.getTeacherId().trim();
                String section = course.getCourseSection().trim();
                deleteClass(courseId, teacherId, section);
            }
        }
        String SQL = String.format("DELETE FROM Course Where Course_Id='%s'", courseId);
        System.out.println(SQL);
        runSetQuery(SQL);

    }

    public void deleteClass(String courseId, String teacherId, String section) {
        String year = String.valueOf(SchoolYear.Year);
        String semester = String.valueOf(SchoolYear.Semester);
        String academicYear = year + '-' + semester;

        deleteEnrolledClass(courseId, teacherId, section, academicYear);
        String SQL = "delete  from Class WHERE Teacher_Id='%s' and Course_Id='%s' and Section='%s' and [Year-Semester]='%s' ";
        SQL = String.format(SQL, teacherId, courseId, section, academicYear);
        System.out.println(SQL);
        runSetQuery(SQL);

    }

    public void deleteEnrolledClass(String courseId, String teacherId, String section, String academicYear) {
        String SQL = "delete  from Enrolled_Class WHERE Teacher_Id='%s' and Course_Id='%s' and Section='%s' and [Year-Semester]='%s' ";
        SQL = String.format(SQL, teacherId, courseId, section, academicYear);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void deleteTeacher(String teacherId) {
        ObservableList<CourseInformation> courseInformations = courseQueries.getClassesList();
        for (CourseInformation courseInformation : courseInformations) {
            if (courseInformation.getTeacherId().equals(teacherId)) {
                deleteCourse(courseInformation.getCourseId());
            }
        }
        String SQL = "delete  from Teacher WHERE Teacher_Id='%s'";
        SQL = String.format(SQL, teacherId);
        System.out.println(SQL);
        runSetQuery(SQL);

        SQL = "delete  from [User] WHERE Username='%s'";
        SQL = String.format(SQL, teacherId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void deleteStudent(String studentId) {
        String SQL = "delete  from Enrolled_Class WHERE Student_Id='%s' ";
        SQL = String.format(SQL, studentId);
        System.out.println(SQL);
        runSetQuery(SQL);

        SQL = "delete  from Student WHERE Student_Id='%s'";
        SQL = String.format(SQL, studentId);
        System.out.println(SQL);
        runSetQuery(SQL);

        SQL = "delete  from [User] WHERE Username='%s'";
        SQL = String.format(SQL, studentId);
        System.out.println(SQL);
        runSetQuery(SQL);

    }
}
