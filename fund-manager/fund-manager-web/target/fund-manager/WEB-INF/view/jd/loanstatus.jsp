<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datetimepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootbox.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/jd/loanstatus.js"></script>
<div class="page-header">
    <form class="form-inline" role="form">
    	<table>
    		<tr>
	    		<td style="text-align:left">
		    		<div class="form-group" style="margin-bottom: 19px">
			        	<label>用户的ID:</label>
			            <input id="custId" type="text" class="input form-control"
			                   placeholder="用户的ID">
	        		</div>
	    		</td>
	    		<td style="text-align:left">
	    			<div class="form-group" style="margin-bottom: 19px">
			        	<label> &nbsp; &nbsp;姓名:</label>
			            <input id="name" type="text" class="input form-control"
			                   placeholder="姓名">
        			</div>
	    		</td>
	    		<td style="text-align:left">
	    			<div class="form-group" style="margin-bottom: 19px">
        	  			<label> &nbsp; &nbsp;身份证:</label>
            			<input id="identityNo" type="text" class="input form-control"
                   		placeholder="身份证">
        			</div>
	    		</td>
	    		<td style="text-align:left">
	    			<div class="form-group" style="margin-bottom: 19px">
        				<label> &nbsp; &nbsp;手机号:</label>
            			<input id="custMobile" type="text" class="input form-control"
                   		placeholder="手机号">
        			</div>
	    		</td>
    		</tr>
    		<tr>
    			<td style="text-align:left">
    				<div class="form-group" style="margin-bottom: 19px">
	        	 		<label>&nbsp; &nbsp;借据号:</label>
	            		<input id="loanNo" type="text" class="input form-control"
	                   	placeholder="借据号">
        			</div>
    			</td>
    			
    			<td colspan = "2" style="text-align:left">
    				<div class="form-group" style="margin-bottom: 19px">
			        	<label>提现申请时间:</label>
			            <input id="applyBegin" type="text" class="input form-control"
			                   placeholder="开始时间">
			            <input id="applyEnd" type="text" class="input form-control"
			                   placeholder="结束时间">
			        </div>
    			</td>
    			
    			<td style="text-align:left">
    				<div class="form-group" style="margin-bottom: 19px">
        				<label> &nbsp; &nbsp;放款状态:</label>
            			<select class ="input form-control" id="status">
            				<option value="" selected = "selected">请选择</option>
			            	<option value="4">放款成功</option>
			            	<option value="7">已解约</option>
			            	<option value ="6">已结清</option>
			            	<option value ="6">放款失败</option>
			            	<option value = "3">待放款</option>
			            </select>
        			</div>
    			</td>
    		</tr>
    		<tr>
    			<td colspan = "2" style="text-align:left">
    				<div class="form-group" style="margin-bottom: 19px">
			        	 <label>放款成功的时间</label>
			            <input id="loanBegin" type="text" class="input form-control"
			                   placeholder="开始时间">
			            <input id="loanEnd" type="text" class="input form-control"
			                   placeholder="结束时间">
			        </div>
    			</td>
    			<td style="text-align:left">
    				<div class="form-group" style="margin-bottom: 19px">
	        	 		<label>&nbsp; &nbsp;业务号:</label>
	            		<input id="vbsId" type="text" class="input form-control"
	                   	placeholder="业务号">
        			</div>
    			</td>
    			<td style="text-align:left">
    				 <div class="form-group" style="margin-bottom: 19px">
			            <span class="input-group-btn">
							<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
				                <i class="fa fa-search"></i> 搜索
				            </button>
						</span>
			        </div>
    			</td>
    		</tr>
    	</table>
    </form>
</div>
<div class="row">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">放款状态查询</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> <i
						class="ace-icon fa fa-arrows-alt"></i>
					</a> <a href="#" data-action="collapse" class="green"> <i
						class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer"
						class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>


