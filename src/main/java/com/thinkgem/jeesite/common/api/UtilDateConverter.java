/**
 * 
 */
package com.thinkgem.jeesite.common.api;

import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * @author ye
 *
 */
public class UtilDateConverter implements Converter {
	
	
	

	/* (non-Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if(null == value) {
			return null;
		}
		if(value instanceof Date) {
			 return value;
		}
		
		if(value instanceof String) {
			return WebUtil.parseDate((String)value);
		}		
		return null;
	}

}
