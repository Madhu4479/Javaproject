package com.Term.Entity.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.Term.Entity.Term;
import com.Term.Hibernate.util.HibernateUtil;

import javassist.bytecode.Descriptor.Iterator;

public class TermService {
	public int lastUpdatedId;
	public Term td;
	boolean b = false;
	public Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Term getTd() {
		return td;
	}

	public void setTd(Term td) {
		this.td = td;
	}

	public TermService() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		this.session = session;

	}

	public int getLastUpdatedId() {
		return lastUpdatedId;
	}

	public void setLastUpdatedId(int lastUpdatedId) {
		this.lastUpdatedId = lastUpdatedId;
	}
	/**
	 * Save the term details into db it includes check condition also to check wether the record
	 * is existed in DB or not if it is not exist records will save into DB and prints the term
	 * is saved successfully
	 * in console as already submitted
	 * @return
	 */
	
	boolean saveTerm(Term tt) {
		session.beginTransaction();
		boolean b = checkTerm(session, tt);
		if (b == false) {
			Integer lastUpdatedId = (Integer) session.save(tt);
			TermService ts = new TermService();
			ts.setLastUpdatedId(lastUpdatedId);
			session.getTransaction().commit();
			session.close();
		}
		System.out.println("the term is saved succesfully:" + b);
		return b;
	}
	/**
	 * check the termName from db if it is exist in DB it will store the data and prints
	 * in console as already submitted
	 * @return
	 */


	private boolean checkTerm(Session session, Term tt) {

		List<String> list = session.createQuery("select t.termName from Term t").list();

		for (String st : list) {

			if (st.equals(tt.getTermName())) {
				System.out.println("Term Already Submitted:");
				session.close();
				b = true;
			}
		}
		return b;
	}
	/**
	 * to get All Term details from DB and returns list of term details
	 * @return
	 */

	public List<Term> getAllTerms() {
		session.beginTransaction();
		List<Term> lis = session.createQuery("from Term").list();

		for (Term tm : lis) {
			System.out.println(tm.getId() + " " + tm.getTermName());
		}
		return lis;
	}
	/**
	 * to get LastUpdated Term from Database 
	 * @returnertr
	 */

	public int getLastSavedId() {
		NativeQuery query = session.createNativeQuery("SELECT Id FROM terms ORDER BY Id DESC LIMIT 1");
		// int lastUpdatedValue=(int)query;
		int lastUpdatedValue = ((Number) query.getSingleResult()).intValue();
		System.out.println("the result is" + lastUpdatedValue);
		return lastUpdatedValue;
	}

}
