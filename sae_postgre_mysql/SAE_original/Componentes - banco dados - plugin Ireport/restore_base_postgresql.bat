cd "D:\PostgreSQL\9.5\bin"
SET PGUSER=postgres
SET PGPASSWORD=!191218bc
echo "Aguarde, realizando o volta do Banco de Dados"
psql SAE < "D:\Banco_dados.sql"
pause
