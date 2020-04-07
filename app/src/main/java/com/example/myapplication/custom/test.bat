@echo off
set tempfile=%temp%\tempfile.txt
set fextion=java
if exist %tempfile% 1 0>%tempfile% 2>nul
 
for %%i in (*.%fextion%) do @(
    set /p=%%i  <nul
    type %%i|find /v "" /c
)>>%tempfile%
echo %cd%目录下答所有.%fextion%信息已全部存入文%tempfile%
echo 是否查看
choice
if "%errorlevel%"=="2" pause&exit /b 0
type %tempfile%
pause