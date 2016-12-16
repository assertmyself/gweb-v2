package com.gbcom.system.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.system.utils.FileWebUtil;
import com.hc.core.controller.BaseCRUDActionController;

/**
 * 组件数据获取
 * 
 * 方法设计原则：前台ajax访问 返回json数据格式
 * 
 * @author SunYanzheng
 * @date 2015-1-22,上午10:10:25
 * @version v1.0.0
 */
@Controller
public class ComponentDataController extends BaseCRUDActionController {
	private static final Logger LOG = Logger
			.getLogger(ComponentDataController.class);



	/**
	 * 下载文件;将文件从服务器下载到客户端；需要指定path参数。 文件生成代码在具体controller中，，文件的下载使用
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param path
	 *            String
	 * @throws Exception
	 *             Exception
	 */
	@RequestMapping
	public void downFile(HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception {
		try {
			path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
			if (path.startsWith("\\")) {
				path = getServletContext().getRealPath("/") + path;
				path = path.replaceAll("\\\\", "/");
			}
			LOG.info("down file path is ：" + path);
			File file = new File(path);
			if (file.exists()) {
				FileWebUtil.downloadSection(request, response, path, file
						.getName(), false);
			} else {
				LOG.info("文件不存在");
			}
		} catch (Exception e) {
			log.error("down file error!!!", e);
		}

	}

	

}