package com.orangeandbronze.enlistment;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.lang3.Validate;

class EnlistmentsForSemester {
	
	private final Term term;
	private final int year;
	private final Collection<Student> students = new HashSet<>();
	private final Collection<Section> sections = new HashSet<>();
	
	EnlistmentsForSemester(Term term, int year) {
		this(term, year, Collections.emptyList(), Collections.emptyList());
	}
	
	EnlistmentsForSemester(Term term, int year, Collection<Student> students, Collection<Section> sections){
		Validate.isTrue(year > 0, "year must be non-negative, was %d", year);
		Validate.notNull(year,"year param must not be null");
		this.term = term;
		this.year = year;	
		this.students.addAll(students);
		this.sections.addAll(sections);
	}

	@Override
	public String toString() {
		return "EnlistmentsForSemester [term=" + term + ", year=" + year + ", students=" + students + ", sections="
				+ sections + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sections == null) ? 0 : sections.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnlistmentsForSemester other = (EnlistmentsForSemester) obj;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (term != other.term)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	
	
	
}
