package com.lbr;

/**
 * Response of the ArticleController
 * @author lbr
 *
 */
public class ArticleResponse {
	
	/**
	 * Total size in enter
	 */
	public Integer totalSize;
	
	/**
	 * Theoretical number of boxes
	 */
	public Integer numberOfBoxesPlanned;
	
	/**
	 * List of all the boxes - String representation
	 */
	public String packing;
	
	/**
	 * An error occured
	 */
	public Boolean error;
	
	/**
	 * Constructor
	 * @param totalSize total size
	 * @param numberOfBoxesOptimized number of boxes optimized
	 * @param packing the final solution
	 * @param error if there was an error
	 */
	public ArticleResponse(Integer totalSize, Integer numberOfBoxesPlanned, String packing, Boolean error) {
		super();
		this.totalSize = totalSize;
		this.numberOfBoxesPlanned = numberOfBoxesPlanned;
		this.packing = packing;
		this.error = error;
	}
	
}
