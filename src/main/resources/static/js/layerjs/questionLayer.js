function deleteQuestion(id) {
	layer.open({
		content : '确认删除？',
		type : 2,
		btn : [ '确认', '取消' ],
		closeBtn : 1,
		yes : function(index) {
			layer.close(index);
			$.ajax({
				url : "/deleteQuestion/" + id,
				type : "post",
				async : false,
				success : function(data) {
					location.href = "/myQuestion";
				}
			})
		}

	});
}

//采纳连接    点击询问是否采纳   
function isAdoptAnswer(id) {
	layer.open({
		content : '确认采纳该答案？',
		type : 2,
		btn : [ '确认', '取消' ],
		closeBtn : 1,
		yes : function(index) {
			layer.close(index);
			$.ajax({
				url : "/isAdoptAnswer/" + id,
				type : "post",
				async : false,
				success : function(data) {
					location.href = "/seeQuestionDetail";
				}
			})
		}

	});
}