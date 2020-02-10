# The Image Module

**图像**模块提供与表示图像PIL相同名称的类。该模块还提供了许多出厂功能，包括从文件加载图像和创建新图像的功能。

## 范例

**打开，旋转和显示图像（使用默认查看器）**

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
im.rotate(45).show()
```

**创建缩略图**

```python
from PIL import Image
import glob, os
size = 128, 128
for infile in glob.glob("photo/one/*.jpg"):
    file, ext = os.path.splitext(infile)
    im = Image.open(infile)
    im.thumbnail(size, Image.ANTIALIAS)
    im.save(file + ".JPEG", "JPEG")
```

## 功能

### new

**Image.new(mode, size)** ⇒ image

**Image.new(mode, size, color)** ⇒ image

使用给定的模式和尺寸创建一个新图像。大小以（宽度，高度）元组给出，以像素为单位。对于单波段图像，颜色作为单个值给出，对于多波段图像，颜色作为一个元组给出（每个波段一个值）。在1.1.4及更高版本中，还可以使用颜色名称（有关详细信息，详见[**ImageColor**](http://effbot.org/imagingbook/imagecolor.htm)模块文档）。如果省略了color参数，则图像将填充为零（通常对应于黑色）。如果颜色为无，则不会初始化图像。如果要在图像中粘贴或绘制东西，这将很有用。

```python
from PIL import Image
im = Image.new("RGB", (512, 512), "red")
im.show()
```

### open

**Image.open(file)** ⇒ image

**Image.open(file, mode)** ⇒ image

打开并标识给定的图像文件。 是一个懒操作； 该函数读取文件头，尝试处理数据之前不会从文件中读取实际图像数据（调用load方法强制加载）。 如果给出了mode参数，则必须为“ r”。

可以使用字符串（代表文件名）或文件对象作为文件参数。 在后一种情况下，文件对象必须实现读取，查找和告诉方法，并以二进制模式打开。

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
```

### blend

**Image.blend(image1, image2, alpha)** ⇒ image

通过使用常数alpha在给定图像之间进行插入固定的值来创建新图像。 两个图像必须具有相同的大小和模式。

**公式**

```python
out = image1 * (1.0 - alpha) + image2 * alpha
```

如果alpha为0.0，则返回第一张图像的副本。 如果alpha为1.0，则返回第二张图像的副本。 alpha值没有限制。 如有必要，将结果裁剪以适合允许的输出范围。

```python
from PIL import Image
im1 = Image.new("RGB", (512, 512), "red")
im2 = Image.new("RGB", (512, 512), "blue")
im = Image.blend(im1, im2, 0.5)
im.show()
```

### composite

**Image.composite(image1, image2, mask)** ⇒ image

通过使用给定图像之间的插值来创建新图像，并将蒙版图像中的相应像素用作alpha。 遮罩可以具有模式“ 1”，“ L”或“ RGBA”。 所有图像的尺寸必须相同。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im2 = Image.open("photo/one/u2.jpg")
r, g, b = im1.split()
im = Image.composite(im1, im2, g)
im.show()
```

### eval

**Image.eval(image, function)** ⇒ image

将函数（应使用一个参数）应用于给定图像中的每个像素。 如果图像有多个波段，则对每个波段都应用相同的功能。 请注意，该函数会针对每个可能的像素值进行一次评估，因此不能使用随机分量或其他生成器。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im = Image.eval(im1, lambda i: i*2)
im.show()
```

### frombuffer

**Image.frombuffer(mode, size, data)** ⇒ image

（PIL 1.1.4中的新增功能） 使用标准的“原始”解码器，根据字符串或缓冲区对象中的像素数据创建图像存储器。 对于某些模式，图像内存将与原始缓冲区共享内存（这意味着对原始缓冲区对象的更改会反映在图像中）。 并非所有模式都可以共享内存。 支持的模式包括“ L”，“ RGBX”，“ RGBA”和“ CMYK”。 对于其他模式，此函数的行为类似于对fromstring函数的相应调用。

