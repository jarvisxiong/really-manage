/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.height = 400; // 设置高度
	// config.width = 1200; // 设置宽度

	config.language = 'zh-cn'; // 中文配置zh-cn / en

	config.filebrowserBrowseUrl = '/wuxincheng-manage/ckfinder/ckfinder.html';    
    config.filebrowserImageBrowseUrl = '/wuxincheng-manage/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/wuxincheng-manage/ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = '/wuxincheng-manage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	config.filebrowserImageUploadUrl = '/wuxincheng-manage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	config.filebrowserFlashUploadUrl = '/wuxincheng-manage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';

	// 配置指定字体
	// config.font_names = '微软雅黑;Arial;宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;Courier New';

	// 设置工具栏可折叠(减少toolbar的占用空间)
	// config.toolbarCanCollapse = true;

};

CKEDITOR.on( 'dialogDefinition', function( ev ) {
	// Take the dialog name and its definition from the event data.
	var dialogName = ev.data.name;
	var dialogDefinition = ev.data.definition;

	// Check if the definition is from the dialog we're
	// interested in (the 'image' dialog). This dialog name found using DevTools plugin
	if ( dialogName == 'image' ) {
		// Remove the 'Link' and 'Advanced' and 'Upload' tabs from the 'Image' dialog.
		dialogDefinition.removeContents( 'Link' ); // 删除"链接"选项卡
		dialogDefinition.removeContents( 'Upload' ); // 删除"上传"选项卡
		dialogDefinition.removeContents( 'advanced' ); // 删除"高级"选项卡

		// 获取"图像信息"属性
		var infoTab = dialogDefinition.getContents( 'info' );

		// 删除相关属性
		infoTab.remove( 'txtHSpace'); // 水平间距
		infoTab.remove( 'txtVSpace'); // 垂直间距
		infoTab.remove( 'txtBorder'); // 边框大小
		// infoTab.remove( 'cmbAlign' ); // 对齐方式
        infoTab.remove( 'ratioLock' ); // 锁定比例和原始尺寸
	}
});