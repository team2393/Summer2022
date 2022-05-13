
/** Example for "manually" editing source code, compiling and running it.
 * 
 *  On Windows, open a "command prompt" and try this:
 *  <pre>
 *    cd git\Summer2022\src\main\java
 *    notepad CompiledCode.java 
 *    \Users\Public\wpilib\2022\jdk\bin\javac CompiledCode.java
 *    notepad CompiledCode.class
 *    \Users\Public\wpilib\2022\jdk\bin\javap -v -p -c CompiledCode.class
 *    \Users\Public\wpilib\2022\jdk\bin\java CompiledCode
 *  </pre>
 *  
 *  On Mac, open a "Terminal" and try this:
 *  <pre>
 *    cd git/Summer2022/src/main/java
 *    open -a TextEdit CompiledCode.java 
 *    ~/wpilib/2022/jdk/bin/javac CompiledCode.java 
 *    open -a TextEdit CompiledCode.class
 *    ~/wpilib/2022/jdk/bin/javap -v -p -c CompiledCode.class
 *    ~/wpilib/2022/jdk/bin/java CompiledCode
 *  </pre>
 * 
 *  Compare that to editing this file in VSCode and pressing "Run"
 */
public class CompiledCode
{
    public static void main(String[] args)
    {
        // Circle with radius ...
        double r = 27.5;
        double pi = 3.1415;
        
        double circ = 2*pi*r;
        double area = pi*r*r;

        System.out.println("For a circle with radius " + r + ",");
        System.out.println("the circumference is " + circ);
        System.out.println("and the area is " + area);
    }    
}
