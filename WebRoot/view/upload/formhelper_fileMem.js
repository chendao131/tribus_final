function $_(id){
	return document.getElementById(id);
}

//Flash的初始化事件，当Flash在浏览器已完全载入后将自动调用此脚本方法（如果存在的话）。
//在此事件中可进行相应的初始化工作，比如设置上传文件的地址、允许上传的文件类型等。
function FileUploader_onInit(){
	var o = $_('FileUploader');
	o.setUploadFileType("图片文件(*.jpg,*.bmp,*.doc)|*.jpg;*.bmp;*.doc");
	o.setUploadFileUrl("http://localhost:8080/tribus_spring/uploadForm/uploadAction.action");
	o.setMaxFileSize(-1);
}


//当Flash出现错误时（如上传文件）将触发此事件
function FileUploader_onError(error){
	alert("出现错误,错误信息为:" + error);
	var o = $_('FileUploader');
	o.resetUpload();
}

//当正在上传文件数据时将触发此事件，通过此事件中的两个参数可计算得知当前的上传进度。
//total : 返回的是当前正在上传文件的总大小，单位是：字节
//size  : 返回的是共已上传到服务器的文件数据大小，单位是：字节
function FileUploader_onUploading(total,size){
	var o = $_('per');
	o.style.width = (size / total * 500) + "px";
	o.innerText = Math.round((size / total * 100),2) + "%";
}

//当Flash已将所有文件数据上传到服务端时（服务端不一定已接收完数据）将触发此事件。
//file : 已上传的文件对象，拥有两个属性
//file.name : 文件名（不带任何路径)
//file.size   : 文件大小，单位：字节
function FileUploader_onSendComplete(file){
	alert("客户端已发送完文件的数据,文件名" + file.name + ",大小:" + file.size);
}

//当Flash已上传完数据并服务器已接收完数据时将触发此事件。
function FileUploader_onUploadComplete(data){
	alert("上传文件完成,服务器返回数据:" + data);	
	$_('FileUploader').resetUpload();
} 