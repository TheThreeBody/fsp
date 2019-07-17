var dtGridColumns = [
{
    id : 'issue_id',
    title : '错误ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hide:true
},{
    id : 'cur_date',
    title : '账务日期',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
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
},{
    id : 'issue_time',
    title : '问题时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'exception_type',
    title : '文件名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo){
        //console.log(record.issue_id);
        if (value =="file_receive" || value == "file_check") {
            return "<<对账文件>>";
        };
        if (value == "file_loan" || value == "file_plan" || value == "file_repay" || value == "file_item" || value == "file_open" ) {
            return "<<对账文件>>";
        };
        if (value == "bill") {
            return '<a id="' + record.issue_id + '" class="fileLink"  value="bill"> <<分期计划文件>> </a>';
        };
        if (value == "instruncation"){
            return '<a id="' + record.issue_id + '" class="fileLink"   value="instruncation"> <<还款信息文件>> </a>';
        }
        if (value == "item") {
            return '<a id="' + record.issue_id + '" class="fileLink"   value="item"> <<还款明细文件>> </a>';
        }
        return "";
    }
}
];

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/baidu/indent/file.html?vbsId=' + $('#vbsId').val(),
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : '异常信息',
    pageSize : 5,
    pageSizeLimit : [5, 10]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);

$(function() {
    var loanForm = $("#loanForm").val();
    if(null == loanForm|| undefined == loanForm||"" == loanForm){
        $("#l_status").html("无");
    }
    if('1' == loanForm){
        $("#l_status").html("正常");
    }
    if('2' == loanForm){
        $("#l_status").html("逾期");
    }
    cName = $("#cName").html();
    cName = cName.trim();
    cName = fsp.desensitization.nameDesensy(cName);
    $("#cName").html(cName);

    cMobile = $("#cMobile").html();
    cMobile = cMobile.trim();
    console.log(cMobile);
    cMobile = fsp.desensitization.mobileDesensy(cMobile);
    $("#cMobile").html(cMobile);

    cIdCard = $("#cIdCard").html();
    cIdCard = cIdCard.trim();
    cIdCard = fsp.desensitization.idCardDesensy(cIdCard);
    $("#cIdCard").html(cIdCard);

    cBankCard = $("#cBankCard").html();
    cBankCard = cBankCard.trim();
    cBankCard = fsp.desensitization.bankCardDesensy(cBankCard);
    $("#cBankCard").html(cBankCard);

    grid.load();


    $("#btnSearch").click(customSearch);

    $(document).on('click','.fileLink',fileDetailSearch);

    //$(".detail").click(indentDetail);
    //注册回车键事件
    //document.onkeypress = function(e){
    //    var ev = document.all ? window.event : e;
    //    if(ev.keyCode==13) {
    //        customSearch();
    //    }
    //};
    //initDate();

});

function customSearch() {
    grid.parameters = new Object();
    //grid.parameters['vbsId1'] = $("#vbsIdSearch1").val();
    //grid.parameters['vbsId2'] = $("#vbsIdSearch2").val();
    var detailURL = '/baidu/indent/detailUI.html';

    var vbsId = $("#vbsIdSearch1").val();
    var bdLoanId = $("#bdLoanId").val();
    webside.common.loadPage(detailURL  + '?vbsId=' + vbsId + '&bdLoanId=' + bdLoanId);
    //grid.refresh(true);
    //$('.dataTable').refresh();
}

//model params
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
//toastr.options = {
//    closeButton: true,
//    debug: false,
//    progressBar: true,
//    positionClass: "toast-bottom-center",
//    onclick: null,
//    showDuration: "300",
//    hideDuration: "1000",
//    timeOut: "10000",
//    extendedTimeOut: "1000",
//    showEasing: "swing",
//    hideEasing: "linear",
//    showMethod: "fadeIn",
//    hideMethod: "fadeOut"
//};
function fileDetailSearch() {
    var issueId = $(this).attr("id");
    $("#handletext").attr("issueId",issueId);
    var type = $(this).val();
    $("#handletext").attr("type",type)
    if (type = "bill") {
        plangrid.parameters = new Object();
        plangrid.parameters['id'] = issueId;
        plangrid.parameters['type'] = type;
        plangrid.refresh(true);
        $("#planModal").modal("show");
    }
    if (type == "instruncation") {
        repaygrid.parameters = new Object();
        repaygrid.parameters['id'] = issueId;
        repaygrid.parameters['type'] = type;
        repaygrid.refresh(true);
        $("#repayModal").modal("show");
    }
    if (type == "item"){
        itemgrid.parameters = new Object();
        itemgrid.parameters['id'] = issueId;
        itemgrid.parameters['type'] = type;
        itemgrid.refresh(true);
        $("#itemModal").modal("show");
    }
}