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
/*{
    id : 'exception_msg',
    title : '异常信息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},*/
{
    id : 'cur_date',
    title : '账务日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'exception_type',
    title : '异常类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	if (value =="file_receive" || value == "file_check") {
    		return "文件提取失败";
    	};
    	if (value == "file_loan" || value == "file_plan" || value == "file_repay" || value == "file_item" || value == "file_open" ) {
    		return "文件解析失败";
    	};
    	if (value == "bill") {
    		return "出账失败";
    	};
    	if (value == "instruncation"){
    		return "填帐失败";
    	}
    	if (value == "item") {
    		return "对账失败";
    	}
    	return "";
    }
}, {
    id : 'issue_time',
    title : '异常时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype:'string',
    oformat:'yyyy-MM-dd hh:mm:ss',
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
    id : 'handle_status',
    title : '处理状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	if (value == "1") {
    		return "已解决";
    	} else {
    		return "未解决";
    	}
    }
},
{
    id : 'exception_type',
    title : '文件类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
    	if (value =="file_receive" || value == "file_check") {
    		return "<<对账文件>>";
    	};
    	if (value == "file_loan" || value == "file_plan" || value == "file_repay" || value == "file_item" || value == "file_open" ) {
    		return "<<对账文件>>";
    	};
    	if (value == "bill") {
    		return "<<分期计划文件>>";
    	};
    	if (value == "instruncation"){
    		return "<<还款信息文件>>";
    	}
    	if (value == "item") {
    		return "<<还款明细文件>>"
    	}
    	return "";
    }
}, {
    id : 'vbs_id',
    title : '订单号',
    type : 'string',
    columnClass : 'text-center',
},
{
    id : 'handle_status',
    title : '操作',
    type : String,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if (value == "1") {
    		if (record.exception_type == "bill" || record.exception_type == "instruncation" || record.exception_type == "item") {
    			return '<span class="label label-sm label-success arrowed arrowed-righ handled" person="'+record.handle_person+'" msg ="'+record.handle_msg+'" time="'+record.handle_time+'">查看处理结果</span>&nbsp&nbsp&nbsp&nbsp<span class="label label-sm label-success arrowed arrowed-righ chakan" key ='+record.id+' type='+ record.exception_type +'>查看解析字段</span>';
    		} else {
    			return '<span class="label label-sm label-success arrowed arrowed-righ handled" person="'+record.handle_person+'" msg ="'+record.handle_msg+'" time="'+record.handle_time+'">查看处理结果</span>';
    		}	
    	} else {
    		if (record.exception_type == "bill" || record.exception_type == "instruncation" || record.exception_type == "item") {
    			return '<span class="label label-sm label-success arrowed arrowed-righ handle" type="'+record.exception_type+'" key ='+record.id+' loanId="'+record.loan_id+'">解决</span>&nbsp&nbsp&nbsp&nbsp<span class="label label-sm label-success arrowed arrowed-righ chakan" key ='+record.id+' type='+ record.exception_type +'>查看解析字段</span>';
    		} else {
    			return '<span class="label label-sm label-success arrowed arrowed-righ handle" type="file" key ='+record.id+'>解决</span>';
    		}	
    	}

       
    }
}
];
var repay = [
{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},
{
    id : 'cur_date',
    title : '账务日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'loan_id',
    title : '借据号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'tran_date',
    title : '交易日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'tran_time',
    title : '交易时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'seq_no',
    title : '交易流水号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'total_amt',
    title : '交易金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'int_reduced_amt',
    title : '减免利息金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'fund_fee_reduced_amt',
    title : '减免借款服务费金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'pnlt_reduced_amt',
    title : '减免罚息金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'income_amt',
    title : '实收金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'prin_amt',
    title : '本金发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'int_amt',
    title : '利息发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'fund_fee_amt',
    title : '借款服务费发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'pnlt_int_amt',
    title : '罚息发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}
];
var plan = [
{
    id : 'id',
    title : '编号',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cur_date',
    title : '账务日期',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'loan_id',
    title : '借据号',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'term_no',
    title : '期序',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'start_date',
    title : '开始日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'end_date',
    title : '到期日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}
,{
    id : 'clear_date',
    title : '结清日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'prin_total',
    title : '应还本金',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'prin_repay',
    title : '已还本金',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_total',
    title : '应还利息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_repay',
    title : '已还利息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_bal',
    title : '利息余额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fund_fee_total',
    title : '应还借款服务费',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fund_fee_repay',
    title : '已还借款服务费',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pnlt_int_total',
    title : '应还罚息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pnlt_int_repay',
    title : '已还罚息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_reduced_amt',
    title : '减免利息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fund_fee_reduced_amt',
    title : '减免罚息',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pnlt_reduced_amt',
    title : '减免借款服务费',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'term_status',
    title : '本期状态',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}
];
var item = [
{
    id : 'id',
    title : '标识',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cur_date',
    title : '账务日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'loan_id',
    title : '借据号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'tran_date',
    title : '交易日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'tran_time',
    title : '交易时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'seq_no',
    title : '交易流水号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'term_no',
    title : '期数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'event',
    title : '交易事件',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'total_amt',
    title : '交易金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_reduced_amt',
    title : '减免利息金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fund_fee_reduced_amt',
    title : '减免罚息金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pnlt_reduced_amt',
    title : '减免借款服务费金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'income_amt',
    title : '实收金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'prin_amt',
    title : '本金发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'int_amt',
    title : '利息发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'fund_fee_amt',
    title : '借款服务费发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pnlt_int_amt',
    title : '罚息发生额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}          
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/loanandexception/exceptionDataList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : '异常信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
var planGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/baidu/loanandexception/exceptionDataDetail.html',
	    columns : plan,
	    gridContainer : 'planContainer',
	    tools : 'refresh|print',
	    exportFileName : '异常信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
	var plangrid = $.fn.dlshouwen.grid.init(planGridOption);
var repayGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/baidu/loanandexception/exceptionDataDetail.html',
	    columns : repay,
	    gridContainer : 'repayContainer',
	    tools : 'refresh|print',
	    exportFileName : '异常信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
var repaygrid = $.fn.dlshouwen.grid.init(repayGridOption);
var itemGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/baidu/loanandexception/exceptionDataDetail.html',
	    columns : item,
	    gridContainer : 'itemContainer',
	    tools : 'refresh|print',
	    exportFileName : '异常信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
var itemgrid = $.fn.dlshouwen.grid.init(itemGridOption);
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
    $(document).on("click",".handled",function(){
    	var person = $(this).attr("person");
    	$("#handleperson").html(person);
    	var time = $(this).attr("time");
    	$("#handletime").html(time);
    	var method = $(this).attr("msg");
    	$("#handlemethod").html(method);
    	$("#handlemsg").modal("show");
    });
    $(document).on('click','.handle',function(){
    	var id = $(this).attr("key");
    	$("#handletext").attr("key",id);
    	var type = $(this).attr("type");
    	$("#handletext").attr("type",type);
    	var loanId = $(this).attr("loanId");
    	if (loanId == undefined) {
    		loanId = "";
    	}
    	$("#handletext").attr("loanId",loanId);
    	$("#handletext").val("");
    	$('#myModal').modal('show');
    });
    $(document).on('click','.chakan',function(){
    	var id = $(this).attr("key");
    	$("#handletext").attr("key",id);
    	var type = $(this).attr("type");
    	$("#handletext").attr("type",type)
    	if (type == "item"){
    		itemgrid.parameters = new Object();
        	itemgrid.parameters['id'] = id;
        	itemgrid.parameters['type'] = type;
        	itemgrid.refresh(true);
        	$("#itemModal").modal("show");
    	} else {
    		if (type == "instruncation") {
    			repaygrid.parameters = new Object();
        		repaygrid.parameters['id'] = id;
        		repaygrid.parameters['type'] = type;
        		repaygrid.refresh(true);
            	$("#repayModal").modal("show");
    		} else {
    			if (type = "bill") {
    				plangrid.parameters = new Object();
    				plangrid.parameters['id'] = id;
    				plangrid.parameters['type'] = type;
    				plangrid.refresh(true);
                	$("#planModal").modal("show");
    			}
    		}
    	}

    });
    $("#saveData").click(function(){
    	$("#myModal").modal("hide");
    	var handleMsg=$("#handletext").val();
    	if (handleMsg.length<5) {
    		toastr["warning"]("备注原因必须超过5个字","消息提示");
    		return ;
    	}
    	var type =$("#handletext").attr("type");
    	var id = $("#handletext").attr("key");
    	var loanId = $("#handletext").attr("loanId");
    	var data =new Array();
    	var obj={};
    	obj["id"]=id;
    	obj["type"]=type;
    	obj["handleMsg"]=handleMsg;
    	obj["loanId"] = loanId;
    	data[0]=obj;
    	var dataParam =JSON.stringify(data);
    	$.ajax({
    		url:sys.rootPath + '/baidu/loanandexception/updateExceptionData.html',
    		type:"post",
    		data:{"data":dataParam},
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
    			toastr["error"]("异常了!!","消息提示");
    		}
    		
    	});
    });
});

//自定义查询
function customSearch() {
	if ($("#beginDate").val() == "" || $("#endDate").val() == "") {
		toastr["warning"]("请选择时间段!!","消息提示");
		return;
	} 
    grid.parameters = new Object();
    grid.parameters['beginDate'] = $("#beginDate").val();
    grid.parameters['endDate'] = $("#endDate").val();
    grid.refresh(true);
}
function initDate() {
    $("#beginDate").datetimepicker({
    	format : 'yyyy-mm-dd',
    	minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true, 
    });
    $("#endDate").datetimepicker({
    	format : 'yyyy-mm-dd',
    	minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true, 
    });
    var endDate = new Date().Format("yyyy-MM-dd");
    var a = new Date();
    a = a.valueOf();
    a = a - 6* 24 * 60 * 60 * 1000;
    a = new Date(a);
    var startDate = new Date(a).Format("yyyy-MM-dd");
    $("#beginDate").val(startDate);
    $("#endDate").val(endDate);
}
