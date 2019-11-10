package com.Term.Entity.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.Term.Entity.Term;

public class TermTest {
	
	/*@BeforeClass
	public void start() {
		TermService ts;
	}*/
	

	/*@Test
	public void test() {
		Term t=new Term();
		t.setId(17);
		t.setTermName("Seventeenth Term");
		TermService ts=new TermService();
		//it will success once record automatically store
		assertEquals("Saved Succesfully",true,ts.saveTerm(t));
		
	}*/
	/*@Test
	public void test2() {
		TermService ts=new TermService();
		assertEquals("Last Used Id is",16,ts.getLastUpdatedId());	
	}*/

	/*@Test
	public void test() {
		TermService ts=new TermService();
		ts.getAllTerms();
		assertEquals("got All Terms",ts.getAllTerms());
		
	}
	*/
	@Test
	public void test2() {
		TermService ts=new TermService();
		assertEquals(17,ts.getLastSavedId());
		
		
		
	}

}
