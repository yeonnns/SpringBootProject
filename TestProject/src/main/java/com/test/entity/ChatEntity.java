package com.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@Entity
@Table(name = "chat")
@NoArgsConstructor
@DynamicInsert
public class ChatEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private int roomNo;
	private String sendId;
	private String contents;
	private String isRead;
	private String notice;
	private String createDate;
	private String first;
	private String second;
	private String isShow;
	private String title;
	
	@Builder
	public ChatEntity(int no, int roomNo, String sendId, String contents, String isRead, String notice, String createDate, String first, String second, String isShow, String title) {
		this.no = no;
		this.roomNo = roomNo;
		this.sendId = sendId;
		this.contents = contents;
		this.isRead = isRead;
		this.notice = notice;
		this.createDate = createDate;
		this.first = first;
		this.second = second;
		this.isShow = isShow;
		this.title = title;
	}
}
