package DTO;

public class StudentGradeDTO {
    private int enrollmentId;
    private CourseDTO course;
    private StudentDTO student;
    private float grade;

    public StudentGradeDTO() {}

    public StudentGradeDTO(int enrollmentId, CourseDTO course, StudentDTO student, float grade) {
        this.enrollmentId = enrollmentId;
        this.course = course;
        this.student = student;
        this.grade = grade;
    }
    public StudentGradeDTO(Object[] rowData) {
        this.enrollmentId = (int) rowData[0];
        int studentId = (int) rowData[1];
        String studentFullName = (String) rowData[2];
        this.student = new StudentDTO(studentId, studentFullName, "", null); // Điều chỉnh tùy theo constructor của lớp StudentDTO

        this.course = new CourseDTO();
        this.course.setCourseId((int) rowData[3]);
        this.course.setTitle((String) rowData[4]);
        this.course.setCredits((int) rowData[5]);

        this.grade = (float) rowData[6];
}
    public Object[] toObject() {
        return new Object[] {
            enrollmentId, student.getPersonId(), student.getFullName(),
            course.getCourseId(), course.getTitle(), course.getCredits(), grade
        };
    }

    @Override
    public String toString() {
        return "StudentGradeDTO{" +
                "enrollmentId=" + enrollmentId +
                ", courseId=" + course.getCourseId() +
                ", studentId=" + student.getPersonId() +
                ", grade=" + grade +
                '}';
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
