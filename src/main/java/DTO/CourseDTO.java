package DTO;

public class CourseDTO {
    private int courseId;
    private String title;
    private int credits;
    private DepartmentDTO department; // Giữ lại đoạn này
    private int departmentId; // Giữ lại đoạn này

    public CourseDTO() {}

    public CourseDTO(int courseId, String title, int credits, int departmentId) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", department=" + department +
                ", departmentId=" + departmentId +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getCourseType() {
        return "Online"; // Mặc định là Online
    }
}
