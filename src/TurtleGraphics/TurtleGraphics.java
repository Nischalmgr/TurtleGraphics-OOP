 package TurtleGraphics;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import uk.ac.leedsbeckett.oop.LBUGraphics;

public class TurtleGraphics extends LBUGraphics {
	private JFrame mainframe;
	private List<String> commandHistory = new ArrayList<>();
	private JTextArea commandTextArea;
	private boolean hasUnsavedChanges = false;
	private boolean isMaximized = false;
	private Dimension originalSize;
	private Point originalLocation;
	public TurtleGraphics() {
		mainframe = new JFrame("Turtle Graphics");
			mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainframe.setLayout(new FlowLayout());
			mainframe.add(this);
		//add text area
			commandTextArea = new JTextArea(24, 20);
			commandTextArea.setLineWrap(true);
			commandTextArea.setWrapStyleWord(true);
			
			JScrollPane scrollPane  =  new JScrollPane(commandTextArea);
			JPanel historyPanel = new JPanel(new BorderLayout());
			historyPanel.add(new JLabel("Command History"), BorderLayout.NORTH);
			historyPanel.add(scrollPane, BorderLayout.CENTER);
			mainframe.add(historyPanel, BorderLayout.WEST);
			//Add nenu file
			JMenuBar menuBar= new JMenuBar();
			JMenu fileMenu =new JMenu("File");
			JMenuItem newPage = new JMenuItem("New file");
			
		
			JMenuItem saveImageItem = new JMenuItem("Save Image");
			JMenuItem loadImageItem = new JMenuItem("Load Image");
			JMenuItem saveCommandItem = new JMenuItem("Save Command");
			JMenuItem loadCommandItem = new JMenuItem("Load Commands");
			
			
			//Add action
			newPage.addActionListener(e -> newFile());
			saveImageItem.addActionListener(e -> saveImage());
			loadImageItem.addActionListener(e -> loadImage());
			saveCommandItem.addActionListener(e -> saveCommand());
			loadCommandItem.addActionListener(e -> loadCommand());
			
			fileMenu.add(newPage);
			fileMenu.addSeparator();
			fileMenu.add(saveImageItem);
			fileMenu.add(loadImageItem);
			fileMenu.addSeparator();
			fileMenu.add(saveCommandItem);
			fileMenu.add (loadCommandItem);
			
			// Window menu
			JMenu windowMenu = new JMenu("Window");
			
			JMenuItem minimizeItem = new JMenuItem("Minimize");
			JMenuItem zoomItem = new JMenuItem("Zoom");
			JMenuItem fillCenterItem = new JMenuItem("Fill Center");
			JMenuItem restoreItem = new JMenuItem("Restore");
			
			minimizeItem.addActionListener(e -> minimizedWindow());
			zoomItem.addActionListener(e -> zoomWindow());
			fillCenterItem.addActionListener(e -> fillCenter());
			restoreItem.addActionListener(e -> restoreWindow());
			
			windowMenu.add(minimizeItem);
			windowMenu.add(zoomItem);
			windowMenu.addSeparator();
			windowMenu.add(fillCenterItem);
			windowMenu.add(restoreItem);
			
			
			 
			//Edit Menu
			JMenu editMenu = new JMenu("Edit");
			JMenuItem penDownItem = new JMenuItem("Pen down");
			JMenuItem penUpItem = new JMenuItem("Pen up");
			JMenuItem clearItem = new JMenuItem("Clear");
			JMenuItem resetItem = new JMenuItem("Reset");
			
			penDownItem.addActionListener(e -> processCommand("pendown"));
			penUpItem.addActionListener(e -> processCommand("penup"));
			clearItem.addActionListener(e -> processCommand("clear"));
			resetItem.addActionListener(e -> processCommand("reset"));
			
			editMenu.add(penDownItem);
			editMenu.add(penUpItem);
			editMenu.addSeparator();
			editMenu.add(clearItem);
			editMenu.add(resetItem);
			
			
			//Help Menu
			JMenu helpMenu = new JMenu("Help");
			JMenuItem aboutItem = new JMenuItem("About");
			JMenuItem aboutCommandsItem = new JMenuItem("About Commands");
			
			aboutItem.addActionListener(e -> about_me());
			aboutCommandsItem.addActionListener(e -> showCommandHelp());
			
			helpMenu.add(aboutItem);
			helpMenu.add(aboutCommandsItem);
			
			
			
				
			menuBar.add( fileMenu);	
			menuBar.add(windowMenu);
			menuBar.add(editMenu);
			menuBar.add(helpMenu);
			mainframe.setJMenuBar( menuBar);
			
			mainframe.pack();
			mainframe.setVisible( true);
			 
			
			
//			about();
		}
	//Window control operations
	private void minimizedWindow() {
		mainframe.setState(JFrame.ICONIFIED);
			
		
	}
	
