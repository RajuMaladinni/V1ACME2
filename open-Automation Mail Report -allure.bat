@echo off
cd /d "%~dp0"
echo ========================================
echo    GENERATING ALLURE REPORT FOR EMAIL
echo ========================================

echo Current directory: %CD%
echo.

:: Check if Allure is installed
allure --version >nul 2>&1
if errorlevel 1 (
    echo ✗ ERROR: Allure is not installed or not in PATH
    pause
    exit /b 1
)

:: Create timestamp for unique folder name
for /f "tokens=1-3 delims=/" %%a in ('date /t') do set DATE=%%c-%%a-%%b
for /f "tokens=1-2 delims=:" %%a in ('time /t') do set TIME=%%a-%%b
set TIMESTAMP=%DATE%_%TIME%
set "REPORT_FOLDER=Allure_Report_%TIMESTAMP%"

echo ✓ Timestamp: %TIMESTAMP%
echo ✓ Report folder: %REPORT_FOLDER%
echo.

:: Generate the report
if exist "target\allure-results" (
    echo ✓ Generating portable Allure report...
    allure generate target\allure-results --clean -o "%REPORT_FOLDER%"
    
    :: Create zip file
    echo ✓ Creating zip archive...
    powershell -Command "Compress-Archive -Path '%REPORT_FOLDER%' -DestinationPath '%REPORT_FOLDER%.zip' -Force"
    
    echo.
    echo ✓ REPORT GENERATED SUCCESSFULLY!
    echo ✓ Folder: %REPORT_FOLDER%
    echo ✓ Zip file: %REPORT_FOLDER%.zip
    echo.
    echo 📧 You can now:
    echo   1. Attach the zip file to email
    echo   2. Or upload the folder to a web server
    echo   3. Open %REPORT_FOLDER%\index.html to verify
    
) else if exist "allure-results" (
    echo ✓ Generating portable Allure report...
    allure generate allure-results --clean -o "%REPORT_FOLDER%"
    
    :: Create zip file
    echo ✓ Creating zip archive...
    powershell -Command "Compress-Archive -Path '%REPORT_FOLDER%' -DestinationPath '%REPORT_FOLDER%.zip' -Force"
    
    echo.
    echo ✓ REPORT GENERATED SUCCESSFULLY!
    echo ✓ Folder: %REPORT_FOLDER%
    echo ✓ Zip file: %REPORT_FOLDER%.zip
    
) else (
    echo ✗ ERROR: No test results found!
    echo Please run tests first: mvn test
    pause
    exit /b 1
)

:: Open the generated report folder
explorer .
pause