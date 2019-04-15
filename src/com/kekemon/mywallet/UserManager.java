package com.kekemon.mywallet;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.kekemon.mywallet.model.UserProfile;

public class UserManager {
	private static SessionFactory factory; 

	static{
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static void addDemoUser(){
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			UserProfile userProfile = new UserProfile();
			userProfile.setAccountName("Kazi Emrul Kayes Emon");
			userProfile.setCurrentAmmount(50000);
			userProfile.setMobile("01714351709");
			userProfile.setPassword("351709");
			
			session.save(userProfile); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}
	
	public static UserProfile getUserBy(String mobile, String password){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<UserProfile> employees = session.createCriteria(UserProfile.class).list();
			for (Iterator<UserProfile> iterator = employees.iterator(); iterator.hasNext();){
				UserProfile userProfile = (UserProfile) iterator.next(); 
				if(userProfile.getPassword().equals(password) && userProfile.getMobile().equals(mobile) ){
					tx.commit();
					return userProfile;
				}
			}
			tx.commit();
		} catch (HibernateException e) {
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		addDemoUser();
		UserProfile userProfile = UserManager.getUserBy("01714351709", "351709");
		System.out.println(userProfile.getAccountName());
	}
}
