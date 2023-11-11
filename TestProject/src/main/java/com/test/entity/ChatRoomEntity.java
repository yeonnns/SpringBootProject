package com.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@Entity
@Table(name = "chat_room")
@NoArgsConstructor
@DynamicInsert
public class ChatRoomEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_no")
	private int roomNo;
	private String title;
	private String first;
	private String second;
	private String isShow;
	private String createDate; 
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room",  referencedColumnName = "room_no", insertable = false, updatable = false)
	private List<ChatEntity> ChatEntity = new ArrayList<ChatEntity>();
	
	@Builder
	public ChatRoomEntity(int roomNo, String title, String first, String second, 
			 String isShow, String createDate) {
		this.roomNo = roomNo;
		this.title = title;
		this.first = first;
		this.second = second;
		this.isShow = isShow;
		this.createDate = createDate;
	}
}
 