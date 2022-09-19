package Uhcl_E_System;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;



public class AppMain {
	
	protected static SessionFactory sessionFactory;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		AppMain app = new AppMain();
		E_Services user = new E_Services();
		app.setup();
		user.mainMenu();
		app.exit();

	}
	
	protected void setup()
	{
		//configure setting from hubernate.cfg.xml
		final StandardServiceRegistry registry = 
				new StandardServiceRegistryBuilder().configure().build();
		
			try
			{
				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				StandardServiceRegistryBuilder.destroy(registry);
			}	
	}
	
	protected void exit()
	{
		sessionFactory.close();
	}
	
	protected static void login(String loginID, String password, String selection)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		E_Services login = session.get(E_Services.class, loginID);
		
		if (selection.equals("1"))
		{
		
			if(login.getType().equals("Faculty"))
			{
				Faculty faculty = new Faculty();
				faculty.facultyWelcome(loginID, selection);
			}
			
			else if (login.getType().equals("Student"))
			{
				Student student = new Student();
				student.studentWelcome(loginID);
			}
		}
			
			else if (selection.equals("2"))
			{
			
				if(login.getType().equals("Faculty"))
				{
					Faculty faculty = new Faculty();
					faculty.bbFacultyWelcome(loginID, selection);
				}
				
				else if (login.getType().equals("Student"))
				{
					Student student = new Student();
					student.bbStudentWelcome(loginID,selection);
				}
		}
		
		
		session.getTransaction().commit();
		session.close();
	}

	public static void facultyViewCourses(String loginID, String selection2) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "From Faculty F where F.instructor = '"+loginID+"'";
		
		Query query = session.createQuery(hql);
		List result = query.list();
		
		 HashMap<Integer, String> map= new HashMap();
		ArrayList<Faculty> viewCourses = (ArrayList<Faculty>)result;
		int i=1;
		for(Faculty f : viewCourses)
		{
			
			if (selection2.equals("1"))
			{
				System.out.println("**************************************");
				System.out.println("CourseID: "+ f.getCourseid());
				Student getStudent = new Student();
				getStudent.viewStudentRegistered(f.getCourseid());
			}
		
			System.out.println();
			
			if(selection2.equals("2"))
			{
					
						map.put(i++, f.getCourseid());
			}
		}
		if(!map.isEmpty())
		{
			for (HashMap.Entry key : map.entrySet()) 
			{
		          System.out.println("COURSE ID "+ key.getKey() + ":" + key.getValue());
		    }
					System.out.println("x : leave Blackboard");
					System.out.println("**************************************");
			Scanner input = new Scanner(System.in);
			if (!input.hasNextInt())
			{
				E_Services back = new E_Services();
				back.mainMenu();
			}
			int num = input.nextInt();
			
			String courseId = "";
			if(map.containsKey(num)) 
			{
				//TODO add logic to store
				courseId = map.get(num);
				Faculty courseSelection = new Faculty();
				courseSelection.getCourseSelection(courseId,loginID,selection2);
			}
		}
		session.getTransaction().commit();
		session.close();
		
		
	}

	public static void studentViewRegisteredCourses(String loginID, String selection) 
	{
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "From Student S where S.student = '"+loginID+"'";
		
		Query query = session.createQuery(hql);
		List result = query.list();
		if(result.isEmpty())
		{
			System.out.println("**************************************");
			System.out.println("you do not have any registered courses");
			System.out.println("**************************************");
		}
		else
		{
			ArrayList<Student> viewCourses = (ArrayList<Student>)result;
			  HashMap<Integer, String> map= new HashMap();
			  int i = 1;
			for(Student s : viewCourses)
				{	
//				if(s.getCourseID().equals(""))
//					System.out.println("You do not have any course registered");						
					if (selection.equals("1"))
					{
							System.out.println("**************************************");
							System.out.print("CourseID: "+ s.getCourseID());
							System.out.print("    Instructor: "+ s.getInstructor());
							System.out.println();				
							System.out.println("**************************************");
					}
					else if (selection.equals("2"))
					{
							map.put(i++, s.getCourseID());
					}
				}
			if(!map.isEmpty())
			{
				for (HashMap.Entry key : map.entrySet()) 
				{
			          System.out.println("COURSE ID "+ key.getKey() + ":" + key.getValue());
			    }
						System.out.println("x : leave Blackboard");
						System.out.println("**************************************");
				Scanner input = new Scanner(System.in);
				if (!input.hasNextInt())
				{
					E_Services back = new E_Services();
					back.mainMenu();
				}
				int num = input.nextInt();
				
				String courseId = "";
				if(map.containsKey(num)) 
				{
					//TODO add logic to store
					courseId = map.get(num);
					Student courseSelection = new Student();
					courseSelection.getCourseSelection(courseId,loginID,selection);
				}
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	public static void viewStudentRegistered(String courseid2)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "From Student S where S.courseID = '"+courseid2+"'";
		
		Query query = session.createQuery(hql);
		List result = query.list();
		if(result.isEmpty())
		{
			System.out.print("Students enrolled: ");
			System.out.println("**************************************");
		}
			
		else
		{
			ArrayList<Student> viewStu = (ArrayList<Student>)result;
			System.out.print("Students enrolled: ");
			for(Student s : viewStu)
				{
				System.out.print(s.getStudent());
				System.out.println();
				System.out.println("**************************************");
				}
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	public static void registerCourse(String loginID) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		E_Services viewMaj = session.get(E_Services.class, loginID);
		
		String major = viewMaj.getMajor();
		
		String hql = "From Courses where major = '"+major+"'";
		
		Query query = session.createQuery(hql);
		List result = query.list();
		
        ArrayList<Courses> viewCourses = (ArrayList<Courses>)result;
        HashMap<Integer, String> map= new HashMap();
		int i=1;
		for(Courses course : viewCourses)
		{
			if(course.getStatus().equals("open"))
			{
				map.put(i++, course.getCourseid());
			}
		}
		
		System.out.println("**************************************");
		System.out.println("Welcome to register a new course");
		
		
		if(!map.isEmpty())
		{
			for (HashMap.Entry key : map.entrySet()) 
			{
				  System.out.println("**************************************");
		          System.out.println("COURSE ID "+ key.getKey() + ":" + key.getValue());
		    }
			System.out.println("Please enter the Id for which you would like to enroll");
			System.out.println("**************************************");
			
			Scanner input = new Scanner(System.in);
			if (!input.hasNextInt())
			{
				Student back = new Student();
				back.studentWelcome(loginID);
			}
			int num = input.nextInt();
			
			String courseId = "";
			if(map.containsKey(num)) {
				courseId = map.get(num);
				
				session.getTransaction().commit();
				session.close();
				
				updateCourse(courseId,loginID);
				
			}else {
				System.out.println("Please select a valid course ID");
			}
		}
		else
			System.out.println("You do not have any course to register");
			System.out.println("**************************************");
		
	}

	private static void updateCourse(String courseId, String loginID) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Courses status = session.load(Courses.class, courseId);
		status.setEnrolled("1");
		status.setStatus("closed");
		
		System.out.println("The course is added to your schedule");
		
		
		session.update(status);
		session.getTransaction().commit();
		session.close();
		
		getIns(courseId, loginID);
		
	}
	
	private static void getIns(String courseId, String loginID)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Courses getInstructor = session.get(Courses.class, courseId);
		String instructor = getInstructor.getInstructor();
		
		session.getTransaction().commit();
		session.close();
		
		updateRegcourse(courseId, loginID, instructor);
	}
	

	private static void updateRegcourse(String courseId, String loginID, String instructor2)
	{
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Student aNew = new Student(courseId,loginID, instructor2);
		
		session.save(aNew);
		
		session.getTransaction().commit();
		session.close();
	}

	public static void viewCourseNotes(String courseSelection) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		String hql = "From BlackBoard where courseID = '"+courseSelection+"'";
		
		Query query = session.createQuery(hql);
		List result = query.list();
		System.out.println("**************************************");
		System.out.println("Course notes of "+courseSelection+ ": ");
		if(result.isEmpty())
		{
			System.out.println(" None ");
			System.out.println("**************************************");
		}
		else
		{
			ArrayList<BlackBoard> viewCourse = (ArrayList<BlackBoard>)result;
			
			for(BlackBoard s : viewCourse)
				{			
				System.out.print( DateAndTime.DateTime() );
				System.out.print(" : "+s.getNotes());
				System.out.println();
				}
			System.out.println("**************************************");
		}
		session.getTransaction().commit();
		session.close();
				
	}

	public static void addCourseNotes(String courseSelection, String newNote) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		BlackBoard aNewPost = new BlackBoard(courseSelection, newNote);
		
		System.out.println("Your note is added to the course. Your students can see it now");
		
		session.save(aNewPost);
		
		session.getTransaction().commit();
		session.close();
		
	}

	public static class DateAndTime
	{
		
		public static final String DATE_FORMAT_NOW = "EEE, d MMM yyyy HH:mm:ss";

	    //get the date and time now as a String
	    public static String DateTime()
	    {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	        return sdf.format(cal.getTime());
	    }
	

	}
}
