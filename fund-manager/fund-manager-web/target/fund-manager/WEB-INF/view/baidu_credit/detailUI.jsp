<%--
  Created by IntelliJ IDEA.
  User: dongwen
  Date: 2017/7/31
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/desensitization/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/baidu_credit/detailUI.js"></script>
<div class="page-header">
    <form class="form-inline" role="form">
        <div class="form-group" style="margin-bottom: 23px">
            <label>业务号:</label>
            <input id="vbsIdSearch1" type="text" class="input form-control"
                   placeholder="业务号">
        </div>
        <div class="form-group" style="margin-bottom: 23px">
            <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借据号:</label>
            <input id="bdLoanId" type="text" class="input form-control"
                   placeholder="借据号">
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="form-group" style="margin-bottom: 23px">
            <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                <i class="fa fa-search"></i> 搜索
            </button>
        </div>
        <input id="vbsId" type="hidden" value="${vbsId }">
    </form>
</div>
<div class="row dataTable">
    <table width="600" height="400" border="2" cellpadding="0" cellspacing="0" class="tb">
        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>还款状态 : </label>
                    </br>
                    <input id="loanForm" type="hidden" value="${indentInfo.loan_form }">
                    <div id="l_status" class="dlshouwen-grid-cell  text-center" value="">

                    </div>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>用户ID : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.cust_id }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>姓名 : </label>
                    </br>
                    <p id="cName" class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.cust_name }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>手机号 : </label>
                    </br>
                    <p id="cMobile" class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.cust_mobile }
                    </p>
                </div>
            </td>
        </tr>
        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>身份证号 : </label>
                    </br>
                    <p id="cIdCard" class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.id_no }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>放款银行 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.to_bank_name }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>银行卡号 : </label>
                    </br>
                    <p id="cBankCard" class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.bank_card_num }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>放款日期 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.start_date }
                    </p>
                </div>
            </td>
        </tr>
        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>固定还款日 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.repay_day }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>借款期数 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.loan_period }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>借款金额 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.amount }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>当前应还 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.owe_now }
                    </p>
                </div>
            </td>
        </tr>
        <tr>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>费率 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.Day_Rate }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>手续费 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.Formalities_Rate } * 本金
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>业务号 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.vbs_bid }
                    </p>
                </div>
            </td>
            <td style="text-align:left">
                <div class="form-group" style="margin-bottom: 23px">
                    <label>用户评级 : </label>
                    </br>
                    <p class="dlshouwen-grid-cell  text-center" style="">
                        ${indentInfo.cust_level }
                    </p>
                </div>
            </td>
        </tr>
    </table>
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

<div class="modal fade" id="planModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="width: auto">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    明细
                </h4>
            </div>
            <div class="modal-body">
                <div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <div id="planContainer" class="dlshouwen-grid-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="repayModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="width: auto">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    明细
                </h4>
            </div>
            <div class="modal-body">
                <div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <div id="repayContainer" class="dlshouwen-grid-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" style="display:inline-block; width: auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    明细
                </h4>
            </div>
            <div class="modal-body">
                <div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <div id="itemContainer" class="dlshouwen-grid-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>