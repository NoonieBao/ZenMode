#!/bin/bash

MODDIR=${0%/*}
cd "$MODDIR"



PackageName="com.cppzeal.forcesleep"
PackageDataDir=/data/data/$PackageName

Logname="boot.log"




su -c 'cp -r $MODDIR/data /' 

echo $PackageDataDir >> $Logname
echo $MODDIR/data / >> $Logname
echo "目录创建成功并文件成功复制到应用数据目录下" >> $Logname
