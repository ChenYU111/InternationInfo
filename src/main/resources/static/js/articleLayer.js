 function seeArticle(id){
	 			alert(id);
		        /*layer.open({
		            type: 0,
		            title: '查看文章详情',
		            shadeClose: true,
		            skin: 'demo-class',
		            closeBtn: 1,
		            area: ['700px', '600px'],
		            content: '/seeOneArticle/'+id,
		            end: function (index) {
		            }
		        }); 
		        */
		        
		        $.post('/seeOneArticle/'+id, {"id":id}, function(str){
		        	  layer.open({
		        	    type: 1,
		        	    content: str //注意，如果str是object，那么需要字符拼接。
		        	  });
		        	});
		        
			}
 
 
 function deleteArticle(id){
	 layer.open({
		  content: '确认删除？',
		  type: 2,
		  btn: ['确认', '取消'],
		  closeBtn: 1,
		  yes: function(index){
			    layer.close(index);
				 $.ajax({
				        url:"/deleteArticleById/"+id,
				        type:"post",
				        async:false,
				        success:function(data){
				        	location.href="/articleManager";
				        }
				 })
		  }
	 	  
	 });
	}