# Tamagotchi

This was a project for my first/second year of university. It was a simple project to get us used to Java and Applets.
- Java: 4
- IDE: of your choice
- JDK: 8 found [here](https://adoptium.net/temurin/releases/?variant=openjdk8)

## How to run
1. Clone the repository
2. To run an applet in Java, you need to use the Applet class from the java.applet package. However, note that applets are deprecated and not 
supported in many modern browsers and environments. For this project, you can use the AppletViewer tool that comes with the JDK mentioned above.
3. Open a terminal and navigate to the directory where the src project is located.
4. If the classes are already built and the html files are already created, just run the command ``appletviewer .\build\Ovo.html``
5. If the classes are not built yet, run the following command: ``javac -d ../build/classes src/Ovo.java``. This will compile the Ovo.java file and create a Ovo.class file in the build/classes directory.
6. Keep the *.gif files in the same directory as the html files.
7. Execute the command from step 4.

## Example
Here is a simple example of an applet:  
```java
import java.applet.Applet;
import java.awt.Graphics;

public class HelloWorldApplet extends Applet {
    public void paint(Graphics g) {
        g.drawString("Hello, World!", 20, 20);
    }
}
```
To run this applet using AppletViewer, follow these steps:  
1. Save the above code in a file named HelloWorldApplet.java.
2. Compile the Java file using the javac command:
```javac HelloWorldApplet.java```
3. Create an HTML file to load the applet. Save the following content in a file named HelloWorldApplet.html:
````html
<html>
<body>
    <applet code="HelloWorldApplet.class" width="300" height="300"></applet>
</body>
</html>
````
4. Run the applet using AppletViewer:
```appletviewer HelloWorldApplet.html ```
5. This will open a window displaying the applet.

## Troubleshooting
- If you get an error message saying that the appletviewer command is not recognized, you may need to add the JDK bin directory to your system's PATH environment variable. For example, if you are using Windows and the JDK is installed in C:\Program Files\Java\jdk1.8.0_301, you can add C:\Program Files\Java\jdk1.8.0_301\bin to the PATH variable.
- If you encounter any issues with the applet not displaying correctly or not running as expected, and a permission exception is thrown, you may need to adjust the java.policy file under ..\jdk-8.0.412.8-hotspot\jre\lib\security to add the following line:
- permission java.io.FilePermission "<dir>\\tamagotchi\\build\\bipe.au", "read";

## Authors
Ady Paulino, Isamara Santos


