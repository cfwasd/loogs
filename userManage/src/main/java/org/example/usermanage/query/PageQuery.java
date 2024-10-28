package org.example.usermanage.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {
    // 当前页码
    public  Integer page;
    // 每页条数
    public Integer pageSize;
    // 排序字段
    public String sort;
    //是否升序
    public Boolean asc;
}
