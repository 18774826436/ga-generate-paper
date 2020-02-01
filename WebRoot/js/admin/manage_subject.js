function getAllSubject(){
	
	$.ajax({
        type: "GET",
        url: contextPath + "/admin_manage?method=getAllSubjects&date=" + new Date(),
        success: function (result) {
            if (result) {
                var NoticeTableData = result;
                $('#table').bootstrapTable("load", NoticeTableData);
            }
        },
        dataType: "json"
    });
}

$(function () {
    $('#table').bootstrapTable({
        method: "get",		//向服务器请求远程数据的方式
        striped: true,		//默认false，当设为true，则每行表格的背景会显示灰白相间
        singleSelect: false,//默认false，设为true则允许复选框仅选择一行
        pageList: [10, 25], //可供选择的每页的行数（*）
        dataType: "json",	//期望从服务器获取的数据的类型，默认为json格式字符串
        pagination: true, 	//默认为false，表格的底部工具栏不会显示分页条（pagination toolbar），可以设为true来显示
        uniqueId: "yes",
        pageSize: 10,		//前提：pagination设为true，启用了分页功能。设置每页初始显示的条数10条，默认10条。
        pageNumber: 1,		//前提：pagination设为true，启用了分页功能。- 默认第1页，用于设置初始的页数
        search: false,	 	//默认false不显示表格右上方搜索框 ，可设为true，在搜索框内只要输入内容即开始搜索
        contentType: "application/x-www-form-urlencoded",//请求数据的contentType（内容类型），用于定义文件的类型，决定接收方以什么形式读取这个文件。
        queryParams: null,	//当请求数据时，你可以通过修改queryParams向服务器发送其余的参数。 
        columns: [
            {
            	title:'学科名称',
            	field:'name',
            	align:'center',
            	valign:'middle'
            },
            {
            	title: '操作',
                field: 'id',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" id="deleteBtn" class="btn btn-danger" onclick="deleteSubject(' + row.id  +')">删除</button> '
                    return e;
                }
            }
        ]
    });
});

function addSubject(){
	var index = layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  closeBtn: 0, //不显示关闭按钮
		  anim: 2,
		  shadeClose: false, //开启遮罩关闭
		  content:   '<div style="margin-left:10px;margin-right:10px;margin-top:10px">学科名称：<input type="text" id="subjectName" ><div>'
			  		+'<div style="margin-top:10px;margin-bottom:10px"><button style="margin-left:70px;" onclick="add();">保存</button><button style="margin-left:10px;" onclick="cancel()">取消</button><div>'
		});
}

function add(){
	var subjectName = $("#subjectName").val();
	$.ajax({
		type:"get",
		url: contextPath + "/admin_manage?method=addSubject",
		data:{subjectName:subjectName},
		success:function(result){
			if(result=="true"){
				alert("添加成功！");
				getAllSubject();
			}
		},
		dataType:"text",
	});
	layer.closeAll();
}

function cancel(){
	layer.closeAll();
}

function deleteSubject(id){
	layer.confirm('确定要删除该学科？',{icon:3, title:'提示'}, function(index){
		$.ajax({
        	type:"get",
        	url: contextPath + "/admin_manage?method=deleteSubjectById",
        	data:{id: id},
        	success:function(result){
        		if(result){
        			alert("删除成功")
        			getAllSubject();
        		}
        	},
        	dataType:"text"
        }); 
		layer.close(index);
	});
}
