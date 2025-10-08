@echo off
cd /d "%~dp0"
echo ========================================
echo        OPENING ALLURE REPORT
echo ========================================

echo Current directory: %CD%
echo.

:: Check if Allure is installed
allure --version >nul 2>&1
if errorlevel 1 (
    echo ✗ ERROR: Allure is not installed or not in PATH
    echo.
    echo To install Allure:
    echo 1. Install Node.js from https://nodejs.org/
    echo 2. Run: npm install -g allure-commandline
    echo 3. Restart and run this file again
    echo.
    pause
    exit /b 1
)

:: Get Allure version
for /f "tokens=*" %%i in ('allure --version') do set "ALLURE_VER=%%i"
echo ✓ Allure Version: !ALLURE_VER!
echo.

:: Check for results and serve the report
if exist "target\allure-results" (
    echo ✓ Test results found in target folder!
    echo ✓ Starting Allure server...
    allure serve target\allure-results
) else if exist "allure-results" (
    echo ✓ Test results found in project root!
    echo ✓ Starting Allure server...
    allure serve allure-results
) else (
    echo ✗ ERROR: No test results found!
    echo.
    echo Please run your tests first using: mvn test
    echo.
    echo Looking in:
    echo - target\allure-results
    echo - allure-results
    pause
)