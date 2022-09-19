package Uhcl_E_System;

import java.util.Scanner;

import javax.persistence.*;

@Entity
@Table(name= "uhcluser")

public class E_Services 
{
	@Id
	@Column(name = "id")
	private String id;
	
	private String password;
	private String major;
	private String type;
	
	public E_Services() {
		
	}

	public E_Services(String id, String password, String major, String type) {
		super();
		this.id = id;
		this.password = password;
		this.major = major;
		this.type = type;
	}
	
	public void mainMenu()
	{
		System.out.println("GO Hawks!");
		System.out.println("1: e-Services");
		System.out.println("2: Blackboard");
		System.out.println("x: Leave");
		Scanner input = new Scanner(System.in);
		String selection = input.nextLine();
		if(selection.equals("x"))
		{
			System.out.println("**************************************");
			System.out.println("Thank you for using UHCL_E_System");
			System.out.println("**************************************");
			System.exit(0);
		}
		
				System.out.println("Please enter your login id");
				String loginID = input.nextLine();
				System.out.println("Please enter your password");
				String password = input.nextLine();
				AppMain.login(loginID, password, selection);
			
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
