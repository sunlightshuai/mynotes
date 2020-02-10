# 讲解

## 使用图像类

Python Imaging Library中最重要的类是Image类。通过从文件加载图像，处理其他图像或从头开始创建图像。

使用方式。

```python
from PIL import Image
img = Image.open("photo/u1.jpg")
```

如果成功，此函数将返回一个**Image**对象。

```python
print(img)
print(img.format)
print(img.size)
print(img.mode)
```

结果

```python
<PIL.JpegImagePlugin.JpegImageFile image mode=RGB size=1080x1440 at 0x1C795CCAA90>
JPEG
(1080, 1440)
RGB
```

**对象**本身是image，以及标记的相关属性，模式，大小。

**格式**属性标识的图像的源。如果未从文件读取图像，则将其设置为“无”。

**大小**属性是含有宽度和高度（以像素为单位）的2元组。

**模式**属性定义的数量和图像中的频带的名字，并且还像素类型和深度。常见模式是灰度图像的“ L”（亮度），真彩色图像的“ RGB”和 印前图像的“ CMYK”。

如果无法打开文件，则会**引发IOError**异常。

将图片加载为python的**Image**类实例，就可以使用该类定义的方法来处理图像。例如，显示上述加载的图像：

```python
img.show()
```

## 读写图像

python Imaging Library支持多种图像文件格式。从磁盘读取文件，使用 **图像**模块中的读取功能。该库可以根据文件的内容自动确定格式。

要保存文件，请使用**Image**类的保存方法 。保存文件时，名称变得很重要。除非指定格式，否则库将使用文件扩展名来发现要使用的文件存储格式。

**将文件转换为JPEG**

```python
import os, sys
import Image
fileList = os.listdir("photo/one")
for infile in fileList:
    f, e = os.path.splitext(infile)
    outfile = f + ".jpg"
    if infile != outfile:
        try:
            Image.open(infile).save(outfile)
        except IOError:
            print "cannot convert", infile
```

**创建JPEG缩略图**

```python
import os
from PIL import Image
size = 450, 450
for root, dirs, fileList in os.walk("photo/one"):
    for infile in fileList:
        outfile = os.path.splitext(infile)[0] + ".JPEG"
        if infile != outfile:
            print(outfile, infile)
            try:
                im = Image.open(root+"/"+infile)
                im.thumbnail(size)
                im.save(outfile, "JPEG")
            except IOError as e:
                print("cannot create thumbnail for", e)
```

**识别图像文件**

```python
import os
from PIL import Image
for root, dirs, fileList in os.walk("photo/one"):
    for infile in fileList:
        try:
            im = Image.open(root+"/"+infile)
            print(infile, im.format, "%dx%d" % im.size, im.mode)
        except IOError:
            pass
```

## 剪切，粘贴和合并图像

**从图像复制子矩形**

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
box = (100, 100, 400, 400)
# 返回此图像的矩形区域。 box是一个定义左，上，右和下像素的4元组坐标
region = im.crop(box)
region.show()
```

**处理子矩形，然后将其粘贴回**

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
box = (100, 100, 400, 400)
# 返回此图像的矩形区域。 box是一个定义左，上，右和下像素的4元组坐标
region = im.crop(box)
# 反转稀疏矩阵的维数
region = region.transpose(Image.ROTATE_180)
im.paste(region, box)
im.show()
```

粘贴区域时，区域的大小必须与给定区域完全匹配。另外，该区域不能延伸到图像之外。但是，原始图像的模式和区域不需要匹配。如果没有，则在粘贴之前会自动转换区域。

**横向滚动图像**

```python
from PIL import Image
def roll(image, delta):
    "横向滚动图像"
    xsize, ysize = image.size
    delta = delta % xsize
    if delta == 0: return image
    part1 = image.crop((0, 0, delta, ysize))
    part2 = image.crop((delta, 0, xsize, ysize))
    image.paste(part2, (0, 0, xsize-delta, ysize))
    image.paste(part1, (xsize-delta, 0, xsize, ysize))
    return image
im = roll(Image.open("photo/one/u1.jpg"),470)
im.show()
```

**拆分和合并**

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
r, g, b = im.split()
# 将一组单波段图像合并为一个新的多波段图像。
im = Image.merge("RGB", (b, g, r))
im.show()
```

## 几何变换

**图片**包含的方法用来**调整大小**和 **旋转**图像

**调整大小**采用元组给出新的大小

**旋转**以逆时针角度为单位

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
# 调整大小
out = im.resize((128, 128))
# 旋转
out = im.rotate(20)
# 旋转90度out = im.transpose(Image.ROTATE_90)
# print(out.size, out.mode, out.format)
out.show()
```

要以90度为步长旋转图像，可以使用**rotate**方法或**transpose**方法。后者还可以用于围绕图像的水平或垂直轴翻转图像。

```python
out = im.transpose（Image.FLIP_LEFT_RIGHT）
out = im.transpose（Image.FLIP_TOP_BOTTOM）
out = im.transpose（Image.ROTATE_90）
out = im.transpose（Image.ROTATE_180）
out = im.transpose（Image.ROTATE_270）
```

在**transpose（ROTATE）**和相应的**rotate** 操作之间，性能或结果没有差异。

## 颜色变换 

使用convert函数在不同的像素表示形式之间转换图像。

