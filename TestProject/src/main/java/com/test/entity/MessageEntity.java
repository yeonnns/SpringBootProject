package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@Entity
@Table(name = "message")
@NoArgsConstructor
@DynamicInsert
public class MessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	private int sendNo;
	/* private List<Integer> noList; */
	private String type;
	private String title;
	private String depth1;
	private String depth2;
	private String position;
	private String userName;
	private String content;
	private String isRead;
	private String isShow;
	private String sendId;
	private String receiveId;
	private String createDate;
	
	@Builder
	public MessageEntity(int no, int sendNo, String type, String depth1, String depth2, String position, String userName, 
			String title, String content, String isRead, String isShow,String sendId, String receiveId,String createDate) {
		this.no = no;
		this.sendNo = sendNo;
		/* this.noList = noList; , List<Integer> noList */
		this.type = type;
		this.depth1 = depth1;
		this.depth2 = depth2;
		this.position = position;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.isRead = isRead;
		this.isShow = isShow;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.createDate = createDate;
	}
}
