package DTO;

import java.sql.Time;
import java.time.LocalTime;

public class OnsiteCourseDTO extends CourseDTO {
    private CourseDTO course;
    private String location;
    private String days;
    private Time time;

    public OnsiteCourseDTO(int courseId , String title, int credits, int departmentId, String location, String days, Time time) {
        super(courseId,title,credits,departmentId);
        this.location = location;
        this.days = days;
        this.time = time;
    }

    public OnsiteCourseDTO() {}

    // public Object[] toObject() {
    //     return new Object[]{
    //             course.getCourseId(), course.getTitle(), course.getCredits(),
    //             course.getDepartment().getName(), this.getLocation(),
    //             this.getDays(), this.getTime().toString()
    //     };
    // }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    public String getCourseType() {
        return "Onsite";
    }
}
