# The ImageColor Module

The **ImageColor** module contains colour tables and converters from CSS3-style colour specifiers to RGB tuples. This module is used by **Image.new** and the **ImageDraw** module, among others.

### Colour Names [#](http://effbot.org/imagingbook/imagecolor.htm#color-names)

The **ImageColor** module supports the following string formats:

- Hexadecimal color specifiers, given as “#rgb” or “#rrggbb”. For example, **“#ff0000”** specifies pure red.
- RGB functions, given as “rgb(red, green, blue)” where the colour values are integers in the range 0 to 255. Alternatively, the color values can be given as three percentages (0% to 100%). For example, **“rgb(255,0,0)”**and **“rgb(100%,0%,0%)”** both specify pure red.
- Hue-Saturation-Lightness (HSL) functions, given as “hsl(hue, saturation%, lightness%)” where hue is the colour given as an angle between 0 and 360 (red=0, green=120, blue=240), saturation is a value between 0% and 100% (gray=0%, full color=100%), and lightness is a value between 0% and 100% (black=0%, normal=50%, white=100%). For example, **“hsl(0,100%,50%)”** is pure red.
- Common HTML colour names. The **ImageColor** module provides some 140 standard colour names, based on the colors supported by the X Window system and most web browsers. Colour names are case insensitive. For example, **“red”** and **“Red”** both specify pure red.

## Functions [#](http://effbot.org/imagingbook/imagecolor.htm#functions)

### getrgb [#](http://effbot.org/imagingbook/imagecolor.htm#tag-ImageColor.getrgb)

**getrgb(color)** ⇒ (red, green, blue)

(New in 1.1.4) Convert a colour string to an RGB tuple. If the string cannot be parsed, this function raises a **ValueError** exception.

### getcolor [#](http://effbot.org/imagingbook/imagecolor.htm#tag-ImageColor.getcolor)

**getcolor(color, mode)** ⇒ (red, green, blue) or integer

(New in 1.1.4) Same as **getrgb**, but converts the RGB value to a greyscale value if the mode is not color or a palette image. If the string cannot be parsed, this function raises a **ValueError** exception.