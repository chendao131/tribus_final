function $_(id){
	return document.getElementById(id);
}

//Flash�ĳ�ʼ���¼�����Flash�����������ȫ������Զ����ô˽ű�������������ڵĻ�����
//�ڴ��¼��пɽ�����Ӧ�ĳ�ʼ�����������������ϴ��ļ��ĵ�ַ�������ϴ����ļ����͵ȡ�
function FileUploader_onInit(){
	var o = $_('FileUploader');
	o.setUploadFileType("ͼƬ�ļ�(*.jpg,*.bmp,*.doc)|*.jpg;*.bmp;*.doc");
	o.setUploadFileUrl("http://localhost:8080/tribus_spring/uploadForm/uploadAction.action");
	o.setMaxFileSize(-1);
}


//��Flash���ִ���ʱ�����ϴ��ļ������������¼�
function FileUploader_onError(error){
	alert("���ִ���,������ϢΪ:" + error);
	var o = $_('FileUploader');
	o.resetUpload();
}

//�������ϴ��ļ�����ʱ���������¼���ͨ�����¼��е����������ɼ����֪��ǰ���ϴ����ȡ�
//total : ���ص��ǵ�ǰ�����ϴ��ļ����ܴ�С����λ�ǣ��ֽ�
//size  : ���ص��ǹ����ϴ������������ļ����ݴ�С����λ�ǣ��ֽ�
function FileUploader_onUploading(total,size){
	var o = $_('per');
	o.style.width = (size / total * 500) + "px";
	o.innerText = Math.round((size / total * 100),2) + "%";
}

//��Flash�ѽ������ļ������ϴ��������ʱ������˲�һ���ѽ��������ݣ����������¼���
//file : ���ϴ����ļ�����ӵ����������
//file.name : �ļ����������κ�·��)
//file.size   : �ļ���С����λ���ֽ�
function FileUploader_onSendComplete(file){
	alert("�ͻ����ѷ������ļ�������,�ļ���" + file.name + ",��С:" + file.size);
}

//��Flash���ϴ������ݲ��������ѽ���������ʱ���������¼���
function FileUploader_onUploadComplete(data){
	alert("�ϴ��ļ����,��������������:" + data);	
	$_('FileUploader').resetUpload();
} 