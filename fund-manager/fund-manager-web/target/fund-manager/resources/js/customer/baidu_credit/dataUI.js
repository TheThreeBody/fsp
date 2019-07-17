var dtGridColumns = [
{
    id : 'vbs_bid',
    title : '业务号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cust_id',
    title : '用户ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cust_name',
    title : '姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
        ,
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return fsp.desensitization.nameDesensy(value);
    }
},{
    id : 'id_no',
    title : '身份证号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
    ,
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return fsp.desensitization.idCardDesensy(value);
    }
},{
    id : 'cust_mobile',
    title : '手机号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
    ,
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return fsp.desensitization.mobileDesensy(value);
    }
},{
    id : 'bd_loan_id',
    title : '借据号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'create_time',
    title : '提现申请时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
},{
    id : 'loan_rel_time',
    title : '放款成功时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
},{
    id : 'order_status',
    title : '还款状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        //if (value == "3") {
        //    return '<span>待放款</span>';
        //}
        if (value =="4" ) {
            return '<span>还款中</span>';
        }
        if(value == "5") {
            return '<span>已结清</span>';
        }
        //if (value =="6" ) {
        //    return '<span>放款失败</span>';
        //}
        return '';
    }
},{
    id : 'handle_status',
    title : '账务状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value =="0") {
            return '<span>异常</span>';
        }
        if (value =="1") {
            return '<span>正常</span>';
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
        //if (value =="3" && record.vbs_notify_number !=undefined && parseInt(record.vbs_notify_number)>0){
            return '<button class="btn btn-sm detail" key =' + record.vbs_bid + '>查看详情</button>';
        //<span class="label label-sm  submit" custname="'+record.cust_name+'" back="'+record.to_bank_name+'" card="'+record.bank_card_num+'" amount="'+record.amount+'" term="'+record.loan_period+'" key="'+record.vbs_bid+'">提交放款</span>;
        //}
    }
}
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/indent/data.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : '异常信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    //grid.load();
    //console.log($('#app1').val());
    //console.log($('#app2').val());
    initDate();

    var today = new Date();
    var day7 = new Date();
    day7.setDate(day7.getDate() - 7);

    $('#applyBegin').val(day7.toLocaleDateString());
    $('#applyEnd').val(today.toLocaleDateString());
    //$('#loanBegin').val(day7.toLocaleDateString());
    //$('#loanEnd').val(today.toLocaleDateString());

    $("#btnSearch").click(customSearch);

    $(document).on('click','.detail',indentDetail);

    //$(".detail").click(indentDetail);
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
    grid.parameters['vbsId'] = $("#vbsId").val();
    grid.parameters['custId'] = $("#custId").val();
    grid.parameters['custName'] = $("#custName").val();
    grid.parameters['custIdNo'] = $("#custIdNo").val();
    grid.parameters['custMobile'] = $("#custMobile").val();
    grid.parameters['bdLoanId'] = $("#bdLoanId").val();
    grid.parameters['loanStatus'] = $("#loanStatus").val();
    grid.parameters['financialStatus'] = $("#financialStatus").val();
    grid.parameters['applyBegin'] = $("#applyBegin").val();
    grid.parameters['applyEnd'] = $("#applyEnd").val();
    grid.parameters['loanBegin'] = $("#loanBegin").val();
    grid.parameters['loanEnd'] = $("#loanEnd").val();
    grid.refresh(true);
}
function indentDetail(){
    var detailURL = '/baidu/indent/detailUI.html';
    var vbsId = $(this).attr('key');
    webside.common.loadPage(detailURL + '?vbsId=' + vbsId);
}
function initDate() {
    $("#applyBegin").datetimepicker({
        format : 'yyyy/mm/dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#applyEnd").datetimepicker({
        format : 'yyyy/mm/dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#loanBegin").datetimepicker({
        format : 'yyyy/mm/dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
    $("#loanEnd").datetimepicker({
        format : 'yyyy/mm/dd',
        minView: "month",
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
    }).on('changeDate',function(e){
    });
}