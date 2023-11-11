package com.test.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.ChatRoomEntity;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer>{

	List<ChatRoomEntity> findAll(Sort sort);

	@Modifying
	@Transactional
	@Query(value = "select * from chat_room where first = :first", nativeQuery = true)
	List<ChatRoomEntity> selectChatRoomList(String first);
	
	@Transactional
	@Query(value = "select * from chat_room where room_no = :roomNo", nativeQuery = true)
	ChatRoomEntity selectChatRDetail(int roomNo);
	
}
 