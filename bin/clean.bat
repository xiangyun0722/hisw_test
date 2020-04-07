@echo off
rem /**
rem  * 
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] �������·����
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean

cd bin
pause