注意：在1.1.6及以下版本中，默认方向与fromstring不同。 在将来的版本中可能会对此进行更改，因此，为了最大程度地提高便携性，建议在使用“原始”解码器时拼出所有参数：

```
im = Image.frombuffer(mode, size, data, "raw", mode, 0, 1)
```

**Image.frombuffer(mode, size, data, decoder, parameters)** ⇒ image

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
r, g, b = im1.split()
im2 = Image.frombuffer(im1.mode, im1.size, im1.tobytes(), 'raw', im1.mode, 0, 1)
im2.show()
```

### merge

**Image.merge(mode, bands)** ⇒ image

从多个单波段图像创建一个新图像。 这些波段以元组或图像列表的形式给出，由模式描述的每个波段一个。 所有频段必须具有相同的大小。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im2 = Image.open("photo/one/u2.jpg")
im3 = Image.merge(im2.mode, im1.split())
im3.show()
```

## 方法

Image类的实例具有以下方法。 除非另有说明，否则所有方法都将返回Image类的新实例，其中包含生成的图像。

### convert

**im.convert(mode)** ⇒ image

将图像转换为另一种模式，然后返回新图像。

从调色板图像转换时，这会转换调色板中的像素。如果省略模式，则选择一种模式，以便无需调色板即可表示图像和调色板中的所有信息。

从彩色图像转换为黑白图像时，该库使用ITU-R 601-2亮度转换：

```
L = R * 299/1000 + G * 587/1000 + B * 114/1000
```

当转换为双层图像（模式“ 1”）时，源图像首先转换为黑白图像。然后将大于127的结果值设置为白色，并对图像进行抖动处理。要使用其他阈值，请使用点方法。要禁用抖动，请使用dither =选项（请参见下文）。

**im.convert(“P”, \**options)** ⇒ image

相同，但是在将“ RGB”图像转换为8位调色板图像时可提供更好的控制。可用的选项有：

**dither=**.控制抖动。默认值为FLOYDSTEINBERG，它将错误分布到相邻像素。要禁用抖动，请使用NONE。

**palette=**.控制调色板的生成。默认值为WEB，这是标准的216色“ Web调色板”。要使用优化的调色板，请使用ADAPTIVE。

**colors=**.当调色板为自适应时，控制用于调色板的颜色数。默认为最大值，即256色。

**im.convert(mode, matrix)** ⇒ image

使用转换矩阵将“ RGB”图像转换为“ L”或“ RGB”。矩阵是4或16元组。

以下示例将RGB图像（使用D65光源根据ITU-R 709线性校准）转换为CIE XYZ颜色空间：

**将RGB转换为XYZ**

```python
rgb2xyz = (
    0.412453, 0.357580, 0.180423, 0,
    0.212671, 0.715160, 0.072169, 0,
    0.019334, 0.119193, 0.950227, 0)
out = im.convert("RGB", rgb2xyz)
```

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im2 = Image.open("photo/one/u2.jpg")
rgb2xyz = (
    0.412453, 0.357580, 0.180423, 0,
    0.212671, 0.715160, 0.072169, 0,
    0.019334, 0.119193, 0.950227, 0)
out = im2.convert("RGB", rgb2xyz)
# out = im1.convert("RGB", rgb2xyz)
out.show()
```

### copy

**im.copy()** ⇒ image

复制图像。 如果希望将东西粘贴到图像中，但仍保留原始图像，请使用此方法。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
out = im1.copy()
out.show()
```

### crop

**im.crop(box)** ⇒ image

返回当前图像中矩形区域的副本。 该框是一个四元组，定义了左，上，右和下像素坐标。

