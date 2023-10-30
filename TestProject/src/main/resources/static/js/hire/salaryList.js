$(document).ready(function() {
	
		$("#totalDate").monthpicker({
			startYear: 1995,
			nextText : '>',
			prevText : '<',
	        pattern: 'yyyy-mm',
	        dateFormat: 'yy-mm',
			monthNamesShort :  ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
	        monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12']
		});
		
		$('#totalDate').change(function(){
			var select = $(this).val();
			
			var nowMonth = select+'-01'
			$('#totalDate').val(select);
			location.href="/user/salaryList?nowMonth="+nowMonth;
		});
		
		// dataTable
		$("#datatablesSimple").DataTable({
			lengthChange: false,
			searching: true,
			ordering: true,
			info: false,
			bStateSave: true,
			paging: true,
			pageLength: 20,
			language: korean,
			buttons: [{
				extend :'excel',
				filename : '급내내역서_'+nowMonth,
				text : '엑셀 다운로드',
				title : '급여내역서'
			}],
			dom: 'Bfltip'
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

function nowMonth(){
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var name = year +'-'+ month;
	return name;
}

function salSubmit() {
	if (fileCheck() == false) {
		$("#excelFile").attr("type", "text");
		$("#excelFile").attr("type", "file");
		return false;
	}
	var form = $("#excelUpForm")[0];

	var data = new FormData(form);
	$.ajax({
		enctype : "multipart/form-data",
		method : "POST",
		url : './salaryFileUpload',
		processData : false,
		cache : false,
		contentType : false,
		data : data,
		success : function(result) {
			alert("파일업로드 완료");
			location.reload();
		}
		,beforeSend:function(){
			$('.wrap-loading-box').removeClass('display-none');
		}
		,complete:function(){
			$('.wrap-loading-box').addClass('display-none');
		}
		,error:function(e){
			alert("파일업로드 실패하였습니다.");
			$('.wrap-loading-box').addClass('display-none');
		}
	});
}
function fileCheck() {
	var filePath = document.getElementById('excelFile').value.split("\\");
	if(filePath.length == 0) {
		alert("파일을 선택해 주세요.");
		return false;
	}
	var thumbext = document.getElementById('excelFile').value; //파일을 추가한 input 박스의 값
	thumbext = thumbext.slice(-3, thumbext.length);
	if (thumbext != "csv") { 
		alert('csv파일만 등록가능합니다.');
		return false;
	}
}	