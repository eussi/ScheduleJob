function validateAdd() {
	if ($.trim($('#jobName').val()) == '') {
		alert('name不能为空！');
		$('#jobName').focus();
		return false;
	}
	if ($.trim($('#jobGroup').val()) == '') {
		alert('group不能为空！');
		$('#jobGroup').focus();
		return false;
	}
	if ($.trim($('#cronExpression').val()) == '') {
		alert('cron表达式不能为空！');
		$('#cronExpression').focus();
		return false;
	}
	if ($.trim($('#beanClass').val()) == ''
			&& $.trim($('#springId').val()) == '') {
		$('#beanClass').focus();
		alert('类路径和spring id至少填写一个');
		return false;
	}
	if ($.trim($('#methodName').val()) == ''
            &&  $.trim($('#beanClass').val()) != ''){
		$('#methodName').focus();
		alert('方法名不能为空！');
		return false;
	}
	if (!cronValidate($('#cronExpression').val())) {
		alert('cron表达式格式错误！');
		$('#cronExpression').focus();
		return false;
	}
	return true;
}
function add(url) {
	if (validateAdd()) {
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			cache : false,
			url : url,
			data : $("#addForm").serialize(),
			success : function(data) {
				if (data.flag) {
					location.reload();
				} else {
					alert(data.msg);
				}

			}// end-callback
		});// end-ajax
	}
}
function changeJobStatus(jobId, url, cmd) {
	$.ajax({
		type : "POST",
		async : false,
		dataType : "JSON",
		cache : false,
		url : url,
		data : {
			jobId : jobId,
			cmd : cmd
		},
		success : function(data) {
			if (data.flag) {
				location.reload();
			} else {
				alert(data.msg);
			}
		}// end-callback
	});// end-ajax
}
function updateCron(jobId, url) {
	var cron = prompt("输入cron表达式！", "")
	if (!cronValidate(cron)) {
		alert('cron表达式格式错误！');
		return;
	}
	$.ajax({
		type : "POST",
		async : false,
		dataType : "JSON",
		cache : false,
		url : url,
		data : {
			jobId : jobId,
			cron : cron
		},
		success : function(data) {
			if (data.flag) {
				location.reload();
			} else {
				alert(data.msg);
			}

		}// end-callback
	});// end-ajax

}