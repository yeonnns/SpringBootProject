package com.test.service;

import java.util.ArrayList;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.config.PrincipalDetails;
import com.test.dto.SalesDto;
import com.test.dto.UserDto;
import com.test.mapper.UserMapper;


//시큐리티 설정에서 .loginProcessingUrl("/loginProc")';
//login 요청이 오면 자동으로 UserDetailsService 타입으로 ioc(service 로 등록) 되어 있는 loadUserByUsername 함수 실행

// 사용자의 정보를 담을 객체 (userDetail)만들었고, db에서 유저 정보 가져오는 인터페이스 UserDetailsService생성
@Service
public class UserService implements UserDetailsService {
	//UserDetailsService 로 로그인 구현

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Load User By UserName : "+username);
		UserDto user = userMapper.UserInfo(username);
		if(user!=null) {
			return new PrincipalDetails(user);
		}else {
		return null;
		}
		
		/*
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		return new PrincipalDetails(user);
		*/
		//return new User(user.getUsername(), user.getPassword(), authorities);

	}
	
	public UserDto getUserInfo(String username) {
		return (UserDto)userMapper.UserInfo(username);
	}
	
	public UserDto getLoginUser() {
		
		//SecurityContextHolde를 통해서 로그인 한 객체를 어디서든지 사용할 수 있음.
		//현재 사용자 인증정보 꺼내오기
		Object principal = SecurityContextHolder.getContext().getAuthentication();
		UserDto userData = new UserDto();
		if(principal!=null) {
			Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(!userDetail.equals("anonymousUser")) {
				PrincipalDetails user = (PrincipalDetails)userDetail;
				userData = userMapper.UserInfo(user.getUsername());
			}
		}
		System.out.println("userData" + userData);
		return userData;
	}
	
	public void updateAuthenticationSession() {
		String username = getLoginUser().getUsername();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication, username));
	}
	
	protected Authentication createNewAuthentication(Authentication currentAuth, String username) {
        UserDetails newPrincipal = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }
	
	public int userInsert(UserDto userDto) {
		return userMapper.userInsert(userDto);
	}
	public ArrayList<SalesDto> SalesInfo() {
		return userMapper.SalesInfo();
	}
	public int[] daySales() {
		return userMapper.daySales();
	}
	public int[] monthSales() {
		return userMapper.monthSales();
	}
	public ArrayList<UserDto> latestJoin() {
		return userMapper.latestJoin();
	}
	
}
