$(document).ready(function(){
	
	var now = new Date();
	$("#datepicker").datepicker({
	 	dateFormat : 'yyyy-mm-dd',
    	language : 'ko',
    	maxDate : now,
    	onSelect : function(date){
			console.log(date);
			moveCrawling(date);
		}
    }); 
	
	var date = $('#selectDate').val(); 
	 $('#datepicker').val(date); 
	
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
		destroy: true,
		autoWidth: true
	});
});

var weekDay = new Array();
var weekHighName = new Array();
var weekHighPri = new Array();
var weekLowName = new Array();
var weekLowPri = new Array();

function test(){
	$.ajax({
		url:"/api/crawl/weekStockChart",
		type: 'post',
		dataType: 'json',
		success: function(data){
			for (var i = 0; i < data.length; i++){
				weekHighPri.push(data[i].lastHighPrice);
				weekLowPri.push(data[i].lastLowPrice);
				weekHighName.push(data[i].stockNameH);
				weekLowName.push(data[i].stockNameL);
				weekDay.push(data[i].createDate);
			}
			weekHighCharts();
		},
		error: function(){
	           alert("통신오류 입니다.")
	         }
	});
}

var myLineChart;

function weekHighCharts(){
		var ctx = document.getElementById("weekHigh");
		var myLineChart = new Chart(ctx, {
		  type: 'horizontalBar',
		  data: {
		    labels: weekHighName,
		    datasets: [{
		      lineTension: 0.3,
		      backgroundColor: "rgba(210, 135, 127, 0.57)",
		      borderColor: "rgba(210, 135, 127, 1)",
		      pointRadius: 5,
		      pointBackgroundColor: "rgba(210, 135, 127, 1)",
		      pointBorderColor: "rgba(255,255,255,0.8)",
		      pointHoverRadius: 5,
		      pointHoverBackgroundColor: "rgba(210, 135, 127, 1)",
		      pointHitRadius: 50,
		      pointBorderWidth: 2,
		      data: weekHighPri,
		    }],
		  },
		  options: {
		    scales: {
		      xAxes: [{
		        time: {
		          unit: 'date'
		        },
		        gridLines: {
		          display: false
		        },
		        ticks: {
		          maxTicksLimit: 7
		        }
		      }],
		      yAxes: [{
		        ticks: {
		          min: -50000,
		          maxTicksLimit: 10
		        },
		        gridLines: {
		          color: "rgba(0, 0, 0, .125)",
		        }
		      }],
		    },
		    legend: {
		      display: false
		    }
		  }
		});
		
		var ctx2 = document.getElementById("weekLow");
		var myLineChart = new Chart(ctx2, {
			type: 'horizontalBar',
			data: {
				labels: weekLowName,
				datasets: [{
					lineTension: 0.3,
					backgroundColor: "rgba(50, 67, 245, 0.41)",
					borderColor: "rgba(50, 67, 245, 1)",
					pointRadius: 5,
					pointBackgroundColor: "rgba(50, 67, 245, 1)",
					pointBorderColor: "rgba(255,255,255,0.8)",
					pointHoverRadius: 5,
					pointHoverBackgroundColor: "rgba(50, 67, 245, 1)",
					pointHitRadius: 50,
					pointBorderWidth: 2,
					data: weekLowPri,
				}],
			},
			options: {
				scales: {
					xAxes: [{
						time: {
							unit: 'date'
						},
						gridLines: {
							display: false
						},
						ticks: {
							maxTicksLimit: 7
						}
					}],
					yAxes: [{
						ticks: {
							max: 50000,
							maxTicksLimit: 10
						},
						gridLines: {
							color: "rgba(0, 0, 0, .125)",
						}
					}],
				},
				legend: {
					display: false
				}
			}
		});
}

function moveCrawling(date){
	selDate = date
	$("#selectDate").val(date);
	location.href="/crawling/stockCrawl?createDate="+date;
};

var korean = {
	"emptyTable": "내역이 존재하지 않습니다.",
	"paginate" : {
	 "next" : "다음",
	 "previous" : "이전"
	},
	"zeroRecords" : "검색된 데이터가 없습니다.",
	"search" : "검색 : "
};

function stockCrawling(){
	alert("크롤링");
	  $(location).attr('href', '/crawling/startStockCrawl');
}

//csv
function stockCsvlDown(){
	 $(location).attr('href', '/crawling/stockCrawlCsvDown');
}

//excel
function stockExcelDown(){
	var stockDate = $('.stockDate').val();
	$.ajax({
		url : "/crawling/stockCrawlExcelDown",
		type : "post",
		contentType: false,	// 기본값 true"="기호로 값과 키 연결, 바이너리 데이터에서는 적절 X 
		processData : false,// data 지정한 개체를 쿼리 문자열로 변환 유무
		async : true, 		// true 비동기, false 동기(응답완료후 다음 동작)
		xhrFields : {		// blob 값 부풀리는 현상 막기
			responseType: 'blob',
		},
		success : function(blob){
			var link = document.createElement('a'); // 새로운<a> 요소를 만든다
			link.href = window.URL.createObjectURL(blob);
			link.download = stockDate + '_stock_list.xlsx';
			link.click();
		},
		error : function(request, status, error){
			alert("다운로드 실패. 개발자에게 문의해주세요");
			console.log(error);
		}
	});
}
	
function nowDay(){
	var today = new Date();
	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = today.getDate();
	var hours = today.getTime();
	var name = year +'-'+ month + '-' + day ;
	return name;
}

