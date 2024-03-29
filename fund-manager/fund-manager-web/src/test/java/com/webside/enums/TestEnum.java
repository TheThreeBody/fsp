package com.webside.enums;

import static org.junit.Assert.assertTrue;

import com.npsex.fsp.commons.core.enums.EmailDescription;
import com.npsex.fsp.commons.core.enums.ExportType;
import org.junit.Test;

public class TestEnum {

	@Test
	public void testEmailDescription() {
		assertTrue(EmailDescription.ADD_EMAIL.getSubject() == "账户创建");
	}
	
	@Test
	public void testExportType()
	{
		System.out.println(ExportType.EXCEL.name());
	}

}
