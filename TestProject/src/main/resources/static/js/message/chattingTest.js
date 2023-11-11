$(document).ready(function(){
	
	//웹소켓 연결 
	var sock = new SockJS("/ws/echo");
	socket = sock;

	socket.onopen = function (e) {
       console.log('open server2!')
    };

    socket.onerror = function (e){
        console.log(e);
        socketClose = true;
    };

    sock.onclose = function() {
      	console.log('connect close2');
        socketClose = true;
    };
    
    socket.onmessage = function (e) {
    	//$('#chat').append(obj.contents);
    };
    
	$('#chatSend').click(function() {
		var rNo = $('#inRoomNo').val()
		setTimeout(function() {
			chatSend(rNo);
		}, 500);
	});

	
	
});


function chatSend(rNo){
	var inRoomNo = $("#inRoomNo").val();
	var content = $("#sendContent").val();
	var second = $("#second").val();
	var type = "chat";
	var time = getTime();
	
	if(socketClose){
		alert("소켓 세션이 만료되어 페이지 새로고침 후 이용가능합니다.");
		return false;
		window.reload();
	}
	socket.send(inRoomNo + "&" + content + "&" + second + "&" + type + "&" + time);
	var msgDiv = "<li class='me'>"
				+ "<div class='entete'>"
				+ "<h3>" + time + "</h3>"
				+ "<h2>&nbsp;&nbsp;" + username + "&nbsp;</h2>"
				+ "<span class='status blue'></span></div>"
				+ "<div class='triangle'></div>"
				+ "<div class='message'>"+content+"</div></li>"
	$('#chat').append(msgDiv);
				
	$('#sendContent').val('');
}

function getTime(){
	var date = new Date();
	var month = date.getMonth() + 1 <10? "0"+(date.getMonth() + 1) : (date.getMonth() + 1);
	var day = date.getDate() <10? "0"+date.getDate() : date.getDate();
	var min = date.getMinutes() <10? "0"+date.getMinutes() : date.getMinutes();
	var time =  month + "/" + day + " " + date.getHours() + ":" + min;
	return time;
}
