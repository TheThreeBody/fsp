Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
var dtGridColumns = [
{
    id : 'cust_id',
    title : '用户的ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cust_name',
    title : '姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	return fsp.desensitization.nameDesensy(value);
    }
    
},{
    id : 'id_no',
    title : '身份证号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	return fsp.desensitization.idCardDesensy(value);
    
    }
},{
    id : 'cust_mobile',
    title : '手机号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	return fsp.desensitization.mobileDesensy(value);
    }
},{
    id : 'pay_no',
    title : '放款流水号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'to_bank_name',
    title : '所属银行',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'bank_card_num',
    title : '银行卡号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	return fsp.desensitization.bankCardDesensy(value);
    }
},{
    id : 'create_time',
    title : '提现申请时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	if (value == undefined || value ==null ) {
    		return "";
    	} else {
    		return value;
    	}
    }
},{
    id : 'loan_rel_time',
    title : '放款成功时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	if (value == undefined || value ==null ) {
    		return "";
    	} else {
    		return value;
    	}
    }
},{
    id : 'amount',
    title : '申请提现金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'loan_period',
    title : '期数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'order_status',
    title : '放款状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if (value =="6" || value =="2"){
    		return '<span class ="fail" data-toggle="popover" data-placement="top" data-content="'+record.remark+'">放款失败</span>';
    	}
    	if (value == "3") {
    		return '<span>待放款</span>';
    	}
    	if (value =="4" || value =="5") {
    		return '<span>放款成功</span>';
    	}
    	if (value== "7") {
    		if (record.operation_msg == undefined ) {
    			return '<span class ="failloan" data-toggle="popover" data-placement="left" data-content="">已解约</span>';
    		} else {
    			return '<span class ="failloan" data-toggle="popover" data-placement="left" data-content="'+record.operation_msg+'">已解约</span>';
    		}
    		
    	}
    	return '';
    }
},{
    id : 'order_status',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if (value =="6"){
    		return '<span class="label label-sm label-success arrowed arrowed-righ jieyue" key ="'+record.vbs_bid+'" status="'+record.operation_status+'">解约</span><span class="label label-sm label-success arrowed arrowed-righ submit" custname="'+record.cust_name+'" back="'+record.to_bank_name+'" card="'+record.bank_card_num+'" amount="'+record.amount+'" term="'+record.loan_period+'" key="'+record.vbs_bid+'" status="'+record.operation_status+'">提交放款</span>';   
    	}
    	return "";
 }
}

];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/loanandexception/loanDataList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tableStyle:'width:2000px',
    tools : 'refresh|print',
    exportFileName : '放款信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
toastr.options = {  
        closeButton: true,  
        debug: false,  
        progressBar: true,  
        positionClass: "toast-bottom-center",  
        onclick: null,  
        showDuration: "300",  
        hideDuration: "1000",  
        timeOut: "10000",  
        extendedTimeOut: "1000",  
        showEasing: "swing",  
        hideEasing: "linear",  
        showMethod: "fadeIn",  
        hideMethod: "fadeOut"  
    };  
