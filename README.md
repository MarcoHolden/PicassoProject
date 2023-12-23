# Picasso  
##  Mark Holden, Barrett Bourgeois, Wonjun Jo, Nick Lagges, Zachary Moore 

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

## Running Picasso

To run Picasso, run `picasso.Main`

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.
## Extensions

### Allow user to see rgb values on canvas
This extension allows the user to click on the canvas to see the (r,g,b) values where their mouse is. 
The extension also allows the user to see the coordinates where they've clicked by showing their mouse position.
The coordinates and colors will be given in range [-1,1]. 

<img width="797" alt="Screenshot 2023-12-14 at 6 59 09 PM" src="https://github.com/WLU-CSCI209-F23/picasso-invincibles/assets/136394451/845a3285-c2d7-4348-86f5-1beeeb4f0613">

### Navigate expression history
This extension allows the user to navigate the history of evaluated expressions in a Picasso session. By pressing the UP arrow key, the user navigates back in time, and the expression entry field will be repopulated with the previous error-free expression different than the one most recently evluated. The DOWN arrow key can be used to navigate toward the present to see the more recently evaluated expressions. 

This feature ensures that no expression with input errors will be retained in history and that equivalent expressions will not be remembered in history if evaluated adjacently. 

### Expression Generation Based on a String
This extension allows the user to input any word into the text field and it will generate an image based on that word. The word must be at least 3 characters long, as variables are 2 or less characters. The word will randomly generate an image and color for the image. The functions implemented are randomly generated, and the number of functions used is based on the word's length. For example, if the text field input is "abcd" (the quotations are not part of the input), then the image will be generated from 4 randomized functions. Words can be assigned to variables, as well.

### Animation
This extension allows the user to animate the image going through a change of color by clicking the "animate" button. It is important that the user does not click other buttons or change the expression in the textbox while the image is animating. After the image animation is complete, "Done animating" will display.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.
