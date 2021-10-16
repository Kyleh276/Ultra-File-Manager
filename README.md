# Ultra File Manager
This project organizes files within a directory that the user chooses, and moves or copies the files to the destination.
I used java to develop this project because when I had originally written the script that organized my files, I was most comfortable with Java.
As well as being comfortable with Java, I had a real use for a program of this type.
This project was originally written as a simple script without a GUI when I was in college.
It grew as I went through school and learned more, and I added a GUI that helped make it more easily usable.
I went back to the project recently because I still have a use for it.
I wanted to simplify the code, and optimize it as much as I could with what I have learned since I originally made this.
I am satisfied with how much cleaner the code is and how much less error prone it has become.

#### Features i'd like to add:

 - Add a real-time loading bar for each file, so the user is not left hanging when a large file is being moved.
 - Add yellow borders to the buttons to go with the colour theme of an ultra-ball from pokemon.
 - Add a section in the gui that holds a list of files that failed to copy or move for any reason.

#### Requirements:

- Windows 10
- [Java 8 (64 Bit)](https://java.com/en/download/)

#### Installation:

This program was written on and tested for Windows 10, other Windows operating systems may work, but until I test them I can only confirm 
that it will work as intended on Windows 10.

##### Install Java 8 for Windows:

[Java 8](https://java.com/en/download/)
	
##### Download Ultra-File-Manager.jar

Download the `Ultra-File-Manager.jar` file.

##### Startup:

Double click on the `Ultra-File-Manager.jar` file.

#### How to use:

- Open the `Ultra-File-Manager.jar` file.
- Click on the `Manage Files` Button.
- Select the `Source Directory` that the files you want to organize and move/copy are currently located by clicking on the button next to the text field.`[...]`
- Select the `Destination directory` that the files will be moved/copied to by clicking the button next to the corresponding text field.`[...]`
- Select the file-types that you wish to move/copy by clicking the corresponding radio button. `All Types` / `Custom Types`
		
###### Custom Types:

- If you clicked the `Custom Types` radio button,  add the `file-type` by typing it in the blank text field next to the `Add` button.
- In order to add a file-type, you must follow the proper syntax `.FILETYPE`, you must have the leading `.` and the character length of the file-type must not exceed `12`. 
- You can remove the currently selected `file-type` from the custom list by clicking on the `Remove` button.
- The custom list can hold up to `50` different file-types at once.
- Duplicates are not allowed.

##### Start Operation:

- Select the operation type `Copy Files` / `Move Files`
- When you are ready, click the `Copy Files` or `Move Files` button.
- This usually doesn't take too long but depending on the size of files you copy or move, the operation will take longer.

#### Copyright Details:

- This code is not for sale and should never be charged for.
- I am the copyright owner of this project's code, if you wish to modify or use this code, please contact me directly.
- The copyright permissions for this code do not allow any unintended use, modification, or monetization without a written and legally binding agreement with me.