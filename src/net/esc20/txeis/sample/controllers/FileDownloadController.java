package net.esc20.txeis.sample.controllers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.esc20.txeis.sample.models.FileModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("fileDownload")
@SessionAttributes("FileModel")
public class FileDownloadController {

	public FileDownloadController() {
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		FileModel fileModel = new FileModel(); 
		fileModel.setName("File Download Example");
		model.addAttribute("FileModel", fileModel);
		return "fileDownload";
		
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public String startProcess(@ModelAttribute("FileModel") FileModel fileModel, BindingResult errors, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		
		if (fileModel.isStartProcess()) {
			fileModel.setStartProcess(false);
			
			createFile(request, "file1.txt", "The rain in spain falls mainly on the plain.");
			createFile(request, "file2.txt", "The brown cow jumped over the yellow moon.");
	
			fileModel.setFilesWritten(true);
		} 
		
		model.addAttribute("FileModel", fileModel);
		
		return "fileDownload";		
	}
	
	private void createFile(HttpServletRequest request, String name, String content) {
		try {
			String filePath = request.getSession().getServletContext().getRealPath("") + File.separator + name;
			File  file = new File(filePath);
			DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
			output.writeChars(content);
			output.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
