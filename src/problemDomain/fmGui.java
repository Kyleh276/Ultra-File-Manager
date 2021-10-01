/*************************************************************************
** Author: Kyle Hendrickson												**
** Name: Ultra File Manager	   									    	**
** Purpose: Used to quickly move or copy many files for point A to B.  	**
** Date: 10/6/2017														**
*************************************************************************/		
package problemDomain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class fmGui
{
	//All needed objects for the GUI are created here.
	
	// need to tweak this file, and optimize it.
	
	private JFrame frmSabreFileManager;
	private ImageIcon img = new ImageIcon("res/FileManager.png");
	private JTextField destinationField;
	private JTextField sourceField;
	private JTextField addFileTypeField;
	
	private JPanel mainPanel = new JPanel();
	private JPanel filePanel = new JPanel();
	private JPanel activePanel = new JPanel();
	
	private JLabel lblStart = new JLabel("Ultra File Manager");
	private JLabel lbldesc1 = new JLabel("Select the source and destination:");
	private JLabel lblDestination = new JLabel("Destination:");
	private JLabel lblSource = new JLabel("Source:");
	
	private JLabel lblFileTypes = new JLabel("Select the file types to process:");
	private JLabel lblFileOperationType = new JLabel("Select the operation type:");
	private JLabel lblFileTypeList = new JLabel("Add the desired file types:");
	private JLabel lblFileTypeListSize = new JLabel("File types: 0/50");
	
	private JButton btnManageFiles = new JButton("Manage Files");
	private JButton btnExit = new JButton("Exit");
	private JButton btnOpenGithub = new JButton("Github");
	
	private JButton startFileOperation = new JButton("Copy Files");
	private JButton mainMenuBtn = new JButton("Main Menu");
	private JButton btnSrc = new JButton("...");
	private JButton btnDst = new JButton("...");
	private JButton btnAddFileType = new JButton("Add");
	
	
	
	private DefaultListModel<String> fileTypesModel = new DefaultListModel<>();
	private JList<String> fileTypesJList = new JList<>(fileTypesModel);
	
	private ButtonGroup fileOperationRadio = new ButtonGroup();
	private ButtonGroup fileTypeRadioG = new ButtonGroup();
	
	private JRadioButton rdbtnMoveFiles = new JRadioButton("Move Files");
	private JRadioButton rdbtnCopyFiles = new JRadioButton("Copy Files");
	
	
	private JRadioButton rdbtnAllFileTypes = new JRadioButton("All Types");
	private JRadioButton rdbtnFileTypeList = new JRadioButton("Custom Types");
	
	//private JScrollPane fileTypeScrollPane = new JScrollPane(fileTypesList);
	
	
	// make check boxes for the user to decide the file types.
	
	private ActionListener actionListener = new MyActionListener();
	
	
	
	// make the move and copy screen one, and have a radio button to pick which one you use.
	public JFileChooser SourceFC = new JFileChooser();
	public JFileChooser DestFC = new JFileChooser();
	
	
	private FileManager fm = new FileManager();
	

	public static void main(String[] args)
	{
		//This create a new process for the program to use.
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					fmGui window = new fmGui();
					
					window.frmSabreFileManager.setVisible(true);
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:45");
				}
			}
		});
	}
	public fmGui()
	{
		//This initilizes all the guis components and creates the panels for main menu, copy and move.
		initialize();
	}
	public String getDestination()
	{
		return destinationField.toString();
	}
	private void initialize()
	{
		// This is the meat of the GUI and contains all of the color and positional settings of the buttons and labels
		// As well as that it also creates all of the Label, button, actionListener, JFrame, and JPanel object that the GUI uses.
		// This section looks intimidating at first glance but is very repetitive upon closer inspection. 
		
		// Set the default values for the window.
		frmSabreFileManager = new JFrame();
		frmSabreFileManager.setIconImage(img.getImage());
		frmSabreFileManager.setResizable(false);
		frmSabreFileManager.setTitle("Ultra File Manager");
		frmSabreFileManager.setForeground(Color.WHITE);
		frmSabreFileManager.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		frmSabreFileManager.setBackground(Color.BLACK);
		frmSabreFileManager.getContentPane().setBackground(Color.BLACK);
		frmSabreFileManager.getContentPane().setForeground(Color.WHITE);
		frmSabreFileManager.setBounds(100, 100, 324, 174);
		frmSabreFileManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSabreFileManager.getContentPane().setLayout(null);
		frmSabreFileManager.getContentPane().add(mainPanel);
		frmSabreFileManager.setVisible(true);
		frmSabreFileManager.getContentPane().add(filePanel);
		
		// Set the default values for the labels.
		lblStart.setBounds(80, 10, 279, 16);
		lblStart.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblStart.setForeground(Color.WHITE);
		
		lbldesc1.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc1.setForeground(Color.WHITE);
		lbldesc1.setBounds(26, 17, 272, 16);
		
		lblSource.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblSource.setForeground(Color.WHITE);
		lblSource.setBounds(26, 45, 115, 22);
		
		lblDestination.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblDestination.setForeground(Color.WHITE);
		lblDestination.setBounds(26, 74, 103, 22);
		
		lblFileTypes.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypes.setForeground(Color.WHITE);
		lblFileTypes.setBounds(26, 106, 272, 16);
		
		lblFileOperationType.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileOperationType.setForeground(Color.WHITE);
		lblFileOperationType.setBounds(26, 156, 272, 16);
		
		lblFileTypeList.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypeList.setForeground(Color.WHITE);
		lblFileTypeList.setBounds(320, 15, 272, 16);
		
		lblFileTypeListSize.setBounds(320,213,150,16);
		lblFileTypeListSize.setForeground(Color.WHITE);
		lblFileTypeListSize.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		
		// Set the default values for the buttons.
		btnManageFiles.setBounds(86, 35, 153, 25);
		btnManageFiles.addActionListener(actionListener);
		btnManageFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnManageFiles.setBackground(Color.BLACK);
		btnManageFiles.setForeground(Color.WHITE);
		btnManageFiles.setFocusPainted(false);
		
		btnOpenGithub.setBounds(86, 90, 153, 25);
		btnOpenGithub.addActionListener(actionListener);
		btnOpenGithub.setForeground(Color.WHITE);
		btnOpenGithub.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnOpenGithub.setBackground(Color.BLACK);
		btnOpenGithub.setFocusPainted(false);
		
		btnExit.setBounds(86, 59, 153, 25);
		btnExit.addActionListener(actionListener);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnExit.setBackground(Color.BLACK);
		btnExit.setFocusPainted(false);
		
		btnSrc.setForeground(Color.WHITE);
		btnSrc.addActionListener(actionListener);
		btnSrc.setFont(new Font("Arial", Font.BOLD, 16));
		btnSrc.setBackground(Color.BLACK);
		btnSrc.setBounds(270, 45, 25, 25);
		btnSrc.setVerticalAlignment(3);
		
		btnDst.setForeground(Color.WHITE);
		btnDst.setFont(new Font("Arial", Font.BOLD, 16));
		btnDst.setBackground(Color.BLACK);
		btnDst.setBounds(270, 74, 25, 25);
		btnDst.setVerticalAlignment(3);
		btnDst.addActionListener(actionListener);
		
		btnAddFileType.setForeground(Color.WHITE);
		btnAddFileType.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnAddFileType.setBackground(Color.BLACK);
		btnAddFileType.setBounds(455, 45, 80, 25);
		btnAddFileType.addActionListener(actionListener);
		
		startFileOperation.setForeground(Color.WHITE);
		startFileOperation.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		startFileOperation.setBackground(Color.BLACK);
		startFileOperation.setBounds(180, 210, 118, 25);
		startFileOperation.addActionListener(actionListener);
		
		mainMenuBtn.setForeground(Color.WHITE);
		mainMenuBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		mainMenuBtn.setBackground(Color.BLACK);
		mainMenuBtn.setBounds(26, 210, 118, 25);
		mainMenuBtn.addActionListener(actionListener);
		
		
		// Set the default values for the radio buttons.
		rdbtnAllFileTypes.setForeground(Color.WHITE);
		rdbtnAllFileTypes.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnAllFileTypes.setBackground(Color.BLACK);
		rdbtnAllFileTypes.setBounds(26, 126, 118, 25);
		rdbtnAllFileTypes.addActionListener(actionListener);
		
		rdbtnFileTypeList.setForeground(Color.WHITE);
		rdbtnFileTypeList.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnFileTypeList.setBackground(Color.BLACK);
		rdbtnFileTypeList.setBounds(140, 126, 130, 25);
		rdbtnFileTypeList.addActionListener(actionListener);
		
		rdbtnMoveFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnMoveFiles.setForeground(Color.WHITE);
		rdbtnMoveFiles.setBackground(Color.BLACK);
		rdbtnMoveFiles.setBounds(140, 180, 134, 25);
		rdbtnMoveFiles.addActionListener(actionListener);
		
		rdbtnCopyFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnCopyFiles.setForeground(Color.WHITE);
		rdbtnCopyFiles.setBackground(Color.BLACK);
		rdbtnCopyFiles.setBounds(26, 180, 110, 25);
		rdbtnCopyFiles.addActionListener(actionListener);
		rdbtnCopyFiles.setSelected(true);
		
		
		// Add the radio buttons to their corresponding groups.
		fileOperationRadio.add(rdbtnMoveFiles);
		fileOperationRadio.add(rdbtnCopyFiles);
		fileTypeRadioG.add(rdbtnAllFileTypes);
		fileTypeRadioG.add(rdbtnFileTypeList);
		
		// Set the default values for the text fields.
		destinationField = new JTextField();
		destinationField.setForeground(Color.WHITE);
		destinationField.setEditable(false);
		destinationField.setColumns(10);
		destinationField.setBorder(null);
		destinationField.setBackground(Color.GRAY);
		destinationField.setBounds(135, 74, 134, 25);
		
		sourceField = new JTextField();
		sourceField.setForeground(Color.WHITE);
		sourceField.setEditable(false);
		sourceField.setBorder(null);
		sourceField.setColumns(10);
		sourceField.setBackground(Color.GRAY);
		sourceField.setBounds(135, 45, 134, 25);
		
		addFileTypeField = new JTextField();
		addFileTypeField.setForeground(Color.WHITE);
		addFileTypeField.setEditable(false);
		addFileTypeField.setBorder(null);
		addFileTypeField.setCaretColor(Color.WHITE);
		addFileTypeField.setColumns(10);
		addFileTypeField.setBackground(Color.GRAY);
		addFileTypeField.setBounds(320, 45, 134, 25);
		
		//fileTypesModel.addElement("testing 123");
		fileTypesJList.setModel(fileTypesModel);
		fileTypesJList.setBounds(320,74,214,130);
		fileTypesJList.setVisible(true);
		fileTypesJList.setBackground(Color.GRAY);
		fileTypesJList.setForeground(Color.WHITE);
		fileTypesJList.setFixedCellWidth(214);
		fileTypesJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		fileTypesJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		fileTypesJList.setVisibleRowCount(-1);
		
		//fileTypesJList.setVisible(true);
		// Set the default values for the scroll pane.
		
		// Set the default values for the filePanel.
		// Add elements to the filePanel.
		filePanel.setBackground(Color.BLACK);
		filePanel.setBounds(0, 0, 700, 274);
		filePanel.setLayout(null);
		filePanel.add(lbldesc1);
		filePanel.add(destinationField);
		filePanel.add(lblDestination);
		filePanel.add(lblSource);
		filePanel.add(btnSrc);
		filePanel.add(btnDst);
		filePanel.add(btnAddFileType);
		filePanel.add(sourceField);
		filePanel.add(addFileTypeField);
		filePanel.add(lblFileTypes);
		filePanel.add(lblFileOperationType);
		filePanel.add(lblFileTypeList);
		filePanel.add(rdbtnMoveFiles);
		filePanel.add(rdbtnCopyFiles);
		filePanel.add(lblFileTypeListSize);
		filePanel.add(rdbtnAllFileTypes);
		filePanel.add(rdbtnFileTypeList);
		filePanel.add(fileTypesJList);
		filePanel.add(startFileOperation);
		filePanel.add(mainMenuBtn);
		filePanel.setVisible(false);
		
		// Set the default values for the main panel.
		// Add elements to the mainPanel.
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBounds(0, 0, 319, 152);
		mainPanel.setLayout(null);
		mainPanel.add(lblStart);
		mainPanel.add(btnManageFiles);
		mainPanel.add(btnExit);
		mainPanel.add(btnOpenGithub);
		mainPanel.setVisible(true);
		
		// Set the default active panel.
		activePanel = mainPanel;
		
	}
	private void chooseSrc()
	{
		/*This is the method that will set the source path of the files that will be moved or copied.
		 * It is set to directories only so that you can't pick a file that would break the next operations.
		 */
		SourceFC.setCurrentDirectory(new java.io.File("."));
		SourceFC.setDialogTitle("Source Folder");
		SourceFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(SourceFC.showOpenDialog(activePanel) == JFileChooser.APPROVE_OPTION)
		{
			/*This is intended to help the user visually tell what directories they have selected.
			 * It updates the GUI based on the previous selection.
			 */
			sourceField.setText(SourceFC.getSelectedFile().toString());
		}
	}
	private void chooseDst()
	{
		/*This is the method that will set the destination path of the files that will be moved or copied.
		 * This is also set to directories only to avoid issues later. 
		 */
		DestFC.setCurrentDirectory(new java.io.File("."));
		DestFC.setDialogTitle("Destination Folder");
		DestFC.setLocation(10,10);
		DestFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//Using movePanel here means that the copy option wont create the dialogue in the correct spot.
		if(DestFC.showOpenDialog(activePanel) == JFileChooser.APPROVE_OPTION)
		{
			//This changes the GUIs destination path to what the user selects.
			destinationField.setText(DestFC.getSelectedFile().toString());
		}
		
	}
