#### Image Program

To run the program, go to the RunProgram class, make sure to have an ImageView and ImageController created in the main method, then call the ImageController's run method.

## Commands:

load image-path image-name 
*Loads an image from computer from the given image-path and stores it under given image-name*

save image-path image-name
#Saves image with the given image-name to given image-path on computer

red-component image-name dest-image-name
#Creates new image of given image-name with greyscale red-component filter and stores it under given dest-image-name

green-component image-name dest-image-name
#Creates new image of given image-name with greyscale green-component filter and stores it under given dest-image-name

blue-component image-name dest-image-name
#Creates new image of given image-name with greyscale blue-component filter and stores it under given dest-image-name

value-component image-name dest-image-name
#Creates new image of given image-name with greyscale value-component filter and stores it under given dest-image-name

luma-component image-name dest-image-name
#Creates new image of given image-name with greyscale luma-component filter and stores it under given dest-image-name

intensity-component image-name dest-image-name
#Creates new image of given image-name with greyscale intensity-component filter and stores it under given dest-image-name

horizontal-flip image-name dest-image-name
#Creates new image of given image-name horizontally flipped and stores it under given dest-image name


vertical-flip image-name dest-image-name
#Creates new image of given image-name vertically flipped and stores it under given dest-image name

brighten increment image-name dest-image-name
#Creates new image of given image-name brightened by the given increment and stores it under given dest-image-name
#A negative increment would darken the image

brighten increment image-name dest-image-name
#Creates new image of given image-name darkened by the given increment and stores it under given dest-image-name
#A negative increment would brighten the image

help
#Displays a help message

q
#Ends the program

