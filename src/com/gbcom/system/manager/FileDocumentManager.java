package com.gbcom.system.manager;

//import com.hc.ipromis.core.menu.MenuItem;
//import com.hc.ipromis.core.session.CurrentProject;
//import com.hc.ipromis.core.util.FileHelp;
//import com.hc.ipromis.core.util.FormatUtil;
//import com.hc.ipromis.core.util.StringHelp;
//import com.hc.ipromis.daoservice.FileClientCategoryService;
//import com.hc.ipromis.daoservice.FileDocumentAttachService;
//import com.hc.ipromis.daoservice.FileDocumentService;
//import com.hc.ipromis.exchange.FileClientCategory;
//import com.hc.ipromis.exchange.FileDocument;
//import com.hc.ipromis.exchange.FileDocumentAttach;
//import com.hc.ipromis.service.ConfigManager;
//import com.hc.ipromis.service.DocumentManager;
//import com.hc.ipromis.service.system.MenuManager;
//import com.hc.ipromis.service.system.SysUserManager;
//import com.hc.ipromis.web.enums.SysPlatform;

import org.hibernate.lob.BlobImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

/**
 * author:Intellj
 */
@Service
public class FileDocumentManager {
	// @Autowired
	// private FileDocumentService fileDocumentService;
	//
	// @Autowired
	// private FileDocumentAttachService fileDocumentAttachService;
	//
	// @Autowired
	// private ConfigManager configManager;
	//
	// @Autowired
	// private SysUserManager sysUserManager;
	//
	// @Autowired
	// private FileClientCategoryService fileClientCategoryService;
	//
	// /**
	// * 判断当前用户是否有权限维护当前业务目录的文档
	// * 说明：1、只要有文档维护权限，那么所有文档目录都能增删改文件
	// *
	// 2、如果没有文档维护权限，那么需要根据业务文档目录的用途编码去查找对应的菜单，然后根据该菜单对应的privilege的维护权限去判断是否有编辑文档的权限
	// *
	// * @param userId
	// * @param fileClientCategoryId
	// * @return
	// */
	// public Boolean hasEditPrivilege(Long userId, Long fileClientCategoryId) {
	// String privilegeCode = "FileDocument_edit";
	// Boolean isSingleProject = CurrentProject.getProject() != null;
	// if (isSingleProject) {
	// privilegeCode = "SingleFileDocument_edit";
	// }
	// //先判断是否有文档编辑权限
	// Boolean check = sysUserManager.hasPrivilege(userId, privilegeCode);
	// if (!check && fileClientCategoryId != null) {
	// //如果没有则再根据业务文档编码去找对应的菜单权限
	// FileClientCategory clientCategory =
	// fileClientCategoryService.get(fileClientCategoryId);
	// String url = "file/fileClient/init.html?purpose=" +
	// clientCategory.getPurpose();
	//
	// MenuItem item = null;
	// Map map = MenuManager.getUrlMap();
	// if (map != null) {
	// if (isSingleProject) {
	// item = (MenuItem) map.get(SysPlatform.PLAT_FORM_SINGLE.getCode() + "_" +
	// url);
	// } else {
	// if (!SysPlatform.isMultiSystem) { //如果是单项目系统则除单项目之外只有个人门户
	// item = (MenuItem) map.get(SysPlatform.PLAT_FORM_PESON.getCode() + "_" +
	// url);
	// }else{
	// item = (MenuItem) map.get(SysPlatform.PLAT_FORM_MULTI.getCode() + "_" +
	// url);
	// }
	// }
	// }
	// if (item != null) {
	// //判断该菜单对应的系统编辑权限是否授权
	// check = sysUserManager.hasPrivilege(userId, item.getPrivilege() +
	// "_edit");
	// }
	// }
	// return check;
	// }
	//
	// /**
	// * 根据id删除文档
	// * 先删除关联附件，在删除文档，最后删除附件对应的实际文件
	// *
	// * @param id
	// */
	// public void deleteFileDocument(Long id) {
	// FileDocument fileDocument = fileDocumentService.get(id);
	// Set<FileDocumentAttach> documentAttachs =
	// fileDocument.getFileDocumentAttachs();
	// for (FileDocumentAttach documentAttach : documentAttachs) {
	// fileDocumentAttachService.delete(documentAttach);
	// }
	//
	// fileDocumentService.delete(fileDocument);
	//
	// //获取该文档中附件保存的目录
	// String dir = DocumentManager.getDocPath(fileDocument.getCategory());
	//
	// //最后再统一删除附件对应的实际文件是因为如果先删除物理文件，那么在删除数据库中对应的附件记录的时候出一场，物理文件将无法恢复
	// for (FileDocumentAttach documentAttach : documentAttachs) {
	// File file = new File(dir + documentAttach.getSaveFileName());
	// file.delete();
	// }
	// }
	// /*********************************************简单附件上传
	// begin***************************************************/
	// /**
	// * 获取附件上传入口---添加和修改页面
	// *
	// * @param columnName
	// * @param attachFileDir
	// * @return
	// * @throws Exception
	// */
	// public String getUploadInputLink(String attachFileDir, String columnName,
	// String attachFileValue, String filterFileExt) {
	// String inputLink =
	// "<input type=\"hidden\" name=\"{0}\" value=\"{1}\"/>\n<img id=\"img_upload_edit\" src=\"{2}/{3}\" alt=\"点击添加附件\" onclick=\"upload({0},'{4}','{5}')\" {6}>";
	// if (!StringHelp.isEmpty(attachFileValue)) {
	// inputLink = FormatUtil.format(inputLink, new String[]{columnName,
	// attachFileValue, DocumentManager.iconContextPath, "xgfjz.gif",
	// attachFileDir, filterFileExt, "style=\"display:none\""});
	// inputLink += "&nbsp;<img id=\"img_upload_delete\" src=\"" +
	// DocumentManager.iconContextPath +
	// "/file_delete.gif\" alt=\"删除已有附件\" onclick=\"uploadDelte(" + columnName +
	// ",this)\">";
	// inputLink = getUploadViewLink(attachFileDir, attachFileValue) +
	// inputLink;
	// } else {
	// inputLink = FormatUtil.format(inputLink, new String[]{columnName, "",
	// DocumentManager.iconContextPath, "tjfjz.gif", attachFileDir,
	// filterFileExt, ""});
	// }
	// return inputLink;
	// }
	//
	// /**
	// * 获取附件下载入口
	// *
	// * @param attachFileDir
	// * @param attachFileValue
	// * @return
	// * @throws Exception
	// */
	// public String getUploadViewLink(String attachFileDir, String
	// attachFileValue) {
	// if (!StringHelp.isEmpty(attachFileValue)) {
	// String viewLink =
	// "<img id=\"img_upload_view\" src=\"{0}/doc.gif\" alt=\"点击下载\" onclick=\"uploadDownload('{1}','{2}')\"><iframe name=\"statusfrm\" width=\"0\" height=\"0\" name=\"hidden\"></iframe>";
	// return FormatUtil.format(viewLink, DocumentManager.iconContextPath,
	// attachFileDir, attachFileValue);
	// } else {
	// return "";
	// }
	// }
	//
	// /**
	// * 简单附件上传--保存附件
	// * 备注：提供给业务模块save.html中调用
	// *
	// * @param oldAttachFile
	// * @param newAttachFile
	// * @param attachFileDir
	// */
	// public void saveUploadAttach(String oldAttachFile, String newAttachFile,
	// String attachFileDir) throws Exception {
	// String path = configManager.getUploadPath() + File.separator +
	// attachFileDir + File.separator;
	// FileHelp.mkDirs(path);
	//
	// //当原来的附件不为空，并且从页面传回来的附件和原来的不一样，那么删除掉原来的附件
	// if (!StringHelp.isEmpty(oldAttachFile) &&
	// !oldAttachFile.equals(newAttachFile)) {
	// File oldFile = new File(path + oldAttachFile);
	// oldFile.delete();
	// }
	// //当页面传回来的附件不为空，并且和原来的附件不一样，那么，保存从页面传回来的附件
	// if (!StringHelp.isEmpty(newAttachFile) &&
	// !newAttachFile.equals(oldAttachFile)) {
	// String tempAttachFile = configManager.getTempPath() + File.separator +
	// newAttachFile;
	// String attachFile = path + newAttachFile;
	//
	// FileHelp.moveFile(tempAttachFile, attachFile);
	// }
	// }
	// /*********************************************简单附件上传
	// end***************************************************/
	/**
	 * @param response
	 *            HttpServletResponse
	 * @param bytes
	 *            byte[]
	 * @param fileName
	 *            String
	 * @throws Exception
	 *             Exception
	 */
	public void downloadByByte(HttpServletResponse response, byte[] bytes,
			String fileName) throws Exception {
		fileName += ".par";
		InputStream inputStream = null;
		OutputStream output = null;
		Blob blob = new BlobImpl(bytes);
		try {
			if (blob != null) {
				inputStream = blob.getBinaryStream();
				response.setContentType("application/msword");
				if (blob.length() != 0) {
					response.setContentLength((int) blob.length());
				}
				response.addHeader("Content-Disposition",
						"attachment; filename=\""
								+ new String(fileName.getBytes(), "ISO8859-1")
								+ "\"");
				byte[] b = new byte[1024];
				int len;
				output = response.getOutputStream();
				while ((len = inputStream.read(b)) != -1) {
					output.write(b, 0, len);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
			if (output != null) {
				output.close();
				output = null;
			}
		}
	}
}
