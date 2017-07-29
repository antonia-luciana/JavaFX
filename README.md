# JavaFX

Drawing Graphs of Functions Create a Swing or JavaFX application that allows drawing graphs of functions.

- Specify a function of one variable in the real domain as a string expression and draw its graph. Any function should be permitted: linear, polynomial, exponential, logarithmic, sinusoidal etc., for example f(x)=sin(x) or f(x)=x^2 + 2x + 1, etc.

You may use a third party library for parsing and evaluating expressions, such as exp4j or Apache Commons JEXL or other.

- Plot several points on the surface and draw the graph of the polynomial function passing through the given coordinates. You may use the Lagrange polynomials or other interpolation technique.

- Both axes of the coordinate system should be displayed, along with texts marking various elements such as O, X, Y, etc.

- When representing a function the user must be able to specify the colour of the drawing and the stroke. Multiple graphs of functions may overlap in the same drawing, unless the user decides to "reset" the view.

- Save/Load the drawing in/from a JPG of PNG file, using javax.imageio.ImageIO.

- You may also encode the drawing using SVG (Scalable Vector Graphics) format. Check out The Apache Batik Project.

- for implementing the application using JavaFX.
