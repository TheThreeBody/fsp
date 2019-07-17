<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel ="stylesheet" href ="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.css"/>
<script type = "text/javascript" src="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datetimepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/desensitization/utils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/baidu_credit/cashList.js"></script>
<div class="page-header">
	<div class="input-group">
		<!-- <input id="searchKey" type="text" class="input form-control"
			placeholder="账户名称..."> <span class="input-group-btn">
			<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
				<i class="fa fa-search"></i> 搜索
			</button>
		</span> -->
			<table  style="width:100%;border-collapse:separate; border-spacing:10px 10px;" cellspacing="20">
			<tr>
			<td  >用户ID</td>
		<td  ><input id="custId" type="text"/></td>
		
		<td >姓名</td>
		<td  > <input id="name" type="text"/></td>
		
		<td >身份证号</td>
		<td ><input id="identityNo" type="text"/></td>
		
		<td >手机号</td>
		<td ><input id="mobile" type="text"/></td>
		
		<td> 提现状态</td>
		<td >
		 <select id="applyResult" style="width: 150px">
		  <option value="-1" selected="selected">请选择</option>
		  <option value="1">成功</option>
		  <option value="0">失败</option>
		  <option value="2">异常</option>
		  </select>
		
		
		</td>
			
			</tr>
			<tr>
				<td colspan="2">
		  流水号<input type="text" id="transactionId"/>
		
		</td>
			<td colspan="4">
		<div>
		提现开始时间
                    <input id="applyBegin" type="text" 
                   placeholder="开始时间">
                   	<input id="applyEnd" type="text" 
                   placeholder="结束时间">
                   </div>
		
		</td>
		
		<td colspan="4">
		<div>
		<!-- 提现成功时间
		 <input id="applyBegin" type="text" 
                   placeholder="开始时间">
                   	<input id="applyEnd" type="text" 
                   placeholder="结束时间"> -->
                   <button id="btnSearch" type="button" style="margin-right:0px">
				 查询
			</button>
		
		</div>
		
		</td>
			
			
			</tr>
			
			
			
			</table>
			
			
			
			
			
			
	</div>
</div>
<div class="row">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">提现信息列表</h4>
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
			<!-- <tr>
			<td>
			
				<span>客户评级:</span><span id="custLevel" ></span>
			</td>
				<td>
			
				<span>费率:</span><span id="dayRate" ></span>
			</td>
			</tr> -->
			<tr>
		<!-- 	<td>
			
			<span>征信模型分:</span><span id="r" ></span>
			</td>
				<td> -->
			
				<span>拒绝阶段:</span><span id="transactionNode" ></span>
			</td>
			<td>
			<span>对外拒绝原因:</span><span id="retCode" ></span>
			</td>
			</tr>
			<tr>
				<td>
			
			<span>拒绝原因:</span><span id="remark" ></span>
			</td>
			</tr>
			
			
			</table>
				<!-- <span>客户评级:</span><span id="custLevel" ></span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>费率:</span><span id="dayRate" ></span></br>
				<span>征信模型分:</span><span id="r" ></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span>拒绝阶段:</span><span id="transactionNode" ></span></br>
				<span>对外拒绝原因:</span><span id="retCode" ></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

<div class="modal fade" id="myModalProblem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="false">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
				解决方法
				</h4>
			</div>
			<div class="modal-body">
			<form action="/baidu/cash/solution.html" method="post" id="tj">
			<input  hidden="true"  id="transactionApplyId"       /><textarea rows="10" cols="70" id="solutionException" style="vertical-align: top"></textarea>
			<div align="center">
			<button id="saveSolution" type="button">
			确定
			</button>
			</div>
			</form>
			
			
			
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



