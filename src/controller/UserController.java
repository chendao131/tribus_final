package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Activity;
import model.ActivityComment;
import model.Book;
import model.BookMark;
import model.Follow;
import model.Invitation;
import model.Movie;
import model.MovieMark;
import model.Music;
import model.MusicMark;
import model.MyTribusList;
import model.User;
import model.UserProfile;
import model.WishList;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import util.CompareUserLike;
import util.PageObject;
import util.Paging;
import util.TribusObjectMapping;
import vo.ActivityVO;
import vo.BookMarkVo;
import vo.MovieMarkVo;
import vo.MusicMarkVo;
import dao.ActivityCommentDao;
import dao.ActivityDao;
import dao.BookDao;
import dao.BookMarkDao;
import dao.FollowDao;
import dao.InvitationDao;
import dao.MovieDao;
import dao.MovieMarkDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.TribusListDao;
import dao.UserDao;
import dao.UserProfileDao;
import dao.WishListDao;

import config.GlobleConfig;

@Controller
@RequestMapping("user")
public class UserController {	
			
	/**
	 * 
	 */
	    private UserDao ud = new UserDao(); 
	    private ActivityDao actiDao = new ActivityDao();
		private MovieMarkDao moviemarkdao = new MovieMarkDao();
		private BookMarkDao bookmarkdao = new BookMarkDao();
		private MusicMarkDao musicmarkdao = new MusicMarkDao();
	    private TribusListDao tribDao = new TribusListDao();
	    private WishListDao wishd = new WishListDao();
		
	    public void setCookieTime(HttpServletRequest request, HttpServletResponse res){	    		    		    	
	    	
	    	String name = request.getParameter("email");
	    	String pwd = request.getParameter("userPw");
	    	
	    	Cookie nameC = new Cookie("userName", name );
	    	Cookie pwdC = new Cookie("pwd", pwd );
	    		    	
	    	nameC.setMaxAge(60*60*24*7);	    	
	    	nameC.setValue(name);	    	
	    	
	    	pwdC.setMaxAge(60*60*24*7);
	    	pwdC.setValue(pwd);	
	    	
	    	res.addCookie(nameC);
	    	res.addCookie(pwdC);  
	    }
	    	    	    
	    public void setCookieTimeNULL(HttpServletRequest request, HttpServletResponse res){	    		    		    	
	    	
	    	String name = request.getParameter("email");
	    	String pwd = request.getParameter("userPw");
	    	
	    	Cookie nameC = new Cookie("userName", name );
	    	Cookie pwdC = new Cookie("pwd", pwd );	    		    	
	    	
	    	nameC.setMaxAge(0);	    	
	    	nameC.setValue(name);	    	
	    	
	    	pwdC.setMaxAge(0);
	    	pwdC.setValue(pwd);	
	    	
	    	
	    	res.addCookie(nameC);
	    	res.addCookie(pwdC); 
	    }
	    
	    
	    public String[] getCookies(HttpServletRequest request, HttpServletResponse res){
	    	String[] nameAndPwd = new String[2];	    	
	    	Cookie[] cookies = request.getCookies();	    	
	    	if(cookies!=null){
	    		for (int i = 0; i < cookies.length; i++) {
		    		try {										
						Cookie c = cookies[i];						
						if( c.getName().equals("userName")){
							nameAndPwd[0] = c.getValue();
						}						
						if( c.getName().equals("pwd")){
							nameAndPwd[1] = c.getValue();
						}										       
					} catch (Exception e) {					
						e.printStackTrace();
					}
				}
	    		return nameAndPwd;
	    	}	    	
	    	return null;	    	
	    }
	    
	    @RequestMapping("logout")
	    public String logout(
	    		HttpServletRequest req,
	    		HttpServletResponse res
	    ){	    	
	    	
	    	HttpSession session = req.getSession();
	    	session.removeAttribute("user");
	    	session.invalidate();
	    	
	    	return "redirect:uesr/loginForm.acion";
	    }
	    
	    @RequestMapping("about")
	    public String about(){
	    	return "user/about";
	    }
	    
	    @RequestMapping("police")
	    public String police(){
	    	return "user/police";
	    }
	    
	    
	    @RequestMapping(value="login",method = RequestMethod.POST)
	    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		  
		  ModelAndView view = new ModelAndView();		  		  
		  String email = request.getParameter("email"); // email
		  String pwd = request.getParameter("userPw");
		  String remeber = request.getParameter("remember");
		  
		  if(remeber.equals("1")){
			  setCookieTime(request,response);
		  }else{
			  setCookieTimeNULL(request,response);  
		  }
		  
