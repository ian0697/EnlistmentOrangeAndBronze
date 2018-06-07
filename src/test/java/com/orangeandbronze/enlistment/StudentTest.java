package com.orangeandbronze.enlistment;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class StudentTest {

	@Test
	public void enlist_two_sections_no_conflict() {
		// Given two section where 2 sections are not overlapping, and 1 student
		Student student = new Student(1);
		Section sec1 = new Section("ABC1235", new Schedule(Days.MTH, Period.P0830), new Subject("subj01"), new Room("R001", 40));
		Section sec2 = new Section("XYZ456", new Schedule(Days.TF, Period.P1000), new Subject("subj02"), new Room("R002", 40));
		
		// When the student enlists in both sections 
		student.enlist(sec1);
		student.enlist(sec2);
		
		// Then both sections should be found in the student's collection of sections
		Collection<Section> sections = student.getSections();
		assertTrue(sections.containsAll(Arrays.asList(sec1, sec2)));
		assertEquals(2, sections.size());
	}
	
	@Test 
	public void enlist_checkCapacity_if_30_students_enlist_in_same_section() {
		// Given 1 room with 40 MAX capacity, & 30 students to enlist in one section
		Room room = new Room("R001", 40);
		Section sec1 = new Section("BCS42", new Schedule(Days.MTH, Period.P0830), new Subject("subj01"), room);
		
		// when 30 students enlist in a section
		for(int counter = 1; counter < 31; counter++) {
			Student student = new Student(counter);
			student.enlist(sec1);
		}
		
		// then the room capacity must be 30
		int actual = room.getCurrentCapacity();
		int expected = 30;
		
		assertEquals(expected,actual);
	}
	
	@Test (expected = RoomCapacityExceededException.class)
	public void enlist_checkCapacity_if_room_capacity_exceeded(){
		//Given 1 room with 40 MAX Capacity, & 50 students to enlist in one section
		Room room = new Room("R001", 40);
		Section sec1 = new Section("BCS42", new Schedule(Days.MTH, Period.P0830), new Subject("subj01"), room);
		
		// when 50 students enlist in a section
		for(int counter = 1; counter < 51; counter++) {
			Student student = new Student(counter);
			student.enlist(sec1);
		}
		
		// then enlist method must throw RoomCapacityExceededException
		int actual = room.getCurrentCapacity();
		int expected = 50;
		
		assertEquals(expected,actual);
	}
	
	@Test(expected = SubjectConflictException.class)
	public void enlist_section_with_same_subject() {
		//Given two sections with same subject, 1 student
		Student student = new Student(100);
		Section sec1 = new Section("BCS23", new Schedule(Days.MTH, Period.P0830), new Subject("subj01"), new Room("R001", 40));
		Section sec2 = new Section("BCS24", new Schedule(Days.TF, Period.P1300), new Subject("subj01"), new Room("R001", 40));
		
		// When student enlist in both section with same subject
		student.enlist(sec1);
		student.enlist(sec2);
		
		// Then on the second enlistment an exception should be thrown
		Collection<Section> sections = student.getSections();
		assertTrue(sections.containsAll(Arrays.asList(sec1, sec2)));
		assertEquals(2, sections.size());
	}
	
	@Test (expected = ScheduleConflictException.class)
	public void enlist_two_sections_same_schedule() {
		// Given two section where schedules are the same, and 1 student
		Student student = new Student(1);
		Section sec1 = new Section("ABC123456T", new Schedule(Days.MTH, Period.P0830), new Subject("subj01"), new Room("R001", 40));
		Section sec2 = new Section("XYZ456", new Schedule(Days.MTH, Period.P0830), new Subject("subj02"), new Room("R002", 40));
		
		// When the student enlists in both sections 
		student.enlist(sec1);
		student.enlist(sec2);
		
		// Then on the second enlistment an exception should be thrown
		Collection<Section> sections = student.getSections();
		assertTrue(sections.containsAll(Arrays.asList(sec1, sec2)));
		assertEquals(2, sections.size());
	}

}
