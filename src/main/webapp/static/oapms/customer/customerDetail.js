function contactList(){
	url = window.location.href;
	url=url.replace("customer/toCustomerDetail", "contact/list");
	window.location.href = url;
}
function addContact(){
	url = window.location.href;
	url=url.replace("customer/toCustomerDetail", "contact/toAdd");
	window.location.href = url;
}