package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

class Room {
	
	private final String roomName;
	private final int maxCapacity;
	private int currentCapacity;
	
	public Room(String roomName, int capacity) {
		Validate.isTrue(StringUtils.isAlphanumeric(roomName),"roomName must be alphanumeric, was %s", roomName);
		Validate.isTrue(capacity > 0, "capacity must not be negative, was %d " , capacity);
		this.roomName = roomName;
		this.maxCapacity = capacity;
		currentCapacity = 0;
	}	

	int getMaxCapacity() {
		return maxCapacity;
	}

	int getCurrentCapacity() {
		return currentCapacity;
	}

	public void incrementCurrent() {
		if(currentCapacity >= maxCapacity) {
			throw new RoomCapacityExceededException("room capacity exceeded max capacity, was " + (currentCapacity + 1));
		}
		
		currentCapacity++;
	}

	@Override
	public String toString() {
		return "Room # " + roomName + "\nMax Capacity: " + maxCapacity + "\nCurrent Capacity " + currentCapacity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
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
		Room other = (Room) obj;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		return true;
	}
	
	
	
	
	

}
