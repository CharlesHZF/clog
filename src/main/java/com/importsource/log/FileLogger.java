/**
 * 
 */
package com.importsource.log;


/**
 * 通过文件输出
 * @author Hezf
 *
 */
public class FileLogger extends AbstractLogger  {

	public FileLogger(Class<?> clazz) {
		super.clazz = clazz;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.importsource.log.AbstractLogger#log(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	protected void log(String msg, String level) {
           String log=getFormat(super.clazz,level)+msg;
           stdout(log);
           //输出到指定的文件中
          writeByLevel(log,level);
	}


	

	


	private void writeByLevel(String log,String level) {
		 String confLevel=getLevel();
		 //confLevel:w,then if(level=w||e){write()}
         //confLevel:e,then if(level=e){write()}
         //confLevel:i,then if(level=e||i||w){write()}
		 //String iwe="iwe";
		 if(confLevel.equals(INFO)){
			 write(log);;
		 }
		 if(confLevel.equals(WARN)){
			 if(level.equals(WARN)||level.equals(ERROR)){
				 write(log);
			 }
		 }
		 if(confLevel.equals(ERROR)){
			 if(level.equals(ERROR)){
				 write(log);
			 }
		 }
	}


	private void write(String log) {
		FileAppender  f=FileManager.getAppender();
		f.append(log);
		
	}
	
	
	private void stdout(String log) {
		System.out.println(log);
	}

}
