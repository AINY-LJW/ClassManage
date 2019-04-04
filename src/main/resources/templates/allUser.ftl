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
						<h1 style="margin-left: 45%">所有用户</h1>
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
							<span>姓名</span> <input id="asin"
								style="line-height: 26px; border: 1px solid #ccc"> <span>学号</span>
							<input id="reviewerName"
								style="line-height: 26px; border: 1px solid #ccc"> <span>专业</span>
							<input id="keyWord"
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
								style="width: 100%; height: auto" url="/getAllUsers"
								toolbar="#tb" title="建议列表" iconCls="icon-save" rownumbers="true"
								pagination="true">
								<!--  工具栏-->

								<thead>
									<tr>
										<th field="id" width="20%">用户ID</th>
										<th field="name" width="10%">姓名</th>
										<th field="stuNum" width="20%">学号</th>
										<th field="major" width="20%">专业</th>
										<th field="password" width="19%">密码</th>
										<th field="opt" width="10%"
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
					<form id="ff" method="post" action="addUser">
						<table cellpadding="5">
							<tr>
								<td>姓名:</td>
								<td><input class="easyui-textbox" type="text" name="name"
									data-options="required:true"></input></td>
							</tr>
							<tr>
								<td>学号</td>
								<td><input class="easyui-textbox" type="text" name="stuNum"
									data-options="required:true"></input></td>
							</tr>
							<tr>
								<td>专业</td>
								<td><input class="easyui-textbox" type="text" name="major"
									data-options="required:true"></input></td>
							</tr>
							<tr>
								<td>密码</td>
								<td><input class="easyui-textbox" type="text" name="pwd"
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
			stuNum : $('#reviewerName').val(),
			major : $('#keyWord').val(),

		});
	}
	//操作
	function operate_formatter(value, row, index) {
		return "<button onClick='deleteRow(" + row.id
				+ ")' class='easyui-linkbutton'>删除</button>";
	}
	//删除
	function deleteRow(value) {

		$.messager.confirm("操作提示", "您确定要删除该用户记录吗？", function(data) {

			if (data) {

				$.ajax({
					type : "POST",
					url : "deleteUserById",
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