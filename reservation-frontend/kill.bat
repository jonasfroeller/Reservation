for /f "tokens=5" %a in ('netstat -ano ^| findstr :4200') do taskkill /PID %a /F
