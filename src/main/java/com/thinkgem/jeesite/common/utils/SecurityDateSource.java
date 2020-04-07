package com.thinkgem.jeesite.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库密码加密解密
 */
public class SecurityDateSource extends DruidDataSource {
    @Override
    public void setUsername(String username){
        try {
            username = ConfigTools.decrypt(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        try {
            password = ConfigTools.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setPassword(password);
    }

    public static void main(String[] args) throws Exception {
    	
//      String password = "root";  加密后的password = [bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==]
//      String username = "root";  加密后的username = [bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==]

//    	String password = "Mljiayuan";   //加密后的password = [S4DK/ynBJDDR6OJbbXNGQV9LxNQYByRDuS9myOoemeDwqkd9uajtL8dXzjZ7GcvD+I6xYDYIo85fgiSZO/LUOg==]
//      String username = "Mljy123!@#";  //加密后的username = [bYdEk2JqBVns52qHWdmM6Kd7VNZ5coQuOCjW2iXOnvAru4mHk/IokLg7YOCO8UIqrDUhhlPCevoecPcf4GW45w==]
        
//    	String password = "root";  	 //加密后的password = [bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==]
//    	String username = "123456";  //加密后的username = [Biyu5YzU+6sxDRbmWEa3B2uUcImzDo0BuXjTlL505+/pTb+/0Oqd3ou1R6J8+9Fy3CYrM18nBDqf6wAaPgUGOg==]

    	String password = "hisw123";  	 //加密后的password = [bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==]
    	String username = "root"; //加密后的username = [k8xxwa/kc2TrfnyO+XovyDOfdYFusuoiN+D3/FP9gY7o+rY0AaXI+LJNJtCoFkN90UOOJw3JkbgVyPdiWXsodg==]

    	
    	System.out.println("加密后的password = [" + ConfigTools.encrypt(password) + "]");
        System.out.println("加密后的username = [" + ConfigTools.encrypt(username) + "]");
    }
}
