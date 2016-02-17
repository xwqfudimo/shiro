package com.xwq.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwq.model.Permission;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
public class PermissionServiceTest {
	@Autowired
	private PermissionService permissionService;
	
	@Test
	public void testGetByRoleId() {
		List<Permission> list = this.permissionService.getByRoleId(3);
		
		for(Permission p : list) {
			System.out.println(p);
		}
	}
}
