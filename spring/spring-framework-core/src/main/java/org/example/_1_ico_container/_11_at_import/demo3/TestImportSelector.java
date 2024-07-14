package org.example._1_ico_container._11_at_import.demo3;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportSelector implements ImportSelector {
	/**
	 * 选择需要导入的类路径
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// debug
		return new String[]{"org.example.share.EmptyObject"};
	}
}