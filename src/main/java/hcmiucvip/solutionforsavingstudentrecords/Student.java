package hcmiucvip.solutionforsavingstudentrecords;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty adminTableDataStudentName;

    public Student(String adminTableDataStudentName) {
        this.adminTableDataStudentName = new SimpleStringProperty(adminTableDataStudentName);
    }
    public String getAdminTableDataStudentName() {
        return adminTableDataStudentName.get();
    }

    public SimpleStringProperty adminTableDataStudentNameProperty() {
        return adminTableDataStudentName;
    }

    public void setAdminTableDataStudentName(String adminTableDataStudentName) {
        this.adminTableDataStudentName.set(adminTableDataStudentName);
    }
}
