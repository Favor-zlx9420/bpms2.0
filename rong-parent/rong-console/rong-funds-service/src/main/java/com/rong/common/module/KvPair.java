package com.rong.common.module;

import java.io.Serializable;

/**
 * c++ 的 pair对
 * @author lether
 *
 * @param <K>
 * @param <V>
 */
public class KvPair<K extends Comparable<K>, V extends Comparable<V>> implements  
Comparable<KvPair<K, V>>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private K key;
	private V value;
    public KvPair(){
    }
    public KvPair(K key, V value) {  
        this.key = key;  
        this.value = value;  
    }
	@Override
	public int compareTo(KvPair<K, V> o) {
		return -key.compareTo(o.getKey());  
	}
	@Override  
    public String toString() {  
        return "[" + key + "," + value + "]";  
    }
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
}
