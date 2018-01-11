package com.lbr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * Service responsible to manage  articles
 * @author lbr
 *
 */
@Service
public class ArticleService {

	/** Logger of information **/
	private Logger LOGGER = Logger.getLogger(ArticleService.class.getName());
	
	/**
	 * Max number of articles
	 */
	private static final Integer MAX_NUMBER_OF_ARTICLES = 10;
	
	/**
	 * Main method in charge to optimize the list
	 * @param articlesSizes articles number (ex: 163841689525773)
	 * @return optimized string list
	 */
	public ArticleResponse optimize(String articlesSizes){
		
		LOGGER.info("Process start");
		
		//Get a Integer list with the entered number
		List<Integer> list = null;
		try {
			list = stringToDecreasingSortedList(articlesSizes);
		} catch (NumberFormatException nfe) {
			LOGGER.info(nfe.getMessage());
			return new ArticleResponse(null, null, articlesSizes, Boolean.TRUE); //Error response if alpha in the String
		}
				
		//Total size regarding the String entered
		AtomicInteger totalSize = new AtomicInteger(0);
		
		//Transformed original Integer list into an Article list 
		List<Article> articlesList = new ArrayList<Article>();
		
		//Future Index/Id of the Article in the list
		AtomicInteger idx = new AtomicInteger(0);
		
		//Prepare an object list
		list.forEach(articleSize -> {
			totalSize.getAndAdd(articleSize);
			articlesList.add(new Article(idx.getAndIncrement(), articleSize));
		});
		
		LOGGER.info("Total articles : " + totalSize);
		
		//Prepare the final String and indicate separator (ex: 73/64/622)
		StringJoiner finalListOfBoxes = new StringJoiner("/");
				
		//=========PROCESS START==========>
		articlesList
			.stream()
			.filter(p -> !p.getProcessed()) //Not processed Article
			.forEach(article -> {
				
				LOGGER.info("Current article: " + article);
				article.setProcessed(Boolean.TRUE);//We directly set it as processed
				
				//Pre-init reference variables
				AtomicInteger totalInBox = new AtomicInteger(article.getSize()); // Current total size value in the current processed box
				StringBuilder box = new StringBuilder(String.valueOf(totalInBox)); // Current box string value
				// The max. authorized value we can add in the current box regarding the current article - ex : For 7, it's 3.
				final Integer maxCandidateValue = MAX_NUMBER_OF_ARTICLES - totalInBox.get(); 
			
				articlesList
					.stream()
					.filter(p -> 
						p.getSize() <= maxCandidateValue && //Only get authorized values
						!p.getProcessed()) //And not processed
					.collect(Collectors.toList()).forEach(filteredArticle -> {
						if(totalInBox.get() + filteredArticle.getSize() <= MAX_NUMBER_OF_ARTICLES){//We always check, we still respect the max value
							totalInBox.getAndAdd(filteredArticle.getSize());//We add the size in the current total
							box.append(filteredArticle.getSize());//We add the Article representation
							articlesList.get(filteredArticle.getIndex()).setProcessed(Boolean.TRUE); //Ok, this one is processed
							LOGGER.info("Candidate: " + filteredArticle);
							LOGGER.info("Size: " + filteredArticle.getSize());
						} else {
							return; //We are outside the max, we can stop the loop
						}
					});

				//Save the calculated box (ex : 64)
				finalListOfBoxes.add(box);
				
				LOGGER.info("Box: " + box);
			
		});
		//<=========PROCESS END==========
		
		Integer numberOfBoxesPlanned = totalSize.get() / MAX_NUMBER_OF_ARTICLES + 1;
		
		LOGGER.info("Process end");
				
		return new ArticleResponse(
				totalSize.get(), //Original total size
				numberOfBoxesPlanned, //Theoretical number of boxes
				finalListOfBoxes.toString(), //The solution, boxes and articles repartition
				Boolean.FALSE); //No error
	}

	/**
	 * Take the entered String and transform it into an Integer list sorted by decreasing order
	 * 775651321 => 7,7,6,5,5,3,2,1,1
	 * 
	 * @param articlesSizes - original String - ex: 775651321
	 * @return the Integer list sorted by decrease order - ex: 7,7,6,5,5,3,2,1,1
	 */
	private List<Integer> stringToDecreasingSortedList(String articlesSizes) {
		List<Integer> list = Arrays
				.stream(articlesSizes.split(""))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		Collections.sort(list, (a,b)->a.compareTo(b));
		Collections.reverse(list);
		return list;
	}
	
}
