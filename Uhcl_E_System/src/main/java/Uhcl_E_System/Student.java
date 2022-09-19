package Uhcl_E_System;

import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "studentregisteredcourse")
public class Student {
	@Id
	@Column(name = "courseid")
	private String courseID;
	@Column(name = "student")
	private String student;
	@Column(name = "instructor")
	private String instructor;

	public void studentWelcome(String loginID) 
	{
		
		System.out.println();
		System.out.println("Welcome to UHCL eServices");
		System.out.println("v : view my courses");
		System.out.println("r : register a course");
		System.out.println("x : Logout");
		System.out.println();
		Scanner input = new Scanner(System.in);
		String selection = input.nextLine();
		while(!selection.equals("x"))
		{
			if(selection.equals("v"))
			{
				String select = "1";
				AppMain.studentViewRegisteredCourses(loginID, select);
			}
			
			else if(selection.equals("r"))
			{
				AppMain.registerCourse(loginID);
				
			}
			
			System.out.println("Welcome to UHCL eServices");
			System.out.println("v : view my courses");
			System.out.println("r : register a course");
			System.out.println("x : Logout");
			System.out.println();
			selection = input.nextLine();
		}
		if (selection.equals("x"))
		{
			E_Services main = new E_Services();
			main.mainMenu();
		}
		
	}
	
	public void viewStudentRegistered(String courseid2) 
	{
		
		AppMain.viewStudentRegistered(courseid2);
		
	}
	
	public void bbStudentWelcome(String loginID, String selection) 
	{
		System.out.println("**************************************");
		System.out.println("Welcome to BlackBoard");
		System.out.println("Select Your Course");
		AppMain.studentViewRegisteredCourses(loginID,selection);		
	}
	
	public void getCourseSelection(String courseId2, String loginID, String selection) 
	{
		Scanner input = new Scanner(System.in);
		String status;
			
			System.out.println("v: View Course Note");
			System.out.println("x: leave the Course");
			status = input.nextLine();
			while(!status.equals("x"))
			{	
				if (status.equals("v"))
				AppMain.viewCourseNotes(courseId2);
				
				System.out.println("v: View Course Note");
				System.out.println("x: leave the Course");
				status = input.nextLine();
			}
			if (status.equals("x"))
			{
				bbStudentWelcome(loginID, selection);
			}
		
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Student(String courseID, String student, String instructor) {
		super();
		this.courseID = courseID;
		this.student = student;
		this.instructor = instructor;
	}

	public Student() {
		
	}

	

	

	
	
	

}
