package com.zte.base.controller.admin;

import com.zte.base.bean.CodeMsg;
import com.zte.base.bean.PageBean;
import com.zte.base.bean.Result;
import com.zte.base.entity.home.News;
import com.zte.base.service.admin.NewsService;
import com.zte.base.service.common.NewsTypeService;
import com.zte.base.util.ValidateEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 新闻列表
 * @author
 * @date
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsTypeService newsTypeService;

    private Logger log = LoggerFactory.getLogger(NewsController.class);

    @GetMapping("/list")
    public String list(Model model, News news, PageBean<News> pageBean)
    {
        model.addAttribute("pageBean",newsService.findList(news,pageBean));
        model.addAttribute("caption",news.getCaption()==null?"":news.getCaption());
        model.addAttribute("title","新闻列表");
        return "admin/news/news_list";
    }

    /**
     * 新闻删除
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id){
        try {
            newsService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success(true);
    }



    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(News news)
    {
        if(news.getNewsType() == null || news.getNewsType().getId() == null)
            return Result.error(CodeMsg.ADMIN_NEWS_NOT_SELECT_ERROR);

        CodeMsg codeMsg = ValidateEntityUtil.validate(news);
        if(codeMsg.getCode() != CodeMsg.SUCCESS.getCode())
            return Result.error(codeMsg);

        if(newsService.save(news) == null)
            return Result.error(CodeMsg.ADMIN_NEWS_ADD_ERROR);

        return Result.success(true);
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("newsType", newsTypeService.findAll());
        return "admin/news/news_add";
    }
}
