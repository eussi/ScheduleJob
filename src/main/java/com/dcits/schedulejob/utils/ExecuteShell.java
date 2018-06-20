package com.dcits.schedulejob.utils;


import com.dcits.schedulejob.constants.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShell {

	
    public static String executeShell(String cmd,String[] envp,String dir) {
        StringBuilder executeResult = new StringBuilder();
        Process process = null;
        BufferedReader bfReader = null;
        try {
        	//cmd：要执行的命令字符串；envp：必要的参数；dir：执行命令时所在目录
        	//注:如果sh中含有awk,一定要按new String[]{"/bin/sh","-c",cmd}写,才可以获得流
            process = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",cmd},envp,new File(dir));
            
            //在waitfor()命令之前读出窗口的标准输出缓冲区中的内容,安置java应用程序阻塞在waitfor()语句
            bfReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            //读行计数器
            int loop = 0;
            String line;
            while ((line = bfReader.readLine()) != null) {
                //超过三千行则中断
                if (loop > 3000) {
                    break;
                }
                executeResult.append(line).append(Constants.NEWLINE);
                loop++;
            }

            /*
             * jdk 1.8 使用
             * 
	            timeout - 等待的最长时间,unit - timeout的时间单位,超时则抛出InterruptedException（中断异常）
	            boolean exitStatus = process.waitFor(60, TimeUnit.SECONDS);
	            synchronized (process) {
	            	process.wait(60000);
				}
				if (exitStatus) {
                if (log.isDebugEnabled())
                	System.out.println("调用shell命令成功，cmd is:[" + cmd + "]，返回结果:[" + executeResult.toString() + "]");
	            } else {
	            	System.out.println("调用shell命令失败，cmd is:[" + cmd + "],error code is " + exitStatus);
	            }
			 **/
            int exitStatus = process.waitFor();
            if (exitStatus == 0 ) {
                	System.out.println("调用shell命令成功，cmd is:[" + cmd + "]");
            } else {
            	System.out.println("调用shell命令失败，cmd is:[" + cmd + "],error code is " + exitStatus);//126 一般是没有执行权限 127 java运行环境与脚本执行环境不一致
            }
            //当前线程在等待时中断
        } catch (InterruptedException e) {
        	System.out.println("shell命令执行超时，cmd is:[" + cmd + "]");
        } catch (IOException e) {
        	System.out.println("命令不正确，cmd is:[" + cmd + "]");
        } finally {
            //关闭缓冲流
            if (bfReader != null) {
                try {
                    bfReader.close();
                } catch (IOException e) {
                	System.out.println("缓冲流关闭异常" + e);
                }
            }
            //关闭进程
            if (process != null) {
                process.destroy();
            }
        }
        return executeResult.toString();
    }

}

