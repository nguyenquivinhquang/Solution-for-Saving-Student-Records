package hcmiucvip.solutionforsavingstudentrecords.core;

public class TeacherInformation {
    private String firstName, lastName, mail;
    private String username, teacherId, department;

    public TeacherInformation(String teacherId, String firstName, String lastName,
                              String username, String department, String mail) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
