function seeArticle(id) {
	$.post('/seeOneArticle/' + id, {
		"id" : id
	}, function(str) {
		layer.open({
			type :3,
			title:"文章详情",
			shadeClose: true,//点击外围   关闭
	        shade: [0.8, '#393D49'],//遮蔽层
	        closeBtn: 2,
	        maxmin: true,
	        area: ['700px', '600px'],
			content : str
		// 注意，如果str是object，那么需要字符拼接。
		});
	});
}

function deleteArticle(id) {
	layer.open({
		content : '确认删除？',
		type : 2,
		btn : [ '确认', '取消' ],
		closeBtn : 1,
		yes : function(index) {
			layer.close(index);
			$.ajax({
				url : "/deleteArticleById/" + id,
				type : "post",
				async : false,
				success : function(data) {
					 layer.tips('删除成功！', that);
					location.href = "/articleManager";
				}
			})
		}
	});
}


