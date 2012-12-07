package dao;

import hibernate.TribusHibernateSessionFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.ToUnicode;

import model.Movie;
import model.MovieMark;
import model.MovieTag;
import model.Music;
import model.MusicTag;
import model.Singer;
import model.Starring;

import au.com.bytecode.opencsv.CSVReader;


public class DataDao {
	public void processTribusMusicData(){
		MusicDao md = new MusicDao();
		MusicTag mt;
		Set<MusicTag> musicTags = null;
		MusicTagDao mtd = new MusicTagDao();
		SingerDao sd= new SingerDao();
		Music m = null;
		CSVReader reader = null;
		try{
			reader = new CSVReader(new FileReader("D:\\tribus_data\\Music\\Rock.csv"));
		} catch (FileNotFoundException e){
			e.printStackTrace(); 
		}
	    String[] nextLine = null;  
	    try {
	    	int count=0;
	    	for(int i=0; i<9; i++){
	    		nextLine = reader.readNext();
	    	}
	        while ((nextLine = reader.readNext()) != null) { 
	        	//count++;
/*	        	if(count==10)
	        		break;*/
	        	m = md.getMusicByName(nextLine[0]);
	        	
	        	if(m==null){
	        		count = 1;
	        		m = new Music();
	        		musicTags = new HashSet<MusicTag>();
	        	}else{
	        		musicTags = m.getTags();
	        	}
	        	mt = mtd.getMusicTagByName("Rock");
	        	musicTags.add(mt);
	        	m.setTags(musicTags);
		        for(int i = 0; i < nextLine.length; i++) {
		        	if(i==0){
		        		m.setMusicName(nextLine[i]);
		        	}
		        	if(i==1){
	        			Singer s = sd.getSingerByName(nextLine[i]);
	        			if(s!=null){
	        				
	        			}
	        			else{
	        				s = new Singer();
	        				s.setSingerName(nextLine[i]);
	        				sd.save(s);
	        			}
		        		m.setSinger(s);
		        	}
		        	if(i==2){
		        		String path = null;
		        		if(nextLine[2].indexOf("/images/I/")!=-1){
		        			path = "http://www.gotribus.com/view/pic/music/rock/"+
		        			nextLine[2].split("/images/I/")[1];
		        		}else{
		        			path = "http://www.gotribus.com/view/pic/music/rock/"+
		        			nextLine[2].split("nav2/dp/")[1];
		        		}
		        		m.setMusicPic(path);
		        		System.out.println(path);
		        	}
/*		            if(i==3 && nextLine[4].length()==0){
		            	String musicTrack[] = ToUnicode.toUnicode(nextLine[3],false).split(" ");
		            	for(int j =0; j<musicTrack.length; j++){
		            		System.out.println(musicTrack[j]);
		            	}
		            	m.setMusicBrief(nextLine[3]);
		            }
		            if(i==3 && nextLine[4].length()!=0){
		            	m.setMusicBrief("Dic1:"+nextLine[3]+"\n"+"Dic2:"+nextLine[4]);
		            }*/
		            if(i==5 && nextLine[5].length()>8){
		            	System.out.println(nextLine[5].split("\\, ")[0]);
		            	String dat = ToUnicode.toUnicode(nextLine[5].split("\\, ")[0], false).split("\\ ")[3];
		            	String mon = ToUnicode.toUnicode(nextLine[5].split("\\, ")[0], false).split("\\ ")[2];
		            	String yea = nextLine[5].split("\\, ")[1];
		            	Date date = new Date();
		            	date.setDate(Integer.parseInt(dat));
		            	date.setMonth(this.parseMonth(mon)); 
		            	date.setYear(Integer.parseInt(yea)-1900);
		            	m.setMusicPublishDate(date); 
		            	System.out.println(date);
		            }
		        } 
		        if(count==1){
		        	md.save(m);
		        	count = 0;
		        }else{
		        	md.update(m);
		        }
		        	
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  		
	}
	public void processTribusMovieData(){
		MovieDao md = new MovieDao();
		MovieTag mt;
		MovieTagDao mtd = new MovieTagDao();
		StarringDao sd= new StarringDao();
		StarTypeDao std = new StarTypeDao();
		Movie m = null;
		CSVReader reader = null;
		try{
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Romance P1.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据romance p2+comedy.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Action.csv"));
			reader = new CSVReader(new FileReader("D:\\tribus_data\\Adventure等N个类别.csv"));
		} catch (FileNotFoundException e){
			e.printStackTrace(); 
		}
	    String[] nextLine = null;  
	    try {  
	    	int count=0;
	    	for(int i=0; i<6694; i++){
	    		nextLine = reader.readNext();
	    	}
	    	//nextLine = reader.readNext();
	        while ((nextLine = reader.readNext()) != null) { 
//	        	System.out.println(nextLine[8]);
/*	        	String test[] = nextLine[8].split("\\| ");
	        	for(int i=0 ; i< test.length; i++){
	        		System.out.println(test[i]);
	        	}*/
/*	        	count++;
	        	if(count==100)
	        		break;*/
	        	if(nextLine[1].length()==0){
	        		continue;
	        	}
	        	m = md.getMovieByNameOriginal(nextLine[0]);
	        	if(m==null){
	        		m = new Movie();
	        		count=1;
	        	}
	        		
	        	Set<Starring> stars = new HashSet<Starring>();
		        for(int i = 0; i < nextLine.length; i++){
		        	if(i==0){
		        		
		        		m.setMovieNameOriginal(nextLine[i]);
		        	}
		        	if(i==1){
		        		String path = "http://www.gotribus.com/view/pic/movie/"
		        			+nextLine[i].substring(4,6).toLowerCase()+"/"
		        			+nextLine[i].substring(6,8).toLowerCase()+"/"+nextLine[i];
		        		m.setMoviePic(path);
		        		//System.out.println(path);
		        	}
		        	if(i==2){
		        		m.setMovieBrief(nextLine[i]);
		        	}
		            if(i==3 && nextLine[3].length()!=0){
		            	
		            	//System.out.println(nextLine[i].replaceAll("\\ and", "\\,"));
		        		String directors[] = nextLine[i].replaceAll(" and", ",").split("\\, ");
		        		//System.out.println(directors.length);
		        		for(int j=0; j<directors.length; j++){
		        			
		        			Starring s = sd.getStarringByName(directors[j]);
		        			if(s!=null){
		        				stars.add(s);
		        			}
		        			else{
		        				Starring newStar = new Starring();
		        				newStar.setStarName(directors[j]);
		        				newStar.setStarType(std.getStarTypeById(1));
		        				sd.save(newStar);
		        				stars.add(newStar);
		        			}
		        		}
		            }

		            if(i==4 && nextLine[4].length()!=0){
		            	String actors[] = nextLine[i].replaceAll(" and", ",").split("\\, ");
		        		for(int j=0; j<actors.length; j++){
		        			//System.out.println(actors[j]);
		        			Starring s = sd.getStarringByName(actors[j]);
		        			if(s!=null){
		        				stars.add(s);
		        			}
		        			else{
		        				Starring newStar = new Starring();
		        				newStar.setStarName(actors[j]);
		        				newStar.setStarType(std.getStarTypeById(2));
		        				sd.save(newStar);
		        				stars.add(newStar);
		        			}
		        		}
		        		m.setStars(stars);
		            }
		            if(i==5){
		            	m.setMovieRegion(nextLine[i]);
		            }
		            if(i==6 && nextLine[i].length()!=0){
		            	String theDate[];
		            	if(nextLine[6].split("\\(")[0].split(" ").length==1){
		            		System.out.println("111111111");
		            		theDate = ("5 March "+nextLine[i].split("\\(")[0]).split(" ");
		            		System.out.println(theDate[0]+"*"+theDate[1]+"*"+theDate[2]);
		            	}else if(nextLine[6].split("\\(")[0].split(" ").length==2){
		            		System.out.println("222222222");
		            		theDate = ("5 "+nextLine[i]).split(" ");
		            		System.out.println(theDate[0]+"*"+theDate[1]+"*"+theDate[2]);
		            	}else{
		            		System.out.println("333333333");
		            		theDate = nextLine[i].split(" ");
		            		System.out.println(theDate[0]+"*"+theDate[1]+"*"+theDate[2]);
		            	}
		            	Date date = new Date();
		            	date.setDate(Integer.parseInt(theDate[0]));
		            	date.setMonth(this.parseMonth(theDate[1])); 
		            	//System.out.println(theDate[2].split("\\(")[0]);
		            	date.setYear(Integer.parseInt(theDate[2].split("\\(")[0])-1900);
		            	//date.setYear(Integer.parseInt("1988")); //theDate[2].split("(")[0])
		            	m.setMovieDate(date);
		            }
		            if(i==8){
		            	Set<MovieTag> movieTags = new HashSet<MovieTag>();
			    		String tags[] = nextLine[8].split("\\| ");
			    		boolean in = false;
			    		for(int j=0; j<tags.length; j++){
			    			if(this.isMainClassification(tags[j])){
			    				in = true;
			    			}
			    			if(tags[j].length()==0){
			    				continue;
			    			}
			    			mt = mtd.getMovieTagByName(tags[j]);
			    			if(mt!=null){
			    				movieTags.add(mt);
			    			}
			    			else{
			    				mt = new MovieTag();
			    				mt.setTagName(tags[i]);
			    				mtd.save(mt);
			    				movieTags.add(mt);
			    			}
			    		}
			    		if(!in){
			    			MovieTag mt_other = mtd.getMovieTagByName("Others");
			    			movieTags.add(mt_other);
			    		}
			    		m.setTags(movieTags);
		            }
		            if(i==9){
		            	m.setMovieRating(nextLine[i]);
		            }
		        } 
		        if(count==1){
		        	md.save(m);
		        	count = 0;
		        }
		        else{
		        	md.update(m);
		        }
		        	
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  		
	}
	
	public void encodingTest(){
		MovieDao md = new MovieDao();
		MovieTag mt;
		MovieTagDao mtd = new MovieTagDao();
		StarringDao sd= new StarringDao();
		StarTypeDao std = new StarTypeDao();
		Movie m = null;
		CSVReader reader = null;
		try{
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Romance P1.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据romance p2+comedy.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Action.csv"));
			reader = new CSVReader(new FileReader("D:\\tribus_data\\Adventure等N个类别.csv"));
		} catch (FileNotFoundException e){
			e.printStackTrace(); 
		}
	    String[] nextLine = null;  
	    try {  
	    	int count=0;
	        while ((nextLine = reader.readNext()) != null) { 
	        	count++;
	        	if(count==30)
	        		break;
	        	System.out.println(nextLine[0]);
		        //System.out.println(ToUnicode.toUnicode(nextLine[0], true));
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  		
	}
	
	public boolean isMainClassification(String s){
		boolean result = false;
		if(s.equals("Action")){
			result=true;
		}
		if(s.equals("Adventure")){
			result=true;
		}
		if(s.equals("Animation")){
			result=true;
		}
		if(s.equals("War")){
			result=true;
		}
		if(s.equals("Comedy")){
			result=true;
		}
		if(s.equals("Horror")){
			result=true;
		}
		if(s.equals("Mystery")){
			result=true;
		}
		if(s.equals("Romance")){
			result=true;
		}
		if(s.equals("Thriller")){
			result=true;
		}
		return result;
	}
	
	public int parseMonth(String s){
		int mon=0;
		if(s.equals("January")){
			mon=0;
		}
		if(s.equals("February")){
			mon=1;
		}
		if(s.equals("March")){
			mon=2;
		}
		if(s.equals("April")){
			mon=3;
		}
		if(s.equals("May")){
			mon=4;
		}
		if(s.equals("June")){
			mon=5;
		}
		if(s.equals("July")){
			mon=6;
		}
		if(s.equals("August")){
			mon=7;
		}
		if(s.equals("September")){
			mon=8;
		}
		if(s.equals("October")){
			mon=9;
		}
		if(s.equals("November")){
			mon=10;
		}
		if(s.equals("December")){
			mon=11;
		}
		return mon;
	}
	
	public void getClassification(){
		MovieTag mt;
		MovieTagDao mtd = new MovieTagDao();
		Set<MovieTag> movieTags = new HashSet<MovieTag>();
		CSVReader reader = null;
		try{
			reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Romance P1.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据Action.csv"));
			//reader = new CSVReader(new FileReader("D:\\tribus_data\\电影下载10万数据romance p2+comedy.csv"));
		} catch (FileNotFoundException e){
			e.printStackTrace(); 
		}
	    String[] nextLine = null;  
	    try {  
	    	int count=0;
	    	nextLine = reader.readNext();
	        while ((nextLine = reader.readNext()) != null) { 
/*	        	count++;
	        	if(count==1000)
	        		break;*/
	        	if(nextLine[8].length()==0){
	        		continue;
	        	}
	    		String tags[] = nextLine[8].split("\\| ");
	    		for(int i=0; i<tags.length; i++){
	    			if(tags[i].length()==0){
	    				continue;
	    			}
	    			mt = mtd.getMovieTagByName(tags[i]);
	    			if(mt!=null){
	    				//movieTags.add(mt);
	    			}
	    			else{
	    				mt = new MovieTag();
	    				mt.setTagName(tags[i]);
	    				mtd.save(mt);
	    				//movieTags.add(mt);
	    			}
	    		}
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}

	private static String PREFIX = "\\u";
	
	private static char ascii2Char(String str) {      
        if (str.length() != 6) {      
            throw new IllegalArgumentException(      
                    "Ascii string of a native character must be 6 character.");      
        }      
        if (!PREFIX.equals(str.substring(0, 2))) {      
            throw new IllegalArgumentException(      
                    "Ascii string of a native character must start with \"\\u\".");      
        }      
        String tmp = str.substring(2, 4);      
        int code = Integer.parseInt(tmp, 16) << 8;      
        tmp = str.substring(4, 6);      
        code += Integer.parseInt(tmp, 16);      
        return (char) code;      
    } 
	
    public static String ascii2Native(String str) {      
        StringBuilder sb = new StringBuilder();      
        int begin = 0;      
        int index = str.indexOf(PREFIX);      
        while (index != -1) {      
            sb.append(str.substring(begin, index));      
            sb.append(ascii2Char(str.substring(index, index + 6)));      
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }
        sb.append(str.substring(begin));      
        return sb.toString();
    }
    
	public List<Movie> HTMLCode2ASCII(){
    	StringEscapeUtils na = new StringEscapeUtils();
		String hql ="select m from Movie m where lower(m.movieNameOriginal) like :name";
		List<Movie> movies = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			 movies = session.createQuery(hql).setString("name", "%?%").list();
/*			Iterator<Movie> it = movies.iterator();
			while(it.hasNext()){
				Movie nm = new Movie();
				nm.setMovieNameOriginal(na.escapeHtml4(it.next().getMovieNameOriginal()));
				session.update(nm);
			}*/
			 for(int i=0; i<movies.size(); i++){
				 Movie m = new Movie();
				 m =  movies.get(i);
				 m.setMovieNameOriginal(m.getMovieNameOriginal().replace("?", "e"));
				 //System.out.println();
			 }
			tx.commit();
		} catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		return movies;	
    }
    
    @SuppressWarnings("static-access")
	public List<Movie> HTMLCode2ASCII2(){
    	StringEscapeUtils na = new StringEscapeUtils();
		String hql ="select m from Movie m where lower(m.movieNameOriginal) like :name";
		List<Movie> movies = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 movies = session.createQuery(hql).setString("name", "%?%").list();
/*			Iterator<Movie> it = movies.iterator();
			while(it.hasNext()){
				Movie nm = new Movie();
				nm.setMovieNameOriginal(na.escapeHtml4(it.next().getMovieNameOriginal()));
				session.update(nm);
			}*/
		} catch (Exception e){
			e.printStackTrace();
		}
		return movies;	
    }
    
	public static void main(String args[]){
		DataDao dd = new DataDao();
		//StringEscapeUtils na = new StringEscapeUtils();
		//String str1 = na.unescapeJava("&#xE4;");
		//System.out.println(na.unescapeHtml4("ddd&#xE4;fffge&#x26;er3t"));
		//dd.processTribusMusicData();
		//dd.processTribusMovieData();
		//dd.getClassification();
		System.out.println(dd.HTMLCode2ASCII().size());
	}
}
