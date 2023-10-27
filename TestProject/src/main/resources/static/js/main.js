$(document).ready(function(){
	// 메인
	$('#home, #hbtn').click(function(){
      $(location).attr('href', '/');
   });

	// 게시판
   $('#bbtn').click(function(){
      $(location).attr('href', '/board/boardList');
   });
   
   // 로그인
   $('#lbtn').click(function(){
	   $(location).attr('href', '/member/login');
   });
   
   // 회원가입
   $('#jbtn').click(function(){
	   $(location).attr('href', '/member/join');
   });
   
   // 로그아웃
   $('#obtn').click(function(){
     $('#frm').attr('action', '/member/logout');
     $('#frm').submit();
   });

   $('#login').click(function(){
		var sid = $('#id').val();
		var spw = $('#pw').val();
		
		var el = $('#id, #pw');
		for(var i = 0; i < el.length; i++){
			var txt = $(el).eq(i).val();
			if(!txt){
				$(el).eq(i).focus();
				return;
			}
		}
		
		$('#frm').attr('action', '/member/loginProc');
		$('#frm').submit();	
	});
   
   
   // 메인 hot 클릭시 datail
   $('.maindatail').click(function(){
	   	var bno = $(this).attr('id');
	   	$('#bno').val(bno);
	   	
	   	$('#frm').attr('action','/board/boardDetail');
	    $('#frm').submit();
	   
   });
   
   $('#info').click(function(){
	   $('#bno').prop('disabled', true);
	   
		$('#frm').attr('action','/member/memInfo');
	    $('#frm').submit();
	   
   })
   
  
});



function startBatch(){
	$.ajax({
		url : "/main",
		type : "POST",
		dataType : "json",
		async : true,
		success : function(data) {
			alert("성공");
		},
		error:function(error) {
			alert("실패");
		}
	});
}

