package com.kaishengit.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * Created by Acer on 2016/12/13.
 * Enchche工具类
 */
public class EhCacheUtil  {
    //创建内存管理者对象，通过一个方法将ehcache。xml配置的缓存区名传进去，
   private static CacheManager cacheManager = new CacheManager();//将cacheManager设置为一份将会读取一次配置文件不然会多次读取配置文件性能低下，写成static静态的
    //将cacheManager进行封装
    public Ehcache getEhcache(String cacheName){
        return cacheManager.getCache(cacheName);
       // return cacheManager.getEhcache(cacheName);

    }
    //将数据放入到缓存区需要执行三个参数往哪个缓存区放，键值都为可序列化的key和value
    public void set(Ehcache ehcache, Serializable key,Serializable value){
        Element element = new Element(key,value);
        ehcache.put(element);
    }
    //方法的重载，参数为缓存区名称，放入值不同的地方
    public void set(String cacheName,Object key,Object value){
        Element element = new Element(key,value);
        getEhcache(cacheName).put(element);
    }
    public void set(String cacheName,Serializable key,Serializable value){
        Element element = new Element(key,value);
        //调用上面的方法
        getEhcache(cacheName).put(element);
    }
    //取值
    public Object get(String cacheName,Serializable key){
        Element element = getEhcache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }
    //取值，方法的重载传进去缓存区
    public Object get(Ehcache eh,Serializable key){
        Element ehcache = eh.get(key);
        return ehcache == null ? null: ehcache.getObjectValue();
    }
    //删除缓存区，将缓存区数据全部删除，当缓存中的数据，通过传进去缓存区的名称
    public void removeAll(String cacheName){
        getEhcache(cacheName).removeAll();
    }
    //方法的重载，传进去一个Ehcache对象
    public void removaAll(Ehcache ehcache){
        ehcache.removeAll();
    }
    //移除部分数据，传进去缓存区名
    public void remove(String cacheName,Serializable key){
        getEhcache(cacheName).remove(key);
    }
    public void remove(Ehcache ehcache,Serializable key){
        ehcache.remove(key);
    }
}
