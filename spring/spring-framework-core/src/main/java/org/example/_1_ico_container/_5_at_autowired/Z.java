package org.example._1_ico_container._5_at_autowired;

import org.example.share.EmptyObject;
import org.springframework.beans.factory.annotation.Autowired;

public class Z {
	public EmptyObject emptyObject;
	@Autowired
	public void setEmptyObject(EmptyObject emptyObject) {
		this.emptyObject = emptyObject;
	}
}
