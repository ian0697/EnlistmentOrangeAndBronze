package com.orangeandbronze.enlistment;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

class Section {
	
	private final String sectionId;
	private final Schedule schedule;
	private final Subject subject;
	private final Room room;
	private final Collection<EnlistmentsForSemester> enlistmentsForSemester = new HashSet<>();
	
	Section(String sectionId, Schedule schedule, Subject subject, Room room){
		this(sectionId, schedule, subject, room, Collections.emptyList());
	}
	
	Section(String sectionId, Schedule schedule, Subject subject, Room room, Collection<EnlistmentsForSemester> enlistmentsForSemester){
		Validate.notBlank(sectionId, "sectionId param must not be null or blank, was %s", sectionId);
		Validate.isTrue(StringUtils.isAlphanumeric(sectionId),"sectionId must be alphanumeric, was %s", sectionId);
		Validate.notNull(schedule,"schedule param must not be null");
		Validate.notNull(subject,"subject param must not be null");
		Validate.notNull(room,"room param must not be null");
		this.schedule = schedule;
		this.sectionId = sectionId;
		this.subject = subject;
		this.room = room;
		this.enlistmentsForSemester.addAll(enlistmentsForSemester);
	}
	
	Room getRoom() {
		return room;
	}

	void checkForConflict(Section other) {
		if(this.schedule.equals(other.schedule)) {
			throw new ScheduleConflictException("Current section " + this + 
					" with schedule " + this.schedule + " has a schedule conflict with new section " 
					+ other + " with schedule " + other.schedule);
		}
		
		if(this.subject.equals(other.subject)) {
			throw new SubjectConflictException("Current section " + this + 
					" with subject " + this.subject + " has a subject conflict with new section " 
					+ other + " with subject " + other.subject);
		}
	}

	@Override
	public String toString() {
		return sectionId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
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
		Section other = (Section) obj;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		return true;
	}

	
}
