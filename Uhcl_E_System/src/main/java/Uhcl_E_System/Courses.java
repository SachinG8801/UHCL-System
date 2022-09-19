package Uhcl_E_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "course")

public class Courses {
	
		@Id
		@Column(name = "courseID")
		private String courseid;
		private String instructor;
		private String major;
		private String cap;
		private String enrolled;
		private String status;
		public Courses(String courseid, String instructor, String major, String cap, String enrolled, String status) {
			
			this.courseid = courseid;
			this.instructor = instructor;
			this.major = major;
			this.cap = cap;
			this.enrolled = enrolled;
			this.status = status;
		}
		public Courses() {
			
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
