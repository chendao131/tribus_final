package controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.Music;
import model.MusicTag;
import model.Singer;
import model.User;
import model.UserProfile;

import org.apache.commons.validator.GenericValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.GetSessionUser;
import util.TribusObjectMapping;
import vo.MessageVO;
import dao.BookDao;
import dao.MessageDao;
import dao.MusicDao;
import dao.MusicTagDao;
import dao.SingerDao;
import dao.UserProfileDao;

public class ResourceController {

	public ResourceController() {
		super();
	}

	@RequestMapping("saveMusicCreation")
	public String saveMusicCreation(HttpServletRequest request, HttpServletResponse response) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MusicDao md = new MusicDao();
		Music m = new Music();
		SingerDao sd = new SingerDao();
		String singerName = request.getParameter("musicSinger");
		if(sd.getSingerByName(singerName)!=null){
			m.setSinger(sd.getSingerByName(singerName));
		}
		else{
			Singer s = new Singer();
			s.setSingerName(singerName);
			m.setSinger(s);
			sd.save(s);
		}
		
		Set<MusicTag> tags = new HashSet<MusicTag>();
		MusicTag mt = new MusicTag();
		MusicTagDao mtd = new MusicTagDao();
		mt = mtd.getMusicTagByName(request.getParameter("musicTag"));
		tags.add(mt);
		
		m.setMusicPic(request.getParameter("successful_img_path"));
		m.setMusicPicBig(request.getParameter("successful_img_path_big"));
		m.setMusicPicMiddle(request.getParameter("successful_img_path_middle"));
		m.setMusicPicSmall(request.getParameter("successful_img_path_small"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("musicDate")))
			m.setMusicPublishDate(Date.valueOf(request.getParameter("musicDate")));
		m.setMusicName(request.getParameter("musicName"));
		m.setMusicBrief(request.getParameter("musicBrief"));
		m.setTags(tags);
		md.save(m);
		return "redirect:/music/"+m.getMusicId()+".action";
	}

	@RequestMapping("edit/{musicId}")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, @PathVariable("musicId")int bookId) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("book/edit");
		MusicDao md = new MusicDao();
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;		
	}

	@RequestMapping("editAction")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res) {
			User u = GetSessionUser.getUser(req, res);
			int userId = u.getUserId();
			ModelAndView mv = new ModelAndView("book/edit");
			BookDao bd = new BookDao();								 					
			Book b = (Book)TribusObjectMapping.convert("model.Book", req, res);
			Book old = bd.getBookById(b.getBookId());
			
			TribusObjectMapping.objectCopy(old, b, new String[]{"bookId"});
			
			int code = bd.update(old);
			if(code == 1){
				mv.addObject("message", "success");
			}else{
				mv.addObject("message", "fail");
			}		
	//		mv.setViewName("redirect:book/"+b.getBookId()+"/"+code);
			//return new AnotherController().handleRequest(request, response);
			MessageDao mgd = new MessageDao();
			List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
			try {
				unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
			mv.setViewName("forward:/book/"+old.getBookId());
			return mv;		
		}

}