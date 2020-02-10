# The ImageChops Module

The **ImageChops** module contains a number of arithmetical image operations, called *channel operations* (“chops”). These can be used for various purposes, including special effects, image compositions, algorithmic painting, and more.

For more pre-made operations, see [**ImageOps**](http://effbot.org/imagingbook/imageops.htm).

At this time, most channel operations are only implemented for 8-bit images (e.g. “L” and “RGB”).

## Functions [#](http://effbot.org/imagingbook/imagechops.htm#functions)

Most channel operations take one or two image arguments and returns a new image. Unless otherwise noted, the result of a channel operation is always clipped to the range 0 to MAX (which is 255 for all modes supported by the operations in this module).

### constant [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.constant)

**ImageChops.constant(image, value)** ⇒ image

Return a layer with the same size as the given image, but filled with the given pixel value.

### duplicate [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.duplicate)

**ImageChops.duplicate(image)** ⇒ image

Return a copy of the given image.

### invert [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.invert)

**ImageChops.invert(image)** ⇒ image

Inverts an image.

```
    out = MAX - image
```

### lighter [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.lighter)

**ImageChops.lighter(image1, image2)** ⇒ image

Compares the two images, pixel by pixel, and returns a new image containing the lighter values.

```
    out = max(image1, image2)
```

### darker [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.darker)

**ImageChops.darker(image1, image2)** ⇒ image

Compares the two images, pixel by pixel, and returns a new image containing the darker values.

```
    out = min(image1, image2)
```

### difference [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.difference)

**ImageChops.difference(image1, image2)** ⇒ image

Returns the absolute value of the difference between the two images.

```
    out = abs(image1 - image2)
```

### multiply [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.multiply)

**ImageChops.multiply(image1, image2)** ⇒ image

Superimposes two images on top of each other. If you multiply an image with a solid black image, the result is black. If you multiply with a solid white image, the image is unaffected.

```
    out = image1 * image2 / MAX
```

### screen [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.screen)

**ImageChops.screen(image1, image2)** ⇒ image

Superimposes two inverted images on top of each other.

```
    out = MAX - ((MAX - image1) * (MAX - image2) / MAX)
```

### add [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.add)

**ImageChops.add(image1, image2, scale, offset)** ⇒ image

Adds two images, dividing the result by scale and adding the offset. If omitted, scale defaults to 1.0, and offset to 0.0.

```
    out = (image1 + image2) / scale + offset
```

### subtract [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.subtract)

**ImageChops.subtract(image1, image2, scale, offset)** ⇒ image

Subtracts two images, dividing the result by scale and adding the offset. If omitted, scale defaults to 1.0, and offset to 0.0.

```
    out = (image1 - image2) / scale + offset
```

### blend [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.blend)

**ImageChops.blend(image1, image2, alpha)** ⇒ image

Same as the **blend** function in the **Image** module.

### composite [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.composite)

**ImageChops.composite(image1, image2, mask)** ⇒ image

Same as the **composite** function in the **Image** module.

### offset [#](http://effbot.org/imagingbook/imagechops.htm#tag-ImageChops.offset)

**ImageChops.offset(image, xoffset, yoffset)** ⇒ image

**ImageChops.offset(image, offset)** ⇒ image

Returns a copy of the image where data has been offset by the given distances. Data wraps around the edges. If yoffset is omitted, it is assumed to be equal to xoffset.