	private void zoomWindow() {
		if (isMaximized) {
			restoreWindow();
			
		}else {
			originalSize = mainframe.getSize();
			originalLocation = mainframe.getLocation();
			mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			isMaximized = true;
		}
	}
	
	private void fillCenter() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		int width = (int)(bounds.width * 0.8);
		int height = (int)(bounds.height* 0.8);
		int x = bounds.x + (bounds.width - width)/2;
		int y = bounds.y +(bounds.height - height)/2;
		
		mainframe.setSize(width,height);
		mainframe.setLocation(x,y);
		isMaximized= false;
		
		
		
	}
	
	private void restoreWindow() {
		mainframe.setSize(originalSize);
		mainframe.setLocation(originalLocation);
		mainframe.setExtendedState(JFrame.NORMAL);
		isMaximized = false;
		
	}
	
	// Help function
	private void showCommandHelp() {
		String helptext ="COMMAND LISTS \n"
				+ "====================== \n\n"
				+ "Movement Commands \n"
				+ "_______________________ \n"
				+ "move [distance] \t : Move forward. \n " 
				+"reverse [distance] \t : Move backward[deafult forward(100). \n"
				+"left [degrees] \t                       : Turn left[deafult 90]. \n"
				+"right [degrees] \t : Turn right[deafult 90].\n\n "
				+" Drawing Controls Command\n"
				+"_________________________\n"
				
				+"pendown \t : Enables drawing wehn the turtle moves.\n"
				+"penup \t : disable drawing wehn the turtle moves.\n"
				+"pen RGB \t : Sets pen colour using RGB vlaues (0-225).\n"
				+"red \t : Sets pen color red .\n"
				+"blue \t : Sets pen color blue s.\n"
				+"yellow \t : Sets pen color yellow.\n"
				+"green \t : Sets pen color green .\n\n"
				
				+"Shapes Commands\n"
				+"_________________________\n"
				+"square L \t : Draws a square with side length l. \n"
				+"triangle A \t : Draws a triangle with sides A,A,A. \n"
				+"triangle A B C \t : Draws a triangle with sides A , B, C .\n"
				+"Circle  R \t : Draws a circle  with  radius R. \n\n"
				
				+"Basic Commands\n"
				+"_________________________\n"
				+"about \t : Display 'OOPS'/'NISCHAL'. \n"
				+"clear \t : Clears thte canavas. \n"
				+"reset \t : Resett the turtle positon. \n \n"
				
				+"Turtle Commands\n"
				+"_________________________\n"
				+"speed A \t : Sets turtle movement(1-10)."
				
				
				
;				
		JTextArea helpArea  = new  JTextArea(helptext);
		helpArea.setEditable(false);
		helpArea.setFont(new Font("Monoospaced", Font.PLAIN,12));
		JScrollPane scrollPane = new JScrollPane(helpArea);
		scrollPane.setPreferredSize(new Dimension(500,400));
		
		JOptionPane.showMessageDialog(
				this,
				scrollPane, "Command Help",
				JOptionPane.PLAIN_MESSAGE
				);
		
		 
	}
	private void about_me() {
		JOptionPane.showMessageDialog(null, "Turtle Graphic Version 1.0 -- Powered by Nischal");
		
	}

