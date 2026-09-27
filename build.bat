@echo off
REM Сборка проекта в исполняемый JAR.
REM Использование: build.bat

set SRC_DIR=src
set OUT_DIR=out
set JAR_NAME=lab1.jar
set MANIFEST=manifest.mf

echo == Очистка каталога сборки ==
if exist %OUT_DIR% rmdir /s /q %OUT_DIR%
mkdir %OUT_DIR%

echo == Компиляция исходников (javac -d %OUT_DIR%) ==
dir /s /b %SRC_DIR%\*.java > sources.txt
javac -d %OUT_DIR% @sources.txt
del sources.txt

echo == Сборка исполняемого JAR ==
jar --create --file %JAR_NAME% --manifest %MANIFEST% -C %OUT_DIR% .

echo == Готово ==
echo Запуск: java -jar %JAR_NAME%
