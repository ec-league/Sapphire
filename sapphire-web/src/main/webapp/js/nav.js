var CreateTicketNav = {
	ticketTypeTag : document.getElementById("problemClass"),
	titleTag : document.getElementById("problemTitle"),
	projectTag : document.getElementById("project"),
	priorityTag : document.getElementById("bugClass"),
	userTag : document.getElementById("transactor"),
	descriptionTag : document.getElementById("TextEName"),
	submitTag : document.getElementById("createTicketSave"),

	loadProject : function() {
		var project = document.getElementById("project");
		$.ajax({
			url : "/rest/manage/project/snapshots/list.ep",
			success : function(data) {
				var list = data.data;
				for (var i = 0; i < list.length; i++) {
					var option = document.createElement("option");
					option.value = list[i].projectId;
					option.innerHTML = list[i].title;
					project.options[i + 1] = option;
				}
			}
		});
	},
	loadUser : function() {
		var userTag = document.getElementById("transactor");
		$.ajax({
			url : "/rest/manage/user/snapshots/list.ep",
			success : function(data) {
				var list = data.data;
				for (var i = 0; i < list.length; i++) {
					var option = document.createElement("option");
					option.value = list[i].userId;
					option.innerHTML = list[i].username;
					userTag.options[i + 1] = option;
				}
			}
		});
	},
	submit : function() {
		var instance = this;
		var dict = {
			"ticketType" : instance.ticketTypeTag.value
		};
		dict["projectId"] = parseInt(instance.projectTag.value);
		dict["title"] = instance.titleTag.value;
		dict["description"] = instance.descriptionTag.value;
		dict["assignUserId"] = parseInt(instance.userTag.value);
		dict["priority"] = instance.priorityTag.value;
		dict["deadline"] = "2015-12-20 00:00:00";
		$
				.ajax({
					url : "/rest/manage/ticket/save.ep",
					method : "POST",
					headers : {
						"Accept" : "application/json",
						"Content-type" : "application/json"
					},
					data : JSON.stringify(dict),
					success : function(data) {
						if (data.returnCode == "200"
								&& data.returnMessage == "SUCCESS") {
							alert("Create Ticket Success!");
						} else {
							alert("Create Ticket Failed! \n"
									+ data.returnMessage);
						}
					}
				});
	},
	load : function() {
		this.loadProject();
		this.loadUser();

		var instance = this;
		$("#createTicketSave").click(function() {
			instance.submit();
		});
	}
};
CreateTicketNav.load();

var BlogNav = {
	createBlog : function() {
		var dict = {};
		dict["title"] = $("#newBlogTitle").val();
		dict["content"] = "";
		$
				.ajax({
					url : "/blog/0/save.ep",
					headers : {
						"Accept" : "application/json",
						"Content-type" : "application/json"
					},
					method : "post",
					data : JSON.stringify(dict),
					success : function(data) {
						if (data.returnCode == "200"
								&& data.returnMessage == "SUCCESS") {
							alert("Create Blog Success!");
						} else {
							alert("Show Blog Failed! \n" + data.returnMessage);
						}
					}
				});
	},
	load : function() {
		var instance = this;
		$("#newBlogSave").click(function() {
			instance.createBlog();
		});
	}
};

BlogNav.load();