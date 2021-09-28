/*************************************************************************
** Author: JiinxC														**
** Name: Ultra File Manager	   									        **
** Purpose: Used to quickly move or copy many files for point A to B.   **
** Date: 10/6/2017														**
*************************************************************************/				
package problemDomain;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class FileManager extends Thread
{
	private String extension = "";
	private File Start;
	private File End;
	private String Custom;
	
	private fmGui g; 
	File[] list = new File[10];
	
	public static void main(String[] args)
	{
		/*This is the main method that creates the FileManager object
		 * and calls the GUI.
		 */
		FileManager fm = new FileManager();
		fm.Gui();
	}
	public void Gui()
	{
		//This method creates a new GUI
		g = new fmGui();
	}
	
	protected List<String> moveFiles(JPanel activePanel)
	{	
		// This method moves any applicable files in the source directory, to the destination directory.
		// It also sorts the files into their respective file types within the destination directory.
		
		// Get the list of files at the starting location.
		list = Start.listFiles();
		
		// Create a FileManager object.
		FileManager fm = new FileManager();
		
		// Create a file variable to hold the destination for the files.
		File destinationFolder = End;
		
		// Create a createFolders object.
		createFolders cf = new createFolders();
		
		// Create the path variables that will be used for the move.
		Path sourceFilePath;
		Path destFilePath;
		
		// Create a list to hold the file names that failed to move.
		List<String> failedList = new ArrayList<>();
		
		// For each file in the list.
		for(File file : list)
		{
			
			// If the file is not a directory and the file has an extension.
			if(!file.isDirectory() && file.getPath().contains("."))
			{
				
				// Set the source path of the file.
				sourceFilePath = Paths.get(file.getPath());
				
				// Set the destination path of the file.
				destFilePath = Paths.get(destinationFolder.toString() + "\\" + file.getPath().substring(file.getPath().lastIndexOf('.')).toUpperCase() + "\\" + file.getName());
				
				// Create a folder to hold the file.
				cf.createFolder(destFilePath.toString());
				
				// Try to:
				try 
				{
					// Move the file from the source to the destination and replace it if need be.
					Files.move(sourceFilePath, destFilePath,StandardCopyOption.REPLACE_EXISTING);
				
				// Catch any exception.
				} catch (Exception e) 
				{
					
					// Print the stack trace.
					e.printStackTrace();
					
					// Add the file to the failed list.
					failedList.add(file.toPath().toString());
					
				}
			}
		}
		// Return the list of files that failed to move.
		return failedList;
	}

	public void setStart(String start)
	{
		/*This Method will set the starting position for the file
		 * mover when called from fmGUI. 
		 */
		Start = new File(start);
	}
	public void setEnd(String end)
	{
		/*This method will set the end position for the file
		 * mover when called from fmGUI
		 */
		End = new File(end);
	}
	public void setCustom(String custom)
	{
		/*This method sets the custom filetype when called from fmGUI 
		 */
		Custom = custom;
	}
	public String getExtension(String custom)
	{
		/*This method moderates what the custom filetypes folder will be called.
		 * It trims the filetype inputed so that it will not have a . in the name.
		 */
		for(int i = 0; i < custom.length(); i++)
		{
			if(custom.charAt(i) == ('.'))
			{
				custom.trim();
				extension = custom.substring(i+1, custom.length());
				extension = extension.toUpperCase();
			}
			else
			{
				extension = custom;
				extension = extension.toUpperCase();
			}
		}
		return extension;
	}
	public String getCustom()
	{
		//This method gives you the current custom filetype variable.
		return Custom;
	}
	public void openGithub(JPanel activePanel)
	{
		/*This method will open a web browser to my github project page. 
		*Use this for debugging or customization.
		*/
			try
			{
				if(JOptionPane.showConfirmDialog(activePanel, "This will open a new page in your browser.") == 0)
				{
					Desktop.getDesktop().browse(new URI("https://github.com/JiinxC/Ultra-File-Manager"));
				}
				
			} catch (IOException | URISyntaxException e)
			{
				JOptionPane.showConfirmDialog(activePanel, "Unable to open page! the link is:\r\nhttps://gitbuh.com/JiinxC/Ultra-File-Manager");
			}
	}
}
