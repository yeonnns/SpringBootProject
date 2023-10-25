
package com.test.config;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.dto.UserDto;


//시큐리티가 /loginProc 낚아채서 로그인을 진행시킨다.
//로그인 완료되면 시큐리티 session 만들어 준다. (security contextHolder)
//오브젝트 => authentication  타입의 객체 
//authentication 안에 user 정보가 있어야됨
//user 오브젝트 타입은 => userDetail 타입이어야 함

//Secutiry => Session Authentication => UserDetails(PrincipalDetails)

// 사용자의 정보를 담는 인터페이스 UserDetails
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	// 직렬화 - 데이터 객체 쉽게 전송할 수 있도록 바이트로 변환 <==> 역직렬화 - 바이트 형식의 데이터 데이터 객체로 변환
	private UserDto user; 

	public PrincipalDetails(UserDto user){
		this.user = user;
	}

	// User의 권한을 리턴하는곳.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getDepth1() {
		return user.getDepth1();
	}
	
	public String getDepth2() {
		return user.getDepth2();
	}
	
	public String getPosition() {
		return user.getPosition();
	}
	
	public String getDepth3() {
		return user.getDepth3();
	}
	
	public String getCreateDate() {
		return user.getCreateDate();
	}
	
	public String getDelYN() {
		return user.getDelYN();
	}
	
	public String getDeleteDate() {
		return user.getDeleteDate();
	}
	
	public String getChangedPpw() {
		return user.getChangedPw();
	}
	
	public String getRole() {
		return user.getRole();
	}
	
	public int getTryCount() {
		return user.getTryCount();
	}

}
