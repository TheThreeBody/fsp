<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel ="stylesheet" href ="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.css"/>
<script type = "text/javascript" src="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datetimepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/desensitization/utils.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/customer/baidu_credit/creditList.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/datebox/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/datebox/css/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/datebox/css/demo.css">
<script id="box" type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datebox/js/jquerybox.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datebox/js/jquery.easyui.min.js"></script> --%>

<html>
<body  >

<div >
	<div >
		<!-- <input id="searchKey" type="text" class="input form-control"
			placeholder="账户名称..."> <span class="input-group-btn">
			<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
				<i class="fa fa-search"></i> 搜索
			</button>
		</span> -->
		<table  style="width:100%;border-collapse:separate; border-spacing:0px 10px;" cellspacing="10">
		<tr >
		<td  >用户ID</td>
		<td  ><input id="custId" type="text"/></td>
		
		<td >姓名</td>
		<td  > <input id="name" type="text"/></td>
		
		<td >身份证号</td>
		<td ><input id="identityNo" type="text"/></td>
		
		<td >手机号</td>
		<td ><input id="mobile" type="text"/></td>
		
		<td> 额度状态</td>
		<td >
		 <select id="amountStatus" style="width: 100px">
		  <option value="-1" selected="selected">请选择</option>
		  <option value="1">未激活</option>
		  <option value="2">已激活</option>
		  <option value="3">失效</option>
		  </select>
		
		</td>
		
		
		</tr>
		
		<tr>
		<!-- <td style="text-align:left"> 授信结果</td>
		<td ><input type="text" id="creditStatus"/></td> -->
		
		<td colspan="2">
		  授信结果
		  <select id="creditStatus" style="width: 150px">
		  <option value="-1" selected="selected">请选择</option>
		  <option value="1">通过</option>
		  <option value="0">拒绝</option>
		  </select>
		
		</td>
		<td colspan="4">
		<div>
		授信开始时间
                    <input   id="applyBegin" type="text" class="easyui-datebox" style="width:180px"
                >
                   	<input   id="applyEnd" type="text" class="easyui-datebox" style="width:180px"
                  >
        <!--       授信结果<input type="text" id="creditStatus"/> -->
                   </div>
		
		</td>
		
		<td colspan="4">
		<div>
		授信成功时间
		 <input   id="creditBegin" type="text" class="easyui-datebox" style="width:130px"
                   >
                   	<input   id="creditEnd" type="text" class="easyui-datebox" style="width:130px"
                  >
		<button id="btnSearch" type="button">
				查询
			</button>
		</div>
		
		</td>
		</tr>
		</table>
		
		
		<!-- <div >
			<input class="easyui-datebox"  labelPosition="top" >
		</div>
		<div >
			<input class="easyui-datebox"  labelPosition="top" >
		</div> -->
		
		
                 
	</div>
</div>
<div class="row">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">授信信息列表</h4>
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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="false">
					&times;
				</button>
				<!-- <h4 class="modal-title" id="myModalLabel">
					已解决
				</h4> -->
			</div>
			<div class="modal-body">
			<table style="width: 100%">
			<tr>
			<td>
		<span>客户评级:</span><span id="custLevel" ></span>
			</td>
			<td>
			<span>费率:</span><span id="dayRate" ></span>
			</td>
			</tr>
			
			<tr>
			<td>
			<span>征信模型分:</span><span id="creditScore" ></span>
			</td>
			
			<td>
			<span>拒绝阶段:</span><span id="processNode" >
			</td>
			
			</tr>
			
			
				<tr>
			<td>
			<span>对外拒绝原因:</span><span id="reasonMsg" ></span>
			</td>
			
			<td>
			<span>拒绝原因:</span><span id="creditMsg" ></span>
			</td>
			
			</tr>
			
			</table >
				<!-- <span>客户评级:</span><span id="custLevel" ></span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>费率:</span><span id="dayRate" ></span></br>
				<span>征信模型分:</span><span id="creditScore" ></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span>拒绝阶段:</span><span id="processNode" ></span></br>
				<span>对外拒绝原因:</span><span id="reasonMsg" ></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			<!-- 	<button type="button" class="btn btn-primary" id ="saveData" hidden="true">
					保存
				</button> -->
			</div>
		</div>
	</div>
</div>
</body>
</html>