这是一个懒惰的操作。 对源图像的更改可能会也可能不会反映在裁剪后的图像中。 要获取单独的副本，请在裁剪后的副本上调用load方法。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
box = (200, 200, 650, 650)
out = im1.crop(box)
out.show()
```

### draft

**im.draft(mode, size)**

配置图像文件加载器，使其返回与给定模式和大小尽可能匹配的图像版本。 例如，您可以使用此方法在加载彩色JPEG时将其转换为灰度，或从PCD文件中提取128x192版本。

请注意，此方法修改了Image对象的位置（确切地说，它重新配置了文件读取器）。 如果图像已经加载，则此方法无效。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
out = im1.draft(im1.mode, (290, 290))
out.show()
```

### filter

**im.filter(filter)** ⇒ image

返回由给定过滤器过滤的图像的副本。 有关可用滤镜的列表，请参见ImageFilter模块。

```python
from PIL import Image, ImageFilter
im1 = Image.open("photo/one/u1.jpg")
out = im1.filter(ImageFilter.EDGE_ENHANCE_MORE)
out.show()
```

### getbands

**im.getbands()** ⇒ 字符串元组

返回一个包含每个band名称的元组。 例如，返回RGB图像上的getband（“ R”，“ G”，“ B”）。

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
out = im.getbands()
print(out)
```

结果

```
('R', 'G', 'B')
```

### getbbox

**im.getbbox()** ⇒ 4元组或无

计算图像中非零区域的边界框。 边框以4元组的形式返回，定义了左，上，右和下像素坐标。 如果图像完全为空，则此方法返回None。

```
from PIL import Image
im = Image.open("photo/one/u1.jpg")
out = im.getbbox()
print(out)
```

结果

```python
(0, 0, 1080, 1440)
```

### getcolors

**im.getcolors()** ⇒ （计数，颜色）元组的列表或无

**im.getcolors(maxcolors)** ⇒ （计数，颜色）元组的列表或无

（1.1.5中的新增功能）返回（计数，颜色）元组的未排序列表，其中count是图像中相应颜色出现的次数。

如果超过了maxcolors值，则该方法停止计数并返回None。 maxcolors的默认值为256。为确保获得图像中的所有颜色，可以传入size [0] * size [1]（但在大图像上进行操作之前，请确保有足够的内存）。

```python
from PIL import Image
im = Image.open("photo/one/u2.JPEG")
out = im.getcolors()
print(out)
```

### getdata

**im.getdata()** ⇒ 顺序

以包含像素值的序列对象的形式返回图像的内容。 序列对象被展平，因此第一行的值紧随零行的值，依此类推。

请注意，此方法返回的序列对象是内部PIL数据类型，它仅支持某些序列操作，包括迭代和基本序列访问。 要将其转换为普通序列（例如用于打印），请使用list（im.getdata（））。

```python
from PIL import Image
im = Image.open("photo/one/u2.JPEG")
out = im.getdata()
print(list(out))
```

### getextrema

**im.getextrema()** ⇒ 2元组

返回一个2元组，其中包含图像的最小值和最大值。 在当前版本的PIL中，这仅适用于单波段图像。

```python
from PIL import Image
im = Image.open("photo/one/u2.JPEG")
out = im.getextrema()
print(out)
```

结果

```python
((0, 255), (0, 255), (0, 255))
```

### getpixel

**im.getpixel(xy)** ⇒ 值或元组

返回给定位置的像素。 如果图像是多层图像，则此方法返回一个元组。请注意，此方法相当慢。

```python
from PIL import Image
im = Image.open("photo/one/u2.jpg")
out = im.getpixel((200, 200))
print(out)
```

结果

```python
(184, 184, 184)
```

### histogram

**im.histogram()** ⇒ list

返回图像的直方图。 直方图作为像素计数列表返回，源图像中的每个像素值一个。 如果图像具有多个波段，则将所有波段的直方图连接起来（例如，“ RGB”图像的直方图包含768个值）。
通过这种方法，将两级图像（模式“ 1”）视为灰度（“ L”）图像。

```python
from PIL import Image
im = Image.open("photo/one/u2.jpg")
out = im.histogram()
print(out)
```

**im.histogram(mask)** ⇒ list

返回蒙版图像非零的图像部分的直方图。 遮罩图像必须具有与图像相同的尺寸，并且可以是双层图像（模式“ 1”）或灰度图像（“ L”）。

```python
from PIL import Image
im = Image.open("photo/one/u2.jpg")
source = im.split()
R, G, B = 0, 1, 2
out = im.histogram(source[G])
print(out)
```

### load

**im.load()**

为图像分配存储空间，并从文件（或从源数据，对于惰性操作）加载图像。 通常情况下，您无需调用此方法，因为Image类在首次访问时会自动加载打开的图像。

（1.1.6中的新增功能）在1.1.6及更高版本中，load返回一个像素访问对象，该对象可用于读取和修改像素。 访问对象的行为类似于二维数组，可以执行以下操作：

```python
from PIL import Image
im = Image.open("photo/one/u1.jpg")
pix = im.load()
print(pix[1, 1])
pix[1, 1] = (0, 0, 0)
print(pix[1, 1])
```

### offset

**im.offset(xoffset, yoffset)** ⇒ image

（不推荐使用）返回图像的副本，其中数据已偏移给定距离。 数据环绕边缘。 如果省略yoffset，则假定它等于xoffset。

不推荐使用此方法，并且已在PIL 1.2中将其删除。 新代码应使用ImageChops模块中的offset函数。

### paste

**im.paste(image, box)**

将另一个图像粘贴到该图像中。 box参数可以是2元组（给出左上角），4元组（定义左，上，右和下像素坐标），或者是None（与（0，0）相同）。 如果指定了4元组，则粘贴图像的大小必须与区域的大小匹配。

如果模式不匹配，则粘贴的图像将转换为该图像的模式（有关详细信息，请参见convert方法）。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im2 = Image.open("photo/one/u2.jpg")
out = im1.paste(im2, (100, 200))
im1.show()
```

