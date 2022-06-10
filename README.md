## Assignment 4 - CS3500

### Design Overview
#### Model
*ImageCache:* An interface for storing images.  
*ImageCacheModel:* Implementation of ImageCache.  
*ImageModel:* An interface for images, representing a single image.  
*ImageModelRGB:* Implementation of ImageModel, representing an image in RGB format.  
*ImageUtils:* A collection of file-related utility functions for images.  
*Pixel:* An interface for pixel data.  
*PixelRGB:* Implementation of Pixel, representing a pixel in RGB format.  
*Filter:* An interface for filters.  
*FilterModel:* Abstract class that implements the Filter interface.
Used for filters that change pixel channel values.  
*FlipFilterModel:* Abstract class that implements the Filter interface. 
Used for filters that flip/transform an image.  
*BrightnessFilter:* Brightness filter that extends the FilterModel class. 
Filter used for brightening/darkening an image.  
*LumaFilter:* Luma filter that extends the FilterModel class. 
Filter used for greyscaling an image using the luma value of each pixel.  
*ValueFilter:* Value filter that extends the FilterModel class.
Filter used for greyscaling an image using the max channel value of each pixel.  
*IntensityFilter:* Intensity filter that extends the FilterModel class.
Filter used for greyscaling an image using the average channel value of each pixel.  
*RGBFilter:* RGB filter that extends the FilterModel class.
Filter used for greyscaling an image depending on which channel was specified in constructor.  
*HorizontalFlipFilter:* Horizontal flip filter that extends the FlipFilterModel class.
Filter used for flipping an image horizontally.  
*VerticalFlipFilter:* Vertical flip filter that extends the FlipFilterModel class.
Filter used for flipping an image vertically.  

#### Controller
*ImageController:* An interface for the controller of the project program.  
*ImageControllerImpl:* Implementation of ImageController, used to run the project program.

#### View
*ImageView:* An interface for the view of the project program.
*ImageViewImpl:* Implementation of ImageView. Used for rendering messages/views to
given appendable.

### Running Program:
To run the program, simply navigate to the RunProgram class and run the main() method. The program will prompt
the user for input through the console that it launches.  

### Script:
- This script applies several filters to an image and saves the resulting images. Run program and enter each line followed by the key 'enter' to run script.
1. load res/capybara.ppm capybara
2. red-component capybara red
3. horizontal-flip capybara horizontal
4. value-filter capybara value
5. brighten 50 capybara bright
6. load red.ppm red
7. load horizontal.ppm horizontal
8. load value.ppm value
9. load bright.ppm bright
10. q

### Image credit:
*Res/capybara.png* image credit: Pixnio  
Image is free to use for creative and commercial use  
https://pixnio.com/fauna-animals/wildlife-marmot-nature-wild-fur-animal-green-grass-mammal

### Commands:

load image-path image-name  
- *Loads an image from computer from the given image-path and stores it under given image-name*

save image-path image-name  
- *Saves image with the given image-name to given image-path on computer*
- *Can save images in jpeg,png, or ppm formats*

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

