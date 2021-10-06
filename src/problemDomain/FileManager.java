/*************************************************************************
** Author: Kyle Hendrickson													**
** Name: Ultra File Manager	   									        **
** Purpose: Used to quickly move or copy many files for point A to B.   **
** Date: 10/6/2017														**
*************************************************************************/				
package problemDomain;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileManager extends Thread
{
	// Set up the Start and End file variables.
	private File Start;
	private File End;
	
	// Set up the GUI variable.
	@SuppressWarnings("unused")
	private fmGui gui; 
	
	// Set up the file list variable.
	File[] list = new File[10];
	
	public static void main(String[] args)
	{
		//This is the main method that creates the FileManager object and calls the GUI.
		FileManager fm = new FileManager();
		fm.Gui();
	}
	public void Gui()
	{
		//This method creates a new GUI
		gui = new fmGui();
	}
	
	protected void moveFiles(JPanel activePanel, ArrayList<String> fileTypesList)
	{
		// This method moves any applicable files in the source directory, to the destination directory.
		// It also sorts the files into their respective file types within the destination directory.
		// It can take a custom list of file types or just do any file that isn't a directory and that
		// has an extension.
		
		// Create a file variable to hold the destination for the files.
		File destinationFolder = End;
					
		// Create a createFolders object.
		createFolders cf = new createFolders();
				
		// Create the path variables that will be used for the move.
		Path sourceFilePath;
		Path destFilePath;
						
		// Get the list of files at the starting location.
		list = Start.listFiles();
		
		// For each file in the list.
		for(File file : list)
		{
			// If the current file is not a directory
			if(!file.isDirectory() && !file.getPath().contains("desktop.ini"))
			{
				// Set the source path of the file.
				sourceFilePath = Paths.get(file.getPath());
						
				// Set the destination path of the file.
				destFilePath = Paths.get(destinationFolder.toString() + "\\" + file.getPath().substring(file.getPath().lastIndexOf('.')).toUpperCase() + "\\" + file.getName());
						
				// If the method was called with a custom file type list, and the current file has an extension that is within the custom list.
				if(fileTypesList != null && fileTypesList.contains(file.getPath().substring(file.getPath().lastIndexOf(".")).toUpperCase()))
				{
					// Create a folder to hold the file.
					cf.createFolder(destFilePath.toString());
					
					// Try to:
					try
					{
						// Move the file from the source to the destination and replace it if need be.
						Files.move(sourceFilePath, destFilePath,StandardCopyOption.REPLACE_EXISTING);	
					}
					// Catch any exceptions.
					catch (Exception e)
					{		
						// Open a dialogue that informs the user that the file was not moved.
						JOptionPane.showMessageDialog(activePanel, "Failed to move: " + file.toPath().getFileName());
					}
				}
				
				// If the filename contains a . and the custom list is null.
				if(file.getPath().contains(".") && fileTypesList == null)
				{
					// Create a folder to hold the file.
					cf.createFolder(destFilePath.toString());
					
					// Try to:
					try 
					{
						// Move the file from the source to the destination and replace it if need be.
						Files.move(sourceFilePath, destFilePath,StandardCopyOption.REPLACE_EXISTING);
					} 
					// Catch any exceptions.
					catch (Exception e) 
					{
						// Open a dialogue that informs the user that the file was not moved.
						JOptionPane.showMessageDialog(activePanel, "Failed to move: " + file.toPath().getFileName());
					}
				}
			}
		}
	}
	protected void copyFiles(JPanel activePanel, ArrayList<String> fileTypesList)
	{
		// This method moves any applicable files in the source directory, to the destination directory.
		// It also sorts the files into their respective file types within the destination directory.
		// It can take a custom list of file types or just do any file that isn't a directory and that
		// has an extension.
		
		// Create a file variable to hold the destination for the files.
		File destinationFolder = End;
					
		// Create a createFolders object.
		createFolders cf = new createFolders();
				
		// Create the path variables that will be used for the move.
		Path sourceFilePath;
		Path destFilePath;
						
		// Get a list of files from the source directory and load them into list.
		list = Start.listFiles();
		
		// For each file in the list.
		for(File file : list)
		{
			// If the file is not a directory.
			if(!file.isDirectory() && !file.getPath().contains("desktop.ini"))
			{
				// Set the source path of the file.
				sourceFilePath = Paths.get(file.getPath());
						
				// Set the destination path of the file.
				destFilePath = Paths.get(destinationFolder.toString() + "\\" + file.getPath().substring(file.getPath().lastIndexOf('.')).toUpperCase() + "\\" + file.getName());
					
				// If the list of custom file types is not null, and the list contains the file's extension.
				if(fileTypesList != null && fileTypesList.contains(file.getPath().substring(file.getPath().lastIndexOf(".")).toUpperCase()))
				{
					// Create a folder to hold the file.
					cf.createFolder(destFilePath.toString());
					// Move the file from the source to the destination and replace it if need be.
					try
					{
						// Copy the file from the source to the destination and replace it if need be.
						Files.copy(sourceFilePath, destFilePath,StandardCopyOption.REPLACE_EXISTING);	
					}
					catch (Exception e)
					{
						// Open a dialogue that informs the user that the file was not copied.
						JOptionPane.showMessageDialog(activePanel, "Failed to copy: " + file.toPath().getFileName());
					}
				}
				
				// If the filename contains a . and the custom file type list is null.	
				if(file.getPath().contains(".") && fileTypesList == null)
				{
					// Create a folder to hold the file.
					cf.createFolder(destFilePath.toString());
					
					// Try to:
					try 
					{
						// Copy the file from the source to the destination and replace it if need be.
						Files.copy(sourceFilePath, destFilePath,StandardCopyOption.REPLACE_EXISTING);
						
						
					} 
					// Catch any exception.
					catch (Exception e) 
					{
						// Open a dialogue that informs the user that the file was not copied.
						JOptionPane.showMessageDialog(activePanel, "Failed to copy: " + file.toPath().getFileName());			
					}
				}
			}
		}
	}
	public void setStart(String start)
	{
		// This method sets the Start variable.
		Start = new File(start);
	}
	public void setEnd(String end)
	{
		// This method sets the End variable.
		End = new File(end);
	}
	public void openGithub(JPanel activePanel)
	{
		//This method will open a web browser to my github project page. 
			// Try to:
			try
			{
				// If the user clicks yes.
				if(JOptionPane.showConfirmDialog(activePanel, "This will open a new page in your browser.") == 0)
				{
					// Open a web page to my github address.
					Desktop.getDesktop().browse(new URI("https://github.com/Kyleh276/"));
				}
			}
			// Catch any exception.
			catch (Exception e)
			{
				// Open a dialogue that shows the error.
				JOptionPane.showConfirmDialog(activePanel, "Unable to open page! The link is:\r\nhttps://github.com/Kyleh276/");
			}
	}
}
