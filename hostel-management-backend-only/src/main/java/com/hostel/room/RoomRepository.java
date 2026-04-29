package com.hostel.room;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByRoomTypeAndFloorNumberOrderByOccupancyAsc(String roomType, Integer floorNumber);
    List<Room> findByRoomTypeOrderByOccupancyAsc(String roomType);
    List<Room> findAllByOrderByOccupancyAsc();
}