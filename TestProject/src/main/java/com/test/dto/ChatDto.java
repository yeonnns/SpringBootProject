package com.test.dto;

import java.util.Date;

import com.test.entity.ChatEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatDto {
	private int no;
	private int roomNo;
	private String sendId;
	private String contents;
	private String isRead;
	private String notice;
	private String createDate;
	
	public ChatEntity toEntity() {
		ChatEntity build = ChatEntity.builder()
							.no(no)
							.roomNo(roomNo)
							.sendId(sendId)
							.contents(contents)
							.isRead(isRead)
							.notice(notice)
							.createDate(createDate)
							.build();
		return build;
	}

	public ChatDto(ChatEntity ch) {
		this.no = ch.getNo();
		this.roomNo = ch.getRoomNo();
		this.sendId = ch.getSendId();
		this.contents = ch.getContents();
		this.isRead = ch.getIsRead();
		this.notice = ch.getNotice();
		this.createDate = ch.getCreateDate();
	}

	@Builder
	public ChatDto(int no, int roomNo, String sendId, String contents, String isRead, String notice, String createDate) {
		this.no = no;
		this.roomNo = roomNo;
		this.sendId = sendId;
		this.contents = contents;
		this.isRead = isRead;
		this.notice = notice;
		this.createDate = createDate;
	}
}
