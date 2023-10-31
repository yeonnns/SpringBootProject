package com.test.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update MessageEntity set is_read = 'Y' where no in (:noList)")
	int updateMsgRead(ArrayList<Integer> noList);
	
	@Modifying
	@Transactional
	@Query(value = "update MessageEntity set is_show = 'N' where no in (:noList)")
	int updateMsgDel(ArrayList<Integer> noList);

	
}
 