		  //GenericValidator.isBlankOrNull(name) 	  
		  if(GenericValidator.minLength(email, 1) == false || GenericValidator.minLength(pwd, 1) == false){
			  view.addObject("message", "your name or password is empty !");
			  view.setViewName("user/login");			  
		  }else{			  
			  User u = ud.haveRegistered(email, pwd);
			  		  		  
			  if(u == null){				  
				  view.addObject("message", "your name or password not currect ! ");
				  view.setViewName("user/login");				  
			  }
//			  else if(u.getUserState() == 0){
//				  view.addObject("message", "you have nor activite your account!");
//				  view.setViewName("user/login");
//			  }
			  else if(u.getUserState() == 1 || u.getUserState() == 0){				  
				  logon(request,u);
				  //view.setViewName("redirect:my.action");
				  view.setViewName("redirect:/activity/index.action");
			  }			  			 		  			 
		  }		  		  		  
		  return view;  
	    }
	  		
	  	
	  @RequestMapping("friendHome/{fId}")
	  public ModelAndView friendsHomepage(
			  HttpServletRequest req,
			  HttpServletResponse res,			  
			  @PathVariable("fId")int fId
	  ){
		  
		  User u = getUser(req, res);
		  int myId = u.getUserId();
		  
		  ModelAndView mv = new ModelAndView();
		  
		  if(myId == fId){
			  mv.setViewName("redirect:/user/my.action");
			  return mv;
		  }
		 
		  CompareUserLike c = new CompareUserLike();
		  List<BookMarkVo> list_book_same = c.getUserBookSimilar(myId, fId);
		  List<MovieMarkVo> list_movie_same = c.getUserMovieSimilar(myId, fId);
		  List<MusicMarkVo> list_music_same = c.getUserMusicSimilar(myId, fId);
		  
		  
		  int number_book_same = list_book_same == null ? 0 : list_book_same.size();
		  int number_movie_same = list_movie_same == null ? 0 : list_movie_same.size();
		  int number_music_same = list_music_same == null ? 0 : list_music_same.size();
		  
		  int total = number_book_same + number_movie_same + number_music_same;
		  	
		  	
		  	UserProfileDao upd = new UserProfileDao(); 
	    	UserProfile upf = upd.getUserProfileById(myId);
	    	//User u = upf.getUser();
	    	
	    	User friend = ud.getUserById(fId);
	    	UserProfile friendProf = upd.getUserProfileById(fId);
	    		    	
	    	MovieDao md = new MovieDao();
	    	List<Movie> user_wanted_movies = md.getMoviesWantedByUserId(myId);
	    	List<Movie> user_watch_movies = md.getMoviesWatchedByUserId(myId);
	    	
	    	BookDao bd = new BookDao();
	    	List<Book> user_wanted_book = bd.getBooksWantedByUserId(myId);
	    	List<Book> user_read_book = bd.getBooksReadByUserId(myId);
	    	
	    	MusicDao musicDao = new MusicDao();	    	
	    	List<Music> user_wanted_music = musicDao.getMusicsWantedByUserId(myId);
	    	List<Music> user_listened_music = musicDao.getMusicsListenedByUserId(myId);
	    		    	
	    	FollowDao fd = new FollowDao();
	    	List<User> l_f = fd.getAllFriends(fId); // your friends's all friends
		  	    		    	
	    	List<User> l_f_you_follow = fd.getAllYouFollow(fId);//all your friend followed
	    		    	
	    	ActivityDao ad = new ActivityDao();
	    	List<Activity> l_friend_activity = ud.getActivityById(fId);
	    		    		    
	    	TribusListDao td = new TribusListDao();
	    	List<MyTribusList> tribusList = td.getTribusListByUserId(fId);
	    	
	    	WishListDao wd = new WishListDao();
	    	List<WishList> wl = wd.getWishListByUserId(fId);
	    	
	    	ActivityCommentDao acd = new ActivityCommentDao();
	    	List<ActivityComment> user_acitivity_comments_list = acd.getActivityCommentByCondition(0,fId);
	    	List<ActivityVO> user_aci_lav = buildActivityVO(user_acitivity_comments_list);
	    	/**
	    	 * start
	    	 */
	    	
	    	mv.addObject("friend", friend);
	    	mv.addObject("friendProf", friendProf);
	    	mv.addObject("userPro", upf);
	    	mv.addObject("user", upf.getUser());
	    	mv.addObject("wantbooks",user_wanted_book);
	    	mv.addObject("readbooks",user_read_book);
	    	mv.addObject("wantmovies", user_wanted_movies);
	    	mv.addObject("watchmovies", user_watch_movies);
	    	mv.addObject("wantmusics", user_wanted_music);
	    	mv.addObject("listenmusics",user_listened_music);	    	
	    	
	    	mv.addObject("friends", l_f);
	    	mv.addObject("friendsNum", l_f == null ? 0 : l_f.size());
	    	mv.addObject("all_friend_follow", l_f_you_follow);
	    	mv.addObject("all_friend_follow_num", l_f_you_follow.size());
	    	if(l_f.contains(u)){
	    		mv.addObject("followed", 1);	
	    	}
	    	
	    	 
	    	mv.addObject("activity", l_friend_activity);
	    	
	    	mv.addObject("tribusList", tribusList);
	    	mv.addObject("wishList", wl);
	    	//mv.addObject("comments", user_comment_list);
	    	mv.addObject("activityComments", user_aci_lav);
	    	
	    	mv.addObject("total", total);
	    	mv.addObject("sameBook", list_book_same);
	    	mv.addObject("sameMovie", list_movie_same);
	    	mv.addObject("sameMusic", list_music_same);
	    	
	    	/**
	    	 * end
	    	 */	    			  
	    	
	    	mv.setViewName("user/new_friendHomePage");
		  return mv;
	  }
	  
	  
	  @RequestMapping("register")
	  public String registerPage(){
		return "user/register_new";  
	  }
	  
	  
	  /**
	   * only remember Name not password
	   * @param req
	   * @param res
	   * @return
	   */
	  @RequestMapping("loginForm")
	  public ModelAndView loginForm(
			  HttpServletRequest req, HttpServletResponse res
	  ){
		  
		  String[] nameAndPwd = getCookies(req,res);		  		  
		  ModelAndView mv = new ModelAndView();		  
		  mv.setViewName("user/login");
		  		  
		  if(nameAndPwd != null && nameAndPwd.length> 0 && nameAndPwd[0] != null){
			  mv.addObject("email", nameAndPwd[0]);
			  mv.addObject("isRem", 1);			 
		  }else{
			  mv.addObject("email", "username");
			  mv.addObject("isRem", 0);
		  }		 
		  return mv;  
	  }
	  
	  	  
	  @RequestMapping("frinedsList")
	  public ModelAndView followersAndfollowees(
			  HttpServletRequest req,
			  HttpServletResponse res	 
	  ){		 
		  
		  int userId = 0;
		  int page = 0;
		  
		  if(GenericValidator.isInt(req.getParameter("id")) && GenericValidator.isInt(req.getParameter("page"))){
			  
			  userId = Integer.parseInt(req.getParameter("id"));
			  page = Integer.parseInt(req.getParameter("page"));
			  
		  }
		  	
		  ModelAndView mv = new ModelAndView();		  
		  FollowDao fd = new FollowDao();
		  UserProfileDao upd = new UserProfileDao();		  		  
		  List<User> friends = fd.getAllFriends(userId);
		  		  
			User u = new User();
		 	UserProfile uf = new UserProfile();
		 	
		 	HttpSession se = req.getSession();
		 	if( se != null ){
		 		u = (User)se.getAttribute("user");
		 		uf = upd.getUserProfileById(userId);
		 	}
					 	
		 	Paging<User> p = new Paging<User>();
			p.setObj(friends);			
			p.setHaveOtherParameters(true);
			
			if(page <= 0){
				page = 1;
			}
			
			PageObject<User> po = p.getResult(GlobleConfig.show_local+"user/frinedsList.action?id="+u.getUserId(),page);
			
			  mv.addObject("friends", po.getL());
			  mv.addObject("followersNum",friends.size());
			  mv.addObject("userProf", uf);
			  mv.addObject("user", u);
			  mv.addObject("pageStr", po.getPageCode());
			  mv.setViewName("user/new_followers");
			  return mv;
	  }
	  
	  @RequestMapping(value="registerAction",method = RequestMethod.POST)
	  public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
		  
		  ModelAndView mv = new ModelAndView();		  
		  User u = (User)TribusObjectMapping.convert("model.User", request, response);		 		  		  
		  
		  //u.setStatus(0);
		  u.setUserState(0);
		  //u.setPassword(TribusMD5.md5Encode(u.getPassword()));
		 // u.setUserPw(u.getUserPw());	
		  
		  if(!GenericValidator.isEmail(u.getUserEmail())){
			  mv.addObject("message", "your email must validate!");
			  mv.setViewName("user/register");  
			  return mv;
		  }else if(!GenericValidator.minLength(u.getUserAlias(), 2)){
			  mv.addObject("message", "your alias must longer than 2 !");
			  mv.setViewName("user/register");
			  return mv;
		  }
		  
		  //u.setStatus(0);		  		
		  //u.set
		  u.setUserVerifycode(u.hashCode());
		  u.setCreateDate(new Date());
		  
		  UserDao ud = new UserDao();
		  UserProfileDao upd = new UserProfileDao();
		  		  
		  UserProfile upf = new UserProfile();		  
		  upf.setUser(u);
		  
		  if(ud.add(u) == 1){
			  upd.add(upf);
			  mv.setViewName("user/success");
		  }
		
