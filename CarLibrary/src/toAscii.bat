@echo off
rem =========================================================================
rem =   用于将webwork下的messages_zh_CN.properties资料文件转化成Ascii码     =
rem =                            chenf 20060102                             =
rem =========================================================================
echo 检查环境...
if not "%JAVA_HOME%" == "" goto okHome
echo 检查不到[JAVA_HOME]环境,请按照下方提供做
echo 右击[我的电脑]选择[属性]-[环境变量],新建以JAVA_HOME为名称,值为jsdk的安装路径.
goto end
:okHome
if exist "%JAVA_HOME%\bin\native2ascii.exe" goto okCommand
echo 出错：找不到%JAVA_HOME%\bin\native2ascii.exe文件
goto end
:okCommand
set path = %JAVA_HOME%\bin
if exist "tmp.txt" goto okFile
goto failFile
:failFile
echo 出错：找不到tmp.txt文件
goto end
:okFile
echo 开始将[tmp]转换成Ascii码的资料文件[messages_zh_CN.properties]...
native2ascii.exe tmp.txt messages_zh_CN.properties
echo 转换完成！
goto succeed
:end
echo 转换失败！
:succeed