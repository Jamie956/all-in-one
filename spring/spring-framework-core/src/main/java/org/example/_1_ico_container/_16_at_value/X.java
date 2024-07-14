package org.example._1_ico_container._16_at_value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class X {
	//在资源文件找不到的key，使用默认值
	@Value("${not.found.value:111}")
	private String a;
	//没用表达式${}，获得的就是字符串本身
	@Value("pureStringValue")
	private String b;
}
