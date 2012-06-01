package controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import util.GetPicPostFix;
import config.GlobleConfig;

@Controller
@RequestMapping("uploadForm")
public class Upload {

	@RequestMapping("index")
	public String goToUploadForm() {
		return "upload/uploadform";
	}

	@RequestMapping(value = "uploadAction", method = RequestMethod.POST)
	public ModelAndView upload(
			@RequestParam(value = "Filedata", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model,HttpServletResponse res) {
		
		    ModelAndView view = new ModelAndView();			
		
			String oriName = file.getOriginalFilename();				
			try {
				if (file != null && file.getBytes() != null) {
										
					String postFix = GetPicPostFix.getFilePostFix(oriName);					
					String path = GlobleConfig.BASE_PIC_PATH;					
					Calendar now = new GregorianCalendar();
					String newName = now.get(Calendar.YEAR) + "/"
							+ now.get(Calendar.MONTH) + "/"
							+ now.get(Calendar.DAY_OF_MONTH) + "/"
							+ System.currentTimeMillis() + postFix;

					File targetFile = new File(path, newName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					
					file.transferTo(targetFile);
					
					view.addObject("fileUrl", GlobleConfig.BASE_DOMAIN_NAME+ newName);
					view.setViewName("upload/uploadsuccess");
					
					return view;
					//res.getWriter().write("success|"+GlobleConfig.BASE_DOMAIN_NAME+ newName);
					
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}		
			view.setViewName("error");
			return view;
	}	
}