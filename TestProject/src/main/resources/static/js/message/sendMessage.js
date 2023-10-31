
/*
	
	$("#newNoticeIcon").css("visibility", "visible");
 */
$(document).ready(function(){
	getTime()
	OpenMessagebtn();
	
	$('#msgSendBtn').click(function() {
		setTimeout(function() {
			messageSend();
		}, 500);
	});
	
	$("#dataTableMessage").DataTable({
		lengthChange: false,
		searching: true,
		ordering: true,
		info: false,
		bStateSave: true,
		paging: true,
		pageLength: 20,
		language: korean,
		destroy: true,
		autoWidth: true,
		columnDefs: [{ "targets": [0], "orderable": false }]
	});
	
	
	$('#content').on('keyup', function() {
        $('#text-cnt').html("("+$(this).val().length+" / 500)");
        if($(this).val().length > 1000) {
            alert("최대 1000자까지 가능합니다.")
            $(this).val($(this).val().substring(0, 1000));
            $('#text-cnt').html("(500 / 500)");
        }
    });
});

var korean = {
	"emptyTable": "내역이 존재하지 않습니다.",
	"paginate" : {
	 "next" : "다음",
	 "previous" : "이전"
	},
	"zeroRecords" : "검색된 데이터가 없습니다.",
	"search" : "검색 : "
};


function depth1Ck() {
	var select = $('.einsck').attr("class");
	if(select.includes('fa-plus')){
		$('.einsck').removeClass("fa-plus");
	    $('.einsck').addClass("fa-minus");
	    var data = "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i><span class='경영진' id='einK'></span>" 
	    				+ "<label class='form-check-label' for='einK'>경영진</label><div class='depth2 einK'></div></div>"
	    			+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='회계팀' id='einAcc'></span>" 
    					+ "<label class='form-check-label' for='einAcc'>회계팀</label><div class='depth2 einAcc'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='디지털마케팅본부' id='einDigit'></span>" 
    					+ "<label class='form-check-label' for='einDigit'>디지털마케팅본부</label><div class='depth2 einDigit'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='마케팅실' id='einMa'></span>" 
    					+ "<label class='form-check-label' for='einMa'>마케팅실</label><div class='depth2 einMa'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='미디어실' id='einMe'></span>" 
    					+ "<label class='form-check-label' for='einMe'>미디어실</label><div class='depth2 einMe'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='커머스실' id='einCu'></span>" 
    					+ "<label class='form-check-label' for='einCu'>커머스실</label><div class='depth2 einCu'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='경영기획본부' id='einManeT'></span>" 
    					+ "<label class='form-check-label' for='einManeT'>경영기획본부</label><div class='depth2 einManeT'></div></div>"
    				+ "<div class='mgl20 mg5'><i id='depth2Ck'  class='fas fa-plus'></i> <span class='경영기획실' id='einManeM'></span>" 
    					+ "<label class='form-check-label' for='einManeM'>경영기획실</label><div class='depth2 einManeM'></div></div>";
	    $('.depth1').html(data);
	} else {
			$('.depth1').html("");
			$('.einsck').removeClass("fa-minus");
			$('.einsck').addClass("fa-plus");
	}
} 

$(document).on("click", "#depth2Ck", function(){
	var select = $(this).attr("class");
	var depth1 = $(this).next().attr("class");
	var depth1Id = $(this).next().attr('id');
	depth1Id = '.' + depth1Id

	if(select.includes('fa-plus')){
		$(this).removeClass("fa-plus");
		$(this).addClass("fa-minus");
		$.ajax({
			url : '/message/api/selectDepth2',
			method : 'POST',
			dataType: 'json',
			async : false,
			data : {
				"depth1" :depth1		
			},
			success : function(result){
				
				var result = result.result;
				var cnt = result.length;
				if(result[0].name != null){
					for(i= 0; i<cnt ; i++){
						var nameTxt = "<div class='mgl40 mg5'><input class='form-check-input' type='checkbox' value='" + result[i].depth3 + "' id='name" + i + "'>" 
	    				+ "<label class='form-check-label' for='name" + i + "'>" + result[i].name + "</label></div>"
	    				$(depth1Id).append(nameTxt);
					}
				}else{
					for(i= 0; i<cnt ; i++){
						var depth2Txt = "<div class='mgl25 mg5'><i id='depth3Ck' class='fas fa-plus'></i><input type='hidden' value='" + result[i].depth2 + "' id='name" + i + "'>" 
	    				+ "<label class='form-check-label' for='name" + i + "'>" + result[i].depth2 + "</label><div class='depth3 " +result[i].depth2+ "'></div>"
	    				$(depth1Id).append(depth2Txt);
					}
				}
			},
			error : function(e){
				alert("ajax 통신오류");
				location.reload();
			}
		});
	} else {
		$(depth1Id).html("");
		$(this).removeClass("fa-minus");
		$(this).addClass("fa-plus");
	}
});
	
$(document).on("click", "#depth3Ck", function(){
	var select = $(this).attr("class");
	var depth2 = $(this).next().val();
	var depth2C = "." + depth2;
	if(select.includes('fa-plus')){
		$(this).removeClass("fa-plus");
		$(this).addClass("fa-minus");
		$.ajax({
			url : "/message/api/selectDepth3",
			method : "POST",
			dataType : "json",
			asunc : false,
			data : {
				"depth2" : depth2
			},
			success : function(result){
				var result = result.result;
				for( i=0, cnt=result.length; i<cnt; i++){
					var depth3Txt = "<div class='mgl30 mg5'><input class='form-check-input' type='checkbox' value='" + result[i].depth3 + "' id='name" + i + "'>" 
    				+ "<label class='form-check-label' for='name" + i + "'>" + result[i].name + "</label></div>"
					$(depth2C).append(depth3Txt);
				}
			},
			error : function(e){
				alert("ajax 통신오류");
				location.reload();
			}
		});
	} else {
		$(depth2C).html("");
		$(this).removeClass("fa-minus");
		$(this).addClass("fa-plus");
	}
});

