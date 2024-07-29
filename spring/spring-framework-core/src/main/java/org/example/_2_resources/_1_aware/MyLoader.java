package org.example._2_resources._1_aware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;


public class MyLoader implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
