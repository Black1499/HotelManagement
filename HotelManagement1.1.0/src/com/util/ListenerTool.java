package com.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.bo.DataBackupBo;
import com.control.BackupTool;
import com.dao.DataBackupDao;

/**
 * Application Lifecycle Listener implementation class ListenerTool
 *
 */
@WebListener
public class ListenerTool extends TimerTask implements ServletContextListener {
       
    /**
     * @see TimerTask#TimerTask()
     */
    public ListenerTool() {
        super();
        // TODO Auto-generated constructor stub
    }

    Timer  t=new Timer();
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	t.cancel();
    }
   
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	ListenerTool lt=new ListenerTool();
    	//t.schedule(lt, 1000, 1000*60*3);
    }
    BackupTool bt=new BackupTool();
    DataBackupDao dbd=new DataBackupDao();
	DataBackupBo dnn=new DataBackupBo();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		String path="D:\\DataBaseBackUp\\";
		File f=new File(path);
		if(f.exists()) {
			f.mkdir();
		}
		String dataPath=dbd.backUp(path);
		if(dataPath!="") {
			File file=new File(dataPath);
			String size=bt.setSize((int)file.length());
			String name=file.getName();
			if(dnn.add(name, size,sdf.format(new Date()) )) {
				System.out.println("数据备份成功");
			}
		}
	}
	
}
