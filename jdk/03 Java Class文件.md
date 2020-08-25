# Java Class文件定义

Java Class 文件是Java程序的二进制文件格式的定义。

# Class文件的内容

Class File 中简介

（1）magic 魔数

每个Java Class文件的前4个字节被称为魔数：0xCAFEBABE。魔数在于分辨Java Class文件和非Java class文件。

（2）minor_version和major_version版本号

（3）constant_pool_count和constant_pool常量池

（4）access_flags定义class文件是类还是接口

（5）this_class常量池的索引

（6）super_class指向超类的常量池索引

（7）interfaces_count和interface在文件中该类直接实现或者由接口所扩展的父接口的数量

（8）fields_count和fields该文件中所声明的字段的描述

（9）methods_count和methods该文件中所声明的方法的描述

（10）attributes_count和attributes该文件中类或者接口所定义属性基本信息

# 特殊字符串

常量池中容纳的符号引用包括三种特殊的字符串：权限定名、简单名称和描述符。

## 全限定名

常量池指向类或者接口时，给出的是类或者接口的全限定名。在class文件中，全限定名中的点用斜线取代。例如，在class文件中，java.lang.Object的全限定名表示为java/lang/Object。

## 简单名称

字段名和方法名以简单名称形式出现在常量池入口中。例如，一个指向类java.lang.Object所属的toString()方法，常量池入口有一个"toString"的方法名。

## 描述符

解释字段的类型，方法的返回值和方法参数的数量、类型以及顺序。

# 常量池

常量池是一个可变长度的cp_info表的有序序列。

## CONSTANT_Utf8_info表

可变长度的CONSTANT_Utf8_info表使用一种UTF-8格式的来存储一个常量字符串。包括：

* 文字字符串
* 被定义的类和接口的权限定名
* 被定义的类和接口的父接口的全限定名
* 被定义的类和超类的全限定名
* 被定义的类和接口的父接口的全限定名
* 由类或者接口声明的任意字段的简单名称和描述符
* 由类或者接口声明的任意方法的简单名称和描述符
* 热河引用的类和接口的全限定名
* 任何引用的字段的简单名称和描述符
* 任何引用的方法的简单名称和描述符
* 与属性相关的字符串

## CONSTANT_Float_info表

固定长度用来存储常量float类型值

## CONSTANT_Long_info表

固定长度用于存储long类型的常量

## CONSTANT_Double_info表

固定长度用于存储double类型常量

## CONSTANT_Class_info表

固定长度使用符号引用来表示类或者接口

## CONSTANT_String_info表

固定长度用来表示文字字符串值

## CONSTANT_Fieldref_info表

固定长度描述了执行字段的符号引用

## CONSTANT_Methodref_info表

固定长度使用符号引用来表述勒种声明的方法

## CONSTANT_InterfaceMethodref_info表

固定长度使用符号引用来描述接口中声明的方法

## CONSTANT_NameAndType_info表

固定长度指向字段或者方法的符号引用的一部分，提供了所引用字段或者方法的简单名称和描述符的常量池入口

# 字段

在类或者接口声明的每一个字段都由class文件中的一个field_info的可变长度来描述。在一个class文件中，不会存在两个相同的名字和描述字段

# 方法

在class文件中，每个在类和接口中声明的方法，或者由编译器产生的方法，都由一个可变的长度来描述

# 属性

Java虚拟机定义了9中属性

| 名称               | 使用                   | 描述                             |
| ------------------ | ---------------------- | -------------------------------- |
| Code               | method_info            | 方法的字节码和其他数据           |
| ConstantValue      | field_info             | final变量的值                    |
| Deprecated         | field_info/method_info | 字段或者方法被禁用的指示符       |
| Exceptions         | method_info            | 方法可能抛出的可被检测的异常     |
| InnerClasses       | ClassFile              | 内部、外部类的列表               |
| LineNumberTable    | Code_attritute         | 方法的行号与字节码的映射         |
| LocalVariableTable | Code_attribute         | 方法的局部变量的描述             |
| SourceFile         | ClassFile              | 源文件名                         |
| Synthetic          | Field_info/method_info | 编译器产生的字段或者方法的指示符 |


