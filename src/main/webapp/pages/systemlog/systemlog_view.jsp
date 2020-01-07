<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<div class="panel-header panel-header-noborder"
		style="width: 100% px; height: 40px">
		<div
			style="display: inline-block; font-size: 20px; height: 40px; padding: 2px 2px; margin-left: 5px;">
			<span id="paneltile" style="font-size: 16px; margin-left: 6px"></span>
		</div>
		<div class="pull-right" style="padding-right: 20px; margin-top: 3px;">
			<a class="mbtn mbtn-white icon_next"
				style="width: 80px; height: 28px; background-image: url('../../images/icons/icon_prev.png');"
				onclick="domoveUp()">上一条</a> <a class="mbtn mbtn-white icon_next"
				style="width: 80px; height: 28px; background-image: url('../../images/icons/icon_next.png');"
				onclick="domovedown()">下一条</a> <a class="mbtn btn-blue"
				style="width: 70px; height: 28px; background-image: url('../../images/icons/delete-white.png');"
				onclick="closeview()">关闭</a>
			

		</div>
	</div>
	<form id="entityviewForm" class="customForm" method="post">
		<table class="table-width">
			<tr>
				<td class="t" style="width: 80px;">用户：</td>
				<td class="tsblue" style="width: 120px;"><span
					name="userAccount_view" id="userAccount_view"></span></td>
				<td class="t" style="width: 80px;">操作时间：</td>
				<td class="tsblue" style="width: 120px;"><span name="operTimeStr_view"
					id="operTimeStr_view" style="width: 120px;"></span></td>

				<td class="t" style="width: 80px;">IP：</td>
				<td class="tsblue" style="width: 120px;"><span name="userIP_view"
					id="userIP_view" style="width: 120px;"></span></td>

			</tr>
			<tr>
				<td class="t">操作内容</td>
				<td class="tsblue" ><span name="description_view"
					id="description_view" ></span></td>
				<td class="t">耗时(ms)：</td>
				<td class="tsblue"><span name="exeTime_view" id="exeTime_view"></span></td>

				<td class="t">结果：</td>
				<td class="tsblue"><span name="sucessStr_view" id="sucessStr_view"></span></td>
			</tr>
			<tr>
				<td class="t">请求参数</td>
				<td class="tsblue" colspan="5"><span name="arguments_view"
					id="arguments_view" style="width: 500px;"></span></td>

			</tr>
			<tr>
				<td class="t">返回结果</td>
				<td class="tsblue" colspan="5"><span name="returnObject_view"
					id="returnObject_view" style="width: 500px;"></span></td>

			</tr>
		</table>
	</form>

</div>
