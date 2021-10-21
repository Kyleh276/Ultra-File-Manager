/*************************************************************************
** Author: Kyle Hendrickson												**
** Name: Ultra File Manager	   									    	**
** Purpose: Used to quickly move or copy many files for point A to B.  	**
** Date: 10/6/2017														**
*************************************************************************/		
package problemDomain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class fmGui
{
	// Set up all the variables the GUI will need.
	
	// Set up the frame for the window.
	private JFrame frmSabreFileManager;
	
	// Set up the icon for the window.
	private ImageIcon img = new ImageIcon("res/FileManager.png");
	
	// Set up the panels that the GUI will switch between.
	private JPanel mainPanel = new JPanel();
	private JPanel filePanel = new JPanel();
	private JPanel activePanel = new JPanel();

	// Set up the text fields for the filePanel.
	private JTextField destinationField;
	private JTextField sourceField;
	private JTextField addFileTypeField;
	
	// Set up the labels for all panels.
	private JLabel lblStart = new JLabel("Ultra File Manager");
	private JLabel lbldesc1 = new JLabel("Select the source and destination:");
	private JLabel lblDestination = new JLabel("Destination:");
	private JLabel lblSource = new JLabel("Source:");
	private JLabel lblFileTypes = new JLabel("Select the file types to process:");
	private JLabel lblFileOperationType = new JLabel("Select the operation type:");
	private JLabel lblFileTypeList = new JLabel("Add the desired file types:");
	private JLabel lblFileTypeListSize = new JLabel("File types: 0/50");
	
	// Set up the buttons for each panel.
	private JButton btnManageFiles = new JButton("Manage Files");
	private JButton btnExit = new JButton("Exit");
	private JButton btnOpenGithub = new JButton("Github");
	private JButton startFileOperation = new JButton("Copy Files");
	private JButton mainMenuBtn = new JButton("Main Menu");
	private JButton btnSrc = new JButton("...");
	private JButton btnDst = new JButton("...");
	private JButton btnAddFileType = new JButton("Add");
	private JButton btnDeleteFileType = new JButton("Remove");
	
	// Set up the radio buttons for the file panel.
	private JRadioButton rdbtnMoveFiles = new JRadioButton("Move Files");
	private JRadioButton rdbtnCopyFiles = new JRadioButton("Copy Files");
	private JRadioButton rdbtnAllFileTypes = new JRadioButton("All Types");
	private JRadioButton rdbtnFileTypeList = new JRadioButton("Custom Types");
	
	// Set up the radio button groups for each set of radio buttons.
	private ButtonGroup fileOperationRadio = new ButtonGroup();
	private ButtonGroup fileTypeRadioG = new ButtonGroup();
	
	// Set up the file choosers for file selection through the GUI.
	public JFileChooser SourceFC = new JFileChooser();
	public JFileChooser DestFC = new JFileChooser();
	
	// Set up the variables for the file panel's custom file type list.
	private ArrayList<String> fileTypesList = new ArrayList<>();
	private DefaultListModel<String> fileTypesModel = new DefaultListModel<>();
	private JList<String> fileTypesJList = new JList<>(fileTypesModel);
	private JScrollPane scroller = new JScrollPane(fileTypesJList);
	
	// Set up the FileManager variable. 
	private FileManager fm = new FileManager();
	
	// Create a custom ActionListener for catching user input and interaction.
	private ActionListener actionListener = new MyActionListener();
	
	// This is the main function that creates the GUI object.
	public static void main(String[] args)
	{
		// Create a new thread for the GUI.
		EventQueue.invokeLater(new Runnable()
		{
			// Run the default method.
			public void run()
			{
				// Try to:
				try
				{
					// Create a GUI object.
					fmGui window = new fmGui();
					// Set the GUI's frame as visible.
					window.frmSabreFileManager.setVisible(true);
				}
				// Catch an exception.
				catch (Exception e)
				{
					// Display an error message if the GUI fails to open.
					JOptionPane.showMessageDialog(null, "Something went wrong!! Error Code:45");
				}
			}
		});
	}
	public fmGui()
	{
		//This initializes all the gui's components and creates the panels for main menu, copy and move.
		initialize();
	}
	private void initialize()
	{
		// This method sets up all the variables for the GUI, and then sets the defaults for each element.
		
		// Set the default values for the window.
		frmSabreFileManager = new JFrame();
		frmSabreFileManager.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		frmSabreFileManager.setTitle("Ultra File Manager");
		frmSabreFileManager.setIconImage(img.getImage());
		frmSabreFileManager.setBounds(100, 100, 318, 174);
		frmSabreFileManager.setBackground(Color.BLACK);
		frmSabreFileManager.setForeground(Color.WHITE);
		frmSabreFileManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSabreFileManager.add(mainPanel);
		frmSabreFileManager.add(filePanel);
		frmSabreFileManager.setResizable(false);
		frmSabreFileManager.setLayout(null);
		frmSabreFileManager.setVisible(true);
		
		// Set the default values for the labels.
		
		// Starting Label.
		lblStart.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblStart.setForeground(Color.WHITE);
		lblStart.setBounds(70, 10, 279, 16);
		
		// Description label.
		lbldesc1.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc1.setForeground(Color.WHITE);
		lbldesc1.setBounds(26, 17, 272, 16);
		
		// Source Label.
		lblSource.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblSource.setForeground(Color.WHITE);
		lblSource.setBounds(26, 45, 115, 22);
		
		// Destination Label.
		lblDestination.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblDestination.setForeground(Color.WHITE);
		lblDestination.setBounds(26, 74, 103, 22);
		
		// File Types Label.
		lblFileTypes.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypes.setForeground(Color.WHITE);
		lblFileTypes.setBounds(26, 106, 272, 16);
		
		// File Operation Type Label.
		lblFileOperationType.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileOperationType.setForeground(Color.WHITE);
		lblFileOperationType.setBounds(26, 156, 272, 16);
		
		// File Type List Label.
		lblFileTypeList.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypeList.setForeground(Color.WHITE);
		lblFileTypeList.setBounds(320, 15, 272, 16);
		
		// File Types List Size Label.
		lblFileTypeListSize.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypeListSize.setForeground(Color.WHITE);
		lblFileTypeListSize.setBounds(320,213,150,16);
		
		// Set the default values for the buttons.
		
		// Manage files button.
		btnManageFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnManageFiles.setBackground(Color.BLACK);
		btnManageFiles.setForeground(Color.WHITE);
		btnManageFiles.setBounds(74, 35, 154, 25);
		btnManageFiles.setFocusPainted(false);
		btnManageFiles.addActionListener(actionListener);
		
		// Github button.
		btnOpenGithub.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnOpenGithub.setBackground(Color.BLACK);
		btnOpenGithub.setForeground(Color.WHITE);
		btnOpenGithub.setBounds(74, 59, 154, 25);
		btnOpenGithub.setFocusPainted(false);
		btnOpenGithub.addActionListener(actionListener);
		
		// Exit button.
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(74, 83, 154, 25);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(actionListener);
		
		// Source button.
		btnSrc.setFont(new Font("Arial", Font.BOLD, 16));
		btnSrc.setBackground(Color.BLACK);
		btnSrc.setForeground(Color.WHITE);
		btnSrc.setBounds(270, 45, 25, 25);
		btnSrc.setVerticalAlignment(3);
		btnSrc.setFocusPainted(false);
		btnSrc.addActionListener(actionListener);
		
		// Destination button.
		btnDst.setFont(new Font("Arial", Font.BOLD, 16));
		btnDst.setBackground(Color.BLACK);
		btnDst.setForeground(Color.WHITE);
		btnDst.setBounds(270, 74, 25, 25);
		btnDst.setVerticalAlignment(3);
		btnDst.setFocusPainted(false);
		btnDst.addActionListener(actionListener);
		
		// Add file type button.
		btnAddFileType.setForeground(Color.WHITE);
		btnAddFileType.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnAddFileType.setBackground(Color.BLACK);
		btnAddFileType.setBounds(455, 45, 80, 25);
		btnAddFileType.setFocusPainted(false);
		btnAddFileType.addActionListener(actionListener);
		
		// Delete file type button.
		btnDeleteFileType.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnDeleteFileType.setBackground(Color.BLACK);
		btnDeleteFileType.setForeground(Color.WHITE);
		btnDeleteFileType.setBounds(453, 210, 82, 25);
		btnDeleteFileType.setFocusPainted(false);
		btnDeleteFileType.addActionListener(actionListener);
		
		// Start file operation button.
		startFileOperation.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		startFileOperation.setBackground(Color.BLACK);
		startFileOperation.setForeground(Color.WHITE);
		startFileOperation.setBounds(180, 210, 118, 25);
		startFileOperation.setFocusPainted(false);
		startFileOperation.addActionListener(actionListener);
		
		//  Main menu button.
		mainMenuBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		mainMenuBtn.setBackground(Color.BLACK);
		mainMenuBtn.setForeground(Color.WHITE);
		mainMenuBtn.setBounds(26, 210, 118, 25);
		mainMenuBtn.setFocusPainted(false);
		mainMenuBtn.addActionListener(actionListener);
		
		// Set the default values for the radio buttons.
		
		// All file types radio button.
		rdbtnAllFileTypes.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnAllFileTypes.setBackground(Color.BLACK);
		rdbtnAllFileTypes.setForeground(Color.WHITE);
		rdbtnAllFileTypes.setBounds(26, 126, 118, 25);
		rdbtnAllFileTypes.setSelected(true);
		rdbtnAllFileTypes.setFocusPainted(false);
		rdbtnAllFileTypes.addActionListener(actionListener);
		
		// FileTypeList radio button.
		rdbtnFileTypeList.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnFileTypeList.setBackground(Color.BLACK);
		rdbtnFileTypeList.setForeground(Color.WHITE);
		rdbtnFileTypeList.setBounds(140, 126, 130, 25);
		rdbtnFileTypeList.setFocusPainted(false);
		rdbtnFileTypeList.addActionListener(actionListener);
		
		// Move Files radio button.
		rdbtnMoveFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnMoveFiles.setBackground(Color.BLACK);
		rdbtnMoveFiles.setForeground(Color.WHITE);
		rdbtnMoveFiles.setBounds(140, 180, 134, 25);
		rdbtnMoveFiles.setFocusPainted(false);
		rdbtnMoveFiles.addActionListener(actionListener);
		
		// Copy Files radio button.
		rdbtnCopyFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnCopyFiles.setForeground(Color.WHITE);
		rdbtnCopyFiles.setBackground(Color.BLACK);
		rdbtnCopyFiles.setBounds(26, 180, 110, 25);
		rdbtnCopyFiles.setSelected(true);
		rdbtnCopyFiles.setFocusPainted(false);
		rdbtnCopyFiles.addActionListener(actionListener);
		
		// Add the radio buttons to their corresponding groups.
		fileOperationRadio.add(rdbtnMoveFiles);
		fileOperationRadio.add(rdbtnCopyFiles);
		fileTypeRadioG.add(rdbtnAllFileTypes);
		fileTypeRadioG.add(rdbtnFileTypeList);
		
		// Set the default values for the destinationField.
		destinationField = new JTextField();
		destinationField.setFont(new Font("Arial", Font.BOLD, 13));
		destinationField.setBounds(135, 74, 134, 25);
		destinationField.setSelectedTextColor(Color.BLACK);
		destinationField.setSelectionColor(Color.YELLOW);
		destinationField.setForeground(Color.WHITE);
		destinationField.setBackground(Color.GRAY);
		destinationField.setEditable(false);
		destinationField.setColumns(10);
		destinationField.setBorder(null);	

		// Set the default values for the sourceField.
		sourceField = new JTextField();
		sourceField.setSelectedTextColor(Color.BLACK);
		sourceField.setSelectionColor(Color.YELLOW);
		sourceField.setForeground(Color.WHITE);
		sourceField.setBackground(Color.GRAY);
		sourceField.setEditable(false);
		sourceField.setBorder(null);
		sourceField.setColumns(10);
		sourceField.setBounds(135, 45, 134, 25);
		sourceField.setFont(new Font("Arial", Font.BOLD, 13));
		
		// Set the default values for the addFileTypeField.
		addFileTypeField = new JTextField();
		addFileTypeField.setFont(new Font("Arial", Font.BOLD, 15));
		addFileTypeField.setSelectedTextColor(Color.BLACK);
		addFileTypeField.setSelectionColor(Color.YELLOW);
		addFileTypeField.setForeground(Color.WHITE);
		addFileTypeField.setCaretColor(Color.WHITE);
		addFileTypeField.setBackground(Color.GRAY);
		addFileTypeField.setEditable(false);
		addFileTypeField.setBorder(null);
		addFileTypeField.setColumns(10);
		addFileTypeField.setBounds(320, 45, 134, 25);
		addFileTypeField.addActionListener(actionListener);
		
		// Set the default values for the fileTypesJList.
		fileTypesJList.setFont(new Font("Arial", Font.BOLD, 15));
		fileTypesJList.setSelectionForeground(Color.BLACK);
		fileTypesJList.setSelectionBackground(Color.YELLOW);
		fileTypesJList.setForeground(Color.WHITE);
		fileTypesJList.setBackground(Color.GRAY);
		fileTypesJList.setModel(fileTypesModel);
		fileTypesJList.setBounds(320,74,214,130);
		fileTypesJList.setVisible(true);
		fileTypesJList.setFixedCellWidth(214);
		fileTypesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fileTypesJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		fileTypesJList.setVisibleRowCount(-1);

		// Set the default values for the scroll pane.
		scroller.setBounds(320,74,214,130);
		scroller.setVisible(true);
		scroller.setBorder(null);
		
		// Set the default values for the filePanel.
		filePanel.setBounds(0, 0, 700, 261);
		filePanel.setBackground(Color.BLACK);
		filePanel.setVisible(false);
		filePanel.setLayout(null);
		
		// Add elements to the filePanel.
		filePanel.add(lbldesc1);
		filePanel.add(lblFileTypes);
		filePanel.add(lblFileOperationType);
		filePanel.add(lblFileTypeList);
		filePanel.add(lblSource);
		filePanel.add(lblDestination);
		filePanel.add(lblFileTypeListSize);
		filePanel.add(btnSrc);
		filePanel.add(btnDst);
		filePanel.add(btnAddFileType);
		filePanel.add(btnDeleteFileType);
		filePanel.add(startFileOperation);
		filePanel.add(mainMenuBtn);
		filePanel.add(sourceField);
		filePanel.add(destinationField);
		filePanel.add(addFileTypeField);
		filePanel.add(rdbtnMoveFiles);
		filePanel.add(rdbtnCopyFiles);
		filePanel.add(rdbtnAllFileTypes);
		filePanel.add(rdbtnFileTypeList);
		filePanel.add(scroller);

		// Set the defaults for the mainPanel.
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBounds(0, 0, 318, 152);
		mainPanel.setLayout(null);
		
		// Add elements to the mainPanel.
		mainPanel.add(lblStart);
		mainPanel.add(btnManageFiles);
		mainPanel.add(btnOpenGithub);
		mainPanel.add(btnExit);
		mainPanel.setVisible(true);
		
		// Set the default active panel.
		activePanel = mainPanel;
		
	}
	private void chooseSrc()
	{
		// This method allows the user to select the source folder.
		
		// Set the source folder.
		SourceFC.setCurrentDirectory(new java.io.File("."));
		// Set the name of the file choosing dialogue.
		SourceFC.setDialogTitle("Source Folder");
		// Set the file selection mode to only include folders.
		SourceFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// If the user chooses a folder.
		if(SourceFC.showOpenDialog(activePanel) == JFileChooser.APPROVE_OPTION)
		{
			// Set the sourceField text to the path of the chosen folder.
			sourceField.setText(SourceFC.getSelectedFile().toString());
		}
	}
	private void chooseDst()
	{
		// This method allows the user to select the destination folder.
		
		// Set the destination folder.
		DestFC.setCurrentDirectory(new java.io.File("."));
		// Set the name of the file choosing dialogue.
		DestFC.setDialogTitle("Destination Folder");
		// Set the file selection dialogue to only include folders.
		DestFC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// If the user chooses a folder.
		if(DestFC.showOpenDialog(activePanel) == JFileChooser.APPROVE_OPTION)
		{
			// Set the destField text to the path of the chosen folder.
			destinationField.setText(DestFC.getSelectedFile().toString());
		}
	}
// Create a custom ActionListener class to handle the user input.
private class MyActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{	
			// If the user clicks the mainPanel's manage files button.
			if(e.getSource() == btnManageFiles)
			{
				//This method swaps the GUI to the filePanel, and resizes the window.
				
				// Set the mainPanel as invisible.
				mainPanel.setVisible(false);
				// Set the filePanel as visible.
				filePanel.setVisible(true);
				// Swap the active panel to the filePanel.
				activePanel = filePanel;
				// Resize the window size to the filePanel's size.
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 287);
			}
			// If the user clicks the mainPanel's open github button.
			else if(e.getSource() == btnOpenGithub)
			{
				// Open a dialogue confirming that the user wants to open a web page to the github address.
				fm.openGithub(activePanel);
			}
			// If the user clicks the mainPanel's exit button.
			else if(e.getSource() == btnExit)
			{
				// Exit the program without an error code.
				System.exit(0);
			}
			// If the user clicks the source button on the filePanel.
			else if(e.getSource() == btnSrc)
			{
				// Open the path selector dialogue for source.
				chooseSrc();
			}
			// If the user clicks the destination on the filePanel.
			else if(e.getSource() == btnDst)
			{
				// Open the path selector dialogue for destination.
				chooseDst();
			}
			// If the Add button on the filePanel is clicked, or the user hits the enter key while the add file type field isn't empty.
			else if(e.getSource() == btnAddFileType || e.getSource() == addFileTypeField)
			{
				// If the text starts with exactly one dot, and has between 1 and 10 chars following it, that are alphanumeric.
				if(addFileTypeField.getText().matches("[.]{1}[A-Za-z0-9]{1,11}+"))
				{
					// If the file type is not already in the list and the list is not full.
					if(!fileTypesModel.contains(addFileTypeField.getText().trim().toUpperCase()) && fileTypesModel.getSize() <= 50)
					{
						// Offset the size of the model so that the GUI label is correct.
						int fileTypesModelSize = fileTypesModel.getSize() + 1;
						// Set the GUI label for number of file types in the list to the offset model size.
						lblFileTypeListSize.setText("File types: " + fileTypesModelSize + "/50");
						// Add the user input to the fileTypesModel.
						fileTypesModel.addElement(addFileTypeField.getText().toUpperCase());
						// Add the user input to the fileTypeList.
						fileTypesList.add(addFileTypeField.getText().toUpperCase());
						// Refresh the GUI's JList to the new model.
						fileTypesJList.setModel(fileTypesModel);
					}					
				}
			}
			// If the Delete button on the filePanel is clicked.
			else if(e.getSource() == btnDeleteFileType)
			{
				// Get all selected items in the JList.
				fileTypesJList.getSelectedIndices();
				// Set up counter for number in list to ensure that the index used to remove items is always correct.
				int removed = 0;
				
				int fileTypesModelSize = fileTypesModel.getSize();
				// run loop and remove all selected types.
				for(int selectedType : fileTypesJList.getSelectedIndices())
				{
					// Remove the selected type from the list.
					fileTypesModel.remove(selectedType - removed);
					// Update the size of the model. 
					fileTypesModelSize = fileTypesModel.getSize();
					// Set the text of the label to the new size of the model.
					lblFileTypeListSize.setText("File types: " + fileTypesModelSize + "/50");
					// Remove the type from the list of types.
					fileTypesList.remove(selectedType - removed);
					// Add one to removed.
					removed++;
				}
				// Update the model.
				fileTypesJList.setModel(fileTypesModel);
			}
			// If the user clicks the Copy Files or Move Files button.
			else if(e.getSource() == startFileOperation)
			{
				// If the user clicks yes on the confirm dialogue.
				if(JOptionPane.showConfirmDialog(activePanel, "This can take a while depending on file sizes.\r\n                         Continue?") == 0)
				{
					// If the sourceField is not blank, and the destinationField is not blank.
					if(!sourceField.getText().equals("") && !destinationField.getText().equals(""))
					{
						fm.setStart(sourceField.getText());
						fm.setEnd(destinationField.getText());
						// If the file panels' copy files radio button is clicked, starts the movement operation.
						if(rdbtnCopyFiles.isSelected())
						{
							// If the custom type list radio button is selected. 
							if(rdbtnFileTypeList.isSelected())
							{
								// Call the copyFiles method with the activePanel and the custom list.
								fm.copyFiles(activePanel,fileTypesList);
							}
							// If the custom type list radio button is not selected.
							else
							{
								// Call the copyFiles method with the activePanel and null for the custom list.
								fm.copyFiles(activePanel,null);
							}
						}
						// If the move files radio button is selected.
						if(rdbtnMoveFiles.isSelected())
						{
							// If the custom types radio button is selected.
							if(rdbtnFileTypeList.isSelected())
							{
								// Call the moveFiles method with the activePanel and the custom list.
								fm.moveFiles(activePanel,fileTypesList);
							}
							// If the custom types radio button is not selected.
							else
							{
								// Call the moveFiles method with the activePanel and null for the custom list.
								fm.moveFiles(activePanel,null);
							}
						}
					}
				}
			}
			// If the filePanel's main menu button is clicked.
			else if(e.getSource() == mainMenuBtn)
			{
				// Set the activePanel to the mainPanel.
				activePanel = mainPanel;
				// Set the mainPanel to visible.
				mainPanel.setVisible(true);
				// Set the filePanel to invisible.
				filePanel.setVisible(false);
				// Get the window position.
				int x = frmSabreFileManager.getX();
				int y = frmSabreFileManager.getY();
				// Reset the radio button default selected values.
				rdbtnAllFileTypes.setSelected(true);
				rdbtnCopyFiles.setSelected(true);
				// Reset the add file type field to blank.
				addFileTypeField.setText("");
				// Set the window position to the inherited positions.
				frmSabreFileManager.setBounds(x, y, 318, 174);
			}
			// If the all file types radio button is clicked.
			else if(e.getSource() == rdbtnAllFileTypes)
			{
				// Set the add file types field to blank. 
				addFileTypeField.setText("");
				// Set the add file type field to non editable.
				addFileTypeField.setEditable(false);
				// Set the add file type field to invisible.
				addFileTypeField.setVisible(false);
				// Resize the window to the default size.
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 287);
			}
			// If the custom file types radio button is clicked.
			else if(e.getSource() == rdbtnFileTypeList)
			{
				// Set the add file type field to editable.
				addFileTypeField.setEditable(true);
				// Set the add file type field to visible.
				addFileTypeField.setVisible(true);
				// Set the window size to include the add file custom types GUI elements.
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 572, 287);
			}
			// If the copy files radio button is clicked.
			else if(e.getSource() == rdbtnCopyFiles)
			{
				// Set the start operation button text to the copy option.
				startFileOperation.setText("Copy Files");
			}
			else if(e.getSource() == rdbtnMoveFiles)
			{
				// Set the start operation button text to the move option.
				startFileOperation.setText("Move Files");
			}
		}
	}
}