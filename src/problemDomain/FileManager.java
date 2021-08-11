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
	@SuppressWarnings("unused")
	
	private fmGui g; 
	File[] list = new File[10];
	private int j = 0;
	private boolean copiedTrue;
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
	public void runAll(JPanel activePanel)
	{
		/*This method is the backend for the Default setting.
		 * This method also tells you where the files that were moved are now located.
		 * If no files are found or manipulated, this method will tell you. 
		 * 
		 * 
		 * 
		 * This method moves all file types found in the directory except for folders.
		 * (a setting could be added to also organize within folders.) 
		 * 
		 * 
		 */
		boolean copied = moveFileCheck(copiedTrue,activePanel);
		if(copied == true)
		{
			JOptionPane.showMessageDialog(activePanel, "Files Successfully Moved To:"
					 + "\n" + End);
		}
		else if(copied == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Files With The Selected Extensions Were Found!");
		}
	}
	public void runDefaultCopy(JPanel activePanel)
	{
		/*This is identical to the above method except it uses the methods intended 
		 * for the copy option.*/
		boolean copied = copyFileCheck(copiedTrue);
		if(copied == true)
		{
			JOptionPane.showMessageDialog(activePanel, "Files Successfully Copied To:"
					+"\n" + End);
		}
		else if(copied == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Files With The Selected Extensions Were Found!");
		}
		
	}
	public void runCustom(JPanel activePanel)
	{	
		/*This method is only used to move only custom filetypes for source to destination and 
		 * it also shows you were the files were moved to or if the files were not moved.
		 * This method moves all the filetypes in a list(specified by the user) to organized folders.
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		boolean copied = moveCustomCheck(copiedTrue, activePanel);
		if(copied == true)
		{
			JOptionPane.showMessageDialog(activePanel, "Files Successfully Moved To:"
					 + "\n" + End);
		}
		else if(copied == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Files With The Selected Extension Were Found!");
		}
	}
	public void runCustomCopy(JPanel activePanel)
	{
		/*This method is the same as the above but is only used when the user selects the
		*copy option in the gui.
		*/
		boolean copied = copyCustomCheck(copiedTrue,activePanel);
		if(copied == true)
		{
			JOptionPane.showMessageDialog(activePanel, "Files Successfully Copied To" 
					+ "\n" + End);
		}
		else if(copied == false)
		{
			
			JOptionPane.showMessageDialog(activePanel, "No Files With The Selected Extension Were Found!");
		}
	}
	public void runBoth(JPanel activePanel)
	{
		//This method moves both default filetypes and custom filetypes to a specified destination.//
		boolean copied = moveCustomCheck(copiedTrue, activePanel);
		boolean copied2 = moveFileCheck(copiedTrue,activePanel);
		if(copied == true && copied2 == true)
		{
			JOptionPane.showMessageDialog(activePanel, "All Files Successfully Moved To:"
					 + "\n" + End);
		}
		else if(copied == true && copied2 == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Default Filetypes Found" + "\n" + "Custom Files Successfully Moved To:"
					 + "\n" + End);
		}
		else if(copied == false && copied2 == true)
		{
			JOptionPane.showMessageDialog(activePanel, "No Custom Files Found!" + "\n" + "Default Files Successfully Moved To:"
					 + "\n" + End);
		}
		else if(copied == false && copied2 == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Matching Files Found to Move!");
		}
		
	}
	public void runBothCopy(JPanel activePanel)
	{
		/*This method is only used when the user selects the copy option and 
		*the both radio button.
		*/
		boolean copied  = copyCustomCheck(copiedTrue, activePanel);
		boolean copied2 = copyFileCheck(copiedTrue);
		if(copied == true && copied2 == true)
		{
			JOptionPane.showMessageDialog(activePanel, "All Files Successfully Copied To" 
					+ "\n" + End);
		}
		else if(copied == true && copied2 == false)
		{
			JOptionPane.showMessageDialog(activePanel, "No Default Filetypes Were Found" + "\n" + "Custom Files Were Copied To" + "\n" + End);
		}
		else if(copied == false && copied2 == true)
		{
			JOptionPane.showMessageDialog(activePanel, "No Custom Files Located!" + "\n" + "Default Filetypes Were Copied To" + End);
		}
		
	}
	protected boolean moveCustomCheck(boolean copiedTrue, JPanel activePanel)
	{
		/*This method checks if any files with the chosen custom filetype
		 * are present in the starting location and if there are any creates a 
		 * folder and moves the files to the destination.
		 */
		list = Start.listFiles();
		System.out.println("Start " + Start);
		getExtension(Custom);
		//Manually setting the End variable to the correct dir because the method setEnd refuses to change the value.
		End = new File(End.getPath() + "\\" + extension);
		int filesMoved = 0;
		for(File file : list)
		{
			if(file.getName().endsWith(extension) || file.getName().endsWith(extension.toLowerCase()))
			{
				//If file is not a directory
				if(Files.isRegularFile(file.toPath()))
				{
					System.out.println(Files.isRegularFile(file.toPath()));
					System.out.println(file.getName());
					/* Create list to hold the files being moved
					 * Add files to list for the gui*/
					try {
						/* Create folder that will hold files
						 * Move files to new folder*/
						createFolders cf = new createFolders();
						cf.createCustom(End.getPath());
						Files.copy(file.toPath(), End.toPath().resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
						filesMoved++;
						
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(activePanel, "Something went wrong!! Error Code:1");
						System.out.println(ex);
						System.exit(1);
					}
				}
			}
				//Check if the number of files moved is the same as the number that were in the source dir.
				if(End.listFiles().length == filesMoved)
				{
					copiedTrue = true;
				}
		}
		return copiedTrue;
	}
	protected boolean copyCustomCheck(boolean copiedTrue, JPanel activePanel)
	{
		/*this method does the same as the above, but instead of moving files 
		 * it copies them.
		 */
		list = Start.listFiles();
		System.out.println("Start " + Start);
		getExtension(Custom);
		//Manually setting the End variable to the correct dir because the method setEnd refuses to change the value.
		End = new File(End.getPath() + "\\" + extension);
		int filesMoved = 0;
		for(File file : list)
		{
			if(file.getName().endsWith(extension) || file.getName().endsWith(extension.toLowerCase()))
			{
				//If file is not a directory
				if(Files.isRegularFile(file.toPath()))
				{
					System.out.println(Files.isRegularFile(file.toPath()));
					System.out.println(file.getName());
					/* Create list to hold the files being moved
					 * Add files to list for the gui*/
					try {
						/* Create folder that will hold files
						 * Move files to new folder*/
						createFolders cf = new createFolders();
						cf.createCustom(End.getPath());
						Files.copy(file.toPath(), End.toPath().resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
						filesMoved++;
						
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(activePanel, "Something went wrong!! Error Code:1");
						System.out.println(ex);
						System.exit(1);
					}
				}
			}
				//Check if the number of files moved is the same as the number that were in the source dir.
				if(End.listFiles().length == filesMoved)
				{
					copiedTrue = true;
				}
		}
		return copiedTrue;
	}
	protected boolean moveFileCheck(boolean copiedTrue, JPanel activePanel)
	{	
		/*This method checks if there are any files of the selected type in the starting location
		 * and if there are, a folder is created and the files are moved. 
		 */
		//Modify this code to get all files and move them to seperate folders based on the filetype
		list = Start.listFiles();
		//System.out.println("Start " + Start);
		
		//Manually setting the End variable to the correct dir because the method setEnd refuses to change the value.
		//End = new File(End.getPath() + "\\" + extension);
		
		int filesMoved = 0;
		String[] fileTypeList;
		ArrayList<String> requiredFolders = new ArrayList<String>();
		for(File file : list)
		{
			//If file is not a folder.
			if(Files.isRegularFile(file.toPath()))
			{
				//This split needs the \\ or it will think you mean that it should use all characters and then it will explode.
				fileTypeList = file.getName().split("\\.");
				//If list already contains the file type of current file.
				if(!requiredFolders.contains(fileTypeList[fileTypeList.length - 1]))
				{
					//Add file type to the list of folders that are needed.
					requiredFolders.add(fileTypeList[fileTypeList.length - 1]);
				}
			}
		}
		//Creates folders that are needed to move each type of file one by one.
		for(String fileType : requiredFolders)
		{
			//Combine item in file types list with the path of the destination directory(set to upper case)
			fileType = End.getPath() + "\\" +  "." + fileType.toUpperCase();
			//Create Folder
			createFolders cf = new createFolders();
			cf.createCustom(fileType);
		}
		
		for(File file : list)
		{
			if(file.getName().endsWith(extension) || file.getName().endsWith(extension.toLowerCase()))
			{
				//If file is not a directory
				if(Files.isRegularFile(file.toPath()))
				{
					/* Create list to hold the files being moved
					 * Add files to list for the gui*/
					try {
						/* Create folder that will hold files
						 * Move files to new folder*/
						createFolders cf = new createFolders();
						cf.createCustom(End.getPath());
						Files.move(file.toPath(), End.toPath().resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
						filesMoved++;
						
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(activePanel, "Something went wrong!! Error Code:1");
						System.out.println(ex);
						System.exit(1);
					}
				}
			}
				//Check if the number of files moved is the same as the number that were in the source dir.
				if(End.listFiles().length == filesMoved)
				{
					copiedTrue = true;
				}
		}
		return copiedTrue;
		/*
		int i = 0;
		while(i < list.length - 1)
		{
			list = Start.listFiles();
			if(list[i].getName().toLowerCase().endsWith(".zip"))
			{
				List<File> zipfiles = new ArrayList<File>();
				String path = End +"\\" + ".ZIP" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				zipfiles.add(list[i]);
				
				for(File file : zipfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:3");
						System.out.println(e);
						System.exit(3);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".exe"))
			{
				List<File> exefiles = new ArrayList<File>();
				String path = End +"\\" + ".EXE" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				exefiles.add(list[i]);
				for(File file : exefiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:4");
						System.out.println(e);
						System.exit(4);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".msi"))
			{
				List<File> msifiles = new ArrayList<File>();
				String path = End +"\\" + ".MSI" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				msifiles.add(list[i]);
				for(File file : msifiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:5");
						System.out.println(e);
						System.exit(5);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".java"))
			{
				List<File> javafiles = new ArrayList<File>();
				String path = End +"\\" + ".JAVA" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				javafiles.add(list[i]);
				for(File file : javafiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:6");
						System.out.println(e);
						System.exit(6);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".pdf"))
			{
				List<File> pdffiles = new ArrayList<File>();
				String path = End +"\\" + ".PDF" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				pdffiles.add(list[i]);
				for(File file : pdffiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:7");
						System.out.println(e);
						System.exit(7);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".7z"))
			{
				List<File> sevenzfiles = new ArrayList<File>();
				String path = End +"\\" + ".7Z" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				sevenzfiles.add(list[i]);
				for(File file : sevenzfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:8");
						System.out.println(e);
						System.exit(8);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".docx"))
			{
				List<File> wordfiles = new ArrayList<File>();
				String path = End +"\\" + "WORD DOCS" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				wordfiles.add(list[i]);
				for(File file : wordfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:9");
						System.out.println(e);
						System.exit(9);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".pptx") || list[i].getName().toLowerCase().endsWith(".ppt"))
			{
				List<File> pptxfiles = new ArrayList<File>();
				String path = End +"\\" + "POWERPOINTS" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				pptxfiles.add(list[i]);
				for(File file : pptxfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:10");
						System.out.println(e);
						System.exit(10);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".sql"))
			{
				List<File> sqlfiles = new ArrayList<File>();
				String path = End +"\\" + ".SQL" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				sqlfiles.add(list[i]);
				for(File file : sqlfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:11");
						System.out.println(e);
						System.exit(11);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".torrent"))
			{
				List<File> torrentfiles = new ArrayList<File>();
				String path = End +"\\" + ".TORRENT" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				torrentfiles.add(list[i]);
				for(File file : torrentfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:12");
						System.out.println(e);
						System.exit(12);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".3ds"))
			{
				List<File> threedsfiles = new ArrayList<File>();
				String path = End +"\\" + ".3DS" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				threedsfiles.add(list[i]);
				for(File file : threedsfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:13");
						System.out.println(e);
						System.exit(13);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".txt"))
			{
				List<File> txtfiles = new ArrayList<File>();
				String path = End +"\\" + ".TXT" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				txtfiles.add(list[i]);
				for(File file : txtfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:14");
						System.out.println(e);
						System.exit(14);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".mp3") || list[i].getName().toLowerCase().endsWith(".ogg") || list[i].getName().toLowerCase().endsWith(".wma") || list[i].getName().toLowerCase().endsWith(".wav"))
			{
				List<File> audiofiles = new ArrayList<File>();
				String path = End +"\\" + "Audio" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				audiofiles.add(list[i]);
				for(File file : audiofiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:15");
						System.out.println(e);
						System.exit(15);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".mp4") || list[i].getName().toLowerCase().endsWith(".flv") || list[i].getName().toLowerCase().endsWith(".mkv") || list[i].getName().toLowerCase().endsWith(".avi") || list[i].getName().toLowerCase().endsWith(".m4p") || list[i].getName().toLowerCase().endsWith(".m4v") || list[i].getName().toLowerCase().endsWith(".mpeg") || list[i].getName().toLowerCase().endsWith(".wmv"))
			{
				List<File> videofiles = new ArrayList<File>();
				String path = End +"\\" + "Video" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				videofiles.add(list[i]);
				for(File file : videofiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:16");
						System.out.println(e);
						System.exit(16);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".png") || list[i].getName().toLowerCase().endsWith(".jpeg") || list[i].getName().toLowerCase().endsWith(".jpg") || list[i].getName().toLowerCase().endsWith(".bmp") || list[i].getName().toLowerCase().endsWith(".gif"))
			{
				List<File> imagefiles = new ArrayList<File>();
				String path = End +"\\" + "IMAGES" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				imagefiles.add(list[i]);
				for(File file : imagefiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:17");
						System.out.println(e);
						System.exit(17);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".iso"))
			{
				List<File> isofiles = new ArrayList<File>();
				String path = End +"\\" + "ISO" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				isofiles.add(list[i]);
				for(File file : isofiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:18");
						System.out.println(e);
						System.exit(18);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".ini"))
			{
				List<File> inifiles = new ArrayList<File>();
				String path = End +"\\" + "INI" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				inifiles.add(list[i]);
				for(File file : inifiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:19");
						System.out.println(e);
						System.exit(19);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".gz"))
			{
				List<File> gzfiles = new ArrayList<File>();
				String path = End +"\\" + "GZ" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				gzfiles.add(list[i]);
				for(File file : gzfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:20");
						System.out.println(e);
						System.exit(20);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".tar"))
			{
				List<File> tarfiles = new ArrayList<File>();
				String path = End +"\\" + "TAR" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				tarfiles.add(list[i]);
				for(File file : tarfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:21");
						System.out.println(e);
						System.exit(21);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".stl"))
			{
				List<File> stlfiles = new ArrayList<File>();
				String path = End +"\\" + "STL" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				stlfiles.add(list[i]);
				for(File file : stlfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:22");
						System.out.println(e);
						System.exit(22);
					}
				}
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".hex"))
			{
				List<File> hexfiles = new ArrayList<File>();
				String path = End +"\\" + "HEX" + "\\";
				try {
					createFolders cf = new createFolders();
					cf.createFolder(path);
					cf.join();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				hexfiles.add(list[i]);
				for(File file : hexfiles)
				{
					try
					{
							Files.move(file.toPath(), (new File(path +
							file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}	 
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:23");
						System.out.println(e);
						System.exit(23);
					}
				}
				copiedTrue = true;
			}
			*/
			/*Add new if statements here for new filetypes
			 * The current filetypes include:
			 * zip,exe,msi,java,pdf,7z,docx,doc,ppt,pptx,sql,torrent,3ds,txt
			 * mp3,ogg,wma,wav,mp4,flv,mkv,avi,m4p,m4v,mpeg,wmv,png,jpeg,jpg
			 * bmp,gif,iso,ini,gz,tar,stl,hex
			 */
		/*
			else 
			{
				i++;
			}
		}
		return copiedTrue;*/
	}
	protected boolean copyFileCheck(boolean copiedTrue)
	{	
		/*This method checks if there are any files of the current type in the starting location
		 * and if there are, a folder is created and the files are copied. 
		 */
		int i = 0;
		int k = 0;
		List<File> files = new ArrayList<File>();
		while(i < list.length - 1)
		{
			list = Start.listFiles();
			if(list[i].getName().toLowerCase().endsWith(".zip"))
			{
				
				String path = End +"\\" + ".ZIP" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:24");
					System.out.println(e);
					System.exit(24);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".exe"))
			{
				String path = End +"\\" + ".EXE" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:25");
					System.out.println(e);
					System.exit(25);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".msi"))
			{
				String path = End +"\\" + ".MSI" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:26");
				System.out.println(e);
				System.exit(26);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".java"))
			{
				String path = End +"\\" + ".JAVA" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:27");
					System.out.println(e);
					System.exit(27);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".pdf"))
			{
				String path = End +"\\" + ".PDF" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:28");
					System.out.println(e);
					System.exit(28);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".7z"))
			{
				String path = End +"\\" + ".7Z" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:29");
					System.out.println(e);
					System.exit(29);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".docx") || list[i].getName().toLowerCase().endsWith(".doc"))
			{
				String path = End +"\\" + "Word Docs" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:30");
					System.out.println(e);
					System.exit(30);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".ppt") || list[i].getName().toLowerCase().endsWith(".pptx"))
			{
				String path = End +"\\" + "Power Points" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:31");
					System.out.println(e);
					System.exit(31);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".sql"))
			{
				String path = End +"\\" + ".SQL" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:32");
					System.out.println(e);
					System.exit(32);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".torrent"))
			{
				String path = End +"\\" + ".TORRENT" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:33");
					System.out.println(e);
					System.exit(33);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".3ds"))
			{
				String path = End +"\\" + ".3DS" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:34");
					System.out.println(e);
					System.exit(34);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".txt"))
			{
				String path = End +"\\" + ".TXT" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:35");
					System.out.println(e);
					System.exit(35);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".mp3") || list[i].getName().toLowerCase().endsWith(".ogg") || list[i].getName().toLowerCase().endsWith(".wma") || list[i].getName().toLowerCase().endsWith(".wav"))
			{
				String path = End +"\\" + "AUDIO" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:36");
					System.out.println(e);
					System.exit(36);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".mp4") || list[i].getName().toLowerCase().endsWith(".flv") || list[i].getName().toLowerCase().endsWith(".mkv") || list[i].getName().toLowerCase().endsWith(".avi") || list[i].getName().toLowerCase().endsWith(".m4p") || list[i].getName().toLowerCase().endsWith(".m4v") || list[i].getName().toLowerCase().endsWith(".mpeg") || list[i].getName().toLowerCase().endsWith(".wmv"))
			{
				String path = End +"\\" + "VIDEO" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:37");
					System.out.println(e);
					System.exit(37);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".png") || list[i].getName().toLowerCase().endsWith(".jpeg") || list[i].getName().toLowerCase().endsWith(".jpg") || list[i].getName().toLowerCase().endsWith(".bmp") || list[i].getName().toLowerCase().endsWith(".gif"))
			{
				String path = End +"\\" + "IMAGE" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:38");
					System.out.println(e);
					System.exit(38);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".iso"))
			{
				String path = End +"\\" + "ISO" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:39");
					System.out.println(e);
					System.exit(39);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".ini"))
			{
				String path = End +"\\" + "INI" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:40");
					System.out.println(e);
					System.exit(40);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".gz"))
			{
				String path = End +"\\" + "GZ" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:41");
					System.out.println(e);
					System.exit(41);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".tar"))
			{
				String path = End +"\\" + "TAR" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:42");
					System.out.println(e);
					System.exit(42);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".stl"))
			{
				String path = End +"\\" + "STL" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:43");
					System.out.println(e);
					System.exit(43);
				}
				i++;
				copiedTrue = true;
			}
			else if(list[i].getName().toLowerCase().endsWith(".hex"))
			{
				String path = End +"\\" + "HEX" + "\\";
				createFolders cf = new createFolders();
				cf.createFolder(path);
				files.add(list[i]);
				File file = files.get(k);
				File file2 = new File("Copy - " + file.getName());
				try
				{
					FileUtils.copyFile(file, file2);
					Files.move(file2.toPath(), (new File(path + file2.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
					k++;
				}	 
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:44");
					System.out.println(e);
					System.exit(44);
				}
				i++;
				copiedTrue = true;
			}
			/*Add new if statements here for new filetypes
			 * The current filetypes include:
			 * zip,exe,msi,java,pdf,7z,docx,doc,ppt,pptx,sql,torrent,3ds,txt
			 * mp3,ogg,wma,wav,mp4,flv,mkv,avi,m4p,m4v,mpeg,wmv,png,jpeg,jpg
			 * bmp,gif,iso,ini,gz,tar,stl,hex
			 */
			else
			{
				i++;
			}
		}
		return copiedTrue;
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