function addButton(){
	var arrDepth3 = [];
	$("input:checked").each(function() {
		  arrDepth3.push($(this).val());
	});
	$.ajax({
		url : "/message/api/selectIdName",
		method : "POST",
		dataType : "json",
		asunc : false,
		traditional: true ,
		data : {
			nameList : arrDepth3
		},
		success : function(result){
			var result = result.result;
			$('#recNameId').val(result.emailId);
			$('#receiveId').val(result.username);
		},
		error : function(e){
			alert("ajax 통신오류");
			location.reload();
		}
	});
};

function delButton(){
	$("input:checked").prop("checked", false); 
	$('#recNameId').val('');
	$('#receiveId').val('');
};

function messageSend(){
	var recNameId = $('#recNameId').val();
	var receiveId = $("#receiveId").val();
	var title = $("#title").val();
	var content = $("#content").val();
	var count = 0;
	var type = "message";
	
	if(receiveId.match(",")){
		 count = receiveId.match(/,/g).filter(function(item) { return item !== ''; }).length+1;
	}else {
		count =1;
	}
	check = window.confirm("총 " + count + " 명\n받는사람 " + recNameId );
	if(!check){return false;}
	
	if(socketClose){
		alert("소켓 세션이 만료되어 페이지 새로고침 후 이용가능합니다.");
		return false;
		window.reload();
	}
	var el = $('#receiveId, #title, #content');
		for(var i = 0; i<el.length; i++){
			var txt = $(el).eq(i).val();
			if(!txt){
				alert("공란을 입력해주세요." + i);
				$(el).eq(i).focus();
				return;
			}
		}
	var recId = $('#receiveId').val();
	var recIdList = recId.split(","); 
	$('#text-cnt').html("(0 / 500)");
	socket.send(receiveId + "&" + title + "&" + content + "&" + type);
	
	
	$('#recNameId').val('');
	$('#receiveId').val('');
	$("#title").val('');
	$("#content").val('');
	$("input:checkbox").prop("checked", false);
};

/*receive section*/
function OpenMessagebtn(){
	$('.textbox').each(function(index, element){
		var messageTextarea = $(this).find('textarea');
		var height = autosize(messageTextarea).css('height');
		
		if(height == "24px"){
			messageTextarea.next().hide();
		}else{
			autosize.destroy(messageTextarea);
		}
	});
}

function openMessage(no){
	if($('#openMessage'+no).html() == '더보기'){
		autosize($('#contentArea'+no));
		$('#openMessage'+no).html(' 닫기');
	}else{
		autosize.destroy($('#contentArea'+no));
		$('#openMessage'+no).html('더보기');
	}
}

function totalInput(){
	$("input[id=totalInput]").is(":checked") ? $(":checkbox").prop("checked", true) : $(":checkbox").prop("checked", false);
}

function arrSearch(){
	var arrNo = [];
	$("input:checked").each(function() {
		var ckVal = $(this).val();
		if(ckVal != 'totalInput'){
			arrNo.push(parseInt(ckVal));
		}
	});
	return arrNo;
}

function msgDelBtn(){
	var arrList = arrSearch();
	check = window.confirm("총 " + arrList.length + " 개의 message를 삭제하시겠습니까?");
	if(!check){return false;}
	
	$.ajax({
		type : "POST",
		url : "/message/api/updateMsgDel",
		dataType : "json",
		async : false,
		traditional: true,
		data : {
			 "noList" : arrList
		},
		success : function(data) {
			if(data.result != 0){
				//location.reload();
				for( i=0, n=arrList.length; i<n; i++){
					var arrIn = '#'+arrList[i];
					$(arrIn).remove();
				}
				$(":checkbox").prop("checked", false)
			}
		},
		error : function(e) {
			console.log("ajax 통신오류");
		}
	});
}

function msgReadBtn(){
	var arrList = arrSearch();
	check = window.confirm("총 " + arrList.length + " 개의 message를 읽음 처리 하시겠습니까?");
	if(!check){return false;}
	
	$.ajax({
		type : "POST",
		url : "/message/api/updateMsgRead",
		dataType : "json",
		async : false,
		traditional: true,
		data : {
			 "noList" : arrList
		},
		success : function(data) {
			if(data.result != 0){
				for( i=0, n=arrList.length; i<n; i++){
					var arrIn = '#'+arrList[i];
					$(arrIn).removeClass('bold');
				}
				$(":checkbox").prop("checked", false)
			}
		},
		error : function(e) {
			console.log("ajax 통신오류");
		}
	});
}

function getTime(){
	var date = new Date();
	var year = "" + date.getFullYear();
	var month = date.getMonth() + 1 <10? "0"+(date.getMonth() + 1) : (date.getMonth() + 1);
	var day = date.getDate() <10? "0"+date.getDate() : date.getDate();
	var min = date.getMinutes() <10? "0"+date.getMinutes() : date.getMinutes();
	var sec = date.getSeconds() <10? "0"+date.getMinutes() : date.getMinutes();
	var time = year + "-" + month + "-" + day + " " + date.getHours() + ":" + min + ":" + sec;
	// 2023-02-14 10:24:24
	return time;
}




