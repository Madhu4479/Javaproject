package com.Term.Entity.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.Term.Entity.Term;
import com.Term.Hibernate.util.HibernateUtil;

public class TermService {
	public int lastUpdatedId;
	public Term td;
	
	 public Term getTd() {
		return td;
	}


	public void setTd(Term td) {
		this.td = td;
	}


	public TermService() {
		 Term td=new Term();
		 td.setId(10);
		 td.setTermName("nineth term");
		  this.td= td;
	 }

	
	public int getLastUpdatedId() {
		return lastUpdatedId;
	}
	public void setLastUpdatedId(int lastUpdatedId) {
		this.lastUpdatedId = lastUpdatedId;
	}
	public static void main(String[] args) {
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			TermService ts=new TermService();
			Term tt=ts.getTerm();
			saveTerm(session,tt);
		}
		catch(HibernateException e) {
			e.printStackTrace();		
		}
	}
	private static void saveTerm(Session session,Term tt) {
		session.beginTransaction();
		boolean b=checkTerm(session,tt);
            if(b==false)
              {
          		Integer lastUpdatedId=(Integer)session.save(tt);
          		TermService ts=new TermService();
          		ts.setLastUpdatedId(lastUpdatedId);
          		session.getTransaction().commit();
          		}
		
	}
	private static boolean checkTerm(Session session,Term tt) {
		boolean b=false;
		List<String> list=session.createQuery("select t.termName from Term t").list();
		
              for(String st:list) {
            	  System.out.println("its running");
            	  if(st.equals(tt.getTermName())) {
            		  System.out.println("Term Already Submitted:");
            		  b=true;
            	  }
              }
              return b;
	}
	private static Term getTerm() {
		/*Term t=new Term();
		t.setId(7);
		t.setTermName("leventh");*/
		return null;
	}
	private int getLastSavedId() {
		return getLastUpdatedId();
	}
	
	}

	
