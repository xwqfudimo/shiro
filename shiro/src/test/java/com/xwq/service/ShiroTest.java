package com.xwq.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class ShiroTest {

	@Test
	public void testMd5(){
		Md5Hash hash = new Md5Hash("123");
		System.out.println(hash.toHex());
		hash = new Md5Hash("123", "zhangsan");
		System.out.println(hash.toHex());
	}
}