**在模式之间转换**

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg").convert("L")
im.show()
```

支持模式与“ L”和“ RGB”模式之间的转换

## 图像增强

### 过滤器

**ImageFilter**模块包含许多可与使用预先定义的增强滤波器**滤波**方法

**应用过滤器**

```python
from PIL import Image, ImageFilter
im = Image.open("photo/one/u1.jpg")
out = im.filter(ImageFilter.DETAIL)
out.show()
```

### 浮点运算 

可以用于转换的图像（例如图像对比度操纵）的像素值。在大多数情况下，可以将一个参数的函数对象传递给此方法，像素根据该功能进行处理：

**应用点变换**

```python
from PIL import Image, ImageFilter
im = Image.open("photo/one/u1.jpg")
out = im.point(lambda i: i * 1.5)
out.show()
```

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
# split the image into individual bands
source = im.split()
R, G, B = 0, 1, 2
# select regions where red is less than 100
mask = source[R].point(lambda i: i < 100 and 255)
# process the green band
out = source[G].point(lambda i: i * 0.7)
# paste the processed band back, but only where red was < 100
source[G].paste(out, None, mask)
# build a new multiband image
im = Image.merge(im.mode, source)
im.show()
```

### 增强 

要进行更高级的图像增强，可以使用[**ImageEnhance**](http://effbot.org/imagingbook/imageenhance.htm)模块中的类。从图像创建后，可以使用增强对象快速尝试不同的设置。可以调整对比度，亮度，色彩平衡和清晰度。

```python
from PIL import Image, ImageEnhance
im = Image.open("photo/one/u1.jpg")
enh = ImageEnhance.Contrast(im)
enh.enhance(1.3).show("30% more contrast")
```

## 图像序列

Python Imaging Library包含对图像序列（也称为*动画*格式）的一些基本支持。支持的序列格式包括FLI / FLC，GIF和一些实验格式。TIFF文件也可以包含多个帧。

打开序列文件时，PIL自动加载序列中的第一帧。可以使用[**seek**](http://effbot.org/imagingbook/image.htm#image-seek-method)和[**tell**](http://effbot.org/imagingbook/image.htm#image-tell-method)方法在不同框架之间移动。

```python
from PIL import Image
im = Image.open("photo/one/luFei.gif")
im.seek(1)
try:
    while 1:
        im.seek(im.tell()+1)
        im.show()
except EOFError as e:
    print(e)
```

# 概念

Python Imaging Library 处理像素

## Brands 

An image can consist of one or more bands of data. The Python Imaging Library allows you to store several bands in a single image, provided they all have the same dimensions and depth.

To get the number and names of bands in an image, use the [**getbands**](http://effbot.org/tag/PIL.Image.getbands) method.

## 模式

图像的模式定义图像中像素的类型和深度。当前版本支持以下标准模式：

- **1**（1位像素，黑白，每字节存储一个像素）
- **L**（8位像素，黑白）
- **P**（8位像素，使用调色板映射到任何其他模式）
- **RGB**（3x8位像素，真彩色）
- **RGBA**（4x8位像素，带透明蒙版的真彩色）
- **CMYK**（4x8位像素，彩色分离）
- **YCbCr**（3x8位像素，彩色视频格式）
- **I**（32位带符号整数像素）
- **F**（32位浮点像素）

PIL还支持一些特殊模式，包括**LA**（带alpha的L），**RGBX**（带填充的真彩色）和**RGBa**（带预乘alpha的真彩色）。但是，PIL不支持用户定义的模式。如果要处理上面未列出的波段组合，请使用一系列Image对象。

您可以通过[**mode**](http://effbot.org/tag/PIL.Image.Image.mode)属性读取图像的[**模式**](http://effbot.org/tag/PIL.Image.Image.mode)。这是一个包含上述值之一的字符串。

## 尺寸

您可以通过[**size**](http://effbot.org/tag/PIL.Image.Image.size) 属性读取图像尺寸。这是一个2元组，包含以像素为单位的水平和垂直大小。

## 坐标系

Python Imaging Library使用笛卡尔像素坐标系，左上角为（0,0）。注意，坐标指的是隐含的像素角。寻址为（0，0）的像素的中心实际上位于（0.5，0.5）。

坐标通常以2元组（x，y）的形式传递给库。矩形用4元组表示，左上角在前。例如，将覆盖所有800x600像素图像的矩形写为（0，0，800，600）。

## 调色板

调色板模式（“P”）使用调色板定义每个像素的实际颜色。

## 信息

可以使用[**info**](http://effbot.org/tag/PIL.Image.Image.info) 属性将辅助信息附加到图像。

## 过滤器

对于可能将多个输入像素映射到单个输出像素的几何运算，Python Imaging Library提供了四个不同的重采样*滤镜*。

- **NEAREST**

  从输入图像中选择最近的像素。忽略所有其他输入像素。

- **BILINEAR**

  在输入图像的2x2环境中使用线性插值。请注意，在当前版本的PIL中，该过滤器在下采样时会使用固定的输入环境。

- **BICUBIC**

  在输入图像的4x4环境中使用三次插值。请注意，在当前版本的PIL中，该过滤器在下采样时会使用固定的输入环境。

- **ANTIALIAS**

  （PIL 1.1.3中的新增功能）。在所有可能对输出值有贡献的像素上，使用高质量的重采样滤波器（截短的Sinc）来计算输出像素值。在当前版本的PIL中，此过滤器只能与**调整大小**和 **缩略图**方法一起使用。

请注意，在当前版本的PIL中，ANTIALIAS过滤器是唯一在下采样时（即，将大图像转换为小图像时）可以正常工作的过滤器。BILINEAR和BICUBIC滤波器使用固定的输入环境，最适合用于比例缩放的几何变换和上采样。