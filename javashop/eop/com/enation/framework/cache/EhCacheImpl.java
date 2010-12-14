/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.cache;

import java.io.Serializable;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

 
/**
 * Ehcache缓存实现
 * @author kingapex
 *
 */
public class EhCacheImpl  implements ICache{

	private net.sf.ehcache.Cache cache;

	/**
	 * 
	 */
	public EhCacheImpl(String name){
        try {
            CacheManager manager = CacheManager.getInstance();
            cache = manager.getCache(name);
            if (cache == null) {
                manager.addCache(name);
                cache = manager.getCache(name);
            }
        } catch (net.sf.ehcache.CacheException e) {
        	e.printStackTrace();
        }
	}


    /**
     * Gets a value of an element which matches the given key.
     * @param key the key of the element to return.
     * @return The value placed into the cache with an earlier put, or null if not found or expired
     * @throws CacheException
     */
    public Object get(Object key){
    	
    	Object obj = null;
        try {
            if (key!= null)
            {
                Element element = cache.get((Serializable) key);
                if (element!=null)
                {
                    obj = element.getValue();
                }
            }
        } catch (net.sf.ehcache.CacheException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * Puts an object into the cache.
     * @param key a {@link Serializable} key
     * @param value a {@link Serializable} value
     * @throws CacheException if the parameters are not {@link Serializable}, the {@link CacheManager}
     * is shutdown or another {@link Exception} occurs.
     */
    public void put(Object key, Object value){
        try {
            Element element = new Element((Serializable) key, (Serializable) value);            
            cache.put(element);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes the element which matches the key.
     * <p>
     * If no element matches, nothing is removed and no Exception is thrown.
     * @param key the key of the element to remove
     * @throws CacheException
     */
    public void remove(Object key){
        try {
            cache.remove((Serializable) key);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Remove all elements in the cache, but leave the cache
     * in a useable state.
     * @throws CacheException
     */
    public void clear(){

        try {
        	//cache.remove(arg0)
            cache.removeAll();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        } 
    }

    public static void main(String[] args)
	{
    	EhCacheImpl cache = new EhCacheImpl("queryCache");
//    	cache.put("test","fjdkafjsdkajd");
    	System.out.println(cache.get("test"));
	}
	
}