private class MyActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{	
			
			if(e.getSource() == btnManageFiles)
			{
				/*This method will make the main panel invisible 
				 * then make the move panel visible and resize the window.
				 * it will also follow the last position of the window when 
				 * resizing instead of putting it at a hard coded location.
				 */
				
				mainPanel.setVisible(false);
				filePanel.setVisible(true);
				activePanel = filePanel;
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 296);
			}
			else if(e.getSource() == btnOpenGithub)
			{
				fm.openGithub(activePanel);
			}
			else if(e.getSource() == btnExit)
			{
				//If the exit button on the main panel is clicked, exit the program without an error code.
				System.exit(0);
			}
			else if(e.getSource() == btnSrc)
			{
				//Tells the GUI to bring up the path selector for source.
				chooseSrc();
			}
			else if(e.getSource() == btnDst)
			{
				//Tells the GUI to bring up the path selector for destination. 
				chooseDst();
			}
			else if(e.getSource() == btnAddFileType)
			{
				// if the text starts with exactly one dot, and has between 1 and 10 chars following it, that are alphanumeric.
				if(addFileTypeField.getText().matches("[.]{1}[A-Za-z0-9]{1,11}+"))
				{
					
					// if the fileType is not already in the list and the list is not full.
					if(!fileTypesModel.contains(addFileTypeField.getText().strip()) && fileTypesModel.getSize() <= 50)
					{
						int fileTypesModelSize = fileTypesModel.getSize() + 1;
						lblFileTypeListSize.setText("File types: " + fileTypesModelSize + "/50");
						fileTypesModel.addElement(addFileTypeField.getText());
						fileTypesJList.setModel(fileTypesModel);
						
						
					}
					System.out.println(addFileTypeField.getText());
					// to do:
					// ability to delete from the list
					// scroll bar for list
					// make sure that the radio buttons get set back to default if the user goes to the main menu.
					// organize user file type list into alphabetical order.
					// make the last added file type the current selection
					// make file types capitals?
					
					
				}
			}
			else if(e.getSource() == startFileOperation)
			{
				
				if(JOptionPane.showConfirmDialog(activePanel, "This can take a while depending on file sizes.\r\n                         Continue?") == 0)
				{
					//If the Move panels' move files button is clicked, starts the movement operation.
					if(!sourceField.getText().equals("") && !destinationField.getText().equals(""))
					{
						/*Checks that the source and destinations for the file operations are not blank.
						 * Then checks which of the radio buttons is clicked and applies settings accordingly.
						 */
						if(rdbtnCopyFiles.isSelected())
						{
							
							/*Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved then sets the custom filetype.
							 * Finally moves the files that are included in the specified settings.
							 * (Custom filetype and Default types in this case.)
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceField.getText());
							fm.setEnd(destinationField.getText());
							fm.copyFiles(activePanel);
							
							
						}
						if(rdbtnMoveFiles.isSelected())
						{
							/*
							 * Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved. Then moves the specified files to the destination folder.
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceField.getText());
							fm.setEnd(destinationField.getText());
							fm.moveFiles(activePanel);
							
						}
					}
				}
				
			}
			else if(e.getSource() == mainMenuBtn)
			{
				/*Takes the GUI back to the main menu by making the move panel invisible and the main panel visible again.
				*also inherits the position on screen form the previous panel.
				*/
				int x = frmSabreFileManager.getX();
				int y = frmSabreFileManager.getY();
				mainPanel.setVisible(true);
				filePanel.setVisible(false);
				activePanel = mainPanel;
				frmSabreFileManager.setBounds(x, y, 324, 174);
			}
			
			else if(e.getSource() == rdbtnAllFileTypes)
			{
				addFileTypeField.setText("");
				addFileTypeField.setEditable(false);
				addFileTypeField.setVisible(false);
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 296);
			}
			else if(e.getSource() == rdbtnFileTypeList)
			{
				//Show the gui elements for the custom file type list.
				addFileTypeField.setEditable(true);
				addFileTypeField.setVisible(true);
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 572, 296);
			}
			
			else if(e.getSource() == rdbtnCopyFiles)
			{
				// this function will handle the copy function.
				startFileOperation.setText("Copy Files");
			}
			else if(e.getSource() == rdbtnMoveFiles)
			{
				//makes custom field become uneditable until another option is selected.
				startFileOperation.setText("Move Files");
			}
			
		}
	}
}