**im.paste(image, box, mask)**

与上述相同，但仅更新蒙版指示的区域。 您可以使用“ 1”，“ L”或“ RGBA”图像（在后一种情况下，alpha波段用作遮罩）。 蒙版为255时，将按原样复制给定图像。 掩码为0时，将保留当前值。 中间值可用于透明效果。

请注意，如果粘贴“ RGBA”图像，则将忽略Alpha波段。 您可以通过使用与源图像和蒙版相同的图像来解决此问题。

```python
from PIL import Image
im1 = Image.open("photo/one/u1.jpg")
im2 = Image.open("photo/one/u2.jpg")
source = im2.split()
out = im1.paste(im2, (100, 200), source[1])
im1.show()
```

### point

**im.point(table)** ⇒ image

**im.point(function)** ⇒ image

返回图像的副本，其中每个像素都已通过给定的查找表进行了映射。该表应在图像中每个波段包含256个值。如果改用函数，则应使用单个参数。对每个可能的像素值调用一次该函数，并将结果表应用于图像的所有波段。

如果图像的模式为“ I”（整数）或“ F”（浮点数），则必须使用一个函数，并且该函数必须具有以下格式：

    参数*比例+偏移
例：

    out = im.point（lambda i：i * 1.2 + 10）
您可以忽略比例尺或偏移量。

im.point（表格，模式）⇒图片

im.point（功能，模式）⇒图像

与上述相同，但为输出图像指定新模式。这可用于一步将“ L”和“ P”图像转换为“ 1”，例如阈值图像。

（1.1.5中的新增功能）该格式还可用于将“ L”图像转换为“ I”或“ F”，并将具有16位数据的“ I”图像转换为“ L”。

### putdata

**im.putdata(data)**

**im.putdata(data, scale, offset)**

从左上角（0，0）开始，将序列对象中的像素值复制到图像中。 标度和偏移量值用于调整序列值：

     像素=值*比例+偏移

如果省略比例，则默认为1.0。 如果省略偏移量，则默认为0.0。

### putpalette

**im.putpalette(sequence)**

将调色板附加到“ P”或“ L”图像。 对于“ L”图像，模式更改为“ P”。 调色板序列应包含768个整数值，其中三个值的每组代表相应像素索引的红色，绿色和蓝色值。 可以使用768字节的字符串代替整数序列。

