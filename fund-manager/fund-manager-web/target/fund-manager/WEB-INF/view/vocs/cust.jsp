<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/vocs/cust.js"></script>
<div class="page-header">
	<div class="input-group">
		<input id="idCardNoS" type="text" class="input form-control" placeholder="身份证号...">
		<input id="mobileS" type="text" class="input form-control" placeholder="手机号...">
		<select id="productId" name="">
			<option value="goboo">rx</option>
			<option value="esurfing">xr</option>
			<option value="hebao">t'm</option>
		</select>
		<%--<input id="productId" type="text" class="input form-control" placeholder="产品id...">--%>
		<br/>
		<span class="input-group-btn">
			<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
				<i class="fa fa-search"></i> 搜索
			</button>
		</span>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">融祥汽车订单信息列表</h4>
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
					<div id="dtGridToolBarContainer" class="dlshouwen-grißd-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>


