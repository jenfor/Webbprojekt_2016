package com.united.view.moments;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("deleteMoment")
@RequestScoped 
public class DeleteMomentBB {

    private String id;
    private String name;
    private String courseId;

    @Override
    public String toString() {
        return "DeleteStudentBB{" + "id=" + id + ", name=" + name + '}';
    }  
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
}