### putpixel

**im.putpixel(xy, colour)**

修改指定位置的像素。 对于单波段图像，颜色作为单个数值给出，对于多波段图像，颜色作为元组给出。

请注意，此方法相对较慢。 如果使用的是1.1.6，则像素访问对象（请参阅**负载**）提供了一种修改图像的更快方法。 如果要生成整个图像，则创建Python列表并使用**putdata**将其复制到图像会更有效。 要进行更广泛的更改，请改用**paste **或**ImageDraw**模块。

可以通过“内联”内部**putpixel **实现方法的调用来加快**putpixel**的速度：

```
    im.load()
    putpixel = im.im.putpixel
    for i in range(n):
       ...
       putpixel((x, y), value)
```

在1.1.6中，以上内容最好写为:

```
    pix = im.load()
    for i in range(n):
        ...
        pix[x, y] = value
```

### quantize

**im.quantize(colors, \**options)** ⇒ image

（不推荐使用）将具有给定颜色数量的“ L”或“ RGB”图像转换为“ P”图像，并返回新图像：

```
out = im.convert("P", palette=Image.ADAPTIVE, colors=256)
```

### resize 

**im.resize(size)** ⇒ image

**im.resize(size, filter)** ⇒ image

返回图像的调整大小后的副本。size参数以2个元组的形式给出请求的大小（以像素为单位）：（**width**，**height **）。

### rotate

**im.rotate(angle)** ⇒ image

**im.rotate(angle, filter=NEAREST, expand=0)** ⇒ image

返回图像的副本，该图像围绕其中心逆时针旋转给定的度数。

### save

**im.save(outfile, options…)**

**im.save(outfile, format, options…)**

将图像保存在给定的文件名下。 如果省略format，则格式将根据文件名扩展名确定。 此方法返回无。

如果由于某种原因保存失败，则该方法将引发异常（通常为**IOError**异常）。 如果发生这种情况，则该方法可能已经创建了文件，并且可能已经向其中写入了数据。 必要时，由您的应用程序删除不完整的文件

### seek

**im.seek(frame)**

在序列文件中查找给定的帧。 如果您在序列的末尾进行查找，则该方法将引发**EOFError**异常。 打开序列文件后，库自动搜索第0帧。

### show

**im.show()**

显示图像。 此方法主要用于调试目的。

在Unix平台上，此方法将图像保存到临时PPM文件中，并调用**xv**实用程序。

在Windows上，它将图像保存到临时BMP文件，并使用标准的BMP显示实用程序来显示它。

此方法返回无。

### split

**im.split()** ⇒ sequence

返回图像中各个图像带的元组。 例如，分割“RGB”图像会创建三个新图像，每个图像都包含一个原始波段（红色，绿色，蓝色）的副本。

### tell

**im.tell()** ⇒ integer

返回当前帧号。

### thumbnail

**im.thumbnail(size)**

**im.thumbnail(size, filter)**

修改图像以使其本身包含缩略图，不大于给定的大小。 此方法计算适当的缩略图大小以保留图像的外观，调用**draft**方法配置文件读取器（如果适用），最后调整图像大小。.

### tobitmap

**im.tobitmap()** ⇒ string

返回转换为X11位图的图像。

### tostring

**im.tostring()** ⇒ string

使用标准的“原始”编码器返回包含像素数据的字符串。

**im.tostring(encoder, parameters)** ⇒ string

使用给定的数据编码返回包含像素数据的字符串。

**注意：**“ tostring”方法仅获取原始像素数据。 要将图像保存为标准文件格式的字符串，请将StringIO对象（或等效对象）传递给**save**方法。

### transform

**im.transform(size, method, data)** ⇒ image

**im.transform(size, method, data, filter)** ⇒ image

创建具有给定大小和与原始模式相同的新图像，并使用给定的变换将数据复制到新图像。

