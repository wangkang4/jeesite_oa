	function save() {
		var bool = true;
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		var date = $(".Wdate").val();
		$(".Wdate").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			}
			$(this).blur(function() {
				if (this.value != "") {
					$(this).next().text("");
				}
			})
		});
		$(".money").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			} else if (!reg.test(this.value)) {
				bool = false;
				$(this).next().text("*格式错误");
			}
			$(this).blur(function() {
				if (this.value == "") {
					$(this).next().text("");
					$(this).next().text("*必填信息");
				} else if (this.value != "" && !reg.test(this.value)) {
					$(this).next().text("");
					$(this).next().text("*格式错误");
				} else if (this.value != "" && reg.test(this.value)) {
					$(this).next().text("");
				}
			})
		});
		$(".aMoney").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			} else if (!reg.test(this.value)) {
				bool = false;
				$(this).next().text("*格式错误");
			}
			$(this).blur(function() {
				if (this.value == "") {
					$(this).next().text("");
					$(this).next().text("*必填信息");
				} else if (this.value != "" && !reg.test(this.value)) {
					$(this).next().text("");
					$(this).next().text("*格式错误");
				} else if (this.value != "" && reg.test(this.value)) {
					$(this).next().text("");
				}
			})
		});
		$(".info").each(function() {
			$(this).next().text("");
			if (this.value.length > 20) {
				bool = false;
				$(this).next().text("*字体过长");
			}
			$(this).blur(function() {
				if (this.value.length <= 20) {
					$(this).next().text("");
				}
			})
		});
		if (bool) {
			$('#flag').val('yes');
			return true;
		}
		return false;
	}

	function stop() {
		var bool = true;
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		var date = $(".Wdate").val();
		$(".Wdate").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			}
		});
		$(".money").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			} else if (this.value != "" && !reg.test(this.value)) {
				bool = false;
				$(this).next().text("*格式错误");
			}
		});
		$(".aMoney").each(function() {
			$(this).next().text("");
			if (this.value == "") {
				bool = false;
				$(this).next().text("*必填信息");
			} else if (!reg.test(this.value)) {
				bool = false;
				$(this).next().text("*格式错误");
			}
		});
		$(".info").each(function() {
			$(this).next().text("");
			if (this.value.length > 20) {
				bool = false;
				$(this).next().text("*字体过长");
			}
		});
		if (bool) {
			$('#flag').val('no');
			return true;
		}
		return false;
	}
