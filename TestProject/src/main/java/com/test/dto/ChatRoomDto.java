package com.test.dto;

import com.test.entity.ChatRoomEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoomDto {
	private int roomNo;
	private String title;
	private String first;
	private String second;
	private String isShow;
	private String createDate;
	
	public ChatRoomEntity toEntity() {
		ChatRoomEntity build = ChatRoomEntity.builder()
							.roomNo(roomNo)
							.title(title)
							.first(first)
							.second(second)
							.isShow(isShow)
							.build();
		return build;
	}

	public ChatRoomDto(ChatRoomEntity mgr) {
		this.roomNo = mgr.getRoomNo();
		this.title = mgr.getTitle();
		this.first = mgr.getFirst();
		this.second = mgr.getSecond();
		this.isShow = mgr.getIsShow();
		this.createDate = mgr.getCreateDate();
	}

	@Builder
	public ChatRoomDto(int roomNo, String title, String first, String second, String isShow, String createDate) {
		this.roomNo = roomNo;
		this.title = title;
		this.first = first;
		this.second = second;
		this.isShow = isShow;
	}
}
