package controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

import model.Activity;
import model.Movie;
import model.MovieTag;
import model.Music;
import model.MusicComment;
import model.MusicTag;
import model.MyTribusList;
import model.Singer;
import model.Starring;
import model.TribusClassify;
import model.User;
import model.UserProfile;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import config.GlobleConfig;

import util.DateToString;
import util.GetSessionUser;
import util.PageObject;
import util.Paging;
import vo.FriendsRecommend;
import vo.FriendsRecommendMovie;
import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MessageVO;
import vo.MovieReview;
import vo.MusicHomeReview;
import vo.MyTribusListVO;
import vo.RecentHotMusic;
import vo.SearchResult;
import vo.SinglePageFriendsRecord;
import vo.SinglePageMain;
import vo.SinglePageReview;
import dao.ActivityDao;
import dao.BookDao;
import dao.HomePageDao;
import dao.MessageDao;
import dao.MovieDao;
import dao.MovieMarkDao;
import dao.MusicCommentDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.MusicTagDao;
import dao.SingerDao;
import dao.SinglePageDao;
import dao.StarringDao;
import dao.TribusListClassifyDao;
import dao.TribusListDao;
import dao.UserProfileDao;

@Controller
@RequestMapping("music")
public class MusicController extends ResourceController {
	@RequestMapping("createMusic")
	public ModelAndView createMusicAction(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/create_movie");
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("markMusic/{musicId}")
	public void markMusic(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();

		
		String mark = request.getParameter("mark");
		if(mark.equals("wanted")){
			MusicMarkDao mmd = new MusicMarkDao();
			mmd.markWatchWantedByMusicIdAndUserId(musicId, userId);
		}else if(mark.equals("done")){
			MusicMarkDao mmd = new MusicMarkDao();
			mmd.markWatchDoneByMusicIdAndUserId(musicId, userId);
		}
	}
	
	@RequestMapping("rateMusic/{musicId}")
	public void rateMusic(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MusicMarkDao mmd = new MusicMarkDao();
		mmd.rateByMusicIdAndUserId(musicId, userId, Integer.parseInt(request.getParameter("rate")));
		mmd.markWatchDoneByMusicIdAndUserId(musicId, userId);
	}
	
	@RequestMapping("deleteRate/{musicId}")
	public void deleteRate(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MusicMarkDao mmd = new MusicMarkDao();
		mmd.deleteRate(musicId, userId);
	}
	
	@RequestMapping("search/{type}")
	public String searchMusicAction(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("type")int type){
		
		String redirect = null;
		String name = request.getParameter("single_search_name");
		switch(type){
		case 1: redirect = "redirect:/music/search/Musics="+name+".action";break;
		case 2: redirect = "redirect:/music/search/Celebrities="+name+".action";break;
		case 3: redirect = "redirect:/music/search/List="+name+".action";break;
		}
		return redirect;
	}
	
	@RequestMapping("search/Musics={musicName}")
	public ModelAndView searchMusicsByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicName")String musicName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_movie");
		SinglePageDao singlePageDao = new SinglePageDao();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		List<Music> musics = md.searchMusicByName(musicName);
		Iterator<Music> iterator = musics.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Music m = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMusicId());
			sr.setItemPic(m.getMusicPic());
			sr.setItemName(m.getMusicName().length()>15? (m.getMusicName().subSequence(0, 15)+"..."): m.getMusicName());
			if(m.getMusicName()!=null){
				//sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
				if(m.getMusicPublishDate()!=null)
					sr.setItemDate(DateToString.convertDateToString(m.getMusicPublishDate()));
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMusicId()));
			
			searchResults.add(sr);
		}

		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/music/search/Musics="+musicName+".action",page);
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
//		mv.addObject("searchResults", searchResults);
		mv.addObject("searchName", musicName);
		
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("search/Celebrities={starName}")
	public ModelAndView searchCelebrityByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("starName")String starName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_celebrities");
		SingerDao sd = new SingerDao();
		List<Singer> singers = sd.searchSingerByName(starName);
		Iterator<Singer> iterator = singers.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Singer s = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setStarId(s.getSingerId());
			sr.setStarPic(s.getSingerPic());
			sr.setStarName(s.getSingerName());
			sr.setSearchString(starName);
			searchResults.add(sr);
		}

		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/music/search/Celebrities="+starName+".action",page);
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		mv.addObject("searchName", starName);
//		mv.addObject("searchResults", searchResults);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("search/List={listName}")
	public ModelAndView searchTribusListByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("listName")String listName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		TribusListDao tld = new TribusListDao();
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_list");
		List<MyTribusListVO> searchResults = tld.getTribusListMovieByResourceName(listName);
		
		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/music/search/List="+listName+".action",page);
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		mv.addObject("searchName", listName);
//		mv.addObject("searchResults", searchResults);	
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("editMusic/{musicId}")
	public ModelAndView editMusic(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/edit_music");
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(musicId);
		
		mv.addObject("music", m);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("saveMusicEdition/{musicId}")
	public String saveMusicEdition(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(musicId);
		SingerDao sd = new SingerDao();
		String singerName = request.getParameter("musicSinger");
		Singer s = sd.getSingerByName(singerName);
		if(s!=null){
			m.setSinger(s);
		}
		else{
			s = new Singer();
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
		if(!GenericValidator.isBlankOrNull(request.getParameter("musicDate")))
			m.setMusicPublishDate(Date.valueOf(request.getParameter("musicDate")));
		m.setMusicName(request.getParameter("musicName"));
		m.setMusicBrief(request.getParameter("musicBrief"));
		md.update(m);
		return "redirect:/music/"+musicId+".action";
	}
	

	
	@RequestMapping("musicHomePage")
	public ModelAndView displayMusicHomePage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/Music_Final");
		HomePageDao homePageDao = new HomePageDao();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		//home page recent hot movie
		List<RecentHotMusic> recentHotMusics_1 = new ArrayList<RecentHotMusic>();
		Integer a[] ={237,235,233,229,232,231};
		for(int i = 0; i< 6; i++){
			Music m = md.getMusicById(a[i]);
			RecentHotMusic hotMusic = new RecentHotMusic();
			hotMusic.setMusicId(m.getMusicId());
			hotMusic.setMusicName(m.getMusicName().length()>15? (m.getMusicName().subSequence(0, 15)+"..."): m.getMusicName());
			//hotMusic.setMusicName(m.getMusicName());
			hotMusic.setMusicPic(m.getMusicPic());
			if(m.getMusicPublishDate()!=null)
				hotMusic.setMusicPublishDate(m.getMusicPublishDate().toString());
			hotMusic.setMusicRating(mmd.getAverageGrade(i));
			hotMusic.setSingerName(m.getSinger().getSingerName());
			recentHotMusics_1.add(hotMusic);
		}
		List<RecentHotMusic> recentHotMusics_2 = new ArrayList<RecentHotMusic>();
		Integer b[] ={230,238,66,245,242,72};
		for(int i = 0; i< 6; i++){
			Music m = md.getMusicById(b[i]);
			RecentHotMusic hotMusic = new RecentHotMusic();
			hotMusic.setMusicId(m.getMusicId());
			hotMusic.setMusicName(m.getMusicName());
			hotMusic.setMusicPic(m.getMusicPic());
			if(m.getMusicPublishDate()!=null)
				hotMusic.setMusicPublishDate(m.getMusicPublishDate().toString());
			hotMusic.setMusicRating(mmd.getAverageGrade(i));
			hotMusic.setSingerName(m.getSinger().getSingerName());
			recentHotMusics_2.add(hotMusic);
		}
		
		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getMusicHomePageReviewByCommentId(i);
			if(homePageReview!=null)
				homePageReviews.add(homePageReview);
		}
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = homePageDao.getMusicHomePageFriendRecommend(u.getUserId());
		//add tribus list
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotMusicTribusList();
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		mv.addObject("recentHotMusics_1", recentHotMusics_1);
		mv.addObject("recentHotMusics_2", recentHotMusics_2);
		if(!homePageReviews.isEmpty())
			mv.addObject("homePageReviews",homePageReviews);
		if(homePageFriendRecommends!=null)
			mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		mv.addObject("tribusList", tribusList);
		
		//guess you like
		ActivityDao ad = new ActivityDao();
		List<Activity> activities = ad.getHottestActivity();
		mv.addObject("guessYouLike", activities);
		return mv;
	}
	
	@RequestMapping("{musicId}")
	public ModelAndView displaySingleMusic(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		TribusListClassifyDao tcDao = new TribusListClassifyDao();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/singleMusicPage");
		SinglePageDao singlePageDao = new SinglePageDao();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		
		SinglePageMain singlePageMain = singlePageDao.getMusicSinglePage(musicId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getMusicSinglePageReviewById(musicId);
		List<SinglePageFriendsRecord> friendRecords = singlePageDao.getMusicSinglePageFriendsRecord(userId, musicId);
		
		Integer markWatch = 0;
		if(mmd.getMarkByMusicAndUserId(musicId, userId)!=null)
			markWatch = mmd.getMarkByMusicAndUserId(musicId, userId).getMusicListen();
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		List<TribusClassify> l = tcDao.getTribusListClassByUserId(u.getUserId());
		mv.addObject("list", l);
		
		mv.addObject("markWatch", markWatch);
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		mv.addObject("singlePageReviews", singlePageReviews);
		mv.addObject("singlePageMain", singlePageMain);
		mv.addObject("SinglePageFriendsRecord", friendRecords);
		
		mv.addObject("myRate", mmd.getGradeByMusicIdAndUseId(musicId, userId));
		return mv;
	}
	
	@RequestMapping("get/{id}")
	public ModelAndView book(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("id")int id					
	) {
		ModelAndView mv = new ModelAndView();
		BookDao bd = new BookDao();							
		Book old = bd.getBookById(id);		
		
		mv.addObject("book",old);
		mv.setViewName("book");		
		//mv.addObject("stars", m.getStars());
		return mv;		
	}
	
	
	@RequestMapping("home")
	public ModelAndView music(HttpServletRequest req, HttpServletResponse res					
	) {
		String id = req.getParameter("id");
		ModelAndView mv = new ModelAndView();
		MusicDao bd = new MusicDao();							
		List<Music> l = bd.getMusicByCondition(new Music());						
		mv.addObject("musics",l);
		
		MusicCommentDao md = new MusicCommentDao();
		List<MusicComment> l_c = md.getMusicCommentsByCondition(new MusicComment());
		
		StarringDao sd = new StarringDao();
		Starring s = sd.getStarringById(Integer.parseInt(id));
		
		mv.setViewName("home");
		mv.addObject("musics", l);
		mv.addObject("musicComments", l_c);
		mv.addObject("star", s);
		
		return mv;		
	}
	
	@RequestMapping("searchByTag/{musicTag}")
	public ModelAndView searchMusicByTag(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("musicTag")String musicTag){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_movie");
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
//		Movie m = md.getMovieById(movieId);
		List<Music> musicsByTag = md.searchMusicByTag(musicTag);
		Iterator<Music> iterator = musicsByTag.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Music m = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMusicId());
			sr.setItemPic(m.getMusicPic());
			sr.setItemName(m.getMusicName().length()>15? (m.getMusicName().subSequence(0, 15)+"..."): m.getMusicName());
			if(m.getMusicName()!=null){
				//sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
				if(m.getMusicPublishDate()!=null)
					sr.setItemDate(m.getMusicPublishDate().toString());
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMusicId()));
			
			searchResults.add(sr);
		}
		
		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/music/searchByTag/"+musicTag+".action",page);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchName", musicTag);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
}
