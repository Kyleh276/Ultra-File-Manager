/*************************************************************************
** Author: Kyle Hendrickson												**
** Name: Ultra File Manager 	   									    **
** Purpose: Used to quickly move or copy many files for point A to B.   **
** Date: 10/6/2017														**
*************************************************************************/		
package problemDomain;

import java.io.File;

public class createFolders extends Thread
{
	// Create a FileManager object.
	FileManager fm = new FileManager();
	
	public void createFolder(String end)
	{
		// This method will create all the folders that are needed.
		
		// Create folder at the specified destination.
		File folder = new File(end);
		
		// If the folder doesn't already exist.
		if(!folder.exists())
			// Create the folder.
			folder.mkdirs();
	}
}
