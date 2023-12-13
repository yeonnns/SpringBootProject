$(document).ready(function(){
	$("#contents").summernote({
		toolbar: [
		    // [groupName, [list of button]]
		    ['fontname', ['fontname']],
		    ['fontsize', ['fontsize']],
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    ['color', ['forecolor','color']],
		    ['table', ['table']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert',['picture','link','video']],
		    ['view', ['fullscreen', 'codeview', 'help']]
		  ],
		height: 300,
		minHeight: null,             // set minimum height of editor
		maxHeight: null,             // set maximum height of editor
		focus: true,
		lang: "ko-KR",
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	});
});


function mailSend(){
	var receiveId = $('#receiveId').val();
	var title = $('#title').val();
	var contents = $('#contents').val();
	var depth3 = $('#depth3').val();
	
	var el = $('#receiveId, #title, #contents'); 
	
		for(var i = 0 ; i < el.length ; i++ ){
			var txt = $(el).eq(i).val();
		
			if(!txt){
				alert('필수 입력사항을 입력해주세요');
				$(el).eq(i).focus();
				return;
			}
		}

	$.ajax({
		type : "POST",
		data : {"receiveId": receiveId,
				"title" : title,
				"contents" : contents,
				"depth3" : depth3},
		dataType : "json",
		async : false,
		url : "/api/mailSend",
		success : function(data){
			var result = data.result;
			if(result){
				alert("메일 전송 성공");
				location.reload();
			}else{
				alert("메일 전송 실패");
				return;
			}
		},
		error : function(e) {
			console.log("ajax 실패");
		}		
	});
		

	
	
	
}

