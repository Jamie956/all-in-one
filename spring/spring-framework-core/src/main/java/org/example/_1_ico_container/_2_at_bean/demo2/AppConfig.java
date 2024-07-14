package org.example._1_ico_container._2_at_bean.demo2;

import org.example.share.EmptyObject;
import org.springframework.context.annotation.Bean;

public class AppConfig {
	// autowireCandidate = false: EmptyObject cannot inject to other bean Y
	@Bean(autowireCandidate = false)
	public EmptyObject emptyObject() {
		return new EmptyObject();
	}
}
