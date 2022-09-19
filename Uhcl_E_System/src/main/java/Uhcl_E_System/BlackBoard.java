package Uhcl_E_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "blackboard")

public class BlackBoard
{
	@Id
	@Column(name = "notes")
	private String notes;
	private String courseid;
	
	
	public BlackBoard() {
		super();
	}
	public BlackBoard(String courseid, String notes) {
		super();
		this.courseid = courseid;
		this.notes = notes;
	}
	
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
		
}
