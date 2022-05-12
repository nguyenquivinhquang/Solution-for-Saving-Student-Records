package hcmiucvip.solutionforsavingstudentrecords.core;

import javafx.beans.property.SimpleStringProperty;

public class StudentInformation {
    private String firstName, lastName, email, address, bId, mail;
    private String username;
    private Integer academicYear;
//    public final SimpleStringProperty abc;
    private final SimpleStringProperty studentId,day;
    public StudentInformation(String studentId, String firstName, String lastName, Integer academicYear, String birthDay, String mail){
        this.studentId = new SimpleStringProperty(studentId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicYear = academicYear;
        this.day = new SimpleStringProperty(birthDay);
        this.mail = mail;
        this.username = studentId;
        bId = "";
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public String getAddress() {
        return address;
    }

    public String getbId() {
        return bId;
    }
    public String getUsername(){return username;}

    public String getDay() {
        return day.get();
    }
    public final SimpleStringProperty dayProperty(){
        return day;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getMail() {
        return mail;
    }

    public String getLastName() {
        return lastName;
    }

    public final String getStudentId() {
        return studentId.get();
    }
    public final SimpleStringProperty studentIdProperty(){
        return studentId;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public void setDay(String birthDay) {
        this.day.set(birthDay);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String toString() {
        return studentId+"-"+firstName+'-'+lastName+'-'+academicYear+'-'+day.get();
    }
}
