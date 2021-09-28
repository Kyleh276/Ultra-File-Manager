/*************************************************************************
** Author: JiinxC														**
** Name: Ultra File Manager 	   									    **
** Purpose: Used to quickly move or copy many files for point A to B.   **
** Date: 10/6/2017														**
*************************************************************************/		
package problemDomain;

import java.io.File;

public class createFolders extends Thread
{
	/*The entire point of this file is to create folders int the destination
	 *  in order to organize them.
	 */
	FileManager fm = new FileManager();
	
	public createFolders()
	{
		//This method creates an object in order to reference the below methods.
	}
	public void createFolder(String end)
	{
		//This method will create all the folders needed for the default setting.
		//specify file variable in order to close the file after creation?
		
		// Create file with the correct destination.
		File folder = new File(end);
		// If the doesn't folder already exist.
		if(!folder.exists())
			// Create the folder.
			folder.mkdirs();
	}
	public void createCustom(String end)
	{
		//Makes a custom folder for the custom filetype that is inputed.
			String path = end;
			new File(path).mkdirs();
	}
}
