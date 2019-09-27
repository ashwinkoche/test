package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create object
			Instructor tempInstructor = new Instructor("ashwin","koche","ashwin.koche@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("abc.com","luv 2 code");
			
			//associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start the transaction
			session.beginTransaction();
			
			//save the object
			//it will save detail object as well
			//as cascading is there
			System.out.println("Saving Instructor:" + tempInstructor);
			session.save(tempInstructor);

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
	}

}
