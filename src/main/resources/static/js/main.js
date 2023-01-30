function resendHandle(){
	$.ajax({
		type: "get",
		url: "resend-verify-mail",
	});
	$.Notification.autoHideNotify(
		"success",
		"top right",
		"Success",
		"Please check your email!"
	);
}