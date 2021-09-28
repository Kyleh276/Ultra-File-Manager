/*************************************************************************
** Author: JiinxC														**
** Name: Ultra File Manager	   									    	**
** Purpose: Used to quickly move or copy many files for point A to B.  	**
** Date: 10/6/2017														**
*************************************************************************/		
package problemDomain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class fmGui
{
	//All needed objects for the GUI are created here.
	
	// need to tweak this file, and optimize it.
	
	private JFrame frmSabreFileManager;
	private ImageIcon img = new ImageIcon("res/FileManager.png");
	private JTextField destinationField;
	private JTextField sourceField;
	private JTextField customField;
	
	private JPanel mainPanel = new JPanel();
	private JPanel movePanel = new JPanel();
	private JPanel copyPanel = new JPanel();
	private JPanel activePanel = new JPanel();
	
	private JLabel lblStart = new JLabel("Please Select A File Operation:");
	private JLabel lbldesc1 = new JLabel("To begin choose a source");
	private JLabel lbldesc2 = new JLabel(" and destination for your files:");
	private JLabel lblDestination = new JLabel("Destination:");
	private JLabel lblSource = new JLabel("Source:");
	private JLabel lblCustomFiletype = new JLabel("Custom Filetype:");
	private JLabel lblFileTypes = new JLabel("What file types do you want to move:");
	
	private JButton btnExit = new JButton("Exit Program");
	private JButton btnMoveFiles = new JButton("Move Files");
	private JButton btnOpenGithub = new JButton("Open Github");
	private JButton btnCopyFiles = new JButton("Copy Files");
	private JButton btnSrc = new JButton("...");
	private JButton btnDst = new JButton("...");
	
	private ButtonGroup radio = new ButtonGroup();
	
	private JRadioButton rdbtnCustomOnly = new JRadioButton("Custom Only");
	private JRadioButton rdbtnDefaultOnly = new JRadioButton("Default Only");
	private JRadioButton rdbtnBoth = new JRadioButton("Both");
	
	private JButton moveFilesFinal = new JButton("Move Files");
	private JButton copyFilesFinal = new JButton("Copy Files");
	private JButton mainMenuBtn = new JButton("Main Menu");
	
	private ActionListener actionListener = new MyActionListener();
	
	
	
	// make the move and copy screen one, and have a radio button to pick which one you use.
	
	//duplicates that could be designed out.
	private JTextField destinationFieldCopy;
	private JTextField sourceFieldCopy;
	private JTextField customFieldCopy;
	private JLabel lblCustomFiletypeCopy = new JLabel("Custom Filetype:");
	private JLabel lblFileTypesCopy = new JLabel("What file types do you want to copy:");
	private JLabel lbldesc2copy = new JLabel(" and destination for your files:");
	private JLabel lbldesc1copy = new JLabel("To begin choose a source");
	private JLabel lblDestinationCopy = new JLabel("Destination:");
	private JLabel lblSourceCopy = new JLabel("Source:");
	private JRadioButton rdbtnCustomOnlyCopy = new JRadioButton("Custom Only");
	private JRadioButton rdbtnDefaultOnlyCopy = new JRadioButton("Default Only");
	private JRadioButton rdbtnBothCopy = new JRadioButton("Both");
	private JButton mainMenuBtnCopy = new JButton("Main Menu");
	public JFileChooser SourceFC = new JFileChooser();
	public JFileChooser DestFC = new JFileChooser();
	private JButton btnSrcCopy = new JButton("...");
	private JButton btnDstCopy = new JButton("...");
	private ButtonGroup radioCopy = new ButtonGroup();
	
	
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
		/*This is the meat of the GUI and contains all of the color and positional settings of the buttons and labels
		 * As well as that it also creates all of the Label, button, actionListener, JFrame, and JPanel object that the GUI uses.
		 * This section looks intimidating at first glance but is very repetitive upon closer inspection. 
		 */
		frmSabreFileManager = new JFrame();
		frmSabreFileManager.setIconImage(img.getImage());
		frmSabreFileManager.setResizable(false);
		frmSabreFileManager.setTitle("Ultra File Manager");
		frmSabreFileManager.setForeground(Color.WHITE);
		frmSabreFileManager.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		frmSabreFileManager.setBackground(Color.BLACK);
		frmSabreFileManager.getContentPane().setBackground(Color.BLACK);
		frmSabreFileManager.getContentPane().setForeground(Color.WHITE);
		frmSabreFileManager.setBounds(100, 100, 324, 184);
		frmSabreFileManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSabreFileManager.getContentPane().setLayout(null);
		frmSabreFileManager.getContentPane().add(mainPanel);
		frmSabreFileManager.setVisible(true);
		
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBounds(0, 0, 319, 152);
		mainPanel.setLayout(null);
		
		lblStart.setBounds(55, 5, 279, 16);
		mainPanel.add(lblStart);
		lblStart.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		lblStart.setForeground(Color.WHITE);
				
		btnMoveFiles.setBounds(86, 35, 153, 25);
		btnMoveFiles.addActionListener(actionListener);
		mainPanel.add(btnMoveFiles);
		btnMoveFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnMoveFiles.setBackground(Color.BLACK);
		btnMoveFiles.setForeground(Color.WHITE);
		
		btnCopyFiles.setBounds(86, 61, 153, 25);
		btnCopyFiles.addActionListener(actionListener);
		mainPanel.add(btnCopyFiles);
		btnCopyFiles.setForeground(Color.WHITE);
		btnCopyFiles.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnCopyFiles.setBackground(Color.BLACK);
		
		btnOpenGithub.setBounds(86, 87, 153, 25);
		mainPanel.add(btnOpenGithub);
		btnOpenGithub.addActionListener(actionListener);
	
		btnOpenGithub.setForeground(Color.WHITE);
		btnOpenGithub.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnOpenGithub.setBackground(Color.BLACK);
		
		btnExit.setBounds(86, 114, 153, 25);
		btnExit.addActionListener(actionListener);
		mainPanel.add(btnExit);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		btnExit.setBackground(Color.BLACK);
		
		movePanel.setBackground(Color.BLACK);
		movePanel.setBounds(0, 0, 335, 274);
		frmSabreFileManager.getContentPane().add(movePanel);
		movePanel.setLayout(null);
		
		lbldesc2.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc2.setForeground(Color.WHITE);
		lbldesc2.setBounds(39, 35, 257, 22);
		movePanel.add(lbldesc2);
		
		lbldesc1.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc1.setForeground(Color.WHITE);
		lbldesc1.setBounds(71, 17, 193, 16);
		movePanel.add(lbldesc1);
		
		lbldesc1copy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc1copy.setForeground(Color.WHITE);
		lbldesc1copy.setBounds(71, 17, 193, 16);
		copyPanel.add(lbldesc1copy);
		lbldesc2copy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lbldesc2copy.setForeground(Color.WHITE);
		lbldesc2copy.setBounds(39, 35, 257, 22);
		copyPanel.add(lbldesc2copy);
		
		destinationField = new JTextField();
		destinationField.setForeground(Color.WHITE);
		destinationField.setEditable(false);
		destinationField.setColumns(10);
		destinationField.setBackground(Color.LIGHT_GRAY);
		destinationField.setBounds(145, 94, 134, 25);
		movePanel.add(destinationField);
		
		lblDestination.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblDestination.setForeground(Color.WHITE);
		lblDestination.setBounds(30, 95, 103, 22);
		movePanel.add(lblDestination);
		
		lblSource.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblSource.setForeground(Color.WHITE);
		lblSource.setBounds(30, 65, 115, 22);
		movePanel.add(lblSource);
		
		btnDst.setForeground(Color.WHITE);
		btnDst.setFont(new Font("Arial", Font.BOLD, 16));
		btnDst.setBackground(Color.GRAY);
		btnDst.setBounds(280, 94, 25, 25);
		btnDst.addActionListener(actionListener);
		movePanel.add(btnDst);
		
		sourceField = new JTextField();
		sourceField.setForeground(Color.WHITE);
		sourceField.setEditable(false);
		sourceField.setColumns(10);
		sourceField.setBackground(Color.LIGHT_GRAY);
		sourceField.setBounds(145, 65, 134, 25);
		movePanel.add(sourceField);
		
		btnSrc.setForeground(Color.WHITE);
		btnSrc.addActionListener(actionListener);
		btnSrc.setFont(new Font("Arial", Font.BOLD, 16));
		btnSrc.setBackground(Color.GRAY);
		btnSrc.setBounds(280, 65, 25, 25);
		movePanel.add(btnSrc);
		
		lblCustomFiletype.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblCustomFiletype.setForeground(Color.WHITE);
		lblCustomFiletype.setBounds(30, 123, 128, 22);
		movePanel.add(lblCustomFiletype);
		
		customField = new JTextField();
		customField.setForeground(Color.WHITE);
		customField.setEditable(false);
		customField.setColumns(10);
		customField.setBackground(Color.LIGHT_GRAY);
		customField.setBounds(177, 123, 128, 25);
		movePanel.add(customField);
		
		lblFileTypes.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypes.setForeground(Color.WHITE);
		lblFileTypes.setBounds(23, 163, 289, 16);
		movePanel.add(lblFileTypes);
		
		rdbtnCustomOnly.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnCustomOnly.setBackground(Color.BLACK);
		rdbtnCustomOnly.setForeground(Color.WHITE);
		rdbtnCustomOnly.setBounds(23, 188, 128, 25);
		rdbtnCustomOnly.addActionListener(actionListener);
		movePanel.add(rdbtnCustomOnly);
		
		rdbtnDefaultOnly.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnDefaultOnly.setForeground(Color.WHITE);
		rdbtnDefaultOnly.setBackground(Color.BLACK);
		rdbtnDefaultOnly.setBounds(23, 209, 134, 25);
		rdbtnDefaultOnly.addActionListener(actionListener);
		movePanel.add(rdbtnDefaultOnly);
		
		rdbtnBoth.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnBoth.setForeground(Color.WHITE);
		rdbtnBoth.setBackground(Color.BLACK);
		rdbtnBoth.setBounds(23, 230, 103, 25);
		rdbtnBoth.addActionListener(actionListener);
		movePanel.add(rdbtnBoth);
		
		radio.add(rdbtnCustomOnly);
		radio.add(rdbtnDefaultOnly);
		radio.add(rdbtnBoth);
				
		moveFilesFinal.setForeground(Color.WHITE);
		moveFilesFinal.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		moveFilesFinal.setBackground(Color.BLACK);
		moveFilesFinal.setBounds(187, 205, 118, 25);
		moveFilesFinal.addActionListener(actionListener);
		movePanel.add(moveFilesFinal);
		
		mainMenuBtn.setForeground(Color.WHITE);
		mainMenuBtn.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		mainMenuBtn.setBackground(Color.BLACK);
		mainMenuBtn.setBounds(187, 230, 118, 25);
		mainMenuBtn.addActionListener(actionListener);
		movePanel.add(mainMenuBtn);
		mainPanel.setVisible(true);
		movePanel.setVisible(false);
		copyPanel.setVisible(false);
		activePanel = mainPanel;
		
		//Copy Panel initialization
		
		copyPanel.setLayout(null);
		copyPanel.setBounds(0, 0, 335, 274);
		copyPanel.setBackground(Color.BLACK);
		
		lblCustomFiletypeCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblCustomFiletypeCopy.setBounds(30, 123, 128, 22);
		lblCustomFiletypeCopy.setForeground(Color.WHITE);
		copyPanel.add(lblCustomFiletypeCopy);
		
		lblSourceCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblSourceCopy.setForeground(Color.WHITE);
		lblSourceCopy.setBounds(30, 65, 115, 22);
		copyPanel.add(lblSourceCopy);
		
		lblDestinationCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblDestinationCopy.setForeground(Color.WHITE);
		lblDestinationCopy.setBounds(30, 95, 103, 22);
		copyPanel.add(lblDestinationCopy);
		
		btnSrcCopy.setForeground(Color.WHITE);
		btnSrcCopy.addActionListener(actionListener);
		btnSrcCopy.setFont(new Font("Arial", Font.BOLD, 16));
		btnSrcCopy.setBackground(Color.GRAY);
		btnSrcCopy.setBounds(280, 65, 25, 25);
		copyPanel.add(btnSrcCopy);
		
		btnDstCopy.setForeground(Color.WHITE);
		btnDstCopy.setFont(new Font("Arial", Font.BOLD, 16));
		btnDstCopy.setBackground(Color.GRAY);
		btnDstCopy.setBounds(280, 94, 25, 25);
		btnDstCopy.addActionListener(actionListener);
		copyPanel.add(btnDstCopy);
		
		customFieldCopy = new JTextField();
		customFieldCopy.setForeground(Color.WHITE);
		customFieldCopy.setEditable(false);
		customFieldCopy.setColumns(10);
		customFieldCopy.setBackground(Color.LIGHT_GRAY);
		customFieldCopy.setBounds(177, 123, 128, 25);
		copyPanel.add(customFieldCopy);
		
		sourceFieldCopy = new JTextField();
		sourceFieldCopy.setForeground(Color.WHITE);
		sourceFieldCopy.setEditable(false);
		sourceFieldCopy.setColumns(10);
		sourceFieldCopy.setBackground(Color.LIGHT_GRAY);
		sourceFieldCopy.setBounds(145, 65, 134, 25);
		copyPanel.add(sourceFieldCopy);
		
		destinationFieldCopy = new JTextField();
		destinationFieldCopy.setForeground(Color.WHITE);
		destinationFieldCopy.setEditable(false);
		destinationFieldCopy.setColumns(10);
		destinationFieldCopy.setBackground(Color.LIGHT_GRAY);
		destinationFieldCopy.setBounds(145, 94, 134, 25);
		copyPanel.add(destinationFieldCopy);
		
		lblFileTypesCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblFileTypesCopy.setForeground(Color.WHITE);
		lblFileTypesCopy.setBounds(23, 163, 289, 16);
		copyPanel.add(lblFileTypesCopy);
		
		rdbtnCustomOnlyCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnCustomOnlyCopy.setBackground(Color.BLACK);
		rdbtnCustomOnlyCopy.setForeground(Color.WHITE);
		rdbtnCustomOnlyCopy.setBounds(23, 188, 128, 25);
		rdbtnCustomOnlyCopy.addActionListener(actionListener);
		copyPanel.add(rdbtnCustomOnlyCopy);
		
		rdbtnDefaultOnlyCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnDefaultOnlyCopy.setForeground(Color.WHITE);
		rdbtnDefaultOnlyCopy.setBackground(Color.BLACK);
		rdbtnDefaultOnlyCopy.setBounds(23, 209, 134, 25);
		rdbtnDefaultOnlyCopy.addActionListener(actionListener);
		copyPanel.add(rdbtnDefaultOnlyCopy);
		
		rdbtnBothCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		rdbtnBothCopy.setForeground(Color.WHITE);
		rdbtnBothCopy.setBackground(Color.BLACK);
		rdbtnBothCopy.setBounds(23, 230, 103, 25);
		rdbtnBothCopy.addActionListener(actionListener);
		copyPanel.add(rdbtnBothCopy);
		
		radioCopy.add(rdbtnCustomOnlyCopy);
		radioCopy.add(rdbtnDefaultOnlyCopy);
		radioCopy.add(rdbtnBothCopy);
		
		copyFilesFinal.setForeground(Color.WHITE);
		copyFilesFinal.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		copyFilesFinal.setBackground(Color.BLACK);
		copyFilesFinal.setBounds(187, 205, 118, 25);
		copyFilesFinal.addActionListener(actionListener);
		copyPanel.add(copyFilesFinal);
		
		mainMenuBtnCopy.setForeground(Color.WHITE);
		mainMenuBtnCopy.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		mainMenuBtnCopy.setBackground(Color.BLACK);
		mainMenuBtnCopy.setBounds(187, 230, 118, 25);
		mainMenuBtnCopy.addActionListener(actionListener);
		copyPanel.add(mainMenuBtnCopy);
		frmSabreFileManager.getContentPane().add(copyPanel);
		
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
			sourceFieldCopy.setText(SourceFC.getSelectedFile().toString());
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
			destinationFieldCopy.setText(DestFC.getSelectedFile().toString());
		}
		
	}
