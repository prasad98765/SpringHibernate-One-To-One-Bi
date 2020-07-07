package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create session

		Session session = factory.getCurrentSession();

		try {

			// create a objects
			Instructor tempInstructor = new Instructor("Prasad", "Chaudhari", "parasd@gamil.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.luv2code.com/youtube",
					"Luv 2 Code!!!");

//			Instructor tempInstructor = 
//					new Instructor("snehal","Chaudhari","snehald@gamil.com");
//			
//			InstructorDetail tempInstructorDetail = 
//					new InstructorDetail("https://www.youtube.com","Guiter....");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will also save the details Object
			// Because of Cascadetype.All
			//
			System.out.println("saving Instrudtor---->" + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done...........");
		} finally {
			factory.close();
		}
	}

}
