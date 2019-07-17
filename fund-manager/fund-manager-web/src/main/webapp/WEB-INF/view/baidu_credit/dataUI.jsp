<%--
  Created by IntelliJ IDEA.
  User: dongwen
  Date: 2017/7/26
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datetimepicker.css"/>
<link rel ="stylesheet" href ="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.css"/>
<script type = "text/javascript" src="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/desensitization/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/baidu_credit/dataUI.js"></script>
<div class="page-header">
    <form class="form-inline" role="form">
        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>业务号:</label>
                    <input id="vbsId" type="text" class="input form-control"
                           placeholder="业务号..">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>用户ID:</label>
                    <input id="custId" type="text" class="input form-control"
                           placeholder="用户ID..">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>&nbsp;&nbsp;身份证号:</label>
                    <input id="custIdNo" type="text" class="input form-control"
                           placeholder="身份证..">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>手机号:</label>
                    <input id="custMobile" type="text" class="input form-control"
                           placeholder="手机号..">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>&nbsp;&nbsp;借据号:</label>
                    <input id="bdLoanId" type="text" class="input form-control"
                           placeholder="借据号">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>还款状态:</label>
                    <select id="loanStatus" class ="input form-control" >
                        <option value="" selected = "selected">请选择</option>
                        <option value="4">还款中</option>
                        <option value = "5">已结清</option>
                    </select>
                </div>
            </td>
        </tr>

        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>财务状态:</label>
                    <select id="financialStatus" class ="input form-control" >
                        <option value="" selected = "selected">请选择</option>
                        <option value="1">正常</option>
                        <option value ="0">异常</option>
                    </select>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>提现申请日期:</label>
                    <input id="applyBegin" type="text" readonly="true"
                           class="input form-control"
                           placeholder="开始时间">
                    &nbsp;
                    <input id="applyEnd" type="text" readonly="true"
                           class="input form-control"
                           placeholder="结束时间">
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 10px">
                    <label>&nbsp;&nbsp;放款成功日期</label>
                    <input id="loanBegin" type="text" readonly="true"
                           class="input form-control"
                           placeholder="开始时间">
                    &nbsp;
                    <input id="loanEnd" type="text" readonly="true"
                           class="input form-control"
                           placeholder="结束时间">
                </div>
            </td>
        </tr>
        <tr>
            <td style="text-align:left">
                <%--<div class="form-group" style="margin-bottom: 10px">--%>
                    <span class="input-group-btn">
                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                </span>
                <%--</div>--%>
            </td>
        </tr>
    </form>
</div>
<div class="row">
    <div class="col-xs-12 widget-container-col ui-sortable"
         style="min-height: 127px;">
        <!-- #section:custom/widget-box.options.transparent -->
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">订单查询</h4>
                <div class="widget-toolbar no-border">
                    <a href="#" data-action="fullscreen" class="orange2">
                        <i class="ace-icon fa fa-arrows-alt"></i>
                    </a>
                    <a href="#" data-action="collapse" class="green">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <div class="widget-body" style="display: block;">
                <div class="widget-main padding-6 no-padding-left no-padding-right">
                    <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                    <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                </div>
            </div>
        </div>
    </div>
</div>