

//var jenkinsRegressionBuildTrigger = "http://localhost:8080/job/Regression/build?token=TOKEN"; 
//
//function startJenkinsRegressionJob(){
//	$.ajax({
//		url: "/radiator/regression/",
//		type: "GET",
//	});
//	
//	$.ajax({
//		url: jenkinsRegressionBuildTrigger,
//		type: "GET",
//	});
//	
//}

var jenkinsSmokeBuildTrigger = "http://localhost:8080/job/Smoke/build?token=TOKEN"; 

//function startJenkinsSmokeJob(){
//	$.ajax({
//		url: "/radiator/smoke/",
//		type: "GET",
////		success: function(result){$("#jobs").append(result);}
//	});
//	
//	$.ajax({
//		url: jenkinsSmokeBuildTrigger,
//		type: "GET",
//	});
//	
//}

var nextBuildNumber;

function startJenkinsSmokeJob(){
	
	$('#smoke_btn').click(function (e) {
        var button = $(this);
        button.val('Running smoke tests ...');
        
        $.getJSON("/radiator/nextBuildNumber/", {'jobName': 'Smoke'} ,  function(result){
        	nextBuildNumber = result.nextBuildNumber;
        		
        });
        
        $.ajax({
        	url: jenkinsSmokeBuildTrigger,
        	type: "GET",
        });
        
        $.getJSON("/radiator/smoke/", function(result){
        	if(result.result == 'SUCCESS'){       		
        		button.val('S');
        	}
        	else if (result.result == 'FAILURE'){
        		button.val('S');
        	}
        		
        });
               
    });
}
		
//	$.ajax({
//		url: "/radiator/smoke/",
//		type: "GET",
////		success: function(result){$("#jobs").append(result);}
//	});
//	
//	$.ajax({
//		url: jenkinsSmokeBuildTrigger,
//		type: "GET",
//	});
	
//}


$(document).ajaxError(function(){
	//if there was an error updating, wait a bit longer and try again
    setTimeout('updateJobs()', 10000);
});



var lastdata;

function updateJobs(){

    $.getJSON('/radiator/selenium/', function(data){
        if (data.content != lastdata){
            lastdata = data.content;
            $('#jobs').html(data.content);
            
//        	$.ajax({
//    		url: "http://localhost:8080/job/Smoke/build?token=TOKEN",
//    		type: "GET",
//        	});
//        	
//        	$('#smoke_btn').click(function (e) {
//                var button = $(this);
//                button.val('Running smoke tests ...');
//                $.getJSON("/radiator/smoke/", jenkinsSmokeJobNextBuildNumber, function(result){
//                	if(result.result == 'SUCCESS'){       		
//                		button.val('S');
//                	}
//                	else if (result.result == 'FAILURE'){
//                		button.val('S');
//                	}
//                		
//                });
//                       
//            });                            
        }
        
        setTimeout('updateJobs()', 5000);
    });
}

$(document).ready(function(){
	
	updateJobs();

    var count = 0;
    setInterval(function() {
        count++;
        var time = new Date().getTime();
            op = (count%2 +0.25).toFixed(2);
            if (op>1) {
                op = 1;
            }
            $('.building').animate({
                opacity: op,
                easing: 'swing'
            },1000)
        },
        1000
    );
	

});

//var allDivBoxes = document.getElementsByClassName("boxed")
//
//for(var div = 0, maxDiv = allDivBoxes.length; div < maxDiv; div++) {
//	
//	var allTdTags = allDivBoxes[div].getElementsByTagName('td')
//	
//	for(var td = 0, maxTd = allTdTags.length; td < maxTd; td++) {
//		
//		var node = allTdTags[td];
//
//		if(node.childNodes[0] != null) {	  
//			
//			//get the text from the first child node - which should be a text node
//			var currentText = node.childNodes[0].nodeValue; 
//			
//			//check for 'one' and assign this table cell's background color accordingly
//			if (currentText === "SUCCESS") {				
//				allDivBoxes[div].style.backgroundColor = "#00FF00";
//				break;
//			} else	if (currentText === "FAILURE") {				
//				allDivBoxes[div].style.backgroundColor = "#FF0000";
//				break;
//			} else if (currentText === "ABORTED") {				
//				allDivBoxes[div].style.backgroundColor = "#FF8000";
//				break;
//			}		
//			
//		}		
//	}
//}
		

