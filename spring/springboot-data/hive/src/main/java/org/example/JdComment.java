package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JdComment {
    private String comment_id;
    private String goods_id;
    private String platform;
    private String goods_type;
    private String goods_name;
    private String shop;
    private String created;
    private String ship_address;
    private String price;
    private Integer commentSum;
}