//		  Mail mail = new Mail();
//		  mail.setMail_from("admin@tribus.com");
//		  mail.setMail_head_name("Tribus Welcome you !");
//		  mail.setMail_to(u.getUserEmail());
//		  mail.setMail_body("Hello, User"+ u.getUserAlias()+", really grateful register our website, please confirm this link"+
//				  "<a href='"+GlobleConfig.BASE_DOMAIN_NAME+"/user/registerConfirm.action?user='"+u.getUserVerifycode()+">click to confirm</a>");
//		  try {
//			mail.send(); 
//		  } catch (Exception e) {						
//			e.printStackTrace();			
//		  }
		  		  
		  return mv;
	  }
	  
	  
	  
	  @RequestMapping(value="error",method = RequestMethod.GET)
	  public String invitationError(HttpServletRequest request,HttpServletResponse response){		  
		  return "user/error";
	  }
	  
	  @RequestMapping(value="error_input",method = RequestMethod.GET)
	  public String invitationErrorInput(HttpServletRequest request,HttpServletResponse response){		  
		  return "user/error_input";
	  }
	  
	  @RequestMapping(value="error_name",method = RequestMethod.GET)
	  public String invitationErrorName(HttpServletRequest request,HttpServletResponse response){		  
		  return "user/error_name";
	  }
	  	  
	  @RequestMapping(value="invitationForm",method = RequestMethod.GET)
	  public String invitationForm(HttpServletRequest request,HttpServletResponse response){		  
		  return "user/invitation-login-empty";
	  }
	  
	  
	  @RequestMapping(value="invitation",method = RequestMethod.POST)
	  public ModelAndView invitation(HttpServletRequest request,HttpServletResponse response){
		  
		  InvitationDao id = new InvitationDao(); 
		  ModelAndView mv = new ModelAndView();		  
		  Invitation inv = (Invitation)TribusObjectMapping.convert("model.Invitation", request, response);
		  
		  if(!GenericValidator.isEmail(inv.getEmail()) || GenericValidator.isBlankOrNull(inv.getEmail())){
			  mv.addObject("message", "Email not illegal ! ");			 
			  mv.setViewName("user/invitation-login-empty");
		  }else{
			  id.save(inv);
			  mv.addObject("message", "ok !");
			  mv.setViewName("user/success");
		  }		  		  
		  return mv;
	  }
	  
	    @RequestMapping(value="registerConfirm",method = RequestMethod.POST)	    
	    public ModelAndView registerConfirm(HttpServletRequest request,HttpServletResponse response) {
	    	
	    	ModelAndView mv = new ModelAndView();
	    	String hashCode = request.getParameter("user");
	    	if("".equals(hashCode) || hashCode == null || !GenericValidator.isInt(hashCode)){
	    		mv.setViewName("user/notActivated");	    			    	
	    	}else{
	    		User u = new User();
		    	u.setUserVerifycode(Integer.parseInt(hashCode));
		    	User user = ud.getUserByCondition(u);
		    	user.setUserState(1);
		    	ud.update(user);		   
		    	
		    	logon(request,user);		    			    	
		    	mv.setViewName("user/index");		    
	    	}	    	
	    	return mv;
	    }
	    
	    
	    /**
	     * Using Ajax
	     * @param followerId
	     * @param followeeId
	     * @param req
	     * @param res
	     */
	    @RequestMapping("follow/{followerId}/{followeeId}")
	    public void follow(
	    		@PathVariable("followerId")int followerId,
				@PathVariable("followeeId")int followeeId,
				HttpServletRequest req,
				HttpServletResponse res
	    ) {	    	
	       
	        Follow f = new Follow();
	        f.setFolloweeId(followeeId);
	        f.setFollowerId(followerId);
	        
	        FollowDao fd = new FollowDao();
	        int result = fd.add(f);
	        
	        //view.addObject("followSuccess", true);
	        //view.setViewName("user/index");
	        try {
	        	if(result == 1)
	        		res.getWriter().write("ok");
	        	else
	        		res.getWriter().write("no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
	        //return view;
	    }
	    
	    
	    
	    /**
	     * Using Ajax
	     * @param followerId
	     * @param followeeId
	     * @param req
	     * @param res
	     */
	    @RequestMapping("unfollow/{followerId}/{followeeId}")
	    public void unfollow(
	    		@PathVariable("followerId")int followerId,
				@PathVariable("followeeId")int followeeId,
				HttpServletRequest req,
				HttpServletResponse res
	    
	    ) {	    	
	    	        
	        Follow f = new Follow();
	        f.setFolloweeId(followeeId);
	        f.setFollowerId(followerId);
	        
	        FollowDao fd = new FollowDao();
	        int result = fd.update(f);	        	   
	        try {
	        	if(result == 1)
	        		res.getWriter().write("ok");
	        	else
	        		res.getWriter().write("no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	                
	    }
	    
	    
	    @RequestMapping("addWishList/reivew/{type}/{id}")
	    public void addReivewWishList(	
	    		@PathVariable("type")String type,
	    		@PathVariable("id")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res
	    ){
	    	
	    	
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	
	    	WishListDao td = new WishListDao();
    		WishList myt = new WishList();
    		
    		
	    	if( "music".equals(type) ){
	    		
	    		MusicDao md = new MusicDao();
		    	Music m = md.getMusicById(id);
		    	myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("music");
	    		myt.setCreateDate(new Date());
	    		myt.setWishListName( m == null ? "": m.getMusicAlias());
	    		
		    	
		    	
	    	}else if( "book".equals(type) ){
	    		
	    		BookDao bd = new BookDao();
	    		Book b = bd.getBookById(id);
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("book");
	    		myt.setCreateDate(new Date());
	    		myt.setWishListName( b == null ? "": b.getBookName());
	    		
	    	}else if("movie".equals(type)){
	    		
	    		MovieDao md = new MovieDao();
	    		Movie m = md.getMovieById(id);
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("movie");
	    		myt.setCreateDate(new Date());
	    		myt.setWishListName( m == null ? "": m.getMovieNameOriginal());
	    			    		
	    	}	    		    
	
				int result = 0;
				try{
					result = td.add(myt);
					
		    		if(result == 1){
		    			res.getWriter().write("ok!");
		    		}else{
		    			res.getWriter().write("no");
		    		}  	
				}catch(Exception e){
					e.printStackTrace();
				}
	    		
	    	
	    }
	    
	    @RequestMapping("addTribusList/reivew/{type}/{id}")
	    public void addReviewTribusList(
	    
	    		@PathVariable("type")String type,
	    		@PathVariable("id")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res
	    			
	    ){

	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	
	    	TribusListDao td = new TribusListDao();
    		MyTribusList myt = new MyTribusList();
    		
    		
	    	if( "music".equals(type) ){
	    		
	    		MusicDao md = new MusicDao();
		    	Music m = md.getMusicById(id);
		    	myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("music");
	    		myt.setCreateDate(new Date());
	    		myt.setResourceName( m == null ? "": m.getMusicAlias());
	    		
		    	
		    	
	    	}else if( "book".equals(type) ){
	    		
	    		BookDao bd = new BookDao();
	    		Book b = bd.getBookById(id);
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("book");
	    		myt.setCreateDate(new Date());
	    		myt.setResourceName( b == null ? "": b.getBookName());
	    		
	    	}else if("movie".equals(type)){
	    		
	    		MovieDao md = new MovieDao();
	    		Movie m = md.getMovieById(id);
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("movie");
	    		myt.setCreateDate(new Date());
	    		myt.setResourceName( m == null ? "": m.getMovieNameOriginal());
	    			    		
	    	}	    		    
	
				int result = 0;
				try{
					result = td.add(myt);
					
		    		if(result == 1){
		    			res.getWriter().write("ok!");
		    		}else{
		    			res.getWriter().write("no");
		    		}  	
				}catch(Exception e){
					e.printStackTrace();
				}
	    		
	    	
	    }
	    
	    
	    @RequestMapping("addWishList/music/{resourceId}")
	    public void addMusicWishList(
	    		@PathVariable("resourceId")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	MusicDao md = new MusicDao();
	    	Music m = md.getMusicById(id);
	    	
	    		WishListDao td = new WishListDao();
	    		WishList myt = new WishList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("music");
	    		myt.setCreateDate(new Date());
	    		myt.setWishListName( m == null ? "": m.getMusicAlias());
	    		
				int result = 0;
				result = td.add(myt);
				
	    		if(result == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no");
	    		}  	
	    }
	    
	    
	    
	    @RequestMapping("addWishList/movie/{resourceId}")
	    public void addMovieWishList(
	    		@PathVariable("resourceId")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	MovieDao md = new MovieDao();
	    	Movie m = md.getMovieById(id);
	    		WishListDao td = new WishListDao();
	    		WishList myt = new WishList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	
	    		myt.setCreateDate(new Date());
	    		myt.setWishListName(m == null ? "":m.getMovieNameOriginal());
	    		myt.setType("movie");
	    		
				int result = 0;
				result = td.add(myt);
				
	    		if(result == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no");
	    		}
	    }
	    
	    
	    
	    @RequestMapping("addWishList/city/{resourceId}")
	    public void addCityWishList(
	    		@PathVariable("resourceId")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	
	    		WishListDao td = new WishListDao();
	    		WishList myt = new WishList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("city");
	    		
				int result = 0;
				result = td.add(myt);
				
	    		if(result == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no");
	    		}	    		    	
	    }
	    
	    
	    
	    @RequestMapping("addWishList/book/{resourceId}")
	    public void addBookWishList(
	    		@PathVariable("resourceId")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	BookDao bd = new BookDao();
	    	Book b = bd.getBookById(id);
	    		WishListDao td = new WishListDao();
	    		WishList myt = new WishList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);
	    		myt.setWishListName(b == null ? "":b.getBookName());
	    		myt.setCreateDate(new Date());
	    		myt.setType("book");
	    		
				int result = 0;
				result = td.add(myt);
				
	    		if(result == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no");
	    		}	    		    	
	    }
	    	   
	    
	    
	    @RequestMapping("addTribusList/city/{resourceid}")
	    public void addCityTribusList(
	    		@PathVariable("resourceid")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws IOException{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    		ActivityDao ad = new ActivityDao();
	    		Activity act = ad.getActivityById(id);
	    		
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("city");
	    		myt.setResourceName(act== null ? "":act.getActivityName());
	    			    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("Already Added");
	    		}else{
	    			res.getWriter().write("+ Tribus List");
	    		}	    		   
	    }
	    
	    
	    /**
	     *  
	     *  resourceId: bookId,movieId,musicId,activityId
	     *  resourceType: book: 1, movie :2,musicId:3, activityId : 4,
	     *                bookcomments: 5
	     *  
	     * @param resourceType
	     * @param req
	     * @param res
	     * @return
	     * @throws IOException 
	     */
	    @RequestMapping("addTribusList/movie/{resourceid}")
	    public void addMovieTribusList(
	    		@PathVariable("resourceid")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws IOException{
	    		  
			
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    	
	    		MovieDao md = new MovieDao();
	    		Movie m = md.getMovieById(id);
	    		
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("movie");
	    		myt.setResourceName(m == null ? "":m.getMovieNameOriginal());
	    			    	
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			//td.
	    			res.getWriter().write("no!");
	    		}	    		    	
	    	
	    }
	    
	    
	    @RequestMapping("addTribusList/music/{resourceid}")
	    public void addMusicTribusList(
	    		@PathVariable("resourceid")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    		    	
	    	MusicDao md = new MusicDao();
	    	Music music = md.getMusicById(id);
	    	
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("music");
	    		myt.setResourceName(music == null ? "":music.getMusicName());
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
	    		}	    		   		
	    }
	    
	    @RequestMapping("addTribusList/book/{resourceid}")
	    public void addBookTribusList(
	    		@PathVariable("resourceid")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws IOException{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("book");
	    		
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
	    		}	    		   	    		
	    }
	    

	    @RequestMapping(value="emailUsed",method = RequestMethod.POST)
	    public void isNameDuplicated(
	    		HttpServletRequest request,HttpServletResponse response
	    ) {	    	
	    	String email = request.getParameter("email");
	    	try {
	    	if("".equals(email) || email == null){	    		
				response.getWriter().write("noEmail");				
	    	}else{
	    		UserDao ud = new UserDao();
	    		if(ud.isEmailUsed(email).getUserId() >0){
	    			response.getWriter().write("used"); 
	    		}else{
	    			response.getWriter().write("ok");
	    		}
	    	}	    			    			    		
	    	} catch (IOException e) {					
				e.printStackTrace();
			}	        
	    }
	    
	    /**
	     * edit user
	     * @param user
	     */
	    
	    @RequestMapping(value="editForm",method = RequestMethod.GET)
	    public ModelAndView editFormUser(HttpServletRequest req, HttpServletResponse respon	    	
	    ){
	    	ModelAndView mav =	new ModelAndView();
	    	
	    	HttpSession se = req.getSession();
	    	User user = (User)se.getAttribute("user");
	    	
	    	if( user!=null ){
		    	UserProfileDao upd = new UserProfileDao();
		    	UserProfile u = upd.getUserProfileById(user.getUserId());
		    	
		    	mav.addObject("userProf", u);
		    	mav.addObject("user", u.getUser());
		    	mav.setViewName("user/personal_setting");	    		
	    	}
	    	
	    	return mav;
	    }
	    
	    
	    /**
	     * edit user
	     * @param user
	     */
	    
	    @RequestMapping(value="editAction/{id}",method = RequestMethod.POST)
	    public String editActionUser(HttpServletRequest req, HttpServletResponse respon,
	    		@PathVariable("id")
	    		int id
	    ){
	    	UserProfile u = (UserProfile)TribusObjectMapping.convert("model.UserProfile", req, respon);
	    	User us = (User)TribusObjectMapping.convert("model.User", req, respon);
	    	
	    	UserDao udao = new UserDao();
	    	UserProfileDao ud = new UserProfileDao();
	    	
	    	UserProfile up = ud.getUserProfileById(id);	  
	    	User user = udao.getUserById(id);
	    	
	    	TribusObjectMapping.objectCopy(up, u, new String[]{"userId","profId","user"});
	    	TribusObjectMapping.objectCopy(user, us, new String[]{"userId","userEmail"});
	    	
	    	up.setUser(user);
	    	udao.update(user);
	    	int res = ud.update(up);		
	    	
	    	
	    	/**
	    	 * update the User object in session
	    	 */
	    	update(req,user);
	    	
	    	
	    	return res == 1 ? "redirect:/user/my.action" : "redirect:editForm.action";
	    }
	    
	    	    	 	    
	    @RequestMapping(value="my",method = RequestMethod.GET)
	    public ModelAndView homepage(HttpServletRequest req, HttpServletResponse respon	    		
	    ){
	    	
	    	/**
	    	 * check it on filter
	    	 */
	    	User u = getUser(req,respon);
	    	int id = u.getUserId();
	    	
	    	
	    	ModelAndView mv = new ModelAndView();
	    	mv.setViewName("user/my");	    
	    	
	    	UserProfileDao upd = new UserProfileDao(); 
	    	UserProfile upf = upd.getUserProfileById(id);
	    	//User u = upf.getUser();
	    	
	    	
	    	MovieDao md = new MovieDao();
	    	List<Movie> user_wanted_movies = md.getMoviesWantedByUserId(id);
	    	List<Movie> user_watch_movies = md.getMoviesWatchedByUserId(id);
	    	
	    	BookDao bd = new BookDao();
	    	List<Book> user_wanted_book = bd.getBooksWantedByUserId(id);
	    	List<Book> user_read_book = bd.getBooksReadByUserId(id);
	    	
	    	MusicDao musicDao = new MusicDao();	    	
	    	List<Music> user_wanted_music = musicDao.getMusicsWantedByUserId(id);
	    	List<Music> user_listened_music = musicDao.getMusicsListenedByUserId(id);
	    	
	    	
	    	FollowDao fd = new FollowDao();
	    	List l_f = fd.getAllFriends(id);
	    	List l_you_follow = fd.getAllYouFollow(id);
	    	
	    	//BookCommentDao bcd = new BookCommentDao();
	    	//List<UserComment> user_comment_list = bcd.getBookCommentsByUserId(id);
	    		    	
	    	List<Activity> l_acit = ud.getActivityById(id);
	    	ActivityCommentDao acd = new ActivityCommentDao();   
	    	List<ActivityComment> user_acitivity_list = acd.getActivityCommentByCondition(0,id);
	    	List<ActivityVO> user_aci_lav = buildActivityVO(user_acitivity_list);
	    	
	    	
	    	TribusListDao td = new TribusListDao();
	    	List<MyTribusList> tribusList = td.getTribusListByUserId(id);
	    	
	    	WishListDao wd = new WishListDao();
	    	List<WishList> wl = wd.getWishListByUserId(id);
	    	
	    	
	    	mv.addObject("userPro", upf);
	    	mv.addObject("proGender", upf.getProfGender() == 1 ? "She":"He");
	    	mv.addObject("user", u);
	    	mv.addObject("wantbooks",user_wanted_book);
	    	mv.addObject("readbooks",user_read_book);
	    	mv.addObject("wantmovies", user_wanted_movies);
	    	mv.addObject("watchmovies", user_watch_movies);
	    	mv.addObject("wantmusics", user_wanted_music);
	    	mv.addObject("listenmusics",user_listened_music);	    	
	    	mv.addObject("friends", l_f);
	    	mv.addObject("friendsNum", l_f == null ? 0 : l_f.size());
	    	mv.addObject("youFollow",l_you_follow == null ? 0 :l_you_follow.size());
	    	mv.addObject("activity", l_acit);
	    	
	    	mv.addObject("tribusList", tribusList);
	    	mv.addObject("wishList", wl);
	    	//mv.addObject("comments", user_comment_list);
	    	mv.addObject("activityComments", user_aci_lav);	
	    	
	    	return mv;
	    }
	    	
	    
	    @RequestMapping("searchAll")
	    public ModelAndView searchAll(HttpServletRequest req,HttpServletResponse res){
	    	
	    	User u = getUser(req,res);
	    	UserProfile up = getUserProfile(req,res);
	    	
	    	String name = req.getParameter("search");
	    	if(GenericValidator.isBlankOrNull(name)){
	    		return null;
	    	}
	    	ModelAndView mv = new ModelAndView();
	    	mv.setViewName("user/searchAll");
	    	
	    	MovieDao md = new MovieDao();
	    	BookDao bd = new BookDao();
	    	MusicDao musicdao = new MusicDao();	    	
	    	
	    	List<Movie> movies = md.searchMovieByName(name);
	    	List<Book> books = bd.searchBookByName(name);
	    	List<Music> musics = musicdao.searchMusicByName(name);
	    	
	    	mv.addObject("user", u);
	    	mv.addObject("userP", up);
	    	mv.addObject("books", books);
	    	mv.addObject("movies", movies);
	    	mv.addObject("musics", musics);
	    		    	
	    	return mv;
	    }
	    
	    @RequestMapping("cityList/{userId}")
	    public void getCityList(
	    		HttpServletRequest req,HttpServletResponse res		
	    ){
	    	
	    }
	    
	    @RequestMapping("resourceList/{type}/{userId}")
	    public void getResourceList(
	    		HttpServletRequest req,HttpServletResponse res		
	    ){
	    	
	    }
	    
	    @RequestMapping("commentList/{type}/{userId}")
	    public void getCommentList(
	    		HttpServletRequest req,HttpServletResponse res		
	    ){
	    	
	    }
	    

	    @RequestMapping("tribusClassList/{userId}")
	    public void getTribusClassList(
	    		HttpServletRequest req,HttpServletResponse res		
	    ){
	    	
	    }
	    
	    
	    @RequestMapping("tribusList/{id}")
	    public ModelAndView getTribusList(
	    		HttpServletRequest req,HttpServletResponse res,
	    		@PathVariable("id")int id
	    ){	    			  
	    	int page = 1;
			if(GenericValidator.isInt(req.getParameter("page"))){				  				 
			    page = Integer.parseInt(req.getParameter("page"));				  
			}	    		    		    	
	    	ModelAndView mv = new ModelAndView();	    	    		    
	    	List<MyTribusList> l = tribDao.getTribusListByUserId(id);
	    	
	    	Paging<MyTribusList> p = new Paging<MyTribusList>();
			p.setObj(l);			
			p.setHaveOtherParameters(false);

			PageObject<MyTribusList> po = p.getResult(GlobleConfig.show_local+"user/tribusList/"+id+".action",page);
			
			mv.setViewName("user/tribusList");	
	    	mv.addObject("list", po.getL());
	    	mv.addObject("pageStr", po.getPageCode());
	    	mv.addObject("userProf", getUserProfile(req,res));
	    	mv.addObject("user", getUser(req,res));
	    	
	    	return mv;
	    }
	    
	    @RequestMapping("wishList/{userId}")
	    public void getWishList(
	    
	    		HttpServletRequest req,HttpServletResponse res,
	    		@PathVariable("userId")int id
	    		
	    ){
	    	int page = 1;
			if(GenericValidator.isInt(req.getParameter("page"))){				  				 
			    page = Integer.parseInt(req.getParameter("page"));				  
			}	    		    		    	
	    	ModelAndView mv = new ModelAndView();	    	    		    
	    	List<WishList> l = wishd.getWishListByUserId(id);
	    	
	    	Paging<WishList> p = new Paging<WishList>();
			p.setObj(l);			
			p.setHaveOtherParameters(false);

			PageObject<WishList> po = p.getResult(GlobleConfig.show_local+"user/wishList/"+id+".action",page);
			
			mv.setViewName("user/tribusList");	
	    	mv.addObject("list", po.getL());
	    	mv.addObject("pageStr", po.getPageCode());
	    	mv.addObject("userProf", getUserProfile(req,res));
	    	mv.addObject("user", getUser(req,res));
	    }
	    
	    
	    /**
	     * 
	     * @param req
	     * @param res
	     * @param type 0:movie, 1:music , 2:book
	     * @param id
	     */
	    @RequestMapping("deleteResource/{type}/{id}")
	    public void deleteResource(
	    		HttpServletRequest req,HttpServletResponse res,
	    		@PathVariable("type")int type,
	    		@PathVariable("id")int id
	    ){
	    	
	    	User u = getUser(req,res);	    	
	    	MovieMark mm = null;
	    	BookMark bm = null;
	    	MusicMark musicm = null;
	    	if(u!=null){
	    		mm = moviemarkdao.getMarkByMovieAndUserId(id, u.getUserId());
	    		musicm = musicmarkdao.getMarkByMusicAndUserId(id, u.getUserId());
	    		bm = bookmarkdao.getMarkByBookAndUserId(id, u.getUserId());
	    	}
	    	int delete_flag = 0;	    	
	    	switch (type) {
			case 0://movie
				moviemarkdao.deleteMovieMark(mm);
				delete_flag = 1;
				break;				
			case 1:
				musicmarkdao.deleteMusicMark(musicm);
				delete_flag = 1;
				break;				
			case 2:
				bookmarkdao.deleteBookMark(bm);
				delete_flag = 1;
				break;
			default:
				break;
			}	    
	    		    	
	    	try {
		    	if(delete_flag == 1){	    		
					res.getWriter().write("ok!");				
		    	}else{
		    		res.getWriter().write("no");
		    	}	    	
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    /**
	     * should do it in DAO
	     */
	    private List<ActivityVO> buildActivityVO(List<ActivityComment> l){
	    	List<ActivityVO> lav = new ArrayList<ActivityVO>();
	    	if( l != null ){
	    		for (ActivityComment ac : l) {
	    			ActivityVO av = new ActivityVO(ac);
	    			String name = actiDao.getActivityNameById(ac.getActivityId());
	    			av.setActivityName(name);
	    			lav.add(av);
				}
	    		return lav;
	    	}
	    	return null;
	    }
	    
	    private User getUser(
	    		HttpServletRequest req,HttpServletResponse res
	    ){
	    	HttpSession session = req.getSession();
	    	User u = session.getAttribute("user") == null ? null : (User)session.getAttribute("user");	    		    	
	    	return u;
	    }
	    
	    private UserProfile getUserProfile(HttpServletRequest req,HttpServletResponse res){
	    	HttpSession session = req.getSession();
	    	UserProfile up = session.getAttribute("userProf") == null ? null : (UserProfile)session.getAttribute("user");	    		    	
	    	return up;
	    }
	    
	    
	    private void update(HttpServletRequest request , User u){	    	 
			  HttpSession session = request.getSession(true);			  			  			  
			  session.setAttribute("user", u);			  
			  if(u!=null){
				  UserProfileDao ud = new UserProfileDao();			  
				  UserProfile up = ud.getUserProfileByUserId(u.getUserId());
				  session.setAttribute("userProf", up);
			  }			  
	    }
	    
	    
	    private void logon(HttpServletRequest request , User u){	    	 
			  HttpSession session = request.getSession(true);			  			  			  
			  session.setAttribute("user", u);
			  
			  if(u!=null){
				  UserProfileDao ud = new UserProfileDao();			  
				  UserProfile up = ud.getUserProfileByUserId(u.getUserId());
				  session.setAttribute("userProf", up);
			  }
			  
			  session.setAttribute("isLogin","alreadylogin");			
	    }
	    	    
}