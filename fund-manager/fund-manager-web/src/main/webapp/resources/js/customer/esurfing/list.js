var dtGridColumns = [
    {
        id : 'custId',
        title : '内部客户号',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
    }, {
        id : 'vbsId',
        title : 'vbs业务号',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    }, {
        id : 'orderStatus',
        title : '订单状态',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution : function(value, record, column, grid, dataNo, columnNo) {
            if(3 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">放款中</span>';
            }
            if(4 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">放款成功</span>';
            }
            if(5 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">还款成功</span>';
            }
            if(6 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">借款失败</span>';
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
        id : 'idCardNo',
        title : '身份证号',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
        ,
        resolution : function(value, record, column, grid, dataNo, columnNo) {
            return fsp.desensitization.idCardDesensy(value);
        }
    }, {
        id : 'name',
        title : '姓名',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
        ,
        resolution : function(value, record, column, grid, dataNo, columnNo) {
            return fsp.desensitization.nameDesensy(value);
        }
    }];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/esurfing/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : 'esurfing订单信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
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

});

//自定义查询
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['vbsId'] = $("#searchKey").val();
    grid.refresh(true);
}

$("#btnFrozen").click(function(url){
    var urll = '/esurfing/frozenUI.html';

    //webside.common.loadPage(urll);
    var rows = grid.getCheckedRecords();
    webside.common.loadPage(urll + '?custId=' + rows[0].custId +'&vbsId=' + rows[0].vbsId);
    //webside.common.loadPage('/esurfing/frozenUI.html')
    //$(".page-content").load(sys.rootPath + urll,function(data, statusTxt, xhr) {
    //
    //    $(".page-content").empty();//清除该节点子元素
    //    data = $.parseJSON(data);
    //
    //});

    //var index;
    //$.ajax({
    //    type : "POST",
    //    url : sys.rootPath + '/esurfing/frozenUI.html',
    //    data : {
    //        "custId" : rows[0].custId
    //    },
    //    dataType : "json",
    //    beforeSend : function()
    //    {
    //        index = layer.load();
    //    },
    //    success : function(resultdata) {
    //        layer.close(index);
    //        if (resultdata.success) {
    //            layer.msg(resultdata.message, {
    //                icon : 1
    //            });
    //webside.common.loadPage(nav + '?id=' + rows[0].id + "&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
    //            grid.tools.getWindowStart(custId,'esurfing订单信息');
    //            //webside.common.loadPage(url + '?custId=' + rows[0].custId);
    //            //webside.common.loadPage("/esurfing/frozenUI.html");
    //        } else {
    //            //layer.msg(resultdata.message, {
    //            //    icon : 5
    //            //});
    //        }
    //    },
    //    error : function(data, errorMsg) {
    //        layer.close(index);
    //        layer.msg(data.responseText, {icon : 2});
    //    }
    //});

})