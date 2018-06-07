package com.orangeandbronze.enlistment;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

class Subject {
	private final String subjectId;
	private final Collection<Subject> prerequisites = new HashSet<>();
	
	Subject(String subjectId){
		this(subjectId, Collections.emptyList());
	}
	
	Subject(String subjectId, Collection<Subject> prerequisites){
		Validate.notBlank(subjectId, "subjectId param must not be null or blank, was %s", subjectId);
		Validate.isTrue(StringUtils.isAlphanumeric(subjectId),"subjectId must be alphanumeric, was %s", subjectId);
		Validate.isTrue(StringUtils.isAlphanumeric(subjectId),"subjectId must be alphanumeric, was %s", subjectId);
		this.subjectId = subjectId;
		this.prerequisites.addAll(prerequisites);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", prerequisites=" + prerequisites + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		Subject other = (Subject) obj;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}
	
	
}
