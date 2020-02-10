# The ImageDraw Module

The **ImageDraw** module provide simple 2D graphics for **Image** objects. You can use this module to create new images, annotate or retouch existing images, and to generate graphics on the fly for web use.

For a more advanced drawing library for PIL, see [The aggdraw Module](http://effbot.org/zone/aggdraw-index.htm).

## Example [#](http://effbot.org/imagingbook/imagedraw.htm#examples)

**Draw a Grey Cross Over an Image**

```
import Image, ImageDraw

im = Image.open("lena.pgm")

draw = ImageDraw.Draw(im)
draw.line((0, 0) + im.size, fill=128)
draw.line((0, im.size[1], im.size[0], 0), fill=128)
del draw

# write to stdout
im.save(sys.stdout, "PNG")
```

## Concepts [#](http://effbot.org/imagingbook/imagedraw.htm#concepts)

### Coordinates [#](http://effbot.org/imagingbook/imagedraw.htm#coordinates)

The graphics interface uses the same coordinate system as PIL itself, with (0, 0) in the upper left corner.

### Colours [#](http://effbot.org/imagingbook/imagedraw.htm#colors)

To specify colours, you can use numbers or tuples just as you would use with **Image.new** or **Image.putpixel**. For “1”, “L”, and “I” images, use integers. For “RGB” images, use a 3-tuple containing integer values. For “F” images, use integer or floating point values.

For palette