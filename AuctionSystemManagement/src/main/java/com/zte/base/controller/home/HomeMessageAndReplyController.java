package com.zte.base.controller.home;

import com.zte.base.bean.CodeMsg;
import com.zte.base.bean.Result;
import com.zte.base.entity.common.BiddingProject;
import com.zte.base.entity.home.HomeUser;
import com.zte.base.entity.home.MessageAndReply;
import com.zte.base.service.admin.BiddingProjectService;
import com.zte.base.service.home.MessageAndReplyService;
import com.zte.base.util.SessionUtil;
import com.zte.base.util.ValidateEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 留言回复Controller
 */
@Controller
@RequestMapping("/home/message")
public class HomeMessageAndReplyController {

    @Autowired
    private MessageAndReplyService messageAndReplyService;

    @Autowired
    private BiddingProjectService biddingProjectService;

    /**
     * 添加留言
     * @param messageAndReply
     * @param projectId
     * @return
     */
    @RequestMapping(value ="add",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(MessageAndReply messageAndReply,Long projectId){
        HomeUser loginedHomeUser = SessionUtil.getLoginedHomeUser();
        if(loginedHomeUser == null){
            return Result.error(CodeMsg.USER_SESSION_EXPIRED);
        }
        BiddingProject biddingProject = biddingProjectService.find(projectId);
        if(biddingProject == null){
            return Result.error(CodeMsg.HOME_MESSAGE_ADD_ERROR);
        }
        CodeMsg validate = ValidateEntityUtil.validate(messageAndReply);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }

        messageAndReply.setHomeUser(loginedHomeUser);
        messageAndReply.setBiddingProject(biddingProject);
        if(messageAndReplyService.save(messageAndReply) == null){
            return Result.error(CodeMsg.HOME_MESSAGE_ADD_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 根据id查看
     * @param id
     * @return
     */
    @RequestMapping("/find")
    public String find(Model model,Long id){
        model.addAttribute("messageAndReply",messageAndReplyService.find(id));
        return "home/home_user/find";
    }

}
