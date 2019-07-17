var dtGridColumns = [
    {
        id : 'creditId',
        title : '额度ID',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
    },{
        id : 'creditAmount',
        title : '总额度',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    }, {
        id : 'creditAmountFrozen',
        title : '冻结额度',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    }, {
        id : 'creditAmountUsed',
        title : '已用额度',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
    }, {
        id : 'creditExpire',
        title : '信用过期时间',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
    }, {
        id : 'creditStatus',
        title : '信用状态',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs',
        resolution : function(value, record, column, grid, dataNo, columnNo) {
            if(0 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">无效</span>';
            }
            if(1 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">有效</span>';
            }
            if(2 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">拒绝</span>';
            }
            if(3 == value) {
                return '<span class="label label-sm label-success arrowed arrowed-righ">黑名单</span>';
            }

        }
    }, {
        id : 'orderId',
        title : '订单orderId',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        hideType : 'xs'
    }
];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/esurfing/frozen.html?custId=' + $('#custId').val() + '&vbsId=' + $('#vbsId').val(),
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : 'esurfing订单冻结额度信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    //var custId = $('#custId').val();
    //$("#searchKey").val(custId);

    $("#btnSearch").click(customSearch);
    grid.load();

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
    $('#vbsId').val($("#searchKey").val());
    grid.refresh(true);
}


$('#btnUnfrozen').click(function(url) {
    var vbsURL = '/esurfing/queryVbsId.html';
    var isClearURL = '/esurfing/isClearLoan.html';
    var unfrozenURL = '/esurfing/unfrozen.html';

    var rows = grid.getCheckedRecords();


    var data='creditId=' + rows[0].creditId + '&orderId=' + rows[0].orderId + '&custId=' + $('#custId').val();
    var index;

    var vbsId = $('#vbsId').val();
    if(null == vbsId || "" == vbsId || undefined == vbsId) {
        $.ajax({
            type: "GET",
            url: sys.rootPath + vbsURL,
            data: data,
            async:false,
            dataType: "json",
            success: function (resultdata) {
                vbsId = resultdata;
            },
            error: function (data, errorMsg) {
            }
            });
    }
    console.log('vbsId:' + vbsId);
    var clearData="vbsId=" + vbsId;
    var isClearLoan = false;
    var noticeMSG = '！！订单:' + vbsId +'未结清！！';
    var b = ['依然解冻？','取消'];


        $.ajax({
            type: "GET",
            url: sys.rootPath + isClearURL,
            data: clearData,
            async:false,
            dataType: "json",
            beforeSend: function () {
                index = layer.load();
            },
            success: function (resultdata) {
                if(resultdata == null) {
                    isClearLoan = false;
                } else {
                    isClearLoan = resultdata;
                }
                layer.close(index);
            },
            error: function (data, errorMsg) {
                layer.close(index);
                layer.msg(data.responseText, {
                    //icon: 2
                });
            }
        });
    //console.log('isClearLoan: '+isClearLoan);
    if('true' == isClearLoan){
        noticeMSG = 'vbs订单:'+ $('#vbsId').val() + '已结清';
        b = ['继续解冻','取消'];
        //弹出model框
        layer.confirm(noticeMSG ,{
            icon : 3,
            title : '是否解冻订单',
            btn: b //按钮
        }, function(index, layero) {
            //window.location.href = sys.rootPath + data.url;

            $.ajax({
                type : "GET",
                url : sys.rootPath + unfrozenURL,
                data : data,
                dataType : "json",
                beforeSend : function() {
                    index = layer.load();
                },
                success : function(resultdata) {
                    grid.refresh(true);
                    $('.layui-layer-btn1').click();
                    layer.close(index);

                    //if (resultdata.success) {
                    //    layer.msg(resultdata.message, {
                    //        icon : 1
                    //    });
                    //
                    //    webside.common.loadPage(frozenURL);
                    //} else {
                    //    layer.msg(resultdata.message, {
                    //        icon : 5
                    //    });
                    //}
                },
                error : function(data, errorMsg) {
                    layer.close(index);
                    //layer.msg(data.responseText, {
                    //    icon : 2
                    //});
                    alert("接口错误");
                }
            });
        });
    } else {
        b = ['确定','取消'];
        layer.confirm(noticeMSG ,{
            icon : 3,
            title : '解冻订单未结清！',
            btn: b //按钮
        }, function(index, layero){
            grid.refresh(true);
            $('.layui-layer-btn1').click();
            layer.close(index);
        });
    }


})