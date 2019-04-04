<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>所有有用户评论</title>


<!--  easyUI-->
<link rel="stylesheet" href="jquery-easyui-1.4.1/themes/gray/easyui.css">
<link rel="stylesheet" href="jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript"
	src="jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="@{/jquery-easyui-1.4.1/easyloader.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.1/datagrid-export.js"></script>


</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1 style="margin-left: 45%">所有教室</h1>
						<div class="description"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top" style="height: 70%">

							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div id="tb" style="padding: 3px">
							<span>教室名称</span> <input id="asin"
								style="line-height: 26px; border: 1px solid #ccc"> <span>预约状态:</span>
							<select id="reviewerName" class="easyui-combobox" name="dept"
								style="width: 200px;">
								<option value="all">所有</option>
								<option value="1">已预约</option>
								<option value="0">未预约</option>

							</select> <span>教学楼:</span> <input id="keyWord"
								style="line-height: 26px; border: 1px solid #ccc"> <a
								class="easyui-linkbutton" plain="true" onclick="doSearch()"
								id="serach" data-options="iconCls:'icon-search'">查找</a> <a
								class="easyui-linkbutton" plain="true"
								onclick="$('#w').window('open')" id="serach"
								data-options="iconCls:'icon-add'">添加</a> <a
								class="easyui-linkbutton" plain="true" onclick="exportExcel()"
								id="serach" data-options="iconCls:'icon-sum'">导出excel</a>
						</div>
						<div class="form-bottom" style="height: 70%">
							<table id="tt" class="easyui-datagrid"
								style="width: 100%; height: auto" url="/getAllClassRoom"
								toolbar="#tb" title="建议列表" iconCls="icon-save" rownumbers="true"
								pagination="true">
								<!--  工具栏-->

								<thead>
									<tr>
										<th field="id" width="20%">教室ID</th>
										<th field="classRoom" width="10%">教室名</th>
										<th field="state" width="20%" formatter="formatShow">状态</th>
										<th field="place" width="20%">地点</th>
										<th field="opt" width="20%"
											align:"center" formatter=operate_formatter>操作</th>
										<!-- <th field="reviewtext" width="47%">评论</th> -->
									</tr>
								</thead>
							</table>
							<div class="table-responsive"></div>
						</div>
					</div>
				</div>
				<!-- 添加操作 -->
				<div id="w" class="easyui-window" title="Modal Window"
					data-options="modal:true,closed:true,iconCls:'icon-save'"
					style="width: 300px; height: 300px; padding: 10px;">
					<form id="ff" method="post" action="addClassRoom">
						<table cellpadding="5">
							<tr>
								<td>教室名称:</td>
								<td><input class="easyui-textbox" type="text" name="name"
									data-options="required:true"></input></td>
							</tr>
							<tr>
								<td>地点</td>
								<td><input class="easyui-textbox" type="text" name="place"
									data-options="required:true"></input></td>
							</tr>


						</table>
					</form>
					<div style="text-align: center; padding: 5px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitForm()">提交</a> <a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="clearForm()">重置</a>
					</div>
				</div>
				<!-- 添加操作 end-->
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script th:src="@{/assets/js/jquery-1.11.1.min.js}"></script>
	<script th:src="@{/assets/bootstrap/js/bootstrap.min.js}"></script>
	<script th:src="@{/assets/js/jquery.backstretch.min.js}"></script>
	<script th:src="@{/assets/js/scripts.js}"></script>
	<script th:src="@{public/media/js/jquery-1.8.3.min.js}"></script>

	<!--[if lt IE 10]>
            <script src="static/assets/js/placeholder.js"></script>
        <![endif]-->
</body>

<script>
	//用户搜索时触发
	function doSearch() {
		$('#tt').datagrid('load', {
			//当所有代码执行完之后，当前表格会自动提交一份带参数的去后台
			name : $('#asin').val(),
			state : $('#reviewerName').combobox('getValue'),
			place : $('#keyWord').val(),

		});
	}
	//格式化预约
	function formatShow(value, row, index) {
		var res = '';
		switch (value) {
		case 0:
			res = '<font style="color:green;">未使用</font>';
			break;
		case 1:
			res = '<font style="color:red;">已预约</font>';
			break;
		default:
			break;
		}
		return res;
	}
	//操作
	function operate_formatter(value, row, index) {
		return "<button onClick='deleteRow(" + row.id
				+ ")' class='easyui-linkbutton'>删除</button>";
	}
	//删除
	function deleteRow(value) {
		$.messager.confirm("操作提示", "您确定要删除该值班记录吗？", function(data) {

			if (data) {

				$.ajax({
					type : "POST",
					url : "deleteClassRoomById",
					data : {
						id : value
					},
					//返回数据的格式
					datatype : "text",//"xml", "html", "script", "json", "jsonp", "text".
					//在请求之前调用的函数
					beforeSend : function() {
						//$("#msg").html("logining");
					},
					success : function(data) {

						$("#tt").datagrid("reload");
						alert("删除成功");
					},
					//调用执行后调用的函数
					complete : function(XMLHttpRequest, textStatus) {

					},
					//调用出错执行的函数
					error : function() {
						alert("删除失败");
					}
				});
			}

		});
	}
	//添加
	function submitForm() {
		$('#ff').form('submit');
		alert("添加成功");
		$('#ff').form('clear');
		$("#tt").datagrid("reload");
	}
	//重置
	function clearForm() {
		$('#ff').form('clear');
	}
	//导出excel
	function exportExcel() {
		$('#tt').datagrid('toExcel', 'dg.xls'); // export to excel
	}
</script>

</html>