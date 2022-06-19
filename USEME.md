### Commands:

load image-path image-name
- *Loads an image from computer from the given image-path and stores it under given image-name*
- *Can load images in jpeg,png,bmp, or ppm formats*

save image-path image-name
- *Saves image with the given image-name to given image-path on computer*
- *Can save images in jpeg,png,bmp, or ppm formats*

-file file-path
- *Loads a file from computer and runs each line in file as a command in current program*
- *An example script is provided in /res*

greyscale image-name dest-image-name
- *Creates new image of given image-name with greyscale(luma transformation) filter and stores it under given dest-image-name*

sepia image-name dest-image-name
- *Creates new image of given image-name with sepia filter and stores it under given dest-image-name*

blur image-name dest-image-name
- *Creates new image of given image-name with blur filter and stores it under given dest-image-name*

sharpen image-name dest-image-name
- *Creates new image of given image-name with sharpen filter and stores it under given dest-image-name*

horizontal-flip image-name dest-image-name
- *Creates new image of given image-name horizontally flipped and stores it under given dest-image name*

vertical-flip image-name dest-image-name
- *Creates new image of given image-name vertically flipped and stores it under given dest-image name*

brighten increment image-name dest-image-name
- *Creates new image of given image-name brightened by the given increment and stores it under given dest-image-name*
- *A negative increment would darken the image*

darken increment image-name dest-image-name
- *Creates new image of given image-name darkened by the given increment and stores it under given dest-image-name*
- *A negative increment would brighten the image*

help
- *Displays a help message containing list of commands*

q
- *Ends the program*

### Legacy Commands:
###### These commands all act as various greyscale filters. The 'greyscale' command is an implementation of luma greyscaling.
red-component image-name dest-image-name
- *Creates new image of given image-name with greyscale red-component filter and stores it under given dest-image-name*

green-component image-name dest-image-name
- *Creates new image of given image-name with greyscale green-component filter and stores it under given dest-image-name*

blue-component image-name dest-image-name
- *Creates new image of given image-name with greyscale blue-component filter and stores it under given dest-image-name*

value-component image-name dest-image-name
- *Creates new image of given image-name with greyscale value-component filter and stores it under given dest-image-name*

luma-component image-name dest-image-name
- *Creates new image of given image-name with greyscale luma-component filter and stores it under given dest-image-name*

intensity-component image-name dest-image-name
- *Creates new image of given image-name with greyscale intensity-component filter and stores it under given dest-image-name*
