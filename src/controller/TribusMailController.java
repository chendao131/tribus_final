package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.TribusObjectMapping;

import dao.MessageDao;
import model.Message;
import model.User;
import dao.UserProfileDao;
import model.UserProfile;


@Controller
@RequestMapping("userMail")
public class TribusMailController {
	
	
	public String sendMail(){
		return "user/sendMail";		
	}
	
	@RequestMapping("sendAction")
	public String sendMailAction(HttpServletRequest request, HttpServletResponse res){
							
		HttpSession se = request.getSession();
		User u = (User)se.getAttribute("user");
		
		MessageDao md = new MessageDao();
		Message mess = (Message)TribusObjectMapping.convert("model.Message", request, res);
		
		md.add(mess);		
		
		return "redirect: user/inbox/"+u.getUserId()+"/1/0";
	}
	
	
	 @RequestMapping("inbox/{id}/{inOrout}/{page}")
	    public String rememberNameAndPwd(	    			    		
	    		HttpServletRequest request, HttpServletResponse res,
	    		@PathVariable("id") int id,
	    		@PathVariable("inOrout") int typeId,
	    		@PathVariable("page") int page
	    ){
		 	ModelAndView mv = new ModelAndView();
		 	MessageDao md = new MessageDao();
		 	UserProfileDao upd = new UserProfileDao();
		 	
		 	
		 	List<Message> inboxAllmails = md.getUserInboxMessageAll(id);
		 	List<Message> outboxAllmails = md.getUserOutboxMessageAll(id);
		 	List<Message> unreadInboxmails = md.getUserInboxMessageAllUnread(id);
		 	
		 	User u = new User();
		 	UserProfile uf = new UserProfile();
		 	
		 	HttpSession se = request.getSession();
		 	if( se != null ){
		 		u = (User)se.getAttribute("user");
		 		uf = upd.getUserProfileById(id);
		 	}
		 	//uf.getProfCity()
		 	
		 	
		 	mv.addObject("inbox", inboxAllmails);
		 	mv.addObject("outbox", outboxAllmails);
		 	mv.addObject("user", u);
		 	mv.addObject("userProf", uf);
		 	mv.addObject("unreadNum", unreadInboxmails);
		 	mv.addObject("reminder", unreadInboxmails != null ? unreadInboxmails.size():0 );
		 	
		 	return "user/mail";
	    }
}
