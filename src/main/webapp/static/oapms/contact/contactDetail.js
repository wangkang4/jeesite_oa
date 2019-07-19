function visitList(){
	url = window.location.href;
	url=url.replace("contact/toContactDetail", "visit/list");
	window.location.href = url;
}
function addVisit(){
	url = window.location.href;
	url=url.replace("contact/toContactDetail", "visit/toAdd");
	window.location.href = url;
}