private class MyActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{	
			if(e.getSource() == btnSrc)
			{
				//Tells the GUI to bring up the path selector for source.
				chooseSrc();
			}
			else if(e.getSource() == btnSrcCopy)
			{
				//Tells the GUI to bring up the path selector for source.
				chooseSrc();
			}
			else if(e.getSource() == btnDst)
			{
				//Tells the GUI to bring up the path selector for destination. 
				chooseDst();
			}
			else if(e.getSource() == btnDstCopy)
			{
				//Tells the GUI to bring up the path selector for destination. 
				chooseDst();
			}
			else if(e.getSource() == btnMoveFiles)
			{
				/*This method will make the main panel invisible 
				 * then make the move panel visible and resize the window.
				 * it will also follow the last position of the window when 
				 * resizing instead of putting it at a hard coded location.
				 */
				copyPanel.setVisible(false);
				mainPanel.setVisible(false);
				movePanel.setVisible(true);
				activePanel = movePanel;
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 310);
			}
			else if(e.getSource() == btnCopyFiles)
			{
				/*This method will make the main panel invisible 
				 * then make the copy panel visible and resize the window.
				 * it will also follow the last position of the window when 
				 * resizing instead of putting it at a hardcoded location. 
				 */
				movePanel.setVisible(false);
				mainPanel.setVisible(false);
				copyPanel.setVisible(true);
				activePanel = copyPanel;
				frmSabreFileManager.setBounds(frmSabreFileManager.getBounds().x, frmSabreFileManager.getBounds().y, 335, 310);
			}
			else if(e.getSource() == btnExit)
			{
				//If the exit button on the main panel is clicked, exit the program without an error code.
				System.exit(0);
			}
			else if(e.getSource() == moveFilesFinal)
			{
				
				if(JOptionPane.showConfirmDialog(activePanel, "This can take a while depending on file sizes.\r\n                         Continue?") == 0)
				{
					//If the Move panels' move files button is clicked, starts the movement operation.
					if(!sourceField.getText().equals("") && !destinationField.getText().equals(""))
					{
						/*Checks that the source and destinations for the file operations are not blank.
						 * Then checks which of the radio buttons is clicked and applies settings accordingly.
						 */
						if(rdbtnBoth.isSelected())
						{
							
							/*Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved then sets the custom filetype.
							 * Finally moves the files that are included in the specified settings.
							 * (Custom filetype and Default types in this case.)
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceField.getText());
							fm.setEnd(destinationField.getText());
							fm.setCustom(customField.getText());
							fm.moveFiles(activePanel);
							
						}
						if(rdbtnCustomOnly.isSelected())
						{
							/*Creates a new FileManager object and sets the starting point and end point 
							 * for the files to be moved then sets the custom filetype.
							 * Finally moves the files that are included in the specified settings.
							 * (Custom filetype only in this case)
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceField.getText());
							fm.setEnd(destinationField.getText());
							fm.setCustom(customField.getText());
							
						}
						if(rdbtnDefaultOnly.isSelected())
						{
							/*
							 * Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved. Then moves the specified files to the destination folder.
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceField.getText());
							fm.setEnd(destinationField.getText());
							
						}
					}
				}
				
			}
			else if(e.getSource() == copyFilesFinal)
			{

				if(JOptionPane.showConfirmDialog(activePanel, "This can take a while depending on file sizes.\r\n                         Continue?") == 0)
				{
					if(!sourceFieldCopy.getText().equals("") && !destinationFieldCopy.getText().equals(""))
					{
						/*Checks that the source and destinations for the file operations are not blank.
						 * Then checks which of the radio buttons is clicked and applies settings accordingly.
						 */
						if(rdbtnBothCopy.isSelected())
						{
							/*Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved then sets the custom filetype.
							 * Finally moves the files that are included in the specified settings.
						 	* (Custom filetype and Default types in this case.)
						 	*/
							FileManager fm = new FileManager();
							fm.setStart(sourceFieldCopy.getText());
							fm.setEnd(destinationFieldCopy.getText());
							fm.setCustom(customFieldCopy.getText());
							
						}
						if(rdbtnCustomOnlyCopy.isSelected())
						{
							/*Creates a new FileManager object and sets the starting point and end point 
							 * for the files to be moved then sets the custom filetype.
							 * Finally moves the files that are included in the specified settings.
							 * (Custom filetype only in this case)
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceFieldCopy.getText());
							fm.setEnd(destinationFieldCopy.getText());
							fm.setCustom(customFieldCopy.getText());
							
						}
						if(rdbtnDefaultOnlyCopy.isSelected())
						{
							/*
							 * Creates a new FileManager object and sets the starting point and end point
							 * for the files to be moved. Then moves the specified files to the destination folder.
							 */
							FileManager fm = new FileManager();
							fm.setStart(sourceFieldCopy.getText());
							fm.setEnd(destinationFieldCopy.getText());
							
						}
					}
				}
			}
			else if(e.getSource() == rdbtnBoth)
			{
				//Makes it so that when you click on the both radio button you can specify a custom filetype
				customField.setEditable(true);
			}
			else if(e.getSource() == rdbtnCustomOnly)
			{
				//Makes the custom field ediable when you click on the custom filetype button
				customField.setEditable(true);
			}
			else if(e.getSource() == rdbtnDefaultOnly)
			{
				//makes custom field become uneditable until another option is selected.
				customField.setEditable(false);
			}
			else if(e.getSource() == rdbtnBothCopy)
			{
				//Makes it so that when you click on the both radio button you can specify a custom filetype
				customFieldCopy.setEditable(true);
			}
			else if(e.getSource() == rdbtnCustomOnlyCopy)
			{
				//Makes the custom field ediable when you click on the custom filetype button
				customFieldCopy.setEditable(true);
			}
			else if(e.getSource() == rdbtnDefaultOnlyCopy)
			{
				//makes custom field become uneditable until another option is selected.
				customFieldCopy.setEditable(false);
			}
			else if(e.getSource() == mainMenuBtn)
			{
				/*Takes the GUI back to the main menu by making the move panel invisible and the main panel visible again.
				*also inherits the position on screen form the previous panel.
				*/
				int x = frmSabreFileManager.getX();
				int y = frmSabreFileManager.getY();
				mainPanel.setVisible(true);
				movePanel.setVisible(false);
				activePanel = mainPanel;
				frmSabreFileManager.setBounds(x, y, 324, 184);
			}
			else if(e.getSource() == mainMenuBtnCopy)
			{
				/*Takes the GUI back to the main menu by making the move panel invisible and the main panel visible again.
				*also inherits the position on screen form the previous panel.
				*/
				int x = frmSabreFileManager.getX();
				int y = frmSabreFileManager.getY();
				mainPanel.setVisible(true);
				movePanel.setVisible(false);
				copyPanel.setVisible(false);
				activePanel = mainPanel;
				frmSabreFileManager.setBounds(x, y, 324, 184);
			}
			else if(e.getSource() == btnOpenGithub)
			{
				fm.openGithub(activePanel);
			}
		}
	}
}