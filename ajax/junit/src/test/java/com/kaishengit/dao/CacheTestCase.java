package com.kaishengit.dao;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;

/**
 * Created by Acer on 2016/12/14.
 */
public class CacheTestCase {
    @Test
    public void testCache(){
        //1.创建内存管理者对象
        CacheManager cacheManager = new CacheManager();
        //2.根据内存管理者获取内存空间并将缓存名称传进去,
        Ehcache cache = cacheManager.getEhcache("user");
        //3.添加,将元素放入创建element对象作为参数传进去
        Element element = new Element("user:1","jack");
        Element element2 = new Element("user:2","rose");
        cache.put(element);
        cache.put(element2);
        //5.删除分为两种：一种将缓存区数据全部删除，另外一种根据键值将放入的具体数据删除
        //cache.removeAll();//将缓存区数据全部删除
        cache.remove("user:1");//将缓存区某一数据删除根据键值
        //4.取值
        Element element1 = cache.get("user:1");//根据键获取元素
        Element element3 = cache.get("user:2");
        System.out.println(element3.getObjectValue());
        System.out.println(element1.getObjectValue());//根据元素取得值

    }
}
