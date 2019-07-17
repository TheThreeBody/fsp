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
    id : 'creditStatus',
    title : '授信结果',
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
    	    	
    	    }
},{
    id : 'beginTime',
    title : '授信申请时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'endTime',
    title : '授信成功时间',
    type :'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'initialAmount',
    title : '额度',
    type :  'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'creditAmount',
    title : '决策额度',
    type :  'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totolAmount',
    title : '渠道额度',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'amountStatus',
    title : '额度状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function(value){
    	if(value==1){
    		return '未激活'
    	}
    	if(value==2){
    		return '已激活'
    	}
    	if(value==3){
    		return '失效'
    	}
    	if(value==4){
    		return ''
    	}
    	
    }
   
},{
    id : 'vauleAccount',
    title : '可用额度',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    
},{
    id : 'creditExpirationDate',
    title : '有效期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'processId',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution:function(value){
    	return "<span><a onclick='creditDetail("+value+")'>查看详情</a></span>";
    	
    }
}
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/credit/creditList.html',
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
    $("#btnSearch").click(customSearch);
   initDate();
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});

//自定义查询
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['name'] = $("#name").val();
    grid.parameters['mobile'] =  $("#mobile").val();
    grid.parameters['identityNo'] =$("#identityNo").val();
    grid.parameters['custId'] = $("#custId").val();
    if( $("#creditStatus").val()!=-1){
    	
    	grid.parameters['creditStatus'] = $("#creditStatus").val();
    }
    if(($("#applyBegin").val()=="" && $("#applyEnd").val()!="") || ($("#applyEnd").val()=="" && $("#applyBegin").val()!="")){
    	alert("请填写完整的授信开始时间");
    	return ;
    }
    if(($("#creditBegin").val()=="" && $("#creditEnd").val()!="") || ($("#creditEnd").val()=="" && $("#creditBegin").val()!="")){
    	alert("请填写完整的授信成功时间");
    	return;
    }
    grid.parameters['applyBegin'] = $("#applyBegin").val();
    grid.parameters['applyEnd'] = $("#applyEnd").val();
    grid.parameters['creditEnd'] = $("#creditEnd").val();
    grid.parameters['creditBegin'] = $("#creditBegin").val();
 if( $("#amountStatus").val()!=-1){
    	
	 grid.parameters['amountStatus'] = $("#amountStatus").val();
    }
 grid.refresh(true);
}
function creditDetail( processId){
	//$('#myModal').modal('show');
	 $.ajax({
         type: "post",
         url: sys.rootPath+ '/baidu/credit/creditDetail.html',
         data: {"processId":processId},
         dataType: "json",
         success: function(data){
        	 $("#custLevel").html(data.custLevel);
        	 $("#dayRate").html(data.dayRate+"/日");
        	 $("#creditScore").html(data.creditScore);//征信模型分
        	 if(data.processNode=="lockOrder"){
        		 
        		 $("#processNode").html("锁单");
        	 }
        	 else  if (data.processNode=="isSubOrder"){
        		 
        		 $("#processNode").html("可交单");
        	 }
        	else if(data.processNode=="whiteKnight"){
        		 
        		 $("#processNode").html("白骑士");
        	 }
        	else	 if(data.processNode=="none"){
        		 
        		 $("#processNode").html("成功");
        	 }
        	else	 if(data.processNode=="decision"){
        		
        		$("#processNode").html("决策");
        	}
        	 else if (data.processNode=="baseField"){
        		 $("#processNode").html("基本信息错误"); 
        	 }
        	 else{
        		 $("#processNode").html("错误信息不详"); 
        	 }
        	 
        	 if(data.reasonMsg=="00000"){
        		 
        		 $("#reasonMsg").html("成功");//对外拒绝原因
        	 }
        	 else if(data.reasonMsg=="802"){
        		 
        		 $("#reasonMsg").html("综合评分不符合");//对外拒绝原因
        	 }
        	 else	 if(data.reasonMsg=="801"){
        		 
        		 $("#reasonMsg").html("黑名单");//对外拒绝原因
        	 }
        	 else{
        		 $("#reasonMsg").html("拒绝"); 
        	 }
        	 $("#creditMsg").html(data.creditMsg);
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
    $("#creditBegin").datetimepicker({
        format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#creditEnd").datetimepicker({
        format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    
}
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
