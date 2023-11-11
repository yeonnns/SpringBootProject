package com.test.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.entity.ChatEntity;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer>{

	@Modifying
	@Query(value = "select * from chat where room_no = :roomNo", nativeQuery = true)
	List<ChatEntity> selectChatList(int roomNo);

	@Modifying
	@Query(value = "select * from chat as a inner join chat_room as b on a.room_no = b.room_no where a.room_no = :roomNo", nativeQuery = true)
	List<ChatEntity> selectJoinChatList(int roomNo);

	List<ChatEntity> findAll(Sort sort);
}
 