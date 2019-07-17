var dtGridColumns = [
{
    id : 'isSuccess',
    title : 'isSuccess',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg'
},
{
    id : 'data',
    title : 'data',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg'
},
{
    id : 'msg',
    title : 'msg',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType:'lg'
},
    {
    id : 'vbsId',
    title : 'vbs业务号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        $('#vbsIdS').val(value);
        return value;
    }
}, {
    id : 'orderStatus',
    title : '订单状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        $("#btnSettle").hide();
        if ("" == value || null == value) {
            return '未指定';
        } else if ("1" == value){
            return '1申请中';
        } else if ("2" == value){
            return ' 2申请贷款';
        } else if ("3" == value){
            return '3待放款';
        } else if ("4" == value){
            $("#btnSettle").show();
            $("#btnSettle").click(settleSearch);
            return '4还款中';
        } else if ("5" == value){
            return '5已结清';
        } else if ("6" == value){
            return '6放款失败';
        }
    }
}, {
    id : 'extOrderId',
    title : '第三方订单号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
}, {
    id : 'custId',
    title : '内部客户号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        $("#custId").val(value);
        return value;
    }
}, {
    id : 'idCardNo',
    title : '身份证号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
}, {
    id : 'name',
    title : '姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
}];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/vocs/indent.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,pdf,txt]',
    exportFileName : '融祥订单信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    grid.load();
    $("#btnSearch").click(customSearch);
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
    grid.parameters['vbsId'] = $("#vbsIdS").val();
    grid.parameters['productId'] = $("#productId").val();
    grid.parameters['idCardNo'] = $("#idCardNoS").val();
    grid.parameters['createtime'] = $("#createtime").val();

    grid.refresh(true);
}
function settleSearch() {

    updateSettle();
    $("#custId").val('');
    $('.fa-refresh').click();
    return layer.alert("结清订单成功");
}
function updateSettle() {
    grid.parameters = new Object();
    grid.parameters['vbsId'] = $("#vbsIdS").val();
    grid.parameters['custId'] = $("#custId").val();
    grid.parameters['productId'] = $("#productId").val();
    grid.refresh(true);

}

//$.ajax({
//    type:'post',
//    url:sys.rootPath + '/vocs/indent.html',
//    data:param,
//    contentType: "application/x-www-form-urlencoded; charset=utf-8",
//    beforeSend: function(xhr) {xhr.setRequestHeader("__REQUEST_TYPE", "AJAX_REQUEST");},
//    success:function(datas){
//
//    },
//    error:function(XMLHttpRequest, textStatus, errorThrown){
//
//    }
//});