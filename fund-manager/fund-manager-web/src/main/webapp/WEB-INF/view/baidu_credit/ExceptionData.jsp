<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datetimepicker.css"/>
<link rel ="stylesheet" href ="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.css"/>
<script type = "text/javascript" src="${pageContext.request.contextPath }/resources/js/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/baidu_credit/ExceptionData.js"></script>
<div class="page-header">
    <form class="form-inline" role="form">
        <label >账务日期:</label>
        <div class="input-group">
            <input id="beginDate" type="text" class="input form-control form_datetime"
                   placeholder="开始账务日期" readonly >
        </div>
        <div class="input-group">
            <input id="endDate" type="text" class="input form-control form_datetime"
                   placeholder="结束账务日期"  readonly>
        </div>
        <div class="input-group">
            <span class="input-group-btn">
			<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                <i class="fa fa-search"></i> 搜索
            </button>
		</span>
        </div>
    </form>
</div>
<div class="row">
    <div class="col-xs-12 widget-container-col ui-sortable"
         style="min-height: 127px;">
        <!-- #section:custom/widget-box.options.transparent -->
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">异常数据</h4>
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
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					已解决
				</h4>
			</div>
			<div class="modal-body">
				<textarea rows="10" cols="10" id ="handletext" style="width: 567px; margin: 0px; height: 242px;" ></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id ="saveData">
					保存
				</button>
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
<div class="modal fade" id="handlemsg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					异常解决的详情
				</h4>
			</div>
			<div class="modal-body">
				<div >
					<p>解决人：<span id ="handleperson"></span></p>
					<p>解决时间：<span id ="handletime"></span></p>
					<p>解决方法：<span id ="handlemethod"></span></p>
				</div>
			</div>
		</div>
	</div>
</div>

