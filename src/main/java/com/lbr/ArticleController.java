package com.lbr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping(value = "/articles")
public class ArticleController {
 
	/**
	 * Article service
	 */
	@Autowired
	private ArticleService articleService;
	
	/**
	 * Not Restful - Optimize action
	 * Should be a POST as it's not strictly related to an "Article" object
	 * 
	 * ex : 46546455 => 64 64 55 54
	 * 163841689525773 => 91/82/81/73/73/64/6/55
	 * 
	 * @param articlesNumber the list of articles
	 * @return the string list optimized
	 */
	@RequestMapping(value = "/optimize/{articlesNumber}", method = RequestMethod.GET)
	public ArticleResponse optimize(@PathVariable("articlesNumber") String articlesNumber) {
		ArticleResponse response = articleService.optimize(articlesNumber);
		return response;//JSON
 
	}
	
	//STUB METHODS / STANDARD API =============
	@RequestMapping(method = RequestMethod.GET)
	public String getArticles() {
		return "The articles list";
	}
 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") Long id) {
		return "Article with id: " + String.valueOf(id);
	}
	
}
