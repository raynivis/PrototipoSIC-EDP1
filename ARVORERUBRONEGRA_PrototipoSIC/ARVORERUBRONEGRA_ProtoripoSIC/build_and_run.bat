@echo off
setlocal

set "srcDir=src"
set "libDir=lib"
set "binDir=bin"

if not exist "%binDir%" mkdir "%binDir%"

set "classpath=%libDir%/*"

echo Compiling...
javac -cp "%classpath%" -d "%binDir%" %srcDir%\Program.java %srcDir%\Estrutura\*.java %srcDir%\Importacoes\*.java %srcDir%\Individuo\*.java %srcDir%\Persistencia\*.java %srcDir%\Relatorio\*.java %srcDir%\Telas\*.java %srcDir%\Timer\*.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b %errorlevel%
)

echo Compilation finished.

echo Running...
java -cp "%binDir%;%classpath%" Program

pause
goto :eof
