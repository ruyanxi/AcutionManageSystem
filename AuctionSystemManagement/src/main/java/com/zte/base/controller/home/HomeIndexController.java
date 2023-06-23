package com.zte.base.controller.home;

import com.zte.base.entity.common.BiddingProject;
import com.zte.base.service.admin.BiddingProjectService;
import com.zte.base.service.admin.LabelTypeService;
import com.zte.base.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 前台首页控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/home/index")
public class HomeIndexController {

	@Autowired
	private LabelTypeService labelTypeService;

	@Autowired
	private BiddingProjectService biddingProjectService;

	@Autowired
	private NewsService newsService;

	/**
	 * 首页
	 * @param
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model){
		Date date = new Date();
		/*List<BiddingProject> top = biddingProjectService.findTop(ProjectStatus.INPUBLIC, ProjectStatus.BIDDING, ProjectStatus.SUCCESSFULBIDDING, ProjectStatus.ENDBIDDING, ProjectStatus.CLOSED);*/
		List<BiddingProject> top = biddingProjectService.findTop8();

		for (BiddingProject biddingProject : top) {
			biddingProjectService.status(biddingProject, date);
		}
		model.addAttribute("projectList",top);
		model.addAttribute("newsList",newsService.findTop());
		return "home/index/index";
	}
}