$(function() {
    //grid.load();
    $("#btnSearch").click(customSearch);

    //注册回车键事件
    document.onkeypress = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    initDate();
    $(document).on('click','.jieyue',function(){
    	var status = $(this).attr("status");
    	var vbs_bid = $(this).attr("key");
    	$("#comment").attr("data",vbs_bid);	
    	$("#jieyue").modal("show");
    });

    $(document).on('click','.submit',function(){
    	var status = $(this).attr("status");
    	var vbs_bid = $(this).attr("key");
    	$("#confirmDiv").attr("data",vbs_bid);
    	var confirmName = $(this).attr("custname");
    	confirmName = fsp.desensitization.nameDesensy(confirmName);
    	$("#confirmName").html(confirmName);
    	var confirmBack = $(this).attr("back");
    	$("#confirmBack").html(confirmBack);
    	var confirmCardNo = $(this).attr("card");
    	confirmCardNo = fsp.desensitization.bankCardDesensy(confirmCardNo);
    	$("#confirmCardNo").html(confirmCardNo);
    	var confirmAmount = $(this).attr("amount");
    	$("#confirmAmount").html(confirmAmount)
    	var confirmTerm = $(this).attr("term");
    	$("#confirmTerm").html(confirmTerm);
    	$('#myModal').modal('show');
    });
    $("#confirmYes").click(function(){
    	$('#myModal').modal('hide');
    	var vbs_bid = $("#confirmDiv").attr("data");
    	$.ajax({
    		url:sys.rootPath + '/baidu/loanandexception/submitLoan.html',
    		type:"post",
    		data:{"vbs_bid":vbs_bid,"type":"loan","comment":""},
    		dataType:"json",
    		success:function(data){
    			if (data.code == "000000") {
    				toastr["success"](data.msg,"消息提示");
    				grid.refresh(true);
    			} else {
    				toastr["error"](data.msg,"消息提示");
    			}
    		},
    		error:function(data){
    			toastr["error"](data,"消息提示");
    		}
    		
    	});
    });
    $("#saveData").click(function(){
    	$("#jieyue").modal("hide");
    	var vbs_bid = $("#comment").attr("data");
    	var comment = $("#comment").val();
    	if (comment.length<5) {
    		toastr["warning"]("备注原因必须超过5个字!!","消息提示");
    		return ;
    	}
    	$.ajax({
    		url:sys.rootPath + '/baidu/loanandexception/submitLoan.html',
    		type:"post",
    		data:{"vbs_bid":vbs_bid,"type":"cancle","comment":comment},
    		dataType:"json",
    		success:function(data){
    			if (data.code == "000000") {
    				toastr["success"](data.msg,"消息提示");
    				grid.refresh(true);
    			} else {
    				toastr["error"](data.msg,"消息提示");
    			}
    		},
    		error:function(data){
    			toastr["error"](data,"消息提示");
    		}
    		
    	});
    });
    $(document).on("mouseenter",".failloan",function(){
    	$(this).popover('show');
    	
    });
    $(document).on("mouseleave",".failloan",function(){
    	$(this).popover('hide');
    });  
    $(document).on("mouseenter",".fail",function(){
    	$(this).popover('show');
    	
    });
    $(document).on("mouseleave",".fail",function(){
    	$(this).popover('hide');
    });  
});

//自定义查询
function customSearch() {
	if ($("#applyBegin").val() =="" && $("#applyEnd").val() == "") {
		
	} else {
		if ($("#applyBegin").val() !="" && $("#applyEnd").val() != ""){
			
		} else {
			toastr["warning"]("请填写完整的提现申请时间!!","消息提示");
			return ;
		}
	};
	if ($("#loanBegin").val() == "" && $("#loanEnd").val() == ""){
		
	} else {
		if ($("#loanBegin").val() != "" && $("#loanEnd").val() != ""){
			
		} else {
			toastr["warning"]("请填写完整的放款成功时间!!","消息提示");
			return;
		}
	}
    grid.parameters = new Object();
    grid.parameters['custId'] = $("#custId").val();
    grid.parameters['custCardNo'] = $("#custCardNO").val();
    grid.parameters['custMobile'] = $("#custMobile").val();
    grid.parameters['loanNo'] = $("#loanNo").val();
    grid.parameters['loanStatus'] = $("#loanStatus").val();
    grid.parameters['applyBegin'] = $("#applyBegin").val();
    grid.parameters['applyEnd'] = $("#applyEnd").val();
    grid.parameters['loanBegin'] = $("#loanBegin").val();
    grid.parameters['loanEnd'] = $("#loanEnd").val();
    grid.parameters['vbsNO'] = $("#vbsNO").val();
    if ($("#custId").val()=="" && $("#custCardNO").val()==""&& $("#custMobile").val()==""&&
    	$("#loanNo").val() =="" && $("#applyBegin").val() == ""&&$("#applyEnd").val()=="" &&
    		$("#loanBegin").val()=="" && $("#loanEnd").val()==""&& $("#vbsNO").val()==""){
    	 var endDate = new Date().Format("yyyy-MM-dd");
    	    var a = new Date();
    	    a = a.valueOf();
    	    a = a - 6* 24 * 60 * 60 * 1000;
    	    a = new Date(a);
    	    var startDate = new Date(a).Format("yyyy-MM-dd");
    	    $("#applyBegin").val(startDate);
    	    $("#applyEnd").val(endDate);
    	    grid.parameters['applyBegin'] = $("#applyBegin").val();
    	    grid.parameters['applyEnd'] = $("#applyEnd").val();
    }
    
    grid.refresh(true);
}
function initDate() {
    $("#applyBegin").datetimepicker({
    	format : 'yyyy-mm-dd',
        autoclose : true,
        minView: "month",
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true, 
    }).on('changeDate',function(e){
    });
    $("#applyEnd").datetimepicker({
        format : 'yyyy-mm-dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#loanBegin").datetimepicker({
        format : 'yyyy-mm-dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#loanEnd").datetimepicker({
        format : 'yyyy-mm-dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
}
