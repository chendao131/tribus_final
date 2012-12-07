package controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import util.GetPicPostFix;
import util.ImageTools;
import config.GlobleConfig;

@Controller
@RequestMapping("uploadForm")
public class UploadController {
	@RequestMapping("index")
	public String goToUploadForm() {
		return "upload/uploadform";
	}

	@RequestMapping(value = "uploadAction", method = RequestMethod.POST)
	public ModelAndView upload(
			@RequestParam(value = "myfile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		ModelAndView view = new ModelAndView();
		try {
			String type = file.getContentType();		
			if (file.getSize() > 1000 * 1000) {// maximal size is 1000kb
				view.setViewName("upload/uploadfail");
				return view;
			}
			if (type.contains("jpg") || type.contains("jpeg") || type.contains("png") || type.contains("gif")) {
				if (file != null && file.getBytes() != null) {

					String div_container_num = request.getParameter("div_container_number");
					String postFix = GetPicPostFix.getFilePostFix(file.getOriginalFilename());
					
					String path = GlobleConfig.BASE_PIC_PATH;
					Calendar now = new GregorianCalendar();
					
					String n_N = now.get(Calendar.YEAR) + "/"
					+ now.get(Calendar.MONTH) + "/"
					+ now.get(Calendar.DAY_OF_MONTH) + "/";
					
					String newName = n_N + System.currentTimeMillis() + postFix;
				
					File targetFile = new File(path, newName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);

					/**
					 * back up
					 */
					String N_path = GlobleConfig.PIC_BACKUP + 
					"/"+now.get(Calendar.YEAR) + "/"
					+ now.get(Calendar.MONTH) + "/"
					+ now.get(Calendar.DAY_OF_MONTH) + "/";
					
					File big = ImageTools.cuttingBig(targetFile);
					File mid = ImageTools.cuttingMidd(targetFile);
					File smal = ImageTools.cuttingSmall(targetFile);
					
					FileUtils.copyFileToDirectory(targetFile, new File(N_path));
					// FileUtils.copyDirectory(new File(path),new File(N_path));
															
					view.addObject("fileUrl_middle", GlobleConfig.BASE_DOMAIN_NAME + n_N +mid.getName());
					view.addObject("fileUrl_big", GlobleConfig.BASE_DOMAIN_NAME + n_N +big.getName());
					view.addObject("fileUrl_small", GlobleConfig.BASE_DOMAIN_NAME + n_N +smal.getName());
					
					view.addObject("id", div_container_num);
					view.setViewName("upload/uploadsuccess");
					return view;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		view.setViewName("upload/uploadfail");
		return view;
	}
}