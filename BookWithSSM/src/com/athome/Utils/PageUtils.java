package com.athome.Utils;

import com.athome.pojo.BookBean;
import com.github.pagehelper.PageInfo;

public class PageUtils {

    public  static String getPage(PageInfo<BookBean> pageInfo,String path,String method){

        StringBuilder builder = new StringBuilder();
        //拼接首页
        builder.append("<a href='"+path+method+"1'>首页</a>");
        builder.append("&nbsp;&nbsp;");

        //拼接上一页
        if (pageInfo.isHasPreviousPage()){
            builder.append("<a href='"+path+method+pageInfo.getPrePage()+"'>上一页</a>");
            builder.append("&nbsp;&nbsp;");
        }else {
            builder.append("上一页");
            builder.append("&nbsp;&nbsp;");
        }

        int[] nums = pageInfo.getNavigatepageNums();
        for (int i: nums
        ) {
            if (i==pageInfo.getPageNum()){

                builder.append("<a style='color:red;' href='"+path+method+i+"'>"+i+"</a>");
                builder.append("&nbsp;&nbsp;");
            }
            else {
                builder.append("<a href='"+path+method+i+"'>"+i+"</a>");
                builder.append("&nbsp;&nbsp;");
            }
        }
        //下一页
        if (pageInfo.isHasNextPage()){
            builder.append("<a href='"+path+method+pageInfo.getNextPage()+"'>下一页</a>");
            builder.append("&nbsp;&nbsp;");
        }
        else {
            builder.append("下一页");
            builder.append("&nbsp;&nbsp;");
        }




        //拼接尾页
        builder.append("<a href='"+path+method+pageInfo.getPages()+"'>尾页</a>");
        builder.append("&nbsp;&nbsp;");
        return builder.toString();
    }
}
