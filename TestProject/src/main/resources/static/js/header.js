$(document).ready(function(){
	
	//웹소켓 연결 
	var sock = new SockJS("/ws/echo");
	socket = sock;

	socket.onopen = function (e) {
       console.log('open server!')
    };

    socket.onerror = function (e){
        console.log(e);
        socketClose = true;
    };

    sock.onclose = function() {
      	console.log('connect close');
        socketClose = true;
    };
    
    socket.onmessage = function (e) {
		obj = JSON.parse(e.data); 
		switch(obj.type){ //type확인
			case "message" : 
				console.log('message receive');
				console.log('obj: ' + obj);
				$(".newMsg").remove();
				$('.msgAfter').after(obj.text);
				//$('.newMsg').fadeOut(6000);
			break;
			
			case "chat" : 
				console.log('chat receive');
				console.log('obj: ' + obj);
				$(".newMsg").remove();
				$('.msgAfter').after(obj.text);
				//$('.newMsg').fadeOut(6000)
				break;
		}
    };
    
});


$(document).on("click", ".newMsg", function(){
	 $(".newMsg").remove();	
	 location.reload();
});

