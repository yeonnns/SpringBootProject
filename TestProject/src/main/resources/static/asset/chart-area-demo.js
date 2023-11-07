
var daySale = new Array();
var monthSale = new Array();

$(document).ready(function(){

	// 일별 매출
	$.ajax({
		url:"/api/daySalesChart",
		type: 'post',
		dataType: 'json',
		success: function(data){
			var result = data.result;
			
			
			for (var i = 0; i < result.length; i++){
				daySale.push(result[i]);
			}
			dayCharts();
		},
		error: function(){
	           alert("통신오류 입니다.")
	         }
	});

	
	
// 월별 매출
	$.ajax({
		url:"/api/monthSalesChart",
		type: 'post',
		dataType: 'json',
		success: function(data){
			var result = data.result;
			
			
			for (var i = 0; i < result.length; i++){
				monthSale.push(result[i]);
			}
			monthCharts();
		},
		error: function(){
	           alert("통신오류 입니다.")
	         }
	});
	
});

//Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
	
function dayCharts(){
var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: ["1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일","15일","16일","17일","18일","19일","20일","21일","22일","23일","24일","25일","26일","27일","28일","29일","30일","31일"],
	    datasets: [{
	      label: "Sessions",
	      lineTension: 0.3,
	      backgroundColor: "rgba(243, 216, 63, 0.2)",
	      borderColor: "rgba(243, 216, 63, 1)",
	      pointRadius: 5,
	      pointBackgroundColor: "rgba(243, 216, 63, 1)",
	      pointBorderColor: "rgba(255,255,255,0.8)",
	      pointHoverRadius: 5,
	      pointHoverBackgroundColor: "rgba(243, 216, 63, 1)",
	      pointHitRadius: 50,
	      pointBorderWidth: 2,
	      data: daySale,
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
	          maxTicksLimit: 31
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          max: 120000,
	          maxTicksLimit: 6
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





function monthCharts(){
	var ctx = document.getElementById("myBarChart");
	var myLineChart = new Chart(ctx, {
	  type: 'bar',
	  data: {
	    labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월","9월", "10월", '11월', "12월"],
	    datasets: [{
	      label: "Revenue",
	      backgroundColor: "rgba(31, 199, 62, 0.5)",
	      borderColor: "rgba(31, 199, 62, 0.5)",
	      data: monthSale,
	    }],
	  },
	  options: {
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'month'
	        },
	        gridLines: {
	          display: false
	        },
	        ticks: {
	          maxTicksLimit: 12
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          min: 1000000,
	          max: 3000000,
	          maxTicksLimit: 10
	        },
	        gridLines: {
	          display: true
	        }
	      }],
	    },
	    legend: {
	      display: false
	    }
	  }
	});
}