//		@Override
//		public void about() {
//			//N
//			reset();
//			drawOff();
//			forward(100);
//			right();
//			forward(400);
//			right();
//			forward(10);
//			setStroke(5);
//			setPenColour(Color.red);
//			drawOn();
//			forward(220);
//			right(150);
//			forward(242);
//			left(150);
//			forward(220);
//		 
//			
//			//I
//			reset();
//			drawOff();
//			forward(80);
//			right();
//			forward(250);
//			setPenColour(Color.blue);
//			setStroke(5);
//			drawOn();
//			right(90);
//			forward(220);
//			drawOff();
//			right(90);
//			forward(80);
//			
//			//S
//			reset();
//			drawOff();
//			forward(80);
//			right();
//			forward(120);
//			right();
//			forward(200);
//			right();
//			right();
//			right();
//			right(25);
//			setPenColour(Color.green);
//			setStroke(5);
//			drawOn();
//			
//			for(int i = 0; i<12; i++) {
//				forward(17);
//				left(17);
//			}
//			for (int i = 0; i<12; i++) {
//				
//				forward(19);
//				right(20);
//				
//			}
//		 
//			
//			//C
//			reset();
//			drawOff();
//			forward(60);
//			right();
//			forward(-20);
//			right();
//			forward(200);
//			left(100);
//			setPenColour(Color.yellow);
//			setStroke(5);
//			drawOn();
//			for (int i = 0; i<9;i++) {
//				forward(38);
//				left(20);
//				
//			}
//			
//			//H
//			reset();
//			drawOff();
//			forward(40);
//			right();
//			forward(-120);
//			right();
//			forward(180);
//			left();
//			
//			setPenColour(Color.cyan);
//			setStroke(5);
//			drawOn();
//			left(90);
//			forward(220);
//			drawOff();	
//			forward(-110);
//			drawOn();
//			right(90);
//			forward(80);
//			left();
//			drawOff();
//			forward(110);
//			drawOn();
//			forward(-220);
//			
//			//A
//			reset();
//			drawOff();
//			forward(80);
//			right();
//			forward(-150);
//			left();
//			setPenColour(Color.magenta);
//			setStroke(5);
//			drawOn();
//			left();
//			left(75);
//			forward(242); 
//			right(150);
//			forward(242);
//			drawOff();
//			forward(-110);
//			right(105);
//			drawOn();
//			forward(60);
//			
//			//L
//		 reset();
//		 drawOff();
//		 forward(80);
//		 right();
//		 forward(-300);
//		 right();
//		 right();
//		 setPenColour(Color.pink);
//		setStroke(5);
//		drawOn();
//		left(90);
//		forward(220);
//		drawOff();
//		forward(-220);
//		right(90);
//		drawOn();
//		forward(80);
//		drawOff();
//			
//			
//			
//		}
	//	
	
	
		private void newFile() {
			if(checkUnsavedChanges () ) return;
			clear();
			reset();
			commandTextArea.setText("");
			commandHistory.clear();
			hasUnsavedChanges= false;
			
		}
	 
		
		private void saveImage(){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save Image");
			fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPG image (*.jpg)", "jpg"));
			fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG image (*.png)", "png"));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[0]);
			
			
			int userSelection = fileChooser.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String extension = "jpg";
				
				
				String selectedDescription = fileChooser.getFileFilter().getDescription();
				
				if (selectedDescription.contains("PNG")) {
					extension ="png";
					
				}
				if (!selectedFile.getName().toLowerCase().endsWith("." + extension)) {
					selectedFile = new File(selectedFile.getAbsolutePath() + "." + extension);
					
				}
				try {
					BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = image.createGraphics();
					paintAll(g2);
					ImageIO.write(image, extension, selectedFile);
					clear();
					reset();
					 
					
					
					displayMessage("Image saved to : " + selectedFile.getAbsolutePath());
					hasUnsavedChanges = false;
					
					
					
				}catch(IOException ex) {
					displayMessage("Fialed to save image");
					        
				}
				
			}
		}    
		
		private void loadImage() {
			 if (checkUnsavedChanges()) return;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Load Image");
			fileChooser.setAcceptAllFileFilterUsed(true);
			if (fileChooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					BufferedImage loadedImage = ImageIO.read(file);
					if (loadedImage!= null) {
						JDialog imageDialog =  new JDialog(mainframe , "Loaded Image", false );
						imageDialog.setLayout(new BorderLayout());
						
						JLabel imageLabel = new JLabel (new  ImageIcon (loadedImage));
						JScrollPane scrollPane = new JScrollPane(imageLabel);
						imageDialog.add(scrollPane, BorderLayout.CENTER);
						
						JButton closeButton = new JButton("close");
						closeButton.addActionListener(e -> imageDialog.dispose());
						JPanel buttonPanel = new JPanel();
						buttonPanel.add(closeButton);
						imageDialog.add(buttonPanel, BorderLayout.SOUTH);
						
						imageDialog.setSize(600,500);
						imageDialog.setLocationRelativeTo(this);
						imageDialog.setVisible(true);
						 
						displayMessage("Image loaded : "+ file.getName());
						clear();
						reset();
						hasUnsavedChanges = false;
						
						
						
						
					}
					else {
						displayMessage("Failed to load image : Unsupported format oor corruptetd file");
					}
					
				} catch (IOException e) {
					displayMessage("Error loaidng Image : " + e.getMessage());
				}
				
			}
			
			
		}
			
		private void saveCommand() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save coommands");
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt", "txt"));
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (!file.getName().toLowerCase().endsWith("txt")) {
					file = new File(file.getAbsolutePath()+ ".txt");
					
							
					
				}
				
				try (PrintWriter writer = new PrintWriter(file)){
					for (String cmd : commandHistory) {
						writer.println(cmd);
					}
					clear();
					reset();
					displayMessage("All commands saved to"+ file.getAbsolutePath());
					hasUnsavedChanges = false;
					
				}
				catch (IOException e) {
					displayMessage("Error saving commands: "+ e.getMessage());
					
				}
			}
			
			
			
		}
		 private void loadCommand() {
			 if (checkUnsavedChanges()) return;
			 
			 JFileChooser fileChooser = new JFileChooser();
			 fileChooser.setDialogTitle("Load commands");
			 
			 if ( fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				 File file = fileChooser.getSelectedFile();
				 StringBuilder loadedCommands = new StringBuilder();
				 
				 try (BufferedReader reader = new BufferedReader (new FileReader (file))){
					 clear();
					 reset();
					 commandHistory.clear();
					 
					 String line;
						 while ((line = reader.readLine()) != null) {
							 if (!line.trim().isEmpty()) {
								 commandHistory.add(line);
								  loadedCommands.append(line).append("\n");
								  
								  commandTextArea.setText(loadedCommands.toString());
								  
								  processCommand(line);
								  
								  repaint();
								  try {
									  Thread.sleep(100);
								  }
								  catch (InterruptedException e) {
									  Thread.currentThread().interrupt();
								  }
								  
								  
							 }
							 
						 }
						 displayMessage ("commands loaded and execuated from "+ file.getAbsolutePath());
					 
				 }catch( IOException e ) {
					 displayMessage(" Error loading commands: "+ e.getMessage());
				 }
			 }
			 
			 
		 }
		 private boolean checkUnsavedChanges() {
			 if(hasUnsavedChanges) {
				 int option = JOptionPane.showConfirmDialog(
						 this,
						 "You have unsaved cahnges Do you want to Proceed",
						 "Unsaved Changes",
						 JOptionPane.YES_NO_OPTION,
						 JOptionPane.WARNING_MESSAGE
						 
						 
						 );
				 return option != JOptionPane.YES_OPTION;
				 
				 
						 
						 
						 
			 }
			 else {
				 return false;
			 }
			 
		 }
		 
		 
		 private void drawSquare(int length) {
			 Point startPos = new  Point(getxPos() , getyPos());
			 int startHeading = getDirection();
			 boolean wasPenDown = getPenState();
			  
			 drawOn();
			 for(int i= 0;i<4; i++) {
				 forward(length);
				 right(90);
			 }
			 if (!wasPenDown)drawOff();
			 setxPos(startPos.x);
			 setyPos(startPos.y);
			 pointTurtle(startHeading);
			 
			 
			 
		 }
		 private boolean isValidColor(int value) {
			 return value >=0 && value <=225;
		 }
		 
		 private void drawEquilateralTriangle(int size) {
			 if (size <=0) {
			 displayMessage("Size must be positive");
			 return;}
			 boolean wasDrawing =getPenState();
			 
			 drawOn();
			 for (int i = 0;i<3;i++) {
				 forward(size);
				 right(120);
				 
			 }
			 if (!wasDrawing) {
				 drawOff();
			 }
			 
			 
			 
		 }
		 private void drawScaleneTriangle( int a, int b , int c) {
			 if (a<=0 || b<=0 || c<=0) {
				 displayMessage("All sides must be positive");
				 return;
			 }
			 if (a+b <=c|| a+c<=b || b+c <= a ) {
				 displayMessage(" Invalid triangle - sides vooilate triangle inequlity");
				 return;
			 }
			 boolean wasDrawing = getPenState();
			 int startX = getxPos();
			 int startY = getyPos();
			 drawOn();
			 double angleA = Math.toDegrees(Math.acos((b*b + c*c - a*a) / (2.0*b*c)));
			 double angleB = Math.toDegrees(Math.acos((a*a + c*c - b*b) / (2.0*a*c)));
			 double angleC = 180 - angleA - angleB;
		 
			 
			
			 int A = (int)Math.round(180- angleA);
			 int B = (int)Math.round(180- angleB);
			 
			 forward(c);
			 right(A);
			 forward(a);
			 right(B);
			 forward(b);
			 if (Math.hypot(getxPos()-startX , getyPos()-startY)>2) {
				 drawLine(getPenColour(),getxPos(),getyPos(),startX,startY);
				 setxPos(startX);
				 setyPos(startY);
				 
			 }
			 
			 
			 if(!wasDrawing) drawOff();
			 
			 
			 
		 }
		@Override
		public void processCommand(String command) {
			// here this is abstract method
			System.out.println("command: "+ command);
			commandHistory.add(command);
			String processedCmd = command.toLowerCase().trim();
			if (!processedCmd.equals("about")&& !processedCmd.equals("clear")) {
				hasUnsavedChanges=true;
			}
			try {
				if (processedCmd.equals("about")) {
					about();
					
				}
				else  if (processedCmd.equals("pendown")) {
					drawOn();
				} 
				
				else  if (processedCmd.equals("penup")) {
					drawOff();
				} 
				
				else  if (processedCmd.equals("pendown")) {
					drawOn();
				} 
				
				else if(processedCmd.startsWith("move")) {
					String[] parts = processedCmd.split(" ");
					if(parts.length<2) {
//						forward(100);
						
						displayMessage("Missing parameter for move command. Usage: move [distance]");
					}else {
						try {
							int x = getxPos();
							
							int y = getyPos();

							int distance = Integer.parseInt(parts[1]);
							int newX = x+ (int )(distance * Math.sin(Math.toRadians(getDirection())));
							int newY = (int) (y - (int) distance * Math.cos(Math.toRadians(getDirection())));
							if (newX <0 || newX > getWidth() || newY < 0 || newY > getHeight()) {
								displayMessage("Cannot move turtle off screen");
								return;
							}
							forward(distance);
							
							
							
//							if (distance > 500 || distance< -500) {
//								displayMessage(" Distance must be between -500 and 500");
//								return;
//							}
//							forward(distance);
						} catch (NumberFormatException e){
							displayMessage("Invalid number format . enter a numeric value");
							
						}
						
					}
					
				}
				
				else if(processedCmd.startsWith("reverse")) {
					String[] parts = processedCmd.split(" ");
					if(parts.length<2) {
						forward(100);
					}else {
						try {
							int distance = Integer.parseInt(parts[1]);
							forward(-distance);
						} catch (NumberFormatException e){
							displayMessage("Invalid number format . enter a numeric value");
							
						}
						
					}
					
				}
				else if (processedCmd.startsWith("left")){
					String[] parts = processedCmd.split(" ");
					if (parts.length<2) {
						left();
					} else {
						try {
							int degree = Integer.parseInt(parts[1]);
							left(degree);
						}catch(NumberFormatException e) {
							displayMessage("Invalid number format. enter a numeric value");
							
						}
					}
					
					
				}
				else if (processedCmd.startsWith("right")){
					String[] parts = processedCmd.split(" ");
					if (parts.length<2) {
						right();
					} else {
						try {
							int degree = Integer.parseInt(parts[1]);
							right(degree);
						}catch(NumberFormatException e) {
							displayMessage("Invalid number format. enter a numeric value");
							
						}
					}
					
					
				}
				
				else if (processedCmd.equals("red")) {
					setPenColour(Color.RED);
				}
				
				else if (processedCmd.equals("blue")) {
					setPenColour(Color.BLUE);
				}
				else if (processedCmd.equals("yellow")) {
					setPenColour(Color.YELLOW);
				}
				else if (processedCmd.equals("green")) {
					setPenColour(Color.GREEN);
				}
				
				else if(processedCmd.startsWith("square ")){
					String[] parts = processedCmd.split(" ");
					if (parts.length < 2) {
						displayMessage ("Invalid Usage, Usage: square [length]");
					}else {
						try {
							int length = Integer.parseInt(parts[1]);
							if( length <=0) {
								displayMessage("Length must be positve");
								
							}else {
								
								drawSquare(length);
							}
						}catch (NumberFormatException e) {
							 displayMessage("Invalid Numbe, please enter a Number");
							
						}
					}
					
				}
				
				else if(processedCmd.startsWith("pen " )) {
					String[] parts = processedCmd.split(" ");
					if (parts.length < 4) {
						displayMessage(" invalid command, Usage : pen <red> <green> <blue> (0-225)");
					}else {
						try {
							int red = Integer.parseInt(parts[1]);
							int green = Integer.parseInt(parts[2]);
							int blue = Integer.parseInt(parts[3]);
							if (isValidColor(red) && isValidColor(green) && isValidColor(blue)) {
								setPenColour(new Color(red, green , blue));
								displayMessage("Pen color set to RGB : (" + red +" , " +green+" , "+blue+" )");
							}else {
								displayMessage("Color values mustt be 0-225");
								
							}
							
							
						} catch(NumberFormatException e) {
							displayMessage("Invalid color values Numbers 0-225 required");
						}
					}
					
				}
				
				else if(processedCmd.startsWith("penwidth ")){
					String[] parts = processedCmd.split(" ");
					if (parts.length < 2) {
						displayMessage ("Invalid Usage, Usage: penwidth [1-20]");
					}else {
						try {
							int width = Integer.parseInt(parts[1]);
							if( width < 1 || width >20) {
								displayMessage("Width must be between 1 and 20");
								
							}else {
								
								setStroke(width);
								displayMessage("Pen width set to "+ width);
							}
						}catch (NumberFormatException e) {
							displayMessage("Invalid width , Please enter a number 1-20");
							
						}
					}
					
				}
				
				
					

				else if(processedCmd.startsWith("triangle ")){
					String[] parts = processedCmd.split(" ");
					if (parts.length==2) {
						try {
							int size= Integer.parseInt(parts[1]);
							drawEquilateralTriangle(size);
						}catch(NumberFormatException e){
							displayMessage("Invalid size . Please enter a positive number");
							
							
						}
					}
					else if (parts.length == 4) {
						try {
							int a= Integer.parseInt(parts[1]);
							int b= Integer.parseInt(parts[2]);
							int c= Integer.parseInt(parts[3]);
							
							drawScaleneTriangle(a, b, c);
						
						}
						catch(NumberFormatException e) {
							displayMessage("Invalid sides. Plese enter positive numbers ");
							
						}
						
					}else {
						displayMessage("Usage: triangel <side> or triangel <side1> <side2> <side3>");
					}

				}
				
				else if(processedCmd.startsWith("speed ")){
					String[] parts = processedCmd.split(" ");
					if (parts.length < 2) {
						displayMessage ("Invalid Usage, Usage: penwidth [1-10]");
					}else {
						try {
							int speed = Integer.parseInt(parts[1]);
							if( speed < 1 || speed >10) {
								displayMessage("Speed must be between 1 and 10");
								
							}else {
								
								setTurtleSpeed(speed);
								displayMessage("Turtle Speed set to "+ speed);
							}
						}catch (NumberFormatException e) {
							displayMessage("Invalid speed , Please enter a number 1-20");
							
						}
					}
					
				} 
				else if(processedCmd.startsWith("circle ")){
					String[] parts = processedCmd.split(" ");
					if (parts.length < 2) {
						displayMessage ("Invalid Usage, Usage: circle [radius]");
					}else {
						try {
							int radius = Integer.parseInt(parts[1]);
							if( radius < 0 || radius > 200) {
								displayMessage("radius must be between 1 and 20");
								
							}else {
								
								circle(radius);
								displayMessage("Circle is drawnn with  "+ radius);
							}
						}catch (NumberFormatException e) {
							displayMessage("Invalid radius, Please enter a number 0 - 400");
							
						}
					}
					
				}
				
					
					
				
				
				
				
				
				else if(processedCmd.equals("clear")) {
					if (checkUnsavedChanges()) return;
					clear();
				}
				else if(processedCmd.equals("reset")) {
					reset();
				}
				else {
					displayMessage("unknown command: "+ command);
					
				}

			} catch(NumberFormatException e){
				displayMessage("invalid number in command: "+ command);
				
				
				
			} catch(Exception e) {
				displayMessage("Error processing:"+ command);
			}
		}
			 
			

	}
 