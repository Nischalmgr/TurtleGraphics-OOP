# Java Turtle Graphics Application 🐢🎨


An interactive drawing tool that lets you control a virtual turtle to create geometric designs. Built with Java Swing, this application is perfect for learning programming concepts through visual feedback.

## Features ✨

###  Graphical Interface
- Drawing canvas with turtle visualization
- Control buttons (penup/pendown, clear, reset)
- Command input field with history tracking
- Message feedback system
- Menu bar for file operations

## Complete Command 
```
  Movement Commands
| Command        | Parameters       | Description                          | Default      |
|----------------|------------------|--------------------------------------|--------------|
| `move`         | distance         | Move forward                         | -            |
| `reverse`      | distance         | Move backward                        | forward(100) |
| `left`         | degrees          | Turn left                            | 90°          |
| `right`        | degrees          | Turn right                           | 90°          |
| `speed`        | 1-10             | Set turtle movement speed            | -            |

 Drawing Controls
| Command        | Parameters       | Description                          |
|----------------|------------------|--------------------------------------|
| `pendown`      | -                | Enable drawing                       |
| `penup`        | -                | Disable drawing                      |
| `pen`          | R G B (0-255)    | Set custom pen color                 |
| `red`/`blue`   | -                | Set predefined colors                |
| `yellow`/`green` | -              | Set predefined colors                |
| `penwidth`     | width            | Set line thickness                   |

 Shape Commands
| Command        | Parameters       | Description                          |
|----------------|------------------|--------------------------------------|
| `square`       | length           | Draws a square                       |
| `triangle`     | size             | Equilateral triangle                 |
| `triangle`     | A B C            | Custom triangle                      |
| `circle`       | radius           | Draws a circle                       |

 Basic Commands
| Command        | Description                          |
|----------------|--------------------------------------|
| `about`        | Displays  ("OOPS/NISCHAL") |
| `clear`        | Clears the canvas                    |
| `reset`        | Resets turtle position               |
```
### Menu Options
```
File Menu:
- New File       : Start fresh canvas
- Save Image     : Export as PNG
- Load Image     : Import image
- Save Commands  : Save history to file
- Load Commands  : Load commands from file

Edit Menu:
- Pen Down     : Enable drawing
- Pen Up         : Disavle drawing
- Reset          : Reset turtle
- Clear          : Clear canvas

Window Menu:
- Minimize       : Minimize window
- Zoom           : Toggle fullscreen
- Fill Center    : Center drawing
- Restore        : Reset window size

Help Menu:
- About          : Version info
- Command Help   : Detailed command reference
```
  
 
 
 ## Future Enhancements

The following features are planned for future updates to improve the functionality, usability, and flexibility of the Turtle Graphics application:

###  GUI Improvements
- **Command Execution Button**: Add a dedicated "Run" button for executing typed commands.
- **Command Autocomplete/Suggestions**: Provide real-time suggestions and auto-complete for command input.
- **Resizable Canvas**: Automatically scale or adjust the drawing area when the window is resized.
- **Dark Mode Support**: Add a theme toggle for light/dark user interface modes.

### Drawing Features
- **Fill Shapes**: Add commands to fill shapes like circles, squares, and triangles with selected colors.
- **Turtle Visibility Toggle**: Allow users to show/hide the turtle cursor on the canvas.
- **Shape Rotation Support**: Allow rotation of drawn shapes at custom angles.
- **Custom Colors by Hex Code**: Support setting colors using hexadecimal (e.g., `#FF5733`).

###  Functional Enhancements
- **Undo/Redo Commands**: Track and reverse previous drawing actions with undo/redo functionality.
- **Command Scheduler**: Allow users to queue commands with delay intervals for animation-like effects.
- **Multi-Turtle Support**: Enable control of multiple turtles simultaneously or individually.
- **Save Project**: Save entire project state including turtle position, canvas contents, and command history.

### File Support
- **Export to SVG**: Allow exporting drawings to vector format (SVG) for high-quality printing or sharing.
- **JSON/XML Command History**: Save/load command history in structured formats with metadata.

###  Help & Accessibility
- **Interactive Help Panel**: Show command explanations and syntax dynamically based on user input.
- **Voice Command Input**: Use speech recognition to control the turtle (optional with external library).

## College github classroom link
https://github.com/OOP-2025-Classroom/oop-portfolio-2025-Nischalmgr.git
## Installation & Usage ⚙️

1. **Requirements**:
   - Java 8 or higher
   - (Optional) Eclipse IDE

2. **Running the Application**:
   ```bash
   git clone  https://github.com/Nischalmgr/TurtleGraphics-OOP.git
   cd turtle-graphics
   download jar file form here - https://github.com/Nischalmgr/jarfile.git ```
   
3. **Tutoorial**:
https://youtu.be/LAu7uWINrtA?si=mqB7wo4_iz85PEFf



 