@echo off
rem Licensed to the Apache Software Foundation (ASF) under one or more
rem contributor license agreements.  See the NOTICE file distributed with
rem this work for additional information regarding copyright ownership.
rem The ASF licenses this file to You under the Apache License, Version 2.0
rem (the "License"); you may not use this file except in compliance with
rem the License.  You may obtain a copy of the License at
rem
rem     http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.

rem ---------------------------------------------------------------------------
rem eXTra Startscript. Voraussetzung ist die Setzung der Umgebungsvariable 
rem EXTRA_CLIENT_HOME.
rem
rem revision: $Id$
rem version ${project.artifactId}-${project.version}
rem ---------------------------------------------------------------------------

set RETURN_CODE_ERROR=32

:logo
echo         ____  ______________              
echo    ____ ^\   ^\/  /^\__    ___/___________   
echo  _/ __ ^\ ^\     /   ^|    ^|  ^\_  __ ^\__  ^\  
echo  ^\  ___/ /     ^\   ^|    ^|   ^|  ^| ^\// __ ^\_
echo   ^\___  ^>___/^\  ^\  ^|____^|   ^|__^|  (____  /
echo       ^\/      ^\_/                      ^\/ 
echo 01100101 01011000 01010100 01110010 01100001 
echo.

rem EXTRA_CLIENT_HOME muss gesetzt sein.
if "%EXTRA_CLIENT_HOME%" == "" goto env_undefined

rem Java suchen
if not "%JRE_HOME%" == "" goto gotJreHome
if not "%JAVA_HOME%" == "" goto gotJavaHome
echo Weder JAVA_HOME noch JRE_HOME Umgebungsvariablen sind definiert.
goto exit

:gotJavaHome
set "JRE_HOME=%JAVA_HOME%"

:gotJreHome
if not exist "%JRE_HOME%\bin\java.exe" goto noJreHome
if not exist "%JRE_HOME%\bin\javaw.exe" goto noJreHome
goto okJava

:env_undefined
echo Bitte die Umgebungsvariable EXTRA_CLIENT_HOME setzen.
exit /b %RETURN_CODE_ERROR%

:noJreHome
rem Needed at least a JRE
echo Die Umgebungsvariable JRE_HOME ist nicht korrekt definiert.
goto exit

:okJava
set _JAVA="%JRE_HOME%\bin\java"
set HEAP_MS=-Xms512m
set HEAP_MX=-Xmx1024m
if "%JAVA_OPTS%" == "" set JAVA_OPTS=%HEAP_MS% %HEAP_MX%

rem java call
echo %_JAVA% %JAVA_OPTS% -classpath "%EXTRA_CLIENT_HOME%\lib\*" de.extra.client.starter.ClientStarter %*
%_JAVA% %JAVA_OPTS% -classpath "%EXTRA_CLIENT_HOME%\lib\*" de.extra.client.starter.ClientStarter %*
goto end

:exit
exit /b %RETURN_CODE_ERROR%

:end
if "%ERRORLEVEL%"=="1" exit /b %RETURN_CODE_ERROR%
if not "%ERRORLEVEL%"=="" echo exit mit code %ERRORLEVEL%
exit /b %ERRORLEVEL%

:exit
