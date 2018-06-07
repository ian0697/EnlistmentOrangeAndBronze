package com.orangeandbronze.enlistment;

import java.util.*;
import org.apache.commons.lang3.*;

class Student {
	private final int studentNumber;
	private final Collection<Section> sections = new HashSet<>();
	
	Student(int studentNumber){
		this(studentNumber, Collections.emptyList());
	}
	
	Student(int studentNumber, Collection<Section> sections){
		Validate.isTrue(studentNumber > 0, "studentNumber must be non-negative, was %d", studentNumber);
		this.studentNumber = studentNumber;	
		this.sections.addAll(sections);
	}
	
	void enlist(Section newSection) {
		Validate.notNull(newSection, "section param must not be null");
		for(Section currentSection: sections) {
			currentSection.checkForConflict(newSection);
		}

		newSection.getRoom().incrementCurrent();
		sections.add(newSection);
	}
	
	Collection<Section> getSections() {
		return new ArrayList<>(sections);
	}
	

	@Override
	public String toString() {
		return "Student# " + studentNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentNumber;
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
		Student other = (Student) obj;
		if (studentNumber != other.studentNumber)
			return false;
		return true;
	}
	
}
