package com.thinkgem.jeesite.modules.live.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.hisw.core.utils.process.ExecuteResult;
import com.hisw.core.utils.process.LocalCommandExecutorService;
import com.hisw.core.utils.process.LocalCommandExecutorServiceImpl;

public class ExeCmdUtil {

	private static Logger logger = Logger.getLogger(ExeCmdUtil.class);
	
	/***
	 * 执行cmd，
	 * @param srcFileName
	 */
	public  static ExecuteResult exeCmdResult(String cmd){
		logger.info(" cmd: "+cmd+"  Ing ......");
		LocalCommandExecutorService service = new LocalCommandExecutorServiceImpl();
		if(StringUtils.indexOf(cmd, "|")>-1){
		  try {
			    ExecuteResult result = null;
			  	List<String> cmds = new ArrayList<String>(); 
				cmds.add("sh"); 
				cmds.add("-c");
				cmds.add(cmd); 
			    logger.info("命令为带管道的命令。需要使用 sh -c cmd 执行");
                ProcessBuilder builder = new ProcessBuilder();    
                builder.command(cmds);    
                builder.redirectErrorStream(true);    
                Process p= builder.start();    
                BufferedReader buf = null; // 保存ffmpeg的输出结果流    
                String line = null;    
                buf = new BufferedReader(new InputStreamReader(p.getInputStream()));    
                StringBuffer sb= new StringBuffer();    
                while ((line = buf.readLine()) != null) {    
                     sb.append(line + "\n");    
                     continue;    
                }    
                p.waitFor();//这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行  
                logger.info("带管道的出错内容：" + sb.toString());  
                result = new ExecuteResult(-1, sb.toString());
                return result;
	        } catch (Exception e) {    
	            logger.error(e,e);  
	        }
	        return null;
		}else{
			String[] commands = StringUtils.split(cmd,";");
	        ExecuteResult result = service.executeCommand(commands,72*3600*1000);//设置72小时超时
	        logger.info(result);
	        return result;
		}
	}
	
}
