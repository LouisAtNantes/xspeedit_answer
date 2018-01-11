package com.lbr;

/**
 * Object representing an article
 * @author lbr
 *
 */
public class Article {

	/**
	 * Index of the article
	 */
	private Integer index;

	/**
	 * Size of the article
	 */
	private Integer size;
	
	/**
	 * Article has already been processed
	 */
	private Boolean processed = Boolean.FALSE;
	
	/**
	 * Constructor
	 * @param size the size of the article
	 */
	public Article(Integer index, Integer size) {
		super();
		this.index = index;
		this.size = size;
	}

	/**
	 * Size getter
	 * @return size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Size setter
	 * @param size the size of the article
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Processed getter
	 * @return processed
	 */
	public Boolean getProcessed() {
		return processed;
	}

	/**
	 * Processed setter
	 * @param processed true : is processed
	 */
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	
	/**
	 * Index getter
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Index setter
	 * @param index the index in the current process
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Article [index=" + index + ", size=" + size + ", processed=" + processed + "]";
	}
}
