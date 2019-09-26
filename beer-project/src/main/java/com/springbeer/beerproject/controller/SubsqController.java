package com.springbeer.beerproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.common.Util;
import com.springbeer.beerproject.entity.SubFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.service.SubsqService;


@Controller
@RequestMapping(value="/subsq")
public class SubsqController {
	
	
	@Autowired
	SubsqService subsqService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String contentList(Model model
			 
		//	,@RequestParam(required = false, defaultValue = "1") int page
		//	, @RequestParam(required = false, defaultValue = "1") int range, HttpSession session
			) {

		List<Subscription> Subscriptions = subsqService.findSubsqList();

		model.addAttribute("Subscription",Subscriptions);

		// Member loginuser = (Member) session.getAttribute("loginuser");
		
//		if(loginuser!=null) {
//			model.addAttribute("id", loginuser.getId());
//		}
		
//		try {
//			String category = "darkbeer"; // 입력 값이 RAM이고 이것에 해당되는 DB를 찾아서 pagination
//
//			int listCnt = contentService.findMemoryListCnt(category);
//
//			// Pagination 媛앹껜�깮�꽦
//			Pagination pagination = new Pagination();
//			pagination.pageInfo(page, range, listCnt);
//
//			model.addAttribute("pagination", pagination);
//
//			
//	        // �쟾泥대━�뒪�듃
//			List<Content> products = productService.findMemoryList(pagination,category);
//			if(products ==null) {
//				return "redirect:/";
//			}
//			
			
//			model.addAttribute("product", products);
//			model.addAttribute("listCnt", listCnt);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		return "/subscripts/subsqlist";
	}
	
	
	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public String subsqDetail(@RequestParam(name="subsqNo") int subsqNo, Model model) {
		
		List<Subscription> subsqDetails = subsqService.subsqdetail(subsqNo);		
//		for (Subscription subsqDetail : subsqDetails) {
//			List<SubFile> files = subsqService.subsqDetailFileListBySubsqNo(subsqDetails.get(0));
//			subsqDetail.setFileList(files);
//		}
		
		model.addAttribute("subsqdetail", subsqDetails);
		
	return "/subscripts/subsqdetail";
	}
	

	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String subsqUpdate(@RequestParam(name="subsqNo") int subsqNo, Model model) {
		
		List<Subscription> subsqUpdate = subsqService.subsqupdate(subsqNo);		
//		for (Subscription subsqDetail : subsqDetails) {
//			List<SubFile> files = subsqService.subsqDetailFileListBySubsqNo(subsqDetails.get(0));
//			subsqDetail.setFileList(files);
//		}
		model.addAttribute("subsqupdate", subsqUpdate);

	return "/subscripts/subsqupdate";
	}
	
	@PostMapping(value = "/subsqupdatewrite/{subsqNo}")
	public String subsqUpdateWrite(@PathVariable("subsqNo") int subsqNo, Subscription subscription, Model model) {
		
		subsqService.subsqupdatewrite(subscription);
	return "redirect:/subsq/list";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String subsqDelete(@RequestParam(name="subsqNo") int subsqNo, Model model) {

		subsqService.subsqdelete(subsqNo);

	return "redirect:/subsq/list";
	}

	
	@GetMapping(value = "/write")
	public String subsqWrite() {
		
	return "/subscripts/write";
	
	}

	@PostMapping(value = "/write")
	public String contentWrite(Subscription subscription, SubFile subfile, Model model, MultipartHttpServletRequest req) {

			MultipartFile mf = req.getFile("subfile");
			boolean test = mf.isEmpty();
			
			if (test == false) { // 파일 안 넣어도 업로드 가능 하려면  @Query사용해야 될 듯.
		
				ServletContext application = req.getServletContext();
				String path = application.getRealPath("/images/");

				String subsqUserFileName =  mf.getOriginalFilename();
				if (subsqUserFileName.contains("\\")) { 
	 
					subsqUserFileName = subsqUserFileName.substring(subsqUserFileName.lastIndexOf("\\") + 1);
				}
				String subsqSavedFileName = Util.makeUniqueFileName(subsqUserFileName);

						try {

							mf.transferTo(new File(path, subsqSavedFileName));

							SubFile subFile = new SubFile();
							subFile.setSubsqSavedFileName(subsqSavedFileName);
							subFile.setSubsqUserFileName(subsqUserFileName);
							ArrayList<SubFile> fileList = new ArrayList<SubFile>();
							fileList.add(subFile);
							subscription.setFiles(fileList); // 일일이 VO에 담는 것을 Controller에서 옮김.
							
							subsqService.writeSubsq(subscription);

						} catch (Exception e) {
							e.printStackTrace();
						} 
					} 

	return "redirect:/subsq/list";
	}
	
	@RequestMapping(value = "/listNew", method = RequestMethod.GET)
	public String subsqlist(Model model) {

		List<Subscription> Subscriptions = subsqService.findSubsqList();

		model.addAttribute("Subscription",Subscriptions);

		return "redirect:/subsq/list";
	}
	
	
	
}