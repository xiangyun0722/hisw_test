/**
 * 
 *//*
package com.thinkgem.jeesite.modules.zjvideo.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.zjvideo.entity.ClassType;
import com.thinkgem.jeesite.modules.zjvideo.entity.Clazz;
import com.thinkgem.jeesite.modules.zjvideo.service.ClassTypeService;
import com.thinkgem.jeesite.modules.zjvideo.service.ClazzService;

*//**
 * 工具类
 * 
 * @author j.feng
 * 
 *//*
public class ClassTypeUtils {
	private static ClassTypeService classTypeService = SpringContextHolder
			.getBean(ClassTypeService.class);
	private static ClazzService clazzService = SpringContextHolder
			.getBean(ClazzService.class);

	public static final String CACHE_CLASS_TYPE_NAME = "classTypeName";

	
	 * public static String getDictValue(String label, String type, String
	 * defaultLabel){ if (StringUtils.isNotBlank(type) &&
	 * StringUtils.isNotBlank(label)){ for (Dict dict : getDictList(type)){ if
	 * (type.equals(dict.getType()) && label.equals(dict.getLabel())){ return
	 * dict.getValue(); } } } return defaultLabel; }
	 * 
	 * public static List<Dict> getDictList(String type){
	 * 
	 * @SuppressWarnings("unchecked") Map<String, List<Dict>> dictMap =
	 * (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP); if
	 * (dictMap==null){ dictMap = Maps.newHashMap(); for (Dict dict :
	 * dictDao.findAllList(new Dict())){ List<Dict> dictList =
	 * dictMap.get(dict.getType()); if (dictList != null){ dictList.add(dict);
	 * }else{ dictMap.put(dict.getType(), Lists.newArrayList(dict)); } }
	 * CacheUtils.put(CACHE_DICT_MAP, dictMap); } List<Dict> dictList =
	 * dictMap.get(type); if (dictList == null){ dictList =
	 * Lists.newArrayList(); } return dictList; }
	 

	*//**
	 * 获取分类名称
	 * 
	 * @param typeid
	 *            分类ID
	 * @return
	 *//*
	public static List<ClassType> getClassTypeById(Integer typeid) {
		List<ClassType> classCacheTypes = (List<ClassType>) CacheUtils
				.get(CACHE_CLASS_TYPE_NAME);
		List<ClassType> classTypes = Lists.newArrayList();
		if (classCacheTypes == null) {
			List<Clazz> clazzs = clazzService.getClassTypeById(typeid);
			for (Clazz clazz : clazzs) {
				ClassType classType = classTypeService.get(clazz.getTypeid());
				classTypes.add(classType);
			}
			CacheUtils.put(CACHE_CLASS_TYPE_NAME, classTypes);
			classCacheTypes = classTypes;
		}
		return classCacheTypes;
	}
}*/