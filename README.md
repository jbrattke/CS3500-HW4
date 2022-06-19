## Image Processing - CS3500

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
*ColorTransformFilterModel:* Abstract class that implements the Filter interface.
Used for filters that involve color transformations using 3x3 matrices.  
*KernelFilterModel:* Abstract class that implements the Filter interface.
Used for filters that involve kernel transformations using nxm matrices.  
*GreyscaleFilter:* Greyscale filter that extends the ColorTransformFilterModel class.
Filter used for greyscaling an image using a luma transformation matrix.   
*SepiaFilter:* Sepia filter that extends the ColorTransformFilterModel class.
Filter apply the sepia effect to an image using a sepia transformation matrix.   
*BlurFilter:* Blur filter that extends the KernelFilterModel class.
Filter used for applying a gaussian blur kernel transformation to an image.  
*SharpnessFilter:* Sharpness filter that extends the KernelFilterModel class.
Filter used for applying a sharpness kernel transformation to an image.  
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
To run the program(in Intellij), simply navigate to the RunProgram class and run the main() method. The program will prompt
the user for input through the console that it launches.  
Alternatively, the RunProgram.jar in /res can be ran from the command line.

### Script:
- An example script is provided in the /res folder, it can be ran in the program using the -file command.
- The script will run each filter the program has and save all resulting images to the /res folder.

### Image credit:
*Res/capybara.png* image credit: Pixnio  
Image is free to use for creative and commercial use  
https://pixnio.com/fauna-animals/wildlife-marmot-nature-wild-fur-animal-green-grass-mammal

