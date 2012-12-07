package controller;


import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import config.GlobleConfig;

import util.PageObject;
import util.Paging;
import util.TribusObjectMapping;
import vo.MessageVO;

import dao.MessageDao;
import dao.UserDao;
import model.Message;
import model.User;
import dao.UserProfileDao;
import model.UserProfile;


@Controller
@RequestMapping("userMail")
public class TribusMailController {
	
	private MessageDao md = new MessageDao();
	private UserProfileDao upd = new UserProfileDao();
	
	@RequestMapping("sendMailForm")
	public String sendMail(){
		return "user/sendMail";		
	}
	
	
	@RequestMapping("messageInfo/{id}")
	public ModelAndView mail(HttpServletRequest request, HttpServletResponse res,
			@PathVariable("id")int id
	){
		
		HttpSession se = request.getSession();
    	User user = (User)se.getAttribute("user");
    	UserProfile uf = null;			 				 	
	 	
    	if( user!= null){
    		uf = upd.getUserProfileById(id);    		
    	}
		
    	Message m = new Message();
		m.setMessageId(id);
		Message nm = md.getMessageByCondition(m);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/mailDetail");		
		mv.addObject("mail", nm);
		mv.addObject("user", user);
	 	mv.addObject("userProf", uf);
		return mv;
	}
	
	@RequestMapping("deleteMailAction")
	public String deleteMail(HttpServletRequest request, HttpServletResponse res		
	){
		
		String ids = request.getParameter("ids");
		
		if( ids == null || ids.split(",").length == 0 ){
			return "redirect:my.action";
		}
		
		String[] id = ids.split(",");
		int[] id_arr = new int[id.length]; 
		for (int i = 0; i < id_arr.length; i++) {
			id_arr[i] = Integer.parseInt(id[i]);
		}		
		
		MessageDao md = new MessageDao();
		
		
		int i = md.deleteMessageByIds(id_arr);					
		if( i  ==  1 ){
			
		}				
		return "redirect:user/box/1/0.action";
	}
	
	
	
	
	@RequestMapping("sendMailAction")
	public String sendMailAction(HttpServletRequest request, HttpServletResponse res){
							
		HttpSession se = request.getSession();	
		User u = (User)se.getAttribute("user");
		UserDao ud = new UserDao();
		
		String email = request.getParameter("messageToUserEmail");
		User mailToUser = new User();
		mailToUser.setUserEmail(email);
		User new_mailToUser = ud.getUserByCondition(mailToUser);		
		
		
		Message mess = (Message)TribusObjectMapping.convert("model.Message", request, res);
		if(new_mailToUser != null){
			mess.setMessageToUserId(new_mailToUser.getUserId());	
			mess.setMessageFromUserId(u!=null ? u.getUserId():0);
			mess.setMessageRead(false);
			mess.setMessageDate(new Date());
			md.add(mess);			
		}						
		return "redirect:/userMail/box/0.action";
	}
	
	
		/**
		 * 1 means inbox, 0 means outbox
		 * @param request
		 * @param res
		 * @param id
		 * @param typeId
		 * @param page
		 * @return
		 * @throws ParseException 
		 */
	 @RequestMapping("box/{inOrout}")
	    public ModelAndView emailList(	    			    		
	    		HttpServletRequest request, HttpServletResponse res,	    		
	    		@PathVariable("inOrout") int typeId	
	    ) throws ParseException{
		  
		    int page = 1;
		  
		    if(GenericValidator.isInt(request.getParameter("page"))){			  			  
			   page = Integer.parseInt(request.getParameter("page"));			  
		    }	 
		    
		 	ModelAndView mv = new ModelAndView();
		 			 			 					 	
		 	HttpSession se = request.getSession();
	    	User user = (User)se.getAttribute("user");			 
		 	if( user!= null){
		 		int id = user.getUserId();
		 		List<MessageVO> inboxAllmails = md.getUserInboxMessageAll(id); 
			 	List<MessageVO> outboxAllmails = md.getUserOutboxMessageAll(id);
			 	List<MessageVO> unreadInboxmails = md.getUserInboxMessageAllUnread(id);
			 	
			 	
			 	UserProfile uf = new UserProfile();			 				 	
			 	uf = upd.getUserProfileById(id);
			 					 			 				 	
			 	Paging<MessageVO> p = new Paging<MessageVO>();
			 	if(typeId == 0){
			 		p.setObj(inboxAllmails);								
			 	}else{
			 		p.setObj(outboxAllmails);								
			 	}
			 	p.setHaveOtherParameters(false);
		
				PageObject<MessageVO> po = p.getResult(
						GlobleConfig.show_local+"userMail/box/"+typeId+"/.action"
						,
						page < 0 ? 0 : page);
										
			 	mv.addObject("isInbox", typeId );
			 	if(typeId == 0){
			 		mv.addObject("inbox", po.getL());	
			 	}else{
			 		mv.addObject("outbox", po.getL());	
			 	}			 	
			 	mv.addObject("pageStr", po.getPageCode());
			 	mv.addObject("user", user);
			 	mv.addObject("userProf", uf);
			 	mv.addObject("unreadNum", unreadInboxmails.size());
			 	mv.addObject("reminder", unreadInboxmails != null ? unreadInboxmails.size():0 );
			 	mv.addObject("page", page);
			 	if(typeId == 1){//inbox
			 		mv.addObject("inOrOutflag", true); // true means inbox
			 	}else{
			 		mv.addObject("inOrOutflag", false); // true means outbox
			 	}			 	 
			 	mv.setViewName("user/mail");
			 	return mv;
		 	}
		 	return null;
	    }
	 	
	 
	 @RequestMapping("readMail/{id}")
	 public void readMessage(
			 HttpServletRequest request, HttpServletResponse res,
			 @PathVariable("id")
			 int id
	 ){
		 MessageDao md = new MessageDao();
		 
		 Message mes = new Message();
		 mes.setMessageId(id);		 
		 
		 Message m = md.getMessageByCondition(mes);
		 m.setMessageRead(true);
		 		 
		 try {
			md.update(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		 
		return ;
	 }
	 
}
