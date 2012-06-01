package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import model.Book;
import model.BookTag;
import model.Movie;
import model.MovieTag;
import model.Music;
import model.MusicTrack;
import model.Singer;
import model.StarType;
import model.Starring;

import dao.BookDao;
import dao.BookTagDao;
import dao.MovieDao;
import dao.MovieTagDao;
import dao.MusicDao;
import dao.MusicTrackDao;
import dao.SingerDao;
import dao.StarTypeDao;
import dao.StarringDao;

public class ManageTables {
	public void saveBook() {
		BufferedReader reader = null;
		BookDao bd = new BookDao();
		BookTagDao btd = new BookTagDao();
		Hashtable<String, Integer> alltags = new Hashtable<String, Integer>();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetBook_Final\\OutCome\\bookInfo\\FilmInfo2.txt"));
			String currentLine;

			// bd.save(b);
			// System.out.println("1");
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("NO.") != -1) {
					Book b = new Book();
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							b.setBookName(currentLine.split("title: ")[1]
									.split(" by ")[0]);
							b.setBookAuthor(currentLine.split("title: ")[1]
									.split(" by ")[1]);
						}
						if (currentLine.indexOf("picURL:") != -1) {
							b.setBookPic(currentLine.split("\"")[1]);
						}
						if (currentLine.indexOf("subjects:") != -1) {
							String tags[] = currentLine.split(":")[1]
									.split("&&");
							Set<BookTag> bookTags = new HashSet<BookTag>();
							for (int i = 0; i < tags.length; i++) {
								System.out.println(tags[i]);
								BookTag bt = btd.getBookTagByName(tags[i]);
								bookTags.add(bt);
							}
							b.setTags(bookTags);

						}
						if (currentLine.indexOf("ISBN:") != -1) {
							b.setBookISBN(currentLine.split("ISBN: ")[1]);
						}
						if (currentLine.indexOf("Publisher:") != -1) {
							b.setBookPublisher(currentLine.split("Publisher:")[1]);
						}
						if (currentLine.indexOf("releaseDate:") != -1) {
							b.setBookPublishDate(currentLine
									.split("releaseDate:")[1]);
						}
						if (currentLine.indexOf("Pages:") != -1) {
							b.setBookPages(Integer.parseInt(currentLine
									.split("Pages:")[1]));
						}
						if (currentLine.indexOf("listPrice: ") != -1) {
							b.setBookPrice(Double.parseDouble(currentLine
									.split("listPrice: ")[1]));
							bd.save(b);
							break;
						}
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveBookTag() {
		BufferedReader reader = null;
		BookTagDao btd = new BookTagDao();
		Hashtable<String, Integer> alltags = new Hashtable<String, Integer>();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetBook_Final\\OutCome\\bookInfo\\FilmInfo2.txt"));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("subjects:") != -1) {

					String tags[] = currentLine.split(":")[1].split("&&");
					for (int i = 0; i < tags.length; i++) {
						if (!btd.isExist(tags[i])) {
							// alltags.put(tags[i], 1);
							System.out.println(tags[i]);
							BookTag bt = new BookTag();
							bt.setTagName(tags[i]);
							btd.save(bt);
						}
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveMusic() {
		BufferedReader reader = null;
		MusicDao md = new MusicDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMusic_Final\\OutCome\\MusicInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Music m = new Music();
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							m.setMusicName(currentLine.split("title:")[1]);
						}
						if (currentLine.indexOf("picURL:") != -1) {
							m.setMusicPic(currentLine.split("picURL:")[1]);
						}
						if (currentLine.indexOf("performer:") != -1) {
							String name = currentLine.split("performer:")[1];
							SingerDao sd = new SingerDao();
							Singer s = sd.getSingerByName(name);
							if (s == null) {
								s = new Singer();
								s.setSingerName(name);
							}
							m.setSinger(s);
						}
						if (currentLine.indexOf("releaseDate:") != -1) {
							m.setMusicPublishDate(currentLine
									.split("releaseDate:")[1]);
						}
						if (currentLine.indexOf("musicDescription:") != -1) {
							m.setMusicBrief(currentLine
									.split("musicDescription:")[1]
									.split("<p style=\"font-size:13px;\">")[1]);
						}
						if (currentLine.indexOf("NO.") != -1) {
							md.save(m);
							break;
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateMusic() {
		BufferedReader reader = null;
		MusicDao md = new MusicDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMusic_Final\\OutCome\\MusicInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Music m = new Music();
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							System.out.println(currentLine.split("title:")[1]);
							m = md.getMusicByName(currentLine.split("title:")[1]);
						}
						if (currentLine.indexOf("picURL:") != -1) {
							System.out.println(currentLine.split("picURL:")[1]);
							m.setMusicPic(currentLine.split("picURL:")[1]);
						}
						if (currentLine.indexOf("NO.") != -1) {
							md.update(m);
							break;
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveMusicTracks() {
		BufferedReader reader = null;
		MusicTrackDao mtd = new MusicTrackDao();
		MusicDao md = new MusicDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMusic_Final\\OutCome\\MusicInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					String musicName = null;
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							musicName = currentLine.split("title:")[1];
						}
						if (currentLine.indexOf("tracks:") != -1) {
							String tracks[] = currentLine.split("tracks:")[1]
									.split("</p>")[0].split("<br>");
							Music m = md.getMusicByName(musicName);
							System.out.println(m.getMusicName());
							for (int i = 1; i < tracks.length; i++) {
								MusicTrack mt = new MusicTrack();
								mt.setMusic(m);
								mt.setTrackName(tracks[i]);
								mtd.save(mt);

							}
						}
						if (currentLine.indexOf("NO.") != -1) {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveSinger() {
		BufferedReader reader = null;
		SingerDao sd = new SingerDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMusic_Final\\OutCome\\MusicInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("performer:") != -1) {
					String singerName = currentLine.split("performer:")[1];
					Singer s = sd.getSingerByName(singerName);
					if (s == null) {
						s = new Singer();
						s.setSingerName(singerName);
					}
					sd.save(s);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveMovieTag() {
		BufferedReader reader = null;
		MovieTagDao mtd = new MovieTagDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();

			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("genre:") != -1) {
					String movieTags[] = currentLine.split("genre:")[1]
							.split(", ");
					for (int i = 0; i < movieTags.length; i++) {
						MovieTag mt = null;
						if (movieTags[i].length() < 30) {
							if ((mt = mtd.getMovieTagByName(movieTags[i])) == null) {
								mt = new MovieTag();
								mt.setTagName(movieTags[i]);
								mtd.save(mt);
							}
						}
					}
					movieTags = null;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveCast() {
		BufferedReader reader = null;
		StarringDao sd = new StarringDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();

			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("cast:") != -1) {
					String movieStars[] = currentLine.split("cast:")[1]
							.split(", ");
					for (int i = 0; i < movieStars.length; i++) {
						if (sd.getStarringByName(movieStars[i]) == null) {
							Starring s = new Starring();
							s.setStarName(movieStars[i]);
							sd.save(s);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveDirector() {
		BufferedReader reader = null;
		StarringDao sd = new StarringDao();
		StarTypeDao std = new StarTypeDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();

			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("director:") != -1) {
					String movieStars[] = currentLine.split("director:")[1]
							.split(", ");
					for (int i = 0; i < movieStars.length; i++) {
						if (sd.getStarringByName(movieStars[i]) == null) {
							Starring s = new Starring();
							StarType st = std.getStarTypeById(1);
							s.setStarType(st);
							s.setStarName(movieStars[i]);
							sd.save(s);
						} else {
							Starring s = sd.getStarringByName(movieStars[i]);
							StarType st = std.getStarTypeById(3);
							s.setStarType(st);
							sd.update(s);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveMovie() {
		BufferedReader reader = null;
		MovieDao md = new MovieDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Movie m = new Movie();
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							String movieNameOriginal = currentLine
									.split("title:")[1];
							if (md.getMovieByNameOriginal(movieNameOriginal) != null) {
								break;
							}
							m.setMovieNameOriginal(currentLine.split("title:")[1]);
						}
						if (currentLine.indexOf("rating:") != -1) {
							m.setMovieRating(currentLine.split("rating:")[1]);
						}
						if (currentLine.indexOf("picURL:") != -1) {
							m.setMoviePic(currentLine.split("picURL:")[1]);
						}
						if (currentLine.indexOf("brief:") != -1) {
							m.setMovieBrief(currentLine.split("brief:")[1]);
						}
						if (currentLine.indexOf("NO.") != -1) {
							md.save(m);
							break;
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//has some problem
	public void saveMovieMovieTag() { 
		Hashtable<String, Integer> allMovies = new Hashtable<String, Integer>();
		BufferedReader reader = null;
		MovieDao md = new MovieDao();
		MovieTagDao mtd = new MovieTagDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Movie m = new Movie();
					String movieNameOriginal;
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							movieNameOriginal = currentLine.split("title:")[1];
							if (allMovies.containsKey(movieNameOriginal)) {
								break;
							}
							allMovies.put(movieNameOriginal, 1);
							m = md.getMovieByNameOriginal(movieNameOriginal);
							Set<MovieTag> tags = new HashSet<MovieTag>();
							while ((currentLine = reader.readLine()) != null) {
								if (currentLine.indexOf("genre:") != -1) {
									String movieTags[] = currentLine.split("genre:")[1].split(", ");
									for (int i = 0; i < movieTags.length; i++) {
										if (movieTags[i].length() < 30) {
											MovieTag mt = mtd.getMovieTagByName(movieTags[i]);
											if(mt!=null)
												tags.add(mt);
										}
									}
									if(m.getMovieId()!=9389)
										m.setTags(tags);
									break;
								}
								if(currentLine.indexOf("NO.") != -1){
									md.update(m);
									break;
								}
							}
						}
/*						if (currentLine.indexOf("NO.") != -1) {
							
							break;
						}*/
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveMovieMovieStarring() { 
		Hashtable<String, Integer> allMovies = new Hashtable<String, Integer>();
		BufferedReader reader = null;
		MovieDao md = new MovieDao();
		StarringDao sd = new StarringDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Movie m = new Movie();
					String movieNameOriginal;
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							movieNameOriginal = currentLine.split("title:")[1];
							if (allMovies.containsKey(movieNameOriginal)) {
								break;
							}
							allMovies.put(movieNameOriginal, 1);
							m = md.getMovieByNameOriginal(movieNameOriginal);
							Set<Starring> stars = new HashSet<Starring>();
							while ((currentLine = reader.readLine()) != null) {
								if (currentLine.indexOf("cast:") != -1) {
									String movieStars[] = currentLine.split("cast:")[1].split(", ");
									for (int i = 0; i < movieStars.length; i++) {
										Starring s = sd.getStarringByName(movieStars[i]);
										if(s!=null)
											stars.add(s);
									}
									m.setStars(stars);
									md.update(m);
									break;
								}
								if(currentLine.indexOf("NO.") != -1){
									break;
								}
							}
						}
						if (currentLine.indexOf("NO.") != -1) {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveMoiveDate() { 
		Hashtable<String, Integer> allMovies = new Hashtable<String, Integer>();
		BufferedReader reader = null;
		MovieDao md = new MovieDao();
		try {
			reader = new BufferedReader(
					new FileReader(
							"C:\\Users\\Leon\\workspace\\thibusCrawler_GetMovie_test\\OutCome\\FilmInfo.txt"));
			String currentLine;
			currentLine = reader.readLine();
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.indexOf("webID:") != -1) {
					Movie m = new Movie();
					String movieNameOriginal;
					while ((currentLine = reader.readLine()) != null) {
						if (currentLine.indexOf("title:") != -1) {
							movieNameOriginal = currentLine.split("title:")[1];
							if (allMovies.containsKey(movieNameOriginal)) {
								break;
							}
							allMovies.put(movieNameOriginal, 1);
							m = md.getMovieByNameOriginal(movieNameOriginal);
							while ((currentLine = reader.readLine()) != null) {
								if (currentLine.indexOf("date_release:") != -1) {
									String releaseDate = currentLine.split("date_release:")[1];
									String dates[] = releaseDate.split(" ");
									dates[0] = this.getMonth(dates[0]);
									dates[1] = this.getDay(dates[1]);
									String formatedDate = dates[2]+"-"+dates[0]+"-"+dates[1];
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date date = null;
									try {
										date = sdf.parse(formatedDate);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									m.setMovieDate(date);
									md.update(m);
									break;
								}
								if(currentLine.indexOf("NO.") != -1){
									break;
								}
							}
						}
						if (currentLine.indexOf("NO.") != -1) {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMonth(String month){
		String getMonth = null;
		if(month.equals("January")){
			getMonth = "01";
		}else if(month.equals("February")){
			getMonth = "02";
		}else if(month.equals("March")){
			getMonth = "03";
		}else if(month.equals("April")){
			getMonth = "04";
		}else if(month.equals("May")){
			getMonth = "05";
		}else if(month.equals("June")){
			getMonth = "06";
		}else if(month.equals("July")){
			getMonth = "07";
		}else if(month.equals("August")){
			getMonth = "08";
		}else if(month.equals("September")){
			getMonth = "09";
		}else if(month.equals("October")){
			getMonth = "10";
		}else if(month.equals("November")){
			getMonth = "11";
		}else if(month.equals("December")){
			getMonth = "12";
		}
		return getMonth;
	}
	
	public String getDay(String day){
		String tmpDay = day.split(",")[0];
		String getDay = tmpDay;
		if(tmpDay.equals("1")){
			getDay = "01";
		}else if(day.equals("2")){
			getDay = "02";
		}else if(day.equals("3")){
			getDay = "03";
		}else if(day.equals("4")){
			getDay = "04";
		}else if(day.equals("5")){
			getDay = "05";
		}else if(day.equals("6")){
			getDay = "06";
		}else if(day.equals("7")){
			getDay = "07";
		}else if(day.equals("8")){
			getDay = "08";
		}else if(day.equals("9")){
			getDay = "09";
		}
		return getDay;
	}
	public static void main(String args[]) {
		ManageTables mt = new ManageTables();
		// mt.saveBook();
		// mt.saveBookTag();
		// mt.saveMusic();
		// mt.saveSinger();
		// mt.saveMusicTracks();
		// mt.saveMovieTag();
		// mt.saveCast();
		// mt.saveDirector();
		//mt.saveMovie();
		//mt.saveMovieMovieTag();
		//mt.saveMovieMovieStarring();
		//mt.saveMoiveDate();
		mt.updateMusic();
	}
}
