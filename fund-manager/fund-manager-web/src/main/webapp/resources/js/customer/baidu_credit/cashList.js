var dtGridColumns = [{
    id : 'custId',
    title : '内部ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'name',
    title : '姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value){
    	return fsp.desensitization.nameDesensy(value);
    }
}, {
    id : 'identityNo',
    title : '身份证号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value){
    	return fsp.desensitization.idCardDesensy(value);
    }
}, {
    id : 'mobile',
    title : '手机号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value){
    	return fsp.desensitization.mobileDesensy(value);
    }
},{
    id : 'transactionId',
    title : '用信流水号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'applyResult',
    title : '提现结果',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    	 resolution:function(value){
    	    	if(value=='1'){
    	    		return '成功'
    	    	}
    	    	if(value=='0'){
    	    		return '失败'
    	    	}
    	    	if(value=='2'){
    	    		return '异常'
    	    	}
    	    	
    	    }
},{
    id : 'beginTime',
    title : '提现申请时间',
    type : Date,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'amount',
    title : '提现额度',
    type :  'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'vauleAccount',
    title : '剩余额度',
    type :  'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'transactionApplyId',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution:function(value, record, column, grid, dataNo, columnNo){
    	if(record.applyResult=="0"){
    		return "<span><a onclick='cashDetail("+value+")'>查看拒绝原因</a></span> ";
    		
    	}
    	else	if(record.applyResult=="2"){
    		return "<span><a onclick='solutionException("+value+")'>已解决</a></span> ";
    		
    	}
    	else{
    		return "";
    	}
    //	alert($(this).parent().find("td").eq(6).text())
    	//alert(value+"--" +"--"+record+"--"+column+"--"+grid+"--"+dataNo+"--"+ columnNo)
    	
    }
}
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/cash/cashList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : '用户登录信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
   // grid.load();
    initDate();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
    $("#saveSolution").click(function(){
    	$("#myModalProblem").modal("hide");
    	var handleMsg=$("#solutionException").val();
    	if (handleMsg.length<5) {
    		alert("内容不能少于五个字")
    		return ;
    	}
    	/*var type =$("#handletext").attr("type");
    	var id = $("#handletext").attr("key");
    	var loanId = $("#handletext").attr("loanId");
    	var data =new Array();
    	var obj={};
    	obj["id"]=id;
    	obj["type"]=type;
    	obj["handleMsg"]=handleMsg;
    	obj["loanId"] = loanId;
    	data[0]=obj;*/
    	//var dataParam =JSON.stringify(data);
    	$.ajax({
    		url:sys.rootPath + '/baidu/cash/solution.html',
    		type:"POST",
    		data:{"transactionApplyId":$("#transactionApplyId").val(),"solutionException":$("#solutionException").val()},
    		dataType:"json",
    		success:function(data){
    			if(data='success'){
    				 grid.refresh(true);
    			}
    		},
    		error:function(data){
    			toastr["error"]("异常了!!","消息提示");
    		}
    		
    	});
    });
   
});

    

//自定义查询
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['name'] = $("#name").val();
    grid.parameters['mobile'] =  $("#mobile").val();
    grid.parameters['identityNo'] =$("#identityNo").val();
    grid.parameters['custId'] = $("#custId").val();
    grid.parameters['transactionId'] = $("#transactionId").val();
 if( $("#applyResult").val()!=-1){
    	
	 grid.parameters['applyResult'] = $("#applyResult").val();
    }
    if(($("#applyBegin").val()=="" && $("#applyEnd").val()!="") || ($("#applyEnd").val()=="" && $("#applyBegin").val()!="")){
    	alert("请填写完整的提现开始时间");
    	return ;
    }
    grid.parameters['applyBegin'] = $("#applyBegin").val();
    grid.parameters['applyEnd'] = $("#applyEnd").val();
    grid.refresh(true);
}
function cashDetail( transactionApplyId){
	//$('#myModal').modal('show');
	 $.ajax({
         type: "POST",
         url: sys.rootPath+ '/baidu/cash/cashDetail.html',
         data: {transactionApplyId:transactionApplyId},
         dataType: "json",
         success: function(data){
        	 $("#custLevel").html(data.custLevel);
        	 $("#dayRate").html(data.dayRate);
        	 $("#custLevel").html(data.custLevel);//征信模型分
        	 if(data.transactionNode=="lockOrder"){
        		 
        		 $("#transactionNode").html("锁单");
        	 }
        	 else	 if(data.transactionNode=="isSubOrder"){
        		 
        		 $("#transactionNode").html("可交单");
        	 }
        	 else if(data.transactionNode=="whiteKnight"){
        		 
        		 $("#transactionNode").html("白骑士");
        	 }
        	 
        	 else	 if(data.transactionNode=="none"){
        		 
        		 $("#transactionNode").html("成功");
        	 }
        		else	 if(data.transactionNode=="decision"){
            		
            		$("#transactionNode").html("决策");
            	}
            	 else if (data.transactionNode=="baseField"){
            		 $("#transactionNode").html("基本信息错误"); 
            	 }
            	 else{
            		 $("#transactionNode").html("错误信息不详"); 
            	 }
        	 
        	 
 if(data.retCode=="0"){
        		 
        		 $("#retCode").html("成功");//对外拒绝原因
        	 }
        	 else if(data.retCode=="802"){
        		 
        		 $("#retCode").html("综合评分不符合");//对外拒绝原因
        	 }
        	 else	 if(data.retCode=="801"){
        		 
        		 $("#retCode").html("黑名单");//对外拒绝原因
        	 }
        	 else{
        		 $("#retCode").html("拒绝"); 
        	 }
        	 
            	 
        	// $("#externalReasionMsg").html(data.externalReasionMsg);//对外拒绝原因
        	 $("#remark").html(data.remark);
        	 $('#myModal').modal('show');
         }
     });
	
}

function initDate() {
    $("#applyBegin").datetimepicker({
    	format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true, 
    }).on('changeDate',function(e){
    });
    $("#applyEnd").datetimepicker({
        format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
   
    
}
function solutionException(value){
	$("#transactionApplyId").val(value);
	 $('#myModalProblem').modal('show');
}

