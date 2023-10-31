package com.test.dto;

import java.util.ArrayList;

import com.test.entity.MessageEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {
	private int no;
	private int sendNo;
	private ArrayList<Integer> noList;
	private String type;
	private String depth1;
	private String depth2;
	private String position;
	private String userName;
	private String title;
	private String content;
	private String isRead;
	private String isShow;
	private String sendId;
	private String receiveId;
	private String createDate;
	
	public MessageEntity toEntity() {
		MessageEntity build = MessageEntity.builder()
							.no(no)
							.sendNo(sendNo)
							.type(type)
							.depth1(depth1)
							.depth2(depth2)
							.position(position)
							.userName(userName)
							.title(title)
							.content(content)
							.isRead(isRead)
							.isShow(isShow)
							.sendId(sendId)
							.receiveId(receiveId)
							.createDate(createDate)
							.build();
		return build;
	}

	/* .noList(noList) */
	public MessageDto(MessageEntity me) {
		this.no = me.getNo();
		this.sendNo = me.getSendNo();
		this.type = me.getType();
		this.depth1 = me.getDepth1();
		this.depth2 = me.getDepth2();
		this.position = me.getPosition();
		this.userName = me.getUserName();
		this.title = me.getTitle();
		this.content = me.getContent();
		this.isRead = me.getIsRead();
		this.isShow = me.getIsShow();
		this.sendId = me.getSendId();
		this.receiveId = me.getReceiveId();
		this.createDate = me.getCreateDate();
	}

	/* this.noList = me.getNoList(); */
	@Builder
	public MessageDto(int no, int sendNo, ArrayList<Integer> noList, String type, String depth1, String depth2, String position, String userName,
			String title, String content, String isRead, String isShow,String sendId, String receiveId,String createDate) {
		this.no = no;
		this.sendNo = sendNo;
		this.noList = noList; 
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
