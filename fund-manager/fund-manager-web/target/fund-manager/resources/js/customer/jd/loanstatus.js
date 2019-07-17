var order_status_type={"3":"待放款","7":"已解约","4":"放款成功","5":"已结清","6":"放款失败"};
var vbs_notify_type={"1":"手动放款","2":"自动放款"};

var operateStatus={"1":"成功","2":"失败"};
var operateType={"1":"再次放款","2":"解约"};
var dtGridColumns = [
		{
			id : 'orderId',
			title : '订单id',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header'
		},
		{
			id : 'custId',
			title : '用户ID',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header'
		},
		{
			id : 'vbsId',
			title : '业务号',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header'
		},
		{
			id : 'name',
			title : '用户姓名',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'identityNo',
			title : '身份证号',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'loanNo',
			title : '借据号',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'bank',
			title : '所属银行',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'cardNo',
			title : '放款银行卡号',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'loanTime',
			title : '申请提现时间',
			type : 'date',
			format : 'yyyy-MM-dd hh:mm:ss',
			otype : 'string',
			oformat : 'yyyy-MM-dd hh:mm:ss',
			columnClass : 'text-left',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'sm|xs'
		},
		{
			id : 'paymentTime',
			title : '放款时间',
			type : 'date',
			format : 'yyyy-MM-dd hh:mm:ss',
			otype : 'string',
			oformat : 'yyyy-MM-dd hh:mm:ss',
			columnClass : 'text-left',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'sm|xs',
			resolution : function(value, record, column, grid, dataNo, columnNo) {
				if(record.status==4||record.status==5){
					return value;
				}
				return "";
			}
		},
		{
			id : 'loanTerm',
			title : '借款期数',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'vbsNotifyType',
			title : 'vbs通知类型',
			type : 'string',
			codeTable:vbs_notify_type,
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'vbsNotifyNum',
			title : 'vbs通知次数(失败)',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'status',
			title : '放款状态',
			type : 'string',
			codeTable:order_status_type,
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'orderDesc',
			title : '订单描述',
			type : 'string',
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'loanAmount',
			title : '申请提现金额',
			type : 'number',
			format : '#,###.00',
			columnClass : 'text-left',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'operateStatus',
			title : '操作状态',
			type : 'string',
			codeTable:operateStatus,
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'operateType',
			title : '操作类型',
			type : 'string',
			codeTable:operateType,
			columnClass : 'text-center',
			headerClass : 'dlshouwen-grid-header',
			hideType : 'xs'
		},
		{
			id : 'operation',
			title : '操作',
			type : 'string',
			columnClass : 'text-center',
			resolution : function(value, record, column, grid, dataNo, columnNo) {
				var content = '';
				if(record.status==6){
					content += '<button class="btn btn-xs btn-default" onclick="AgainLoan('+record.vbsId+')"><i class="fa fa-edit"></i>提交放款</button>';
					content +='  ';
					content += '<button class="btn btn-xs btn-danger" onclick="Surrender('+record.vbsId+')"><i class="fa fa-trash-o"></i>解约</button>';
					return content;
				}
				return content;
			}
		} ];
var dtGridOption = {
	lang : 'zh-cn',
	ajaxLoad : true,
	tableStyle : 'width:160%;max-width: 200%;',
	loadURL : sys.rootPath + '/jd/loan/list.html',
	columns : dtGridColumns,
	gridContainer : 'dtGridContainer',
	toolbarContainer : 'dtGridToolBarContainer',
	tools : 'refresh|print|export[excel,csv,pdf,txt]',
	exportFileName : '订单放款状态信息',
	pageSize : 10,
	pageSizeLimit : [ 10, 20, 30 ]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	grid.load();
	$("#btnSearch").click(customSearch);

	// 注册回车键事件
	document.onkeypress = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			customSearch();
		}
	};
	 initDate();

});


//自定义查询
function customSearch() {
	if ($("#applyBegin").val() =="" && $("#applyEnd").val() == "") {
		
	} else {
		if ($("#applyBegin").val() !="" && $("#applyEnd").val() != ""){
			
		} else {
			alert("请填写完整的提现申请时间");
			return ;
		}
	};
	if ($("#loanBegin").val() == "" && $("#loanEnd").val() == ""){
		
	} else {
		if ($("#loanBegin").val() != "" && $("#loanEnd").val() != ""){
			
		} else {
			alert("请填写完整的放款成功时间");
			return;
		}
	}
    grid.parameters = new Object();
    grid.parameters['custId'] = $("#custId").val();
    grid.parameters['name'] = $("#name").val();
    grid.parameters['identityNo'] = $("#identityNo").val();
    grid.parameters['custMobile'] = $("#custMobile").val();
    grid.parameters['loanNo'] = $("#loanNo").val();
    grid.parameters['status'] = $("#status").val();
    grid.parameters['applyBegin'] = $("#applyBegin").val();
    grid.parameters['applyEnd'] = $("#applyEnd").val();
    grid.parameters['loanBegin'] = $("#loanBegin").val();
    grid.parameters['loanEnd'] = $("#loanEnd").val();
    grid.parameters['vbsId'] = $("#vbsId").val();
    grid.refresh(true);
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
    $("#loanBegin").datetimepicker({
        format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#loanEnd").datetimepicker({
        format : 'yyyy-mm-dd hh:ii:00',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
}



function Surrender(vbsId){
	bootbox.setLocale("zh_CN");
	bootbox.prompt({ 
		  value:'解约备注(必填)',
		  inputType: 'textarea',
		  size: "small",
		  title: "订单解约",
		  callback: function(msg){ 
			  /* result = String containing user input if OK clicked or null if Cancel clicked */
			  if(msg!=null){
				  if(msg=='解约备注(必填)' || msg==''){
					  bootbox.alert("解约备注必须填写");
					  return;
				  }
				  bootbox.confirm({ 
					  size: "small",
					  message: "确认要进行解约吗?", 
					  callback: function(result){
						  if(result){
							  $.ajax({
						    		url:sys.rootPath + '/jd/loan/surrender.html',
						    		type:"post",
						    		data:{"vbsId":vbsId,"remark":msg},
						    		dataType:"json",
						    		success:function(data){
						    			if (data.isSuccess==1) {
						    				bootbox.alert(data.data,function(e){
							    				grid.refresh(true);
							    			});
						    			} else {
						    				bootbox.alert(data.msg);
						    			}
						    		},
						    		error:function(data){
						    			bootbox.alert("ajax异常");
						    		}
						    		
						    	});
						  }
						 
					  }
				});
				  
				  
			  }
		  }
	});
}

function AgainLoan(vbsId){
	bootbox.setLocale("zh_CN");
	bootbox.confirm({ 
		  size: "small",
		  message: "确认要重新放款吗?", 
		  callback: function(result){
			  if(result){
				  $.ajax({
			    		url:sys.rootPath + '/jd/loan/againLoan.html',
			    		type:"post",
			    		data:{"vbsId":vbsId},
			    		dataType:"json",
			    		success:function(data){
			    			if (data.isSuccess==1) {
			    				bootbox.alert(data.data,function(e){
				    				grid.refresh(true);
				    			});
			    			} else {
			    				bootbox.alert(data.msg);
			    			}
			    		},
			    		error:function(data){
			    			bootbox.alert("ajax异常");
			    			
			    		}
			    		
			    	});
			  }
			 
		  }
	});
}


