package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Activity;
import model.ActivityComment;
import model.Book;
import model.Follow;
import model.Invitation;
import model.Movie;
import model.Music;
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
import util.TribusObjectMapping;
import vo.BookMarkVo;
import vo.MovieMarkVo;
import vo.MusicMarkVo;
import dao.ActivityCommentDao;
import dao.ActivityDao;
import dao.BookDao;
import dao.FollowDao;
import dao.InvitationDao;
import dao.MovieDao;
import dao.MusicDao;
import dao.TribusListDao;
import dao.UserDao;
import dao.UserProfileDao;
import dao.WishListDao;


@Controller
@RequestMapping("user")
public class UserController {	
	
	    private UserDao ud = new UserDao(); 
	
//	    @RequestMapping("coo/{name}/{pwd}")
//	    public void rememberNameAndPwd(	    		
//	    		@PathVariable("name")
//	    		String name,
//	    		@PathVariable("pwd")
//	    		String pwd,
//	    		HttpServletRequest request, HttpServletResponse res		
//	    ){
	
	    @RequestMapping("coo")
	    public void rememberNameAndPwd(	    			    		
	    		HttpServletRequest request, HttpServletResponse res		
	    ){
	    	String name = request.getParameter("name");
	    	String pwd = request.getParameter("pwd");
	    	
	    	Cookie nameC = new Cookie("userName", name );
	    	Cookie pwdC = new Cookie("pwd", pwd );
	    	
	    	nameC.setMaxAge(60*60*24*7);	    	
	    	nameC.setValue(name);	    	
	    	
	    	pwdC.setMaxAge(60*60*24*7);
	    	pwdC.setValue(pwd);
	    	
	    	//nameC.setPath("/tribus_spring/user/");
	    	//pwdC.setPath("/tribus_spring/user/");
	    	
	    	res.addCookie(nameC);
	    	res.addCookie(pwdC);
	    	
	    	try {
				res.getWriter().write("ok!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    	    
	    public void setCookieTime(Cookie nameC,Cookie pwdC,String name,String pwd  				    		
	    ){	    		    		    	
	    	
	    	nameC.setMaxAge(60*60*24*7);	    	
	    	nameC.setValue(name);	    	
	    	
	    	pwdC.setMaxAge(60*60*24*7);
	    	pwdC.setValue(pwd);	
	    }
	    
	    @RequestMapping("getC")
	    public String[] getCookie(HttpServletRequest request, HttpServletResponse res){
	    	String[] nameAndPwd = new String[2];
	    	
	    	Cookie[] cookies = request.getCookies();
	    	for (int i = 0; i < cookies.length; i++) {
	    		try {
									
					Cookie c = cookies[i];
//					System.out.println(c.getName());
//					System.out.println(c.getValue());				       				      
				       //res.getWriter().write(name);
					
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
	    
	    @RequestMapping(value="login",method = RequestMethod.POST)
	    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		  
		  ModelAndView view = new ModelAndView();		  		  
		  String email = request.getParameter("email"); // email
		  String pwd = request.getParameter("userPw");
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
				  view.setViewName("redirect:my.action");
			  }
			  
			  //
			  
			  
		  }		  		  		  
		  return view;  
	    }
	  	
	  @RequestMapping("index/{id}/{email}")	  
	  public ModelAndView index(
			  @PathVariable("id")int id,
			  @PathVariable("email")String email
	  ){
		  
		  ModelAndView view = new ModelAndView();		  				  
		  return view;
		  
		  
		  /**
		   * no end time activity
		   */
		  
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
			  mv.setViewName("redirect:my.action");
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
	    		    	
	    	MovieDao md = new MovieDao();
	    	List<Movie> user_wanted_movies = md.getMoviesWantedByUserId(myId);
	    	List<Movie> user_watch_movies = md.getMoviesWatchedByUserId(myId);
	    	
	    	BookDao bd = new BookDao();
	    	List<Book> user_wanted_book = null;//bd.getBooksWantedByUserId(myId);
	    	List<Book> user_read_book = null;//bd.getBooksReadByUserId(myId);
	    	
	    	MusicDao musicDao = new MusicDao();	    	
	    	List<Music> user_wanted_music = null;//musicDao.getMusicsWantedByUserId(myId);
	    	List<Music> user_listened_music = null;//musicDao.getMusicsListenedByUserId(myId);
	    		    	
	    	FollowDao fd = new FollowDao();
	    	List l_f = fd.getAllFriends(myId);
		  
	    	ActivityDao ad = new ActivityDao();
	    	List<Activity> l_friend_activity = ud.getActivityById(fId);
	    	
	    	
	    	
	    	TribusListDao td = new TribusListDao();
	    	List<MyTribusList> tribusList = td.getTribusListByUserId(fId);
	    	
	    	WishListDao wd = new WishListDao();
	    	List<WishList> wl = wd.getWishListByUserId(fId);
	    	
	    	ActivityCommentDao acd = new ActivityCommentDao();
	    	List<model.ActivityComment> user_acitivity_comments_list = acd.getActivityCommentByCondition(0,fId);
	    	
	    	/**
	    	 * start
	    	 */
	    	
	    	
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
	    	mv.addObject("activity", l_friend_activity);
	    	
	    	mv.addObject("tribusList", tribusList);
	    	mv.addObject("wishList", wl);
	    	//mv.addObject("comments", user_comment_list);
	    	mv.addObject("activityComments", user_acitivity_comments_list);
	    	
	    	mv.addObject("total", total);
	    	mv.addObject("sameBook", list_book_same);
	    	mv.addObject("sameMovie", list_movie_same);
	    	mv.addObject("sameMusic", list_music_same);
	    	
	    	/**
	    	 * end
	    	 */
	    	
		  
		  return null;
	  }
	  
	  
	  @RequestMapping("register")
	  public String registerPage(){
		return "user/register";  
	  }
	  
	  
	  @RequestMapping("loginForm")
	  public String loginForm(){
		return "user/login";  
	  }
	  
	  
	  
	  @RequestMapping("frinedsList/{userId}/{page}")
	  public ModelAndView getFriends(
			  HttpServletRequest req,
			  HttpServletResponse res,
			  @PathVariable("userId")int userId,
			  @PathVariable("page")int page
	  ){		 
		  
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
			  
			  mv.addObject("friends", friends);
			  mv.addObject("userProf", uf);
			  mv.addObject("user", u);
			  
			  mv.setViewName("user/followers");
			  return mv;
	  }
	  
	  @RequestMapping(value="registerAction",method = RequestMethod.POST)
	  public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
		  
		  ModelAndView mv = new ModelAndView();		  
		  User u = (User)TribusObjectMapping.convert("model.User", request, response);		 		  		  
		  
//		  u.setStatus(0);
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
	    
	    
	    
	    
	    @RequestMapping("addWishList/music{resourceId}")
	    public void addMusicWishList(
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
	    		myt.setType("music");
	    		
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
	    		}	    		    	
	    }
	    
	    
	    
	    @RequestMapping("addWishList/movie{resourceId}")
	    public void addMovieWishList(
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
	    		myt.setType("movie");
	    		
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
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
	    	
	    		WishListDao td = new WishListDao();
	    		WishList myt = new WishList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("movie");
	    		
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
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
	    @RequestMapping("addTribusList/movie/{resource}")
	    public void addMovieTribusList(
	    		@PathVariable("resource")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws IOException{
	    		  
			
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("movie");
	    		
	    		
	    		if(td.add(myt) == 1){
	    			res.getWriter().write("ok!");
	    		}else{
	    			res.getWriter().write("no!");
	    		}	    		    	
	    	
	    }
	    
	    
	    @RequestMapping("addTribusList/music/{resource}")
	    public void addMusicTribusList(
	    		@PathVariable("resource")int id,					    		
	    		HttpServletRequest req,HttpServletResponse res) throws Exception{
	    	
	    	HttpSession hs = req.getSession();
	    	User obj = (User)hs.getAttribute("user");
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile upf = ud.getUserProfileById(obj.getUserId());
	    	
	    		TribusListDao td = new TribusListDao();
	    		MyTribusList myt = new MyTribusList();
	    		myt.setResourceId(id);
	    		myt.setUserProfile(upf);	    		
	    		myt.setType("music");
	    		
	    		
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
	    
	    @RequestMapping(value="editForm/{id}",method = RequestMethod.GET)
	    public ModelAndView editFormUser(HttpServletRequest req, HttpServletResponse respon,
	    		@PathVariable("id")int id
	    ){
	    	ModelAndView mav =	new ModelAndView();
	    	
	    	UserProfileDao upd = new UserProfileDao();
	    	UserProfile u = upd.getUserProfileById(id);
	    	
	    	mav.addObject("userProf", u);
	    	mav.addObject("user", u.getUser());
	    	mav.setViewName("user/personal_setting");
	    	
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
	    		    
	    	UserProfileDao ud = new UserProfileDao();
	    	UserProfile up = ud.getUserProfileById(id);
	    	
	    	TribusObjectMapping.objectCopy(up, u, new String[]{"userId","profId","user"});
	    	
	    	int res = ud.update(up);	    	
	    	return res == 1 ? "redirect:/user/my.action" : "redirect:editForm.action";
	    }
	    
	    
//	    @RequestMapping("friends/{id}")
//	    public String findAllFriends(
//	    		HttpServletRequest req, HttpServletResponse respon,
//	    		@PathVariable("id")int id
//	    ){	    		    		    	
//	    	FollowDao fd = new FollowDao();
//	    	List<User> friends = fd.getAllFriends(id);
//	    	ModelAndView mav = new ModelAndView();
//	    	mav.addObject("follows", friends);
//	    	return "user/follow";
//	    }
	    	    	 	    
	    @RequestMapping(value="my",method = RequestMethod.GET)
	    public ModelAndView homepage(HttpServletRequest req, HttpServletResponse respon	    		
	    ){
	    	
	    	/**
	    	 * check it on filter
	    	 */
	    	User u = getUser(req,respon);
	    	int id = u.getUserId();
	    	
	    	
	    	ModelAndView mv = new ModelAndView();
	    	mv.setViewName("user/mytribus");	    
	    	
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
	    	
	    	//BookCommentDao bcd = new BookCommentDao();
	    	//List<UserComment> user_comment_list = bcd.getBookCommentsByUserId(id);
	    		    	
	    	List<Activity> l_acit = ud.getActivityById(id);
	    	ActivityCommentDao acd = new ActivityCommentDao();
	    	List<ActivityComment> user_acitivity_list = acd.getActivityCommentByCondition(0,id);
	    	
	    	TribusListDao td = new TribusListDao();
	    	List<MyTribusList> tribusList = td.getTribusListByUserId(id);
	    	
	    	WishListDao wd = new WishListDao();
	    	List<WishList> wl = wd.getWishListByUserId(id);
	    	
	    	
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
	    	mv.addObject("activity", l_acit);
	    	
	    	mv.addObject("tribusList", tribusList);
	    	mv.addObject("wishList", wl);
	    	//mv.addObject("comments", user_comment_list);
	    	mv.addObject("activityComments", user_acitivity_list);	
	    	
	    	return mv;
	    }
	    	   
	    
	    private User getUser(
	    		HttpServletRequest req,HttpServletResponse res
	    ){
	    	HttpSession session = req.getSession();
	    	User u = session.getAttribute("user") == null ? null : (User)session.getAttribute("user");	    		    	
	    	return u;
	    }
	    
	    private void logon(HttpServletRequest request , User u){	    	 
			  HttpSession session = request.getSession(true);
			  session.setAttribute("user", u);			  
			  session.setAttribute("isLogin","alreadylogin");
	    }
	    	    
}