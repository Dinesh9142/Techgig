package com.dinesh.dao;

import java.util.List;

public interface IDictionary{

	List<String> findByName(String searchword);

}
