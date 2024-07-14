package org.example._1_ico_container._21_at_lookup;

import org.example.share.EmptyObject;
import org.springframework.beans.factory.annotation.Lookup;

public class A {
	@Lookup("emptyObject")
	public EmptyObject lookupEmptyObject(){
		return null;
	}
}
