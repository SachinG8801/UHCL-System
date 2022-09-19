package Uhcl_E_System;

import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "course")

public class Faculty
{
	@Id
	@Column(name = "courseID")
	private String courseid;
	private String instructor;
	private String major;
	private String cap;
	private String enrolled;
	private String status;
	
	
	
	public Faculty() {
	
	}

	

	public Faculty(String courseid, String instructor, String major, String cap, String enrolled, String status) {
	
		this.courseid = courseid;
		this.instructor = instructor;
		this.major = major;
		this.cap = cap;
		this.enrolled = enrolled;
		this.status = status;
	}



	public void facultyWelcome(String loginID, String selection2)
	{
		System.out.println();
		System.out.println("Welcome to UHCL eServices");
		System.out.println("v : view my courses");
		System.out.println("x : Logout");
		System.out.println();
		Scanner input = new Scanner(System.in);
		String selection = input.nextLine();
		while(!selection.equals("x"))
		{
			if(selection.equals("v"))
			{
				AppMain.facultyViewCourses(loginID, selection2);
			}
			System.out.println("Welcome to UHCL eServices");
			System.out.println("v : view my courses");
			System.out.println("x : Logout");
			selection = input.nextLine();
		}
		
		if (selection.equals("x"))
		{
			E_Services e_Services = new E_Services();
			e_Services.mainMenu();
		}
				
	}
	public void bbFacultyWelcome(String loginID, String selection) 
	{
		System.out.println("**************************************");
		System.out.println("Welcome to BlackBoard");
		System.out.println("Select your Course");
		AppMain.facultyViewCourses(loginID, selection);	
		
		
	}
	
	public void getCourseSelection (String courseid, String loginID, String selection2) 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Please select your options");
		System.out.println("v : view course notes");
		System.out.println("p : post new course note");
		System.out.println("x : leave the course");
		String optionSelcection = input.nextLine();
		String newNote = "";
		while(!optionSelcection.equals("x"))
		{
			if (optionSelcection.equals("v"))
				AppMain.viewCourseNotes(courseid);
			else if (optionSelcection.equals("p"))
				{
					System.out.println("Please enter your new note: ");
					newNote = input.nextLine();
					AppMain.addCourseNotes(courseid, newNote);
					
				}
				System.out.println("Please select your options");
				System.out.println("v : view course notes");
				System.out.println("p : post new course note");
				System.out.println("x : leave the course");
				optionSelcection = input.nextLine();
		}
		if (optionSelcection.equals("x"))
		{
			bbFacultyWelcome(loginID, selection2);
		}
		
	}
	
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	
	
}