**im.transform(size, EXTENT, data)** ⇒ image

**im.transform(size, EXTENT, data, filter)** ⇒ image

从图像中提取一个子区域。

**数据**是一个四元组（*x0，y0，x1，y1*），用于指定输入图像坐标系中的两个点。 生成的图像将包含从这两点之间采样的数据，以使输入图像中的（*x0，y0*）最终位于输出图像中的（0,0），而（*x1，y1*）则位于*尺寸*。

此方法可用于裁剪，拉伸，缩小或镜像当前图像中的任意矩形。 它比crop稍慢一些，但与相应的resize操作一样快。

**im.transform(size, AFFINE, data)** ⇒ image

**im.transform(size, AFFINE, data, filter)** ⇒ image

对图像应用仿射变换，并将结果放入具有给定大小的新图像中。

*Data*是一个6元组（*a，b，c，d，e，f*），其中包含仿射变换矩阵的前两行。 对于输出图像中的每个像素(*x, y*)，新值取自图像中的位置（a * x * + b * y * + c，d * x * + e * y * + f） 输入图像，四舍五入到最接近的像素。

此功能可用于缩放，平移，旋转和剪切原始图像

**im.transform(size, QUAD, data)** ⇒ image

**im.transform(size, QUAD, data, filter)** ⇒ image

将图像的四边形（由四个角定义的区域）映射到具有给定大小的矩形。

*Data*是一个8元组（*x0，y0，x1，y1，x2，y2，y3，y3*），其中包含源四边形的左上角，左下角，右下角和右上角。

**im.transform(size, MESH, data) image** ⇒ image

**im.transform(size, MESH, data, filter) image** ⇒ image

与**QUAD**相似，但数据是目标矩形和相应的源四边形的列表。

**im.transform(size, PERSPECTIVE, data) image** ⇒ image

**im.transform(size, PERSPECTIVE, data, filter) image** ⇒ image

对图像应用透视变换，并将结果放入具有给定大小的新图像中。

数据是一个8元组（*a，b，c，d，e，f，g，h*），其中包含用于透视变换的系数。 对于输出图像中的每个像素（*x，y*），从（a * x * + b * y * + c）/（g * x * + h * y * + 1）位置获取新值 ，（d * x * + e * y * + f）/（g * x * + h * y * + 1），四舍五入到最接近的像素。

此功能可用于更改原始图像的2D透视图。

### transpose

**im.transpose(method)** ⇒ image

返回图像的翻转或旋转副本。

*方法*可以是以下之一：**FLIP_LEFT_RIGHT**,**FLIP_TOP_BOTTOM**, **ROTATE_90**, **ROTATE_180**, or **ROTATE_270**

### verify

**im.verify()**

尝试确定文件是否损坏，而无需实际解码图像数据。 如果此方法发现任何问题，它将引发适当的异常。 此方法仅适用于新打开的图像。 如果图像已经加载，则结果不确定。 另外，如果在使用此方法后需要加载图像，则必须重新打开图像文件。

请注意，此方法无法捕获所有可能的错误； 为了捕获解码错误，您可能还必须加载整个图像。

## Attributes

**Image**类的实例具有以下属性：

### format

**im.format** ⇒ string or None

源文件的文件格式。 对于由库本身（通过工厂功能或通过在现有图像上运行方法）创建的图像，此属性设置为None。

### mode

**im.mode** ⇒ string

图像模式。 这是一个字符串，指定图像使用的像素格式。 典型值为“ 1”，“ L”，“ RGB”或“ CMYK”。

### size

**im.size** ⇒ (width, height)

图片大小，以像素为单位。 大小以2元组（宽度，高度）给出。

### palette

**im.palette** ⇒ palette or None

调色板表（如果有）。 如果mode为“ P”，则它应该是**ImagePalette**

### info

**im.info** ⇒ dictionary

包含与图像关联的数据的字典。 文件处理程序使用此字典来传递从文件读取的各种非图像信息。 有关详细信息，请参见各种文件处理程序的文档。