/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.lab2.client;

import it.pastorini.lab2.client.resources.Course;
import it.pastorini.lab2.client.resources.Student;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.xml.bind.JAXB;

/**
 *
 * @author biar
 */
public class RestfulClient {
    
    private static String BASE_URL = "http://localhost:8080/lab2/";
    private static String COURSES_URL = BASE_URL + "courses/";
    private static String STUDENTS_PART_URL = "students/";
    
    private static Client client = ClientBuilder.newClient();
  
    private Course getCourse(int courseId) {
        return client
                .target(COURSES_URL)
                .path(String.valueOf(courseId))
                .request(MediaType.APPLICATION_XML)
                .get(Course.class);
    }
    
    private Student getStudent(int courseId, int studentId) {
        return client
                .target(COURSES_URL)
                .path(String.valueOf(courseId))
                .path(STUDENTS_PART_URL)
                .path(String.valueOf(studentId))
                .request(MediaType.APPLICATION_XML)
                .get(Student.class);
    }
    
    private void postStudent(int courseId, Student student) {
        Response response =client
            .target(COURSES_URL)
            .path(String.valueOf(courseId))
            .path(STUDENTS_PART_URL)
            .request(MediaType.APPLICATION_XML)
            .post(Entity.entity(student, MediaType.APPLICATION_XML));
        
        StatusType responseStatus = response.getStatusInfo();
        System.out.println(responseStatus);
       
    }
    
    private void deleteStudent(int courseId, Student student) throws Exception {
        deleteStudent(courseId, student.getId());
    }
    
    private void deleteStudent(int courseId, int studentId) throws Exception {
        Response response = client
            .target(COURSES_URL)
            .path(String.valueOf(courseId))
            .path(STUDENTS_PART_URL)
            .path(String.valueOf(studentId))
            .request(MediaType.APPLICATION_XML)
            .delete();
        
        StatusType responseStatus = response.getStatusInfo();
        if (responseStatus.getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new Exception(String.format("%s (%d): %s", responseStatus.getFamily(), responseStatus.getStatusCode(), responseStatus.getReasonPhrase()));
        }
    }
    
    public static void main(String[] argc) throws Exception {
        RestfulClient client = new RestfulClient();
        
        Course course = client.getCourse(1);
        System.out.println(course);
        
        for (Student student : course.getStudents()) {
            System.out.println(client.getStudent(1, student.getId()));
        }
        
        Student claudio = new Student();
        claudio.setId(93);
        claudio.setName("Claudio");
        
        client.postStudent(1, claudio);
        
        Course newCourse = client.getCourse(1);
        System.out.println(newCourse);
    }
}
