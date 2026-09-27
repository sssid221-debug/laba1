#!/usr/bin/env bash
# Сборка проекта в исполняемый JAR.
# Использование: ./build.sh
set -e

SRC_DIR="src"
OUT_DIR="out"
JAR_NAME="lab1.jar"
MANIFEST="manifest.mf"

echo "== Очистка каталога сборки =="
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

echo "== Компиляция исходников (javac -d $OUT_DIR) =="
find "$SRC_DIR" -name "*.java" > sources.txt
javac -d "$OUT_DIR" @sources.txt
rm -f sources.txt

echo "== Сборка исполняемого JAR =="
jar --create --file "$JAR_NAME" --manifest "$MANIFEST" -C "$OUT_DIR" .

echo "== Готово =="
echo "Запуск: java -jar $JAR_NAME"
