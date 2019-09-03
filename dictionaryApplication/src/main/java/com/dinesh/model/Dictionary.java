package com.dinesh.model;


import javax.persistence.*;

/**
 * Created by Dinesh
 */

@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String searchword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSearchWord() {
		return searchword;
	}

	public void setSearchWord(String word) {
		this.searchword = word;
	}


}
