var language = {// 语言设置
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 项结果",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索:",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
    }
};

function bindPageTable(tableName, pageLength, columns, url, token, queryParam) {
    if($.fn.dataTable.isDataTable("#" + tableName)){
        $("#" + tableName).DataTable().destroy()
    }

    $("#" + tableName).dataTable({
        "language": language,
        "columns": columns,
        "pageLength": pageLength,
        "autoWidth": false,
        "lengthChange": false,
        "searching": false,
        "serverSide": true,
        "processing": true,
        "sAjaxSource": url,
        "fnServerData": function(url, aoData, fnCallback) {
        	var start;
        	var pageLength;
        	var draw;
        	for(var index in aoData){
        		if(aoData[index].name == 'iDisplayStart'){
        			start = aoData[index].value;
        			continue;
        		}
        		
        		if(aoData[index].name == 'iDisplayLength'){
        			pageLength = aoData[index].value;
        			continue;
        		}
        		
        		if(aoData[index].name == 'sEcho'){
        			draw = aoData[index].value;
        			continue;
        		}
        		
        		if(start && pageLength && draw){
        			break;
        		}
        	}
        	
        	if(!queryParam){
        		queryParam = {};
        	}
        	queryParam.pageNum = start / pageLength + 1;
        	queryParam.draw = draw;
        	
        	$.ajax({
            	url: url,
            	type: 'POST',
    			async: false,
    			data: queryParam,
    			beforeSend: function(xhr) {
    				xhr.setRequestHeader('Authorization', 'Basic'+token)
    			},
    			success: function (jsonResult) {
    				if(jsonResult.code == 200){
    					var obj = {};
    					obj.draw = jsonResult.message;
    					obj.recordsFiltered = jsonResult.data.total;
    					obj.recordsTotal = jsonResult.data.total;
    					obj.data = jsonResult.data.result;
    					fnCallback(obj);
                    }else{
                    	layer.msg(jsonResult.message,{icon:2,time:2000});
                    }
    			},
    			error: function (request, error) {
    				alert(error);
    			}
            });
        }
    });
}
